package vrgx.dialgo.proyectoFinal.ecuacion;

import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;

public abstract class OperadorBinario extends Nodo {
	// ---------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------
	/**
	 * El hijo izquierdo
	 */
	protected Nodo izquierda;
	/**
	 * El hijo derecho
	 */
	protected Nodo derecha;
	// ---------------------------------------------
	// CONSTRUCTORES
	// ---------------------------------------------
	public OperadorBinario(String simboloP) {
		super(simboloP, OPERADOR_BINARIO);
	}
	// ---------------------------------------------
	// REGLAS DE INFERENCIA
	// ---------------------------------------------
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#simplificar()
	 */
	@Override
	public Nodo simplificar(Ecuacion ecuacion) throws NodoInvalidoExcepcion {
		Nodo tempIzquierda = izquierda.simplificar(ecuacion);
		if (tempIzquierda != izquierda) {
			izquierda = tempIzquierda;
			ecuacion.registrarResultado();
		}
		Nodo tempDerecha = derecha.simplificar(ecuacion);
		if (tempDerecha != derecha) {
			derecha = tempDerecha;
			ecuacion.registrarResultado();
		}
		return this;
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#coleccionar(vrgx.dialgo.proyectoFinal.ecuacion.Ecuacion)
	 */
	@Override
	public Nodo coleccionar(Ecuacion ecuacion) throws NodoInvalidoExcepcion {
		Nodo tempIzquierda = izquierda.coleccionar(ecuacion);
		if (tempIzquierda != izquierda) {
			izquierda = tempIzquierda;
			ecuacion.registrarResultado();
		}
		Nodo tempDerecha = derecha.coleccionar(ecuacion);
		if (tempDerecha != derecha) {
			derecha = tempDerecha;
			ecuacion.registrarResultado();
		}
		simplificar(ecuacion);
		return this;
	}
	// ---------------------------------------------
	// METODOS AUXILIARES
	// ---------------------------------------------
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#contieneIncognita()
	 */
	@Override
	public boolean contieneIncognita() {
		return izquierda.contieneIncognita() || derecha.contieneIncognita();
	}
	// ---------------------------------------------
	// METODOS CONTROL
	// ---------------------------------------------
	public String recorrerInOrden() {
		return izquierda.recorrerInOrden() +" "+ darSimbolo() +" "+ derecha.recorrerInOrden();
	}
	public String recorrerPosOrden() {
		return izquierda.recorrerPosOrden() +" "+ derecha.recorrerPosOrden() +" "+ darSimbolo();
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#darHijos()
	 */
	public Nodo[] darHijos() {
		Nodo[] respuesta = {izquierda, derecha};
		return respuesta;
	}
}
