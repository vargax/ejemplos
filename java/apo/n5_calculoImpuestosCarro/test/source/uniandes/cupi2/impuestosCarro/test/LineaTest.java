/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Impuestos de Carros
 * Autor: Katalina Marcos.
 * Modificación: Diana Puentes - Jun 23, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.impuestosCarro.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.impuestosCarro.mundo.Linea;
import uniandes.cupi2.impuestosCarro.mundo.Modelo;

/**
 * Clase de prueba para la línea de un modelo
 */
public class LineaTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Nombre de la línea
     */
    private String nombre;
    /**
     * Línea del vehículo
     */
    private Linea linea;
    /**
     * Modelo de vehículo 1
     */
    private Modelo modelo1;
    /**
     * Modelo de vehículo 2
     */
    private Modelo modelo2;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Escenario con una línea sin modelos
     */
    private void setupEscenario1( )
    {
        nombre = "allegro";
        linea = new Linea( nombre );
    }

    /**
     * Escenario de una línea con dos modelos
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        modelo1 = new Modelo( "2005", 43000000 );
        modelo2 = new Modelo( "2004", 40000000 );
        linea.adicionarModelo( modelo1 );
        linea.adicionarModelo( modelo2 );
    }

    /**
     * Prueba la obtención válida del nombre de la línea
     */
    public void testDarNombre( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //Valida que el nombre sea el adecuado
        assertEquals( nombre, linea.darNombre( ) );
    }

    /**
     * Prueba la correcta adición de un modelo
     */
    public void testAdicionarModelo( )
    {
        ArrayList modelos;
        Modelo nuevoModelo;
        int antes;

        //Configura el escenario de prueba
        setupEscenario2( );

        //Prueba la correcta adición de modelos
        modelos = linea.darModelos( );
        antes = modelos.size( );
        nuevoModelo = new Modelo( "2000", 30000000 );
        linea.adicionarModelo( nuevoModelo );
        assertEquals( antes + 1, modelos.size( ) );
        assertEquals( nuevoModelo, modelos.get( antes ) );
    }

    /**
     * Prueba la obtención de los modelos en una línea sin modelos
     */
    public void testDarModelosVacio( )
    {
        ArrayList modelos;

        //Configura el escenario de prueba
        setupEscenario1( );

        //Verifica que no hayan modelos
        modelos = linea.darModelos( );
        assertEquals( 0, modelos.size( ) );
    }

    /**
     * Prueba la obtención de los modelos en una línea con modelos
     */
    public void testDarModelos( )
    {
        ArrayList modelos;
        Modelo unModelo;

        //Configura el escenario de prueba
        setupEscenario2( );

        //Verifica que estén los modelos de prueba
        modelos = linea.darModelos( );
        assertEquals( 2, modelos.size( ) );
        unModelo = ( Modelo )modelos.get( 0 );
        assertEquals( modelo1, unModelo );
        unModelo = ( Modelo )modelos.get( 1 );
        assertEquals( modelo2, unModelo );
    }

    /**
     * Prueba la búsqueda de un modelo que existe
     */
    public void testBuscarModeloExiste( )
    {
        Modelo modeloEncontrado;

        //Configura el escenario de prueba
        setupEscenario2( );

        //Verifica que se encuentre un modelo que está en la línea
        modeloEncontrado = linea.buscarModelo( modelo1.darAnio( ) );
        assertEquals( modelo1, modeloEncontrado );
    }

    /**
     * Prueba la búsqueda de un modelo que no existe
     */
    public void testBuscarModeloNoExiste( )
    {
        Modelo modeloEncontrado;

        //Configura el escenario de prueba
        setupEscenario1( );

        //Verifica que no encuentre un modelo que no está en la línea
        modeloEncontrado = linea.buscarModelo( "1800" );
        assertNull( modeloEncontrado );
    }
}