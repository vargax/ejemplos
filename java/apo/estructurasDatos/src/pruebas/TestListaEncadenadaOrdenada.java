package pruebas;

import estructurasDatos.listas.ListaEncadenadaOrdenada;
import junit.framework.TestCase;

public class TestListaEncadenadaOrdenada extends TestCase {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private ListaEncadenadaOrdenada<Integer> lista;
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Construye una nueva lista
	 */
	public void setupEscenario1() {
		lista = new ListaEncadenadaOrdenada<Integer>();
	}
	/**
	 * Agrega algunos elementos desordenados
	 */
	public void testAgregarElementos() {
		setupEscenario1();
		lista.agregar(0);
		lista.agregar(-1);
		lista.agregar(1);
		lista.agregar(-2);
		lista.agregar(3);
		lista.agregar(-3);
		lista.agregar(2);
		lista.agregar(4);
		
		int j = -3;
		for (Integer i : lista) {
			assertTrue(i == j);
			j++;
		}
	}
}
