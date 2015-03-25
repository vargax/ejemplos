package uniandes.cupi2.bodyCupi2.excepciones;
/**
 * Excepción en caso que el usuario no haya sido registrado en la aplicación
 */
public class UsuarioNoExisteException extends Exception {

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Construye una excepción con un mensaje explicando que el usuario no existe
     * @param id El id del usuario opr el cual se está preguntando
     */
    public UsuarioNoExisteException( int id ) {
        super( "El usuario con id " + id + " no existe" );
    }
    
}
