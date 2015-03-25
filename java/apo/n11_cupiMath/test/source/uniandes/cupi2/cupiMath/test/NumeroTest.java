package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Numero;


/**
 * Clase de prueba de los métodos de la clase Numero
 * Es necesario correr las pruebas usando aserciones 
 */
public class NumeroTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Representa un número
     */
    private Numero numero;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Construye un número y le asigna como valor "100"
     */
    public void setupEscenario1()
    {
        numero=new Numero( 1 );
        numero.asignarValor( numero.darValor( )+"00" );
    }
    
    
    /**
     * Valida que a un número se le asigne el valor correctamente
     */
    public void testNumero()
    {
        setupEscenario1( );
        assertEquals( "El operando no fue creado correctamente","100", numero.darValor( ) );
    }
    
    
    /**
     * Valida que la evaluación del número sea el valor númerico que se le asignó
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals("La evaluación del número no es la esperada", 100.0, numero.evaluar( ));
    }
    
    /**
     * Valida que el número no tenga hijos
     */
    public void testVerificarInvariante()
    {
        setupEscenario1( );
        try
        {
            numero.verificarInvariante( );
        }
        catch(AssertionError ae)
        {
            fail("Un número debe tener ambos hijos en null");
        }
        
        
    }
    
    
    
}
