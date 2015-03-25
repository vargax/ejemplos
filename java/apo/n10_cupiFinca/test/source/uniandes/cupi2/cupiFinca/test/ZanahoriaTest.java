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
import uniandes.cupi2.cupiFinca.mundo.Cultivable;
import uniandes.cupi2.cupiFinca.mundo.Zanahoria;
import uniandes.cupi2.cupiFinca.mundo.excepciones.PersistenciaException;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Zanahoria
 */
public class ZanahoriaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Zanahoria zanahoria;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Zanahoria
     * 
     */
    private void setupEscenario1( )
    {
        try
        {
            zanahoria = new Zanahoria( 0, 0, 30, 0 );
        }
        catch( PersistenciaException e )
        {
            fail( "No debe haber error. " + e.getMessage( ) );
        }
    }

    /**
     * Prueba del constructor de una zanahoria
     */
    public void testCultivble( )
    {
        setupEscenario1( );

        // Código de la prueba de valores iniciales
        assertEquals( "La finca debe tener el nombre definido por defecto para el terreno", zanahoria.darNombre( ), Zanahoria.NOMBRE_CULTIVO );
        assertEquals( "La finca debe tener el costo definido por defecto para el terreno", zanahoria.darCosto( ), Zanahoria.COSTO_CULTIVO );
        assertEquals( "El tiempo inicial es el definido por parámetro", zanahoria.darTiempo( ), 0 );
        assertTrue( "El estado de recolección del producto que se cultiva en el terreno debe ser verdadero", zanahoria.esRecolectable( ) );
    }

    /**
     * Prueba que devuelve la ganancia dependiendo el estado en el que se encuentra.<br>
     * 1. En caso de que no esté en un estado previo al de recolección.<br>
     * 2. En caso de que esté en un estado de recolección<br>
     * 2. En caso de que no esté en un estado posterior al de recolección.
     */
    public void testDarGanancia( )
    {
        setupEscenario1( );
        
        // 1. En el caso de que no sea requerido cambiar del estado
        int ganancia = zanahoria.darGanancia( );

        assertEquals( "La ganancia es invalida", ganancia, -1 );

        // 2. en caso de que se requiera el cambio de estado
        for( int i = zanahoria.darTiempo( ); i <= Cultivable.TIEMPO_CRECIMIENTO; i++ )
        {
            zanahoria.avanzarCiclo( );
        }
        ganancia = zanahoria.darGanancia( );
        assertEquals( "La ganancia debe ser la asignada", ganancia, Zanahoria.GANANCIA_CULTIVO );
        
        // 3. en caso de que se requiera el cambio de estado
        for( int i = zanahoria.darTiempo( ); i <= Cultivable.TIEMPO_COSECHA; i++ )
        {
            zanahoria.avanzarCiclo( );
        }
        ganancia = zanahoria.darGanancia( );
        assertEquals( "La ganancia debe ser la asignada", ganancia, 0 );

    }
}