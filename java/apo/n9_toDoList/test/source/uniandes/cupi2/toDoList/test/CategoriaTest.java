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

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.toDoList.mundo.ElementoExisteException;
import uniandes.cupi2.toDoList.mundo.ElementoNoExisteException;
import uniandes.cupi2.toDoList.mundo.Categoria;
import uniandes.cupi2.toDoList.mundo.Tarea;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Categoria estén correctamente implementados
 */
public class CategoriaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Categoria categoria;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Categoría vacía
     * 
     */
    private void setupEscenario1( )
    {
        categoria = new Categoria( "Categoría" );
    }

    /**
     * Construye una nueva categoría con cuatro tareas Una de las tareas está terminada, y otra está vencida
     */
    private void setupEscenario2( )
    {
        try
        {
            categoria = new Categoria( "Categoría" );
            // El año base inicia en 1900
            Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
            categoria.agregarTarea( "Tarea 1", "Descripción 1", fechaInicio, null );
            categoria.agregarTarea( "Tarea 2", "Descripción 2", fechaInicio, null );
            categoria.agregarTarea( "Tarea 3", "Descripción 3", fechaInicio, null );
            categoria.agregarTarea( "Tarea 4", "Descripción 4", fechaInicio, null );

            // Tarea Terminada
            categoria.terminarTarea( "Tarea 2" );

            // Tarea Vencida
            // El año base inicia en 1900
            Date fechaFin = new Date( 2011 - 1900, 2, 20, 10, 00 ); // cambiando 1 por 2
            categoria.editarTarea( "Tarea 4", "Descripción", fechaInicio, fechaFin );
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

    /**
     * Prueba 1 - Prueba el método agregarTarea Métodos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea1( )
    {
        setupEscenario2( );
        try
        {
            // El año base inicia en 1900
            Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
            categoria.agregarTarea( "Tarea 1", "Descripción 1", fechaInicio, null );
            fail( "No debió agregar ninguna tarea" );
        }
        catch( ElementoExisteException e )
        {
            // Lanza excepción porque ya existe una tarea con ese nombre
        }
    }

    /**
     * Prueba 2 - Prueba el método agregarTarea Métodos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea2( )
    {
        setupEscenario2( );
        try
        {
            categoria.agregarTarea( "Tarea 5", "Descripción 5", new Date( ), null );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debió lanzar excepción" );
        }
    }

    /**
     * Prueba 3 - Prueba el método buscarTarea Métodos a probar: <br>
     * buscarTarea
     */
    public void testBuscarTarea( )
    {
        setupEscenario2( );
        Tarea buscada = categoria.buscarTarea( "Tarea 3" );
        assertNotNull( "No encuentra la tarea buscada ", buscada );
        assertEquals( "La descripción no corresponde", buscada.darDescripcion( ), "Descripción 3" );
    }

    /**
     * Prueba 4 - Prueba el método buscarTarea Métodos a probar: <br>
     * buscarTarea
     */
    public void testBuscarTarea2( )
    {
        setupEscenario2( );
        Tarea buscada = categoria.buscarTarea( "Tarea no existente" );
        assertNull( "No encuentra la tarea buscada ", buscada );
    }

    /**
     * Prueba 5 - Prueba el método editarTarea Métodos a probar: <br>
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
            categoria.editarTarea( "Tarea 1", "Nueva descripción", fechaInicio, fechaFin );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debió lanzar excepción" );
        }
        Tarea tarea = categoria.buscarTarea( "Tarea 1" );
        assertEquals( "La nueva descripción no corresponde", tarea.darDescripcion( ), "Nueva descripción" );
        assertEquals( "La nueva fecha de inicio no corresponde", tarea.darFechaInicio( ), fechaInicio );
        assertEquals( "La nueva descripción no corresponde", tarea.darFechaFin( ), fechaFin );
    }

    /**
     * Prueba 6 - Prueba el método editarTarea Métodos a probar: <br>
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
            categoria.editarTarea( "Tarea que no existe", "Nueva descripción", fechaInicio, fechaFin );
            fail( "Debió lanzar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 7 - Prueba el método terminarTarea Métodos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTarea( )
    {
        setupEscenario2( );
        try
        {
            categoria.terminarTarea( "Tarea 1" );
            Tarea tarea = categoria.buscarTarea( "Tarea 1" );
            assertTrue( "La tarea no está terminada", tarea.estaTerminada( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debió lanzar excepción" );
        }
    }

    /**
     * Prueba 8 - Prueba el método terminarTarea Métodos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTareaError( )
    {
        setupEscenario1( );
        try
        {
            categoria.terminarTarea( "Tarea 1" );
            fail( "Debió lanzar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 9 - Prueba el método terminarTarea Métodos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTareaError2( )
    {
        setupEscenario2( );
        try
        {
            categoria.terminarTarea( "Tarea que no existe" );
            fail( "Debió lanzar excepción" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 10 - Prueba el método darTareasPendientes Métodos a probar: <br>
     * darTareasPendientes
     */
    public void testDarTareasPendientes( )
    {
        setupEscenario2( );
        Tarea pendientes = categoria.darTareasPendientes( );
        Tarea actual = pendientes;

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 1" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 3" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 4" );
        actual = actual.darSiguienteTarea( );

        assertNull( "No debería haber más tareas", actual );

    }

    /**
     * Prueba 11 - Prueba el método darTareasTerminadas Métodos a probar: <br>
     * darTareasTerminadas
     */
    public void testDarTareasTerminadas( )
    {
        setupEscenario1( );
        Tarea terminadas = categoria.darTareasTerminadas( );
        assertNull( "No debe haber tareas terminadas", terminadas );
    }

    /**
     * Prueba 12 - Prueba el método darTareasPendientes Métodos a probar: <br>
     * darTareasPendientes
     */
    public void testDarTareasTerminadas2( )
    {
        setupEscenario2( );
        Tarea terminadas = categoria.darTareasTerminadas( );
        assertEquals( "El nombre de la tarea es incorrecto", terminadas.darNombre( ), "Tarea 2" );

        terminadas = terminadas.darSiguienteTarea( );
        assertNull( "No debería haber más tareas", terminadas );
    }

    /**
     * Prueba 13 - Prueba el método darTareasVencidas Métodos a probar: <br>
     * darTareasVencidas
     */
    public void testDarTareasVencidas( )
    {
        setupEscenario2( );
        Tarea vencida = categoria.darTareasVencidas( );
        assertEquals( "La tarea vencida no es correcta", vencida.darNombre( ), "Tarea 4" );
    }

    /**
     * Prueba 14 - Prueba el método eliminarTareasTerminadas Métodos a probar: <br>
     * eliminarTareasTerminadas
     */
    public void testEliminarTareasTerminadas( )
    {
        setupEscenario2( );
        categoria.eliminarTareasTerminadas( );
        Tarea eliminada = categoria.buscarTarea( "Tarea 2" );
        assertNull( "La tarea no se eliminó correctamente", eliminada );
    }

}