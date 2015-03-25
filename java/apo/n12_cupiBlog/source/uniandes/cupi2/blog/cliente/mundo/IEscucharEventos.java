/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiCourier
 * Autor: Luis Ricardo Ruiz Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.cliente.mundo;

import java.util.ArrayList;

/**
 * Interfaz que representa los elementos que escuchar los eventos que llegan del servidor
 */
public interface IEscucharEventos
{

    /**
     * Método que notifica una actualización de la lista de los artículos
     * @param articulos La lista de artículos obtenida. articulos != null
     */
    public void actualizarListaArticulos( ArrayList articulos );
    
    /**
     * Método que notifica una actualización de la lista de los comentarios.
     */
    public void actualizarComentarios( );
    
    /**
     * Notifica el inicio de sesión del usuario
     * @param estadoSesion El estado de sesión del usuario. estadoSesion != null
     */
    public void cambiarEstadoSesion( boolean estadoSesion );

    /**
     * Método que notifica una actualización de un mensaje
     * @param mensaje El mensaje del cual se debe notificar. mensaje != null
     */
    public void notificarMensaje( String mensaje );

    /**
     * Método que notifica una excepción
     * @param e La excepción que llega en el mensaje
     */
    public void notificarExcepcion( Exception e );
}
