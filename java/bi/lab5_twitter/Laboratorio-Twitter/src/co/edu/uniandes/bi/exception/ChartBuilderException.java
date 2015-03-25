package co.edu.uniandes.bi.exception;
/**
 * 
 */

/**
 * Excepción relacionada con la creación de un gráfico
 * @author Sebastian
 *
 */
public class ChartBuilderException extends Exception {

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
	public ChartBuilderException() {
		super();
	}

	/**
	 * Construye una excepción con el mensaje dado
	 * @param msg el mensaje de la excepción
	 */
	public ChartBuilderException(String msg) {
		super(msg);
		
	}

	/**
	 * Construye una excepción a partir del objeto dado
	 * @param exc 
	 */
	public ChartBuilderException(Throwable exc) {
		super(exc);
	}

	/**
	 * @param msg el mensaje de la excepción
	 * @param exc 
	 */
	public ChartBuilderException(String msg, Throwable exc) {
		super(msg, exc);
	}

}
