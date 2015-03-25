/**
 * 
 */
package co.edu.uniandes.bi.twitter;

import java.util.logging.Logger;

import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterMethod;
import twitter4j.User;

/**
 * Sobreescribe algunos de los métodos del listener del servicio asincrónico de Twitter
 * @author Sebastian
 *
 */
public class TwitterAsyncListener extends TwitterAdapter {
	
	
	//--------------------------------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Log de la instancia
	 */
	private Logger log;
	
	//--------------------------------------------------------------------------------------------------
	// Constructor
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public TwitterAsyncListener() {
		log = Logger.getLogger(this.getClass().getCanonicalName());
	}
	
	//--------------------------------------------------------------------------------------------------
	// TwitterListener
	//--------------------------------------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see twitter4j.TwitterListener#gotRateLimitStatus(twitter4j.RateLimitStatus)
	 */


	/* (non-Javadoc)
	 * @see twitter4j.TwitterListener#onException(twitter4j.TwitterException, twitter4j.TwitterMethod)
	 */
	@Override
	public void onException(TwitterException exception, TwitterMethod method) {
		log.severe(exception.getMessage());
	}

	/* (non-Javadoc)
	 * @see twitter4j.TwitterListener#searched(twitter4j.QueryResult)
	 */
	@Override
	public void searched(QueryResult queryResult) {
		log.info("Resultado de búsqueda recibido: "+queryResult.getTweets().size());
	}

	/* (non-Javadoc)
	 * @see twitter4j.TwitterListener#verifiedCredentials(twitter4j.User)
	 */
	@Override
	public void verifiedCredentials(User user) {
		log.info("Se verificaron las credenciales para el usuario: "+user);
	}

}
