/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_bancoDonacion
 * Autor: Catalina Rodríguez - 11-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bancoSangre.test;

import junit.framework.TestCase;
import uniandes.cupi2.bancoSangre.mundo.BancoSangre;
import uniandes.cupi2.bancoSangre.mundo.TipoSangre;

/**
 * Esta es la clase usada para verificar que los métodos de la clase BancoSangre estén correctamente implementados
 */
public class BancoSangreTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private BancoSangre bancoSangre;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo BancoSangre vacío
     */
    private void setupEscenario1( )
    {
        bancoSangre = new BancoSangre( );
    }
    
    /**
     * Construye un nuevo BancoSangre con donaciones
     */
    private void setupEscenario2()
    {
    	bancoSangre = new BancoSangre();
    	
    	bancoSangre.registarDonacion(23, BancoSangre.FEMENINO, 55.3, TipoSangre.TIPO_O, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(25, BancoSangre.MASCULINO, 60, TipoSangre.TIPO_O, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(60, BancoSangre.FEMENINO, 80, TipoSangre.TIPO_O, TipoSangre.RH_P, false);
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_B, TipoSangre.RH_N, false);    	
    }
    
    /**
     * Construye un nuevo BancoSangre con donaciones
     */
    private void setupEscenario3()
    {
    	bancoSangre = new BancoSangre();
    	
    	bancoSangre.registarDonacion(23, BancoSangre.FEMENINO, 55.3, TipoSangre.TIPO_A, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(25, BancoSangre.MASCULINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(60, BancoSangre.FEMENINO, 80, TipoSangre.TIPO_O, TipoSangre.RH_P, false);
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_O, TipoSangre.RH_N, false);    	
    }
    
    /**
     * Prueba 1 - Prueba el método constructor <br>
     * Métodos a probar: <br>
     * BancoSangre, darTipo1, darTipo2, darTipo3, darTipo4 
     */
    public void testBancoSangre( )
    {
        setupEscenario1( );
        
        assertNotNull("No creó el tipo de sangre 1", bancoSangre.darTipo1());
        assertNotNull("No creó el tipo de sangre 2", bancoSangre.darTipo2());
        assertNotNull("No creó el tipo de sangre 3", bancoSangre.darTipo3());
        assertNotNull("No creó el tipo de sangre 4", bancoSangre.darTipo4());    
    }
    
    /**
     * Prueba 2 - Prueba el método registrarDonacion <br>
     * Métodos a probar: <br>
     * registrarDonacion
     */
    public void testRegistarDonacion()
    {
    	setupEscenario1();
    	
    	String respuesta = bancoSangre.registarDonacion(25, BancoSangre.FEMENINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	assertTrue("Debería realizar la donación", respuesta.toLowerCase().contains(BancoSangre.DONACION.toLowerCase()));
    	
    	TipoSangre tipo = bancoSangre.darTipo2();
    	assertEquals("Debería incrementar el número de bolsas disponibles", 1, tipo.darCantidadDisponible());
    	
    	respuesta = bancoSangre.registarDonacion(16, BancoSangre.FEMENINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	assertTrue("No debería realizar la donación", respuesta.toLowerCase().contains(BancoSangre.ERROR_DONADOR.toLowerCase()));
    	
    	respuesta = bancoSangre.registarDonacion(25, BancoSangre.FEMENINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, true);
    	assertTrue("No debería realizar la donación", respuesta.toLowerCase().contains(BancoSangre.ERROR_DONADOR.toLowerCase()));
    	
    	respuesta = bancoSangre.registarDonacion(25, BancoSangre.FEMENINO, 49.5, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	assertTrue("No debería realizar la donación", respuesta.toLowerCase().contains(BancoSangre.ERROR_DONADOR.toLowerCase()));
    	
    	respuesta = bancoSangre.registarDonacion(25, BancoSangre.FEMENINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_P, false);
    	assertTrue("No debería realizar la donación", respuesta.toLowerCase().contains(BancoSangre.ERROR_TIPO_SANGRE.toLowerCase() + "b+"));
    }

    /**
     * Prueba 3 - Prueba del método obtenerTipoSangreMasDisponible <br>
     * Métodos a probar: <br>
     * obtenerTipoSangreMasDisponible, registarDonacion
     */
    public void testObtenerTipoSangreMasDisponible()
    {
    	setupEscenario1();
    	
    	String respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertEquals("El tipo de sangre no es el correcto", BancoSangre.NINGUNO,respuesta);
    	
    	setupEscenario2();
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("o-"));
    	
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_B, TipoSangre.RH_N, false);    	
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("b-"));
    	
    	setupEscenario3();
    	  	  	
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("a-"));
    	
    	bancoSangre.registarDonacion(25, BancoSangre.MASCULINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(60, BancoSangre.FEMENINO, 80, TipoSangre.TIPO_O, TipoSangre.RH_P, false);
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_O, TipoSangre.RH_N, false);   
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("b-"));
    	
    	bancoSangre.registarDonacion(60, BancoSangre.FEMENINO, 80, TipoSangre.TIPO_O, TipoSangre.RH_P, false);
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_O, TipoSangre.RH_N, false);   
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("o+"));
    	
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_O, TipoSangre.RH_N, false);   
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("o-"));
    }
    
    /**
     * Prueba 4 - Prueba del método obtenerTipoSangreMasSuministrada <br>
     * Métodos a probar: <br>
     * obtenerTipoSangreMasSuministrada, suministrarSangre
     */
    public void testObtenerTipoSangreMasSuministrada()
    {
    	setupEscenario1();
    	
    	String respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertEquals("El tipo de sangre no es el correcto", BancoSangre.NINGUNO,respuesta);
    	
    	setupEscenario2();
    	
    	respuesta = bancoSangre.obtenerTipoSangreMasSuministrado();
    	assertTrue("Respuesta incorrecta", respuesta.toLowerCase().contains(BancoSangre.NINGUNO.toLowerCase()));
    	
    	bancoSangre.suministrarSangre(TipoSangre.TIPO_O, TipoSangre.RH_N, 1);    	
    	respuesta = bancoSangre.obtenerTipoSangreMasSuministrado();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("o-"));
    }
    
    /**
     * Prueba 5 - Prueba del método obtenerTipoSangreMenosDisponible <br>
     * Métodos a probar: <br>
     * obtenerTipoSangreMenosDisponible, suministrarSangre
     */
    public void testObtenerTipoSangreMenosDisponible()
    {
    	setupEscenario2();
    	
    	String respuesta = bancoSangre.obtenerTipoSangreMenosDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("a-"));
    	
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_A, TipoSangre.RH_N, false);    	
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_A, TipoSangre.RH_N, false);    	
    	respuesta = bancoSangre.obtenerTipoSangreMenosDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("b-"));
    }
    
    /**
     * Prueba 6 - Prueba del método suministrarSangre<br>
     * Métodos a probar: <br>
     * suministrarSangre
     */
    public void testSuministrarSangre()
    {
    	setupEscenario2();
    	
    	String respuesta = bancoSangre.suministrarSangre(TipoSangre.TIPO_O, TipoSangre.RH_N, 1);
    	assertTrue("Debería suministrar las bolsas de sangre", respuesta.toLowerCase().contains(BancoSangre.SUMINITRAR.toLowerCase()));
    	
    	TipoSangre tipo = bancoSangre.darTipo4();
    	assertEquals("Debería incrementar el número de bolsas despachadas", 1, tipo.darCantidadSuministrada());
    	    	
    	respuesta = bancoSangre.suministrarSangre(TipoSangre.TIPO_O, TipoSangre.RH_N, 1);
    	assertTrue("No debería suministrar las bolsas de sangre", respuesta.toLowerCase().contains(BancoSangre.ERROR_CANTIDAD.toLowerCase()));
    }
}