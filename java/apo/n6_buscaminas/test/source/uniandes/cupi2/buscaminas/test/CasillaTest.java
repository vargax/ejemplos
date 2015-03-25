/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CasillaTest.java 289 2006-11-06 21:48:13Z f-vela $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 
 * Ejercicio: n6_buscaminas 
 * Autor: Milena Vela - 22/03/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.buscaminas.test;


import uniandes.cupi2.buscaminas.mundo.Casilla;
import junit.framework.TestCase;

/**
 * Prueba la clase Casilla
 */
public class CasillaTest extends TestCase {

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la casilla utilizada para las pruebas
     */
    private Casilla casilla;
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye el escenario 1 donde hay una casilla marcada
     */
    private void setupEscenario1( )
    {
        casilla = new Casilla( Casilla.MARCADA, 1, 1 );
    }
    
    /**
     * Construye el escenario 2 donde hay una casilla marcada equivocada
     */
    private void setupEscenario2( )
    {
        casilla = new Casilla( Casilla.MARCADA_EQUIVOCADA, 6, 2 );
    }
    
    /**
     * Construye el escenario 3 donde hay una casilla sin destapar
     */
    private void setupEscenario3( )
    {
        casilla = new Casilla( Casilla.TAPADA, 5, 8 );
    }

    /**
     * Verificar el método darEstado
     */
	public void testDarEstado() 
	{
		setupEscenario1( );
		assertEquals( "La casilla esta marcada", Casilla.MARCADA, casilla.darEstado() );
		setupEscenario2( );
		assertEquals( "La casilla esta marcada equivocada", Casilla.MARCADA_EQUIVOCADA, casilla.darEstado() );
		setupEscenario3( );
		assertEquals( "La casilla esta sin destapar", Casilla.TAPADA, casilla.darEstado() );
	}
    
     /**
     * Verificar el método darFila
     */
    public void testDarFila() 
    {
        setupEscenario1( );
        assertEquals( "La fila esta equivocada", 1, casilla.darFila() );
        setupEscenario2( );
        assertEquals( "La fila esta equivocada", 6, casilla.darFila() );
        setupEscenario3( );
        assertEquals( "La fila esta equivocada", 5, casilla.darFila() );
    }
    
    /**
     * Verificar el método darColumna
     */
    public void testDarColumna() 
    {
        setupEscenario1( );
        assertEquals( "La columna esta equivocada", 1, casilla.darColumna() );
        setupEscenario2( );
        assertEquals( "La columna esta equivocada", 2, casilla.darColumna() );
        setupEscenario3( );
        assertEquals( "La columna esta equivocada", 8, casilla.darColumna() );
    }

}
