package lab5infracom.server;

import java.util.ArrayList;

public class UdpServer {
	// ----------------------------------------------------------
	// CONSTANTES
	// ----------------------------------------------------------
	private final static String DIRECCION_MULTICAST = "230.0.0.1";
	private final static String PUERTO_INICIAL = "5004";
	
	// ----------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------
	private static UdpServer udpServer;
	
	private ArrayList<VideoStream> canales;
	private int puertoActual;
	
	// ----------------------------------------------------------
	// CONSTRUCTOR
	// ----------------------------------------------------------
	private UdpServer() {
		canales = new ArrayList<VideoStream>();
		puertoActual = Integer.parseInt(PUERTO_INICIAL);
	}
	
	public final static UdpServer getInstance() {
		if (udpServer == null) {
			System.out.println("UdpServer.getInstance() :: Generando instancia de UdpServer...");
			udpServer = new UdpServer();
		}
		return udpServer;
	}
	// ----------------------------------------------------------
	// METODOS
	// ----------------------------------------------------------
	public void publicarVideo(String pathVideo) {
		try {
			synchronized (this) {
				VideoStream videoStream = new VideoStream(pathVideo, DIRECCION_MULTICAST, puertoActual+"");
				canales.add(videoStream);
				videoStream.start();
				System.out.println("UdpServer.publicarVideo() :: Video "+pathVideo+" disponible en canal "+puertoActual);
				puertoActual++;	
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void detener() {
		for (VideoStream videoStream : canales) {
			videoStream.detener();
			try {
				videoStream.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		udpServer = null;
	}
}
