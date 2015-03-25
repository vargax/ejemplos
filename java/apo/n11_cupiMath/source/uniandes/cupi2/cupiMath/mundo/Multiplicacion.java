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
 * Representa el operador multiplicación
 */
public class Multiplicacion extends OperadorBinario
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con el símbolo de la multiplicación
     */
    public final static String SIMBOLO = "*";

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo de la multiplicación
     * @param elTipo El tipo del operador. elTipo != null
     */
    public Multiplicacion( String elTipo )
    {
        super( elTipo );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de aplicar el operador sobre el subárbol izquierdo y el derecho
     */
    public double evaluar( )
    {
        return darNodoIzquierdo().evaluar()*darNodoDerecho().evaluar();
    }
}
