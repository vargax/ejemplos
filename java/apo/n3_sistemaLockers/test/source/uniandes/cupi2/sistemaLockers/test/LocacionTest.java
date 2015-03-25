package uniandes.cupi2.sistemaLockers.test;

import java.util.ArrayList;

import uniandes.cupi2.sistemaLockers.mundo.Casillero;
import uniandes.cupi2.sistemaLockers.mundo.Locacion;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar la correcta implementación de Locacion
 */
public class LocacionTest extends TestCase 
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
	private Locacion locacion;
	
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

	/**
	 * Crea una nueva Locación vacía
	 */
	private void setupEscenario1()
	{
		locacion = new Locacion("prueba");
	}
	
	/**
	 * Crea una nueva Locación con casilleros
	 */
	private void setupEscenario2()
	{
		locacion = new Locacion("prueba");
		locacion.crearCasilleros(Casillero.TIPO_1, 5);
		locacion.crearCasilleros(Casillero.TIPO_2, 10);
	}
	
	/**
	 * Prueba 1 - Prueba el método constructor <br>
	 * Métodos a probar: <br>
	 * Locacion, darNombre, darCasilleros, darNumCasilleros
	 */
	public void testLocacion()
	{
		setupEscenario1();
		
		assertEquals("Nombre de la locación inicializado incorrectamente", "prueba", locacion.darNombre().toLowerCase());
		assertNotNull("La lista de casillero debería ser inicializada", locacion.darCasilleros());
		assertTrue("El número de casilleros debería ser cero", locacion.darNumCasilleros()==0);
	}
	
	/**
	 * Prueba 2 - Prueba el método crearCasilleros <br>
	 * Métodos a probar: <br>
	 * crearCasilleros, darNumCasilleros, darCasilleros
	 */
	public void testCrearCasilleros()
	{
		setupEscenario1();
		
		locacion.crearCasilleros(Casillero.TIPO_3, 3);
		
		assertEquals("El número de casilleros es incorrecto", 3, locacion.darNumCasilleros());
		
		Casillero c = (Casillero)locacion.darCasilleros().get(0);
		assertEquals("Error al crear el casillero", Casillero.TIPO_3, c.darTipo());
		assertEquals("Error al crear el casillero", "prueba1", c.darId().toLowerCase());
		
		c = (Casillero)locacion.darCasilleros().get(1);
		assertEquals("Error al crear el casillero", Casillero.TIPO_3, c.darTipo());
		assertEquals("Error al crear el casillero", "prueba2", c.darId().toLowerCase());
		
		c = (Casillero)locacion.darCasilleros().get(2);
		assertEquals("Error al crear el casillero", Casillero.TIPO_3, c.darTipo());
		assertEquals("Error al crear el casillero", "prueba3", c.darId().toLowerCase());	
	}

	/**
	 * Prueba 3 - Prueba el método buscarCasilleros <br>
	 * Métodos a probar: <br>
	 * buscarCasilleros
	 */
	public void testBuscarCasilleros()
	{
		setupEscenario2();
		
		ArrayList respuesta = locacion.buscarCasilleros(Casillero.TIPO_1);
		assertEquals("El número de casillero encontrados es incorrecto", 5, respuesta.size());
		for (int i = 0; i < respuesta.size(); i++)
		{
			String id = (String)respuesta.get(i);
			assertTrue("Respuesta incorrecta", id.contains("prueba"));
			int num = Integer.parseInt(id.substring(6).trim());
			assertTrue("Respuesta incorrecta", num>=1);
			assertTrue("Respuesta incorrecta", num<=5);
		}
		
		respuesta = locacion.buscarCasilleros(Casillero.TIPO_2);
		assertEquals("El número de casillero encontrados es incorrecto", 10, respuesta.size());
		for (int i = 0; i < respuesta.size(); i++)
		{
			String id = (String)respuesta.get(i);
			assertTrue("Respuesta incorrecta", id.contains("prueba"));
			int num = Integer.parseInt(id.substring(6).trim());
			assertTrue("Respuesta incorrecta", num>=6);
			assertTrue("Respuesta incorrecta", num<=15);
		}
		
		respuesta = locacion.buscarCasilleros(Casillero.TIPO_3);
		assertEquals("El número de casillero encontrados es incorrecto", 0, respuesta.size());
	}
	
	/**
	 * Prueba 4 - Prueba el método asignarCasillero <br>
	 * Métodos a probar: <br>
	 * asignarCasillero
	 */
	public void testAsignarCasillero()
	{
		setupEscenario2();
		
		locacion.asignarCasillero("prueba1");
		Casillero c = locacion.darCasillero(0);
		assertTrue("El casillero debería estar asignado", c.estaAsignado());
		
		locacion.asignarCasillero("prueba5");
		c = locacion.darCasillero(4);
		assertTrue("El casillero debería estar asignado", c.estaAsignado());
		
		locacion.asignarCasillero("prueba15");
		c = locacion.darCasillero(14);
		assertTrue("El casillero debería estar asignado", c.estaAsignado());		
	}
	
	/**
	 * Prueba 5 - Prueba el método desasignarCasillero <br>
	 * Métodos a probar: <br>
	 * desasignarCasillero
	 */
	public void testDesasignarCasillero()
	{
		setupEscenario2();
		
		locacion.asignarCasillero("prueba1");
		locacion.asignarCasillero("prueba5");
		locacion.asignarCasillero("prueba15");
		
		locacion.desasignarCasillero("prueba1");
		Casillero c = locacion.darCasillero(0);
		assertFalse("El casillero no debería estar asignado", c.estaAsignado());
		
		locacion.desasignarCasillero("prueba5");
		c = locacion.darCasillero(4);
		assertFalse("El casillero no debería estar asignado", c.estaAsignado());
		
		locacion.desasignarCasillero("prueba15");
		c = locacion.darCasillero(14);
		assertFalse("El casillero no debería estar asignado", c.estaAsignado());		
	}
	
	/**
	 * Prueba 6 - Prueba el método calcularPorcentajeAsignado <br>
	 * Métodos a probar: <br>
	 * calcularPorcentajeAsignado, asignarCasillero, desasignarCasillero
	 */
	public void testCalcularPorcentajeAsignado()
	{
		setupEscenario2();
		double porcentaje = locacion.calcularPorcentajeAsignado();
		assertTrue("Cálculo incorrecto del porcentaje", porcentaje == 0.0);
		
		locacion.asignarCasillero("prueba1");
		locacion.asignarCasillero("prueba5");
		locacion.asignarCasillero("prueba15");
		locacion.asignarCasillero("prueba3");
		locacion.asignarCasillero("prueba10");
		locacion.asignarCasillero("prueba7");
		porcentaje = locacion.calcularPorcentajeAsignado();
		assertTrue("Cálculo incorrecto del porcentaje", porcentaje == 40.0);
				
		locacion.desasignarCasillero("prueba5");
		locacion.desasignarCasillero("prueba1");
		locacion.desasignarCasillero("prueba7");
		porcentaje = locacion.calcularPorcentajeAsignado();
		assertTrue("Cálculo incorrecto del porcentaje", porcentaje == 20.0);
	}
	
	/**
	 * Prueba 7 - Prueba el método consultarTipoPopular <br>
	 * Métodos a probar: <br>
	 * consultarTipoPopular, asignarCasillero
	 */
	public void testConsultarTipoPopular()
	{
		setupEscenario1();
		String tipo = locacion.consultarTipoPopular();
		assertTrue("Respuesta incorrecta", tipo.equals(Casillero.NINGUNO));
		
		setupEscenario2();
		locacion.asignarCasillero("prueba10");
		locacion.asignarCasillero("prueba12");
		tipo = locacion.consultarTipoPopular();
		assertTrue("Respuesta incorrecta", tipo.equals(Casillero.TIPO_2));
		
		locacion.asignarCasillero("prueba1");
		locacion.asignarCasillero("prueba2");
		tipo = locacion.consultarTipoPopular();
		assertTrue("Respuesta incorrecta", tipo.equals(Casillero.TIPO_1));
		
	}
	
	/**
	 * Prueba 8 - Prueba el método tieneTodoTipo <br>
	 * Métodos a probar: <br>
	 * tieneTodoTipo, crearCasilleros
	 */
	public void testTieneTodoTipo()
	{
		setupEscenario2();
		
		boolean todoTipo = locacion.tieneTodoTipo();
		assertFalse("Respuesta incorrecta. La locación no tiene de todos los tipos de casilleros", todoTipo);
		
		locacion.crearCasilleros(Casillero.TIPO_3, 1);
		todoTipo = locacion.tieneTodoTipo();
		assertTrue("Respuesta incorrecta. La locación tiene de todos los tipos de casilleros", todoTipo);
	}
	
	/**
	 * Prueba 9 - Prueba el método darNumCasillerosDesocupados <br>
	 * Métodos a probar: <br>
	 * darNumCasillerosDesocupados, crearCasilleros, asignarCasillero
	 */
	public void testDarNumCasillerosDesocupados()
	{
		setupEscenario2();
		
		int numCasilleros = locacion.darNumCasillerosDesocupados(Casillero.TIPO_1);
		assertEquals("Respuesta incorrecta", 5, numCasilleros);
		numCasilleros = locacion.darNumCasillerosDesocupados(Casillero.TIPO_2);
		assertEquals("Respuesta incorrecta", 10, numCasilleros);
		numCasilleros = locacion.darNumCasillerosDesocupados(Casillero.TIPO_3);
		assertEquals("Respuesta incorrecta", 0, numCasilleros);
		
		locacion.crearCasilleros(Casillero.TIPO_3, 2);
		locacion.asignarCasillero("prueba1");
		locacion.asignarCasillero("prueba5");
		locacion.asignarCasillero("prueba15");
		locacion.asignarCasillero("prueba3");
		locacion.asignarCasillero("prueba10");
		
		numCasilleros = locacion.darNumCasillerosDesocupados(Casillero.TIPO_1);
		assertEquals("Respuesta incorrecta", 2, numCasilleros);
		numCasilleros = locacion.darNumCasillerosDesocupados(Casillero.TIPO_2);
		assertEquals("Respuesta incorrecta", 8, numCasilleros);
		numCasilleros = locacion.darNumCasillerosDesocupados(Casillero.TIPO_3);
		assertEquals("Respuesta incorrecta", 2, numCasilleros);
	}

}
