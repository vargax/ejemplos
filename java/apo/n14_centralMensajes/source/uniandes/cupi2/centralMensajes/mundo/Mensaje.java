/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Pablo Marquez - 13 Jun, 2007
 * Autor: Juan Erasmo Gómez - 6 Ago, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.mundo;

/**
 * Clase que representa un mensaje dentro de la central.
 */
public class Mensaje
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Identificador del mensajes.
     */
    private int id;

    /**
     * Booleano que representa si se ha leído el mensaje.
     */
    private boolean leido;

    /**
     * Texto codificado del mensaje.
     */
    private String texto;

    /**
     * Llave del cliente que envió el mensaje.
     */
    private String origen;

    /**
     * Llave del cliente que recibe el mensaje.
     */
    private String destino;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del mensaje.
     * @param nId Identificador del mensaje.
     * @param nOrigen Llave del cliente que envió el mensaje - nOrigen != null.
     * @param nDestino Llave del cliente que recibe el mensaje - nDestion != null.
     * @param nTexto Texto codificado del mensaje - nTexto != null.
     */
    public Mensaje( int nId, String nOrigen, String nDestino, String nTexto )
    {
        id = nId;
        leido = false;
        texto = nTexto;
        origen = nOrigen;
        destino = nDestino;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el identificador del mensaje.
     * @return El identificador del mensaje.
     */
    public int darIdentificador( )
    {
        return id;
    }

    /**
     * Retorna el texto codificado del mensaje.
     * @return El texto codificado del mensaje.
     */
    public String darTexto( )
    {
        return texto;
    }

    /**
     * Retorna la llave del cliente que envió el mensaje.
     * @return La llave del cliente que envió el mensaje.
     */
    public String darIdOrigen( )
    {
        return origen;
    }

    /**
     * Retorna la llave del cliente que recibe el mensaje.
     * @return La llave del cliente que recibe el mensaje.
     */
    public String darIdDestino( )
    {
        return destino;
    }

    /**
     * Verifica si el mensaje fue leído.
     * @return true si el mensaje fue leído o false en caso contrario.
     */
    public boolean fueLeido( )
    {
        return leido;
    }

    /**
     * Cambia el estado del mensaje.
     * @param nLeido nuevo estado del mensaje<br>
     *        <li>true si el mensaje pasa a leído. <li>false si el mensaje pasa a no leído.
     */
    public void cambiarEstadoMensaje( boolean nLeido )
    {
        leido = nLeido;
    }

}
