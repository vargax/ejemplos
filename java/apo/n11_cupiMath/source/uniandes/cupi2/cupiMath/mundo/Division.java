package uniandes.cupi2.cupiMath.mundo;

public final class Division extends OperadorBinario {
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con el símbolo de la división
     */
	public final static String SIMBOLO = "/";
	 // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo de la división
     * @param elTipo El tipo del operador. elTipo != null
     */
	public Division(String elTipo) {
		super(elTipo);
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de aplicar el operador sobre el subárbol izquierdo y el derecho
     */
	@Override
	public double evaluar() {
		return darNodoIzquierdo().evaluar()/darNodoDerecho().evaluar();
	}
	

}
