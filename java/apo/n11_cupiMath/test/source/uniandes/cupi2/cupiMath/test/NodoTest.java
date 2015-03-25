package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.Suma;

/**
 * Clase para probar los métodos de la clase Nodo
 * Es necesario correr las pruebas usando aserciones 
 */
public class NodoTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La clase sobre la cual se realizan las pruebas
     */
    private Nodo operadorSuma;
    
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Construye un nodo de tipo Suma
     */
    private void setupEscenario1()
    {
        operadorSuma=new Suma( Suma.SIMBOLO );
    }
    
    /**
     * Construye un nodo de tipo suma y sus nodos izquierdo y derecho
     */
    private void setupEscenario2()
    {
        setupEscenario1( );
        Nodo elNodoIzquierdo=new Numero( 5 );
        operadorSuma.asignarNodoIzquierdo( elNodoIzquierdo );
        Nodo elNodoDerecho=new Numero( 2 );
        operadorSuma.asignarNodoDerecho( elNodoDerecho );
        
    }
    
    
    /**
     * Verifica que se asigne correctamente un valor arbitrario a un nodo
     */
    public void testAsignarValor()
    {
        setupEscenario1( );
        operadorSuma.asignarValor( "?" );
        assertEquals( "El valor de un nodo no se asigna correctamente","?", operadorSuma.darValor( ) );
    }
    
    
    /**
     * Verifica que se retorne correctamente el valor de un nodo
     */
    public void testDarValor()
    {
        setupEscenario1( );
        String elValor=operadorSuma.darValor( );
        assertEquals( "El valor del símbolo no es el esperado",Suma.SIMBOLO, elValor );
        
    }
    
    
    /**
     * Verifica que el nodo izquierdo se asigne correctamente
     */
    public void testAsignarNodoIzquierdo()
    {
        setupEscenario1( );
        Nodo elNodoIzquierdo=new Numero( 5 );
        operadorSuma.asignarNodoIzquierdo( elNodoIzquierdo );
        assertNotNull( "El nodo izquierdo no fue asignado" ,operadorSuma.darNodoIzquierdo( ) );
        assertEquals("El nodo izquierdo no corresponde al esperado",elNodoIzquierdo.darValor( ),operadorSuma.darNodoIzquierdo( ).darValor( ));        
    }
    
    /**
     * Verifica que el nodo derecho se asigne correctamente
     */
    public void testAsignarNodoDerecho()
    {
        setupEscenario1( );
        Nodo elNodoDerecho=new Numero( 2 );
        operadorSuma.asignarNodoDerecho( elNodoDerecho );
        assertNotNull( "El nodo derecho no fue asignado" ,operadorSuma.darNodoDerecho( ) );
        assertEquals("El nodo derecho no corresponde al esperado",elNodoDerecho.darValor( ),operadorSuma.darNodoDerecho( ).darValor( ));        
    }
    
    /**
     * Verifica que el nodo izquierdo se retorne correctamente
     */
    public void testDarNodoIzquierdo()
    {
        setupEscenario2( );
        Nodo nodoIzquierdo=operadorSuma.darNodoIzquierdo( );
        assertNotNull( "El nodo izquierdo no fue inicializado", nodoIzquierdo );
        assertEquals( "5", nodoIzquierdo.darValor( ) );
        
    }
    
    /**
     * Verifica que el nodo derecho se retorne correctamente
     */
    public void testDarNodoDerecho()
    {
        setupEscenario2( );
        Nodo nodoDerecho=operadorSuma.darNodoDerecho( );
        assertNotNull( "El nodo derecho no fue inicializado", nodoDerecho );
        assertEquals( "2", nodoDerecho.darValor( ) );
    }
    
    
}
