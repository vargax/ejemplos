package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.Suma;

/**
 * Clase de pruba para los métodos de la clase Suma
 * Es necesario correr las pruebas usando aserciones 
 */
public class SumaTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La clase sobre la cual se realizan las pruebas
     */
    private Suma operadorSuma;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Crea un nuevo operador suma con dos operandos
     */
    public void setupEscenario1()
    {
        operadorSuma=new Suma( Suma.SIMBOLO );
        Nodo elNodoIzquierdo=new Numero( 5 );
        operadorSuma.asignarNodoIzquierdo( elNodoIzquierdo );
        Nodo elNodoDerecho=new Numero( 2 );
        operadorSuma.asignarNodoDerecho( elNodoDerecho );
    }
    
    
    /**
     * Verifica que la evaluación del operador suma, retorne el valor de sumar sus dos operandos
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals( "El resultado de la suma no es el esperado", 7.0,operadorSuma.evaluar( ));
    }
    
}
