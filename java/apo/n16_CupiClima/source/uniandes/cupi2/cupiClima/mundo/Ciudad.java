package uniandes.cupi2.cupiClima.mundo;

import java.io.Serializable;
import java.util.Iterator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;
import estructurasDatos.IListaEncadenada;
import estructurasDatos.ListaEncadenada;

public class Ciudad implements Comparable<Ciudad>, Serializable {
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------
	// Constantes
	// ----------------------------------------------------
	/**
	 * El id de la aplicación otorgado por Yahoo!
	 */
	private final static String appid = "fXbeQY3V34EhlSHVEAzM18M2K8Nm1I8zQOL5w.UnY5nZjpoWaAegGL1de7hDi0km_fkv56ePBs150fNNG3tRylYkZfPxLCo-";
	/**
	 * Llave para Flickr
	 */
	private final static String flickrKey = "8de19f2411189d032f360b7e37cbdfc9";
	/**
	 * Secreto para Flickr
	 */
	private final static String flickrSecret = "00b19940dd0351ef";
	/**
	 * El número de resultados a solicitar al sevicio de geolocalización de Yahoo!
	 */
	private final static int numResultados = 10;
	/**
	 * El número de fotografias a solicitar al servicio de Flickr
	 */
	private final static int numFotos = 20;
	/**
	 * La temperatura a partir de la cual se considera una ciudad cálida
	 */
	private final static int CALIDA = 20;
	// ----------------------------------------------------
	// Atributos
	// ----------------------------------------------------
	/**
	 * El nombre de la ciudad
	 */
	private String nombreCiudad;
	/**
	 * El nombre completo de la ciudad
	 */
	private String nombreCompleto;
	/**
	 * El WOEID de la ciudad
	 */
	private String woeid;
	/**
	 * Una lista con los anuncios registrados para esta ciudad
	 */
	private IListaEncadenada<Anuncio> anuncios;
	/**
	 * Un iterador para desplegar los anuncios en orden
	 */
	private Iterator<Anuncio> iterador;
	/**
	 * El clima actual de la ciudad
	 */
	private Clima clima;
	// ----------------------------------------------------
	// Constructor
	// ----------------------------------------------------
	public Ciudad(String woeid, String nombreCiudad, String nombreCompleto) {
		this.woeid = woeid;
		this.nombreCiudad = nombreCiudad;
		this.nombreCompleto = nombreCompleto;
		clima = new Clima(this.woeid);
		anuncios = new ListaEncadenada<Anuncio>();
		iterador = anuncios.iterator();
	}
	public Ciudad(String woeid) {
		String url = "http://where.yahooapis.com/v1/place/"+woeid+"?appid="+appid;
		this.woeid = woeid;
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			sp.parse(url,new DefaultHandler() {
				private String tempVal = "";
				
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (qName.equals("name")){
						nombreCiudad = tempVal;
					} else if (qName.equals("country")) {
						nombreCompleto = tempVal;
						nombreCiudad = nombreCiudad+","+tempVal;
					} else if (qName.equals("admin1")) {
						nombreCompleto = tempVal + ", " + nombreCompleto;
					} else if (qName.equals("admin2")) {
						nombreCompleto = tempVal + ", " + nombreCompleto;
					} else if (qName.equals("locality1")) {
						nombreCompleto = tempVal + ", " + nombreCompleto;
					}
				}
				
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					tempVal = new String(ch,start,length);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		clima = new Clima(this.woeid);
		anuncios = new ListaEncadenada<Anuncio>();
		iterador = anuncios.iterator();
	}
	// ----------------------------------------------------
	// Métodos
	// ----------------------------------------------------
	/**
	 * Entrega el listado con la descripción y URL de la ciudad actual.
	 * @param El WoeId de la región para la cual se buscan las fotografias
	 * @return Una matriz de String que contiene en la columna 0 la descripción y en la columna 1 la url de la imagen
	 */
	public static String[][] darFotosWoeid(String woeid) throws Exception {
		String[][] respuesta = new String[numFotos][2];
		
			Flickr flickr = new Flickr(flickrKey, new REST("www.flickr.com"));
			flickr.setSharedSecret(flickrSecret);
			Flickr.debugStream = false;
			SearchParameters sp = new SearchParameters();
			sp.setWoeId(woeid);
			sp.setAccuracy(6);
			PhotosInterface pi = flickr.getPhotosInterface();
			PhotoList pl = pi.search(sp,numFotos,1);
			
			for(int i = 0; i < numFotos; i++) {
				 Photo p = (Photo)pl.get(i);
				 respuesta[i][0] = p.getDescription();
				 respuesta[i][1] = "http://farm"+p.getFarm()+".staticflickr.com/"+p.getServer()+"/"+p.getId()+"_"+p.getSecret()+"_m.jpg";
			}
			
		return respuesta;
	}
	/**
	 * Entrega el listado con la descripción y URL de la ciudad actual.
	 * @param El criterio de busqueda a utilizar
	 * @return Una matriz de String que contiene en la columna 0 la descripción y en la columna 1 la url de la imagen
	 */
	public static String[][] darFotos(String nombreCiudad) {
		String url = "http://api.flickr.com/services/feeds/photos_public.gne?tags="+nombreCiudad;
		final String[][] respuesta = new String[numFotos][2];
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			sp.parse(url,new DefaultHandler() {
				/**
				 * Entero para representar la foto actual
				 */
				private int fotoActual = -1;
				private String tempVal = "";
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (fotoActual < numFotos && qName.equals("link") && attributes.getValue(0).equals("enclosure")) {
						respuesta[fotoActual][1] = attributes.getValue(2);
					}
				}
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (qName.equals("title") && !tempVal.startsWith("Recent")) {
						fotoActual++;
						if (fotoActual < numFotos) respuesta[fotoActual][0] = tempVal;
					}
				}
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					tempVal = new String(ch,start,length);
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	/**
	 * Dar condiciones
	 */
	public String[] darCondiciones() {
		// Calculando tiempo transcurrido
		long time = System.currentTimeMillis() - clima.getFechaRecuperacion().getTime();
		if (Math.floor(time/(1000*60*60)) > 1) clima = new Clima(this.woeid);
				
		String[] respuesta = clima.darCondiciones();
		respuesta[0] = nombreCiudad;
		respuesta[1] = nombreCompleto;
		return respuesta;
	}
	/**
	 * Dar clima
	 */
	public Clima darClima() {
		return clima;
	}
	/**
	 * Registrar un anuncio
	 */
	public void registrarAnuncio(String texto, String link, String rutaImagen) {
		Anuncio tempAnuncio = new Anuncio(this, texto, rutaImagen, link);
		anuncios.agregar(tempAnuncio);
	}
	/**
	 * Dar el siguiente anuncio a desplegar
	 */
	public String[] darSiguienteAnuncio() {
		if(iterador.hasNext()) {
			return iterador.next().darAnuncioDesplegar();
		}
		if(anuncios.darNumeroObjetos() > 0) {
			iterador = anuncios.iterator();
			return iterador.next().darAnuncioDesplegar();
		}
		return null;
	}
	/**
	 * Dar todos los anuncios registrados
	 */
	public IListaEncadenada<String[]> darAnuncios() {
		if(anuncios.darNumeroObjetos()>0) {
			IListaEncadenada<String[]> respuesta = new ListaEncadenada<String[]>();
			for(Anuncio a : anuncios) respuesta.agregar(a.darAnuncioAdministrar());
			return respuesta;
		}
		return null;
	}
	/**
	 * Entrega el nombre y woeid de la ciudad para la vista de administración 
	 */
	public String darVistaAdministracion() {
		return nombreCiudad+" "+woeid;
	}
	
	public String[] esCalida() {
		int temperatura = clima.getTemperatura(); 
		if ( temperatura >= CALIDA) {
			String[] respuesta = {nombreCiudad,temperatura+""};
			return respuesta;
		}
		return null;
	}
	// ----------------------------------------------------
	// Métodos Estáticos
	// ----------------------------------------------------
	/**
	 * Busca una ciudad en el servicio de geolocalización de Yahoo!
	 * @param criterioBusqueda: El criterio para realizar la busqueda 
	 * @return Una matriz de String que contiene en la columna 0 el WOEID y en la columna 1 la descripción de las ciudades encontradas 
	 */
	public static String[][] buscarCiudad(final String criterioBusqueda) {
		String url = "http://where.yahooapis.com/v1/places.q("+criterioBusqueda+");count="+numResultados+"?appid="+appid;
		//System.out.println(url);
		final String[][] respuesta = new String[numResultados][3];
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			sp.parse(url,new DefaultHandler() {
				/**
				 * Entero para representar la ciudad actual
				 */
				private int ciudadActual = -1;
				private String tempVal = "";
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (ciudadActual < numResultados) {
						if (qName.equals("woeid")) {
							ciudadActual++;
							respuesta[ciudadActual][0] = tempVal;
						} else if (qName.equals("name")){
							respuesta[ciudadActual][1] = tempVal;
						} else if (qName.equals("country")) {
							respuesta[ciudadActual][2] = tempVal;
						} else if (qName.equals("admin1")) {
							respuesta[ciudadActual][2] = tempVal + ", " + respuesta[ciudadActual][2];
						} else if (qName.equals("admin2")) {
							respuesta[ciudadActual][2] = tempVal + ", " + respuesta[ciudadActual][2];
						} else if (qName.equals("locality1")) {
							respuesta[ciudadActual][2] = tempVal + ", " + respuesta[ciudadActual][2];
						}
					}
				}
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					tempVal = new String(ch,start,length);
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	// ----------------------------------------------------
	// Métodos Interfaz
	// ----------------------------------------------------
	@Override
	public int compareTo(Ciudad o) {
		return woeid.compareTo(o.getWoeid());
	}
	// ----------------------------------------------------
	// Setters/Getters
	// ----------------------------------------------------
	public String getWoeid() {
		return woeid;
	}
	public String getNombreCiudad() {
		return nombreCiudad;
	}
}