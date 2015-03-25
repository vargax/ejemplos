package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.Resta;

/**
 * Clase de pruba para los métodos de la clase resta
 * Es necesario correr las pruebas usando aserciones 
 */
public class RestaTest extends TestCase
{
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Clase sobre la cual se realizan las pruebas
     */
    private Resta operadorResta;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Crea un nuevo operador resta con dos operandos
     */
    public void setupEscenario1()
    {
        operadorResta=new Resta( Resta.SIMBOLO );
        Nodo elNodoIzquierdo=new Numero( 5 );
        operadorResta.asignarNodoIzquierdo( elNodoIzquierdo );
        Nodo elNodoDerecho=new Numero( 2 );
        operadorResta.asignarNodoDerecho( elNodoDerecho );
    }
    
    
    /**
     * Verifica que la evaluación del operador resta, retorne el valor de restar sus dos operandos
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals( "El resultado de la resta no es el esperado", 3.0,operadorResta.evaluar( ));
    }

}
