package uniandes.cupi2.procesadoraCafe.test;

import junit.framework.TestCase;
import uniandes.cupi2.procesadoraCafe.mundo.Cliente;

/**
 * Verifica la correcta implementación de la clase Cliente
 */
public class ClienteTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se van a realizar las pruebas
     */
    private Cliente cliente;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo Cliente
     */
    public void setupEscenario1()
    {
        cliente = new Cliente("nombre1", "nit1", "telefono1");
    }
    
    /**
     * Prueba 1 - Prueba el método constructor de la clase Cliente <br>
     * <b>Métodos a probar: </b> 
     * Cliente, darNombre, darNit, darTelefono, darCantidadVendida
     */
    public void testCliente()
    {
        setupEscenario1( );
        
        assertEquals("El nombre del cliente no es inicializado correctamente", "nombre1", cliente.darNombre( ));
        assertEquals("El NIT del cliente no es inicializado correctamente", "nit1", cliente.darNit( ));
        assertEquals("El teléfono del cliente no es inicializado correctamente", "telefono1", cliente.darTelefono( ));
        assertEquals("La cantidad de kilos vendidos al cliente no es inicializada correctamente", 0.0, cliente.darCantidadVendida( ));
    }
    
    /**
     * Prueba 2 - Prueba el método registarVentaCafe <br>
     * <b>Métodos a probar: </b> 
     * registarVentaCafe, darCantidadVendida 
     */
    public void testRegistrarVentaCafe()
    {
        setupEscenario1( );
        
        cliente.registarVentaCafe( 10.3 );
        assertEquals( "No se incrementa correctamente la cantidad vendida", 10.3, cliente.darCantidadVendida( ) );
    }
    
    /**
     * Prueba 3 - Prueba el método darNombre
     * <b>Métodos a probar: </b> 
     * darNombre
     */
    public void testDarNombre()
    {
    	setupEscenario1( );
        assertEquals("El nombre del cliente no es inicializado correctamente", "nombre1", cliente.darNombre( ));
    }
        
    /**
     * Prueba 4 - Prueba el método darNit
     * <b>Métodos a probar: </b> 
     * darNit
     */
    public void testDarNit()
    {
    	setupEscenario1( );
        assertEquals("El NIT del cliente no es inicializado correctamente", "nit1", cliente.darNit( ));
    }
    
    /**
     * Prueba 5 - Prueba el método darTelefono
     * <b>Métodos a probar: </b> 
     * darTelefono
     */
    public void testDarTelefono()
    {
    	setupEscenario1( );
        assertEquals("El teléfono del cliente no es inicializado correctamente", "telefono1", cliente.darTelefono( ));
    }
    
    /**
     * Prueba 6 - Prueba el método darCantidadVendida
     * <b>Métodos a probar: </b> 
     * darCantidadVendida
     */
    public void testDarCantidadVendida()
    {
    	setupEscenario1( );
        assertEquals("La cantidad de kilos vendidos al cliente no es inicializada correctamente", 0.0, cliente.darCantidadVendida( ));
    }
}
