package estructurasDatos;

import java.io.Serializable;
import java.util.Iterator;

import excepciones.ObjetoNoEncontradoExcepcion;

public interface IListaSimpleEncadenada<T> extends Serializable, Iterator<T>, Iterable<T> {
	/**
	 * Agrega un nuevo objeto al principio de lista
	 * @param nuevoObjeto: objeto a agregar
	 */
	public void agregarObjeto(T nuevoObjeto);
	
	/**
	 * Busca un objeto en la lista basado en el resultado de su método toString()
	 * @param cadenaObjeto: Cadena que representa el objeto a buscar
	 * @return el objeto buscado
	 */
	public T buscarObjeto(String cadenaObjeto) throws ObjetoNoEncontradoExcepcion;
	
	/**
	 * Elimina un objeto de la lista
	 * @param objetoEliminar: Referencia al objeto a eliminar
	 */
	public void eliminarObjeto(T objetoEliminar);
	
	/**
	 * Elimina un objeto de la lista
	 * @param cadenaObjeto: Cadena que representa el objeto a eliminar
	 */
	public void eliminarObjeto(String cadenaObjeto);
	
	/**
	 * Entrega todos los objetos almacenados en la lista
	 * @return T[]: Arreglo que contiene todos los objetos de tipo T almacenados en la lista
	 */
	public Object[] darObjetos();
	/**
	 * Entrega el número de objetos almacenados en la lista
	 */
	public int darNumeroObjetos();
}
