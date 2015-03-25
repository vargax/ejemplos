package uniandes.cupi2.sistemaLockers.test;

import uniandes.cupi2.sistemaLockers.mundo.Casillero;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar la correcta implementación de Casillero
 */
public class CasilleroTest extends TestCase 
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
	private Casillero casillero;
	
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

	/**
	 * Crea un nuevo Casillero
	 */
	private void setupEscenario1()
	{
		casillero = new Casillero(Casillero.TIPO_1, "ID1");
	}
	
	/**
	 * Prueba 1 - Prueba el método constructor <br>
     * Métodos a probar: <br>
     * Casillero, darTipo, darId, estaAsignado
	 */
	public void testCasillero()
	{
		setupEscenario1();
		
		assertEquals("Tipo inicializado incorrectamente", Casillero.TIPO_1, casillero.darTipo());
		assertEquals("Id inicializado incorrectamente", "ID1", casillero.darId());
		assertFalse("El casillero no debe estar asignado", casillero.estaAsignado());
	}
	
	/**
	 * Prueba 2 - Prueba el método asignarCasillero <br>
	 * Métodos a probar: <br>
	 * asignarCasillero, estaAsigando
	 */
	public void testAsignarCasillero()
	{
		setupEscenario1();
		
		casillero.asignarCasillero();
		assertTrue("El casillero debería estar asignado", casillero.estaAsignado());
	}

	/**
	 * Prueba 3 - Prueba el método desasignarCasillero <br>
	 * Métodos a probar: <br>
	 * desasignarCasillero, estaAsigando
	 */
	public void testDesasignarCasillero()
	{
		setupEscenario1();
		
		casillero.desasignarCasillero();
		assertFalse("El casillero no debería estar asignado", casillero.estaAsignado());
	}
	
}
