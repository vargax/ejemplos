package co.edu.uniandes.bi.twitter;

import java.util.logging.Logger;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * Recibe los tweets/estados del flujo de Twitter
 * @author Sebastian
 *
 */
public class TwitterStreamListener implements StatusListener {

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
	public TwitterStreamListener() {
		log = Logger.getLogger(this.getClass().getCanonicalName());
	}
	
	//--------------------------------------------------------------------------------------------------
	// StatusListener
	//--------------------------------------------------------------------------------------------------

	/** 
	 * @see twitter4j.StreamListener#onException(java.lang.Exception)
	 */
	@Override
	public void onException(Exception exception) {
		log.severe("StatusListener#onException(): "+exception.getMessage());
		
	}

	/** 
	 * @see twitter4j.StatusListener#onDeletionNotice(twitter4j.StatusDeletionNotice)
	 */
	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		log.info("StatusListener#onDeletionNotice(): "+statusDeletionNotice);
	}

	/**
	 * @see twitter4j.StatusListener#onScrubGeo(int, long)
	 */
	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		log.info("StatusListener#onScrubGeo(): userId="+userId+" upToStatusId="+upToStatusId);
	}

	/**
	 * @see twitter4j.StatusListener#onStatus(twitter4j.Status)
	 */
	@Override
	public void onStatus(Status status) {

	}

	/**
	 * @see twitter4j.StatusListener#onTrackLimitationNotice(int)
	 */
	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		log.warning("StatusListener#onTrackLimitationNotice(): numberOfLimitedStatuses="+numberOfLimitedStatuses);
	}

    @Override
    public void onStallWarning( StallWarning arg0 )
    {
        // TODO Auto-generated method stub
        
    }

}
