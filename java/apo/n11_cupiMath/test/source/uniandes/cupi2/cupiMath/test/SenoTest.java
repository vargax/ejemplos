package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.Seno;

/**
 * Clase de pruba para los métodos de la clase Seno
 * Es necesario correr las pruebas usando aserciones 
 */
public class SenoTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Operador Seno
     */
    private Seno operadorSeno;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Construye un operador Seno con su operando
     */
    public void setupEscenario1()
    {
        operadorSeno=new Seno( Seno.SIMBOLO );
        Nodo elNodoIzquierdo=new Numero( 1 );
        operadorSeno.asignarNodoIzquierdo( elNodoIzquierdo );
    }
    
    /**
     * Verifica que la evaluación del operador Seno, retorne el valor de aplicar el Seno a su operando izquierdo
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals( "El resultado del seno no es el esperado" ,0.8414709848078965, operadorSeno.evaluar( ));
    }

}
