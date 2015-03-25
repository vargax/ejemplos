package estructurasDatos;

import java.io.Serializable;

import excepciones.ObjetoNoEncontradoExcepcion;
import excepciones.ObjetoYaExisteExcepcion;

public class ArbolBinarioNodo<T extends Comparable<T>> implements Serializable {
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = -5502264331578963210L;
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	/**
	 * El objeto almacenado en este nodo
	 */
	private T objetoAlmacenado;
	/**
	 * El nodo izquierdo del arbol
	 */
	private ArbolBinarioNodo<T> izquierda;
	/**
	 * El nodo derecho del arbol
	 */
	private ArbolBinarioNodo<T> derecha;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	public ArbolBinarioNodo(T objetoAlmacenar) {
		objetoAlmacenado = objetoAlmacenar;
		
		izquierda = null;
		derecha = null;
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	/**
	 * Entrega el objeto almacenado en el nodo
	 */
	public T darObjeto() {
		return objetoAlmacenado;
	}
	/**
	 * Agrega un nuevo nodo al arbol binario
	 * @throws Exception 
	 */
	public void agregarNodo(ArbolBinarioNodo<T> nuevoNodo) throws ObjetoYaExisteExcepcion {
		int comparacion = objetoAlmacenado.compareTo(nuevoNodo.darObjeto());
		if (comparacion == 0) throw new ObjetoYaExisteExcepcion("El objeto "+nuevoNodo.darObjeto().toString()+" ya se encuetra almacenado en el arbol");
		if (comparacion < 0) {
			if (izquierda != null) izquierda.agregarNodo(nuevoNodo);
			else izquierda = nuevoNodo;
		} else {
			if (derecha != null) derecha.agregarNodo(nuevoNodo);
			else derecha = nuevoNodo;
		}
	}
	/**
	 * Busca un nodo almacenado en el arbol
	 */
	public T buscarNodo(String cadenaObjeto) throws ObjetoNoEncontradoExcepcion {
		int comparacion = objetoAlmacenado.toString().compareTo(cadenaObjeto);
		if (comparacion < 0) return izquierda.buscarNodo(cadenaObjeto);
		if (comparacion == 0) return objetoAlmacenado;
		return derecha.buscarNodo(cadenaObjeto);
	}
	/**
	 * Elimina uno de los nodos almacenados en el arbol
	 * @param objetoEliminar: Una referencia al objeto a eliminar
	 * @return
	 */
	public ArbolBinarioNodo<T> eliminarNodo(T objetoEliminar) {
		if (objetoAlmacenado == objetoEliminar) {
			try {
				izquierda.agregarNodo(derecha);
				return izquierda;
			} catch (ObjetoYaExisteExcepcion e) {
				e.printStackTrace();
			}
		}
		if (objetoAlmacenado.compareTo(objetoEliminar) < 0) izquierda =	izquierda.eliminarNodo(objetoEliminar);
		else derecha = derecha.eliminarNodo(objetoEliminar);
		return this;
	}
}
