package estructurasDatos;

import java.util.Iterator;

import excepciones.ObjetoNoEncontradoExcepcion;

public class ListaSimpleEncadenada<T> implements IListaSimpleEncadenada<T> {
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = 2743772800552517606L;
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	/**
	 * La cabeza de la lista
	 */
	private ListaSimpleEncadenadaNodo<T> cabezaLista;
	/**
	 * Un apuntador para la lista
	 */
	private ListaSimpleEncadenadaNodo<T> apuntadorIterador;
	/**
	 * Entero que representa el número de elementos almacenados en la lista
	 */
	private int numElementos;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	public ListaSimpleEncadenada(T primerElemento) {
		cabezaLista = new ListaSimpleEncadenadaNodo<T>(primerElemento);
		numElementos = 1;
		apuntadorIterador = cabezaLista;
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	@Override
	public void agregarObjeto(T nuevoObjeto) {
		ListaSimpleEncadenadaNodo<T> tempNodo = new ListaSimpleEncadenadaNodo<T>(nuevoObjeto);
		tempNodo.definirSiguiente(cabezaLista);
		cabezaLista = tempNodo;
		numElementos++;
	}

	@Override
	public T buscarObjeto(String cadenaObjeto) throws ObjetoNoEncontradoExcepcion {
		ListaSimpleEncadenadaNodo<T> apuntador = cabezaLista;
		while (apuntador != null) {
			T tempObjeto = apuntador.darObjeto();
			if (tempObjeto.toString().equals(cadenaObjeto)) return tempObjeto;
			apuntador = apuntador.darSiguiente();
		}
		throw new ObjetoNoEncontradoExcepcion("No se encontro el objeto "+cadenaObjeto+" en la lista");
	}

	@Override
	public void eliminarObjeto(T objetoEliminar) {
		// En caso que el objeto a eliminar sea el primero de la lista
		if (cabezaLista.darObjeto() == objetoEliminar) {
			cabezaLista = cabezaLista.darSiguiente();
			numElementos--;
		}
		// De lo contrario debo recorrer la lista
		else {
			boolean continuar = true;
			ListaSimpleEncadenadaNodo<T> apuntador = cabezaLista;
			
			while (continuar && apuntador.darSiguiente() != null) {
				T tempObjeto = apuntador.darSiguiente().darObjeto();
				if (tempObjeto == objetoEliminar) {
					continuar = false;
					apuntador.definirSiguiente(apuntador.darSiguiente().darSiguiente());
					numElementos--;
				}
				apuntador = apuntador.darSiguiente();
			}
		}
	}

	@Override
	public void eliminarObjeto(String cadenaObjeto) {
		// En caso que el objeto a eliminar sea el primero de la lista
		if (cabezaLista.darObjeto().toString().equals(cadenaObjeto)) {
			cabezaLista = cabezaLista.darSiguiente();
			numElementos--;
		}
		// De lo contrario debo recorrer la lista
		else {
			boolean continuar = true;
			ListaSimpleEncadenadaNodo<T> apuntador = cabezaLista;
			
			while (continuar && apuntador.darSiguiente() != null) {
				T tempObjeto = apuntador.darSiguiente().darObjeto();
				if (tempObjeto.toString().equals(cadenaObjeto)) {
					continuar = false;
					apuntador.definirSiguiente(apuntador.darSiguiente().darSiguiente());
					numElementos--;
				}
				apuntador = apuntador.darSiguiente();
			}
		}
	}
	/* (non-Javadoc)
	 * @see estructurasDatos.IListaSimpleEncadenada#darObjetos()
	 */
	@Override
	public Object[] darObjetos() {
		Object[] respuesta = new Object[numElementos];
		if (numElementos == 0) return respuesta;
		
		ListaSimpleEncadenadaNodo<T> apuntador = cabezaLista;
		respuesta[0] = apuntador.darObjeto();
		for (int i = 1; i < numElementos; i++) {
			apuntador = apuntador.darSiguiente();
			respuesta[i] =  apuntador.darObjeto();
		}
		return respuesta;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (apuntadorIterador == cabezaLista) return true;
		return apuntadorIterador.darSiguiente() != null;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		T respuesta = apuntadorIterador.darObjeto();
		apuntadorIterador = apuntadorIterador.darSiguiente();
		return respuesta;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		apuntadorIterador = cabezaLista;
		return this;
	}
	/* (non-Javadoc)
	 * @see estructurasDatos.IListaSimpleEncadenada#darNumeroObjetos()
	 */
	@Override
	public int darNumeroObjetos() {
		return numElementos;
	}
}
