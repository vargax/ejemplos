package estructurasDatos.grafos;

import java.util.Stack;

import estructurasDatos.Dupla;
import estructurasDatos.IIdentificable;
import estructurasDatos.IIdentificableComparable;
import estructurasDatos.listas.IListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;
/**
 * 
 * @author cvargasc
 *
 * @param <T> El tipo de los vertices
 * @param <U> El tipo de los arcos
 */
public interface IVertice<T extends IIdentificable, U extends IIdentificableComparable<U>> extends IIdentificable {
	/**
	 * Agrega un nuevo sucesor al vertice
	 * @throws ElementoExisteException: En caso que ya se encuentre un sucesor con dicho id asociado al vertice
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador del sucesor no genere un criterio de
	 * ordenamiento válido para almacenarlo en el índice.
	 */
	public void agregarSucesor(IArco<T,U> nuevoSucesor) throws ElementoExisteException, CriterioOrdenamientoInvalidoException;
	/**
	 * Agrega un nuevo predecesor al vertice
	 * @throws ElementoExisteException: En caso que ya se encuentre un predecesor con dicho id asociado al vertice
	 * @throws CriterioOrdenamientoInvalidoException En caso que el identificador del predecesor no genere un criterio de
	 * ordenamiento válido para almacenarlo en el índice.
	 */
	public void agregarPredecesor(IArco<T,U> nuevoPredecesor) throws ElementoExisteException, CriterioOrdenamientoInvalidoException;
	/**
	 * Elimina las rutas mínimas calculadas por el vértice, lo cual forzará su recálculo de ser necesario.
	 */
	public void resetDikjstra();
	/**
	 * Entrega una lista con todos los arcos sucesores del vertice
	 * @return Una lista con los arcos registrados como sucesores del vértice
	 */
	public IListaEncadenada<IArco<T,U>> darSucesores();
	/**
	 * Entrega el objeto almacenado en el vertice
	 * @return El objeto almacenado en el vertice.
	 */
	public T darObjeto();
	/**
	 * Devuelve la ruta de mínimo costo hasta el vertice de destino
	 * @param idVerticeDestino El id del vértice de destino
	 * @return Un arreglo de duplas que contienen los vértices y arcos que se deben tomar
	 * para llegar con mínimo costo al vértice de destino, null en caso de no existir ninguna ruta.
	 */
	public IListaEncadenada<Dupla<T,U>> darRutaMinimoCosto(String idVerticeDestino);
	/**
	 * Devuelve el costo mínimo de viajar hasta el vértice de destino
	 * @param idVerticeDestino El id del vértice de destino
	 * @return long que representa el costo de viajar del vértice actual al vértice pasado como 
	 * parámetro, -1 en caso de no existir ninguna ruta.
	 */
	public long darMinimoCosto(String idVerticeDestino);
	/**
	 * El vértice es fuente
	 * @return Verdadero si el vértice es fuente
	 */
	public boolean esFuente();
	/**
	 * El vértice es sumidero
	 * @return Verdadero si el vértice es sumidero
	 */
	public boolean esSumidero();
	
	void calcularCircuitoMasLargo(IListaEncadenada<IVertice<T, U>> circuitoActual);
	
	public IListaEncadenada<IVertice<T,U>> darCircutoMasLargo();
}
