package estructurasDatos.arboles;

import estructurasDatos.listas.IListaEncadenada;
import estructurasDatos.listas.ListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;

/**
 * Una estructura de datos que permite recuperar sus objetos a partir de un identificador.
 * Un mismo elemento puede ser almacenado múltiples identificadores. 
 * La estructura evoluciona dinamicamente en función del número de elementos agregados.
 * Los objetos se almacenan en listas simples encadenadas mientras sean menos de 37, una vez
 * es superado este valor se genera un arreglo de 37 nuevos IndicesLexicográficos y se reorganizan
 * lo elementos de la lista en estos índices. A medida que el índice crece los elementos se van
 * organizando en una jerarquia similar a la de un Trie.
 * @author cvargasc
 */
public class IndiceLexicografico<T> implements IIndiceLexicografico<T> {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	protected final static int NUMERO_ELEMENTOS_REHASH = 37;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El número de elementos actualmente almacenados en el índice
	 */
	protected int numeroElementos;
	/**
	 * Cadena que representa la raiz que tienen en común todos los elementos almacenados
	 * en este índice.
	 */
	protected String raizComun;
	/**
	 * Una lista encadenada para almacenar los elementos cuyo criterio de ordenamiento finaliza en este nivel.
	 * También se utiliza como única estructura de almacenamiento mientras numeroElementos < NUMERO_ELEMENTOS_REHASH. 
	 */
	protected IListaEncadenada<NodoIndice<T>> lista;
	/**
	 * Un arreglo de índices para almacenar los elementos cuando numeroElementos > NUMERO_ELEMENTOS_REHASH
	 */
	protected IndiceLexicografico<T>[] indices;
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	/**
	 * Crea un nuevo índice lexicográfico vacio.
	 */
	public IndiceLexicografico() {
		numeroElementos = 0;
		raizComun = "";
		lista = new ListaEncadenada<NodoIndice<T>>();
		indices = null;
	}
	/**
	 * Crea un nuevo indice lexicográfico con el primer elemento
	 * @throws CriterioOrdenamientoInvalidoException En caso que el criterioOrden introducido para el primer objeto
	 * no genere un criterio de ordenamiento válido para el índice.
	 */
	public IndiceLexicografico(String criterioOrden, T primerElemento) throws CriterioOrdenamientoInvalidoException {
		numeroElementos = 0;
		raizComun = "";
		lista = new ListaEncadenada<NodoIndice<T>>();
		indices = null;
		agregarObjeto(criterioOrden, primerElemento);
	}
	/**
	 * Crea un nuevo índice lexicográfico anidado
	 */
	protected IndiceLexicografico(String raizComunP) {
		numeroElementos = 0;
		raizComun = raizComunP;
		lista = new ListaEncadenada<NodoIndice<T>>();
		indices = null;
	}
	//-----------------------------------------------------------------
    // Métodos protegidos
    //-----------------------------------------------------------------
	/**
	 * Función de hash.
	 * @return Entero que representa la posición del objeto en el arreglo dado su criterio de ordenamiento. 
	 */
	protected int funcionHash(String criterioReducido) {
		criterioReducido = criterioReducido.substring(0, raizComun.length());
//		//** DEBUG **//
//		if (!criterioReducido.startsWith(raizComun)) 
//			throw new CriterioOrdenamientoInvalidoException("El criterio de ordenamiento reducido del nodo" +
//				" es "+nodo.darCriterioReducido()+" mientras la raiz común de este índice es "+raizComun);
		//** DEBUG **//
		
		if (raizComun.equals(criterioReducido)) return -1;
		
		char siguienteCaracter = criterioReducido.charAt(raizComun.length());
		return Character.isDigit(siguienteCaracter) ? Integer.parseInt(siguienteCaracter+"") : siguienteCaracter - 87;
	}
	/**
	 * Función de rehash().
	 * Reemplaza la lista encadenada por un arreglo de índices.
	 * @throws CriterioOrdenamientoInvalidoException 
	 */
	protected void rehash() {
		IListaEncadenada<NodoIndice<T>> tempLista = lista;
		
		numeroElementos = 0;
		lista = new ListaEncadenada<NodoIndice<T>>();
		indices = new IndiceLexicografico[37];
		//** DEBUG **//
//		System.out.println("Llamado de rehash() sobre "+raizComun+" ("+numeroElementos+")");
		//** DEBUG **//
		for(NodoIndice<T> nodo : tempLista) agregarNodo(nodo);
	}
	/**
	 * Agrega el nodo recibido como parámetro al índice
	 * @throws CriterioOrdenamientoInvalidoException 
	 */
	protected void agregarNodo(NodoIndice<T> nodo) {
		if (numeroElementos < NUMERO_ELEMENTOS_REHASH) {
			lista.agregar(nodo);
		} else {
			if (indices == null) rehash();
			int posicion = funcionHash(nodo.darCriterioReducido());
			if (posicion == -1) lista.agregar(nodo);
			else {		
				if (indices[posicion] == null) indices[posicion] = new IndiceLexicografico<T>(raizComun+nodo.darCriterioReducido().charAt(raizComun.length()));
				indices[posicion].agregarNodo(nodo);
			}
		}
		numeroElementos++;
	}
	/**
	 * Recupera los nodos que coinciden con el criterio de busqueda solicitado 
	 * @throws CriterioOrdenamientoInvalidoException 
	 */
	protected void recuperarNodos(String criterioBusqueda, IListaEncadenada<T> contenedoraNodos) throws CriterioOrdenamientoInvalidoException {
		int posicion = funcionHash(NodoIndice.generarCriterioReducido(criterioBusqueda));
		if (posicion == -1 || indices == null) {
			for (NodoIndice<T> nodoActual : lista) {
				if (nodoActual.darCriterioOrdenamiento().startsWith(criterioBusqueda)) {
					contenedoraNodos.agregar(nodoActual.darObjetoAlmacenado());
				}
			}
			
			if (indices != null)
				for (IndiceLexicografico<T> ind : indices) 
					if (ind != null) ind.darNodos(contenedoraNodos);
		} else if (indices != null && indices[posicion] != null) indices[posicion].recuperarNodos(criterioBusqueda, contenedoraNodos);
	}
	/**
	 * Entrega todos los nodos almacenados en el índice
	 */
	protected void darNodos(IListaEncadenada<T> contenedoraNodos) {
		for (NodoIndice<T> nodo : lista) {
			contenedoraNodos.agregar(nodo.darObjetoAlmacenado());
		}
		
		if (indices != null)
			for (IndiceLexicografico<T> ind : indices)
				if (ind != null) ind.darNodos(contenedoraNodos);
	}
	//-----------------------------------------------------------------
    // Métodos Interfaz
    //-----------------------------------------------------------------
	@Override
	public void agregarObjeto(String criterioOrden, T objetoGuardar) throws CriterioOrdenamientoInvalidoException {
		NodoIndice<T> nodo = new NodoIndice<T>(criterioOrden, objetoGuardar);
		agregarNodo(nodo);
	}

	@Override
	public IListaEncadenada<T> buscarObjeto(String criterioBusqueda) throws CriterioOrdenamientoInvalidoException {
		ListaEncadenada<T> contenedoraNodos = new ListaEncadenada<T>();
		recuperarNodos(criterioBusqueda, contenedoraNodos);
		
		return contenedoraNodos;
	}
	
	@Override
	public IListaEncadenada<T> darObjetos() {
		ListaEncadenada<T> respuesta = new ListaEncadenada<T>();
		darNodos(respuesta);
		return respuesta;
	}
	
	@Override
	public int darNumeroElementos() {
		return numeroElementos;
	}
}
