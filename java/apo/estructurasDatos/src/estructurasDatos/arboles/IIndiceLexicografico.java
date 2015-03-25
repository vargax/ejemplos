package estructurasDatos.arboles;

import java.io.Serializable;

import estructurasDatos.listas.IListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;

public interface IIndiceLexicografico<T> extends Serializable {
	/**
	 * Agrega un elemento al índice
	 * @param criterioOrden: La cadena a través de la cual se recuperará el objeto 
	 * @param objetoGuardar: Una referencia al objeto a almacenar
	 * @throws CriterioOrdenamientoInvalidoException En caso que el criterioOrden introducido
	 * para el objeto no genere un criterio de ordenamiento válido para ubicar el objeto en el índice.
	 */
	public void agregarObjeto(String criterioOrden, T objetoGuardar) throws CriterioOrdenamientoInvalidoException;
	/**
	 * Recupera objetos del índice
	 * @param criterioBusqueda: La cadena buscada
	 * @return Una lista encadenada que contiene todos los objetos coincidentes.
	 * @throws CriterioOrdenamientoInvalidoException En caso que el criterio de busqueda introducido no 
	 * genere un criterio de ordenamiento válido para ubicar el objeto en el índice.
	 */
	public IListaEncadenada<T> buscarObjeto(String criterioBusqueda) throws CriterioOrdenamientoInvalidoException;
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
