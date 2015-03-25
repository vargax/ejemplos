package uniandes.cupi2.recetario.test;

import uniandes.cupi2.recetario.mundo.Receta;
import junit.framework.TestCase;

/**
 * Clase usada para verificar la correcta implementación de la clase Receta
 */
public class RecetaTest extends TestCase
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es la clase donde se harán las pruebas
	 */
	private Receta receta;
	/**
	 * Un arreglo para almacenar recetas diferentes y compararlas
	 */
	private Receta recetas[];

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Construye una nueva Receta
	 */
	public void setupEscenario1() 
	{
		receta = new Receta("Prueba", "imagen", 2, Receta.CATEGORIA_2, 10, new String[] { "ingrediente1", "ingrediente2" }, "instrucciones");
	}
	
	public void setupEscenario2() {
		recetas = new Receta[3];
		
		recetas[0] = new Receta("a", "imagen", 1, Receta.CATEGORIA_5, 1, new String[] {"1ingredienteReceta0", "2ingredienteReceta0"}, "instruccionesReceta0");
		recetas[1] = new Receta("d", "imagen", 3, Receta.CATEGORIA_3, 5, new String[] {"1ingredienteReceta1", "2ingredienteReceta1", "3ingredienteReceta1"}, "instruccionesReceta1");
		recetas[2] = new Receta("g", "imagen", 5, Receta.CATEGORIA_1, 10, new String[] {"1ingredienteReceta2", "2ingredienteReceta2", "3ingredienteReceta2", "4ingredienteReceta2"}, "instruccionesReceta2");
	}
	/**
	 * 
	 */
	/**
	 * Prueba 1 - Verifica que el nombre de la receta sea correcto
	 */
	public void testDarNombre() 
	{
		setupEscenario1();
		assertNotNull("El nombre de la receta no fue inicializado", receta.darNombre());
		assertEquals("El nombre de la receta no es el esperado", "Prueba", receta.darNombre());
	}

	/**
	 * Prueba 2 - Verifica que la categoría de la receta sea correcta
	 */
	public void testDarCategoria() 
	{
		setupEscenario1();
		assertNotNull("La categoría de la receta no fue inicializada", receta.darCategoria());
		assertEquals("La categoría de la receta no es la esperada", Receta.CATEGORIA_2, receta.darCategoria());
	}

	/**
	 * Prueba 3 - Verifica que la dificultad de la receta sea correcta
	 */
	public void testDarDificultad() 
	{
		setupEscenario1();
		assertEquals("La dificultad de la receta no es la esperada", 2, receta.darDificultad());
	}

	/**
	 * Prueba 4 - Verifica que la foto de la receta sea correcta
	 */
	public void testDarFoto()
	{
		setupEscenario1();
		assertNotNull("La foto de la receta no fue inicializada", receta.darFoto());
		assertEquals("La foto de la receta no es la esperada", "imagen", receta.darFoto());
	}

	/**
	 * Prueba 5 - Verifica que los ingredientes de la receta sean correctos
	 */
	public void testDarIngredientes()
	{
		setupEscenario1();
		assertNotNull("La lista de ingredientes de la receta no fue inicializada", receta.darIngredientes());
		assertEquals("El número de ingredientes de la receta no es el esperado", 2, receta.darIngredientes().length);
		assertEquals("El ingrediente de la receta no es el esperado", "ingrediente1", receta.darIngredientes()[0]);
		assertEquals("El ingrediente de la receta no es el esperado", "ingrediente2", receta.darIngredientes()[1]);
	}

	/**
	 * Prueba 6 - Verifica que las instrucciones de la receta sean correctas
	 */
	public void testDarInstrucciones()
	{
		setupEscenario1();
		assertNotNull("Las instrucciones de la receta no fueron inicializadas",	receta.darInstrucciones());
		assertEquals("Las instrucciones de la receta no son las esperadas",	"instrucciones", receta.darInstrucciones());
	}

	/**
	 * Prueba 7 - Verifica que el tiempo de preparación de la receta sea correcto
	 */
	public void testDarTiempoPreparacion() 
	{
		setupEscenario1();
		assertEquals("El tiempo de preparación de la receta no es el esperado", 10, receta.darTiempoPreparacion());
	}

	/**
	 * Prueba 8 - Verifica que dos recetas se comparen correctamente según su nombre <br>
	 * Caso de prueba 1: La receta con la cual se realiza la comparación es menor.
	 * Caso de prueba 2: Las recetas que se están comparando son iguales.
	 * Caso de prueba 3: La receta con la cual se realiza la comparacaión es mayor.
	 */
	public void testCompararPorNombre()
	{
		setupEscenario2();
		assertEquals("La comparación por nombre cuando el nombre es MENOR ha fallado.", 1, recetas[1].compararPorNombre(recetas[0]));
		assertEquals("La comparación por nombre cuando los nombres son iguales ha fallado.", 0, recetas[1].compararPorNombre(recetas[1]));
		assertEquals("La comparación por nombre cuando el nombres es MAYOR ha fallado.", -1, recetas[1].compararPorNombre(recetas[2]));
	}

	/**
	 * Prueba 9 - Verifica que dos recetas se comparen correctamente según su categoría <br>
	 * Caso de prueba 1: La receta con la cual se realiza la comparación es menor.
	 * Caso de prueba 2: Las recetas que se están comparando son iguales.
	 * Caso de prueba 3: La receta con la cual se realiza la comparacaión es mayor.
	 */
	public void testCompararPorCategoria()
	{
		setupEscenario2();
		assertEquals("La comparación por categoría cuando la categoría es MENOR ha fallado.", 1, recetas[1].compararPorCategoria(recetas[0]));
		assertEquals("La comparación por categoría cuando las categorías son iguales ha fallado.", 0, recetas[1].compararPorCategoria(recetas[1]));
		assertEquals("La comparación por categoría cuando la categoría es MAYOR ha fallado.", -1, recetas[1].compararPorCategoria(recetas[2]));
	}

	/**
	 * Prueba 10 - Verifica que dos recetas se comparen correctamente según su dificultad <br>
	 * Caso de prueba 1: La receta con la cual se realiza la comparación es menor.
	 * Caso de prueba 2: Las recetas que se están comparando son iguales.
	 * Caso de prueba 3: La receta con la cual se realiza la comparacaión es mayor.
	 */
	public void testCompararPorDificultad()
	{
		setupEscenario2();
		assertEquals("La comparación por dificultad cuando la dificultad es MENOR ha fallado.", 1, recetas[1].compararPorDificultad(recetas[0]));
		assertEquals("La comparación por dificultad cuando las dificultades son iguales ha fallado.", 0, recetas[1].compararPorDificultad(recetas[1]));
		assertEquals("La comparación por dificultad cuando la dificultad es MAYOR ha fallado.", -1, recetas[1].compararPorDificultad(recetas[2]));
	}
	
	/**
	 * Prueba 11 - Verifica que dos recetas se comparen correctamente según su número de ingredientes <br>
	 * Caso de prueba 1: La receta con la cual se realiza la comparación es menor.
	 * Caso de prueba 2: Las recetas que se están comparando son iguales.
	 * Caso de prueba 3: La receta con la cual se realiza la comparacaión es mayor.
	 */
	public void testCompararPorNumeroIngredientes()
	{
		setupEscenario2();
		assertEquals("La comparación por número de ingredientes cuando el número de ingredientes es MENOR ha fallado.", 1, recetas[1].compararPorNumeroIngredientes(recetas[0]));
		assertEquals("La comparación por número de ingredientes cuando el número de ingredientes es igual ha fallado.", 0, recetas[1].compararPorNumeroIngredientes(recetas[1]));
		assertEquals("La comparación por número de ingredientes cuando el número de ingredientes es MAYOR ha fallado.", -1, recetas[1].compararPorNumeroIngredientes(recetas[2]));

	}
	
	/**
	 * Prueba 12 - Verifica que dos recetas se comparen correctamente según su tiempo de preparación <br>
	 * Caso de prueba 1: La receta con la cual se realiza la comparación es menor.
	 * Caso de prueba 2: Las recetas que se están comparando son iguales.
	 * Caso de prueba 3: La receta con la cual se realiza la comparacaión es mayor.
	 */
	public void testCompararPorTiempoPreparacion()
	{
		setupEscenario2();
		assertEquals("La comparación por tiempo de preparación cuando el tiempo de preparación es MENOR ha fallado.", 1, recetas[1].compararPorTiempoPreparacion(recetas[0]));
		assertEquals("La comparación por tiempo de preparación cuando los tiempos de preparación son iguales ha fallado.", 0, recetas[1].compararPorTiempoPreparacion(recetas[1]));
		assertEquals("La comparación por tiempo de preparación cuando el tiempo de preparación es MAYOR ha fallado.", -1, recetas[1].compararPorTiempoPreparacion(recetas[2]));

	}
	
	/**
	 * Prueba 13 - Verifica que el toString retorne el nombre de la receta
	 */
	public void testToString() 
	{
		setupEscenario1();

		assertEquals("El toString debe retornar el nombre de la receta", receta.darNombre(), receta.toString());
	}
	
	/**
	 * Prueba 14 - Verifica que se busque correctamente un ingrediente en la lista de ingredientes de la receta
	 * Caso de prueba 1: El ingrediente a buscar hace parte de los ingredientes de la receta <br>
	 * Caso de prueba 2: El ingrediente a buscar no hace parte de los ingredientes de la receta <br>
	 */
	public void testTieneIngrediente()
	{
		setupEscenario1();
		
		// Se busca un ingrediente que hace parte de los ingredientes de la receta
		boolean tieneIngrediente = receta.tieneIngrediente("ingrediente1");
		assertTrue("El ingrediente hace parte de la lista de ingredientes de la receta", tieneIngrediente);
		
		// Se busca un ingrediente que hace parte de los ingredientes de la receta
		tieneIngrediente = receta.tieneIngrediente("ingrediente3");
		assertFalse("El ingrediente no hace parte de la lista de ingredientes de la receta", tieneIngrediente);
		
	}
}