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
import uniandes.cupi2.collections.lista.Lista;

/**
 * Clase que representa un cliente de la central de mensajes.
 */
public class Cliente
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Identificador del cliente.
     */
    private String id;

    /**
     * Mensajes recibidos por el cliente.
     */
    private Lista<Mensaje> mensajes;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un cliente a partir de su identificador.
     * @param nId Identificador del cliente - nId != null.
     */
    public Cliente( String nId )
    {
        id = nId;
        mensajes = new Lista<Mensaje>( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el identificador del cliente.
     * @return El identificador del cliente.
     */
    public String darIdentificador( )
    {
        return id;
    }

    /**
     * Agrega un mensaje al cliente.
     * @param mensaje Mensaje que se va agregar al cliente.
     */
    public void agregarMensaje( Mensaje mensaje )
    {
        mensajes.agregar( mensaje );
    }

    /**
     * Retorna el número de mensajes recibidos por el cliente.
     * @return El número de mensajes recibidos por el cliente.
     */
    public int darNumeroMensajesRecibidos( )
    {
        return mensajes.darLongitud( );
    }

    /**
     * Retorna las llaves de los mensajes recibidos por el cliente.
     * @return Las llaves de los mensajes recibidos por el cliente.
     */
    public Iterador<String> darLlavesMensajes( )
    {
        Lista<String> llaves = new Lista<String>( );
        for( int i = 0; i < mensajes.darLongitud( ); i++ )
            llaves.agregar( "" + mensajes.darElemento( i ).darIdentificador( ) );
        return llaves.darIterador( );
    }

    /**
     * Retorna las llaves de los mensajes no leídos del cliente.
     * @return Las llaves de los mensajes no leídos del cliente.
     */
    public Iterador<String> darLlavesMensajesNoLeidos( )
    {
        Lista<String> llaves = new Lista<String>( );
        for( int i = 0; i < mensajes.darLongitud( ); i++ )
        {
            Mensaje m = mensajes.darElemento( i );
            if( !m.fueLeido( ) )
                llaves.agregar( m.darIdentificador( ) + "" );
        }
        return llaves.darIterador( );
    }

    /**
     * Elimina un mensaje de los mensajes recibidos por el cliente.
     * @param mensaje Mensaje a eliminar.
     */
    public void eliminarMensaje( Mensaje mensaje )
    {
        mensajes.eliminar( mensaje );
    }

}
