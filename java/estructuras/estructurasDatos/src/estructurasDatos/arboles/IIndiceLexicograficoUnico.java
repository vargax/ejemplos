package estructurasDatos.arboles;

import estructurasDatos.IIdentificable;
import estructurasDatos.listas.IListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;

public interface IIndiceLexicograficoUnico<T extends IIdentificable> {
	/**
	 * Agrega un elemento al índice 
	 * @param objetoGuardar: Una referencia al objeto a almacenar
	 * @throws ElementoExisteException en caso de encontrar otro elemento con el
	 * mismo identificador en la estructura.
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador del objeto no genere un criterio
	 * de ordenamiento válido para almacenar el objeto en el índice.
	 */
	public void agregarObjeto(T objetoGuardar) throws ElementoExisteException, CriterioOrdenamientoInvalidoException;
	/**
	 * Recupera un objeto del índice
	 * @param id: El identificador del objeto
	 * @return El objeto buscado o null en caso de no encontrar ningún objeto con dicho id
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador introducido no genere
	 * un criterio de ordenamiento válido para ubicar el objeto en el índice.
	 */
	public T recuperarObjeto(String id) throws CriterioOrdenamientoInvalidoException;
	/**
	 * Devuelve una lista que contiene todos los elementos almacenados en el índice
	 * @return
	 */
	public IListaEncadenada<T> darObjetos();
	/**
	 * Devuelve el número de objetos almacenados en el índice
	 * @return Entero que representa el número de elementos actualmente almacenados en el índice
	 */
	public int darNumeroElementos();
}
