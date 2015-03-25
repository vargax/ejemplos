/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - Apr 25, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.colegioweb.mundo;

/**
 * Excepción generada en el manejo de los datos
 */
@SuppressWarnings("serial")
public class DatosException extends Exception
{

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepción
     * @param mensaje Mensaje de la excepción
     */
    public DatosException( String mensaje )
    {
        super( mensaje );
    }

    /**
     * Constructor de la excepción
     * @param mensaje Mensaje de la excepción
     * @param interna Excepción Interna
     */
    public DatosException( String mensaje, Exception interna )
    {
        super( mensaje, interna );
    }

}
