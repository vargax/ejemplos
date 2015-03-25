package vrgx.dialgo.proyectoFinal.ecuacion.operaciones;

import java.util.Stack;

import vrgx.dialgo.proyectoFinal.ecuacion.Ecuacion;
import vrgx.dialgo.proyectoFinal.ecuacion.Nodo;
import vrgx.dialgo.proyectoFinal.ecuacion.OperadorBinario;
import vrgx.dialgo.proyectoFinal.ecuacion.Parametro;
import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;
import vrgx.dialgo.proyectoFinal.excepciones.ProgramacionExcepcion;

public class Multiplicacion extends OperadorBinario {
	// ---------------------------------------------
	// CONSTANTES
	// ---------------------------------------------
	/**
	 * Constantes que representan el símbolo del operador
	 */
	public final static String MULTIPLICACION = "*";
	public final static String DIVISION = "/";
	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	/**
	 * Crea una nueva multiplicación a partir de la especificación de sus Nodos hijos
	 * @param hijoIzquierdo
	 * @param hijoDerecho
	 * @throws NodoInvalidoExcepcion
	 */
	public Multiplicacion(Nodo hijoIzquierdo, Nodo hijoDerecho) throws NodoInvalidoExcepcion {
		super(MULTIPLICACION);
		izquierda = hijoIzquierdo;
		derecha = hijoDerecho;
	}
	/**
	 * Crea una nueva multiplicación a partir de una pila de nodos.
	 * Pre: El hijo derecho se encuentra en el tope de la pila.
	 * Pre: La pila contiene al menos dos elementos.
	 * Pos: Se ha creado una nueva multiplicación
	 * Pos: Se han removido dos Nodos de la pila
	 * @param simboloP
	 * @param pila
	 * @throws NodoInvalidoExcepcion 
	 */
	public Multiplicacion(Stack<Nodo> pila, String tipo) throws NodoInvalidoExcepcion {
		super(MULTIPLICACION);
		derecha = pila.pop();
		izquierda = pila.pop();
		if (tipo.equals(DIVISION)) {
			derecha.invertirNodo();
		}
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
		// Si mis hijos resultan ser números los opero
		if (izquierda.darTipo().equals(NUMERO) && derecha.darTipo().equals(NUMERO)) {
			ecuacion.registrarTransformaciones(Ecuacion.ARITMETICA + "a*b = c, a,b,c : R");
			Parametro tempParametro = new Parametro(((Parametro)izquierda).darValor() * ((Parametro)derecha).darValor());
			if (esDivisor()) tempParametro.invertirNodo();
			return tempParametro;
		}
		// Si no son números, los comparo y si son iguales los simplifico...
		if (izquierda.equals(derecha)) {
			ecuacion.registrarTransformaciones(Ecuacion.SIMPLIFICACION + "a*a = a^2, a : R");
			return new Cuadrado(izquierda);
		}
		return this;
	}
	@Override
	public Nodo atraccion(Stack<Nodo> contenedora, boolean atraccion) throws NodoInvalidoExcepcion, ProgramacionExcepcion {
		if (atraccion) {	
			// Primero le pregunto a mis hijos por la izquierda...
			izquierda = izquierda.atraccion(contenedora, atraccion);
			// Verifico si mi hijo izquierdo o alguno de sus descendientes resultó ser padre de la incógnita...
			if (!contenedora.empty()) {
				// Si sí lo era, debo operarlo con mi hijo derecho...
				contenedora.push(new Multiplicacion(contenedora.pop(), derecha));
				// y miro si soy divisor...
				if(esDivisor()) contenedora.peek().invertirNodo();
				// Si mi hijo izquierdo era el padre puedo eliminarme...
				if (izquierda == null)	return null;
			}
			// Si estoy aquí es porque por la izquierda no estaba, probemos por la derecha... 
			derecha = derecha.atraccion(contenedora, atraccion);
			// Verifico si mi hijo derecho o alguno de sus descendientes resultó ser padre de la incógnita...
			if (!contenedora.empty()) {
				// Si sí lo era, debo operarlo con mi hijo izquierdo...
				contenedora.push(new Multiplicacion(izquierda, contenedora.pop()));
				// y miro si soy divisor...
				if(esDivisor()) contenedora.peek().invertirNodo();
				// Si mi hijo derecho era el padre puedo eliminarme...
				if (derecha == null) return null;
			}
		}
		return this;
	}
	// ---------------------------------------------
	// METODOS AUXILIARES
	// ---------------------------------------------
	/**
	 * Determina si es posible coleccionar este nodo con el nodo pasado como parámetro
	 */
	public boolean coleccionable(Nodo candidato, Nodo[] aColeccionar, Nodo[] coleccionado, Stack<Nodo> restantes) {
		try {
			Multiplicacion tempCandidato = (Multiplicacion) candidato;
			Nodo[] hijosCandidato = tempCandidato.darHijos();
			// Tratando de coleccionar izquierda/izquierda...
			if (izquierda.equals(hijosCandidato[0])) {
				coleccionado[0] = izquierda;
				if(esDivisor()) derecha.invertirNodo(); if(esNegativo()) derecha.cambiarSigno();
				aColeccionar[0] = derecha;
				if(tempCandidato.esDivisor()) hijosCandidato[1].invertirNodo(); if(tempCandidato.esNegativo()) hijosCandidato[1].cambiarSigno();
				aColeccionar[1] = hijosCandidato[1];
				return true;
			}
			// Tratando de coleccionar izquierda/derecha...
			if (izquierda.equals(hijosCandidato[1])) {
				coleccionado[0] = izquierda;
				if(esDivisor()) derecha.invertirNodo(); if(esNegativo()) derecha.cambiarSigno();
				aColeccionar[0] = derecha;
				if(tempCandidato.esDivisor()) hijosCandidato[0].invertirNodo(); if(tempCandidato.esNegativo()) hijosCandidato[0].cambiarSigno();
				aColeccionar[1] = hijosCandidato[0];
				return true;
			}
			// Tratando de coleccionar derecha/izquierda...
			if (derecha.equals(hijosCandidato[0])) {
				coleccionado[0] = derecha;
				if(esDivisor()) izquierda.invertirNodo(); if(esNegativo()) izquierda.cambiarSigno();
				aColeccionar[0] = izquierda;
				if(tempCandidato.esDivisor()) hijosCandidato[1].invertirNodo(); if(tempCandidato.esNegativo()) hijosCandidato[1].cambiarSigno();
				aColeccionar[1] = hijosCandidato[1];
				return true;
			}
			// Tratando de coleccionar derecha/derecha...
			if (derecha.equals(hijosCandidato[1])) {
				coleccionado[0] = derecha;
				if(esDivisor()) izquierda.invertirNodo(); if(esNegativo()) izquierda.cambiarSigno();
				aColeccionar[0] = izquierda;
				if(tempCandidato.esDivisor()) hijosCandidato[0].invertirNodo(); if(tempCandidato.esNegativo()) hijosCandidato[0].cambiarSigno();
				aColeccionar[1] = hijosCandidato[0];
				return true;
			}
			return false;
		} catch (ClassCastException e) {
			return false;
		}
	}
	// ---------------------------------------------
	// METODOS CONTROL
	// ---------------------------------------------
	
	@Override
	public String recorrerInOrden() {
		String respuesta = null;
		
		String izq = izquierda.recorrerInOrden();
		String der = derecha.recorrerInOrden();
		
		respuesta = izquierda.darTipo().equals(OPERADOR_BINARIO) ? "("+izq+")" : izq;
		respuesta += MULTIPLICACION;
		respuesta += derecha.darTipo().equals(OPERADOR_BINARIO) ? "(" + der +")" : der;
		
		if (esDivisor()) respuesta = "1/"+respuesta;
		if (esNegativo()) respuesta = "-"+respuesta;
		
		return respuesta;
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#equals(vrgx.dialgo.proyectoFinal.ecuacion.Nodo)
	 */
	@Override
	public boolean equals(Nodo nodoP) {
		try {
			Nodo[] hijos = ((Multiplicacion) nodoP).darHijos();
			return (hijos[0].equals(izquierda) && hijos[1].equals(derecha)) || (hijos[0].equals(derecha) && hijos[1].equals(izquierda));
		} catch (ClassCastException e) {
			return false;
		}
	}
	
}
