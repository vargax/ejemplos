package vrgx.dialgo.proyectoFinal.ecuacion.operaciones;

import java.util.Stack;

import vrgx.dialgo.proyectoFinal.ecuacion.Ecuacion;
import vrgx.dialgo.proyectoFinal.ecuacion.Nodo;
import vrgx.dialgo.proyectoFinal.ecuacion.OperadorBinario;
import vrgx.dialgo.proyectoFinal.ecuacion.Parametro;
import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;
import vrgx.dialgo.proyectoFinal.excepciones.ProgramacionExcepcion;

public class Suma extends OperadorBinario {
	// ---------------------------------------------
	// CONSTANTES
	// ---------------------------------------------
	/**
	 * Constantes que representan el símbolo del operador
	 */
	public final static String SUMA = "+";
	public final static String RESTA = "-";
	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	/**
	 * Crea una nueva suma a partir de la especificación de sus Nodos hijos
	 * @param hijoIzquierdo
	 * @param hijoDerecho
	 * @throws NodoInvalidoExcepcion
	 */
	public Suma(Nodo hijoIzquierdo, Nodo hijoDerecho) throws NodoInvalidoExcepcion {
		super(SUMA);
		izquierda = hijoIzquierdo;
		derecha = hijoDerecho;
	}
	/**
	 * Crea una nueva suma a partir de una pila de nodos.
	 * Pre: El hijo derecho se encuentra en el tope de la pila.
	 * Pre: La pila contiene al menos dos elementos.
	 * Pos: Se ha creado una nueva suma
	 * Pos: Se han removido dos Nodos de la pila
	 */
	public Suma(Stack<Nodo> pila, String tipo) throws NodoInvalidoExcepcion {
		super(SUMA);
		derecha = pila.pop();
		izquierda = pila.pop();
		if (tipo.equals(RESTA)) derecha.cambiarSigno();
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
		// Elemento neutro...
		if (izquierda.darTipo().equals(NUMERO)) {
			Parametro tempParametro = (Parametro) izquierda;
			if (tempParametro.darValor() == 0) {
				ecuacion.registrarTransformaciones(Ecuacion.ARITMETICA + "a + 0 = a");
				return derecha;
			}
		}
		if (derecha.darTipo().equals(NUMERO)) {
			Parametro tempParametro = (Parametro) derecha;
			if (tempParametro.darValor() == 0) {
				ecuacion.registrarTransformaciones(Ecuacion.ARITMETICA + "a + 0 = a");
				return izquierda;
			}
		}
		// Si mis hijos resultan ser números los opero
		if (izquierda.darTipo().equals(NUMERO) && derecha.darTipo().equals(NUMERO)) {
			ecuacion.registrarTransformaciones(Ecuacion.ARITMETICA + "a + b = c, a,b,c : R");
			Parametro tempParametro = new Parametro(((Parametro)izquierda).darValor() + ((Parametro)derecha).darValor());
			if (esDivisor()) tempParametro.invertirNodo();
			return tempParametro;
		}
		// Si no son números, los comparo y si son iguales los simplifico...
		if (izquierda.equals(derecha)) {
			// Caso en el que son ambos positivos...
			if (!izquierda.esNegativo() && !derecha.esNegativo()) {
				ecuacion.registrarTransformaciones(Ecuacion.SIMPLIFICACION + "a + a = 2*a, a : R");
				return new Multiplicacion(new Parametro(2), izquierda);
			} 
			// Caso en el que uno es negativo y el otro es positivo...
			if ((izquierda.esNegativo() && !derecha.esNegativo()) || (!izquierda.esNegativo() && derecha.esNegativo())) {
				ecuacion.registrarTransformaciones(Ecuacion.SIMPLIFICACION + "a - a = 0, a : R");
				return new Parametro(0);
			}
			// Caso en el que ambos son negativos...
			if (izquierda.esNegativo() && derecha.esNegativo()) {
				ecuacion.registrarTransformaciones(Ecuacion.SIMPLIFICACION + "-a - a = -2*a, a : R");
				return new Multiplicacion(new Parametro(-2), izquierda);
			} 
		}
		return this;
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#atraccion(vrgx.dialgo.proyectoFinal.ecuacion.Nodo)
	 */
	@Override
	public Nodo atraccion(Stack<Nodo> contenedora, boolean atraccion) throws NodoInvalidoExcepcion, ProgramacionExcepcion {
		// Primero le pregunto a mis hijos por la izquierda...
		izquierda = izquierda.atraccion(contenedora, atraccion);
		// Si mi hijo izquierdo resulta contener a la incognita...
		if (izquierda == null) {
			// Si yo soy negativo, debo cambiarle el signo a mis hijos...
			if(esNegativo()) {
				contenedora.peek().cambiarSigno();
				derecha.cambiarSigno();
			}
			// y pasando a mi hijo derecho me elimino...
			return derecha;
		}
		/* 
		 * Si llegué hasta aquí es porque mi hijo izquierdo no era padre de la incógnita...
		 * Ahora debo verificar si alguno de sus descendiente si lo era...
		 */
		if (!contenedora.empty()) {
			// Si entré aquí es porque alguno de los descendientes de mi hijo izquierdo contenía a la incognita...
			if(esNegativo()) contenedora.peek().cambiarSigno(); // Si yo soy negativo debo cambiarle el signo...
			return this; // Y detener la ejecución..
		}
		/*
		 * Si llegué hasta aquí es porque ninguno de los descendientes de mi hijo izquierdo contenía la incógnita...
		 * Ahora debo preguntarle a mi hijo derecho...
		 */
		derecha = derecha.atraccion(contenedora, atraccion);
		// Si mi hijo derecho resulta contener la incógnita...
		if (derecha == null) {
			// Si yo soy negativo, debo cambiarle el signo a mis hijos...
			if(esNegativo()) {
				contenedora.peek().cambiarSigno();
				izquierda.cambiarSigno();
			}
			// y pasando a mi hijo izquierdo me elimino...
			return izquierda;
		}
		/*
		 * Si llegué hasta aquí es porque ni mi hijo izquierdo ni mi hijo derecho eran padres de la incógnita...
		 * Ahora debo verificar si alguno de los descendientes de mi hijo derecho si lo era...
		 */
		if (!contenedora.empty())
			// Si entré aquí es porque alguno de los descendientes de mi hijo derecho contenía a la incognita...
			if(esNegativo())
				contenedora.peek().cambiarSigno(); // Si yo soy negativo debo cambiarle el signo...
		 // Y finalizo ..
		return this;
	}
	/**
	 * La suma puede coleccionar multiplicaciones.
	 */
	@Override
	public Nodo coleccionar(Ecuacion ecuacion) throws NodoInvalidoExcepcion {
		super.coleccionar(ecuacion);
		Nodo[] aColeccionar = new Nodo[2];
		Nodo[] coleccionado = new Nodo[1];
		Stack<Nodo> restantes = new Stack<Nodo>();
		if (esNegativo()) derecha.cambiarSigno();
		if(izquierda.coleccionable(derecha, aColeccionar, coleccionado, restantes)) {
			ecuacion.registrarTransformaciones(Ecuacion.COLECCION+"(a*u) + (b*u) = (a + b)*u");
			// Realizando la colección...
			Multiplicacion tempMultiplicacion = new Multiplicacion(coleccionado[0], new Suma(aColeccionar[0],aColeccionar[1]));
			if(esDivisor()) tempMultiplicacion.invertirNodo();
			if(esNegativo()) tempMultiplicacion.cambiarSigno();
			// Recuperando los restantes...
			if (restantes.empty()) return tempMultiplicacion;
			restantes.push(tempMultiplicacion);
			Suma tempSuma = new Suma(restantes,SUMA);
			while (!restantes.empty()) {
				restantes.push(tempSuma);
				tempSuma = new Suma(restantes,SUMA);
			}
			return tempSuma;
		}
		if (esNegativo()) derecha.cambiarSigno();
		return this;
	}
	// ---------------------------------------------
	// METODOS AUXILIARES
	// ---------------------------------------------
	/**
	 * Determina si es posible coleccionar este nodo con el nodo pasado como parámetro
	 * @throws NodoInvalidoExcepcion 
	 */
	public boolean coleccionable(Nodo candidato, Nodo[] aColeccionar, Nodo[] coleccionado, Stack<Nodo> restantes) throws NodoInvalidoExcepcion {
		// Si me están preguntando por otra suma...
		try {
			Suma tempCandidato = (Suma) candidato;
			Nodo[] hijosCandidato = tempCandidato.darHijos();
			// Tratando de coleccionar izquierda/izquierda...
			if (izquierda.equals(hijosCandidato[0])) {
				coleccionado[0] = izquierda;
				aColeccionar[0] = derecha;
				aColeccionar[1] = hijosCandidato[1];
				return true;
			}
			// Tratando de coleccionar izquierda/derecha...
			if (izquierda.equals(hijosCandidato[1])) {
				coleccionado[0] = izquierda;
				aColeccionar[0] = derecha;
				aColeccionar[1] = hijosCandidato[0];
				return true;
			}
			// Tratando de coleccionar derecha/izquierda...
			if (derecha.equals(hijosCandidato[0])) {
				coleccionado[0] = derecha;
				aColeccionar[0] = izquierda;
				aColeccionar[1] = hijosCandidato[1];
				return true;
			}
			// Tratando de coleccionar derecha/derecha...
			if (derecha.equals(hijosCandidato[1])) {
				coleccionado[0] = derecha;
				aColeccionar[0] = izquierda;
				aColeccionar[1] = hijosCandidato[0];
				return true;
			}
		} catch (ClassCastException e) {}
		// Si me preguntan por alguna otra cosa...
		if (izquierda.coleccionable(candidato, aColeccionar, coleccionado, restantes)) {
			restantes.push(derecha);
			return true;
		}
		if (derecha.coleccionable(candidato, aColeccionar, coleccionado, restantes)) {
			restantes.push(izquierda);
			return true;
		}
		return false;
	}
	// ---------------------------------------------
	// METODOS CONTROL
	// ---------------------------------------------
	@Override
	public String recorrerInOrden() {
		String respuesta = null;
		
		String izq = izquierda.recorrerInOrden()+" ";
		String der = " "+derecha.recorrerInOrden();
		
		respuesta = izq + SUMA + der;
		
		if (esDivisor()) respuesta = "1/("+respuesta+")";
		if (esNegativo()) respuesta = "-"+respuesta;
		
		return respuesta;
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#equals(vrgx.dialgo.proyectoFinal.ecuacion.Nodo)
	 */
	@Override
	public boolean equals(Nodo nodoP) {
		try {
			Nodo[] hijos = ((Suma) nodoP).darHijos();
			return (hijos[0].equals(izquierda) && hijos[1].equals(derecha)) || (hijos[0].equals(derecha) && hijos[1].equals(izquierda));
		} catch (ClassCastException e) {
			return false;
		}
	}
}