package vrgx.dialgo.proyectoFinal.ecuacion;

import java.util.Stack;

import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;
import vrgx.dialgo.proyectoFinal.excepciones.ProgramacionExcepcion;

/**
 * Clase que representa un nodo del arbol
 * @author cvargasc
 *
 */
public abstract class Nodo {
	// ---------------------------------------------
	// CONSTANTES
	// ---------------------------------------------
	public final static String OPERADOR_UNARIO = "OU";
	public final static String OPERADOR_BINARIO = "OB";
	public final static String PARAMETRO = "P";
	public final static String NUMERO = "N";
	// ---------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------
	/**
	 * El símbolo que representa el nodo
	 */
	protected String simbolo;
	/**
	 * El tipo del Nodo
	 */
	private String tipo;
	/**
	 * Verdadero si el nodo es negativo
	 */
	private boolean negativo;
	/**
	 * Verdadero si el nodo es divisor
	 */
	private boolean divisor;
	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	public Nodo(String simboloP, String tipoP) {
		simbolo = simboloP;
		tipo = tipoP;
		negativo = false;
		divisor = false;
	}
	// ---------------------------------------------
	//REGLAS DE INFERENCIA
	// ---------------------------------------------
	/**
	 * Simplificar: Si los hijos son simplificables, los simplifica.
	 */
	public abstract Nodo simplificar(Ecuacion ecuacion) throws NodoInvalidoExcepcion;
	/**
	 * Coleccionar: Si es posible coleccionar los nodos hijos, los colecciona
	 */
	public abstract Nodo coleccionar(Ecuacion ecuacion) throws NodoInvalidoExcepcion;
	/**
	 * Junta las incógnitas, en la medida de lo posible.
	 * @param nodoIncognita: Se asignará al padre de la incognita, en caso de encontrarlo.
	 * @return El nodo que debe asignarse a la rama sobre la cual se realizó el llamado.
	 * @throws NodoInvalidoExcepcion 
	 * @throws ProgramacionExcepcion 
	 */
	public Nodo atraccion(Stack<Nodo> contenedora, boolean atraccion) throws NodoInvalidoExcepcion, ProgramacionExcepcion {
		return this;
	}
	// ---------------------------------------------
	// MÉTODOS AUXILIARES
	// ---------------------------------------------
	/**
	 * Determina si es posible coleccionar este nodo con el nodo pasado como parámetro.
	 * @throws NodoInvalidoExcepcion 
	 */
	public boolean coleccionable(Nodo candidato, Nodo[] aColeccionar, Nodo[] coleccionado, Stack<Nodo> restantes) throws NodoInvalidoExcepcion {
		return false;
	}
	/**
	 * Determina si la incógnita está contenida en alguno de los nodos hijos.
	 */
	public abstract boolean contieneIncognita();
	// ---------------------------------------------
	// MÉTODOS CONTROL
	// ---------------------------------------------
	/**
	 * Verdadero si el nodo es negativo
	 */
	public boolean esNegativo() {
		return negativo;
	}
	/**
	 * Cambia el signo del nodo
	 */
	public void cambiarSigno() {
		negativo = negativo ? false : true;
	}
	/**
	 * Vardadero si el nodo es divisor
	 */
	public boolean esDivisor() {
		return divisor;
	}
	/**
	 * Invierte el nodo
	 */
	public void invertirNodo() {
		divisor = divisor ? false : true;
	}
	/**
	 * Devuelve el símbolo del nodo
	 * @return
	 */
	public String darSimbolo() {
		String respuesta = new String(simbolo);
		if (divisor) respuesta = "1/"+respuesta;
		if (negativo) respuesta = "-"+respuesta;
		return respuesta;
	}
	/**
	 * Devuelve el tipo del Nodo
	 */
	public String darTipo() {
		return tipo;
	}
	/**
	 * Devuelve los hijos del nodo
	 */
	public abstract Nodo[] darHijos();
	/**
	 * Recorre el arbol en inOrdern
	 */
	public abstract String recorrerInOrden();
	/**
	 * Recorre el arbol en posOrden
	 */
	public abstract String recorrerPosOrden();
	/**
	 * Convierte un nodo a una cadena de texto 
	 */
	public String toString() {
		return simbolo;
	}
	/**
	 * Compara dos nodos
	 */
	public boolean equals(Nodo nodoP) {
		return recorrerPosOrden().equals(nodoP.recorrerPosOrden());
	}
}
