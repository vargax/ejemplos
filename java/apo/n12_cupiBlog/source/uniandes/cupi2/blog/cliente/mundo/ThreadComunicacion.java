/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.cliente.mundo;

import uniandes.cupi2.blog.comun.Comunicacion;
import uniandes.cupi2.blog.excepciones.CupiBlogComunicacionException;


/**
 * Representa un hilo para enviar mensaje de forma asíncrona
 */
public class ThreadComunicacion extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el manejador de comunicaciones
     */
    private ComunicacionServidorBlog manejadorComunicaciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo hilo para recibir mensajes
     * @param nComunicacionServidor El manejador de comunicaciones
     */
    public ThreadComunicacion( ComunicacionServidorBlog nComunicacionServidor )
    {
        this.manejadorComunicaciones = nComunicacionServidor;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que se invoca cuando inicia la ejecución del hilo
     */
    public void run( )
    {
        while( true )
        {
            try
            {
                Comando comando = manejadorComunicaciones.recibirMensaje( );
                manejadorComunicaciones.procesarMensaje( comando );
                
                if(comando.darNombre( ).equals( Comunicacion.LOGOUT )){
                    return;
                }
            }
            catch( CupiBlogComunicacionException e )
            {
                manejadorComunicaciones.notificarExcepcion( e );
                return;
            }
            catch( Exception e )
            {
                manejadorComunicaciones.notificarExcepcion( e );
            }
        }
    }
}