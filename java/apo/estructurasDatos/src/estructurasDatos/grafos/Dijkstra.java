package estructurasDatos.grafos;

import java.util.Observable;
import java.util.Stack;

import estructurasDatos.Dupla;
import estructurasDatos.IIdentificable;
import estructurasDatos.IIdentificableComparable;
import estructurasDatos.arboles.IIndiceLexicograficoUnico;
import estructurasDatos.arboles.IndiceLexicograficoUnico;
import estructurasDatos.listas.IListaEncadenada;
import estructurasDatos.listas.ListaEncadenada;
import estructurasDatos.listas.ListaEncadenadaOrdenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;
/**
 * 
 * @author cvargasc
 *
 * @param <T> El tipo de los vertices
 * @param <U> El tipo de los arcos
 */
public class Dijkstra<T extends IIdentificable,U extends IIdentificableComparable<U>> extends Observable {
	// --------------------------------------------------------
	// Constantes
	// --------------------------------------------------------
	private final static boolean DEBUG = false;
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	/**
	 * El vértice de origen
	 */
	private IVertice<T,U> origen;
	/**
	 * Una contenedora para las triplas Dijkstra
	 */
	private IIndiceLexicograficoUnico<TriplaDijkstra<T,U>> destinos;
	/**
	 * Una lista ordenada para almacenar las triplas del frente de exploración
	 * La lista se ordena por el menor costo, luego el siguiente vertice a marcar
	 * siempre es la primera tripla de la lista. Una vez marcado el vertice
	 *  se debe remover la tripla de la lista. 
	 */
	private ListaEncadenadaOrdenada<TriplaDijkstra<T,U>> frenteExploracion;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	public Dijkstra(IVertice<T,U> origen) {
		this.origen = origen;
		// La triplaDijkstra para el vértice de origen
		TriplaDijkstra<T, U> triplaOrigen = new TriplaDijkstra<T, U>(origen, 0, origen, null);
		try{
			destinos = new IndiceLexicograficoUnico<TriplaDijkstra<T,U>>(triplaOrigen);
		} catch (CriterioOrdenamientoInvalidoException e) {
			// Nunca debería entrar aquí... Ya el Grafo garantizó que todos los candidatos tienen criterios 
			// de ordenamiento válidos!
			System.err.println("Dijkstra.Dijkstra().CriterioOrdenamientoInvalidoException: "+e.getMessage());
		}
		frenteExploracion = new ListaEncadenadaOrdenada<TriplaDijkstra<T,U>>(triplaOrigen);
		if (DEBUG) System.out.println("   El frente de exploración se ha inagurado con "+triplaOrigen);
//		calcularMinimos();
	}
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	/**
	 * Método encargado de calcular los caminos de costo mínimo a cada uno de los vértices
	 * desde el vertice de origen. Cada vez que alcanza un nuevo arco notifica a sus objervadores.
	 * Esta notificación contiene la dupla (Long costo, U a). Se espera que el observador
	 * parametrice el arco de ser necesario antes de solicitarle su costo mínimo y continuar con
	 * el proceso.
	 */
	public void calcularMinimos() {
		TriplaDijkstra<T,U> minimo = frenteExploracion.darPrimerElemento();
		while (minimo != null) {
			IListaEncadenada<IArco<T,U>> sucesores = minimo.darArcos();
			long costo = minimo.darCosto();
			for(IArco<T,U> a : sucesores) {
				if (DEBUG) System.out.println(a);
				// Notificando a los observadores acerca del arco que voy a procesar
				this.setChanged(); this.notifyObservers(new Dupla<Long,U>(costo, a.darObjeto()));
				// Finalizada la notificación
				TriplaDijkstra<T, U> candidata = new TriplaDijkstra<T,U>(a.darDestino(), costo + a.darCosto(), minimo.getDestino(),a);
				if (DEBUG) System.out.println("Candidata: "+candidata);
				try {
					destinos.agregarObjeto(candidata);
					// Si llegué aquí es porque no había otro camino hasta el vertice que acabo de encontrar, luego
					// debo agregar esta tripla al frente de exploración.
					if (DEBUG) System.out.println("   No había otro camino para "+candidata.getDestino());
					frenteExploracion.agregar(candidata);
					if (DEBUG) System.out.println("   Ahora el frente de exploración tiene "+frenteExploracion.darNumeroObjetos()+" triplas");
				} catch (ElementoExisteException e) {
					// Si entré aquí es porque ya existe otro camino para el vértice que estoy agregando
					// Debo entonces comparar los dos caminos que tengo y quedarme con el menor
					if (DEBUG) System.out.println("   Existía otro camino para "+candidata.getDestino());
					TriplaDijkstra<T, U> actual = (TriplaDijkstra<T, U>) e.getElemento();
					if (DEBUG) System.out.println("   El camino anterior era "+actual);
					if (actual.compareTo(candidata) > 0) { 		// Si la tripla candidata es menor debo:
						if (DEBUG) System.out.println("   El nuevo camino es menor... reemplazando");
						frenteExploracion.eliminar(actual);		// Remover la tripla actual del frente de exploración 
						actual.setCosto(candidata.darCosto());	// Actualizar el costo,
						actual.setPasandoPor(candidata.getPasandoPor()); // el vértice de la tripa actual
						actual.setArco(candidata.getArco());	// y el arco de la tripla actual
						frenteExploracion.agregar(actual);		// y volverla a agregar al frente de exploración (para actualizar su posición en la lista --> su costo disminuyó)
						if (DEBUG) System.out.println("Actual: "+actual);
					}
				} catch (CriterioOrdenamientoInvalidoException e) {
					// Nunca debería entrar aquí... Ya el Grafo garantizó que todos los candidatos tienen criterios 
					// de ordenamiento válidos!
					System.err.println("Dijkstra.calcularMinimos().CriterioOrdenamientoInvalidoException: "+e.getMessage());
				}
			}
			frenteExploracion.eliminar(minimo);
			minimo = frenteExploracion.darPrimerElemento();
		}
	}
	/**
	 * Entrega la ruta mínima para alcanzar el vértice pasado como parámetro
	 * @param idVerticeDestino: El identificador del vértice de destino
	 * @return Un arreglo con los vértices que se deben recorrer para alcanzar el vértice de destino,
	 * null en caso de no existir dicha ruta.
	 */
	public IListaEncadenada<Dupla<T,U>> darRutaMinima(String idVerticeDestino) {
		try {
			TriplaDijkstra<T,U> td = destinos.recuperarObjeto(idVerticeDestino);
			if (td == null) {
				System.err.println("Dijkstra.darRutaMinima(): No existe un camino entre "+origen.getId()
						+" y "+idVerticeDestino);
				return null;
			}
			
			Stack<TriplaDijkstra<T,U>> s = new Stack<TriplaDijkstra<T,U>>();
			while (td.getDestino() != this.origen) {
				s.push(td);
				//System.out.println(td);
				td = destinos.recuperarObjeto(td.getPasandoPor().getId());
			}
			
			IListaEncadenada<Dupla<T,U>> respuesta = new ListaEncadenada<Dupla<T,U>>();
			while (!s.empty()) {
				TriplaDijkstra<T,U> tripla = s.pop();
				respuesta.agregar(new Dupla<T, U>(tripla.getPasandoPor().darObjeto(),tripla.getArco().darObjeto()));
			}
			return respuesta;
		} catch (CriterioOrdenamientoInvalidoException e) {
			// Nunca debería entrar aquí... Ya el Grafo garantizó que todos los candidatos tienen criterios 
			// de ordenamiento válidos!
			System.err.println("Dijkstra.darRutaMinima().CriterioOrdenamientoInvalidoException: "+e.getMessage());
			return null;
		}
	}
	
	public long darCostoMinimo(String idVerticeDestino) {
		try {
			return destinos.recuperarObjeto(idVerticeDestino).darCosto();
		} catch (CriterioOrdenamientoInvalidoException e) {
			// Nunca debería entrar aquí... Ya el Grafo garantizó que todos los candidatos tienen criterios 
			// de ordenamiento válidos!
			System.err.println("Dijkstra.darRutaMinima().CriterioOrdenamientoInvalidoException: "+e.getMessage());
			return -1;
		}
	}
}
