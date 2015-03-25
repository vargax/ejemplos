package uniandes.cupi2.bodyCupi2.excepciones;

/**
 * Excepción al tratar de cargar datos de un archivo que no cumple con el formato definido
 *
 */
public class FormatoArchivoException extends Exception
{
    // -----------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // ----------------------------------------------
    // Métodos
    // ----------------------------------------------

    /**
     * Constructor de la excepción.
     * @param mensaje Mensajes que se muestra cuando se lanza la excepción.
     */
    public FormatoArchivoException( String mensaje )
    {
        super( mensaje );
    }
}
