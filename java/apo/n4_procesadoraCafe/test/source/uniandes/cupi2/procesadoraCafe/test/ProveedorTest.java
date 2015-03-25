package uniandes.cupi2.procesadoraCafe.test;

import junit.framework.TestCase;
import uniandes.cupi2.procesadoraCafe.mundo.Proveedor;

/**
 * Prueba la correcta implementación de la clase Proveedor
 */
public class ProveedorTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se van a realizar las pruebas
     */
    private Proveedor proveedor;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo Proveedor
     */
    public void setupEscenario1()
    {
        proveedor = new Proveedor( "nombre1", 123456789, "telefono1", "origen1", 100 );
    }
    
    /**
     * Prueba 1 - Prueba el método constructor de la clase Proveedor <br>
     * <b>Métodos a probar: </b> 
     * Proveedor, darNombre, darCedula, darTelefono, darOrigen, darPrecio
     */
    public void testProveedor()
    {
    	setupEscenario1( );
        
        assertEquals( "El nombre del proveedor no es inicializado correctamente", "nombre1", proveedor.darNombre( ));
        assertEquals( "La cédula del proveedor no es inicializada correctamente", 123456789, proveedor.darCedula( ));
        assertEquals( "El teléfono del proveedor no es inicializado correctamente", "telefono1", proveedor.darTelefono( ));
        assertEquals( "El origen del café del proveedor no es inicializado correctamente", "origen1", proveedor.darOrigen( ));
        assertEquals( "El precio de venta del café del proveedor no es inicializado correctamente", 100.0, proveedor.darPrecio( ));        
    }
   
    /**
     * Prueba 2 - Prueba el método darNombre <br>
     * <b>Métodos a probar: </b> 
     * darNombre
     */
    public void testDarNombre()
    {
        setupEscenario1( );
        
        assertEquals( "El nombre del proveedor no es inicializado correctamente", "nombre1", proveedor.darNombre( ));
    }
    
    /**
     * Prueba 3 - Prueba el método darCedula <br>
     * <b>Métodos a probar: </b> 
     * darCedula
     */
    public void testDarCedula()
    {
        setupEscenario1( );
        
        assertEquals( "La cédula del proveedor no es inicializada correctamente", 123456789, proveedor.darCedula( ));        
    }
    
    /**
     * Prueba 4 - Prueba el método darTelefono <br>
     * <b>Métodos a probar: </b> 
     * darTelefono
     */
    public void testDarTelefono()
    {
        setupEscenario1( );
        
        assertEquals( "El teléfono del proveedor no es inicializado correctamente", "telefono1", proveedor.darTelefono( ));        
    }
    
    /**
     * Prueba 5 - Prueba el método darOrigen <br>
     * <b>Métodos a probar: </b> 
     * darOrigen
     */
    public void testDarOrigen()
    {
        setupEscenario1( );
        
        assertEquals( "El origen del café del proveedor no es inicializado correctamente", "origen1", proveedor.darOrigen( ));
     }
    
    /**
     * Prueba 6 - Prueba el método darPrecio <br>
     * <b>Métodos a probar: </b> 
     * darPrecio
     */
    public void testDarPrecio()
    {
        setupEscenario1( );
        
        assertEquals( "El precio de venta del café del proveedor no es inicializado correctamente", 100.0, proveedor.darPrecio( ));        
    }
}
