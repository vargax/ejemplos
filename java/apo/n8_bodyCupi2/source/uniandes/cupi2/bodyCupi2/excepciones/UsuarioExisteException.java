package uniandes.cupi2.bodyCupi2.excepciones;

/**
 * Excepción en el caso que un usuario ya haya sido registrado en la aplicación
 */
public class UsuarioExisteException extends Exception
{

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Construye una excepción con un mensaje explicando que el usuario ya existe
     * @param nombreUsuario El nombre del usuario que ya existe
     */
    public UsuarioExisteException( String nombreUsuario )
    {
        super( "El usuario " + nombreUsuario + " ya existe" );
    }
    
}
