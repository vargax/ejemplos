package uniandes.cupi2.cupiClima.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import com.aetrion.flickr.FlickrException;

import estructurasDatos.IIndiceLexicografico;
import estructurasDatos.IListaEncadenada;
import estructurasDatos.IndiceLexicografico;
import estructurasDatos.ListaEncadenada;
import estructurasDatos.ListaEncadenadaOrdenada;
import excepciones.CriterioOrdenamientoInvalidoException;

public class CupiClima implements ICupiClima {
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------
	// Constantes
	// ----------------------------------------------------
	/**
	 * Referencia a la única instancia de la clase
	 * Implementación del patrón Singleton
	 */
	private static CupiClima instancia = null;
	/**
	 * Directorio de trabajo de la aplicación
	 */
	public final static String directorioTrabajo = "/home/cvargasc/Desarrollo/tmp/";
	/**
	 * Capitales del mundo
	 */
	private final static String[] capitales = {"Kabul","Tirana","Berlin","Luanda","Saint John","Willemstad","Riad","Argel","Buenos Aires","Erevan","Oranjestad","Canberra","Vienna","Baku","Manama","Dacca","Bridgetown","Belmopan","Hamilton","Minsk","Sucre",
			"Sarajevo","Gaborone","Brasília","Sofia","Ouagadougou","Bujumbura","Bruxelles","Yaoundé","Ottawa","Praia","Santiago","Pekin","Nicosia","Bogotá","Moroni","Brazzaville","Pyongyang","Seul","San José","Yamoussoukro","Zagreb",
			"La Habana","Copenhagen","Djibouti","Roseau","Quito","Cairo","San Salvador","Abu Dhabi","Asmara","Bratislava","Lubliano","Madrid","Tallin","Addis Abeba","Suva","Manila","Helsinki","Paris","Libreville","Banjul","Tbilisi",
			"Accra","Saint George","Athens","Nuuk","Basse-Terre","Ciudad de Guatemala","Saint Peter Port","Conakry","Malabo","Cayenne","Bissau","Georgetown","Port-au-Prince","Tegucigalpa","Hong Kong","Budapest","New Delhi","Jacarta","Bagdad",
			"Dublin","Teheran","Saint Helier","Douglas","Reykjavík","George Town","The Settlement","Avarua","Port Stanley","Road Town","Roma","Kingston","Tokio","Amman","Astana","Nairobi","Tarawa","Kuwait","Bishkek","Vientiane","Nassau","Maseru","Monrovia",
			"Tripoli","Luxembourg","Beirut","Macao","Skopje","Antananarivo","Kuala Lumpor","Lilongwe","Male"}; 
	// ----------------------------------------------------
	// Atributos
	// ----------------------------------------------------
	/**
	 * Un arreglo para almacenar las ciudades
	 */
	private IIndiceLexicografico<Ciudad> ciudades;
	// ----------------------------------------------------
	// Constructor
	// ----------------------------------------------------
	/**
	 * Constructor del mundo (patrón singleton)
	 */
	private CupiClima() {
		ciudades = new IndiceLexicografico<Ciudad>();
	}
	/**
	 * Método utilizado para obtener una referencia al mundo
	 */
	public static CupiClima getInstance() {
		if (instancia == null) {
			System.out.println("MUNDO: Tratando de restaurar el mundo del problema");
			try {
				File data = new File(directorioTrabajo+"data");
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(data));
				instancia = (CupiClima) ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (instancia == null) {
			instancia = new CupiClima();
			System.out.println("MUNDO: Inicializando con las capitales del mundo");
			for(String s : capitales) {
				String woeid = Ciudad.buscarCiudad(s)[0][0];
				if (woeid!= null) instancia.darCondiciones(woeid);
			}
		}
		return instancia;
	}
	// ----------------------------------------------------
	// Métodos Interfaz
	// ----------------------------------------------------
	@Override
	public String[] darCondiciones(String woeid) {
		IListaEncadenada<Ciudad> resultado = ciudades.buscarObjeto(woeid);
		System.out.println("MUNDO: Encontradas "+resultado.darNumeroObjetos()+" coincidencias en la base de datos");
		Iterator<Ciudad> i = resultado.iterator();
		Ciudad tempCiudad;
		if (i.hasNext()) {
			tempCiudad = i.next();
		}
		else {
			tempCiudad = new Ciudad(woeid);
			try {
				ciudades.agregarObjeto(woeid, tempCiudad);
				System.out.println("MUNDO: Agregando "+tempCiudad.getNombreCiudad()+" ("+tempCiudad.getWoeid()+") a la base de datos");
			} 
			catch (CriterioOrdenamientoInvalidoException e) {e.printStackTrace();}
		}
		
		String[] respuesta = new String[9];
		int j = 0;
		for (String s : tempCiudad.darCondiciones()) {
			respuesta[j] = s; j++;
		}
		if (tempCiudad.darSiguienteAnuncio() != null) {
			for (String s : tempCiudad.darSiguienteAnuncio()) {
				respuesta[j] = s; j++;
			}
		}
		return respuesta;
	}
	@Override
	public String[][] darFotos(String nombreCiudad) {
		return Ciudad.darFotos(nombreCiudad);
	}
	
	@Override
	public String[][] darFotosWoeid(String woeid) throws Exception {
		return Ciudad.darFotosWoeid(woeid);
	}
	@Override
	public String[][] buscarCiudad(String criterioBusqueda) {
		return Ciudad.buscarCiudad(criterioBusqueda);
	}
	@Override
	public void registrarAnuncio(String woeid, String texto, String link, String rutaImagen) {
		System.out.println("MUNDO: Intentando registrar el anuncio"+texto+"para "+woeid+" ["+link+" | "+rutaImagen);
		IListaEncadenada<Ciudad> resultado = ciudades.buscarObjeto(woeid);
		System.out.println("MUNDO: Encontradas "+resultado.darNumeroObjetos()+" coincidencias en la base de datos");
		Iterator<Ciudad> i = resultado.iterator();
		if (i.hasNext()) {
			Ciudad tempCiudad = i.next();
			System.out.println("MUNDO: Registrando publicidad "+texto+" para "+tempCiudad.getNombreCiudad());
			tempCiudad.registrarAnuncio(texto, link, rutaImagen);
		}
	}
	@Override
	public IListaEncadenada<String[]> darAnuncios() {
		IListaEncadenada<String[]> respuesta = new ListaEncadenada<String[]>();
		for (Ciudad c : ciudades.darObjetos()) {
			IListaEncadenada<String[]> l = c.darAnuncios();
			if(l != null && l.darNumeroObjetos() > 0) {
				for(String[] a : l) {
					respuesta.agregar(a);
				}
			}
		}
		return respuesta;
	}
	@Override
	public IListaEncadenada<String> darCiudades() {
		IListaEncadenada<String> respuesta = new ListaEncadenadaOrdenada<String>();
		for(Ciudad c : ciudades.darObjetos()) {
			respuesta.agregar(c.darVistaAdministracion());
		}
		return respuesta;
	}
	@Override
	public IListaEncadenada<String[]> darCiudadesTemperatura() {
		IListaEncadenada<String[]> respuesta = new ListaEncadenada<String[]>();
		Iterator<Ciudad> i = ciudades.darObjetos().iterator();
		int encontrados = 0;
		while(i.hasNext() && encontrados<5) {
			String[] tmp = i.next().esCalida();
			if (tmp != null) {
				respuesta.agregar(tmp);
				encontrados++;
			}
		}
		return respuesta;
	}
	// ----------------------------------------------------
	// Métodos
	// ----------------------------------------------------
	/**
	 * Método encargado de persistir el mundo durante el cierre del servidor
	 */
	public void persistir() {
		System.out.println("MUNDO: Persistiendo");
		if (instancia != null) {
			try {
				File data = new File(directorioTrabajo+"data");
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(data));
				oos.writeObject(instancia);
				System.out.println("MUNDO: La aplicación se ha persistido correctamente");
				oos.close();
			} catch (Exception e) {
				System.out.println("MUNDO - ERROR: Imposible persistir la aplicación");
				e.printStackTrace();
			}
		}
	}
	// ----------------------------------------------------
	// Metodo MAIN
	// ----------------------------------------------------
	/**
	 * Permite ejecutar y probar la aplicación en consola
	 */
	public static void main(String[] args) {
		try {
			CupiClima mundo = getInstance();
			mundo.persistir();
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			String criterioBusqueda = "";
//			while (criterioBusqueda != null) {
//				System.out.println("Escriba el nombre de la ciudad:");
//				criterioBusqueda = br.readLine();
//				String[][] resultados = Ciudad.buscarCiudad(criterioBusqueda);
//				int i = 0;
//				for (String[] s : resultados) {
//					System.out.println(i+". WOEID: "+s[0]+" Nombre: "+s[1]+" Nombre Completo: "+s[2]);
//					i++;
//				}
//				System.out.println("Escriba el número de la ciudad deseada:");
//				i = Integer.parseInt(br.readLine());
////				Ciudad ciudad = new Ciudad(resultados[i][0], resultados[i][1], resultados[i][2]);
////				System.out.println(ciudad.darClima());
////				resultados = ciudad.darFotos();
////				for (String s : condiciones) {
////					System.out.println("Descripción: "+s[0]+" url: "+s[1]);
////				}
//				String[] condiciones = mundo.darCondiciones(resultados[i][0]);
//				for (String s : condiciones) System.out.println(s);
//				String[][] fotos = mundo.darFotosWoeid(resultados[i][0]);
//				for (String[] s : fotos) {
//					System.out.println("Descripción: "+s[0]+" url: "+s[1]);
//				}
////				Ciudad ciudad = new Ciudad(resultados[i][0],"","");
////				String[] s = ciudad.darClima().darCondiciones();
////				System.out.println(s[0]);
////				System.out.println(ciudad.darClima());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
