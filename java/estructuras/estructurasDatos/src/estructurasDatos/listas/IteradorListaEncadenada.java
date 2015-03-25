package estructurasDatos.listas;

import java.io.Serializable;
import java.util.Iterator;


public abstract class IteradorListaEncadenada<T> implements Iterator<T>, Serializable {
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	protected NodoListaEncadenada<T> nodoAnterior;
	protected NodoListaEncadenada<T> nodoActual;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public IteradorListaEncadenada(NodoListaEncadenada<T> cabezaLista) {
		nodoActual = new NodoListaEncadenada<T>(null);
		nodoActual.cambiarNodoSiguiente(cabezaLista);
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	@Override
	public boolean hasNext() {
		return nodoActual != null ? nodoActual.darNodoSiguiente() != null : false;
	}

	@Override
	public T next() {
		nodoAnterior = nodoActual;
		nodoActual = nodoActual.darNodoSiguiente();
		if (nodoActual != null) return nodoActual.darObjetoAlmacenado();
		return null;
	}
}
