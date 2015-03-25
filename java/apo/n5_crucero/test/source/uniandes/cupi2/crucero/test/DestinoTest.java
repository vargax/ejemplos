package uniandes.cupi2.crucero.test;

import javax.swing.ImageIcon;

import junit.framework.TestCase;
import uniandes.cupi2.crucero.mundo.Destino;
import uniandes.cupi2.crucero.mundo.Foto;

/**
 * Verifica la correcta implementación de la clase Destino
 */
public class DestinoTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Destino destino;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Destino
     */
    private void setupEscenario1()
    {
    	destino = new Destino("ciudad1", "pais1");
    }
    
    /**
     * Construye un nuevo Destino con imágenes
     */
    private void setupEscenario2()
    {
    	destino = new Destino("cuidad2", "pais2");
    	destino.agregarImagen(new Foto("titulo1", new ImageIcon()));
    	destino.agregarImagen(new Foto("titulo2", new ImageIcon()));
    	destino.agregarImagen(new Foto("titulo3", new ImageIcon()));    	
    }
    
    /**
     * Prueba 1 - Prueba el método constructor de la clase <br>
     * <b> Métodos a probar: </b>
     * Destino, darCiudad, darPais, darImagenes, darImagenActual
     */
    public void testDestino()
    {
    	setupEscenario1();
    	
    	assertEquals("La ciudad destino no es inicializada correctamente", "ciudad1", destino.darCiudad());
    	assertEquals("El país destino no es inicializado correctamente", "pais1", destino.darPais());
    	assertNotNull("El arreglo de imágenes debería ser inicializado", destino.darImagenes());
    	assertEquals("El tamaño del arreglo debería ser " + Destino.MAX_FOTOS, Destino.MAX_FOTOS, destino.darImagenes().length);
    	assertNull("La destino no debería tener una imagen actual", destino.darImagenActual());
    }
    
    /**
     * Prueba 2 - Prueba el método agregarImagen
     *  <b> Métodos a probar: </b>
     * agregaImagen, darImagenes
     */
    public void testAgregarImagen()
    {
    	setupEscenario1();
    	
    	destino.agregarImagen(new Foto("titulo1", new ImageIcon()));
    	assertNotNull("No agrega la imagen", destino.darImagenes()[0]);
    	destino.agregarImagen(new Foto("titulo1", new ImageIcon()));
    	assertNotNull("No agrega la imagen", destino.darImagenes()[1]);
    }
    
    /**
     * Prueba 3 - Prueba el método darImagenSiguiente <br>
     * <b> Métodos a probar: </b>
     * darImagenSiguiente
     * <b> Resultado esperado: </b>
     * 1. Si la imagen es la primera es posible avanzar
     * 2. Si la imagen es intermedia es posible avanzar
     */
    public void testDarImagenSiguiente()
    {
    	setupEscenario2();
    	
    	try 
    	{
			Foto f = destino.darImagenSiguiente();
			assertNotNull("La imagen siguiente no debería ser nula", f);
			assertEquals("Imagen siguiente incorrecta", "titulo2", f.darTitulo());
			
			f = destino.darImagenSiguiente();
			assertNotNull("La imagen siguiente no debería ser nula", f);
			assertEquals("Imagen siguiente incorrecta", "titulo3", f.darTitulo());
		} 
    	catch (Exception e) 
    	{
			fail("No se debería generar la excepción: " + e.getMessage());
		}
    }
  
    /**
     * Prueba 4 - Prueba el método darImagenSiguiente <br>
     * <b> Métodos a probar: </b>
     * darImagenSiguiente, agregarImagen
     * <b> Resultado esperado: </b>
     * Si la imagen es la última no se debe permitir avanzar
     */
    public void testDarImagenSiguienteError()
    {
    	setupEscenario1();
    	destino.agregarImagen(new Foto("titulo1", new ImageIcon()));
    	
    	try 
    	{
			destino.darImagenSiguiente();
			fail("No debería permitir el avance");
		} 
    	catch (Exception e) 
    	{
			//Debería generar excepción
		}
    }
   
    /**
     * Prueba 5 - Prueba el método darImagenAnterior <br>
     * <b> Métodos a probar: </b>
     * darImagenAnterior, darImagenSiguiente
     * <b> Resultado esperado: </b>
     * 1. Si la imagen es la última es posible retroceder
     * 2. Si la imagen es intermedia es posible retroceder
     */
    public void testDarImagenAnterior()
    {
    	setupEscenario2();
    	
    	try 
    	{
			destino.darImagenSiguiente();
			destino.darImagenSiguiente();
			
			Foto f = destino.darImagenAnterior();
			assertNotNull("La imagen anterior no debería ser nula", f);
			assertEquals("Imagen anterior incorrecta", "titulo2", f.darTitulo());
			
			f = destino.darImagenAnterior();
			assertNotNull("La imagen anterior no debería ser nula", f);
			assertEquals("Imagen anterior incorrecta", "titulo1", f.darTitulo());
		} 
    	catch (Exception e) 
    	{
			fail("No se debería generar la excepción: " + e.getMessage());
		}
    }
  
    /**
     * Prueba 4 - Prueba el método darImagenSiguiente <br>
     * <b> Métodos a probar: </b>
     * darImagenAnterior
     * <b> Resultado esperado: </b>
     * Si la imagen es la primera no se debe permitir retroceder
     */
    public void testDarImagenAnteriorError()
    {
    	setupEscenario2();
    	
    	try 
    	{
			destino.darImagenAnterior();
			fail("No debería permitir retroceder");
		} 
    	catch (Exception e) 
    	{
			//Debería generar excepción
		}
    }
 }