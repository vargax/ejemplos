package uniandes.cupi2.cupIphone.componentes.excepciones;

/**
 * Excepción para indicar los errores producidos durante la 
 * ejecución de un componente
 * @author Yeisson Oviedo
 *
 */
public class EjecucionException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param mensaje Mensaje 
	 */
	public EjecucionException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor
	 * @param mensaje
	 * @param t
	 */
	public EjecucionException(String mensaje, Throwable t) {
		super(mensaje, t);
	}

}
