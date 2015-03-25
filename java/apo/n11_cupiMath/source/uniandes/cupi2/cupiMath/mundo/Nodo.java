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
 * Clase abstracta que representa un Nodo
 */
public abstract class Nodo
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El valor del nodo
     */
    private String valor;

    /**
     * El nodo izquierdo
     */
    private Nodo nodoIzquierdo;

    /**
     * El nodo derecho
     */
    private Nodo nodoDerecho;
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de la expresión aritmética cuya raíz es este método
     * @return El valor de la expresión aritmética cuya raíz es este método
     */
    public abstract double evaluar( );

    /**
     * Retorna el nodo izquierdo
     * @return El nodo izquierdo
     */
    public Nodo darNodoIzquierdo( )
    {
        return nodoIzquierdo;
    }

    /**
     * Retorna el nodo derecho
     * @return El nodo derecho
     */
    public Nodo darNodoDerecho( )
    {
        return nodoDerecho;
    }

    /**
     * Retorna el valor del nodo
     * @return El valor del nodo
     */
    public String darValor( )
    {
        return valor;
    }

    /**
     * Asigna el parámetro recibido al nodo izquierdo
     * @param izquierdo El nodo izquierdo. izquierdo != null
     */
    public void asignarNodoIzquierdo( Nodo izquierdo )
    {   
        nodoIzquierdo = izquierdo;
    }

    /**
     * Asigna el parámetro recibido al nodo derecho
     * @param derecho El nodo derecho. derecho != null
     */
    public void asignarNodoDerecho( Nodo derecho )
    {
        nodoDerecho = derecho;
    }

    /**
     * Asigna el valor al nodo
     * @param elValor El valor del nodo
     */
    public void asignarValor( String elValor )
    {
        valor = elValor;
    }

    /**
     * Retorna la expresión en notación polaca
     * @return La expresión en notación polaca
     * 
     */
    public String convertirEnNotacionPolaca( )
    {
    	String derecha = nodoDerecho != null ? " "+nodoDerecho.convertirEnNotacionPolaca() : "";
    	String izquierda = nodoIzquierdo != null ? " "+nodoIzquierdo.convertirEnNotacionPolaca() : "";
    	return valor + izquierda + derecha;        
    }
    
    

    /**
     * Verifica el invariantes a todos los nodos del árbol
     */
    public void arbolBienFormado( )
    {
    	if (nodoIzquierdo != null) nodoIzquierdo.verificarInvariante();
        verificarInvariante();
        if (nodoDerecho != null) nodoDerecho.verificarInvariante();
    }
    
    /**
     * Verificar el invariante del nodo
     */
    protected abstract void verificarInvariante( );
    
    public void buscarNumero(int contador, ArrayList dondeEstan, double numeroBuscado) {
    	contador++;
    	if (nodoIzquierdo != null) nodoIzquierdo.buscarNumero(contador,dondeEstan, numeroBuscado);
    	if (nodoDerecho != null) nodoDerecho.buscarNumero(contador,dondeEstan, numeroBuscado);
    }
    
    public String elementosRama(double numeroBuscado) {
    	String respuesta = "";
    	if (nodoIzquierdo != null) respuesta += nodoIzquierdo.elementosRama(numeroBuscado);
    	if (respuesta.equals("") && nodoDerecho != null) respuesta += nodoDerecho.elementosRama(numeroBuscado);
    	
    	if (respuesta.equals("")) return respuesta;
    	return darValor() + " " + respuesta;
    }
    
    public boolean arbolCompleto() {
    	boolean izquierda = nodoIzquierdo != null ? nodoIzquierdo.arbolCompleto() : false;
    	boolean derecha = nodoDerecho != null ? nodoDerecho.arbolCompleto() : false;
    	return izquierda && derecha;
    }
}
