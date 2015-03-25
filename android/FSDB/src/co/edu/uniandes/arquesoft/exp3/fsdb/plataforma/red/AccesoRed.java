package co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.red;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.dispositivos.ConexionException;

import android.util.Log;

/**
 *
 * @author c.vargas124
 */
public class AccesoRed implements IAccesoRed {
	// -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
    /**
     * Constante para el log
     */
	public final String LOGCAT = "AccesoRed";
	// -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
	public AccesoRed() {
		Log.d(LOGCAT, "Instanciando controlador red...");
	}
	// -------------------------------------------------------------------------
    // Métodos Interfaz
    // -------------------------------------------------------------------------
	public Socket solicitarSocket(final String ip, final int puerto) throws ConexionException {
		Log.d(LOGCAT, "Generando un socket para "+ip+" en el puerto "+puerto);
		try {
			return new Socket(ip, puerto);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new ConexionException("Error al establecer la conexión: Host desconocido");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ConexionException("Error al establecer la conexión: Error de Entrada/Salida en el Socket");
		}
    }
}