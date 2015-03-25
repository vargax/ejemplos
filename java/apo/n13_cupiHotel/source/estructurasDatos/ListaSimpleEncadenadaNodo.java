package estructurasDatos;

import java.io.Serializable;

public class ListaSimpleEncadenadaNodo<T> implements Serializable{
	
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = -4335640139327971494L;
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	/**
	 * El objeto almacenado en este nodo
	 */
	private T objetoAlmacenado;
	/**
	 * El siguiente elemento de la lista
	 */
	private ListaSimpleEncadenadaNodo<T> siguienteNodo;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	/**
	 * Crea un nuevo nodo de una lista simplemente encadenada
	 * @param objetoAlmacenar: el objeto a almacenar en el nodo
	 */
	public ListaSimpleEncadenadaNodo(T objetoAlmacenar) {
		objetoAlmacenado = objetoAlmacenar;
		siguienteNodo = null;
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	/**
	 * Define el siguiente elemento de la lista
	 * @param siguienteNodo: el siguiente nodo
	 */
	public void definirSiguiente(ListaSimpleEncadenadaNodo<T> siguienteNodoP) {
		siguienteNodo = siguienteNodoP;
	}
	/**
	 * Entrega el siguiente nodo de la lista
	 */
	public ListaSimpleEncadenadaNodo<T> darSiguiente() {
		return siguienteNodo;
	}
	/**
	 * Entrega el elemento almacenado en este nodo
	 */
	public T darObjeto() {
		return objetoAlmacenado;
	}
}
