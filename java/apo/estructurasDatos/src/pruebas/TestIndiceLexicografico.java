package pruebas;

import estructurasDatos.arboles.IIndiceLexicografico;
import estructurasDatos.arboles.IndiceLexicografico;
import estructurasDatos.listas.IListaEncadenada;
import excepciones.CriterioOrdenamientoInvalidoException;
import junit.framework.TestCase;

public class TestIndiceLexicografico extends TestCase {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private IIndiceLexicografico<String> indice;
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Construye un nuevo índice
	 */
	public void setupEscenario1() {
		indice = new IndiceLexicografico<String>();
	}
	/**
	 * Construye un nuevo índice con algunos elementos
	 * @throws CriterioOrdenamientoInvalidoException 
	 */
	public void setupEscenario2() throws CriterioOrdenamientoInvalidoException {
		indice = new IndiceLexicografico<String>();
		
		String hundred = "Hartismere was a hundred of Suffolk, " +
				"that later gave its name to a poor law union, a rural sanitary district, and the Hartismere " +
				"Rural District. Listed as Hertesmere in the Domesday Book, the name of the hundred is " +
				"derived from Hart's mere where Hart is a personal name.[1] Hartismere also gives its name " +
				"to the 11-19 Co-educational Foundation School based in Eye. It serves pupils aged 11–16 " +
				"years whilst the associated sixth form college instructs 16-19 year students. " +
				"The School is distinctive in having particularly close links to the Hartismere " +
				"Community. It is also the name of a hospital and maternity unit at Eye.";
		indice.agregarObjeto("Hartismere", hundred);
		indice.agregarObjeto("hundred", hundred);
		
		String leifLampater = "Leif Lampater (born December 22, 1982 in Waiblingen) is a German racing cyclist. " +
				"He mainly focuses on track cycling and excels in the madison, individual pursuit, team pursuit and " +
				"six-day racing. In his career so far he has become German national team pursuit and madison champion" +
				" once each, he has won Track cycling World Cups in team pursuit twice and twice in madison. On top of " +
				"that he has won a total of six six-day cycling events.";
		indice.agregarObjeto("Leif", leifLampater);
		indice.agregarObjeto("Lampater", leifLampater);
		
		String kazimierzZdziechowski = "Kazimierz Zdziechowski, also known under pseudonyms Władysław Zdora, Władysław " +
				"Mouner, (1878–1942) was a Polish landowner, prose writer, publicist, literary critic and novelist. Brother" +
				" of Marian Zdziechowski, Polish philologist and philosopher." +
				"Zdziechowski was born on March 14, 1878 in Raków, Belarus. He graduated law in university of Moscow. " +
				"Then, he managed family estate in Raków. Since 1896 he collaborated with press as a prose writer, publicist" +
				" and literary critic. Zdziechowski was murdered on August 4, 1942 during the Second World War in the German " +
				"concentration camp Auschwitz.";
		indice.agregarObjeto("kazimierz", kazimierzZdziechowski);
		indice.agregarObjeto("Zdziechowski", kazimierzZdziechowski);
		indice.agregarObjeto("Władysław", kazimierzZdziechowski);
		indice.agregarObjeto("Zdora", kazimierzZdziechowski);
		
		String treeStructure = "A tree structure is a way of representing the hierarchical nature of a structure in a graphical" +
				" form. It is named a tree structure because the classic representation resembles a tree, even though the chart " +
				"is generally upside down compared to an actual tree, with the root at the top and the leaves at the bottom.";
		indice.agregarObjeto("tree", treeStructure);
		indice.agregarObjeto("structure", treeStructure);
		
		String treeDiagram = "The term tree diagram refers to a specific type of diagram that has a unique network topology. " +
				"It can be seen as a specific type of network diagram, which in turn can be seen as a special kind of cluster diagram.";
		indice.agregarObjeto("tree", treeDiagram);
		indice.agregarObjeto("Diagram", treeDiagram);
		
		String treeDataStructure = "In computer science, a tree is a widely-used data structure that emulates a hierarchical " +
				"tree structure with a set of linked nodes. Mathematically, it is an ordered directed tree, more specifically an arborescence: " +
				"an acyclic connected graph where each node has zero or more children nodes and at most one parent node. Furthermore, " +
				"the children of each node have a specific order.";
		indice.agregarObjeto("tree", treeDataStructure);
		indice.agregarObjeto("data", treeDataStructure);
		indice.agregarObjeto("structure", treeDataStructure);
	}
	/**
	 * Prueba el funcionamiento del contador del índice
	 */
	public void testContador() {
		setupEscenario1();
		int contador = 0;
		for (int i = 0; i < 1000; i++) {
			try {
				indice.agregarObjeto(""+i, ""+i);
			} catch (CriterioOrdenamientoInvalidoException e) {
				System.err.println(e.getMessage());
				fail("Todos los criterios de ordenamiento son válidos");
			}
			contador++;
			assertEquals(contador, indice.darNumeroElementos());
		}
	}
	/**
	 * Realiza algunas pruebas sobre el índice con pocas palabras
	 */
	public void testAlgunasPalabras() {
		setupEscenario1();
		try {
			// Criterios de ordenamiento extraños
			indice.agregarObjeto("%$&/ casas en la esquina /&%", "Casas en la esquina");
			indice.agregarObjeto("%$&/armando cas!|as barr/$era", "armando casas barrera");
			// Múltiples entradas con el mismo criterio de ordenamiento
			indice.agregarObjeto("a","una a");
			indice.agregarObjeto("a","otra a");
			indice.agregarObjeto("a","otra a más");
			indice.agregarObjeto("a","la última a");
			indice.agregarObjeto("b","una b");
	
			IListaEncadenada<String> resultado = indice.buscarObjeto("a");
			assertEquals(4, resultado.darNumeroObjetos());
	
			resultado = indice.buscarObjeto("b");
			assertEquals(1, resultado.darNumeroObjetos());
			
			resultado = indice.buscarObjeto("c");
			assertEquals(0, resultado.darNumeroObjetos());
			
			assertEquals(7, indice.darNumeroElementos());
			
		} catch (CriterioOrdenamientoInvalidoException e) {
			System.err.println(e.getMessage());
			fail("Todos los criterios de ordenamiento son válidos");
		}
	}
	/**
	 * Agrega muchas palabras al índice, luego realiza busquedas sobre ellas
	 */
	public void testBusquedaPalabras() {
		setupEscenario1();
		int contador = 0;
		try {
			for (char i = 'b'; i <= 'z'; i++)
				for (char j = 'b'; j <= 'z'; j++)
					for (char k = 'b'; k <= 'z'; k++){
						String s = ""+i+j+k;
						indice.agregarObjeto(s, s);
						contador++;
					}				
			
			//Verificando el número de objetos en la contenedora...
			assertEquals(contador, indice.darNumeroElementos());
			
			assertEquals(0, indice.buscarObjeto("a").darNumeroObjetos());
			
			assertEquals(25, indice.buscarObjeto("br").darNumeroObjetos());
			
			assertEquals(625, indice.buscarObjeto("c").darNumeroObjetos());
			assertEquals(25, indice.buscarObjeto("cr").darNumeroObjetos());
			assertEquals(1, indice.buscarObjeto("crb").darNumeroObjetos());
		} catch (CriterioOrdenamientoInvalidoException e) {
			System.err.println(e.getMessage());
			fail("Todos los criterios de ordenamiento son válidos");
		}
		//assertEquals(15625, indice.buscarObjeto("").darNumeroObjetos());
	}
	/**
	 * Test debug busqueda
	 */
	public void testBusquedaPrimerasPalabras() {
		setupEscenario1();
		try {
			int contador = 0;
				for (char i = 'a'; i <= 'z'; i++)
					for (char j = 'a'; j <= 'z'; j++)
						for (char k = 'a'; k <= 'z'; k++){
							String s = ""+i+j+k;
							indice.agregarObjeto(s, s);
							contador++;
						}
				
			assertEquals(676, indice.buscarObjeto("a").darNumeroObjetos());
			assertEquals(26, indice.buscarObjeto("aa").darNumeroObjetos());
			assertEquals(contador, indice.darNumeroElementos());
		} catch (CriterioOrdenamientoInvalidoException e) {
			System.err.println(e.getMessage());
			fail("Todos los criterios de ordenamiento son válidos");
		}
	}
	/**
	 * Agrega muchas palabras sobre una misma rama (debe probar rehash())
	 */
	public void testRama() {
		setupEscenario1();
		try {
			for (int i = 0; i <= 1000; i++)
				for (char j = 'a'; j <= 'z'; j++)
					for (char k = 'a'; k <= 'z'; k++)
						indice.agregarObjeto(k+""+i+j, k+""+i+j);
			
			//assertEquals(676676, indice.buscarObjeto("").darNumeroObjetos());
			assertEquals(676676, indice.darNumeroElementos());
		} catch (CriterioOrdenamientoInvalidoException e) {
			System.err.println(e.getMessage());
			fail("Todos los criterios de ordenamiento son válidos");
		}
	}
	/**
	 * Recupera objetos del índice a partir de sus criterios de ordenamiento
	 */
	public void testBusqueda() {
		try {
			setupEscenario2();
			IListaEncadenada<String> resultados = indice.buscarObjeto("Hartismere");
			assertEquals(1, resultados.darNumeroObjetos());
			
			resultados = indice.buscarObjeto("hundred");
			assertEquals(1, resultados.darNumeroObjetos());
			
			resultados = indice.buscarObjeto("tree");
			assertEquals(3, resultados.darNumeroObjetos());
			
			resultados = indice.darObjetos();
			assertEquals(15, resultados.darNumeroObjetos());
		} catch (CriterioOrdenamientoInvalidoException e) {
			System.err.println(e.getMessage());
			fail("Todos los criterios de ordenamiento son válidos");
		}
	}
}
