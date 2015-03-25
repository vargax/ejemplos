package uniandes.cupi2.procesadoraCafe.test;

import junit.framework.TestCase;
import uniandes.cupi2.procesadoraCafe.mundo.Producto;

/**
 * Prueba la correcta implementación de la clase Producto
 */
public class ProductoTest extends TestCase
{
 // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se van a realizar las pruebas
     */
    private Producto producto;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo Producto
     */
    public void setupEscenario1()
    {
        producto = new Producto( "nombre1", "aroma1", "acidez1", "cuerpo1", 500.50, 1.3 );
    }
    
    /**
     * Prueba 1 - Prueba el método constructor de la clase Producto <br>
     * <b>Métodos a probar: </b> 
     * Producto, darNombre, darAroma, darAcidez, darCuerpo, darPrecio, darConversion, darCantidadDisponible
     */
    public void testProducto()
    {
        setupEscenario1( );
        
        assertEquals("El nombre del producto no es inicializado correctamente", "nombre1", producto.darNombre( ));
        assertEquals("El aroma del producto no es inicializado correctamente", "aroma1", producto.darAroma( ));
        assertEquals("La acidez del producto no es inicializada correctamente", "acidez1", producto.darAcidez( ));
        assertEquals("El cuerpo del producto no es inicializado correctamente", "cuerpo1", producto.darCuerpo( ));
        assertEquals("El precio del producto no es inicializado correctamente", 500.50, producto.darPrecio( ));
        assertEquals("El factor de conversión del producto no es inicializado correctamente", 1.3, producto.darConversion( ));
        assertEquals("La cantidad de kilos disponibles del producto no es inicializado correctamente", 0.0, producto.darCantidadDisponible( ));
    }
    
    /**
     * Prueba 2 - Prueba el método producir <br>
     * <b>Métodos a probar: </b> 
     * producir, darCantidadDisponible
     */
    public void testProducir()
    {
        setupEscenario1( );
        
        producto.producir( 12.3 );
        assertEquals("No se incrementa correctamente la cantidad de kilos disponibles", 12.3, producto.darCantidadDisponible( ));
    }
    
    /**
     * Prueba 3 - Prueba el método vender <br>
     * <b>Métodos a probar: </b> 
     * vender, producir, darCantidadDisponible
     * <b>Resultado esperado: </b>
     * Se vende la cantidad de kilos dada por parámetro
     */
    public void testVender()
    {
        setupEscenario1( );

        producto.producir( 12.5 );
        try
        {
            producto.vender( 10.0 );
            assertEquals("No disminuye correctamente la cantidad de kilos disponibles", 2.5, producto.darCantidadDisponible( ));
        }
        catch (Exception e) 
        {
            fail("No se debería generar la excepción: " + e.getMessage( ));
        }
    }
    
    /**
     * Prueba 3 - Prueba el método vender <br>
     * <b>Métodos a probar: </b> 
     * vender, producir
     * <b>Resultado esperado: </b>
     * Se lanza una excepción pues la cantidad de kilos no es suficiente
     */
    public void testVenderError()
    {
    	setupEscenario1( );

    	producto.producir( 9.8 );
        try
        {
            producto.vender( 10.0 );
            fail("No se debería realizar la venta, la cantidad disponible es insuficiente");
        }
        catch (Exception e) 
        {
            //El método debe lanzar excepción
        }
    }
    
    /**
     * Prueba 4 - Prueba el método darNombre <br>
     * <b>Métodos a probar: </b> 
     * darNombre
     */
    public void testDarNombre()
    {
        setupEscenario1( );
        
        assertEquals("El nombre del producto no es inicializado correctamente", "nombre1", producto.darNombre( ));
    }
    
    /**
     * Prueba 5 - Prueba el método darAroma <br>
     * <b>Métodos a probar: </b> 
     * darAroma
     */
    public void testDarAroma()
    {
        setupEscenario1( );
        
        assertEquals("El aroma del producto no es inicializado correctamente", "aroma1", producto.darAroma( ));
    }
    
    /**
     * Prueba 6 - Prueba el método darAcidez <br>
     * <b>Métodos a probar: </b> 
     * darAcidez
     */
    public void testDarAcidez()
    {
        setupEscenario1( );
        
        assertEquals("La acidez del producto no es inicializada correctamente", "acidez1", producto.darAcidez( ));
    }
    
    /**
     * Prueba 7 - Prueba el método darCuerpo <br>
     * <b>Métodos a probar: </b> 
     * darCuerpo
     */
    public void testDarCuerpo()
    {
        setupEscenario1( );
        
        assertEquals("El cuerpo del producto no es inicializado correctamente", "cuerpo1", producto.darCuerpo( ));
    }  
         
    /**
     * Prueba 8 - Prueba el método darPrecio <br>
     * <b>Métodos a probar: </b> 
     * darPrecio
     */
    public void testDarPrecio()
    {
        setupEscenario1( );
        
        assertEquals("El precio del producto no es inicializado correctamente", 500.50, producto.darPrecio( ));
    }
    
    /**
     * Prueba 9 - Prueba el método darConversion <br>
     * <b>Métodos a probar: </b> 
     * darConversion
     */
    public void testDarConversion()
    {
        setupEscenario1( );
        
        assertEquals("El factor de conversión del producto no es inicializado correctamente", 1.3, producto.darConversion( ));
    }
    
    /**
     * Prueba 10 - Prueba el método darCantidadDisponible <br>
     * <b>Métodos a probar: </b> 
     * darCantidadDisponible
     */
    public void testDarCantidadDisponible()
    {
        setupEscenario1( );
        
        assertEquals("La cantidad de kilos disponibles del producto no es inicializado correctamente", 0.0, producto.darCantidadDisponible( ));
    }
}
