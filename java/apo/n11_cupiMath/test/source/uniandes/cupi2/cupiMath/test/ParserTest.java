package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.CupiMath;
import uniandes.cupi2.cupiMath.mundo.Parser;
import uniandes.cupi2.cupiMath.mundo.Sqrt;
import uniandes.cupi2.cupiMath.mundo.Suma;

/**
 * Clase de pruebas para los métodos del Parser. Debido a que cada prueba usa una expresión
 * diferente para validar cada método, dentro de la misma prueba se define el escenario que va a usar
 * Es necesario correr las pruebas usando aserciones 
 */
public class ParserTest extends TestCase
{
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Verifica que se valide correctamente si un caracter es un dígito
     * Usa como escenario de prueba la expresión "56"
     */
    public void testEsDigito()
    {
        Parser parser=new Parser("56");
        boolean esDigito=parser.esDigito( );
        assertTrue( "El primer caracter de la expresión es un dígito",esDigito );
    }
    
    /**
     * Verifique que se retorne correctamente un dígito <br>
     * Valida que una vez se retorne el dígito se aumente en 1, la posición del parser en la expresión <br>
     * Usa como escenario de prueba la expresión "56"
     */
    public void testDarDigito()
    {
        Parser parser= new Parser("56");
        int posicionPreLectura=parser.darPosicion( );
        int valor=parser.darDigito( );
        int posicionPostLectura= parser.darPosicion( );
        assertTrue("El dígito retornado por el parser no es el esperado",5==valor);
        assertTrue("La posición del parser no se actualizó", posicionPostLectura==posicionPreLectura+1);
    }
    
    
    /**
     * Verifica que se se valide correctamente si un caracter es <br>
     * alguno de los operadores binarios registrados en la aplicación <br>
     * Usa como escenarios de prueba,expresiones con cada uno de los operadores binarios  
     */
    public void testEsOperadorBinario()
    {
        
        for(int i=0; i<CupiMath.OPERADORES_BINARIOS.length;i++)
        {
            String operadorBinario=CupiMath.OPERADORES_BINARIOS[i];
            Parser parserTemp=new Parser(operadorBinario);
            boolean esOperadorBinario=parserTemp.esOperadorBinario( );
            assertTrue("No se reconoció al operador "+operadorBinario, esOperadorBinario);
        }
    }
    
    
    /**
     * Verifica que se retorne correctamente un operador binario
     * Valida que una vez se retorne el operador binario se aumente en 1, la posición del parser en la expresión <br>
     * Usa como escenario de prueba una expresió que tine como valor al símbolo de la suma
     */
    public void testDarOperadorBinario()
    {
       Parser parser=new Parser(Suma.SIMBOLO);
       int posicionPreLectura=parser.darPosicion( );
       String operadorBinario=parser.darOperadorBinario( );
       int posicionPostLectura=parser.darPosicion( );
       assertEquals("El operador binario no es el esperado", Suma.SIMBOLO, operadorBinario );
       assertTrue("La posición del parser no se actualizó", posicionPostLectura==posicionPreLectura+1);
    }
    
    
    /**
     * Verifica que se se valide correctamente si una cadena de caracteres es un operador unarios <br>
     * registrado en la aplicación.<br>
     * Usa como escenarios de prueba,expresiones con cada uno de los operadores unarios <br>
     */
    public void testEsOperadorUnario()
    {
        String operandoPrueba="[45]";
        for(int i=0; i<CupiMath.OPERADORES_UNARIOS.length;i++)
        {
            String operadorUnario=CupiMath.OPERADORES_UNARIOS[i];
            Parser parserTemp=new Parser(operadorUnario+operandoPrueba);
            boolean esOperadorUnario=parserTemp.esOperadorUnario( );
            assertTrue("No se reconoció al operador "+operadorUnario, esOperadorUnario);
        }
    }
    
    /**
     * Verifica que se retorne correctamente un operador unario <br>
     * Valida que una vez se retorne el operador unario se aumente en la longitud del operador unario, <br>
     * la posición del parser en la expresión <br>
     */
    public void testDarOperadorUnario()
    {
        Parser parser=new Parser(Sqrt.SIMBOLO+"[100]");
        int posicionPreLectura=parser.darPosicion( );
        String operadorUnario=parser.darOperadorUnario( );
        int posicionPostLectura=parser.darPosicion( );
        assertEquals("El operador unario no es el esperado", Sqrt.SIMBOLO , operadorUnario ); 
        assertTrue("La posición del parser no se actualizó", posicionPostLectura==posicionPreLectura+operadorUnario.length( ));
    }
    
    /**
     * Verifica que se valide correctamente si un caracter es un punto decimal
     */
    public void testEsPuntoDecimal()
    {
        Parser parser=new Parser(".");
        assertTrue( "No se leyó un punto decimal", parser.esPuntoDecimal( ));
    }
    
    
    /**
     * Verifica que se retorne correctamente un punto decimal
     */
    public void testDarPuntoDecimal()
    {
        Parser parser=new Parser(".");
        int posicionPreLectura=parser.darPosicion( );
        String puntoDecimal=parser.darPuntoDecimal( );
        int posicionPostLectura=parser.darPosicion( );
        assertEquals( "No se retornó un punto decimal", ".", puntoDecimal);
        assertTrue("La posición del parser no se actualizó", posicionPostLectura==posicionPreLectura+1);
        
    }
    


    
}
