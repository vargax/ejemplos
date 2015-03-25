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
 * Representa un número en la expresión
 */
public class Numero extends Nodo
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un número con el valor de su primer dígito.
     * @param elValor El primer dígito del número. elValor != null
     */
    public Numero( int elValor )
    {
        this.asignarValor( "" + elValor );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor del número
     * @return El valor numérico de la expresión.
     */
    public double evaluar( )
    {
        return Double.parseDouble( darValor( ) );
    }

    /**
     * Un número no debe tener nodos
     */
    public void verificarInvariante( )
    {
        assert this.darNodoIzquierdo( ) == null && this.darNodoDerecho( ) == null : "Un operando no debe tener nodos hijos";

    }
    
    public void buscarNumero(int contador, ArrayList dondeEstan, double numeroBuscado) {
    	contador++;
    	if (evaluar() == numeroBuscado) dondeEstan.add(contador);
    }
    
    public String elementosRama(double numeroBuscado) {
    	if (evaluar() == numeroBuscado) return darValor();
    	else return "";
    }
    
    public boolean arbolCompleto() {
    	return true;
    }
}
