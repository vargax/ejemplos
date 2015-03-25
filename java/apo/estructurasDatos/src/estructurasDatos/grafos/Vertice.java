package estructurasDatos.grafos;

import java.util.Observer;
import java.util.Stack;

import estructurasDatos.Dupla;
import estructurasDatos.IIdentificable;
import estructurasDatos.IIdentificableComparable;
import estructurasDatos.arboles.IIndiceLexicograficoUnico;
import estructurasDatos.arboles.IndiceLexicograficoUnico;
import estructurasDatos.listas.IListaEncadenada;
import estructurasDatos.listas.ListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;

public class Vertice<T extends IIdentificable, U extends IIdentificableComparable<U>> implements IVertice<T,U> {
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	/**
	 * El objeto almacenado en la estructura
	 */
	private T objetoAlmacenado;
	/**
	 * El conjunto de los arcos predecesores
	 */
	private IIndiceLexicograficoUnico<IArco<T,U>> predecesores;
	/**
	 * El conjunto de los arcos sucesores
	 */
	private IIndiceLexicograficoUnico<IArco<T,U>> sucesores;
	/**
	 * Una referencia al observador del grafo
	 */
	private Observer observador;
	/**
	 * Los caminos de mínimo costo a todos los vértices accesibles desde este vertice
	 */
	private Dijkstra<T, U> dijkstra;
	/**
	 * Una lista para almacenar el recorrido más largo
	 */
	private IListaEncadenada<IVertice<T,U>> caminosMasLargos;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	/**
	 * Crea un nuevo vertice
	 * @param objetoAlmacenar El objeto a almacenar en el vertice
	 */
	public Vertice(T objetoAlmacenar, Observer observador) {
		this.objetoAlmacenado = objetoAlmacenar;
		this.predecesores = new IndiceLexicograficoUnico<IArco<T,U>>();
		this.sucesores = new IndiceLexicograficoUnico<IArco<T,U>>();
		this.observador = observador;
		this.dijkstra = null;
	}
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	protected void inicializarDijkstra() {
		if (observador != null) {
			dijkstra= new Dijkstra<T,U>(this);
			dijkstra.addObserver(observador);
			dijkstra.calcularMinimos();
		} else if (dijkstra == null) {
			dijkstra= new Dijkstra<T,U>(this);
			dijkstra.calcularMinimos();
		}
	}
	
	@Override
	public String toString() {
		return this.objetoAlmacenado+"";
	}
	// --------------------------------------------------------
	// Métodos Interfaz
	// --------------------------------------------------------
	@Override
	public String getId() {
		return objetoAlmacenado.getId();
	}
	@Override
	public void agregarSucesor(IArco<T, U> nuevoSucesor) throws ElementoExisteException, CriterioOrdenamientoInvalidoException {
		try {
			sucesores.agregarObjeto(nuevoSucesor);
		} catch (ElementoExisteException e) {
			throw new ElementoExisteException("Ya hay un arco sucesor con id "+nuevoSucesor.getId()+" asociado a este vertice",e.getElemento());
		}
	}
	@Override
	public void agregarPredecesor(IArco<T, U> nuevoPredecesor) throws ElementoExisteException, CriterioOrdenamientoInvalidoException {
		try {
			predecesores.agregarObjeto(nuevoPredecesor);
		} catch (ElementoExisteException e) {
			throw new ElementoExisteException("Ya hay un arco predecesor con id "+nuevoPredecesor.getId()+" asociado a este vertice",e.getElemento());
		}
	}
	@Override
	public IListaEncadenada<IArco<T, U>> darSucesores() {
		return sucesores.darObjetos();
	}
	@Override
	public T darObjeto() {
		return objetoAlmacenado;
	}
	@Override
	public IListaEncadenada<Dupla<T, U>> darRutaMinimoCosto(String idVerticeDestino) {
		inicializarDijkstra();
		return dijkstra.darRutaMinima(idVerticeDestino);
	}
	@Override
	public long darMinimoCosto(String idVerticeDestino) {
		inicializarDijkstra();
		return dijkstra.darCostoMinimo(idVerticeDestino);
	}
	@Override
	public void resetDikjstra() {
		dijkstra = null;
	}
	@Override
	public boolean esFuente() {
		return predecesores.darNumeroElementos() == 0;
	}
	@Override
	public boolean esSumidero() {
		return sucesores.darNumeroElementos() == 0;
	}
	@Override
	public void calcularCircuitoMasLargo(IListaEncadenada<IVertice<T,U>> circuitoActual) {
		if (this.caminosMasLargos == null) {
			this.caminosMasLargos = circuitoActual == null ? new ListaEncadenada<IVertice<T,U>>() : circuitoActual.clone();
			this.caminosMasLargos.agregar(this);
			
			System.out.println("viene una pila de "+this.caminosMasLargos.darNumeroObjetos());
			for (IVertice<T, U> v : this.caminosMasLargos) System.out.println("   "+v.darObjeto());
			
			for(IArco<T,U> a : this.sucesores.darObjetos()) {
				IVertice<T,U> destino = a.darDestino();
				if (!this.caminosMasLargos.buscar(destino)) {
					System.out.println("aún no está en la pila...");
					destino.calcularCircuitoMasLargo(this.caminosMasLargos);
				}
			}
		} else if (this.caminosMasLargos.darNumeroObjetos() < circuitoActual.darNumeroObjetos()) {
			this.caminosMasLargos = circuitoActual.clone();
			this.caminosMasLargos.agregar(this);
			System.out.println("Ha llegado una pila mayor: "+this.caminosMasLargos.darNumeroObjetos());
			for (IVertice<T, U> v : this.caminosMasLargos) System.out.println("   "+v.darObjeto());
		} else {
			System.out.println("me quedo con mi pila actual");
		}
	}
	@Override
	public IListaEncadenada<IVertice<T, U>> darCircutoMasLargo() {
		IListaEncadenada<IVertice<T, U>> resp = this.caminosMasLargos;
		this.caminosMasLargos = null;
		return resp;
	}
}

