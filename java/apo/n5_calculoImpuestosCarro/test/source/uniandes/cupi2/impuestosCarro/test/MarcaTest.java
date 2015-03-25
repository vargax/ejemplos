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
import uniandes.cupi2.impuestosCarro.mundo.Marca;
import uniandes.cupi2.impuestosCarro.mundo.Modelo;

/**
 * Clase de prueba para la marca de un vehículo
 */
public class MarcaTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Nombre de la marca de prueba
     */
    private String nombre;
    /**
     * Marca de prueba
     */
    private Marca marca;
    /**
     * Línea de prueba
     */
    private Linea linea;
    /**
     * Modelo de prueba
     */
    private Modelo modelo;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Escenario con una Marca sin líneas
     */
    private void setupEscenario1( )
    {
        nombre = "mazda";
        marca = new Marca( nombre );
    }

    /**
     * Escenario de una marca con una línea sin modelos
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        linea = new Linea( "allegro" );
        marca.adicionarLinea( linea );
    }

    /**
     * Escenario de una marca con una línea con un modelo
     */
    private void setupEscenario3( )
    {
        setupEscenario2( );
        modelo = new Modelo( "2005", 43000000 );
        linea.adicionarModelo( modelo );
    }

    /**
     * Prueba la obtención válida del nombre de la marca
     */
    public void testDarNombre( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //Valida que el nombre sea el adecuado
        assertEquals( nombre, marca.darNombre( ) );
    }

    /**
     * Prueba la correcta adición de una línea
     */
    public void testAdicionarLinea( )
    {
        ArrayList lineas;
        Linea nuevaLinea;
        int antes;

        //Configura el escenario de prueba
        setupEscenario1( );

        //Prueba la correcta adición de líneas
        lineas = marca.darLineas( );
        antes = lineas.size( );
        nuevaLinea = new Linea( "mazda3" );
        marca.adicionarLinea( nuevaLinea );
        assertEquals( antes + 1, lineas.size( ) );
        assertEquals( nuevaLinea, lineas.get( antes ) );
    }

    /**
     * Prueba la obtención de las líneas en una marca sin líneas
     */
    public void testDarLineasVacio( )
    {
        ArrayList lineas;

        //Configura el escenario de prueba
        setupEscenario1( );

        //Verifica que no hayan líneas
        lineas = marca.darLineas( );
        assertEquals( 0, lineas.size( ) );
    }

    /**
     * Prueba la obtención de los líneas en una marca con líneas
     */
    public void testDarLineas( )
    {
        ArrayList lineas;
        Linea unaLinea;
        Modelo unModelo;

        //Configura el escenario de prueba
        setupEscenario3( );

        //Verifica que esté la línea de prueba
        lineas = marca.darLineas( );
        assertEquals( 1, lineas.size( ) );
        unaLinea = ( Linea )lineas.get( 0 );
        assertEquals( linea, unaLinea );
        unModelo = unaLinea.buscarModelo( modelo.darAnio( ) );
        assertEquals( modelo, unModelo );
    }

    /**
     * Prueba la búsqueda de una línea que existe
     */
    public void testBuscarLineaExiste( )
    {
        Linea lineaEncontrada;

        //Configura el escenario de prueba
        setupEscenario2( );

        //Verifica que se encuentre una línea que está en la marca
        lineaEncontrada = marca.buscarLinea( linea.darNombre( ) );
        assertEquals( linea, lineaEncontrada );
    }

    /**
     * Prueba la búsqueda de una línea que no existe
     */
    public void testBuscarLineaNoExiste( )
    {
        Linea lineaEncontrada;

        //Configura el escenario de prueba
        setupEscenario1( );

        //Verifica que no encuentre un modelo que no está en la línea
        lineaEncontrada = marca.buscarLinea( "laLinea" );
        assertNull( lineaEncontrada );
    }
}