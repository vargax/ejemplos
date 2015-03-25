package vrgx.dialgo.proyectoFinal.ecuacion;

import java.util.ArrayList;
import java.util.Stack;

import vrgx.dialgo.proyectoFinal.ecuacion.operaciones.*;
import vrgx.dialgo.proyectoFinal.excepciones.ExpresionMalformadaExcepcion;
import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;
import vrgx.dialgo.proyectoFinal.excepciones.ProgramacionExcepcion;

/**
 * Clase que representa la ecuación a despejar
 * @author cvargasc
 *
 */
public class Ecuacion {
	// ---------------------------------------------
	// CONSTANTES
	// ---------------------------------------------
	/**
	 * Constantes para describrir una simplificación
	 */
	public final static String SIMPLIFICACION = "Simplificación: ";
	public final static String ARITMETICA = "Aritmética: ";
	public final static String ATRACCION = "Atracción: ";
	public final static String COLECCION = "Colección: ";
	public final static String AISLAMIENTO = "Aislamiento: ";
	// ---------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------
	/**
	 * La raiz del arbol
	 */
	private Igualdad raiz;
	/**
	 * La variable objetivo
	 */
	private String incognita;
	/**
	 * Las transformaciones realizadas
	 */
	private ArrayList<String> transformaciones;
	/**
	 * Los resultados de las transformaciones
	 */
	private ArrayList<String> resultados;
	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	/**
	 * El constructor del arbol que representa la ecuación.
	 * @param especificacion: Cadena que contiene los argumentos de la ecuación
	 * en notación posfija.
	 * @throws ExpresionMalformadaExcepcion 
	 */
	public Ecuacion(String incognitaP, String especificacion) throws ExpresionMalformadaExcepcion {
		// Inicializando 
		incognita = incognitaP;
		transformaciones = new ArrayList<String>();
		resultados = new ArrayList<String>();
		// Generando el árbol sintáctico
		String[] argumentos = especificacion.split(" ");
		Stack<Nodo> pila = new Stack<Nodo>();
		for (int i = 0; i < argumentos.length; i++) {
			String argumentoEnProceso = argumentos[i];
			Nodo nodoEnProceso = null;
			try {
				// Si son operadores
				if (argumentoEnProceso.equals(Igualdad.SIMBOLO)) nodoEnProceso = new Igualdad(pila, this);
				else if (argumentoEnProceso.equals(Suma.SUMA)) nodoEnProceso = new Suma(pila,Suma.SUMA);
				else if (argumentoEnProceso.equals(Suma.RESTA)) nodoEnProceso = new Suma(pila,Suma.RESTA);
				else if (argumentoEnProceso.equals(Multiplicacion.MULTIPLICACION)) nodoEnProceso = new Multiplicacion(pila,Multiplicacion.MULTIPLICACION);
				else if (argumentoEnProceso.equals(Multiplicacion.DIVISION)) nodoEnProceso = new Multiplicacion(pila,Multiplicacion.DIVISION);
				else if (argumentoEnProceso.equals(Cuadrado.SIMBOLO)) nodoEnProceso = new Cuadrado(pila);
				else if (argumentoEnProceso.equals(Raiz.SIMBOLO)) nodoEnProceso = new Raiz(pila);
				else if (argumentoEnProceso.equals(Exponenciacion.SIMBOLO)) nodoEnProceso = new Exponenciacion(pila);
				else if (argumentoEnProceso.equals(Logaritmo.SIMBOLO)) nodoEnProceso = new Logaritmo(pila);
				// Si no es un operador debe ser un parámetro
				else try {
					nodoEnProceso = new Parametro(Integer.parseInt(argumentoEnProceso));
				} catch (NumberFormatException e){
					nodoEnProceso = new Parametro(incognita, argumentoEnProceso);
				}
				pila.push(nodoEnProceso);
			} catch(NodoInvalidoExcepcion e) {
				throw new ExpresionMalformadaExcepcion("Expresión malformada "+e.getMessage());
			}
		}
		if (pila.peek().darSimbolo().equals(Igualdad.SIMBOLO)) 
			raiz = (Igualdad) pila.pop();
		else throw new ExpresionMalformadaExcepcion("Se esperaba un '=', se encontró un "+pila.peek().darSimbolo());
		if (!pila.empty()) throw new ExpresionMalformadaExcepcion("La pila debía estar vacía, sin embargo '"+pila.peek().darSimbolo()+"' se encuentra en el tope");
	}
	// ---------------------------------------------
	// MÉTODOS
	// ---------------------------------------------
	/**
	 * Método que inicia el proceso de despejar la ecuación.
	 * @throws NodoInvalidoExcepcion 
	 * @throws ProgramacionExcepcion 
	 */
	public void despejarEcuacion() throws NodoInvalidoExcepcion, ProgramacionExcepcion {
		System.out.println(recorrerInOrden());
		registrarResultado();
		raiz = raiz.simplificar();
		raiz = raiz.coleccionar();
		raiz = raiz.atraccion();
		raiz = raiz.coleccionar();
		raiz.aislamiento();
		//raiz.simplificar();
		System.out.println(recorrerInOrden());
	}
	/**
	 * Registra una nueva transformación
	 */
	public void registrarResultado() {
		resultados.add(recorrerInOrden());
	}
	public void registrarTransformaciones(String transformacion) {
		transformaciones.add(transformacion);
	}
	// ---------------------------------------------
	// MÉTODOS CONTROL
	// ---------------------------------------------
	/**
	 * Recorre la ecuación en inOrden
	 */
	public String recorrerInOrden() {
		return raiz.recorrerInOrden();
	}
	/**
	 * Recorre la ecuación en posOrden
	 */
	public String recorrerPosOrden() {
		return raiz.recorrerPosOrden();
	}
	/**
	 * Método que entrega un vector con los pasos seguidos para despejar la ecuación.
	 * @return
	 */
	public ArrayList<String> darTransformaciones() {
		return transformaciones;
	}
	/**
	 * Método que entrega un vector con la explicación de cada paso.
	 * @return
	 */
	public ArrayList<String> darResultados() {
		return resultados;
	}
}
