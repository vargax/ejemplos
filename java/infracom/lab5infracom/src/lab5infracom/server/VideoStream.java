package lab5infracom.server;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

/**
 * Clase encargada de emitir en multicast un vídeo determinado.
 */
public class VideoStream extends Thread {
	// ----------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------
	MediaPlayerFactory mediaPlayerFactory;
	HeadlessMediaPlayer headlessMediaPlayer;
	
	String pathVideo;
	String direccionMulticast;
	String puerto;
	
	String descripcion;
	// ----------------------------------------------------------
	// CONSTRUCTOR
	// ----------------------------------------------------------
	public VideoStream(String pathVideo, String direccionMulticast, String puerto) throws InterruptedException {
		
		mediaPlayerFactory = new MediaPlayerFactory();
		headlessMediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
//		headlessMediaPlayer.setRepeat(true);
		
		this.pathVideo = pathVideo;
		this.direccionMulticast = direccionMulticast;
		this.puerto = puerto;
		
		descripcion = pathVideo+" en "+direccionMulticast+":"+puerto;
	}
	
	// ----------------------------------------------------------
	// MÉTODOS
	// ----------------------------------------------------------
	public void run() {
		System.out.println("VideoStream.run() :: Reproduciendo "+descripcion);
		headlessMediaPlayer.playMedia(pathVideo, 
				  ":sout=#rtp{dst="+direccionMulticast+",port="+puerto+",mux=ts}",
				  ":no-sout-rtp-sap", 
				  ":no-sout-standard-sap", 
				  ":sout-all", 
				  ":sout-keep"
				);
		
	}
	
	public void detener() {
		System.out.println("VideoStream.detener() :: Deteniendo reproducción de "+descripcion);
		headlessMediaPlayer.stop();
		headlessMediaPlayer.release();
		headlessMediaPlayer = null;
	}
}
