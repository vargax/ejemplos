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
 * Representa el operador resta
 */
public class Resta extends OperadorBinario
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con el símbolo de la resta
     */
    public final static String SIMBOLO = "-";

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * COnstruye el nodo de la operación de la resta
     * @param elTipo El tipo del operador. elTipo != null
     */
    public Resta( String elTipo )
    {
        super( elTipo );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de aplicar el operador sobre el subárbol izquierdo y el derecho
     * @return El resultado de la operación de la resta de los subárboles
     */
    public double evaluar( )
    {
        double evaluacion = 0;
        evaluacion = darNodoIzquierdo( ).evaluar( ) - darNodoDerecho( ).evaluar( );
        return evaluacion;
    }
}
