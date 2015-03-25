/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_estacionServicio
 * Autor: Catalina Rodriguez - 02-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.estacionServicio.test;

import junit.framework.TestCase;
import uniandes.cupi2.estacionServicio.mundo.EstacionServicio;

/**
 * Esta es la clase usada para verificar que los métodos de la clase EstacionServicio estén correctamente implementados
 */
public class EstacionServicioTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private EstacionServicio estacionServicio;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva EstacionServicio vacía
     */
    private void setupEscenario1( )
    {
        estacionServicio = new EstacionServicio( );
        estacionServicio.inicializar();
    }

    /**
     * Construye una nueva estación de servicio con ventas
     */
    private void setupEscenario2()
    {
    	estacionServicio = new EstacionServicio();
    	estacionServicio.inicializar();
    	
    	estacionServicio.registrarVentaParticularSurtidor1(7800);
    	estacionServicio.registrarVentaPublicoSurtidor2(18050);
    	estacionServicio.registrarVentaParticularSurtidor3(67000);
    }
    
    /**
     * Prueba 1 - Prueba el método inicializar
     * Métodos a probar: <br>
     * inicializar, darSurtidor1, darSurtidor2, darSurtidor3
     */
    public void testInicializar( )
    {
        setupEscenario1( );
        
        assertNotNull("No creó el Surtidor 1", estacionServicio.darSurtidor1());
        assertNotNull("No creó el Surtidor 2", estacionServicio.darSurtidor2());
        assertNotNull("No creó el surtidor 3", estacionServicio.darSurtidor3());
    }
    
    /**
     * Prueba 2 - Prueba el método registrarVentaParticularSurtidor1<br>
     * Métodos a probar: <br>
     * registrarVentaParticularSurtidor1, darSurtidor1
     */
    public void testRegistrarVentaParticularSurtidor1()
    {
    	setupEscenario1();
    	
    	estacionServicio.registrarVentaParticularSurtidor1(15600);
    	
    	assertEquals("No registró la venta correctamente", 2.0, estacionServicio.darSurtidor1().darNumeroGalonesVendidos());
    	assertEquals("No registró la venta correctamente", 15600.0, estacionServicio.darSurtidor1().darDineroRecaudado());	
    }

    /**
     * Prueba 3 - Prueba el método registrarVentaParticularSurtidor2<br>
     * Métodos a probar: <br>
     * registrarParticularVentaSurtidor2, darSurtidor2
     */
    public void testRegistrarVentaParticularSurtidor2()
    {
    	setupEscenario1();
    	
    	estacionServicio.registrarVentaParticularSurtidor2(9500);
    	
    	assertEquals("No registró la venta correctamente", 1.0, estacionServicio.darSurtidor2().darNumeroGalonesVendidos());
    	assertEquals("No registró la venta correctamente", 9500.0, estacionServicio.darSurtidor2().darDineroRecaudado());	
    }

    /**
     * Prueba 4 - Prueba el método registrarVentaParticularSurtidor3<br>
     * Métodos a probar: <br>
     * registrarVentaParticularSurtidor3, darSurtidor3
     */
    public void testRegistrarVentaParticularSurtidor3()
    {
    	setupEscenario1();
    	
    	estacionServicio.registrarVentaParticularSurtidor3(20100);
    	
    	assertEquals("No registró la venta correctamente", 3.0, estacionServicio.darSurtidor3().darNumeroGalonesVendidos());
    	assertEquals("No registró la venta correctamente", 20100.0, estacionServicio.darSurtidor3().darDineroRecaudado());	
    }
    
    /**
     * Prueba 5 - Prueba el método registrarVentaPublicoSurtidor1<br>
     * Métodos a probar: <br>
     * registrarVentaPublicoSurtidor1, darSurtidor1
     */
    public void testRegistrarVentaPublicoSurtidor1()
    {
    	setupEscenario1();
    	
    	estacionServicio.registrarVentaPublicoSurtidor1(18525);
    	
    	assertEquals("No registró la venta correctamente", 2.5, estacionServicio.darSurtidor1().darNumeroGalonesVendidos());
    	assertEquals("No registró la venta correctamente", 18525.0, estacionServicio.darSurtidor1().darDineroRecaudado());	
    }

    /**
     * Prueba 6 - Prueba el método registrarVentaPublicoSurtidor2<br>
     * Métodos a probar: <br>
     * registrarVentaPublicoSurtidor2, darSurtidor2
     */
    public void testRegistrarVentaPublicoSurtidor2()
    {
    	setupEscenario1();
    	
    	estacionServicio.registrarVentaPublicoSurtidor2(9025);
    	
    	assertEquals("No registró la venta correctamente", 1.0, estacionServicio.darSurtidor2().darNumeroGalonesVendidos());
    	assertEquals("No registró la venta correctamente", 9025.0, estacionServicio.darSurtidor2().darDineroRecaudado());	
    }

    /**
     * Prueba 7 - Prueba el método registrarVentaPublicoSurtidor2<br>
     * Métodos a probar: <br>
     * registrarVentaPublicoSurtidor2, darSurtidor3
     */
    public void testRegistrarVentaPublicoSurtidor3()
    {
    	setupEscenario1();
    	
    	estacionServicio.registrarVentaPublicoSurtidor3(63650);
    	
    	assertEquals("No registró la venta correctamente", 10.0, estacionServicio.darSurtidor3().darNumeroGalonesVendidos());
    	assertEquals("No registró la venta correctamente", 63650.0, estacionServicio.darSurtidor3().darDineroRecaudado());	
    }
    
    /**
     * Prueba 8 - Prueba el método darTotalGalones <br>
     * Métodos a probar: <br>
     * darTotalGalones
     */
    public void testDarTotalGalones()
    {
        setupEscenario2( );
        
        assertTrue( "No calculo correctamente el número total de galones vendidos", estacionServicio.darTotalGalones() == 13 );
    }

    /**
     * Prueba 9 - Prueba el método darTotalDineroRecaudado <br>
     * Métodos a probar: <br>
     * darTotalDineroRecaudado
     */
    public void testDarTotalDineroRecaudado( )
    {
        setupEscenario2( );

        assertEquals( "No calculo correctamente el dinero total recaudado", 92850.0, estacionServicio.darTotalDineroRecaudado() );
    }

    /**
     * Prueba 10 - Prueba el método reiniciar <br>
     * Métodos a probar: <br>
     * reiniciar, darSurtidor1, darSurtidor2, darSurtidor3
     */
    public void testReiniciar( )
    {
        setupEscenario2( );

        estacionServicio.reiniciar();

        assertTrue( "No reinició el dinero recaudado por ventas del surtidor 1", estacionServicio.darSurtidor1().darDineroRecaudado() == 0 );
        assertTrue( "No reinició el número de galones vendidos del surtidor 1", estacionServicio.darSurtidor1().darNumeroGalonesVendidos() == 0 );

        assertTrue( "No reinició el dinero recaudado por ventas del surtidor 2", estacionServicio.darSurtidor2().darDineroRecaudado() == 0 );
        assertTrue( "No reinició el número de galones vendidos del surtidor 2", estacionServicio.darSurtidor2().darNumeroGalonesVendidos() == 0 );

        assertTrue( "No reinició el dinero recaudado por ventas del surtidor 3", estacionServicio.darSurtidor3().darDineroRecaudado() == 0 );
        assertTrue( "No reinició el número de galones vendidos del surtidor 3", estacionServicio.darSurtidor3().darNumeroGalonesVendidos() == 0 );
    }
}