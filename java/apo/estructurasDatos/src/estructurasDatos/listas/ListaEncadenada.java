package estructurasDatos.listas;

import java.util.Iterator;


public class ListaEncadenada<T> implements IListaEncadenada<T> {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * La cabeza de la lista
	 */
	protected NodoListaEncadenada<T> cabezaLista;
	/**
	 * La cola de la lista
	 */
	protected NodoListaEncadenada<T> colaLista;
	/**
	 * El número de elementos en la lista
	 */
	protected int numeroElementos;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Crea un nueva lista encadenada vacía
	 */
	public ListaEncadenada () {
		cabezaLista = null;
		colaLista = null;
		numeroElementos = 0;
	}
	/**
	 * Crea una nueva lista encadenada con un elemento
	 */
	public ListaEncadenada(T primerElemento) {
		cabezaLista = null;
		colaLista = null;
		numeroElementos = 0;
		agregar(primerElemento);
	}
	//-----------------------------------------------------------------
    // Métodos protegidos
    //-----------------------------------------------------------------
	/**
	 * Entrega la cola de la lista
	 */
	protected NodoListaEncadenada<T> darCola() {
		return colaLista;
	}
	//-----------------------------------------------------------------
    // Métodos Interfaz
    //-----------------------------------------------------------------
	@Override
	public Iterator<T> iterator() {
		IteradorListaEncadenada<T> iterador = new IteradorListaEncadenada<T>(cabezaLista) {
			@Override
			public void remove() {
				// Si es el único elemento
				if (nodoActual == cabezaLista && cabezaLista == colaLista) {
					cabezaLista = null;
					colaLista = null;
					numeroElementos = 0;
				}
				// Si es la cabeza de la lista
				else if (nodoActual == cabezaLista) {
					cabezaLista = cabezaLista.darNodoSiguiente();
					numeroElementos--;
				}
				// Si es la cola de la lista
				else if (nodoActual == colaLista) {
					colaLista = nodoAnterior;
					nodoAnterior.cambiarNodoSiguiente(null);
					numeroElementos--;
				}
				// Caso General
				else {
					nodoAnterior.cambiarNodoSiguiente(nodoActual.darNodoSiguiente());
					nodoActual = nodoAnterior.darNodoSiguiente();
					numeroElementos--;
				}
			}
		};
		return iterador; 
	}

	@Override
	public void agregar(T nuevoObjeto) {
		NodoListaEncadenada<T> nuevoNodo = new NodoListaEncadenada<T>(nuevoObjeto);
		if (colaLista != null) {
			colaLista.cambiarNodoSiguiente(nuevoNodo);
			colaLista = nuevoNodo;
		}
		else {
			cabezaLista = nuevoNodo;
			colaLista = nuevoNodo;
		}
		numeroElementos++;
	}
	
	@Override
	public boolean eliminar(T objetoEliminar) {
		Iterator<T> i = iterator();
		boolean encontrado = false;
		while(i.hasNext() && !encontrado) {
			if (i.next() == objetoEliminar) {
				i.remove();
				encontrado = true;
			}
		}
		return encontrado;
	}

	@Override
	public int darNumeroObjetos() {
		return numeroElementos;
	}
	
	@Override
	public Object[] toArray() {
		Object[] respuesta = new Object[numeroElementos];
		int i = 0;
		for(T t : this) {
			respuesta[i] = t;
			i++;
		}
		return respuesta;
	}
	
	@Override
	public IListaEncadenada<T> clone() {
		IListaEncadenada<T> respuesta = new ListaEncadenada<T>();
		for(T t : this) respuesta.agregar(t);
		return respuesta;
	}
	@Override
	public boolean buscar(T objetoBuscar) {
		for(T t : this) {
			if (t == objetoBuscar) return true;
		}
		return false;
	}
}
