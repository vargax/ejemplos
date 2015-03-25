package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Multiplicacion;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;


/**
 *Clase de pruba para los métodos de la clase multiplicación
 *Es necesario correr las pruebas usando aserciones 
 */
public class MultiplicacionTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La clase sobre la cual se realizan las pruebas
     */
    private Multiplicacion operadorMultiplicacion;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Crea un nuevo operador multiplicación con dos operandos
     */
    public void setupEscenario1()
    {
        operadorMultiplicacion=new Multiplicacion( Multiplicacion.SIMBOLO );
        Nodo elNodoIzquierdo=new Numero( 5 );
        operadorMultiplicacion.asignarNodoIzquierdo( elNodoIzquierdo );
        Nodo elNodoDerecho=new Numero( 2 );
        operadorMultiplicacion.asignarNodoDerecho( elNodoDerecho );
    }
    
    
    /**
     * Verifica que la evaluación del operador multiplicación, retorne el valor de multiplicar sus dos operandos
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals( "El resultado de la resta no es el esperado", 10.0,operadorMultiplicacion.evaluar( ));
    }
}
