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
import uniandes.cupi2.impuestosCarro.mundo.Modelo;

/**
 * Clase de prueba para el modelo de un vehículo
 */
public class ModeloTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Año de un modelo
     */
    private String anio;
    /**
     * Precio del modelo
     */
    private double precio;
    /**
     * Modelo de vehículo
     */
    private Modelo modelo;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Escenario con la creación válida de un modelo de vehículo
     */
    private void setupEscenario1( )
    {
        anio = "2005";
        precio = 43000000;
        modelo = new Modelo( anio, precio );
    }

    /**
     * Prueba la obtención válida del año de un modelo
     */
    public void testDarAnho( )
    {
        //Prepara el escenario
        setupEscenario1( );

        //Valida los datos
        assertEquals( anio, modelo.darAnio( ) );
    }

    /**
     * Prueba la obtención válida del precio de un modelo
     */
    public void testDarPrecio( )
    {
        //Prepara el escenario
        setupEscenario1( );

        //Valida los datos
        assertEquals( precio, modelo.darPrecio( ), 0 );
    }
}