package uniandes.infracom.SisTrans;

import java.io.IOException; 
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author Martín Uribe Gutierrez
 * @author Lina Giseth Casas Salas
 * Infraestructura Computacional 2012-20
 * Universidad de los Andes
 * Algoritmos tomados de: 
 * http://www.java2s.com/Code/Java/Security/CatalogSecurity.htm
 * http://www.bouncycastle.org/
 * 
 * Clase principal, encargada de administrar las conexiones y de iniciar el listado de camiones y su correspondiente
 * información
 */
public class SisTrans {
	/**
	 * Define el número de camiones en el sistema
	 */
	public static final int TRANS_SIZE = 20;
	
	/**
	 * Puerto por el cual se van a esperar peticiones
	 */
	public static final int SOCKET = 80;
	
	/**
	 * Modela los camiones del sistema
	 */
	private Camion[] camiones;
	
	/**
	 * Modela el canal de recepción del servidor
	 */
	private ServerSocket s;
	
	/**
	 * Constructor que crea la información del sistema y delega peticiones
	 * @throws UnknownHostException 
	 */
	public SisTrans() throws UnknownHostException {
		
		Random r = new Random(System.currentTimeMillis());	
		camiones = new Camion[TRANS_SIZE];	
		for (int i = 0; i < camiones.length; i++) {
			camiones[i] = new Camion(r);
		}		
		MueveCamiones mc = new MueveCamiones(camiones);
		mc.start();
		
		for (int i = 0; i < camiones.length; i++) {
			System.out.println(camiones[i].getId());
		}
		
		 try {
			s = new ServerSocket(SOCKET);
			System.out.println("Servidor Listo");
			int i = 0;
			while( true ) {
				//Crea un nuevo thread por cada conexion.
				Socket ss = s.accept();
				i++;
				ProtocoloSeguro p = new ProtocoloSeguro(i, ss, camiones );
				p.start();
				System.out.println("Conexión recibida");
			}
		} 
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método de inicio del servidor.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SisTrans s = new SisTrans();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
	}
}
