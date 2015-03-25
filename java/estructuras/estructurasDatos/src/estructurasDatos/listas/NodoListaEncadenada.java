package estructurasDatos.listas;

import java.io.Serializable;

public class NodoListaEncadenada<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El objeto almacenado en el nodo
	 */
	private T objetoAlmacenado;
	/**
	 * El nodo siguiente
	 */
	private NodoListaEncadenada<T> nodoSiguiente;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Crea un nuevo nodo
	 */
	public NodoListaEncadenada(T objetoAlmacenar) {
		objetoAlmacenado = objetoAlmacenar;
		nodoSiguiente = null;
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Entrega el nodo siguiente
	 */
	public NodoListaEncadenada<T> darNodoSiguiente() {
		return nodoSiguiente;
	}
	/**
	 * Cambia el nodo siguiente
	 */
	public void cambiarNodoSiguiente(NodoListaEncadenada<T> nuevoNodo) {
		nodoSiguiente = nuevoNodo;
	}
	/**
	 * Entrega el objeto almacenado en el nodo
	 */
	public T darObjetoAlmacenado() {
		return objetoAlmacenado;
	}
}
