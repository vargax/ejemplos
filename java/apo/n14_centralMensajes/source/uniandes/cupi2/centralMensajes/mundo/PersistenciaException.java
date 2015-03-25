/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Juan Erasmo Gómez - 6 Ago, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.mundo;

/**
 * Excepción que se arroja cuando se presenta algún error de persistencia.
 */
public class PersistenciaException extends Exception
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = 8160211976322963359L;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepción.
     * @param mensaje Mensaje de error.
     */
    public PersistenciaException( String mensaje )
    {
        super( mensaje );
    }

	/**
	 * Constructor
	 * @param mensaje
	 * @param causa
	 */
	public PersistenciaException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

}
