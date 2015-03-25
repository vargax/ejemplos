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
import excepciones.ElementoNoExisteException;

public class Grafo<T extends IIdentificable, U extends IIdentificableComparable<U>>	implements IGrafo<T, U> {
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	/**
	 * Contenedora para almacenar los vertices del grafo
	 */
	private IIndiceLexicograficoUnico<IVertice<T,U>> vertices;
	/**
	 * Contenedora para almacenar los arcos del grafo
	 */
	private IIndiceLexicograficoUnico<IArco<T,U>> arcos;
	/**
	 * El observador del grafo
	 */
	private Observer observador;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	/**
	 * Crea un nuevo grafo vacio.
	 * La búsqueda de rutas mínimas se realiza utilizando el algoritmo de Dijkstra,
	 * la clase encargada de realizar estas búsquedas soporta el patrón Observable-Observador,
	 * por medio del cual notifica a sus observadores antes de visitar cada arco. Lo anterior
	 * permite realizar alguna parametrización del arco antes que el algoritmo solicite su costo.
	 * Al definir un observador el grafo asume que los costos de los arcos no son constantes, por
	 * lo cual recalculará la ruta cada vez que se solicite un mínimo. 
	 * @param observador: Una referencia al observador de Dijkstra. 
	 */
	public Grafo(Observer observador) {
		vertices = new IndiceLexicograficoUnico<IVertice<T,U>>();
		arcos = new IndiceLexicograficoUnico<IArco<T,U>>();
		this.observador = observador;
	}
	/**
	 * Crea un nuevo grafo vacío.
	 * La búsqueda de rutas mínimas se realiza utilizando el algoritmo de Dijkstra. El cálculo se
	 * realiza bajo demanda cuando se solicita por primera vez una ruta desde un vértice determinado.
	 * Este constructor asume que los costos de los arcos son constantes, por lo cual las rutas
	 * mínimas desde cada vértice una vez calculadas se almacenan. Si se realizan modificaciones en el
	 * grafo y se requiere recalcular las rutas mínimas se debe llamar el método resetDikjstra() antes
	 * de solicitar una nueva ruta mínima.
	 */
	public Grafo() {
		vertices = new IndiceLexicograficoUnico<IVertice<T,U>>();
		arcos = new IndiceLexicograficoUnico<IArco<T,U>>();
		observador = null;
	}
	// --------------------------------------------------------
	// Métodos Interfaz
	// --------------------------------------------------------
	@Override
	public void agregarVertice(T nuevoVertice) throws ElementoExisteException, CriterioOrdenamientoInvalidoException {
		vertices.agregarObjeto(new Vertice<T, U>(nuevoVertice,this.observador));
	}
	@Override
	public void agregarArco(String idVerticeOrigen, String idVerticeDestino, U arco) throws ElementoExisteException, ElementoNoExisteException, CriterioOrdenamientoInvalidoException {
		IVertice<T,U> origen = vertices.recuperarObjeto(idVerticeOrigen);
		if (origen == null) 	throw new ElementoNoExisteException("El vertice de origen (id "+idVerticeOrigen+ ") no se encuentra registrado en el grafo",idVerticeOrigen);
		
		IVertice<T,U> destino = vertices.recuperarObjeto(idVerticeDestino);
		if (destino == null) 	throw new ElementoNoExisteException("El vertice de destino (id "+idVerticeDestino+ ") no se encuentra registrado en el grafo",idVerticeDestino);
		
		IArco<T, U> nuevoArco = new Arco<T,U>(arco, origen, destino);
		arcos.agregarObjeto(nuevoArco);
		origen.agregarSucesor(nuevoArco);
		destino.agregarPredecesor(nuevoArco);
	}
	
	@Override
	public T darVertice(String idVertice) throws CriterioOrdenamientoInvalidoException {
		IVertice<T, U> vertice = vertices.recuperarObjeto(idVertice);
		if(vertice != null) return vertice.darObjeto();
		return null;
	}
	
	@Override
	public U darArco(String idArco) throws CriterioOrdenamientoInvalidoException {
		IArco<T, U> arco = arcos.recuperarObjeto(idArco); 
		if (arco != null) return arco.darObjeto();
		return null;
	}
	
	@Override
	public IListaEncadenada<U> darArcos() {
		IListaEncadenada<U> respuesta = new ListaEncadenada<U>();
		for(IArco<T,U> a : arcos.darObjetos()) respuesta.agregar(a.darObjeto());
		return respuesta;
	}
	
	@Override
	public IListaEncadenada<T> darVertices() {
		IListaEncadenada<T> respuesta = new ListaEncadenada<T>();
		for(IVertice<T,U> v : vertices.darObjetos()) respuesta.agregar(v.darObjeto());
		return respuesta;
	}
	
	@Override
	public int darNumeroArcos() {
		return arcos.darNumeroElementos();
	}
	
	@Override
	public int darNumeroVertices() {
		return vertices.darNumeroElementos();
	}
	@Override
	public IListaEncadenada<Dupla<T, U>> rutaMinima(String idVerticeOrigen, String idVerticeDestino) throws ElementoNoExisteException, CriterioOrdenamientoInvalidoException {
		IVertice<T,U> origen = vertices.recuperarObjeto(idVerticeOrigen);
		IVertice<T,U> destino = vertices.recuperarObjeto(idVerticeDestino);
		
		if (origen == null) throw new ElementoNoExisteException("El vertice "+idVerticeOrigen+" no se encuentra registrado en el grafo", idVerticeOrigen);
		if (destino == null) throw new ElementoNoExisteException("El vertice "+idVerticeDestino+" no se encuentra registrado en el grafo", idVerticeDestino);
		
		return origen.darRutaMinimoCosto(idVerticeDestino);
	}
	@Override
	public long costoMinimo(String idVerticeOrigen, String idVerticeDestino) throws ElementoNoExisteException, CriterioOrdenamientoInvalidoException {
		IVertice<T,U> origen = vertices.recuperarObjeto(idVerticeOrigen);
		IVertice<T,U> destino = vertices.recuperarObjeto(idVerticeDestino);
		
		if (origen == null) throw new ElementoNoExisteException("El vertice "+idVerticeOrigen+" no se encuentra registrado en el grafo", idVerticeOrigen);
		if (destino == null) throw new ElementoNoExisteException("El vertice "+idVerticeDestino+" no se encuentra registrado en el grafo", idVerticeDestino);
		
		return origen.darMinimoCosto(idVerticeDestino);
	}
	@Override
	public IListaEncadenada<T> darFuentes() {
		IListaEncadenada<T> respuesta = new ListaEncadenada<T>();
		for(IVertice<T,U> v : vertices.darObjetos()) {
			if (v.esFuente()) respuesta.agregar(v.darObjeto());
		}
		return respuesta;
	}
	@Override
	public IListaEncadenada<T> darNoFuentes() {
		IListaEncadenada<T> respuesta = new ListaEncadenada<T>();
		for(IVertice<T,U> v : vertices.darObjetos()) {
			if (!v.esFuente()) respuesta.agregar(v.darObjeto());
		}
		return respuesta;
	}
	@Override
	public IListaEncadenada<T> darSumideros() {
		IListaEncadenada<T> respuesta = new ListaEncadenada<T>();
		for(IVertice<T,U> v : vertices.darObjetos()) {
			if (v.esSumidero()) respuesta.agregar(v.darObjeto());
		}
		return respuesta;
	}
	@Override
	public IListaEncadenada<T> darNoSumideros() {
		IListaEncadenada<T> respuesta = new ListaEncadenada<T>();
		for(IVertice<T,U> v : vertices.darObjetos()) {
			if (!v.esSumidero()) respuesta.agregar(v.darObjeto());
		}
		return respuesta;
	}
	@Override
	public void recalcularMinimos() {
		for (IVertice<T,U> v : vertices.darObjetos()) v.resetDikjstra();
	}
	@Override
	public IListaEncadenada<T> darCaminoMasLargo(String idVerticeOrigen) throws ElementoNoExisteException, CriterioOrdenamientoInvalidoException {
		IVertice<T,U> origen = vertices.recuperarObjeto(idVerticeOrigen);
		if (origen == null) throw new ElementoNoExisteException("El vertice "+idVerticeOrigen+" no se encuentra registrado en el grafo", idVerticeOrigen);
		origen.calcularCircuitoMasLargo(null);
		IListaEncadenada<IVertice<T,U>> respuesta = new ListaEncadenada<IVertice<T,U>>();
		for(IVertice<T,U> v : vertices.darObjetos()) {
			if (v.darCircutoMasLargo() != null && respuesta.darNumeroObjetos() < v.darCircutoMasLargo().darNumeroObjetos()) 
				respuesta = v.darCircutoMasLargo();
		}
		IListaEncadenada<T> resp = new ListaEncadenada<T>();
		for(IVertice<T, U> v : respuesta) resp.agregar(v.darObjeto());
		return resp;
	}
}