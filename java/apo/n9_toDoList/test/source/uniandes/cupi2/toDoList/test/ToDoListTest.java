/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_toDoList
 * Autor: Carlos Navarrete Puentes - 24-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.toDoList.test;

import java.io.File;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.toDoList.mundo.ElementoExisteException;
import uniandes.cupi2.toDoList.mundo.ElementoNoExisteException;
import uniandes.cupi2.toDoList.mundo.PersistenciaException;
import uniandes.cupi2.toDoList.mundo.Categoria;
import uniandes.cupi2.toDoList.mundo.Tarea;
import uniandes.cupi2.toDoList.mundo.ToDoList;

/**
 * Esta es la clase usada para verificar que los métodos de la clase ToDoList estén correctamente implementados
 */
public class ToDoListTest extends TestCase
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta del archivo de pruebas
     */

    public static final String RUTA_ARCHIVO = "test/data/toDoListTest.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private ToDoList toDoList;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva ToDoList vacía
     * 
     */
    private void setupEscenario1( )
    {
        try
        {
            // Borra la lista de tareas si ya existe
            File f = new File( RUTA_ARCHIVO );
            if( f.exists( ) )
            {
                f.delete( );
            }
            toDoList = new ToDoList( RUTA_ARCHIVO );
        }
        catch( PersistenciaException e )
        {
            fail( "Debería cargar la lista de tareas sin problemas" );
        }
    }

    /**
     * Construye una nueva ToDoList con dos categorías y dos tareas cada una
     */
    private void setupEscenario2( )
    {
        try
        {
            // Borra la lista de tareas si ya existe
            File f = new File( RUTA_ARCHIVO );
            if( f.exists( ) )
            {
                f.delete( );
            }
            toDoList = new ToDoList( RUTA_ARCHIVO );

            try
            {
                toDoList.agregarCategoria( "Categoría 1" );
                toDoList.agregarCategoria( "Categoría 2" );
                // El año base inicia en 1900
                Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
                toDoList.agregarTarea( "Categoría 1", "Tarea 1", "Descripción 1", fechaInicio, null );
                toDoList.agregarTarea( "Categoría 1", "Tarea 2", "Descripción 2", fechaInicio, null );
                toDoList.agregarTarea( "Categoría 2", "Tarea 1", "Descripción 1", fechaInicio, null );
                toDoList.agregarTarea( "Categoría 2", "Tarea 2", "Descripción 2", fechaInicio, null );
            }
            catch( ElementoExisteException e )
            {
                fail( "No debería lanzar excepción "+e.getMessage() );
            }
            catch( ElementoNoExisteException e )
            {
                fail( "No debería lanzar excepción "+e.getMessage() );
            }
        }
        catch( PersistenciaException e )
        {
            e.printStackTrace( );
            fail( "Debería cargar la lista de tareas sin problemas" );
        }
    }

    /**
     * Construye una nueva ToDoList con dos categorías y dos tareas cada una Una de las tareas está terminada, y otra está vencida
     */
    private void setupEscenario3( )
    {
        try
        {
            // Borra la lista de tareas si ya existe
            File f = new File( RUTA_ARCHIVO );
            if( f.exists( ) )
            {
                f.delete( );
            }
            toDoList = new ToDoList( RUTA_ARCHIVO );

            try
            {
                toDoList.agregarCategoria( "Categoría 1" );
                toDoList.agregarCategoria( "Categoría 2" );
                // El año base inicia en 1900
                Date fechaInicio = new Date( 2011 - 1900, 1, 15, 5, 30 );
                toDoList.agregarTarea( "Categoría 1", "Tarea 1", "Descripción 1", fechaInicio, null );
                toDoList.agregarTarea( "Categoría 1", "Tarea 2", "Descripción 2", fechaInicio, null );
                toDoList.agregarTarea( "Categoría 2", "Tarea 1", "Descripción 1", fechaInicio, null );
                toDoList.agregarTarea( "Categoría 2", "Tarea 2", "Descripción 2", fechaInicio, null );

                // Tarea Terminada
                toDoList.terminarTarea( "Categoría 1", "Tarea 2" );

                // Tarea Vencida
                // El año base inicia en 1900
                Date fechaFin = new Date( 2011 - 1900, 1, 20, 10, 00 );
                toDoList.editarTarea( "Categoría 2", "Tarea 2", "Descripción", fechaInicio, fechaFin );

            }
            catch( ElementoExisteException e )
            {
                fail( "No debería lanzar excepción" );
            }
            catch( ElementoNoExisteException e )
            {
                fail( "No debería lanzar excepción" );
            }
        }
        catch( PersistenciaException e )
        {
            fail( "Debería cargar la lista de tareas sin problemas" );
        }
    }

    /**
     * Prueba 1 - Prueba el método darPrimeraCategoria 
     * Métodos a probar: <br>
     * darPrimeraCategoria
     */
    public void testDarPrimeraCategoria1( )
    {
        setupEscenario1( );
        assertNull( "La primera categoría es incorrecta", toDoList.darPrimeraCategoria( ) );
    }

    /**
     * Prueba 2 - Prueba el método darPrimeraCategoria Métodos a probar: <br>
     * darPrimeraCategoria
     */
    public void testDarPrimeraCategoria2( )
    {
        setupEscenario2( );
        assertEquals( "La primera categoría es incorrecta", toDoList.darPrimeraCategoria( ).darNombre( ), "Categoría 1" );
    }

    /**
     * Prueba 3 - Prueba el método agregarCategoria Métodos a probar: <br>
     * agregarCategoria
     */
    public void testAgregarCategoria( )
    {
        setupEscenario1( );
        try
        {
            toDoList.agregarCategoria( "Categoría 1" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debería lanzar excepción" );
        }
    }

    /**
     * Prueba 4 - Prueba el método agregarcategoría Métodos a probar: <br>
     * agregarcategoría
     */
    public void testAgregarcategoríaError( )
    {
        setupEscenario2( );
        try
        {
            toDoList.agregarCategoria( "Categoría 1" );
            fail( "Debería lanzar excepción (Categoría 1 ya existe)" );
        }
        catch( ElementoExisteException e )
        {
            // Ya existe una categoría con ese nombre
        }
    }

    /**
     * Prueba 5 - Prueba el método buscarCategoria Métodos a probar: <br>
     * buscarCategoria
     */
    public void testBuscarCategoria1( )
    {
        setupEscenario1( );
        assertNull( "Encuentra una categoría que no existe", toDoList.buscarCategoria( "Categoría 1" ) );
    }

    /**
     * Prueba 6 - Prueba el método buscarCategoria Métodos a probar: <br>
     * buscarCategoria
     */
    public void testBuscarCategoria2( )
    {
        setupEscenario2( );
        Categoria buscada = toDoList.buscarCategoria( "Categoría 1" );
        assertEquals( "La categoría encontrada es incorrecta", buscada.darNombre( ), "Categoría 1" );
    }

    /**
     * Prueba 7 - Prueba el método agregarTarea Métodos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea( )
    {
        setupEscenario1( );
        try
        {
            toDoList.agregarTarea( "Categoría 1", "Tarea 1", "Descripción 1", new Date( ), null );
            fail( "No debió agregar ninguna tarea" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No existe ninguna tarea con ese nombre" );
        }
        catch( ElementoNoExisteException e )
        {
            // Lanza excepción porque no existe ninguna categoría con ese nombre
        }
    }

    /**
     * Prueba 8 - Prueba el método agregarTarea Métodos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea2( )
    {
        setupEscenario2( );
        try
        {
            // El año base inicia en 1900
            Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
            toDoList.agregarTarea( "Categoría 1", "Tarea 1", "Descripción 1", fechaInicio, null );
            fail( "No debió agregar ninguna tarea" );
        }
        catch( ElementoExisteException e )
        {
            // Lanza excepción porque ya existe una tarea con ese nombre
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No existe ninguna categoría con ese nombre" );
        }
    }

    /**
     * Prueba 9 - Prueba el método agregarTarea Métodos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea3( )
    {
        setupEscenario2( );
        try
        {
            toDoList.agregarTarea( "Categoría 1", "Tarea 3", "Descripción 1", new Date( ), null );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debió lanzar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debió lanzar excepción" );
        }
    }

    /**
     * Prueba 10 - Prueba el método editarTarea Métodos a probar: <br>
     * editarTarea
     */
    public void testEditarTarea( )
    {
        setupEscenario2( );
        // El año base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 16, 8, 30 );
        // El año base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 3, 15, 10, 00 );
        try
        {
            toDoList.editarTarea( "Categoría 1", "Tarea 1", "Nueva descripción", fechaInicio, fechaFin );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debió lanzar excepción" );
        }
        Categoria p = toDoList.darPrimeraCategoria( );
        Tarea tarea = p.buscarTarea( "Tarea 1" );
        assertEquals( "La nueva descripción no corresponde", tarea.darDescripcion( ), "Nueva descripción" );
        assertEquals( "La nueva fecha de inicio no corresponde", tarea.darFechaInicio( ), fechaInicio );
        assertEquals( "La nueva descripción no corresponde", tarea.darFechaFin( ), fechaFin );
    }

    /**
     * Prueba 11 - Prueba el método editarTarea Métodos a probar: <br>
     * editarTarea
     */
    public void testEditarTareaError( )
    {
        setupEscenario2( );
        // El año base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 16, 8, 30 );
        // El año base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 3, 15, 10, 00 );
        try
        {
            toDoList.editarTarea( "Categoría que no existe", "Tarea 1", "Nueva descripción", fechaInicio, fechaFin );
            fail( "Debió lanzar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la categoría dada
        }
    }

    /**
     * Prueba 12 - Prueba el método editarTarea Métodos a probar: <br>
     * editarTarea
     */
    public void testEditarTareaError2( )
    {
        setupEscenario2( );
        // El año base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 16, 8, 30 );
        // El año base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 3, 15, 10, 00 );
        try
        {
            toDoList.editarTarea( "Categoría 1", "Tarea que no existe", "Nueva descripción", fechaInicio, fechaFin );
            fail( "Debió lanzar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 13 - Prueba el método terminarTarea Métodos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTarea( )
    {
        setupEscenario2( );
        try
        {
            toDoList.terminarTarea( "Categoría 1", "Tarea 1" );
            Categoria p = toDoList.darPrimeraCategoria( );
            Tarea tarea = p.buscarTarea( "Tarea 1" );
            assertTrue( "La tarea no está terminada", tarea.estaTerminada( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debió lanzar excepción" );
        }
    }

    /**
     * Prueba 14 - Prueba el método terminarTarea Métodos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTareaError( )
    {
        setupEscenario1( );
        try
        {
            toDoList.terminarTarea( "Categoría 1", "Tarea 1" );
            fail( "Debió lanzar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la categoría dada
        }
    }

    /**
     * Prueba 15 - Prueba el método terminarTarea Métodos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTareaError2( )
    {
        setupEscenario2( );
        try
        {
            toDoList.terminarTarea( "Categoría 1", "Tarea que no existe" );
            fail( "Debió lanzar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 16 - Prueba el método darTareasPendientes Métodos a probar: <br>
     * darTareasPendientes
     */
    public void testDarTareasPendientes( )
    {
        setupEscenario2( );
        Tarea pendientes = toDoList.darTareasPendientes( );
        Tarea actual = pendientes;

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 1" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 2" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 1" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 2" );
    }

    /**
     * Prueba 17 - Prueba el método darTareasTerminadas Métodos a probar: <br>
     * darTareasTerminadas
     */
    public void testDarTareasTerminadas( )
    {
        setupEscenario2( );
        Tarea terminadas = toDoList.darTareasTerminadas( );
        assertNull( "No debería haber tareas terminadas", terminadas );
    }

    /**
     * Prueba 18 - Prueba el método darTareasPendientes Métodos a probar: <br>
     * darTareasPendientes
     */
    public void testDarTareasTerminadas2( )
    {
        setupEscenario2( );
        try
        {
            toDoList.terminarTarea( "Categoría 1", "Tarea 2" );
            toDoList.terminarTarea( "Categoría 2", "Tarea 2" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debió lanzar excepción" );
        }
        Tarea terminadas = toDoList.darTareasTerminadas( );
        Tarea actual = terminadas;
        int numeroTareas = 0;
        while( actual != null )
        {
            numeroTareas++;
            assertEquals( "El nombre de la tarea no es correcto", actual.darNombre( ), "Tarea 2" );
            actual = actual.darSiguienteTarea( );
        }
        assertEquals( "El número de tareas es incorrecto", numeroTareas, 2 );
    }

    /**
     * Prueba 19 - Prueba el método darTareasVencidas Métodos a probar: <br>
     * darTareasVencidas
     */
    public void testDarTareasVencidas( )
    {
        setupEscenario3( );
        Tarea vencida = toDoList.darTareasVencidas( );
        assertEquals( "La tarea vencida no es correcta", vencida.darNombre( ), "Tarea 2" );
    }

    /**
     * Prueba 20 - Prueba el método eliminarTareasTerminadas Métodos a probar: <br>
     * eliminarTareasTerminadas
     */
    public void testEliminarTareasTerminadas( )
    {
        setupEscenario3( );
        toDoList.eliminarTareasTerminadas( );
        Categoria p = toDoList.darPrimeraCategoria( );
        Tarea eliminada = p.buscarTarea( "Tarea 2" );
        assertNull( "La tarea no se eliminó correctamente", eliminada );
    }

}