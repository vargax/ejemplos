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

import junit.framework.TestCase;
import uniandes.cupi2.impuestosCarro.mundo.RangoImpuesto;

/**
 * Clase de prueba para el rango de impuestos
 */
public class RangoImpuestoTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Rango de impuesto
     */
    private RangoImpuesto rango;
    /**
     * Valor inicial del rango
     */
    private double inicio;
    /**
     * Valor final del rango
     */
    private double fin;
    /**
     * porcentaje del rango
     */
    private double porcentaje;
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Crea un escenario configurando un rango
     */
    private void setupEscenario1( )
    {
        inicio = 0;
        fin = 100000000;
        porcentaje = 5;
        rango = new RangoImpuesto( inicio, fin, porcentaje );
    }

    /**
     * Prueba que el rango contenga a su limite inferior
     */
    public void testContieneALimiteInferior( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //Prueba que el limite inferior esté contenido
        assertTrue( rango.contieneA( inicio ) );
    }

    /**
     * Prueba que el rango no contenga a su limite superior
     */
    public void testContieneALimiteSuperior( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //Prueba que el limite superior no esté contenido
        assertFalse( rango.contieneA( fin ) );
    }

    /**
     * Prueba que el rango contenga al valor medio entre el limite inferior y el limite superior
     */
    public void testContieneAValorMedio( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //Prueba que el valor medio esté contenido
        assertTrue( rango.contieneA( ( inicio + fin ) / 2 ) );
    }

    /**
     * Prueba que retorne el porcentaje correctamente
     */
    public void testDarPorcentaje( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //Prueba que el porcentaje dado sea correcto
        assertEquals( porcentaje, rango.darPorcentaje( ), 0 );
    }

}