package estructurasDatos.grafos;

import estructurasDatos.IIdentificable;
import estructurasDatos.IIdentificableComparable;
import estructurasDatos.listas.IListaEncadenada;
/**
 * Clase para modelar una tripla Dijkstra. Almacena el vértice de destino, el costo mínimo de llegar a dicho vértice
 * y el vértice por el cual debo pasar para alcanzarlo.
 * @author cvargasc 
 * @param <T> El tipo de los vertices
 * @param <U> El tipo de los arcos
 */
public class TriplaDijkstra<T extends IIdentificable, U extends IIdentificableComparable<U>> implements IIdentificableComparable<TriplaDijkstra<T, U>> {
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	/**
	 * El vértice de destino 
	 */
	private IVertice<T,U> destino;
	/**
	 * El costo de llegar a dicho vertice
	 */
	private long costo;
	/**
	 * El vértice por el cual debo pasar para llegar a this.destino con this.costo
	 */
	private IVertice<T,U> pasandoPor;
	/**
	 * El arco que utilizo para llegar al vertice
	 */
	private IArco<T,U> arco;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	/**
	 * Crea una nueva triplaDijkstra
	 * @param destino: El vértice de destino
	 * @param costo: El costo total hasta dicho vertice
	 * @param pasandoPor: El antecesor del vértice de destino que genera el costo de esta tripla
	 * @param arco: El arco utilizado para llegar hasta this.destino
	 */
	public TriplaDijkstra(IVertice<T,U> destino, long costo, IVertice<T,U> pasandoPor, IArco<T,U> arco) {
		this.destino = destino;
		this.costo = costo;
		this.pasandoPor = pasandoPor;
		this.arco = arco;
	}
	
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	public IListaEncadenada<IArco<T,U>> darArcos() {
		return destino.darSucesores();
	}
	@Override
	public String toString() {
		return "("+this.pasandoPor.getId()+","+this.costo+","+this.destino.getId()+")";
	}
	// --------------------------------------------------------
	// Métodos Interfaz
	// --------------------------------------------------------
	@Override
	public String getId() {
		return destino.getId();
	}

	@Override
	public int compareTo(TriplaDijkstra<T, U> o) {
		return (int) (this.costo - o.darCosto());
	}
	
	@Override
	public long darCosto() {
		return this.costo;
	}
	// --------------------------------------------------------
	// Getters/Setters
	// --------------------------------------------------------

	public void setCosto(long costo) {
		this.costo = costo;
	}

	public IVertice<T, U> getPasandoPor() {
		return pasandoPor;
	}

	public void setPasandoPor(IVertice<T, U> pasandoPor) {
		this.pasandoPor = pasandoPor;
	}

	public IVertice<T, U> getDestino() {
		return this.destino;
	}
	public IArco<T, U> getArco() {
		return arco;
	}
	public void setArco(IArco<T, U> arco) {
		this.arco = arco;
	}
}
