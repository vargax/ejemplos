package co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.dispositivos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import android.util.Log;

import co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.red.IAccesoRed;

/**
 *
 * @author c.vargas124
 */
public class ControladorMockTensiometro implements IDispositivo {
    // -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
    /**
     * Constante para el log
     */
	private final String LOGCAT = "MockDispositivo";
	/**
     * Representa el nombre del dispositivo
     */
    public final static String nombre = "tensiometro";
    /**
     * La ip en la cual reside el dispositivo
     */
    private final static String ip = "157.253.226.174";
    /**
     * El puerto de escucha del dispositivo
     */
    private final static int puerto = 1375;
    /**
     * El separador del protocolo
     */
    private final String SEPARADOR = ";;;";
    /**
     * Tiempo de espera para el Thread
     */
    private final long ESPERA = 5000;
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    /**
     * La interfaz que gestiona el acceso a la red
     */
    private IAccesoRed ar;
    
	private String respuesta;
    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
    public ControladorMockTensiometro(IAccesoRed ar) {
        this.ar = ar;
    }
    
    // -------------------------------------------------------------------------
    // Métodos Interfaz
    // -------------------------------------------------------------------------
    /**
     * Entrega el nombre del dispositivo que realiza la medición
     * @return El nombre el dispositivo
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece una conexión con el dispositivo, recupera la medición y cierra
     * la conexión.
     * @return String que representa la medición recuperada del dispositivo
     * @throws ConexionException En caso que no sea posible establecer una 
     * conexión con el dispositivo.
     */
    public String recuperarDato() throws ConexionException {
    	Thread thread = new Thread() {
    		public void run() {
		        try {
		        	Log.d(LOGCAT, nombre+": Solicitando Socket...");
					Socket socket = ar.solicitarSocket(ip, puerto);
					Log.d(LOGCAT, nombre+": Se ha establecido la conexión...");
			        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			        respuesta = in.readLine();
			        Log.d(LOGCAT, nombre+": Se ha recuperado el dato "+respuesta);
			        socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	};
    	thread.start();
    	try {
			thread.join(ESPERA);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	if (respuesta == null) throw new ConexionException("No fue posible recuperar el dato del "+ControladorMockTensiometro.nombre);
    	return this.respuesta;
    }
    
    @Override
	public String[] parserDato(String dato) {
		return dato.split(this.SEPARADOR);
	}
	// -------------------------------------------------------------------------
    // Métodos Superclase
    // -------------------------------------------------------------------------

}
