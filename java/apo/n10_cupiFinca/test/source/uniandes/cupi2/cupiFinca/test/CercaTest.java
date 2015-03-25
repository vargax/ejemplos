/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodríguez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiFinca.mundo.Cerca;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Cerca esten bien implementados
 */
public class CercaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Cerca cerca;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Cerca
     * 
     */
    private void setupEscenario1( )
    {
        cerca = new Cerca( 0, 0, 30 );
    }

    /**
     * Prueba del constructor de una cerca
     */
    public void testCerca( )
    {
        setupEscenario1( );

        // Código de la prueba de valores iniciales
        assertEquals( "La finca debe tener el nombre definido por defecto para el terreno", cerca.darNombre( ), Cerca.NOMBRE_CONSTRUCCION );
        assertEquals( "La finca debe tener el costo definido por defecto para el terreno", cerca.darCosto( ), Cerca.COSTO_CONSTRUCCION );
        assertEquals( "El tiempo inicial es el definido por parámetro", cerca.darTiempo( ), 0 );
        assertEquals( "El tiempo inicial es el definido por parámetro", cerca.darGanancia( ), 0 );
        assertFalse( "El estado de recolección del producto que se cultiva en el terreno debe ser verdadero", cerca.esRecolectable( ) );
    }
}