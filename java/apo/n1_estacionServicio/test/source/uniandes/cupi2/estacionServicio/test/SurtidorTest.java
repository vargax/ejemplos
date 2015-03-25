package uniandes.cupi2.estacionServicio.test;

import uniandes.cupi2.estacionServicio.mundo.Surtidor;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Surtidor estén correctamente implementados
 */
public class SurtidorTest extends TestCase 
{
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Surtidor surtidor;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo surtidor vacío
     * 
     */
    private void setupEscenario1()
    {
    	surtidor = new Surtidor();
    	surtidor.inicializar("Combustible1", 5000);
    }
    
    /**
     * Prueba 1 - Prueba el método inicializar
     * Métodos a probar: <br>
     * inicializar, darCostoGalon, darDineroRecaudado, darNumeroGalonesVendidos, darTipoCombustible
     */
    public void testInicializar()
    {
    	setupEscenario1();
    	
    	assertTrue("No se crea con el costo del galón dado por parámetro", surtidor.darCostoGalon() == 5000);
    	assertTrue("No inicializa el dinero recaudado en cero", surtidor.darDineroRecaudado() == 0);
    	assertTrue("No inicializa el número de galones vendido en cero", surtidor.darNumeroGalonesVendidos() == 0);
    	assertTrue("No se crea con el tipo dado por parámetro", surtidor.darTipoCombustible().equals("Combustible1"));
    }
    
    /**
     * Prueba 2 - Prueba el método registrarVentaParticular
     * Métodos a probar: <br>
     * registrarVentaParticular, darDineroRecaudado, darNumeroGalonesVendidos
     */
    public void testRegistrarVentaParticular()
    {
    	setupEscenario1();
    	
    	surtidor.registarVentaParticular(25000);
    	
    	assertTrue("No incrementa el dinero recaudado correctamente", surtidor.darDineroRecaudado() == 25000);
    	assertTrue("No aumenta el número de galones vendido correctamente", surtidor.darNumeroGalonesVendidos() == 5);
    	
    	surtidor.registarVentaParticular(10000);
    	
    	assertTrue("No incrementa el dinero recaudado correctamente", surtidor.darDineroRecaudado() == 35000);
    	assertTrue("No aumenta el número de galones vendido correctamente", surtidor.darNumeroGalonesVendidos() == 7);
    }
    
    /**
     * Prueba 3 - Prueba el método registrarVentaServicioPublico
     * Métodos a probar: <br>
     * registrarVentaServicioPublico, darDineroRecaudado, darNumeroGalonesVendidos
     */
    public void testRegistrarVentaPublico()
    {
    	setupEscenario1();
    	
    	double galonesVendidos= surtidor.registrarVentaServicioPublico(25000);
    	
    	double numGalones = (double)25000/4750;	
    	assertTrue("No incrementa el dinero recaudado correctamente", surtidor.darDineroRecaudado() == 25000);
    	assertEquals("El número de galones vendidos es incorrecto", numGalones, galonesVendidos);
    	assertEquals("No incrementa el número de galones vendidos totales correctamente", numGalones, surtidor.darNumeroGalonesVendidos());
    	    	
    	galonesVendidos = surtidor.registrarVentaServicioPublico(10000);
    	
    	numGalones = (double)10000/4750;
    	double total = (double)25000/4750 + numGalones;
    	assertTrue("No incrementa el dinero recaudado correctamente", surtidor.darDineroRecaudado() == 35000);
    	assertEquals("El número de galones vendido es incorrecto", numGalones, galonesVendidos);
    	assertEquals("No incrementa el número de galones vendidos totales correctamente", total, surtidor.darNumeroGalonesVendidos());
    	
    }
    
    /**
     * Prueba 4 - Prueba el método reiniciar
     * Métodos a probar: <br>
     * reiniciar, darDineroRecaudado, darNumeroGalonesVendidos
     */
    public void testReiniciar()
    {
    	setupEscenario1();
    	
    	surtidor.registarVentaParticular(12);
    	surtidor.reiniciar();
    	
    	assertTrue("No reinicia el dinero recaudado en cero", surtidor.darDineroRecaudado() == 0);
    	assertTrue("No reinicia el número de galones vendido en cero", surtidor.darNumeroGalonesVendidos() == 0);   	
    }
}
