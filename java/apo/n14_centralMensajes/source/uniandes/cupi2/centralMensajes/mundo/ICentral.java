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

import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Interfaz que modela el comportamiento de una central de mensajes.
 */
public interface ICentral
{

    /**
     * Envía un mensaje de un usuario a otro. <br>
     * <b>pre: </b>Los clientes seleccionados están registrados en la central.<br>
     * <b>pos: </b>Al destinatario se le dio acceso al mensaje y le queda registrado como no leído.
     * @param idClienteOrigen Identificador del cliente que envía el mensaje - idClienteOrigen != null.
     * @param idClienteDestino Identificador del cliente al que se le envía el mensaje - idClienteDestino != null.
     * @param mensaje Contenido del mensaje en bytes - mensaje != null.
     * @param codificacion Clave de codificación que usa el mensaje - codificacion != null y es una clave válida.
     */
    public void enviarMensaje( String idClienteOrigen, String idClienteDestino, String mensaje, String codificacion );

    /**
     * Recupera el contenido codificado de un mensaje. <br>
     * <b>pre: </b> El cliente y mensaje seleccionados están registrados en la central.<br>
     * @param llaveCliente Identificador del cliente que recupera el mensaje - idCliente != null.
     * @param llaveMensaje Identificador del mensaje a recuperar - llaveMensaje != null.
     * @param codificacion Clave de codificación que se usará para descifrar el mensaje - codificacion != null y es una clave válida.
     * @return El contenido descifrado del mensaje.
     */
    public String recuperarMensaje( String llaveCliente, String llaveMensaje, String codificacion );

    /**
     * Elimina el mensaje asociado a la llave especificada. Al cliente asociado a la llave especificada se le elimina la llave del mensaje. <br>
     * <b>pre: </b> El cliente y mensaje seleccionados están registrados en la central.<br>
     * <b>pos: </b> El mensaje seleccionado fue eliminado de la central, y al cliente se le eliminó la llave del mensaje.
     * @param llaveMensaje Llave del mensaje que se quiere eliminar - llaveMensaje != null.
     * @param llaveCliente Llave del cliente al que se le elimina de su lista la llave del mensaje - llaveCliente != null.
     */
    public void eliminarMensaje( String llaveMensaje, String llaveCliente );

    /**
     * Retorna los nombres de las claves.
     * @return Los nombres de las claves.
     */
    public Iterador<String> darCodificaciones( );

    /**
     * Retorna los identificadores de los clientes.
     * @return Los identificadores de los clientes.
     */
    public Iterador<String> darClientes( );

    /**
     * Retorna la cantidad de clientes registrados.
     * @return La cantidad de clientes registrados.
     */
    public int darCantidadClientes( );

    /**
     * Retorna las llaves de los mensajes recibido por un cliente que no han sido leídos por éste. <br>
     * <b>pre: </b>El cliente seleccionado está registrado en la central.
     * @param cliente Identificador del cliente seleccionado - cliente != null.
     * @return Las llaves de los mensajes no leídos del cliente seleccionado.
     */
    public Iterador<String> darLlavesMensajesNoLeidos( String cliente );

    /**
     * Retorna las llaves de todos los mensajes recibos por un cliente.<br>
     * <b>pre: El cliente seleccionado está registrado en la central.</b>
     * @param cliente Identificador del cliente seleccionado - cliente != null.
     * @return Las llaves de los mensajes recibido por el cliente seleccionado.
     */
    public Iterador<String> darLlavesMensajes( String cliente );

    /**
     * Marca un mensaje como leído.<br>
     * <b>pre: </b>El cliente y mensaje seleccionado están registrados en la central y están asociados.<br>
     * <b>pos: </b>El mensaje seleccionado ya no aparece como no leído para el cliente seleccionado.
     * @param cliente Identificador del destinatario del mensaje - cliente != null.
     * @param llaveMensaje Llave del mensaje a marcar - llaveMensaje != null.
     */
    public void marcarMensajeLeido( String cliente, String llaveMensaje );

    /**
     * Retorna el remitente de un mensaje. <br>
     * <b>pre: </b>El mensaje seleccionado está registrado en la central.<br>
     * @param llaveMensaje Llave del mensaje del que se quiere conocer el remitente - llaveMensaje != null.
     * @return El remitente del mensaje seleccionado.
     */
    public String darRemitenteMensaje( String llaveMensaje );

    /**
     * Crear un nuevo cliente. <br>
     * <b>pos: </b>Se registra el nuevo cliente. Si ya existía un cliente con ese identificados, éste es reemplazado.
     * @param identificador Identificador del cliente - identificador != null.
     */
    public void nuevoCliente( String identificador );

}
