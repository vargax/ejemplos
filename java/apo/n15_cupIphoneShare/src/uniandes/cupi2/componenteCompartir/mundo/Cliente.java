package uniandes.cupi2.componenteCompartir.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import estructurasDatos.IListaEncadenada;

/**
 * Clase que representa un hilo de comunicación con un cliente de cupiShare
 */
public class Cliente extends Thread {
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	/**
	 * El mundo de la aplicación
	 */
	private ComponenteCompartir mundo;
	/**
	 * El nombre del cliente
	 */
	private String nombre;
	/**
	 * El canal de comunicación
	 */
	private Socket socket;
	/**
	 * El canal de escritura
	 */
	private PrintWriter out;
	/**
	 * El canal de lectura
	 */
	private BufferedReader in;
	/**
	 * Indica si la conexión del usuario se encuentra activa
	 */
	private boolean conectado;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	/**
	 * Crea una nueva instancia para un cliente con su correspondiente hilo de ejecución
	 * @param mundoP: Una referencia a la instancia de ComponenteCompartir
	 * @param socketP: El socket de comunicación con el cliente
	 * @throws IOException: En caso de presentarse alguna excepción de lectura/escritura
	 */
	public Cliente(ComponenteCompartir mundoP, Socket socketP) throws IOException {
		mundo = mundoP;
		socket = socketP;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		conectado = true;
	}
	// -----------------------------------------------------------------
    // Hilo de ejecución
    // -----------------------------------------------------------------
	/**
	 * Inicia el proceso del usuario y realiza todas las operaciones necesarias mientras este dure
	 */
	public void run() {
		while(conectado && !socket.isClosed()) {
			try {
				String protocolo = in.readLine();
				System.out.println("<"+protocolo+"> --> IN");
// Login ----------------------------------------------------------------------------
				if (protocolo.startsWith("LOGIN")) {
					System.out.println("PROTOCOLO: Solicitud de ingreso...");
					String parametro = (protocolo.split(";;;"))[1];
	    			nombre = parametro;
	    			String respuesta = "LOGINOK";
	    			System.out.println("OUT --> <"+respuesta+">");
	    			out = new PrintWriter(socket.getOutputStream(),true);
	    			out.println(respuesta);
	    			mundo.registrarCliente(this);
	    			System.out.println("PROTOCOLO: Login exitoso de "+nombre);
				} 
// Logout ----------------------------------------------------------------------------
				else if(protocolo.startsWith("LOGOUT")) {
					System.out.println("PROTOCOLO: Solicitud de salida...");
					conectado = false;
					desconectar();
				}
// Listar Archivos -----------------------------------------------------------------
				else if(protocolo.startsWith("ARCHIVOS")) {
					System.out.println("PROTOCOLO: Listar archivos...");
					String respuesta = darListaArchivos();
					System.out.println("OUT --> <"+respuesta+">");
	    			out.println(respuesta);
				}
// Enviar archivo --------------------------------------------------------------------
				else if(protocolo.startsWith("DESCARGAR")) {
					String parametro = (protocolo.split(";;;"))[1];
					String[] parametros = parametro.split(":::");
	    			String nombreArchivo = parametros[0];
	    			nombre = parametros[1];
	    			System.out.println("PROTOCOLO: Solicitando la descarga de "+nombreArchivo);
	    			File tempArchivo = new File(mundo.darPath()+"/"+nombreArchivo);
	    			float tamaño = tempArchivo.length();
	    			mundo.setDescargas(tamaño);
	    			System.out.println("CLIENTE: Instanciando PrintStream...");
	    			PrintStream ps = new PrintStream(socket.getOutputStream());
	    			FileInputStream archivo = new FileInputStream(mundo.darPath()+"/"+nombreArchivo);
	    			byte[] buffer = new byte[1024]; 
	    			int len; 
	    			System.out.println("CLIENTE: Enviando archivo...");
	    			while((len=archivo.read(buffer))>0) { 
	    				ps.write(buffer,0,len); 
	    			}
	    			System.out.println("CLIENTE: Transferencia finalizada");
	    			ps.close();
	    			archivo.close();
	    			socket.close();
	    			System.out.println("CLIENTE: Registrando estadísiticas...");
	    			mundo.registrarEstadisticas(nombreArchivo, nombre);
	    			mundo.setDescargas(-tamaño);
	    			stop();
				}
			} catch (Exception e) {
				System.out.println("PROTOCOLO ERROR: Se ha presentado un error en la comunicación...");
				e.printStackTrace();
				System.out.println("");
				System.out.println("PROTOCOLO ERROR: Cerrando canales de comunicación...");
				desconectar();
			}
		}
	}
	// -----------------------------------------------------------------
    // Protocolo
    // -----------------------------------------------------------------
	/**
	 * Entrega la lista de archivos disponibles para descargar
	 * @return
	 */
	private String darListaArchivos() {
		IListaEncadenada<String> archivos = mundo.darNombresArchivos();
		String respuesta = "ARCHIVOS;;;";
		for (String archivo : archivos) respuesta = respuesta+archivo+":::";
		return respuesta.substring(0, respuesta.length()-3);
	}
	/**
	 * Notifica al cliente de la finalización de la comunicación
	 */
	public void logout() {
		System.out.println("CLIENTE: Notificando logout...");
		String protocolo = "LOGOUT";
		System.out.println("OUT --> <"+protocolo+">");
		out.println(protocolo);
		desconectar();
	}
	/**
	 * Cierra el canal de comunicación con el cliente
	 */
	private void desconectar() {
		conectado = false;
		this.interrupt();
		try {
			System.out.println("CLIENTE: Finalizando hilo de ejecución con "+nombre);
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
		} catch (IOException e) {
			System.out.println("CLIENTE - ERROR: No fue posible cerrar el socket...");
			e.printStackTrace();
		}
		mundo.desconectarCliente(this);
		stop();
	}
	// -----------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------
	public String getNombre() {
		return nombre;
	}
}
