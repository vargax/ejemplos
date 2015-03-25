package estructurasDatos;

import excepciones.ObjetoNoEncontradoExcepcion;
import excepciones.ObjetoYaExisteExcepcion;

public class ArbolBinario<T extends Comparable<T>> implements IArbolBinario<T> {
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = 6432041430581567481L;
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	/**
	 * La raiz del arbol binario
	 */
	private ArbolBinarioNodo<T> raiz;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	/**
	 * Crea un nuevo arbol binario
	 */
	public ArbolBinario(T primerObjeto) {
		raiz = new ArbolBinarioNodo<T>(primerObjeto);
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	@Override
	public T buscarObjeto(String cadenaObjeto) throws ObjetoNoEncontradoExcepcion {
		return raiz.buscarNodo(cadenaObjeto);
	}
	@Override
	public void agregarObjeto(T nuevoObjeto) throws ObjetoYaExisteExcepcion {
		raiz.agregarNodo(new ArbolBinarioNodo<T>(nuevoObjeto));
	}
	@Override
	public void eliminarObjeto(T objetoEliminar) {
		raiz.eliminarNodo(objetoEliminar);
	}
	@Override
	public void eliminarObjeto(String cadenaObjeto) throws ObjetoNoEncontradoExcepcion {
		raiz.eliminarNodo(buscarObjeto(cadenaObjeto));
	}
}
