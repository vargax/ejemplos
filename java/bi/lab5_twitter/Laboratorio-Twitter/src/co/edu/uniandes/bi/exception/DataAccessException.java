package co.edu.uniandes.bi.exception;
/**
 * 
 */

/**
 * Excepción relacionada con el acceso a un servicio de datos
 * @author Sebastian
 *
 */
public class DataAccessException extends Exception {

	//--------------------------------------------------------------------------------------------------
	// Constantes
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Identificación de la versión de la clase
	 */
	private static final long serialVersionUID = -1529904341369314915L;

	//--------------------------------------------------------------------------------------------------
	// Constructores
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public DataAccessException() {
		super();
	}

	/**
	 * Construye una excepción con el mensaje dado
	 * @param msg el mensaje de la excepción
	 */
	public DataAccessException(String msg) {
		super(msg);
		
	}

	/**
	 * Construye una excepción a partir del objeto dado
	 * @param exc 
	 */
	public DataAccessException(Throwable exc) {
		super(exc);
	}

	/**
	 * @param msg el mensaje de la excepción
	 * @param exc 
	 */
	public DataAccessException(String msg, Throwable exc) {
		super(msg, exc);
	}

}
