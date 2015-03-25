package vrgx.dialgo.proyectoFinal.ecuacion;

import java.util.EmptyStackException;
import java.util.Stack;

import vrgx.dialgo.proyectoFinal.excepciones.NodoInvalidoExcepcion;
import vrgx.dialgo.proyectoFinal.excepciones.ProgramacionExcepcion;

public abstract class OperadorUnario extends Nodo {
	// ---------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------
	/**
	 * El nodo hijo
	 */
	protected Nodo hijo;
	// ---------------------------------------------
	// CONSTRUCTORES
	// ---------------------------------------------
	/**
	 * Crea un nuevo operador unario a partir del símbolo y sus hijos.
	 * @param simboloP
	 * @param nodoHijo
	 * @throws NodoInvalidoExcepcion
	 */
	public OperadorUnario(String simboloP, Nodo nodoHijo) throws NodoInvalidoExcepcion {
		super(simboloP, OPERADOR_UNARIO);
		if (nodoHijo != null)
			hijo = nodoHijo;
		else throw new NodoInvalidoExcepcion("El hijo de un operador unario debe ser diferente de null");
	}
	/**
	 * Crea un nuevo operador unario a partir de una pila de nodos.
	 * Pre: El hijo se encuentra en el tope de la pila.
	 * Pre: La pila contiene al menos un elemento.
	 * Pos: Se ha creado un nuevo operador unario
	 * Pos: Se ha removido un Nodo de la pila
	 * @param simboloP
	 * @param pila
	 * @throws NodoInvalidoExcepcion 
	 */
	public OperadorUnario(String simboloP, Stack<Nodo> pila) throws NodoInvalidoExcepcion {
		super(simboloP, Nodo.OPERADOR_UNARIO);
		try {
			hijo = pila.pop();
		} catch (EmptyStackException e) {
			throw new NodoInvalidoExcepcion("Para un operador unario la pila debe contener al menos un elemento" +e.getMessage());
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
		Nodo tempNodo = hijo.simplificar(ecuacion);
		if (hijo != tempNodo) {
			hijo = tempNodo;
			ecuacion.registrarResultado();
		}
		return this;
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#atraccion(vrgx.dialgo.proyectoFinal.ecuacion.Nodo)
	 */
	@Override
	public Nodo atraccion(Stack<Nodo> contenedora, boolean atraccion) throws NodoInvalidoExcepcion, ProgramacionExcepcion {
		if (atraccion)
			if (contieneIncognita()) {
				contenedora.push(this);
				return null;
			}
		else
			if (!contieneIncognita()) {
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
		Nodo tempNodo = hijo.coleccionar(ecuacion);
		if (hijo != tempNodo) {
			hijo = tempNodo;
			ecuacion.registrarResultado();
		}
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
		return hijo.contieneIncognita();
	}
	// ---------------------------------------------
	// MÉTODOS
	// ---------------------------------------------
	public String recorrerInOrden() {
		return darSimbolo()+"("+hijo.recorrerInOrden()+")";
	}
	public String recorrerPosOrden() {
		return hijo.recorrerPosOrden() +" "+ darSimbolo();
	}
	/* (non-Javadoc)
	 * @see vrgx.dialgo.proyectoFinal.ecuacion.Nodo#darHijos()
	 */
	public Nodo[] darHijos() {
		Nodo[] respuesta = {hijo};
		return respuesta;
	}
}
