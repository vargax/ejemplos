package vrgx.dialgo.proyectoFinal.ecuacion.operaciones;

import java.util.Stack;

import vrgx.dialgo.proyectoFinal.ecuacion.Ecuacion;
import vrgx.dialgo.proyectoFinal.ecuacion.Nodo;
import vrgx.dialgo.proyectoFinal.ecuacion.OperadorUnario;
import vrgx.dialgo.proyectoFinal.ecuacion.Parametro;
import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;

public class Logaritmo extends OperadorUnario {
	// ---------------------------------------------
	// CONSTANTES
	// ---------------------------------------------
	/**
	 * Constante que representa el símbolo del operador
	 */
	public final static String SIMBOLO = "log";
	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	/**
	 * Crea un nuevo Logaritmo a partir del símbolo y sus hijos.
	 * @param simboloP
	 * @param nodoHijo
	 * @throws NodoInvalidoExcepcion
	 */
	public Logaritmo(Nodo nodoHijo) throws NodoInvalidoExcepcion {
		super(SIMBOLO,nodoHijo);
	}
	/**
	 * Crea un nuevo Logaritmo a partir de una pila de nodos.
	 * Pre: El hijo se encuentra en el tope de la pila.
	 * Pre: La pila contiene al menos un elemento.
	 * Pos: Se ha creado un nuevo Logaritmo
	 * Pos: Se ha removido un Nodo de la pila
	 * @param simboloP
	 * @param pila
	 * @throws NodoInvalidoExcepcion 
	 */
	public Logaritmo(Stack<Nodo> pila) throws NodoInvalidoExcepcion {
		super(SIMBOLO, pila);
	}
	// ---------------------------------------------
	// REGLAS DE INFERENCIA
	// ---------------------------------------------
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#simplificar()
	 */
	@Override
	public Nodo simplificar(Ecuacion ecuacion) throws NodoInvalidoExcepcion {
		// Primero que todo le pido a mis hijos que se simplifiquen...
		super.simplificar(ecuacion);
		// Si mi hijo resulta ser un número, me opero...
		if (hijo.darTipo().equals(NUMERO)) {
			ecuacion.registrarTransformaciones(Ecuacion.ARITMETICA + "log(n) = m, n,m : R");
			Parametro tempParametro = new Parametro(Math.log(((Parametro)hijo).darValor()) / Math.log(2));
			// Si yo soy negativo o divisor...
			if (esDivisor()) tempParametro.invertirNodo();
			if (esNegativo()) tempParametro.cambiarSigno();
			return tempParametro;
		}
		// Si es una exponenciación, la elimino...
		try {
			Exponenciacion tempExponenciacion = (Exponenciacion)hijo;
			ecuacion.registrarTransformaciones(Ecuacion.SIMPLIFICACION + "log(2^u) = u");
			Nodo tempNodo = tempExponenciacion.darHijos()[0];
			if (esDivisor()) tempNodo.invertirNodo();
			if (esNegativo()) tempNodo.cambiarSigno();
			return tempNodo;
		} catch (ClassCastException e) {
			return this;
		}
	}
}