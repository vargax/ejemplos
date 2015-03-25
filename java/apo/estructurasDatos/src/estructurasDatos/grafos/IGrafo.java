package estructurasDatos.grafos;

import java.util.Stack;

import estructurasDatos.Dupla;
import estructurasDatos.IIdentificable;
import estructurasDatos.IIdentificableComparable;
import estructurasDatos.listas.IListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;
import excepciones.ElementoNoExisteException;
/**
 * 
 * @author cvargasc
 *
 * @param <T> El tipo de los vertices
 * @param <U> El tipo de los arcos
 */

public interface IGrafo<T extends IIdentificable, U extends IIdentificableComparable<U>> {
	/**
	 * Agrega un nuevo vertice al grafo
	 * @param nuevoVertice: La información del vertice a agregar
	 * @throws ElementoExisteException En caso que ya se encuentre inscrito otro
	 * vertice con el mismo identificador en el grafo.
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador del objeto no genere un criterio
	 * de ordenamiento válido para almacenar el objeto en el índice.
	 */
	public void agregarVertice(T nuevoVertice) throws ElementoExisteException, CriterioOrdenamientoInvalidoException;
	/**
	 * Agrega un nuevo arco al grafo
	 * @param idVerticeOrigen: El identificador del vertice de origen
	 * @param idVerticeDestino: El identificador del vertice de destino
	 * @param arco: La información del arco a agregar
	 * @throws ElementoNoExisteException: En caso de no encontrarse el vertice de
	 * origen o de destino en el grafo.
	 * @throws ElementoExisteException: En caso de encontrarse un arco con el mismo
	 * id registrado en el grafo. 
	 * @throws CriterioOrdenamientoInvalidoException En caso que los identificadores de los vértices no generen un criterio
	 * de ordenamiento válido para recuperar los vértices del índice ó el identificador del arco no genere un criterio de 
	 * ordenamiento válido para almacenar el arco en el índice.
	 */
	public void agregarArco(String idVerticeOrigen, String idVerticeDestino, U arco) throws ElementoNoExisteException, ElementoExisteException, CriterioOrdenamientoInvalidoException;
	/**
	 * Recupera el objeto almacenado en el vertice identificado con el id pasado como parámetro
	 * @param idVertice El id del vertice
	 * @return El objeto almacenado en el vertice, null en caso de no encontrar ningún vertice con dicho identificador
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador del vertice no genere un criterio
	 * de ordenamiento válido para recuperar el vértice del índice.
	 */
	public T darVertice(String idVertice) throws CriterioOrdenamientoInvalidoException;
	/**
	 * Recupera el objeto almacenado en el arco identificado con el id pasado como parámetro
	 * @param idArco El id del arco
	 * @return El objeto almacenado en el arco, null en caso de no encontrar ningún arco con dicho identificador
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador del arco no genere un criterio de 
	 * ordenamiento válido para recuperar el arco del índice. 
	 */
	public U darArco(String idArco) throws CriterioOrdenamientoInvalidoException;
	/**
	 * Recupera todos los arcos almacenados en el grafo
	 * @return Un lista encadenada que contiene todos los arcos almacenados en el grafo
	 */
	public IListaEncadenada<U> darArcos();
	/**
	 * Recupera todos los vertices almacenados en el grafo
	 * @return Un lista encadenada que contiene todos los vertices almacenados en el grafo
	 */
	public IListaEncadenada<T> darVertices();
	/**
	 * El número total de arcos en el grafo
	 * @return Entero que representa el número de arcos del grafo
	 */
	public int darNumeroArcos();
	/**
	 * El número total de vertices en el grafo
	 * @return Entero que representa el número de vertices del grafo
	 */
	public int darNumeroVertices();
	/**
	 * Entrega la ruta mínima entre dos vertices calculada utilizando el algoritmo de Dijkstra
	 * @param idVerticeOrigen: El id del vértice de origen
	 * @param idVerticeDestino: El id del vértice de destino
	 * @return Un arreglo de duplas que contienen en la primera entrada el vertice y la segunda el arco
	 * a seguir en dicho vértice, null en caso de no haber ningún camino.
	 * @throws ElementoNoExisteException En caso de no encontrarse registrado en el grafo alguno de los
	 * vertices de origen o destino.
	 * @throws CriterioOrdenamientoInvalidoException En caso que los identificadores de los vértices no generen un criterio
	 * de ordenamiento válido para recuperar los vértices del índice
	 */
	public IListaEncadenada<Dupla<T, U>> rutaMinima(String idVerticeOrigen, String idVerticeDestino) throws ElementoNoExisteException, CriterioOrdenamientoInvalidoException;
	/**
	 * Elimina todas las rutas mínimias calculadas hasta el momento. Este método debe llamarse si se realizaron modificaciones
	 * estructurales en el grafo y se quiere forzar el recalculo de las rutas, considerando las rutas recién agregadas.
	 * Este método no tiene ningún efecto si se utiliza el patrón observador para el algoritmo de Dijkstra, pues en este modo
	 * las rutas mínimas no son almacenadas.
	 */
	public void recalcularMinimos();
	/**
	 * El costo mínimo de viajar del vértice de origen al vértice de destino
	 * @param idVerticeOrigen: El identificador del vértice de origen
	 * @param idVerticeDestino: El identificador del vértice de destino
	 * @return long que representa el costo mínimo de viajar del vértice de origen al vértice de destino,
	 * -1 en caso de no existir ninguna ruta.
	 * @throws ElementoNoExisteException En caso de no encontrarse registrado en el grafo alguno de los
	 * vertices de origen o destino.
	 * @throws CriterioOrdenamientoInvalidoException En caso que los identificadores de los vértices no generen un criterio
	 * de ordenamiento válido para recuperar los vértices del índice
	 */
	public long costoMinimo(String idVerticeOrigen, String idVerticeDestino) throws ElementoNoExisteException, CriterioOrdenamientoInvalidoException;
	/**
	 * Entrega los vértices que son fuentes del grafo. Las fuentes son aquellos vértices que no tienen ningún predecesor.
	 * @return Una lista que contiene los vértices que son fuentes del grafo.
	 */
	public IListaEncadenada<T> darFuentes();
	/**
	 * Entrega todos los vértices que NO son fuentes del grafo. Es decir aquellos vértices que tienen al menos un predecesor.
	 * @return Una lista que contiene los vértices que no son fuentes del grafo.
	 */
	public IListaEncadenada<T> darNoFuentes();
	/**
	 * Entrega los vértices que son sumideros del grafo. Los sumideros son aquellos vértices que no tienen ningún sucesor.
	 * @return Una lista que contiene todos los vértices que son sumideros del grafo.
	 */
	public IListaEncadenada<T> darSumideros();
	/**
	 * Entrega los vértices que NO son sumideros del grafo. Es decir aquellos vértices que tienen al menos un sucesor.
	 * @return Una lista que contiene todos los vértices que no son sumideros del grafo.
	 */
	public IListaEncadenada<T> darNoSumideros();
	
	public IListaEncadenada<T> darCaminoMasLargo(String idVerticeOrigen) throws ElementoNoExisteException, CriterioOrdenamientoInvalidoException;
}
