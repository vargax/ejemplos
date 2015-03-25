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
import uniandes.cupi2.cupiFinca.mundo.Casa;
import uniandes.cupi2.cupiFinca.mundo.Cebolla;
import uniandes.cupi2.cupiFinca.mundo.Cultivable;
import uniandes.cupi2.cupiFinca.mundo.ITerreno;
import uniandes.cupi2.cupiFinca.mundo.excepciones.PersistenciaException;

/**
 * Esta es la clase usada para verificar que los métodos de la interfaz ITerreno y clase Terreno esten bien implementados
 */
public class ITerrenoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private ITerreno terreno;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Terreno Cultivable
     * 
     */
    private void setupEscenario1( )
    {
        try
        {
            terreno = new Cebolla( 0, 0, 30, 0 );
        }
        catch( PersistenciaException e )
        {
            fail( "No debe haber error. " + e.getMessage( ) );
        }
    }

    /**
     * Construye un nuevo Terreno de Construcción
     * 
     */
    private void setupEscenario2( )
    {
        terreno = new Casa( 0, 0, 30 );
    }

    /**
     * Prueba del constructor de un elemento recolectable
     */
    public void testTerreno1( )
    {
        setupEscenario1( );

        // Código de la prueba de valores iniciales
        assertEquals( "La finca debe tener el nombre definido por defecto para el terreno", terreno.darNombre( ), Cebolla.NOMBRE_CULTIVO );
        assertEquals( "La finca debe tener el costo definido por defecto para el terreno", terreno.darCosto( ), Cebolla.COSTO_CULTIVO );
        assertEquals( "El tiempo inicial es el definido por parámetro", terreno.darTiempo( ), 0 );
        assertTrue( "El estado de recolección del producto que se cultiva en el terreno debe ser verdadero", terreno.esRecolectable( ) );
    }

    /**
     * Prueba del constructor de un elemento no recolectable
     */
    public void testTerreno2( )
    {
        setupEscenario2( );

        // Código de la prueba de valores iniciales
        assertEquals( "La finca debe tener el nombre definido por defecto para el terreno", terreno.darNombre( ), Casa.NOMBRE_CONSTRUCCION );
        assertEquals( "La finca debe tener el costo definido por defecto para el terreno", terreno.darCosto( ), Casa.COSTO_CONSTRUCCION );
        assertEquals( "El tiempo inicial es el definido por parámetro", terreno.darTiempo( ), 0 );
        assertFalse( "El estado de recolección del producto que se cultiva en el terreno", terreno.esRecolectable( ) );
    }

    /**
     * Prueba que actualiza el estado del terreno.<br>
     * 1. En caso de que aumente el tiempo y no cambie de estado<br>
     * 2. En caso de que aumente el tiempo y tenga que cambiar de estado
     */
    public void testAvanzarCiclo( )
    {
        setupEscenario1( );
        
        //Asigna el estado inicial
        terreno.avanzarCiclo( );//Para definir el estado, inicialmente el estado no se define

        // 1. En el caso de que no sea requerido cambiar del estado
        int estadoAnterior = terreno.darEstado( );
        int tiempoAnterior = terreno.darTiempo( );

        terreno.avanzarCiclo( );
        assertNotSame( "El tiempo debió cambiar", tiempoAnterior, terreno.darTiempo( ) );
        assertEquals( "El estado no se debió modificar", estadoAnterior, terreno.darEstado( ) );

        // 2. en caso de que se requiera el cambio de estado
        for( int i = 2/*Anteriormente avanzo dos veces*/; i < Cultivable.TIEMPO_PREPARACION; i++ )
        {
            terreno.avanzarCiclo( );
        }
        estadoAnterior = terreno.darEstado( );
        tiempoAnterior = terreno.darTiempo( );

        terreno.avanzarCiclo( );
        assertNotSame( "El tiempo debió cambiar", tiempoAnterior, terreno.darTiempo( ) );
        System.out.println(estadoAnterior + " " + terreno.darEstado( ));
        assertNotSame( "El estado no se debió modificar", estadoAnterior, terreno.darEstado( ) );
    }
}