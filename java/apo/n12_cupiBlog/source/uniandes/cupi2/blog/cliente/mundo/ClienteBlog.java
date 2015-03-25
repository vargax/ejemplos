/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_CupiBlog
 * Autor: Luis Ricardo Ruiz Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.cliente.mundo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import uniandes.cupi2.blog.comun.Articulo;
import uniandes.cupi2.blog.comun.InfoUsuario;
import uniandes.cupi2.blog.excepciones.CupiBlogComunicacionException;
import uniandes.cupi2.blog.excepciones.CupiBlogProtocoloException;

/**
 * Es la clase que interviene en las acciones del mundo del cliente.<br>
 * <b>inv: </b><br>
 * manejadorEventos != null<br>
 * articulos != null<br>
 */
public class ClienteBlog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La información de usuario del blog
     */
    private InfoUsuario usuario;

    /**
     * El artículo actual que se esta mostrando
     */
    private Articulo articuloActual;

    /**
     * La lista de artículos que se han publicado
     */
    private ArrayList articulos;

    /**
     * La lista de comentarios del artículo actual
     */
    private ArrayList comentariosArticuloActual;

    /**
     * El observador de eventos del cliente
     */
    private IEscucharEventos manejadorEventos;

    /**
     * Encargado de la comunicación con el servidor
     */
    private ComunicacionServidorBlog comunicacion;
    
    /**
     * La dirección ip del servidor
     */
    private String ipServidor;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * El constructor del cliente del blog.
     * @param pObservador El observador de los eventos del cliente. pObservador != null
     * @param pIpServidro La dirección ip del servidor al cual se va a conectar el cliente. pIpSevidor != null
     */
    public ClienteBlog( IEscucharEventos pObservador, String pIpServidor )
    {
        manejadorEventos = pObservador;
        usuario = null;
        articuloActual = null;
        ipServidor = pIpServidor;

        articulos = new ArrayList( );
        comentariosArticuloActual = new ArrayList( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la información de usuario del cliente.
     * @return La información de usuario del cliente.
     */
    public InfoUsuario darUsuario( )
    {
        return usuario;
    }

    /**
     * Modifica la información de usuario del cliente.
     * @param nUsuario La nueva información de usuario del cliente. nUsuario != null
     */
    public void modificarUsuario( InfoUsuario nUsuario )
    {
        usuario = nUsuario;
    }

    /**
     * Devuelve la información del artículo que actualmente se muestra.
     * @return La información del artículo que actualmente se muestra.
     */
    public Articulo darArticuloActual( )
    {
        return articuloActual;
    }

    /**
     * Modifica el artículo que actualmente se muestra.
     * @param nArticuloActual La nueva información de artículo. nArticuloActual != null
     */
    public void modificarArticuloActual( Articulo nArticuloActual )
    {
        articuloActual = nArticuloActual;
    }

    /**
     * Devuelve la lista de artículos publicados
     * @return La lista de artículos publicados
     */
    public ArrayList darListaArticulos( )
    {
        return articulos;
    }

    /**
     * Modifica el contenido de la lista de artículos
     * @param pArticulos La nueva lista de artículos. pArticulos != null
     */
    public void modificarListaArticulos( ArrayList pArticulos )
    {
        articulos.clear( );
        articulos.addAll( pArticulos );
        manejadorEventos.actualizarListaArticulos( articulos );
    }

    /**
     * Devuelve la lista de usuarios conectados al blog
     * @return La lista de usuarios conectados al blog
     */
    public ArrayList darListaComentariosArticuloActual( )
    {
        return comentariosArticuloActual;
    }

    /**
     * Modifica la lista de comentarios del artículo actual
     * @param pComentarios La nueva lista de comentarios del artículo actual. pComentarios != null
     */
    public void modificarListaComentariosArticuloActual( ArrayList pComentarios )
    {
        comentariosArticuloActual.clear( );
        comentariosArticuloActual.addAll( pComentarios );
        manejadorEventos.actualizarComentarios( );
    }

    /**
     * Limpia el contenido de las listas del cliente
     */
    public void reiniciar( )
    {
        usuario = null;
        articuloActual = null;

        articulos.clear( );
        comentariosArticuloActual.clear( );

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos de conexión
    // -----------------------------------------------------------------

    /**
     * Establece la conexión con el servidor. En caso de no poder lanza la excepción con el mensaje.
     * @throws CupiBlogComunicacionException En caso de no poder establecer conexión con el servidor.
     */
    private void iniciarConexion( ) throws CupiBlogComunicacionException
    {
        try
        {
            Socket socket = new Socket( ipServidor, 9999 );
            comunicacion = new ComunicacionServidorBlog( this, socket );
        }
        catch( UnknownHostException e )
        {
            throw new CupiBlogComunicacionException( "No hay conexión con el host: " + e.getMessage( ) );
        }
        catch( IOException e )
        {
            throw new CupiBlogComunicacionException( "No se ha podido establecer la conexión con el servidor: " + e.getMessage( ) );
        }
    }

    /**
     * Inicia la sesión del usuario. Lanza excepción en caso de no poder.
     * @param nombreUsuario El nombre de usuario con el cuál se va a iniciar sesión. nombreUsuario != null
     * @throws CupiBlogComunicacionException En caso de no tener comunicación con el servidor
     * @throws CupiBlogProtocoloException En caso de que el protocolo no cumple con los requerimientos
     */
    public void iniciarSesion( String nombreUsuario ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        if( comunicacion == null )
            iniciarConexion( );
        reiniciar( );
        comunicacion.iniciarSesion( nombreUsuario );
        manejadorEventos.cambiarEstadoSesion( true );
        listarArticulos( );
    }

    /**
     * Registra un usuario en el servidor e Inicia la sesión. Lanza excepción en caso de no poder.
     * @param nombreUsuario El nombre de usuario con el cuál iniciar sesión. nombreUsuario != null
     * @param nombres Los nombres del usuario. nombres != null
     * @param apellidos Los apellidos del usuario. apellidos != null
     * @throws CupiBlogComunicacionException En caso de no tener comunicación con el servidor
     * @throws CupiBlogProtocoloException En caso de que el protocolo no cumple con los requerimientos
     */
    public void registrarUsuario( String nombreUsuario, String nombres, String apellidos ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        if( comunicacion == null )
            iniciarConexion( );
        reiniciar( );
        comunicacion.registrarUsuario( nombreUsuario, nombres, apellidos );
        manejadorEventos.cambiarEstadoSesion( true );
        listarArticulos( );
    }

    /**
     * Publica un nuevo artículo dado un título, la categoria a la que pertenece y el contenido.<br>
     * <b>pre: </b>La comunicación con el servidor debe estar establecida.<br>
     * @param titulo El título del artículo. titulo != null
     * @param categoria La categoria del artículo. categoria != null
     * @param contenido El contenido del artículo. contenido != null
     * @throws CupiBlogProtocoloException En caso de que el protocolo de comunicación no sea el correcto
     * @throws CupiBlogComunicacionException En caso de que la comunicación entre el cliente y el servidor falle
     */
    public void publicarArticulo( String titulo, String categoria, String contenido ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
    	comunicacion.publicarArticulo(titulo, categoria, contenido);
    }

    /**
     * Publica un nuevo comentario al artículo actual.<br>
     * <b>pre: </b>La comunicación con el servidor debe estar establecida.<br>
     * @param contenido El contenido del comentario. contenido != null
     * @throws CupiBlogProtocoloException En caso de que el protocolo de comunicación no sea el correcto
     * @throws CupiBlogComunicacionException En caso de que la comunicación entre el cliente y el servidor falle
     */
    public void publicarComentario( String contenido ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        if(articuloActual != null){
            comunicacion.publicarComentario( articuloActual, contenido );
        }
    }

    /**
     * Lista todos los artículos del blog por orden cronológico.<br>
     * <b>pre: </b>La comunicación con el servidor debe estar establecida.<br>
     * @throws CupiBlogProtocoloException En caso de que el protocolo de comunicación no sea el correcto
     * @throws CupiBlogComunicacionException En caso de que la comunicación entre el cliente y el servidor falle
     */
    public void listarArticulos( ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        comunicacion.solicitarListaArticulos( );
    }

    /**
     * Busca los artículos que pertenecen a la categoría que llega por parámetro.<br>
     * <b>pre: </b>La comunicación con el servidor debe estar establecida.<br>
     * @param categoria La categoria del cuál buscar. contenido != null
     * @throws CupiBlogProtocoloException En caso de que el protocolo de comunicación no sea el correcto
     * @throws CupiBlogComunicacionException En caso de que la comunicación entre el cliente y el servidor falle
     */
    public void buscarArticulosCategoria( String categoria ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        comunicacion.buscarArticulosCategoria( categoria );
    }

    /**
     * Solicita los comentarios del artículo actual, si existe el artículo actual.<br>
     * <b>pre: </b>La comunicación con el servidor debe estar establecida. El usuario del cliente debe estar configurado.<br>
     * @throws CupiBlogProtocoloException En caso de que el protocolo de comunicación no sea el correcto
     * @throws CupiBlogComunicacionException En caso de que la comunicación entre el cliente y el servidor falle
     */
    public void solicitarComentariosArticuloActual( ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        if(articuloActual != null){
            comunicacion.solicitarComentariosArticulo( articuloActual );
        }
    }

    /**
     * Solicita las estadísticas de publicaciones del usuario.<br>
     * <b>pre: </b>La comunicación con el servidor debe estar establecida. El usuario del cliente debe estar configurado.<br>
     * @throws CupiBlogProtocoloException En caso de que el protocolo de comunicación no sea el correcto
     * @throws CupiBlogComunicacionException En caso de que la comunicación entre el cliente y el servidor falle
     */
    public void solicitarEstadisticas( ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        comunicacion.solicitarEstadisticasUsuario( usuario.darNombreUsuario( ) );
    }

    /**
     * Notifica al manejador de eventos la recepción de la información de publicación del usuario.
     * @param numeroArticulos El número de artículos publicados por el usuario. numeroArticulos >= 0
     * @param numeroComentarios El número de comentarios publicados por el usuario. numeroComentarios >= 0
     */
    public void notificarResultadosEstadisticas( int numeroArticulos, int numeroComentarios )
    {
        String mensaje = "El usuario ha publicado:\n";
        mensaje += numeroArticulos + " Artículos.\n";
        mensaje += numeroComentarios + " Comentarios.";

        notificarMensaje( mensaje );
    }

    /**
     * Cierra la sesión del usuario del cliente del blog.
     * @throws IOException En caso de error al comunicarse con el servidor.
     */
    public void cerrarSesion( ) throws IOException
    {
        if( comunicacion != null )
        {
            comunicacion.cerrarSesion( );
            manejadorEventos.cambiarEstadoSesion( false );
            comunicacion = null;
        }
    }

    /**
     * Notifica un nuevo mensaje proveniente de la interfaz.
     * @param mensaje El mensaje de publicar un mensaje. mensaje != null
     */
    public void notificarMensaje( String mensaje )
    {
        manejadorEventos.notificarMensaje( mensaje );
    }

    /**
     * Notifica una nueva excepción en la comunicación con el servidor.
     * @param e La excepción que ocurrió con la comunicación con el servidor.
     */
    public void notificarExcepcion( Exception e )
    {
        manejadorEventos.notificarExcepcion( e );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica la invariante de la clase <b>inv: </b> manejadorEventos != null<br>
     * articulos != null<br>
     */
    private void verificarInvariante( )
    {
        assert manejadorEventos != null : "El manejador de Eventos debe existir";
        assert articulos != null : "La lista de los artículos debe estar inicializada";
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}