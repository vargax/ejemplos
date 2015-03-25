package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Division;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;

/**
 *Clase de pruba para los métodos de la clase división
 *Es necesario correr las pruebas usando aserciones 
 *
 */
public class DivisionTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La clase sobre la cual se realizan las pruebas
     */
    private Division operadorDivision;
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Crea un nuevo operador división con dos operandos
     */
    public void setupEscenario1()
    {
        operadorDivision=new Division( Division.SIMBOLO );
        Nodo elNodoIzquierdo=new Numero( 5 );
        operadorDivision.asignarNodoIzquierdo( elNodoIzquierdo );
        Nodo elNodoDerecho=new Numero( 2 );
        operadorDivision.asignarNodoDerecho( elNodoDerecho );
    }
    
    
    /**
     * Verifica que la evaluación del operador división, retorne el valor de dividir sus dos operandos
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals( "El resultado de la división no es el esperado", 2.5,operadorDivision.evaluar( ));
    }
}
