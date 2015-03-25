package uniandes.infracom.SisTrans;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
	// --------------------------------------------------------------
	// CONSTANTES
	// --------------------------------------------------------------
	/**
	 * Define el número de camiones en el sistema
	 */
	public static final int TRANS_SIZE = 20;
	
	/**
	 * Puerto por el cual se van a esperar peticiones
	 */
	public static final int PUERTO_SOCKETSERVER = 4456;
	
	// --------------------------------------------------------------
	// ATRIBUTOS ESTÁTICOS
	// --------------------------------------------------------------
	/**
	 * El número de threads en el pool
	 */
	private static int numThreads;
	/**
	 * Define si se utilizará el protocolo seguro
	 */
	private static boolean usarProtocoloSeguro;

	// --------------------------------------------------------------
	// ATRIBUTOS
	// --------------------------------------------------------------
	/**
	 * Modela los camiones del sistema
	 */
	private Camion[] camiones;
	
	/**
	 * Modela el canal de recepción del servidor
	 */
	private ServerSocket s;
	
	/**
	 * El ejecutor encargado de administrar los Threads
	 */
	private ExecutorService es;
	
	// --------------------------------------------------------------
	// CONSTRUCTOR
	// --------------------------------------------------------------
	public Servidor() {
		// Creo algunos camiones
		Random r = new Random(System.currentTimeMillis());	
		camiones = new Camion[TRANS_SIZE];	
		for (int i = 0; i < camiones.length; i++) {
			camiones[i] = new Camion(r);
		}
		// Inicio el thread encargado de mover los camiones
		MueveCamiones mc = new MueveCamiones(camiones);
		mc.start();
		// Verifico los camiones creados
		for (int i = 0; i < camiones.length; i++) {
			//System.out.println(camiones[i].getId());
		}
		
		// Inicializo el pool de Threads
		es = Executors.newFixedThreadPool(numThreads);
		
		// Inicio el servidor
		try {
			s = new ServerSocket(PUERTO_SOCKETSERVER);
			System.out.println(",Servidor listo escuchando en el puerto "+this.PUERTO_SOCKETSERVER);
			int i = 0;
			while( true ) {
				Socket ss = s.accept();
				i++;
				ProtocoloSeguro p = new ProtocoloSeguro(i, ss, camiones );
//				ProtocoloInseguro p = new ProtocoloInseguro(i, ss, camiones);
				es.execute(p);
			}
		} 
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// --------------------------------------------------------------
	// MÉTODO MAIN
	// --------------------------------------------------------------
	public static void main(String[] args) {
		int threads = 4;
		boolean protocoloSeguro = true;
		try {
			threads = Integer.parseInt(args[0]);
			System.out.println(",Se utilizarán "+threads+" threads");
		} catch (Exception e) {
			System.err.println("MAIN: No se ha definido un número de threads para el servidor," +
					" se utilizarán "+threads+": "+e.getMessage());
		}
		try {
			protocoloSeguro = Boolean.parseBoolean(args[1]);
			System.out.println(",Se utilizará "+(protocoloSeguro ? "el protocolo seguro" : "el protocolo inseguro"));
		} catch (Exception e) {
			System.err.println("MAIN: No se ha definido el tipo de protocolo a utilizar," +
					" se utilizará "+(protocoloSeguro ? "el protocolo seguro" : "el protocolo inseguro")+": "+e.getMessage());
		}
		Servidor.numThreads = threads;
		Servidor.usarProtocoloSeguro = protocoloSeguro;
		Servidor s = new Servidor();
	}
}
