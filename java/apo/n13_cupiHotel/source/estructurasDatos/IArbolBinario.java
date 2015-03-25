package estructurasDatos;

import java.io.Serializable;

import excepciones.ObjetoNoEncontradoExcepcion;
import excepciones.ObjetoYaExisteExcepcion;

public interface IArbolBinario<T extends Comparable<T>> extends Serializable {
	/**
	 * Busca un objeto en el arbol binario
	 * @param cadenaObjeto: Cadena que representa el objeto buscado (compara con el 
	 * resultado del método toString() del objeto almacenado)
	 * @return Una referencia al objeto buscado
	 * @throws ObjetoNoEncontradoExcepcion: En caso de no encontrar el objeto buscado en el arbol
	 */
	public T buscarObjeto(String cadenaObjeto) throws ObjetoNoEncontradoExcepcion;
	/**
	 * Agrega un nuevo objeto al arbol binario
	 * @param nuevoObjeto: El objeto a agregar
	 */
	public void agregarObjeto(T nuevoObjeto) throws ObjetoYaExisteExcepcion;
	/**
	 * Elimina un objeto del arbol binario
	 * @param objetoEliminar: Referencia al objeto a elminar
	 */
	public void eliminarObjeto(T objetoEliminar);
	/**
	 * Elimina un objeto del arbol binario
	 * @param objetoEliminar: Cadena que representa el objeto a eliminar
	 */
	public void eliminarObjeto(String cadenaObjeto) throws ObjetoNoEncontradoExcepcion;
}
