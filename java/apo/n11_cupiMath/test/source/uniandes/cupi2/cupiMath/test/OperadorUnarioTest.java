package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.OperadorUnario;
import uniandes.cupi2.cupiMath.mundo.Sqrt;

/**
 * Clase de prueba para los métodos de un OperadorUnario
 * Es necesario correr las pruebas usando aserciones 
 */
public class OperadorUnarioTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * El operador de la raíz cuadradaa
     */
    private OperadorUnario operadorSqrt;
    
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Construye un operador un sin operando
     */
    public void setupEscenario1()
    {
        operadorSqrt=new Sqrt( Sqrt.SIMBOLO );
    }
    
    /**
     * Construye un operador unario con un operador
     */
    public void setupEscenario2()
    {
        setupEscenario1( );
        Nodo elNodoIzquierdo=new Numero( 4 );
        operadorSqrt.asignarNodoIzquierdo( elNodoIzquierdo );
    }
    
    
    /**
     * Verifica que se atrape un error al verificar el invariante ya que no se
     * le asignó el hijo izquierdo al operador unario
     */
    public void testVerificarInvariante()
    {
        setupEscenario1( );
        try
        {
            operadorSqrt.verificarInvariante( ); 
            fail( );
        }
        catch(AssertionError ae)
        {
            //Debe atraparse un error al verificar el invariante
        }  
        
    }
    
    /**
     * Verifica que no se atrape un error al verificar el invariante de un operador binario
     * cuando se le han asignado ambos hijos
     */
    public void testVerificarInvariante2()
    {
        setupEscenario2( );
        try
        {
            operadorSqrt.verificarInvariante( );
        }
        catch (AssertionError ae)
        {
            fail();
        }
        
    }
}
