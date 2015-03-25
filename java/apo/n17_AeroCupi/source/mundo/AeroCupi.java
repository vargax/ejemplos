package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import estructurasDatos.Dupla;
import estructurasDatos.grafos.Grafo;
import estructurasDatos.grafos.IGrafo;
import estructurasDatos.grafos.Vertice;
import estructurasDatos.listas.IListaEncadenada;
import estructurasDatos.listas.ListaEncadenada;
import estructurasDatos.listas.ListaEncadenadaOrdenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;
import excepciones.ElementoNoExisteException;

public class AeroCupi implements Observer {
	// --------------------------------------------------------
	// Constantes
	// --------------------------------------------------------
	//private final static String ARCHIVO_ORIGEN_XML = "./data/itinerario_aero_civil.xml";
//	private final static String ARCHIVO_ORIGEN_XML = "/home/cvargasc/Desarrollo/eclipse/n17_AeroCupi/data/itinerario_aero_civil.xml";
	private final static String ARCHIVO_ORIGEN_CSV = "/home/cvargasc/Desarrollo/eclipse/n17_AeroCupi/data/ItinerarioAeroCivil.csv";
	/**
	 * Referencia a la única instancia de la clase
	 * Implementación del patrón Singleton
	 */
	private static AeroCupi instancia = null;
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	/**
	 * El grafo que almacena la información de los vuelos
	 */
	private IGrafo<Aeropuerto, Ruta> grafo;
	/**
	 * Una lista para almacenar los nombres de los aeropuertos no 
	 * no son fuentes
	 */
	private IListaEncadenada<String> noFuentes;
	/**
	 * Una lista para almacenar los nombres de los aeropuertos que
	 * no son sumideros
	 */
	private IListaEncadenada<String> noSumideros;
	/**
	 * La fecha de partida de la busqueda actual
	 */
	private Date fechaPartida;
	/**
	 * La duración mínima de la busqueda actual
	 */
	private long duracionVuelos; private long duracionTotal;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	/**
	 * Constructor del mundo (patrón singleton)
	 */
	private AeroCupi() {
		grafo = new Grafo<Aeropuerto, Ruta>(this);
	}
	/**
	 * Método utilizado para obtener una referencia al mundo
	 * @return La instancia del mundo
	 */
	public static AeroCupi getInstance() {
		if (instancia == null) {
			System.out.println("AeroCupi.getInstance(): Construyendo una nueva instancia del mundo...");
			instancia = new AeroCupi();
			System.out.println("AeroCupi.getInstance(): Cargando la información de los vuelos...");
			instancia.cargarInfoVuelosCSV();
			instancia.refrescarFuentesSumideros();
			instancia.pruebas();
		}
		return instancia;
	}
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	public void registrarVuelo(String aerolinea, String numero, String origen, String destino,
			String salida, String llegada, String equipo, String sillas, boolean[] disponibilidad) throws ParseException, ElementoExisteException, ElementoNoExisteException, CriterioOrdenamientoInvalidoException {
		long salidaP = new SimpleDateFormat("HH:mm").parse(salida).getTime();
		long llegadaP = new SimpleDateFormat("HH:mm").parse(llegada).getTime();
		if (llegadaP < salidaP) llegadaP += 1000*60*60*24;
		for (int j = 0; j < disponibilidad.length; j++) {
			if (disponibilidad[j]) {
				// 01/01/1970 fue un jueves, luego toca sumar 1000*60*60*24*4 para que el día de referencia sea un lunes
				long salidaFin = 1000*60*60*24*4 + salidaP + j*1000*60*60*24;
				long llegadaFin = 1000*60*60*24*4 + llegadaP + j*1000*60*60*24;
				Vuelo vuelo = new Vuelo(aerolinea, origen, destino, numero, new Date(salidaFin), new Date(llegadaFin), equipo, sillas);
				agregarVuelo(vuelo);
			}
		}
	}
	/**
	 * Registra un nuevo aeropuerto en el sistema
	 * @param nombreAeropuerto: El nombre del nuevo aeropuerto
	 * @return Una referencia al aeropuerto recién registrado 
	 * @throws ElementoExisteException En caso que ya exista otro aeropuerto con el mismo nombre registrado en el sistema
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador del aeropuerto no sea válido
	 */
	public Aeropuerto agregarAeropuerto(String nombreAeropuerto) throws ElementoExisteException, CriterioOrdenamientoInvalidoException {
		Aeropuerto nuevoAeropuerto = new Aeropuerto(nombreAeropuerto);
		grafo.agregarVertice(nuevoAeropuerto);
		return nuevoAeropuerto;
	}
	/**
	 * Registra un nuevo vuelo en el sistema.
	 * Si el Aeropuerto de origen no se encuentra registrado en el sistema, lo registra.
	 * Si el Aeropuerto de destino no se encuentra registrado en el sistema, lo registra.
	 * Si la ruta que cubre el vuelo no se encuentra registrada en el sistema, la registra.
	 * Agrega el vuelo a la ruta correspondiente.
	 * @param vuelo: La información del vuelo
	 * @throws ElementoExisteException: No debia presentarse: Al parecer he tratado de registar un Aeropuerto o Ruta que ya existe
	 * @throws ElementoNoExisteException: No debía presentarse: Al parecer he tratado de registar un Vuelo en un Aeropuerto o Ruta que no existe
	 * @throws CriterioOrdenamientoInvalidoException: En caso que el identificador del aeropuerto de origen, aeropuerto de destino o ruta 
	 * sea inválido.
	 */
	private void agregarVuelo(Vuelo vuelo) throws ElementoExisteException, ElementoNoExisteException, CriterioOrdenamientoInvalidoException {
		// Primero verifico si la ruta que cubre este vuelo ya fue generada
		String idRuta = vuelo.getOrigen()+" => "+vuelo.getDestino();
		Ruta ruta = grafo.darArco(idRuta);
		// Si la ruta ya existe, solo debo registrar en ella el nuevo vuelo
		if (ruta != null) {
			ruta.agregarVuelo(vuelo);
		} else {
			// Si estoy aquí es porque la ruta no existe -> debo crearla en el grafo
			Aeropuerto origen = grafo.darVertice(vuelo.getOrigen());
			if (origen == null) {
//				System.out.println("AeroCupi.agregarVuelo(): Aeropuerto "+vuelo.getOrigen()+ " no encontrado, registrando...");
				origen = this.agregarAeropuerto(vuelo.getOrigen());
			}
			Aeropuerto destino = grafo.darVertice(vuelo.getDestino()); 
			if (destino == null) {
//				System.out.println("AeroCupi.agregarVuelo(): Aeropuerto "+vuelo.getDestino()+ " no encontrado, registrando...");
				destino = this.agregarAeropuerto(vuelo.getDestino());
			}
//			System.out.println("AeroCupi.agregarVuelo(): Ruta "+idRuta+ " no encontrada, registrando...");
			ruta = new Ruta(idRuta,origen,destino);
			grafo.agregarArco(origen.getId(), destino.getId(), ruta);
			ruta.agregarVuelo(vuelo);
		}
//		System.out.println("AeroCupi.agregarVuelo(): Se ha registrado el vuelo "+vuelo.getNumero()+ " de "+vuelo.getAerolinea());
	}
	
	/**
	 * Dijkstra enviará el costo acumulado hasta el momento y la ruta que está a punto de visitar para que ésta sea parametrizada.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Realizo el cast a la dupla
		Dupla<Long,Ruta> dupla = (Dupla<Long, Ruta>) arg;
		// Calculo el tiempo transcurrido hasta el momento, en base a la hora de salida y el costo que envía Dijkstra
		long fechaSet = fechaPartida.getTime()+dupla.getIzq();
		while (fechaSet > 1000*60*60*24*7) fechaSet -= 1000*60*60*24*7; 
		dupla.getDer().setCosto(new Date(fechaSet));
	}
	
	public void refrescarFuentesSumideros() {
		this.noFuentes = new ListaEncadenadaOrdenada<String>();
		for(Aeropuerto a : this.grafo.darNoFuentes()) noFuentes.agregar(a.toString());
		this.noSumideros = new ListaEncadenadaOrdenada<String>();
		for(Aeropuerto a : this.grafo.darNoSumideros()) noSumideros.agregar(a.toString());
	}
	
	public void cargarInfoVuelosXML() {
//		System.out.println("AeroCupi.cargarInfoVuelosXML(): Inicializando...");
//		final IListaEncadenada<String> vuelosFallidos = new ListaEncadenada<String>();
//		final IListaEncadenada<Vuelo> vuelosExitosos = new ListaEncadenada<Vuelo>();
//		final IListaEncadenada<Exception> excepciones = new ListaEncadenada<Exception>();
//		try {
//			SAXParserFactory spf = SAXParserFactory.newInstance();
//			SAXParser sp;
//			sp = spf.newSAXParser();
//			sp.parse(new File(ARCHIVO_ORIGEN_XML),new DefaultHandler() {
//				private String tempVal;
//				
//				String aerolinea;
//				String origen;
//				String destino;
//				String numero;
//				String salida;
//				String llegada;
//				String equipo;
//				String sillas;
//				boolean[] disponibilidad = new boolean[7]; 
//				
//				@Override
//				public void startElement(String uri, String localName, String qName, Attributes attributes) {
//					
//				}
//				
//				@Override
//				public void characters(char[] ch, int start, int length) throws SAXException {
//					tempVal = new String(ch,start,length);
//				}
//				
//				public void endElement(String uri, String localName, String qName) throws SAXException {
//					if (qName.equals("nombre")) aerolinea = tempVal;
//					else if (qName.equals("numero")) 		numero = tempVal;
//					else if (qName.equals("origen")) 		origen = tempVal;
//					else if (qName.equals("destino")) 		destino = tempVal;
//					else if (qName.equals("horasalida")) 	salida = tempVal;
//					else if (qName.equals("horallegada")) 	llegada = tempVal;
//					else if (qName.equals("tipoequipo")) 	equipo = tempVal;
//					else if (qName.equals("sillas")) 		sillas = tempVal;
//					else if (qName.equals("lunes")) 		disponibilidad[0] = tempVal.equals("true");
//					else if (qName.equals("martes")) 		disponibilidad[1] = tempVal.equals("true");
//					else if (qName.equals("miercoles")) 	disponibilidad[2] = tempVal.equals("true");
//					else if (qName.equals("jueves")) 		disponibilidad[3] = tempVal.equals("true");
//					else if (qName.equals("viernes")) 		disponibilidad[4] = tempVal.equals("true");
//					else if (qName.equals("sabado")) 		disponibilidad[5] = tempVal.equals("true");
//					else if (qName.equals("domingo")) {
//						disponibilidad[6] = tempVal.equals("true");
//						try {
//							Vuelo vuelo = new Vuelo(aerolinea, origen, destino, numero, salida, llegada, equipo, sillas, disponibilidad);
//							agregarVuelo(vuelo);
//							vuelosExitosos.agregar(vuelo);
//						} catch (ParseException e) {
//							String s = "AeroCupi.cargarInfoVuelos().ParseException: Error al crear el vuelo "+numero+" de "+aerolinea+": "+e.getMessage();
////							System.err.println(s);
//							vuelosFallidos.agregar(s);
//						} catch (ElementoExisteException e) {
////							System.err.println("AeroCupi.cargarInfoVuelos().ElementoExisteException: Al parecer he tratado de registar un Aeropuerto o Ruta que ya existe: "+e.getMessage());
//							excepciones.agregar(e);
//						} catch (ElementoNoExisteException e) {
////							System.err.println("AeroCupi.cargarInfoVuelos().ElementoNoExisteException: Al parecer he tratado de registar un Vuelo en un Aeropuerto o Ruta que no existe: "+e.getMessage());
//							excepciones.agregar(e);
//						} catch (CriterioOrdenamientoInvalidoException e) {
////							System.err.println("AeroCupi.cargarInfoVuelos().CriterioOrdenamientoInvalidoException: "+e.getMessage());
//							excepciones.agregar(e);
//						}
//					}
//				}
//			});
//		} catch (ParserConfigurationException e) {
//			System.err.println("AeroCupi.cargarInfoVuelos().ParserConfigurationException: "+e.getMessage());
//			e.printStackTrace();
//		} catch (SAXException e) {
//			System.err.println("AeroCupi.cargarInfoVuelos().SAXException: "+e.getMessage());
//		} catch (IOException e) {
//			System.err.println("AeroCupi.cargarInfoVuelos().IOException: "+e.getMessage());
//		}
//		
//		System.out.println("MAIN: Se registraron "+grafo.darNumeroVertices()+" aeropuertos");
////		for (Aeropuerto a : grafo.darVertices()) System.out.println("   "+a.toString()); 
//		
//		System.out.println("MAIN: Se registraron "+grafo.darNumeroArcos()+" rutas");
////		for (Ruta r : grafo.darArcos()) System.out.println("   "+r.toString());
//		
//		System.out.println("MAIN: Se registraron "+vuelosExitosos.darNumeroObjetos()+" vuelos");
//		System.err.println("MAIN: Falló el registro de "+vuelosFallidos.darNumeroObjetos()+" vuelos");
//		for (String s : vuelosFallidos) System.err.println("   "+s);
//		if(excepciones.darNumeroObjetos() != 0) {
//			System.err.println("MAIN: Se presentaron "+excepciones.darNumeroObjetos()+" excepciones durante el proceso");
//			for (Exception e : excepciones) System.err.println("   "+e.getMessage());
//		} else System.out.println("MAIN: Se presentaron "+excepciones.darNumeroObjetos()+" excepciones durante el proceso");
	}
	
	public void cargarInfoVuelosCSV() {
		System.out.println("AeroCupi.cargarInfoVuelosCSV(): Inicializando...");
		IListaEncadenada<String> vuelosFallidos = new ListaEncadenada<String>();
		IListaEncadenada<Vuelo> vuelosExitosos = new ListaEncadenada<Vuelo>();
		IListaEncadenada<Exception> excepciones = new ListaEncadenada<Exception>();
		try {
			BufferedReader bf;
			bf = new BufferedReader(new FileReader(new File(ARCHIVO_ORIGEN_CSV)));
			String actual = bf.readLine();
			String aerolinea=""; String numero=""; String origen=""; String destino="";
			String salida=""; String llegada=""; String equipo=""; String sillas=""; 
			boolean disponibilidad[] = new boolean[7];
			while(actual != null) {
				String actuales[] = actual.split(",");
				aerolinea = actuales[0].equals("") ? aerolinea : actuales[0];
				numero = actuales[1].equals("") ? numero : actuales[1];
				origen = actuales[2].equals("") ? origen : actuales[2];
				destino = actuales[3].equals("") ? destino : actuales[3];
				salida = actuales[4].equals("") ? salida : actuales[4];
				llegada = actuales[5].equals("") ? llegada : actuales[5];
				equipo = actuales[6].equals("") ? equipo : actuales[6];
				sillas = actuales[7].equals("") ? sillas : actuales[7];
				int i = 8;
				for (i = i - 0; i < actuales.length; i++) {
					disponibilidad[i-8] = actuales[i].equals("X");
				}
				for (i = i - 8; i < disponibilidad.length; i++) {
					disponibilidad[i] = false;
				}
				try {
					String s = "";
					for(boolean b : disponibilidad) s = b ? s+"T" : s+"F"; 
//					System.out.println("+++++++++ "+"Disponibilidad: "+s+" ++++++++++++++++++++++");
					long salidaP = new SimpleDateFormat("HH:mm").parse(salida).getTime();
					long llegadaP = new SimpleDateFormat("HH:mm").parse(llegada).getTime();
					if (llegadaP < salidaP) llegadaP += 1000*60*60*24;
					for (int j = 0; j < disponibilidad.length; j++) {
						if (disponibilidad[j]) {
							// 01/01/1970 fue un jueves, luego toca sumar 1000*60*60*24*4 para que el día de referencia sea un lunes
							long salidaFin = 1000*60*60*24*4 + salidaP + j*1000*60*60*24;
							long llegadaFin = 1000*60*60*24*4 + llegadaP + j*1000*60*60*24;
							Vuelo vuelo = new Vuelo(aerolinea, origen, destino, numero, new Date(salidaFin), new Date(llegadaFin), equipo, sillas);
							agregarVuelo(vuelo);
							vuelosExitosos.agregar(vuelo);
						}
					}
				} catch (ParseException e) {
					String s = "AeroCupi.cargarInfoVuelos().ParseException: Error al crear el vuelo "+numero+" de "+aerolinea+": "+e.getMessage();
//					System.err.println(s);
					vuelosFallidos.agregar(s);
				} catch (ElementoExisteException e) {
//					System.err.println("AeroCupi.cargarInfoVuelos().ElementoExisteException: Al parecer he tratado de registar un Aeropuerto o Ruta que ya existe: "+e.getMessage());
					excepciones.agregar(e);
				} catch (ElementoNoExisteException e) {
//					System.err.println("AeroCupi.cargarInfoVuelos().ElementoNoExisteException: Al parecer he tratado de registar un Vuelo en un Aeropuerto o Ruta que no existe: "+e.getMessage());
					excepciones.agregar(e);
				} catch (CriterioOrdenamientoInvalidoException e) {
//					System.err.println("AeroCupi.cargarInfoVuelos().CriterioOrdenamientoInvalidoException: "+e.getMessage());
					excepciones.agregar(e);
				}
				actual = bf.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("AeroCupi.cargarInfoVuelosCSV().FileNotFoundException: "+e.getMessage());
		} catch (IOException e) {
			System.err.println("AeroCupi.cargarInfoVuelosCSV().IOException: "+e.getMessage());
		}
		System.out.println("MAIN: Se registraron "+grafo.darNumeroVertices()+" aeropuertos");
//		for (Aeropuerto a : grafo.darVertices()) System.out.println("   "+a.toString()); 
		
		System.out.println("MAIN: Se registraron "+grafo.darNumeroArcos()+" rutas");
//		for (Ruta r : grafo.darArcos()) System.out.println("   "+r.toString());
		
		System.out.println("MAIN: Se registraron "+vuelosExitosos.darNumeroObjetos()+" vuelos");
		if(vuelosFallidos.darNumeroObjetos() != 0) {
			System.err.println("MAIN: Falló el registro de "+vuelosFallidos.darNumeroObjetos()+" vuelos");
			for (String s : vuelosFallidos) System.err.println("   "+s);
		}
		if(excepciones.darNumeroObjetos() != 0) {
			System.err.println("MAIN: Se presentaron "+excepciones.darNumeroObjetos()+" excepciones durante el proceso");
			for (Exception e : excepciones) System.err.println("   "+e.getMessage());
		} else System.out.println("MAIN: Se presentaron "+excepciones.darNumeroObjetos()+" excepciones durante el proceso");
	}
	// --------------------------------------------------------
	// Getters/Setters
	// --------------------------------------------------------
	public IListaEncadenada<String> darNoFuentes() {
		return noFuentes;
	}
	public IListaEncadenada<String> darNoSumideros() {
		return noSumideros;
	}
	public IListaEncadenada<String> darAeropuertos() {
		IListaEncadenada<Aeropuerto> aeropuertos = grafo.darVertices();
		IListaEncadenada<String> respuesta = new ListaEncadenadaOrdenada<String>();
		for (Aeropuerto a : aeropuertos) respuesta.agregar(a.toString());
		return respuesta;
	}
	public String[] darDuraciones() {
		long total = this.duracionTotal/(1000*60);
		long enTierra = total - this.duracionVuelos;
		String[] respuesta = {""+this.duracionVuelos, ""+enTierra, ""+total};
		return respuesta;
	}
	// Vuelo	Aerolinea	Origen		Destino
	public IListaEncadenada<String[]> darRutaMinima(String origen, String destino, String dia, String hora) {
		IListaEncadenada<String[]> respuesta = new ListaEncadenada<String[]>();
		this.duracionVuelos = 0;
		try {
			int diaP = 1;
			if(dia.equalsIgnoreCase("martes")) 			diaP = 2;
			else if(dia.equalsIgnoreCase("miércoles")) 	diaP = 3;
			else if(dia.equalsIgnoreCase("jueves")) 	diaP = 4;
			else if(dia.equalsIgnoreCase("viernes")) 	diaP = 5;
			else if(dia.equalsIgnoreCase("sábado")) 	diaP = 6;
			else if(dia.equalsIgnoreCase("domingo")) 	diaP = 7;
			
			long partida = diaP*1000*60*60*24*4 + new SimpleDateFormat("HH:mm").parse(hora).getTime();
			this.fechaPartida = new Date(partida);
			
			IListaEncadenada<Dupla<Aeropuerto, Ruta>> dijkstra = grafo.rutaMinima(origen, destino);
			if(dijkstra != null){
				this.duracionTotal = grafo.costoMinimo(origen, destino);
				for(Dupla<Aeropuerto, Ruta> d : dijkstra) {
					String[] parada = new String[4];
					Vuelo menorDuracion = d.getDer().darVueloMenorDuracion();
					duracionVuelos += menorDuracion.getDuracion();
					parada[0] = menorDuracion.getNumero()+"";
					parada[1] = menorDuracion.getAerolinea();
					parada[2] = menorDuracion.getOrigen()+" ("+new SimpleDateFormat("E HH:mm").format(menorDuracion.getSalida())+")";
					parada[3] = menorDuracion.getDestino()+" ("+new SimpleDateFormat("E HH:mm").format(menorDuracion.getLlegada())+")";
					respuesta.agregar(parada);
				}
			}
		} catch (ElementoNoExisteException e) {
			e.printStackTrace();
		} catch (CriterioOrdenamientoInvalidoException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	// ----------------------------------------------------
	// Metodo MAIN
	// ----------------------------------------------------
	/**
	 * Permite ejecutar y probar la aplicación en consola
	 */
	public static void main(String[] args) {
		try {
			AeroCupi.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void pruebas() {
		IListaEncadenada<Aeropuerto> fuentes = grafo.darFuentes();
		System.out.println("El grafo contiene "+fuentes.darNumeroObjetos()+" fuentes");
		for(Aeropuerto a : fuentes) System.out.println("   "+a);
		
		IListaEncadenada<Aeropuerto> sumideros = grafo.darSumideros();
		System.out.println("El grafo contiene "+sumideros.darNumeroObjetos()+" sumideros");
		for(Aeropuerto a : sumideros) System.out.println("   "+a);
		
//		IListaEncadenada<String[]> respuesta = darRutaMinima("ATLANTA", "LIMA", "jueves", "2:00");
//		System.out.println("++++++ Se ha encontrado una ruta:");
//		for (String[] s : respuesta)
//			System.out.println(s[0]+" | "+s[1]+" | "+s[2]+" | "+s[3]);
	}
}
