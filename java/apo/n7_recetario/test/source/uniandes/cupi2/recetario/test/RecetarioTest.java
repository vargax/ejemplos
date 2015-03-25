/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_recetario
 * Autor: Catalina Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.recetario.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.recetario.mundo.Receta;
import uniandes.cupi2.recetario.mundo.Recetario;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Recetario estén correctamente implementados
 */
public class RecetarioTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Recetario recetario;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Recetario vacío
     *  
     */
    private void setupEscenario1( )
    {
        recetario = new Recetario( );
    }

    /**
     * Construye un nuevo Recetario con 3 recetas
     */
    private void setupEscenario2()
    {
    	recetario = new Recetario( );
    	recetario.agregarReceta("Sopa de Pepinos", "./data/imagenes/sopaPepino.jpg", 2, Receta.CATEGORIA_1, 25, new String[]{"Caldo de pollo", "Nata líquida", "Pepinos","Queso"}, "Mezclar los ingredientes y poner a cocinar");
    	recetario.agregarReceta("Dulce de castañas", "./data/imagenes/dulceCastanas.jpg", 5, Receta.CATEGORIA_6, 60, new String[]{"Almendra molida","Azúcar","Canela","Castañas","Chocolate","Huevos","Limón"}, "Mezclar los ingredientes y poner en la nevera");
    	recetario.agregarReceta("Berenjenas al Horno", "./data/imagenes/berenjenas.jpg", 3, Receta.CATEGORIA_3, 30, new String[]{"Berenjenas","Carne picada","Huevos","Sal","Queso rallado"}, "Mezclar los ingredientes y poner al horno");
    }
  
    /**
     * Construye un nuevo Recetario con 8 recetas. Escenario usado para probar los métodos de ordenamiento y de búsqueda
     */
    private void setupEscenario3()
    {
    	recetario = new Recetario( );
    	recetario.agregarReceta("Sopa de Pepinos", "./data/imagenes/sopaPepino.jpg", 2, Receta.CATEGORIA_1, 25, new String[]{"Caldo de pollo", "Nata líquida", "Pepinos","Queso"}, "Mezclar los ingredientes y poner a cocinar");
    	recetario.agregarReceta("Dulce de castañas", "./data/imagenes/dulceCastanas.jpg", 5, Receta.CATEGORIA_6, 60, new String[]{"Almendra molida","Azúcar","Canela","Castañas","Chocolate","Huevos","Limón"}, "Mezclar los ingredientes y poner en la nevera");
    	recetario.agregarReceta("Berenjenas al Horno", "./data/imagenes/berenjenas.jpg", 3, Receta.CATEGORIA_3, 30, new String[]{"Berenjenas","Carne picada","Huevos","Sal","Queso rallado"}, "Mezclar los ingredientes y poner al horno");
    	recetario.agregarReceta("Pavo en Escabeche", "./data/imagenes/pavoEscabeche.jpg", 7, Receta.CATEGORIA_5, 180, new String[]{"Aceite de oliva","Ajo","Almendras","Azúcar","Cebolla","Ciruelas pasas","Dátiles","Higos","Laurel","Orejones","Pasas de corinto","Pavo"}, "Mezclar los ingredientes y poner a cocinar");
    	recetario.agregarReceta("Pasta a la Carbonara", "./data/imagenes/pastaCarbonara.jpg", 4, Receta.CATEGORIA_2, 30, new String[]{"Aceite","Pasta","Huevo","Pimienta negra","Queso pecorino","Sal","Tocino"}, "Mezclar los ingredientes y cocinar");
    	recetario.agregarReceta("Tarta de Atún", "./data/imagenes/tartaAtun.jpg", 8, Receta.CATEGORIA_4, 40, new String[]{"Atún","Pepinillos","Alcaparras","Cebollino","Salsa Perrins","Mostaza Dijon","Aceite de oliva","Zumo limón","Lima kafir","Salsa de Soja","Lechuga","Pimienta","Sal"}, "Picamos, mezclamos los ingredientes y cocinamos");
    	recetario.agregarReceta("Cerdo Agridulce", "./data/imagenes/cerdoAgridulce.jpg", 5, Receta.CATEGORIA_5, 45, new String[]{"Maicena","Sal","Pimienta","Cerdo en cubos","Aceite de girasol","Pimiento","Piña"}, "Mezclar los ingredientes y poner al horno");
    	recetario.agregarReceta("Mousse de Maracuyá", "./data/imagenes/mousseMaracuya.jpg", 1, Receta.CATEGORIA_6, 15, new String[]{"Leche condensada","Crema de leche","Maracuyás"}, "Mezclar los ingredientes y refrigerar");
    }
    
    /**
     * Prueba 1 - Verifica que al crearse la clase recetario se inicialice la lista de recetas y que su tamaño sea igual a cero 
     */
    public void testRecetario( )
    {
        setupEscenario1( );
        assertNotNull("La lista debio haberse inicializado", recetario.darRecetas());
        assertEquals("La lista de recetas debe crearse vacía", 0, recetario.darRecetas().size());
    }
    
    /**
     * Prueba 2 - Verifica que se pueda agregar una receta cuando no se ha agregado ninguna
     */
    public void testRegistrarPrimeraReceta()
    {
        setupEscenario1( );
        
        //Verificar que se haya ingresado la receta
        boolean recetaAgregada = recetario.agregarReceta("Sopa de Pepinos", "./data/imagenes/sopaPepino.jpg", 2, Receta.CATEGORIA_1, 25, new String[]{"Caldo de pollo", "Nata líquida", "Pepinos","Queso"}, "Mezclar los ingredientes y poner a cocinar");
    	assertTrue( "Siempre se debe poder agregar la primera receta", recetaAgregada);
        
    	ArrayList recetas = recetario.darRecetas();
        assertEquals( "La receta no se agregó a la lista de recetas", 1, recetas.size() );
        
        //Datos de la receta que se agregó en la lista
        Receta receta = (Receta) recetas.get( 0 );
        assertEquals("El nombre de la receta no es el esperado", "Sopa de Pepinos", receta.darNombre());
        assertEquals("La categoría de la receta no es la esperada", Receta.CATEGORIA_1, receta.darCategoria());
        assertEquals("La dificultad de la receta no es la esperada", 2, receta.darDificultad());
        assertEquals("La foto de la receta no es la esperada", "./data/imagenes/sopaPepino.jpg", receta.darFoto());
        assertEquals("Las instrucciones de la receta no son las esperadas",	"Mezclar los ingredientes y poner a cocinar", receta.darInstrucciones());
        assertEquals("El tiempo de preparación de la receta no es el esperado", 25, receta.darTiempoPreparacion());        
    }
    
    /**
     * Prueba 3 - Verifica que se pueda registrar una receta cuando ya existen recetas en el recetario
     */
    public void testRegistrarReceta()
    {
        setupEscenario2( );
        
        //Verificar que se haya ingresado la receta
        boolean recetaAgregada = recetario.agregarReceta("Tarta de Atún", "./data/imagenes/tartaAtun.jpg", 8, Receta.CATEGORIA_4, 40, new String[]{"Atún","Pepinillos","Alcaparras","Cebollino","Salsa Perrins","Mostaza Dijon","Aceite de oliva","Zumo limón","Lima kafir","Salsa de Soja","Lechuga","Pimienta","Sal"}, "Picamos, mezclamos los ingredientes y cocinamos");
    	assertTrue( "Error al agregar la receta", recetaAgregada);
        
    	ArrayList recetas = recetario.darRecetas();
        assertEquals( "La receta no se agregó a la lista de recetas", 4, recetas.size() );
        
        //Datos de la receta que se agregó en la lista
        Receta receta = (Receta) recetas.get( 3 );
        assertEquals("El nombre de la receta no es el esperado", "Tarta de Atún", receta.darNombre());
        assertEquals("La categoría de la receta no es la esperada", Receta.CATEGORIA_4, receta.darCategoria());
        assertEquals("La dificultad de la receta no es la esperada", 8, receta.darDificultad());
        assertEquals("La foto de la receta no es la esperada", "./data/imagenes/tartaAtun.jpg", receta.darFoto());
        assertEquals("Las instrucciones de la receta no son las esperadas",	"Picamos, mezclamos los ingredientes y cocinamos", receta.darInstrucciones());
        assertEquals("El tiempo de preparación de la receta no es el esperado", 40, receta.darTiempoPreparacion());  
    }
    
    /**
     * Prueba 4 - Verifica que no se agregue una receta si ya existe en el recetario una receta con el mismo nombre
     */
    public void testRegistrarRecetaConNombreExistente()
    {
        setupEscenario2( );
        
        //Verificar que no se haya ingresado la receta
        boolean recetaAgregada = recetario.agregarReceta("Sopa de Pepinos", "./data/imagenes/sopaPepino.jpg", 2, Receta.CATEGORIA_1, 25, new String[]{"Caldo de pollo", "Nata líquida", "Pepinos","Queso"}, "Mezclar los ingredientes y poner a cocinar");
    	assertFalse( "No debió haber registrado la receta", recetaAgregada);
        assertEquals( "El número de recetas del recetario no debió cambiar", 3, recetario.darRecetas().size() );
    }

    /**
     * Prueba 5 - Verifica que busque correctamente una receta con un nombre dado <br>
     * Si la receta existe se debe retornar el índice de la receta en la lista del recetario
     */
    public void testBuscarReceta()
    {
        setupEscenario2( );
        
        int indice = recetario.buscarReceta("Berenjenas al Horno");
        assertTrue( "Debió haber encontrado una receta con el nombre dado", indice > -1);
        assertEquals( "El índice retornado no es el correcto", 2, indice);
    }
    
    /**
     * Prueba 6 - Verifica que busque correctamente una receta con un nombre dado <br>
     * Si la receta no existe se debe retornar -1
     */
    public void testBuscarRecetaNoExistente()
    {
        setupEscenario2( );
        
        int indice = recetario.buscarReceta("Mondongo");
        assertEquals( "No debería encontrar una receta con el nombre dado", -1, indice);
    }
    
    /**
     * Prueba 7 - Verifica el método ordenar por nombre
     * Caso de prueba 1: Se espera que "Berenjenas al horno" esté en la primera posición
     * Caso de prueba 2: Se espera que "Cerdo Agridulce" esté en la segunda posición
     * Caso de prueba 3: Se espera que "Dulce de castañas" esté en la tercera posición
     * Caso de prueba 4: Se espera que "Mousse de Maracuyá" esté en la cuarta posición
     * Caso de prueba 5: Se espera que "Pasta a la Carbonara" esté en la quinta posición
     * Caso de prueba 6: Se espera que "Pavo en Escabeche" esté en la sexta posición
     * Caso de prueba 7: Se espera que "Sopa de Pepinos" esté en la septima posición
     * Caso de prueba 8: Se espera que "Tarta de Atún" esté en la octava posición
     */
    public void testOrdenarPorNombre()
    {
    	setupEscenario3();
    	recetario.ordenarPorNombre();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Berenjenas al Horno","Cerdo Agridulce","Dulce de castañas","Mousse de Maracuyá",
    			"Pasta a la Carbonara","Pavo en Escabeche","Sopa de Pepinos","Tarta de Atún"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Falló el ordenamiento por nombre",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}
    }
    
    /**
     * Prueba 8 - Verifica el método ordenar por dificultad
	 * Caso de prueba 1: Se espera que "Mousse de Maracuyá" esté en la primera posición
     * Caso de prueba 2: Se espera que "Sopa de Pepinos" esté en la segunda posición
     * Caso de prueba 3: Se espera que "Berenjenas al horno" esté en la tercera posición
     * Caso de prueba 4: Se espera que "Pasta a la Carbonara" esté en la cuarta posición
     * Caso de prueba 5: Se espera que "Dulce de castañas" esté en la quinta posición
     * Caso de prueba 6: Se espera que "Cerdo Agridulce" esté en la sexta posición
     * Caso de prueba 7: Se espera que "Pavo en Escabeche" esté en la septima posición
     * Caso de prueba 8: Se espera que "Tarta de Atún" esté en la octava posición
     */
    public void testOrdenarPorDificultad()
    {
    	setupEscenario3();
    	recetario.ordenarPorDificultad();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Mousse de Maracuyá","Sopa de Pepinos","Berenjenas al Horno","Pasta a la Carbonara","Dulce de castañas","Cerdo Agridulce",
    			"Pavo en Escabeche","Tarta de Atún"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Falló el ordenamiento por dificultad",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}

    }
    
    /**
     * Prueba 9 - Verifica el método ordenar por número de ingredientes
     * Caso de prueba 1: Se espera que "Mousse de Maracuyá" esté en la primera posición
     * Caso de prueba 2: Se espera que "Sopa de Pepinos" esté en la segunda posición
     * Caso de prueba 3: Se espera que "Berenjenas al horno" esté en la tercera posición
     * Caso de prueba 4: Se espera que "Pasta a la Carbonara" esté en la cuarta posición
     * Caso de prueba 5: Se espera que "Cerdo Agridulce" esté en la quinta posición
     * Caso de prueba 6: Se espera que "Dulce de castañas" esté en la sexta posición
     * Caso de prueba 7: Se espera que "Pavo en Escabeche" esté en la septima posición
     * Caso de prueba 8: Se espera que "Tarta de Atún" esté en la octava posición
     */
    public void testOrdenarPorNumeroIngredientes()
    {
    	setupEscenario3();
    	recetario.ordenarPorNumeroIngredientes();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Mousse de Maracuyá","Sopa de Pepinos","Berenjenas al Horno","Pasta a la Carbonara","Cerdo Agridulce","Dulce de castañas","Pavo en Escabeche",
    			"Tarta de Atún"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Falló el ordenamiento por número de ingredientes",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}
    }
    
    /**
     * Prueba 10 - Verifica el método ordenar por categoría
     * Caso de prueba 1: Se espera que "Sopa de Pepinos" esté en la primera posición
     * Caso de prueba 2: Se espera que "Dulce de castañas" esté en la segunda posición
     * Caso de prueba 3: Se espera que "Mousse de Maracuyá" esté en la tercera posición
     * Caso de prueba 4: Se espera que "Tarta de Atún" esté en la cuarta posición
     * Caso de prueba 5: Se espera que "Pasta a la Carbonara" esté en la quinta posición
     * Caso de prueba 6: Se espera que "Berenjenas al horno" esté en la sexta posición
     * Caso de prueba 7: Se espera que "Cerdo Agridulce" esté en la septima posición
     * Caso de prueba 8: Se espera que "Pavo en Escabeche" esté en la octava posición
     */
    public void testOrdenarPorCategoria()
    {
    	setupEscenario3();
    	recetario.ordenarPorCategoria();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Sopa de Pepinos","Dulce de castañas","Mousse de Maracuyá","Tarta de Atún","Pasta a la Carbonara","Berenjenas al Horno","Cerdo Agridulce",
    			"Pavo en Escabeche"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Falló el ordenamiento por categoría",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}
    }
    
    /**
     * Prueba 11 - Verifica el método ordenar por tiempo de preparación
     * Caso de prueba 1: Se espera que "Mousse de Maracuyá" esté en la primera posición
     * Caso de prueba 2: Se espera que "Sopa de Pepinos" esté en la segunda posición
     * Caso de prueba 3: Se espera que "Berenjenas al horno" esté en la tercera posición
     * Caso de prueba 4: Se espera que "Pasta a la Carbonara" esté en la cuarta posición
     * Caso de prueba 5: Se espera que "Tarta de Atún" esté en la quinta posición
     * Caso de prueba 6: Se espera que "Cerdo Agridulce" esté en la sexta posición
     * Caso de prueba 7: Se espera que "Dulce de castañas" esté en la septima posición
     * Caso de prueba 8: Se espera que "Pavo en Escabeche" esté en la octava posición
     */
    public void testOrdenarPorTiempoPreparacion()
    {
    	setupEscenario3();
    	recetario.ordenarPorTiempoPreparacion();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Mousse de Maracuyá","Sopa de Pepinos","Berenjenas al Horno","Pasta a la Carbonara","Tarta de Atún","Cerdo Agridulce","Dulce de castañas",
    			"Pavo en Escabeche"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Falló el ordenamiento por categoría",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}
    }
    
    /**
     * Prueba 12 - Verifica que el arreglo de recetas no retorne null, y que tenga el tamaño esperado
     */
    public void testDarRecetas()
    {
        setupEscenario2( );
        
        ArrayList recetas = recetario.darRecetas();
        assertNotNull( "La lista de recetas no debe ser null", recetas);
        assertEquals( "El tamaño de la lista de recetas no es correcto", 3, recetas.size( ) );
    }
    
    /**
     * Prueba 13 - Verifica que busque correctamente una receta con un nombre usando búsqueda binaria <br>
     * Si la receta existe se debe retornar el índice de la receta en la lista del recetario
     */
    public void testBuscarBinarioPorNombre()
    {
        setupEscenario2( );
        
        recetario.ordenarPorNombre();
        int indice = recetario.buscarBinarioPorNombre("Sopa de pepinos");
        assertTrue( "Debió haber encontrado una receta con el nombre dado", indice > -1);
        assertEquals( "El índice retornado no es el correcto", 2, indice);
    }
    
    /**
     * Prueba 14 - Verifica que busque correctamente una receta con un nombre usando búsqueda binaria <br>
     * Si la receta no existe se debe retornar -1
     */
    public void testBuscarBinarioPorNombreNoExistente()
    {
        setupEscenario2( );
        
        recetario.ordenarPorNombre();
        int indice = recetario.buscarBinarioPorNombre("Mondongo");
        assertEquals( "No debería encontrar una receta con el nombre dado", -1, indice);
    }
    
    /**
     * Prueba 15 - Verifica el método buscar receta más fácil
     */
    public void testBuscarRecetaMasFacil()
    {
    	setupEscenario3();
    	
    	int indice = recetario.buscarRecetaMasFacil();
    	assertEquals("La receta más fácil no es correcta", 7, indice);
    }
    
    /**
    * Prueba 16 - Verifica el método buscar receta más difícil
    */
   public void testBuscarRecetaMasDificil()
   {
	   setupEscenario3();
   	
	   int indice = recetario.buscarRecetaMasDificil();
	   assertEquals("La receta más difícil no es correcta", 5, indice);
   }
   
   /**
    * Prueba 17 - Verifica el método buscar recetas con ingrediente <br>
    * Verifica que el número de recetas con el ingrediente dado sea el esperado <br>+
    * Verifica que las receta tengan el ingrediente dado 
    */
   public void testBuscarRecetasConIngrediente()
   {
	   setupEscenario3();
	   
	   ArrayList recetas = recetario.buscarRecetasConIngrediente("Aceite");
	   assertEquals("No se realizó la búsqueda correctamente", 4, recetas.size());
	   
	   for(int i=0; i<recetas.size( ); i++)
       {
           Receta receta = (Receta) recetas.get(i);
           assertTrue( "No se buscaron correctamente las recetas", receta.tieneIngrediente("Aceite") );
       }
   }
}