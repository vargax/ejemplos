package co.edu.uniandes.arquesoft.exp3.fsdb.nucleo;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.GestionDatosRemoto.GestionDatosRemoto;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.dispositivos.ConexionException;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.dispositivos.ControladorMockTensiometro;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.dispositivos.IDispositivo;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.Persistencia;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.PersistenciaException;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.Registro;
import co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.red.IAccesoRed;
import co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.red.AccesoRed;

/**
 *
 * @author c.vargas124
 */
public class GestionDatosLocal  implements IRecolectarDatosDispositivos {
    // -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
	public final String LOG_CAT = "GestionDatosLocal";
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
	/**
	 * El contexto de la aplicación
	 */
	private Context contexto;
    /**
     * El manejador de persistencia
     */
    private Persistencia persistencia;
    /**
     * El dispositivo a gestionar
     */
    private IDispositivo dispositivo;
    
    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
    public GestionDatosLocal(Context contexto) {
    	this.contexto = contexto;
        IAccesoRed ar = new AccesoRed();
        this.persistencia = new Persistencia(this.contexto);
        this.dispositivo = new ControladorMockTensiometro(ar);
    }
    
    // -------------------------------------------------------------------------
    // Métodos ISincronizarFSDB
    // -------------------------------------------------------------------------
    public void sincronizarFSDB() {
    	Thread thread = new Thread() {
    		public void run() {
		    	try {
		    		Log.d(LOG_CAT, "Instanciando gestor de datos remoto...");
		    		GestionDatosRemoto gdr = new GestionDatosRemoto();
		    		ArrayList<Registro> registros = persistencia.recuperarNoEnviados();
		    		Log.d(LOG_CAT, "Recuperados "+registros.size()+" registros pendientes por entregar");
//		    		if (registros.size() > 0) {
			    		boolean entregados = gdr.enviarDatos(registros);
			    		if (entregados) {
			    			int actualizados = persistencia.notificarEntrega();
			    			Log.d(LOG_CAT, "Se han marcado "+actualizados+" registros como entregados");
//			    		}
		    		}
		    	} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	};
    	thread.start();
    }

    // -------------------------------------------------------------------------
    // Métodos IRecolectarDatosDispositivos
    // -------------------------------------------------------------------------
    public String[] recolectarDato() throws ConexionException, PersistenciaException {
        String dato = dispositivo.recuperarDato();
        persistencia.registrarDato(dispositivo.getNombre(), dato);
        return dispositivo.parserDato(dato);
    }
}
