package uniandes.cupi2.componenteCompartir.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import estructurasDatos.IListaEncadenada;
import estructurasDatos.ListaEncadenada;

public class Servidor extends Thread {
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	/**
	 * El mundo de la aplicación
	 */
	private ComponenteCompartir mundo;
	/**
	 * La ip del servidor
	 */
	private String ip;
	/**
	 * El puerto del servidor
	 */
	private int puerto;
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
	 * Crea una conexión con un servidor cupiPhone
	 * @param mundoP: Una referencia al mundo de la aplicación
	 * @param ipP: La ip del servidor
	 * @param puertoP: El puerto de escucha del servidor
	 * @throws UnknownHostException: En caso de no encontrar al servidor
	 * @throws IOException: En caso de presentarse un error con los canales de lectura/escritura
	 */
	public Servidor(ComponenteCompartir mundoP, String ipP, int puertoP) throws UnknownHostException, IOException {
		mundo = mundoP;
		ip = ipP;
		puerto = puertoP;
		System.out.println("SERVIDOR: Inicializando conexión con "+ip+":"+puerto);
		socket = new Socket(ip,puerto);
		out = new PrintWriter(socket.getOutputStream(),true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		conectado = true;
		// Inicia la conexión con el servidor
		login();
	}
	// -----------------------------------------------------------------
    // Hilo de ejecución
    // -----------------------------------------------------------------
	/**
	 * Inicia la comunicación con el servidor y realiza todas las operaciones necesarias mientras esta dure
	 */
	public void run() {
		while(conectado && !socket.isClosed()) {
			try {
				String protocolo = in.readLine();
				System.out.println("<"+protocolo+"> --> IN");
// Confirmación login ----------------------------------------------------------------
				if(protocolo.startsWith("LOGINOK")) {
					System.out.println("PROTOCOLO: Login confirmado...");
					actualizarArchivos();
				}
// Logout ----------------------------------------------------------------------------
				else if(protocolo.startsWith("LOGOUT")) {
					System.out.println("PROTOCOLO: Solicitud de salida...");
					desconectar();
				}
// Recibir listado de archivos -------------------------------------------------------
				else if(protocolo.startsWith("ARCHIVOS")) {
					System.out.println("PROTOCOLO: Listado de archivos recibido...");
					IListaEncadenada<String> respuesta = new ListaEncadenada<String>();
					if (protocolo.split(";;;").length >1) {
						String parametro = protocolo.split(";;;")[1];
						String[] parametros = parametro.split(":::");
						for(String s : parametros) respuesta.agregar(s);
						System.out.println("SERVIDOR: Recuperados "+respuesta.darNumeroObjetos()+" archivos");
					} else {
						respuesta.agregar("Ningún archivo disponible");
						System.out.println("SERVIDOR: Recuperados 0 archivos");
					}
					mundo.refrescarRemotos(respuesta);
				}
			} catch (Exception e) {
				System.out.println("PROTOCOLO ERROR: Se ha presentado un error en la comunicación...");
				System.out.println("PROTOCOLO ERROR: Cerrando canales de comunicación...");
				e.printStackTrace();
				desconectar();
			}
		}
	}
	// -----------------------------------------------------------------
    // Protocolo
    // -----------------------------------------------------------------
	/**
	 * Realiza el login del cliente en el servidor
	 * @throws IOException 
	 */
	private void login() throws IOException {
		System.out.println("SERVIDOR: Solicitando login...");
		String protocolo = "LOGIN;;;"+mundo.darNombre();
		System.out.println("OUT --> <"+protocolo+">");
		out.println(protocolo);
		
	}
	/**
	 * Notifica al servidor de la finalización de la comunicación
	 */
	public void logout() {
		System.out.println("SERVIDOR: Notificando logout...");
		String protocolo = "LOGOUT";
		System.out.println("OUT --> <"+protocolo+">");
		out.println(protocolo);
		desconectar();
	}
	/**
	 * Actualiza el listado de archivos disponibles en el servidor
	 * @throws IOException 
	 */
	public void actualizarArchivos() throws IOException {
		System.out.println("SERVIDOR: Recuperando el listado de archivos...");
		String protocolo = "ARCHIVOS";
		System.out.println("OUT --> <"+protocolo+">");
		out.println(protocolo);
	}
	/**
	 * Solicita al servidor la descarga de un archivo
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 */
	public void descargarArchivo(String nombreArchivoP) {
		System.out.println("SERVIDOR: Solicitando el envío de "+nombreArchivoP);
		final String nombreArchivo = nombreArchivoP;
		Thread descarga = new Thread() {
			public void run() {
				try {
					System.out.println("SERVIDOR - DESCARGA: Iniciando canal dedicado para la descarga de un elemento...");
					Socket socketDescarga = new Socket(ip,puerto);
					PrintWriter outDescarga = new PrintWriter(socketDescarga.getOutputStream(),true);
	    			System.out.println("PROTOCOLO: Se recibirá el archivo "+nombreArchivo);
	    			FileOutputStream fos = new FileOutputStream(mundo.darPath()+"/"+nombreArchivo+"cupiShare");
					System.out.println("SERVIDOR - DESCARGA: Instanciando InputStream...");
					InputStream is = socketDescarga.getInputStream();
					String protocolo = "DESCARGAR;;;"+nombreArchivo+":::"+mundo.darNombre();
					System.out.println("OUT --> <"+protocolo+">");
					outDescarga.println(protocolo);
					byte[] buffer = new byte[1024];
					int len;
					System.out.println("SERVIDOR - DESCARGA: Leyendo archivo...");
					while((len=is.read(buffer))>0) {
						fos.write(buffer,0,len);
					}
					System.out.println("SERVIDOR - DESCARGA: Transferencia finalizada");
					fos.close();
					is.close();
					socketDescarga.close();
					System.out.println("SERVIDOR - DESCARGA: Desconectando...");
					mundo.refrescarLocales();
				} catch (Exception e) {
					System.out.println("SERVIDOR - DESCARGA - ERROR: Se presentó un error al procesar la descarga");
					e.printStackTrace();
				}
			}
		};
		descarga.start();
	}
	/**
	 * Cierra el canal de comunicación con el servidor
	 */
	private void desconectar() {
		conectado = false;
		this.interrupt();
		try {
			System.out.println("SERVIDOR: Finalizando hilo de ejecución con "+ip+":"+puerto);
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
			mundo.desconectarServidor(ip+":"+puerto);
		} catch (IOException e) {
			System.out.println("SERVIDOR - ERROR: No fue posible cerrar el socket...");
			e.printStackTrace();
		}
	}
	// -----------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------
	public String darIp() {
		return ip;
	}
}
