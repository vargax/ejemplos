package pruebas;

import estructurasDatos.Dupla;
import estructurasDatos.grafos.Grafo;
import estructurasDatos.grafos.IGrafo;
import estructurasDatos.listas.IListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import excepciones.ElementoExisteException;
import excepciones.ElementoNoExisteException;
import junit.framework.TestCase;

public class TestGrafo extends TestCase {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private IGrafo<Identificable, IdentificableComparable> grafo;
	/**
	 * Contiene el número de pasos y el costo total de ir del vétice 1 a cada uno de los 7 vértices
	 */
	private int[][] pasosCosto = {{0,0},{1,10},{2,16},{3,19},{2,13},{5,26},{4,21}};
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Construye un nuevo grafo vacío
	 */
	public void setupEscenario1() {
		grafo = new Grafo<Identificable, IdentificableComparable>();
	}
	/**
	 * Construye un nuevo grafo con pocos vertices y pocos arcos
	 * (Construye el grafo del ejemplo 1 de Dijkstra
	 * http://cupi2.uniandes.edu.co/sitio/images/cursosCupi2/datos/presentaciones/n17_dijkstra.pdf
	 * @throws ElementoExisteException 
	 * @throws ElementoNoExisteException 
	 */
	public void setupEscenario2() throws ElementoExisteException, ElementoNoExisteException {
		grafo = new Grafo<Identificable, IdentificableComparable>();
		try {
			for (int i = 1; i <= 7; i++) grafo.agregarVertice(new Identificable(""+i));
			
			grafo.agregarArco("1", "2", new IdentificableComparable("1->2", 10));
			grafo.agregarArco("1", "3", new IdentificableComparable("1->3", 18));
			grafo.agregarArco("2", "3", new IdentificableComparable("2->3", 6));
			grafo.agregarArco("2", "5", new IdentificableComparable("2->5", 3));
			grafo.agregarArco("3", "4", new IdentificableComparable("3->4", 3));
			grafo.agregarArco("3", "6", new IdentificableComparable("3->6", 20));
			grafo.agregarArco("4", "3", new IdentificableComparable("4->3", 2));
			grafo.agregarArco("4", "7", new IdentificableComparable("4->7", 2));
			grafo.agregarArco("5", "4", new IdentificableComparable("5->4", 8));
			grafo.agregarArco("5", "7", new IdentificableComparable("5->7", 10));
			grafo.agregarArco("7", "6", new IdentificableComparable("7->6", 5));
		} catch (CriterioOrdenamientoInvalidoException e) {
			System.err.println(e.getMessage());
			fail("Todos los criterios de ordenamiento son válidos");
		}
	}
	
	public void testInicializarGrafo() {
		try {
			setupEscenario2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDijkstra() {
		try {
			setupEscenario2();
			for (int i = 1; i <= 7; i++) {
				System.out.println("Ruta mínima de 1 a "+i);
				IListaEncadenada<Dupla<Identificable, IdentificableComparable>> respuesta = grafo.rutaMinima("1", ""+i);
				for (Dupla<Identificable, IdentificableComparable> d : respuesta) {
					System.out.println("  En "+d.getIzq().getId()+" tomar "+d.getDer().getId()+" por un costo de "+d.getDer().darCosto());
				}
				assertEquals(pasosCosto[i-1][0], respuesta.darNumeroObjetos());
				assertEquals(pasosCosto[i-1][1], grafo.costoMinimo("1", ""+i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testCaminoMasLargo() {
		try {
			setupEscenario2();
			IListaEncadenada<Identificable> respuesta = grafo.darCaminoMasLargo(""+1);
			for(Identificable i : respuesta) System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

