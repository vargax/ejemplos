package vrgx.dialgo.proyectoFinal.ecuacion;

import java.util.Stack;

import vrgx.dialgo.proyectoFinal.ecuacion.operaciones.Multiplicacion;
import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;

/**
 * Representa un parámetro en la ecuación
 * @author cvargasc
 *
 */
public class Parametro extends Nodo {
	// ---------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------
	/**
	 * Verdadero si el Parámetro representa la incognita de la ecuación.
	 */
	private boolean incognita;
	/**
	 * Contiene el valor del parámetro, en caso de ser un número
	 */
	private double valor;
	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	/**
	 * Crea un nuevo Parámetro
	 * @param incognitaP: La incognita de la ecuación
	 * @param simboloP: El simbolo que representará el parámetro
	 */
	public Parametro(String incognitaP, String simboloP) {
		super(simboloP, Nodo.PARAMETRO);
		incognita = incognitaP.equals(simboloP);
	}
	/**
	 * Crea un parámetro que representa un número
	 */
	public Parametro(double numero) {
		super(""+Math.abs(numero), Nodo.NUMERO);
		if (numero < 0) super.cambiarSigno();
		incognita = false;
		valor = numero;
	}
	// ---------------------------------------------
	// REGLAS DE INFERENCIA
	// ---------------------------------------------
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#coleccionar()
	 */
	@Override
	public Nodo simplificar(Ecuacion ecuacion) throws NodoInvalidoExcepcion {
		return this;
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#atraccion(vrgx.dialgo.proyectoFinal.ecuacion.Nodo)
	 */
	@Override
	public Nodo atraccion(Stack<Nodo> contenedora, boolean atraccion) {
		// Si sí soy la incógnita, le indico a mi padre que debemos subir...
		// Si no soy la incognita, le indico a mi padre que deje las cosas como están...
		if ((atraccion && incognita) || (!atraccion && !incognita)) {
			contenedora.push(this);
			return null;
		}
		return this;
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#coleccionar(vrgx.dialgo.proyectoFinal.ecuacion.Ecuacion)
	 */
	@Override
	public Nodo coleccionar(Ecuacion ecuacion) throws NodoInvalidoExcepcion {
		return this;
	}
	// ---------------------------------------------
	// METODOS AUXILIARES
	// ---------------------------------------------
	@Override
	public boolean coleccionable(Nodo candidato, Nodo[] aColeccionar, Nodo[] coleccionado, Stack<Nodo> restantes) throws NodoInvalidoExcepcion {
		Multiplicacion tempMultiplicacion = new Multiplicacion(new Parametro(1), this);
		return tempMultiplicacion.coleccionable(candidato, aColeccionar, coleccionado, restantes);
	}
	// ---------------------------------------------
	// METODOS CONTROL
	// ---------------------------------------------
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#contieneIncognita()
	 */
	@Override
	public boolean contieneIncognita() {
		return incognita;
	}
	/**
	 * Entrega el valor del parámetro, en caso de ser un número
	 */
	public double darValor() {
		double respuesta = valor;
		if (esNegativo()) respuesta = -respuesta;
		if (esDivisor()) respuesta = 1/respuesta;
		return respuesta;
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#recorrerInOrden()
	 */
	@Override
	public String recorrerInOrden() {
		return darSimbolo();
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#recorrerPosOrden()
	 */
	@Override
	public String recorrerPosOrden() {
		return darSimbolo();
		
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#darHijos()
	 */
	public Nodo[] darHijos() {
		Nodo[] respuesta = {this};
		return respuesta;
	}
}
