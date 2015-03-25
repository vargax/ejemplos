/**
 * 
 */
package co.edu.uniandes.bi.twitter;

import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * Ofrece los servicios de Twitter Stream
 * @author Sebastian
 *
 */
public class TwitterStreamAdapter {

	//--------------------------------------------------------------------------------------------------
	// Miembros de la clase
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Instancia compartida de la clase
	 */
	private static TwitterStreamAdapter instance;
	
	//--------------------------------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Servicio Twitter Stream
	 */
	private TwitterStream twitterStream;
	
	/**
	 * Log de la instancia
	 */
	private Logger log;
	
	
	//--------------------------------------------------------------------------------------------------
	// Constructores
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	private TwitterStreamAdapter() {
		log = Logger.getLogger(this.getClass().getCanonicalName());
		twitterStream = new TwitterStreamFactory().getInstance();
	}
	
	//--------------------------------------------------------------------------------------------------
	// Métodos privados
	//--------------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------------------------
	// Métodos públicos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Agrega un listener para los tweets que recibe la instancia
	 * @param statusListener
	 */
	public void addStatusListener(StatusListener statusListener) {
		// StatusListener
		twitterStream.addListener(statusListener);
	}
	
	/**
	 * Detiene la captura de estados de Twitter
	 */
	public void stopStream() {
		log.info("Deteniendo el servicio...");
		//Shuts down internal dispatcher thread shared by all TwitterStream instances.
		twitterStream.shutdown();
	}
	
	/**
	 * Empieza a recibir mensajes que contienen las palabras dadas
	 * y los envía al StatusListener registrado con el método
	 * TwitterStreamAdapter#statusListener()
	   @param keywords
	   @see <a href="http://dev.twitter.com/pages/streaming_api_methods">Stream API Methods</a>
	 * 
	 */
	public void monitorKeywords(Set<String> keywords) {
		String[] track = new String[keywords.size()];
		track = keywords.toArray(track);
		
		FilterQuery filter = new FilterQuery();
		
		log.info("Consultando tweets que contengan las palabras"+ Arrays.toString(track));
		
		// De la documentación de Twitter:
		// Specifies keywords to track....
		// Comma separated keywords and phrases are logical ORs, 
		// phrases are logical ANDs. Words within phrases are delimited 
		// by spaces. A tweet matches if any phrase matches. A phrase 
		// matches if all of the words are present in the tweet. 
		// (e.g. ‘the twitter’ is the AND twitter, and ‘the,twitter’ is
		// the OR twitter.). Terms are exact-matched, and also 
		// exact-matched ignoring punctuation. Each comma-seperated term 
		// may be up to 60 characters long.
		filter.track(track);
		
		// De la documentación de Twitter4J:
		// The default access level allows up to 200 track keywords, 400 follow userids 
		// and 10 1-degree location boxes. Increased access levels allow 80,000 follow 
		// userids ("shadow" role), 400,000 follow userids ("birddog" role), 10,000 track
		// keywords ("restricted track" role), 200,000 track keywords ("partner track" role),
		// and 200 10-degree location boxes ("locRestricted" role). Increased track access 
		// levels also pass a higher proportion of statuses before limiting the stream. 
		twitterStream.filter(filter);
	}
	
	
	//--------------------------------------------------------------------------------------------------
	// Métodos estáticos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Obtiene una instancia compartida de la clase
	   @return instance
	 */
	public TwitterStreamAdapter getInstance() {
		if (instance == null) {
			instance = new TwitterStreamAdapter();
		}
		return instance;
	}

	
}
