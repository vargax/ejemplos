package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.Sqrt;


/**
 *Clase de pruba para los métodos de la clase Sqrt
 *Es necesario correr las pruebas usando aserciones 
 */
public class SqrtTest extends TestCase
{
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Operador Raíz cuadrada
     */
    private Sqrt operadorSqrt;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Construye un operador sqrt con su operando
     */
    public void setupEscenario1()
    {
        operadorSqrt=new Sqrt( Sqrt.SIMBOLO );
        Nodo elNodoIzquierdo=new Numero( 4 );
        operadorSqrt.asignarNodoIzquierdo( elNodoIzquierdo );
    }
    
    /**
     * Verifica que la evaluación del operador Sqrt, retorne el valor de aplicar la raíz cuadrada a su operando izquierdo
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals( "El resultado de la raíz cuadrada no es el esperado" ,2.0, operadorSqrt.evaluar( ));
    }

}
