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

import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.toDoList.mundo.Tarea;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Tarea estén correctamente implementados
 */
public class TareaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Tarea tarea;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Tarea
     * 
     */
    private void setupEscenario1( )
    {
        // El año base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 1, 15, 5, 30 );
        // El año base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 1, 20, 10, 00 );
        tarea = new Tarea( "Nombre", "Descripción", fechaInicio, fechaFin );
    }

    /**
     * Construye una nueva lista de tareas con dos tareas
     */
    private void setupEscenario2( )
    {
        // El año base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 1, 15, 5, 30 );
        // El año base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 1, 20, 10, 00 );
        tarea = new Tarea( "Tarea 1", "Descripción 1", fechaInicio, fechaFin );

        // El año base inicia en 1900
        Date fechaInicio2 = new Date( 2011 - 1900, 1, 16, 5, 30 );
        // El año base inicia en 1900
        Date fechaFin2 = new Date( 2011 - 1900, 1, 21, 10, 00 );
        Tarea tarea2 = new Tarea( "Tarea 2", "Descripción 2", fechaInicio2, fechaFin2 );

        tarea.cambiarSiguienteTarea( tarea2 );
    }

    /**
     * Prueba 1 - Prueba el método cambiarSiguienteTarea Métodos a probar: <br>
     * cambiarSiguienteTarea
     */
    public void testCambiarSiguienteTarea( )
    {
        setupEscenario1( );
        // El año base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
        Tarea nueva = new Tarea( "Tarea 2", "Descripción 2", fechaInicio, null );
        tarea.cambiarSiguienteTarea( nueva );
        Tarea siguiente = tarea.darSiguienteTarea( );
        assertEquals( "El nombre de la tarea siguiente no es correcto", siguiente.darNombre( ), "Tarea 2" );
        assertEquals( "La descripción de la tarea siguiente no es correcta", siguiente.darDescripcion( ), "Descripción 2" );
        assertEquals( "La fecha de inicio no es igual", siguiente.darFechaInicio( ), fechaInicio );
        assertNull( "No debería tener fecha de finalización", siguiente.darFechaFin( ) );
    }

    /**
     * Prueba 2 - Prueba el método darCopia Métodos a probar: <br>
     * darCopia
     */
    public void testDarCopia( )
    {
        setupEscenario2( );
        // El año base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 1, 15, 5, 30 );
        // El año base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 1, 20, 10, 00 );
        Tarea copia = tarea.darCopia( );

        assertEquals( "El nombre de la tarea no es correcto", copia.darNombre( ), "Tarea 1" );
        assertEquals( "La descripción de la tarea no es correcta", copia.darDescripcion( ), "Descripción 1" );
        assertEquals( "La fecha de inicio no es igual", copia.darFechaInicio( ), fechaInicio );
        assertEquals( "La fecha de finalización no es igual", copia.darFechaFin( ), fechaFin );
        assertNull( "No debería tener siguiente", copia.darSiguienteTarea( ) );
    }

    /**
     * Prueba 3 - Prueba el método darSiguienteTarea Métodos a probar: <br>
     * darSiguienteTarea
     */
    public void testDarSiguienteTarea( )
    {
        setupEscenario2( );
        // El año base inicia en 1900
        Date fechaInicio2 = new Date( 2011 - 1900, 1, 16, 5, 30 );
        // El año base inicia en 1900
        Date fechaFin2 = new Date( 2011 - 1900, 1, 21, 10, 00 );

        Tarea siguiente = tarea.darSiguienteTarea( );
        assertEquals( "El nombre de la tarea no es correcto", siguiente.darNombre( ), "Tarea 2" );
        assertEquals( "La descripción de la tarea no es correcta", siguiente.darDescripcion( ), "Descripción 2" );
        assertEquals( "La fecha de inicio no es igual", siguiente.darFechaInicio( ), fechaInicio2 );
        assertEquals( "La fecha de finalización no es igual", siguiente.darFechaFin( ), fechaFin2 );
    }

    /**
     * Prueba 4 - Prueba el método editarTarea Métodos a probar: <br>
     * editarTarea
     */
    public void testEditarTarea( )
    {
        setupEscenario2( );
        // El año base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 16, 8, 30 );
        // El año base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 3, 15, 10, 00 );
        tarea.editarTarea( "Nueva descripción", fechaInicio, fechaFin );

        assertEquals( "La nueva descripción no corresponde", tarea.darDescripcion( ), "Nueva descripción" );
        assertEquals( "La nueva fecha de inicio no corresponde", tarea.darFechaInicio( ), fechaInicio );
        assertEquals( "La nueva descripción no corresponde", tarea.darFechaFin( ), fechaFin );
    }

    
    /**
     * Prueba 5 - Prueba el método terminarTarea Métodos a probar: <br>
     * terminarTarea, estaTerminada
     */
    public void testTerminarTarea( )
    {
        setupEscenario2( );
        tarea.terminarTarea( );
        assertTrue( "La tarea no está terminada", tarea.estaTerminada( ) );
    }

    /**
     * Prueba 6 - Prueba el método estaVencida Métodos a probar: <br>
     * estaVencida
     */
    public void testEstaVencida( )
    {
        setupEscenario2( );

        assertTrue( "La tarea debería ser igual", tarea.estaVencida( ) );
    }

}