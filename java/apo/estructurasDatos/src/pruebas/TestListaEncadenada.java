package pruebas;

import estructurasDatos.listas.ListaEncadenada;
import junit.framework.TestCase;

public class TestListaEncadenada extends TestCase {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private ListaEncadenada<String> lista;
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Construye una nueva lista vacia
	 */
	public void setupEscenario1() {
		lista = new ListaEncadenada<String>();
	}
	
	/**
	 * Agrega y elimina un solo elemento
	 */
	public void testUnicoElemento() {
		setupEscenario1();
		String s1 = "s1";
		lista.agregar(s1);
		System.out.println("Tras agregar:");
		for (String i : lista) System.out.println(i);
		assertTrue(lista.darNumeroObjetos() == 1);
		lista.eliminar(s1);
		System.out.println("Tras eliminar:");
		for (String i : lista) System.out.println(i);
		assertTrue(lista.darNumeroObjetos() == 0);
	}
	public void testCabeza() {
		setupEscenario1();
		String s1 = "s1";
		String s2 = "s2";
		lista.agregar(s1);
		lista.agregar(s2);
		System.out.println("Tras agregar:");
		for (String i : lista) System.out.println(i);
		assertTrue(lista.darNumeroObjetos() == 2);
		lista.eliminar(s1);
		System.out.println("Tras eliminar:");
		for (String i : lista) System.out.println(i);
		assertTrue(lista.darNumeroObjetos() == 1);
	}
	public void testCola() {
		setupEscenario1();
		String s1 = "s1";
		String s2 = "s2";
		lista.agregar(s1);
		lista.agregar(s2);
		System.out.println("Tras agregar:");
		for (String i : lista) System.out.println(i);
		assertTrue(lista.darNumeroObjetos() == 2);
		lista.eliminar(s2);
		System.out.println("Tras eliminar:");
		for (String i : lista) System.out.println(i);
		assertTrue(lista.darNumeroObjetos() == 1);
	}
	public void testGeneral() {
		setupEscenario1();
		String s1 = "s1";
		String s2 = "s2";
		String s3 = "s3";
		lista.agregar(s1);
		lista.agregar(s2);
		lista.agregar(s3);
		System.out.println("Tras agregar:");
		for (String i : lista) System.out.println(i);
		assertTrue(lista.darNumeroObjetos() == 3);
		lista.eliminar(s2);
		System.out.println("Tras eliminar:");
		for (String i : lista) System.out.println(i);
		assertTrue(lista.darNumeroObjetos() == 2);
	}
}
