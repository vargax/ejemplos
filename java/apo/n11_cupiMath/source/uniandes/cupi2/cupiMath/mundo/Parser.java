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

/**
 * Clase auxiliar para leer la expresión aritmética ingresada por el usuario
 */
public class Parser
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La cadena de caracteres con la expresión aritmética
     */
    private String expresionAritmetica;

    /**
     * La posición en la cual se encuentra leyendo en la expresión aritmética
     */
    int posicion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa la posición en 0 y la expresionAritmetica con el valor recibido por parámetro
     * <b>pre: </b>En la expresión aritmética no hay sub-expresiones entre paréntesis al mismo nivel, únicamente pueden haber paréntesis anidados<br>
     * @param laExpresion La expresión aritmética. laExpresion != null
     */
    public Parser( String laExpresion )
    {
        expresionAritmetica = laExpresion;
        posicion = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna true sí en la posición actual hay un dígito, false en caso contrario
     * @return true si en la posición actual hay un dígito, false en caso contrario
     */
    public boolean esDigito( )
    {
        boolean esDigito = false;
        try
        {
            String digito = expresionAritmetica.substring( posicion, posicion + 1 );
            Integer.parseInt( digito );
            esDigito = true;
        }
        catch( NumberFormatException ex )
        {
            //No hace nada 
        }
        return esDigito;

    }

    /**
     * Retorna el dígito que hay en posición actual y aumenta la posición en uno
     * @return El dígito en la posición actual
     */
    public int darDigito( )
    {
        int digito = Integer.parseInt( expresionAritmetica.substring( posicion, posicion + 1 ) );
        posicion++;
        return digito;
    }

    /**
     * Verifica si el carácter actual es un punto decimal
     * @return true si es un punto decimal, false en caso contrario
     */
    public boolean esPuntoDecimal( )
    {
        boolean esPuntoDecimal = false;
        String punto = expresionAritmetica.substring( posicion, posicion + 1 );
        if( punto.equals( "." ) )
            esPuntoDecimal = true;
        return esPuntoDecimal;
    }

    /**
     * Retorna un punto decimal y aumenta la posición en uno
     * @return El punto decimal de la expresión
     */
    public String darPuntoDecimal( )
    {
        String puntoDecimal = expresionAritmetica.substring( posicion, posicion + 1 );
        posicion++;
        return puntoDecimal;
    }

    /**
     * Verifica si en la posición actual hay un operador binario. <br>
     * Un operador binario es un carácter. Los operadores binarios son: SUMA, RESTA, MULTIPLICACION y DIVISION
     * @return true sí a partir de la posición actual hay un operador, false en caso contrario
     */
    public boolean esOperadorBinario( )
    {
        boolean esOperador = false;
        String operadorBinario = expresionAritmetica.substring( posicion, posicion + 1 );
        for(int i=0;i<CupiMath.OPERADORES_BINARIOS.length && !esOperador;i++)
        {
            if(operadorBinario.equals( CupiMath.OPERADORES_BINARIOS[i] ))
                esOperador=true;
        }
        
        return esOperador;
    }

    /**
     * Verifica si en la posición actual hay un operador unario: <br>
     * Un operador unario es una cadena de caracteres. Los operadores unarios son: SENO y SQRT
     * @return true sí a partir de la posición actual hay un operador, false en caso contrario
     */
    public boolean esOperadorUnario( )
    {
        boolean esOperador = false;
        String operadorUnario = "";
        char caracterActual = expresionAritmetica.substring( posicion, posicion + 1 ).charAt( 0 );
        int posicionTemp = posicion;
        while( ( caracterActual >= 'a' && caracterActual <= 'z' ) || ( caracterActual >= 'A' && caracterActual <= 'Z' ) )
        {
            operadorUnario = operadorUnario + caracterActual;
            posicionTemp++;
            caracterActual = expresionAritmetica.substring( posicionTemp, posicionTemp + 1 ).charAt( 0 );
        }
        for(int i=0; i< CupiMath.OPERADORES_UNARIOS.length&&!esOperador;i++)
        {
            if(operadorUnario.equals( CupiMath.OPERADORES_UNARIOS[i] ) )
                esOperador=true;
        }
        
        return esOperador;
    }

    /**
     * Retorna el operador binario en la posición actual y aumenta la posición actual en uno
     * @return El operador binario en la posición actual
     */
    public String darOperadorBinario( )
    {
        String operadorBinario = expresionAritmetica.substring( posicion, posicion + 1 );
        posicion++;
        return operadorBinario;

    }

    /**
     * Retorna el operador unario que se encuentra a partir de la posición actual y aumenta la posición actual de acuerdo al número de caracteres del operador
     * @return El operador unario de la expresión
     */
    public String darOperadorUnario()
    {
        String operadorUnario = "";
        char caracterActual = expresionAritmetica.substring( posicion, posicion + 1 ).charAt( 0 );
        int posicionTemp = posicion;
        while( ( caracterActual >= 'a' && caracterActual <= 'z' ) || ( caracterActual >= 'A' && caracterActual <= 'Z' ) )
        {
            operadorUnario = operadorUnario + caracterActual;
            posicionTemp++;
            caracterActual = expresionAritmetica.substring( posicionTemp, posicionTemp + 1 ).charAt( 0 );

        }
        posicion = posicion + operadorUnario.length( );
        return operadorUnario;
    }

    /**
     * Devuelve si la expresión inicia con un paréntesis de apertura
     * @return True en caso que sea un paréntesis de apertura, false en caso contrario
     */
    public boolean esParentesisApertura( )
    {
        boolean esParentesisApertura = false;
        String parentesis = expresionAritmetica.substring( posicion, posicion + 1 );
        if( parentesis.equals( "(" ) )
            esParentesisApertura = true;
        return esParentesisApertura;
    }

    /**
     * Verifica que la posición sea parte de la cadena. Ya que el carácter en la posición no ha sido leída, y en cuyo caso deben haber más caracteres.
     * @return True en caso que hayan más caracteres sin leer en la expresión False en caso contrario
     */
    public boolean hayMasCaracteres( )
    {
        boolean hayMas = false;
        if( posicion <= expresionAritmetica.length( ) - 1 )
            hayMas = true;
        return hayMas;
    }

    /**
     * Retorna una subcadena de la expresión aritmética, la cual contiene a una sub-expresión entre paréntesis La subcadena empieza en la posición + 1 y termina en la posición
     * del último paréntesis que haya en la expresión
     * @return Una sub-expresión que está entre paréntesis
     */
    public String darExpresionEntreParentesis( )
    {
        int ultimaPosicionParentesis = expresionAritmetica.lastIndexOf( ")", expresionAritmetica.length( ) - 1 );
        String respuesta = expresionAritmetica.substring( posicion + 1, ultimaPosicionParentesis );
        posicion = ultimaPosicionParentesis + 1;
        return respuesta;
    }

    /**
     * Retorna una subcadena que corresponde a la sub-expresión que hace de operando del operador unario
     * @return La expresión que hace de operando del operador unario
     */
    public String darExpresionEntreParentesisOperadorUnario( )
    {
        int siguientePosicionParentesis = expresionAritmetica.indexOf( "]", posicion );
        String respuesta = expresionAritmetica.substring( posicion + 1, siguientePosicionParentesis );
        posicion = siguientePosicionParentesis + 1;
        return respuesta;
    }
    
    /**
     * Retorna la posición de la cadena en la cual está el parser
     * @return La posición de la cadena en la cual está el parser
     */
    public int darPosicion()
    {
        return posicion;
    }
}
