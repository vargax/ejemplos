package uniandes.cupi2.bancoSangre.test;

import junit.framework.TestCase;
import uniandes.cupi2.bancoSangre.mundo.TipoSangre;

/**
 * Esta es la clase usada para verificar la correcta implementación de TipoSangre
 */
public class TipoSangreTest extends TestCase 
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
	private TipoSangre tipo;
	
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo TipoSangre
     */
	private void setupEscenario1()
	{
		tipo = new TipoSangre(TipoSangre.TIPO_AB, TipoSangre.RH_P);
	}
	
    /**
     * Prueba 1 - Prueba el método constructor <br>
     * Métodos a probar: <br>
     * TipoSangre, darCantidadDespachada, darCantidadDisponible, darRh, darTipo
     */
    public void testTipoSangre()
    {
    	setupEscenario1();
    	
    	assertTrue("El número de bolsas despachadas debería ser cero", tipo.darCantidadSuministrada()==0);
    	assertTrue("El número de bolsas disponibles debería ser cero", tipo.darCantidadDisponible()==0);
    	assertEquals("El factor RH no es correcto", "+", tipo.darRh());
    	assertTrue("El tipo de sangre no es correcto", tipo.darTipo().toLowerCase().contains("ab"));
    }
    
    /**
     * Prueba 2 - Prueba el método registrarDonacion <br>
     * Métodos a probar: <br>
     * registrarDonacion, darCantidadDespachada, darCantidadDisponible
     */
    public void testRegistrarDonacion()
    {
    	setupEscenario1();
    	
    	tipo.registrarDonacion();
    	assertTrue("El número de bolsas despachadas debería ser cero", tipo.darCantidadSuministrada()==0);
    	assertTrue("El número de bolsas disponibles debería incrementar en 1", tipo.darCantidadDisponible()==1);
    }
    
    /**
     * Prueba 3 - Prueba el método suministrar <br>
     * Métodos a probar: <br>
     * suministrar, registrarDonacion, darCantidadDespachada, darCantidadDisponible
     */
    public void testSuministrar()
    {
    	setupEscenario1();
    	
    	tipo.registrarDonacion();
    	boolean suministro = tipo.suministrar(1);
    	assertFalse("No debería suministrar las bolsas", suministro);
    	assertTrue("El número de bolsas despachadas no debería cambiar", tipo.darCantidadSuministrada()==0);
    	assertTrue("El número de bolsas disponibles no debería cambiar", tipo.darCantidadDisponible()==1);

    	tipo.registrarDonacion();
    	suministro = tipo.suministrar(1);
    	assertTrue("Debería suministrar las bolsas", suministro);
    	assertTrue("El número de bolsas despachadas debería incrementar", tipo.darCantidadSuministrada()==1);
    	assertTrue("El número de bolsas disponibles debería disminuir", tipo.darCantidadDisponible()==1);
    }
}
