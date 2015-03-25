package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Multiplicacion;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.OperadorBinario;

/**
 * Clase de pruebas para los métodos del OperadorBinario
 * Es necesario correr las pruebas usando aserciones 
 */
public class OperadorBinarioTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * El operador de la multiplicación
     */
    private OperadorBinario operadorMultiplicacion;
    
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Construye un operador binario sin operandos
     */
    public void setupEscenario1()
    {
        operadorMultiplicacion=new Multiplicacion( Multiplicacion.SIMBOLO );
    }
    
    /**
     * Construye un operador binario con operadores
     */
    public void setupEscenario2()
    {
        setupEscenario1( );
        Nodo elNodoIzquierdo=new Numero( 5 );
        operadorMultiplicacion.asignarNodoIzquierdo( elNodoIzquierdo );
        Nodo elNodoDerecho=new Numero( 2 );
        operadorMultiplicacion.asignarNodoDerecho( elNodoDerecho );
    }
    
    
    /**
     * Verifica que se atrape un error al verificar el invariante ya que no se
     * le asignarion hijos a un operador binario
     */
    public void testVerificarInvariante()
    {
        setupEscenario1( );
        try
        {
            operadorMultiplicacion.verificarInvariante( ); 
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
            operadorMultiplicacion.verificarInvariante( );
        }
        catch (AssertionError ae)
        {
            fail();
        }
        
    }
}
