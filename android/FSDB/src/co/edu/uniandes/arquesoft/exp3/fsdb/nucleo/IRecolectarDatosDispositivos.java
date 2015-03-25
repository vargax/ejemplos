	package co.edu.uniandes.arquesoft.exp3.fsdb.nucleo;

import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.dispositivos.ConexionException;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.PersistenciaException;

/**
 *
 * @author c.vargas124
 */
public interface IRecolectarDatosDispositivos {
    public String[] recolectarDato() throws ConexionException, PersistenciaException;
}
