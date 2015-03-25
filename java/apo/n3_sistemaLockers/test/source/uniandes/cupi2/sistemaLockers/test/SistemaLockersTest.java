/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_sistemaLockers
 * Autor: Catalina Rodriguez - 23-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sistemaLockers.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.sistemaLockers.mundo.Casillero;
import uniandes.cupi2.sistemaLockers.mundo.Locacion;
import uniandes.cupi2.sistemaLockers.mundo.SistemaLockers;

/**
 * Esta es la clase usada para verificar que los métodos de la clase SistemaLockers estén correctamente implementados
 */
public class SistemaLockersTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private SistemaLockers sistemaLockers;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo SistemaLockers vacío
     *  
     */
    private void setupEscenario1( )
    {
        sistemaLockers = new SistemaLockers( );
    }
    
    /**
     * Construye un nuevo SistemaLockers con casilleros
     */
    private void setupEscenario2()
    {
    	sistemaLockers = new SistemaLockers();
    	
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_1, Casillero.TIPO_3, 15);
    	sistemaLockers.asignarCasillero(Locacion.NOMBRE_LOC_1, "ML3");
    	
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_5, Casillero.TIPO_1, 5); 	
    }

    /**
     * Prueba 1 - Prueba el método constructor <br>
     * Métodos a probar: <br>
     * SistemaLockers, darLocacion
     */
    public void testSistemaLockers( )
    {
        setupEscenario1( );
        
        for (int i = 0; i < SistemaLockers.NUM_LOCACIONES; i++) 
        {
			assertNotNull("No inicializa la locación " + (i+1), sistemaLockers.darLocacion(i));
		}
        
        assertEquals("Locación 1 incorrecta", Locacion.NOMBRE_LOC_1, sistemaLockers.darLocacion(0).darNombre());
        assertEquals("Locación 2 incorrecta", Locacion.NOMBRE_LOC_2, sistemaLockers.darLocacion(1).darNombre());
        assertEquals("Locación 3 incorrecta", Locacion.NOMBRE_LOC_3, sistemaLockers.darLocacion(2).darNombre());
        assertEquals("Locación 4 incorrecta", Locacion.NOMBRE_LOC_4, sistemaLockers.darLocacion(3).darNombre());
        assertEquals("Locación 5 incorrecta", Locacion.NOMBRE_LOC_5, sistemaLockers.darLocacion(4).darNombre());       
    }
    
    /**
     * Prueba 2 - Prueba el método crearCasilleros <br>
     * Métodos a probar: <br>
     * crearCasilleros, darLocacion
     */
    public void testCrearCasilleros()
    {
    	setupEscenario1();
    	
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_1, Casillero.TIPO_1, 3);
    	assertTrue("No crea correctamente los casilleros", sistemaLockers.darLocacion(0).darNumCasilleros() == 3);
    	assertTrue("La locación no debería tener casilleros", sistemaLockers.darLocacion(1).darNumCasilleros() == 0);
    	assertTrue("La locación no debería tener casilleros", sistemaLockers.darLocacion(2).darNumCasilleros() == 0);
    	assertTrue("La locación no debería tener casilleros", sistemaLockers.darLocacion(3).darNumCasilleros() == 0);
    	assertTrue("La locación no debería tener casilleros", sistemaLockers.darLocacion(4).darNumCasilleros() == 0);
    	
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_4, Casillero.TIPO_3, 5);
    	assertTrue("Número incorrecto de casilleros", sistemaLockers.darLocacion(0).darNumCasilleros() == 3);
    	assertTrue("La locación no debería tener casilleros", sistemaLockers.darLocacion(1).darNumCasilleros() == 0);
    	assertTrue("La locación no debería tener casilleros", sistemaLockers.darLocacion(2).darNumCasilleros() == 0);
    	assertTrue("No crea correctamente los casilleros", sistemaLockers.darLocacion(3).darNumCasilleros() == 5);
    	assertTrue("La locación no debería tener casilleros", sistemaLockers.darLocacion(4).darNumCasilleros() == 0);
    }
    
    /**
     * Prueba 3 - Prueba el método buscarCasilleros <br>
     * Métodos a probar: <br>
     * buscarCasilleros
     */
    public void testBuscarCasilleros()
    {
    	setupEscenario2();
    	
    	ArrayList resultado = sistemaLockers.buscarCasilleros(Locacion.NOMBRE_LOC_1, Casillero.TIPO_1);
    	assertTrue("La lista resultado no debería tener elementos", resultado.isEmpty());
    	
    	resultado = sistemaLockers.buscarCasilleros(Locacion.NOMBRE_LOC_1, Casillero.TIPO_3);
    	assertTrue("La lista resultado no tiene un número correcto de elementos", resultado.size() == 14);
    	for (int i = 0; i < resultado.size(); i++)
		{
			String id = (String)resultado.get(i);
			assertTrue("Respuesta incorrecta", id.contains(Locacion.NOMBRE_LOC_1));
		}
    }
    
    /**
     * Prueba 4 - Prueba el método asignarCasillero <br>
     * Métodos a probar: <br>
     * asignarCasillero, crearCasilleros, calcularPorcentajeAsignado
     */
    public void testAsignarCasillero()
    {
    	setupEscenario1();
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_2, Casillero.TIPO_2, 5);
    	sistemaLockers.asignarCasillero(Locacion.NOMBRE_LOC_2, "O1");
    	
    	assertTrue("No asigna el casillero correctamente", sistemaLockers.calcularPorcentajeAsignado(Locacion.NOMBRE_LOC_2) == 20.0);
    	assertTrue("No debería asignar casilleros a otra locación", sistemaLockers.calcularPorcentajeAsignado(Locacion.NOMBRE_LOC_1) == 0);
    	assertTrue("No debería asignar casilleros a otra locación", sistemaLockers.calcularPorcentajeAsignado(Locacion.NOMBRE_LOC_3) == 0);
    	assertTrue("No debería asignar casilleros a otra locación", sistemaLockers.calcularPorcentajeAsignado(Locacion.NOMBRE_LOC_4) == 0);
    	assertTrue("No debería asignar casilleros a otra locación", sistemaLockers.calcularPorcentajeAsignado(Locacion.NOMBRE_LOC_5) == 0);
    }
    
    /**
     * Prueba 5 - Prueba el método desasignarCasillero <br>
     * Métodos a probar: <br>
     * desasignarCasillero, asignarCasillero, crearCasilleros, calcularPorcentajeAsignado
     */
    public void testDesasignarCasillero()
    {
    	setupEscenario2();
    	sistemaLockers.desasignarCasillero(Locacion.NOMBRE_LOC_1, "ML3");
    	
    	assertTrue("No des-asigna el casillero correctamente", sistemaLockers.calcularPorcentajeAsignado("ML") == 0.0);
    }
    
    /**
     * Prueba 6 - Prueba el método calcularPorcentajeAsignado <br>
     * Métodos a probar: <br>
     * calcularPorcentajeAsignado
     */
    public void testCalcularPorcentajeAsignado()
    {
    	setupEscenario2();
    	double porcentaje = sistemaLockers.calcularPorcentajeAsignado(Locacion.NOMBRE_LOC_5);
    	assertTrue("Porcentaje incorrecto", porcentaje == 0.0);
    	
    	porcentaje = sistemaLockers.calcularPorcentajeAsignado(Locacion.NOMBRE_LOC_1);
    	assertTrue("Porcentaje incorrecto", porcentaje > 6.6 && porcentaje < 6.7 );	
    }
    
    /**
     * Prueba 7 - Prueba el método calcularPorcentajeTotalAsignado <br>
     * Métodos a probar: <br>
     * calcularPorcentajeTotalAsignado
     */
    public void testCalcularPorcentajeTotalAsignado()
    {
    	setupEscenario2();
    	double porcentaje = sistemaLockers.calcularPorcentajeTotalAsignado();
    	assertTrue("Porcentaje incorrecto", porcentaje == 5.0);
    	
    	sistemaLockers.asignarCasillero(Locacion.NOMBRE_LOC_5, "Caneca1");
    	sistemaLockers.asignarCasillero(Locacion.NOMBRE_LOC_5, "Caneca2");
    	sistemaLockers.asignarCasillero(Locacion.NOMBRE_LOC_5, "Caneca3");
    	porcentaje = sistemaLockers.calcularPorcentajeTotalAsignado();
    	assertTrue("Porcentaje incorrecto", porcentaje == 20.0 );
    }
    
    /**
     * Prueba 8 - Prueba el método consultarLocacionPopular <br>
     * Métodos a probar: <br>
     * consultarLocacionPopular
     */
    public void testConsultarLocacionPopular()
    {
    	setupEscenario2();
    	String locacion = sistemaLockers.consultarLocacionPopular();
    	assertTrue("Locación incorrecta", locacion.equalsIgnoreCase(Locacion.NOMBRE_LOC_1));
    	
    	sistemaLockers.asignarCasillero(Locacion.NOMBRE_LOC_5, "Caneca1");
    	sistemaLockers.asignarCasillero(Locacion.NOMBRE_LOC_5, "Caneca2");
    	locacion = sistemaLockers.consultarLocacionPopular();
    	assertTrue("Locación incorrecta", locacion.equalsIgnoreCase(Locacion.NOMBRE_LOC_5));
    }
    
    /**
     * Prueba 9 - Prueba el método consultarTipoPopular <br>
     * Métodos a probar: <br>
     * consultarTipoPopular
     */
    public void testConsultarTipoPopular()
    {
    	setupEscenario2();
    	String tipo = sistemaLockers.consultarTipoPopular(Locacion.NOMBRE_LOC_5);
    	//assertTrue("Tipo incorrecto", tipo.equalsIgnoreCase(Casillero.TIPO_1));
    	
    	tipo = sistemaLockers.consultarTipoPopular(Locacion.NOMBRE_LOC_1);
    	assertTrue("Tipo incorrecto", tipo.equalsIgnoreCase(Casillero.TIPO_3));
    	
    	tipo = sistemaLockers.consultarTipoPopular(Locacion.NOMBRE_LOC_2);
    	assertTrue("Tipo incorrecto", tipo.equalsIgnoreCase(Casillero.NINGUNO));
    }
    
    /**
     * Prueba 10 - Prueba el método darLocacionConMasCasillerosDesocupadosTipo <br>
     * Métodos a probar: <br>
     * darLocacionConMasCasillerosDesocupadosTipo, crearCasilleros
     */
    public void testDarLocacionConMasCasillerosDesocupadosTipo()
    {
    	setupEscenario2();
    	String locacion = sistemaLockers.darLocacionConMasCasillerosDesocupadosTipo(Casillero.TIPO_1);
    	assertTrue("Locación incorrecto", locacion.equalsIgnoreCase(Locacion.NOMBRE_LOC_5));
    	
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_1, Casillero.TIPO_1, 5); 	
    	locacion = sistemaLockers.darLocacionConMasCasillerosDesocupadosTipo(Casillero.TIPO_1);
    	assertTrue("Locación incorrecto", locacion.equalsIgnoreCase(Locacion.NOMBRE_LOC_1));
    	
    	locacion = sistemaLockers.darLocacionConMasCasillerosDesocupadosTipo(Casillero.TIPO_3);
    	assertTrue("Locación incorrecto", locacion.equalsIgnoreCase(Locacion.NOMBRE_LOC_1));
    	
    	locacion = sistemaLockers.darLocacionConMasCasillerosDesocupadosTipo(Casillero.TIPO_2);
    	assertTrue("Locación incorrecto", locacion.equalsIgnoreCase(Casillero.NINGUNO));
    }
    

    /**
     * Prueba 11 - Prueba el método darNumLocacionesTodoTipo <br>
     * Métodos a probar: <br>
     * darNumLocacionesTodoTipo, crearCasilleros
     */
    public void testDarNumLocacionesTodoTipo()
    {
    	setupEscenario2();
    	
    	int numLocaciones = sistemaLockers.darNumLocacionesTodoTipo();
    	assertEquals("Respuesta incorrecta incorrecto", 0, numLocaciones);

    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_1, Casillero.TIPO_1, 5); 
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_1, Casillero.TIPO_2, 1); 
    	numLocaciones = sistemaLockers.darNumLocacionesTodoTipo();
    	assertEquals("Respuesta incorrecta incorrecto", 1, numLocaciones);
    	
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_5, Casillero.TIPO_3, 5); 
    	sistemaLockers.crearCasilleros(Locacion.NOMBRE_LOC_5, Casillero.TIPO_2, 1); 
    	numLocaciones = sistemaLockers.darNumLocacionesTodoTipo();
    	assertEquals("Respuesta incorrecta incorrecto", 2, numLocaciones);
    }
}