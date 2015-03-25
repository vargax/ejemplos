package estructurasDatos.grafos;

import estructurasDatos.IIdentificable;
import estructurasDatos.IIdentificableComparable;

public class Arco<T extends IIdentificable, U extends IIdentificableComparable<U>> implements IArco<T,U> {
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	private U objetoAlmacenado;
	/**
	 * El vertice de origen del arco
	 */
	private IVertice<T, U> origen;
	/**
	 * El vertice de destino del arco
	 */
	private IVertice<T, U> destino;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	/**
	 * Construye un nuevo arco
	 * @param objetoAlmacenar: El objeto que contendrá el arco
	 * @param origen: El vertice de origen del arco
	 * @param destino: El vertice de destino del arco
	 */
	public Arco(U objetoAlmacenar, IVertice<T,U> origen, IVertice<T,U> destino) {
		this.objetoAlmacenado = objetoAlmacenar;
		this.origen = origen;
		this.destino = destino;
	}
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	@Override
	public String toString() {
		return this.origen+" ==> "+destino;
	}
	// --------------------------------------------------------
	// Métodos Interfaz
	// --------------------------------------------------------
	@Override
	public String getId() {
		return objetoAlmacenado.getId();
	}
	@Override
	public IVertice<T, U> darDestino() {
		return this.destino;
	}
	@Override
	public int compareTo(U u) {
		return objetoAlmacenado.compareTo(u);
	}
	@Override
	public U darObjeto() {
		return objetoAlmacenado;
	}
	@Override
	public long darCosto() {
		return objetoAlmacenado.darCosto();
	}
	
}
