package estructurasDatos.listas;

public class ListaEncadenadaOrdenada<T extends Comparable<T>> extends ListaEncadenada<T> {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Crea una nueva lista encadenada ordenada vacía
	 */
	public ListaEncadenadaOrdenada() {
		super();
	}
	/**
	 * Crea una nueva lista encadena ordenada con el primer elemento
	 */
	public ListaEncadenadaOrdenada(T primerElemento) {
		super();
		agregar(primerElemento);
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	@Override
	public void agregar(T nuevoObjeto) {
		NodoListaEncadenada<T> nuevoNodo = new NodoListaEncadenada<T>(nuevoObjeto);
		
		if (cabezaLista == null) {
			cabezaLista = nuevoNodo;
			colaLista = nuevoNodo;
		} else if (cabezaLista.darObjetoAlmacenado().compareTo(nuevoObjeto) > 0) { 
			nuevoNodo.cambiarNodoSiguiente(cabezaLista);
			cabezaLista = nuevoNodo;
		} else {
			NodoListaEncadenada<T> apuntador = cabezaLista;
			NodoListaEncadenada<T> anterior = null;
			
			while (apuntador != null && apuntador.darObjetoAlmacenado().compareTo(nuevoObjeto) <= 0) {
				anterior = apuntador;
				apuntador = apuntador.darNodoSiguiente();
			}
			
			anterior.cambiarNodoSiguiente(nuevoNodo);
			nuevoNodo.cambiarNodoSiguiente(apuntador);
			
			if(apuntador == null) colaLista = nuevoNodo;
		}
		numeroElementos++;
	}
	
	public T darPrimerElemento() {
		return cabezaLista != null ? cabezaLista.darObjetoAlmacenado() : null;
	}
	
	public T darUltimoElemento() {
		return colaLista != null ? colaLista.darObjetoAlmacenado() : null;
	}
}
