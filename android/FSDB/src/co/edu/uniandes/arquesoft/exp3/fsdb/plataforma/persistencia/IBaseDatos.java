package co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.persistencia;

import java.util.ArrayList;

import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.PersistenciaException;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.Registro;

public interface IBaseDatos {
	/**
	 * Inserta un nuevo registro en la base de datos local
	 * @param registro
	 * @throws PersistenciaException 
	 */
	public void insertarRegistro(Registro registro) throws PersistenciaException;
	
	/**
	 * Recupera los registros que aún no han sido enviados o
	 * que aún no se han marcado como entregados.
	 * @return Conjunto de registros no marcados como entregados
	 * POST: Los registros devueltos se marcan como enviados en la
	 * base de datos.
	 */
	public ArrayList<Registro> registrosEnviar();
	
	/**
	 * Marca como entregados todos los registros marcados como 
	 * enviados en la base de datos.
	 * @return Número de registros marcados como entregados
	 */
	public int marcarEntregados();
}
