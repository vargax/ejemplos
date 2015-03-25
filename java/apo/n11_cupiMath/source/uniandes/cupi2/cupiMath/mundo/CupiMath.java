/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_cupiMath
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiMath.mundo;

import java.util.ArrayList;

/**
 * Clase encargada de procesar las expresiones aritméticas<br>
 * <b>inv: </b>El árbol debe estar bien formado.
 */
public class CupiMath
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Los operadores binarios de la aplicación
     */
    
    public final static String[] OPERADORES_BINARIOS = new String[]{ Suma.SIMBOLO, Resta.SIMBOLO, Multiplicacion.SIMBOLO, Division.SIMBOLO };

    /**
     * Los operadores unarios de la aplicación
     */
    public final static String[] OPERADORES_UNARIOS = new String[]{ Seno.SIMBOLO, Sqrt.SIMBOLO };

    /**
     * Los tipos de paréntesis de la aplicación
     */
    public final static String[] TIPOS_PARENTESIS = new String[]{ "(", ")", "[", "]" };

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nodo raíz del árbol
     */
    private Nodo raiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la clase principal
     */
    public CupiMath( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un parser para procesar la expresión ingresada por el usuario <br>
     * Invoca a la función construirExpresionAritmetica para que construya el árbol <br>
     * de la expresión, por último verifica el invariante de la clase.
     * @param laExpresion La expresión aritmética
     * @throws Exception En caso que la expresión esté mal formada
     */
    public void construirArbol( String laExpresion ) throws Exception
    {
        try
        {
            Parser parser = new Parser( laExpresion );
            raiz = construirExpresionAritmetica( parser );
            verificarInvariante( );
        }
        catch( Exception ex )
        {
            throw ex;
        }
    }

    /**
     * Función para construir el árbol de una expresión. El parser debe validar, en caso de que hayan más caracteres:<br>
     * 1. Si es un dígito.<br>
     * 2. Si es un operador binario.<br>
     * 3. Si es un operador unario.<br>
     * 4. Si es un paréntesis de apertura<br>
     * @param parser El ayudante usado para leer una expresión aritmética
     * @return El nodo raíz de un árbol con el resultado de la expresión
     * @throws Exception En caso que la expresión esté malformada
     */
    public Nodo construirExpresionAritmetica( Parser parser ) throws Exception
    {
        if( parser.hayMasCaracteres( ) && parser.esDigito( ) )
            return construirNodoNumero( parser );
        else if( parser.hayMasCaracteres( ) && parser.esOperadorBinario( ) )
            return construirOperadorBinario( parser );
        else if( parser.hayMasCaracteres( ) && parser.esOperadorUnario( ) )
            return construirOperadorUnario( parser );
        else if( parser.hayMasCaracteres( ) && parser.esParentesisApertura( ) )
            return construirSubExpresionAritmetica( parser );
        else
            throw new Exception( "Expresión mal formada" );
    }

    /**
     * Función recursiva para construir un nodo de tipo número <br>
     * Construye un número que puede ser entero o decimal. Debe validar si tiene puntos que separan los decimales de la parte entera.<br>
     * <b>pre: </b>El caracter actual en el parser es un valor numérico.
     * En caso que exista un operador posterior al número, el número se le debe asignar como su operando
     * @param parser El parser encargado de procesar las expresiones. parser != null
     * @return En caso que no haya un operador posterior al número debe retornar un nodo que represente al número, en caso contrario debe 
     * retornar un nodo producto de procesar el resto de la expresión aritmética
     * @throws Exception En caso que la expresión esté mal formada
     */
    public Nodo construirNodoNumero( Parser parser ) throws Exception
    {
        int elDigito = parser.darDigito( );
        Numero numero = new Numero( elDigito );
        int numDecimales = 0;
        // Revisar si se está leyendo un número de más de un dígito
        while( parser.hayMasCaracteres( ) && ( parser.esDigito( ) || parser.esPuntoDecimal( ) ) )
        {
            if( parser.esDigito( ) )
            {
                int nuevoDigito = parser.darDigito( );
                numero.asignarValor( numero.darValor( ) + nuevoDigito );
            }
            else
            {
                if( numDecimales > 0 )
                    throw new Exception( "Un número solo debe tener un punto decimal" );
                else
                {
                    String puntoDecimal = parser.darPuntoDecimal( );
                    numero.asignarValor( numero.darValor( ) + puntoDecimal );
                    numDecimales++;
                }
            }

        }
        if( parser.hayMasCaracteres( ) )
        {
            Nodo padre = construirExpresionAritmetica( parser );
            padre.asignarNodoIzquierdo( numero );
            return padre;
        }
        else
        {
            return numero;
        }
    }

    /**
     * Construye un nodo para representar un operador binario.<br>
     * <b>pos: </b>La expresión aritmética nunca puede terminar en un operador binario por lo tanto se debe procesar el resto de la expresión aritmética.
     * @param parser El parser de la expresión aritmética. parser != null
     * @return Un nodo que representa a un operador binario
     * @throws Exception En caso que la expresión este mal formada
     */
    public Nodo construirOperadorBinario( Parser parser ) throws Exception
    {
        Nodo padre = null;
        String elOperador = parser.darOperadorBinario( );
        if( elOperador.equals( Suma.SIMBOLO ) )
            padre = new Suma( elOperador );
        else if( elOperador.equals( Resta.SIMBOLO ) )
            padre = new Resta( elOperador );
        else if( elOperador.equals( Multiplicacion.SIMBOLO ) )
            padre = new Multiplicacion( elOperador );
        else if(elOperador.equals(Division.SIMBOLO))
        	padre = new Division(elOperador);
        
        padre.asignarNodoDerecho( construirExpresionAritmetica( parser ) );
        return padre;
    }

    /**
     * Construye un nodo para representar un operador unario <br>
     * Luego de procesar el operador hay que procesar su operando <br>
     * <b>pos: </b>Un operador unario siempre viene seguido de un operando, el cual es una expresión aritmética entre paréntesis cuadrados.<br>
     * @param parser El parser de la expresión aritmética. parser != null
     * @return Un nodo que representa a un operador unario
     * @throws Exception En caso que la expresión este mal formada
     */
    public Nodo construirOperadorUnario( Parser parser ) throws Exception
    {
        Nodo padre = null;
        String elOperador = parser.darOperadorUnario( );

        if( elOperador.equals( Seno.SIMBOLO ) )
            padre = new Seno( Seno.SIMBOLO );
        else if( elOperador.equals( Sqrt.SIMBOLO ) )
            padre = new Sqrt( Sqrt.SIMBOLO );

        padre.asignarNodoIzquierdo( construirOperandoDeOperadorUnario( parser ) );
        if( parser.hayMasCaracteres( ) )
        {
            Nodo operadorUnario = padre;
            Nodo operadorBinario = construirExpresionAritmetica( parser );
            operadorBinario.asignarNodoIzquierdo( operadorUnario );
            return operadorBinario;
        }
        else
        {
            return padre;
        }
    }

    /**
     * Construye un nodo que contiene una expresión aritmética entre paréntesis <br>
     * Si hay más caracteres luego de la sub-expresión debe procesar el resto de la expresión
     * @param parser El parser de la expresión aritmética. parser != null
     * @return Un nodo que corresponde a la raíz de una expresión aritmética
     * @throws Exception En caso que la sub-expresión sea una expresión aritmética mal formada
     */
    public Nodo construirSubExpresionAritmetica( Parser parser ) throws Exception
    {
        Parser parserSubexpresion = new Parser( parser.darExpresionEntreParentesis( ) );
        Nodo nodoParentesis = construirExpresionAritmetica( parserSubexpresion );
        if( parser.hayMasCaracteres( ) )
        {
            Nodo padre = construirExpresionAritmetica( parser );
            padre.asignarNodoIzquierdo( nodoParentesis );
            return padre;
        }
        else
            return nodoParentesis;
    }

    /**
     * Construye un nodo que contiene una expresión aritmética entre paréntesis y representa al operando de un operador unario <br>
     * @param parser El parser de la expresión aritmética. parser != null
     * @return Un nodo que corresponde a la raíz de una expresión
     * @throws Exception En caso que el operando sea una expresión aritmética mal formada
     */
    public Nodo construirOperandoDeOperadorUnario( Parser parser ) throws Exception
    {
        Parser parserSubexpresion = new Parser( parser.darExpresionEntreParentesisOperadorUnario( ) );
        Nodo nodoParentesis = construirExpresionAritmetica( parserSubexpresion );
        return nodoParentesis;
    }

    /**
     * Construye un árbol a partir de una expresión aritmética Recorre dicho árbol para evaluar la expresión <b>pre: </b>El árbol de la expresión aritmética está construido<br>
     * @param laExpresion La expresión aritmética. laExpresion != null
     * @return El valor de la expresión aritmética
     * @throws Exception En caso que la expresión no comience por un dígito
     */
    public double evaluarExpresion( String laExpresion ) throws Exception
    {
        Parser expr = new Parser(laExpresion);
        raiz = construirExpresionAritmetica(expr);
        return raiz.evaluar();
    }

    /**
     * Convierte una expresión aritmética expresada en notación infija a notación polaca. Recorre el árbol en preorden
     * @param laExpresion La cadena de caracteres que contiene la expresión. laExpresion != null
     * @return La expresión ingresada por el usuario en notación polaca
     * @throws Exception En caso de que la expresión no sea correcta
     */
    public String convertirEnNotacionPolaca( String laExpresion ) throws Exception
    {
        construirArbol( laExpresion );
        return raiz.convertirEnNotacionPolaca();
    }

    /**
     * Retorna la raíz del árbol que representa la expresión aritmética
     * @return La raíz del árbol que representa la expresión aritmética
     */
    public Nodo darRaiz( )
    {
        return raiz;
    }
    public String buscarNumero(double numeroBuscado) {
    	ArrayList resultados = new ArrayList();
    	raiz.buscarNumero(0,resultados,numeroBuscado);
    	if (resultados.size() == 0) {
    		return "NO existe ningún número que coincida con el valor ingresado";
    	} else {
    		String respuesta = "El número está en los niveles:";
    		for (int i = 0; i < resultados.size(); i++)
    			respuesta += " "+resultados.get(i);
    		return respuesta;
    	}
    }
    
    public String elementosRama(double numeroBuscado) {
    	String resultado = raiz.elementosRama(numeroBuscado);
    	String respuesta = "";
    	if (resultado.equals("")) {
    		respuesta += "NO existe ningún número que coincida con el valor ingresado";
    	} else {
    		respuesta += "Los elementos de la primera rama en la que aparece el número son: "+resultado;
    	}
    	if (raiz.arbolCompleto()) return respuesta + " y el arbol está completo.";
    	else return respuesta + " y el arbol no está completo.";
    	
    }
    // -----------------------------------------------------------------
    // Verificación Invariante
    // -----------------------------------------------------------------
    
    /**
     * Verifica que el árbol de la expresión aritmética esté bien formado.
     */
    private void verificarInvariante( )
    {
    	raiz.verificarInvariante();
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}
