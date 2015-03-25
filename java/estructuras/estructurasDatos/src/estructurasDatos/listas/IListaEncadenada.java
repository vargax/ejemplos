package estructurasDatos.listas;

import java.io.Serializable;

import estructurasDatos.grafos.IVertice;

public interface IListaEncadenada<T> extends Serializable, Iterable<T>, Cloneable {
	/**
	 * Agrega un nuevo objeto al final de la lista
	 * @param nuevoObjeto: objeto a agregar
	 */
	public void agregar(T nuevoObjeto);
	/**
	 * Determina si un determinado objeto se encuentra almacenado en la lista
	 * @param objetoBuscar Una referencia al objeto buscado
	 * @return Verdadero si el objeto se encuentra en la lista, falso de lo contrario
	 */
	public boolean buscar(T objetoBuscar);
	/**
	 * Elimina un objeto de la lista
	 * @return true : Si el objeto fue encontrado y eliminado, false de lo contrario
	 */
	public boolean eliminar(T objetoEliminar);
	/**
	 * Entrega el número de objetos almacenados en la lista
	 */
	public int darNumeroObjetos();
	/**
	 * Entrega un arreglo con los objetos almacenados en la lista
	 */
	public Object[] toArray();
	/**
	 * Entrega una copia de la lista
	 */
	public IListaEncadenada<T> clone();
}
