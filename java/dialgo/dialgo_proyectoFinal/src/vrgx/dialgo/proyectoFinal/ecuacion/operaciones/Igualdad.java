package vrgx.dialgo.proyectoFinal.ecuacion.operaciones;

import java.util.Stack;

import vrgx.dialgo.proyectoFinal.ecuacion.Ecuacion;
import vrgx.dialgo.proyectoFinal.ecuacion.Nodo;
import vrgx.dialgo.proyectoFinal.ecuacion.OperadorBinario;
import vrgx.dialgo.proyectoFinal.ecuacion.Parametro;
import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;
import vrgx.dialgo.proyectoFinal.excepciones.ProgramacionExcepcion;

public class Igualdad extends OperadorBinario {
	// ---------------------------------------------
	// CONSTANTES
	// ---------------------------------------------
	/**
	 * Constante que representa el símbolo del operador
	 */
	public final static String SIMBOLO = "=";
	// ---------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------
	/**
	 * Representa a la ecuación
	 */
	private Ecuacion ecuacion;
	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	/**
	 * Crea una nueva igualdad a partir de la especificación de sus Nodos hijos
	 * @param hijoIzquierdo
	 * @param hijoDerecho
	 * @throws NodoInvalidoExcepcion
	 */
	public Igualdad (Nodo hijoIzquierdo, Nodo hijoDerecho, Ecuacion ecuacionP) throws NodoInvalidoExcepcion {
		super(SIMBOLO);
		izquierda = hijoIzquierdo;
		derecha = hijoDerecho;
		ecuacion = ecuacionP;
	}
	/**
	 * Crea una nueva igualdad a partir de una pila de nodos.
	 * Pre: El hijo derecho se encuentra en el tope de la pila.
	 * Pre: La pila contiene al menos dos elementos.
	 * Pos: Se ha creado una nueva igualdad
	 * Pos: Se han removido dos Nodos de la pila
	 * @param simboloP
	 * @param pila
	 * @throws NodoInvalidoExcepcion 
	 */
	public Igualdad(Stack<Nodo> pila, Ecuacion ecuacionP) throws NodoInvalidoExcepcion {
		super(SIMBOLO);
		derecha = pila.pop();
		izquierda = pila.pop();
		ecuacion = ecuacionP;
	}
	// ---------------------------------------------
	// REGLAS DE INFERENCIA
	// ---------------------------------------------
	
	public Igualdad simplificar() throws NodoInvalidoExcepcion {
		// Primero le pido a mis hijos que se simplifiquen...
		super.simplificar(ecuacion);
		// Si mis dos hijos son negativos, les cambio el signo...
		if (izquierda.esNegativo() && derecha.esNegativo()) {
			ecuacion.registrarTransformaciones(Ecuacion.SIMPLIFICACION +"-a = -a <> a = a");
			izquierda.cambiarSigno(); 
			derecha.cambiarSigno();
			ecuacion.registrarResultado();
		}
		// Si mis dos hijos son divisores, los invierto...
		if (izquierda.esDivisor() && derecha.esDivisor()) {
			ecuacion.registrarTransformaciones(Ecuacion.SIMPLIFICACION +"1/a = 1/a <> a = a");
			izquierda.invertirNodo();
			derecha.invertirNodo();
			ecuacion.registrarResultado();
		}
		// Si mis dos hijos son el mismo tipo de operador unario los simplifico...
		if (izquierda.darTipo().equals(OPERADOR_UNARIO) && izquierda.darSimbolo().equals(derecha.darSimbolo())) {
				String izq = izquierda.darSimbolo();
				String der = derecha.darSimbolo();
				izquierda = izquierda.darHijos()[0];
				derecha = derecha.darHijos()[0];
				ecuacion.registrarTransformaciones(Ecuacion.SIMPLIFICACION + izq +"(u) = "+ der +"(v) <> u = v");
				ecuacion.registrarResultado();
		}
		return this;
	}
	
	public Igualdad atraccion() throws NodoInvalidoExcepcion, ProgramacionExcepcion {
		Stack<Nodo> contenedora = new Stack<Nodo>();
		boolean continuar = true;
		// Voy a realizar atracción por la derecha hasta sacar todas las incognitas...
		while (continuar) {
			derecha = derecha.atraccion(contenedora, true);
			if (derecha == null) derecha = new Parametro(0);
			if (!contenedora.empty()) {
				Nodo tempNodo = contenedora.pop();
				tempNodo.cambiarSigno();
				izquierda = new Suma(izquierda, tempNodo);
				ecuacion.registrarTransformaciones(Ecuacion.ATRACCION + "a = b + c <> a - c = b");
				ecuacion.registrarResultado();
				if (!contenedora.empty()) 
					throw new ProgramacionExcepcion("Igualdad.atraccion(Stack<Nodo> contenedora): La contenedora debía estar vacía"); 
			} else continuar = false;
		}
		return this;
	}
	
	public Igualdad aislamiento() throws NodoInvalidoExcepcion, ProgramacionExcepcion {
		Stack<Nodo> contenedora = new Stack<Nodo>();
		boolean continuar = true;
		// Voy a realizar aislamiento por la izquierda hasta sacar todos los parámetros que no son incógnitas...
		// Sacando multiplicaciones...
		do {	
			// Sacando sumas...
			do {
				izquierda = izquierda.atraccion(contenedora, false);
				if (izquierda == null) throw new ProgramacionExcepcion("Igualdad.aislamiento(Stack<Nodo> contenedora): " +
						"Debía haber al menos una incognita al lado izquierdo, sin embargo no quedó ninguna");
				if (!contenedora.empty()) {
					Nodo tempNodo = contenedora.pop();
					tempNodo.cambiarSigno();
					derecha = new Suma(derecha, tempNodo);
					ecuacion.registrarTransformaciones(Ecuacion.AISLAMIENTO + "a + b = c <> a = c - b");
					ecuacion.registrarResultado();
					if (!contenedora.empty()) 
						throw new ProgramacionExcepcion("Igualdad.aislamiento(Stack<Nodo> contenedora): La contenedora debía estar vacía"); 
				}
			} while (!contenedora.empty());
			
			if (izquierda.darSimbolo().equals(Multiplicacion.MULTIPLICACION)) {
				Nodo[] tempHijos = izquierda.darHijos();
				if (!tempHijos[0].contieneIncognita()) {
					tempHijos[0].invertirNodo();
					Multiplicacion tempMultiplicacion = new Multiplicacion(derecha,tempHijos[0]);
					derecha = tempMultiplicacion;
					izquierda = tempHijos[1];
					ecuacion.registrarTransformaciones(Ecuacion.AISLAMIENTO + "a*b = c <> a = c/b");
					ecuacion.registrarResultado();
				} else if (!tempHijos[1].contieneIncognita()) {
					tempHijos[1].invertirNodo();
					Multiplicacion tempMultiplicacion = new Multiplicacion(derecha,tempHijos[1]);
					derecha = tempMultiplicacion;
					izquierda = tempHijos[0];
					ecuacion.registrarTransformaciones(Ecuacion.AISLAMIENTO + "a*b = c <> a = c/b");
					ecuacion.registrarResultado();
				} else continuar = false; // Si llegué aquí es porque los dos contienen la incógita... Aquí se debían tratar de reconocer expresiones factorizadas
			} else continuar = false;
			simplificar();
		} while (continuar && izquierda.darSimbolo().equals(Multiplicacion.MULTIPLICACION)); // Hasta aquí solo se procesan operadores binarios.
		/*
		 * El reconocimiento de operadores unarios debía ir aquí... la implementación sería aplicar 
		 * el inverso del operador detectado sobre los dos hijos y llamar el método de simplificacion(),
		 * el resultado sería la eliminación del operador binario del lado izquierdo y la conservación del 
		 * inverso operando todo el subárbol derecho.
		 */
		return this;
	}
	
	public Igualdad coleccionar() throws NodoInvalidoExcepcion {
		super.coleccionar(ecuacion);
		if (izquierda.darTipo().equals(OPERADOR_BINARIO) && izquierda.darSimbolo().equals(derecha.darSimbolo())) {
			Nodo[] aColeccionar = new Nodo[2];
			Nodo[] coleccionado = new Nodo[1];
			if (izquierda.coleccionable(derecha, aColeccionar, coleccionado, new Stack<Nodo>())) {
				ecuacion.registrarTransformaciones(Ecuacion.COLECCION+"a"+izquierda.darSimbolo()+"u = b"+izquierda.darSimbolo()+"u <> a = b");
				izquierda = aColeccionar[0];
				derecha = aColeccionar[1];
				ecuacion.registrarResultado();
			}
		}
		return this;
	}
}
