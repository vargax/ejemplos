package isis.parsers.sistrans.vuelos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import estructurasDatos.listas.IListaEncadenada;
import estructurasDatos.listas.ListaEncadenada;

public class TDLA {
	// ---------------------------------------------
	// Constantes
	// ---------------------------------------------
	
	// ---------------------------------------------
	// Atributos
	// ---------------------------------------------
	private IListaEncadenada<Vuelo> vuelosExitosos;;
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	public TDLA() {
		cargarInfoVuelosCVS();
//		generarSQLvuelos();
//		generarSQLusuarios();
		generarSQLreservas();
	}
	
	// ---------------------------------------------
	// Métodos
	// ---------------------------------------------
	private void cargarInfoVuelosCVS() {
		String archivoEntrada = "./data/tdla/input.csv";
		System.out.println("TDLA.cargarInfoVuelosCSV(): Inicializando...");
		IListaEncadenada<String> vuelosFallidos = new ListaEncadenada<String>();
		this.vuelosExitosos = new ListaEncadenada<Vuelo>();
		IListaEncadenada<Exception> excepciones = new ListaEncadenada<Exception>();
		try {
			BufferedReader bf;
			bf = new BufferedReader(new FileReader(new File(archivoEntrada)));
			String actual = bf.readLine();
			String aerolinea=""; String numero=""; String origen=""; String destino="";
			String salida=""; String llegada=""; String equipo=""; String sillas=""; 
			boolean disponibilidad[] = new boolean[7];
			// Constantes para calcular las fechas
			// 01/01/1970 fue un jueves, luego toca sumar 1000*60*60*24*4 para que el día de referencia sea un lunes
			// 1000*60*60*24*7*52*32 son las semanas que pasan en 32 años -> llevarlo al 2012
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date ref = sdf.parse("04/11/2012 19:00");
			long referencia = ref.getTime();
			long dia = 1000*60*60*24;
			
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
							long salidaFin = referencia + salidaP + j*dia;
							long llegadaFin = referencia + llegadaP + j*dia;
							Vuelo vuelo = new Vuelo(aerolinea, origen, destino, numero, new Date(salidaFin), new Date(llegadaFin), equipo, sillas);
							vuelosExitosos.agregar(vuelo);
						}
					}
				} catch (ParseException e) {
					String s = "TDLA.cargarInfoVuelos().ParseException: Error al crear el vuelo "+numero+" de "+aerolinea+": "+e.getMessage();
//					System.err.println(s);
					vuelosFallidos.agregar(s);
				} 
				actual = bf.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("TDLA.cargarInfoVuelosCSV().FileNotFoundException: "+e.getMessage());
		} catch (IOException e) {
			System.err.println("TDLA.cargarInfoVuelosCSV().IOException: "+e.getMessage());
		} catch (ParseException e) {
			System.err.println("TDLA.cargarInfoVuelosCSV().ParseException: "+e.getMessage());
		}
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
	
	private void generarSQLvuelos() {
		String sqlVuelos = "./data/tdla/vuelos.sql";
		try {
			int numeroSemanas = 16;
			PrintWriter pw = new PrintWriter(new File(sqlVuelos));
			for(Vuelo v : vuelosExitosos) {
				pw.println(v.sql(numeroSemanas));
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void generarSQLusuarios() {
		String archivoNombres = "./data/tdla/nombres.csv";
		String archivoApellidos = "./data/tdla/apellidos.csv";
		String arhivoSalida = "./data/tdla/usuarios.sql";
		
		ArrayList<String> nombres = new ArrayList<String>();
		ArrayList<String> apellidos = new ArrayList<String>();
		try {
			// Cargando los nombres a memoria principal
			BufferedReader bf = new BufferedReader(new FileReader(new File(archivoNombres)));
			String actual = bf.readLine();
			for(int i = 0; i < 5; i++) {
				nombres.add(actual);
				actual = bf.readLine();
			}
			bf.close();
			
			// Cargando los apellidos a memoria principal
			bf = new BufferedReader(new FileReader(new File(archivoApellidos)));
			actual = bf.readLine();
			while(actual != null) {
				apellidos.add(actual);
				actual = bf.readLine();
			}
			bf.close();
			
			PrintWriter pw = new PrintWriter(new File(arhivoSalida));
			for(String nombre : nombres) {
				for(String apellido : apellidos) {
					String userName = (nombre+apellido).toLowerCase();
					userName.replace(" ", "");
					userName = userName.substring(0, userName.length() > 25 ? 25 : userName.length());
					String insert = "INSERT INTO usuario(id, username, nombre, identificacion) " +
							"VALUES(id_usuario.nextval, '"+userName+"','"+nombre+" "+apellido+"',"
							+Math.round(1000000000*Math.random())+");";
					System.out.println(insert);
					pw.println(insert);
				}
			}
			pw.println("COMMIT;");
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("TDLA.generarSQLusuarios().FileNotFoundException: "+e.getMessage());
		} catch (IOException e) {
			System.err.println("TDLA.generarSQLusuarios().IOException: "+e.getMessage());
		}
	}
	
	private void generarSQLreservas() {
		int numeroVuelos = 97520;
		int numeroUsuarios  = 129144;
		int maxSillasPorReserva = 2;
		String arhivoSalida = "./data/tdla/reservas.sql";
		
		try {
			PrintWriter pw = new PrintWriter(new File(arhivoSalida));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			int i = 1;
			for (Vuelo v : vuelosExitosos) {
				i++;
				if(v.getSillas() > 20){
					int reservasVueloActual = (int) (Math.random()*(v.getSillas()/2*maxSillasPorReserva));
					System.out.println(reservasVueloActual);
					int sillasReservadas = 0;
					for (int j = 0; j < reservasVueloActual; j++) {
						int idUsuario = (int) Math.round(numeroUsuarios*Math.random());
						int numeroSillasReservar = (int) Math.round(maxSillasPorReserva*Math.random());
						String registrarReserva = "INSERT INTO reserva(id, id_usuario, id_vuelo, fecha, posiciones, estado) " +
								"VALUES(id_reserva.nextval, "+idUsuario+","+i+",to_date('"+sdf.format(new Date())+"','DD/MM/YYYY HH24:MI')," +
								numeroSillasReservar+","+(Math.random() >= 0.5 ? 1 : 0)+");";
						System.out.println(registrarReserva);
						sillasReservadas += numeroSillasReservar;
						pw.println(registrarReserva);
					}
					if(sillasReservadas != 0) {
						String updateVuelo = "UPDATE vuelo SET sillasdisponibles = sillasdisponibles - "+sillasReservadas+" WHERE id ="+i+";";
						pw.println(updateVuelo);
						System.out.println(updateVuelo);
						pw.println("COMMIT;");
					}
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("TDLA.generarSQLreservas().FileNotFoundException: "+e.getMessage());
		}
	}
	// ---------------------------------------------
	// Método MAIN
	// ---------------------------------------------
	public static void main(String[] s) {
		TDLA tdla = new TDLA();
	}
}
