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

package uniandes.cupi2.blog.cliente.interfaz;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.blog.cliente.mundo.ClienteBlog;
import uniandes.cupi2.blog.cliente.mundo.IEscucharEventos;
import uniandes.cupi2.blog.comun.Articulo;

/**
 * Esta es la ventana principal de la aplicación cliente del blog.
 */
public class InterfazClienteBlog extends JFrame implements IEscucharEventos
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private ClienteBlog blog;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la lista de artículos
     */
    private PanelArticulos panelArticulos;

    /**
     * Panel con la información del artículo a mostrar
     */
    private PanelArticulo panelArticulo;
    
    /**
     * Panel con los comandos del blog
     */
    private PanelComandos panelComandos;

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la interfaz del cliente del blog. <br>
     * <b>post: </b> La Interfaz es creada.
     */
    public InterfazClienteBlog( )
    {
        // Crea la clase principal
        String ipServidor = (String) JOptionPane.showInputDialog( this, "La dirección IP del servidor", "localhost" );
        if(ipServidor == null)
            this.dispose( );
        if(ipServidor.length( ) == 0)
            JOptionPane.showMessageDialog( this, "Debe ingresar la ip del Servidor al cual conectar", "Dirección ip del servidor", JOptionPane.INFORMATION_MESSAGE );

        blog = new ClienteBlog( this, ipServidor );

        // Construye la forma
        setTitle("CupiBlog");
        setLayout( new BorderLayout( ) );
        setSize( 950, 700 );
        setResizable( false );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelArticulos = new PanelArticulos( this );
        add( panelArticulos, BorderLayout.WEST );

        panelArticulo = new PanelArticulo( this );
        add( panelArticulo, BorderLayout.CENTER );

        panelComandos = new PanelComandos( this );
        add( panelComandos, BorderLayout.EAST );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        // Centrar la ventana
        setLocationRelativeTo( null );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Solicita al servidor el inicio de sesión del usuario.
     * @param nombreUsuario El nombre del usuario a ingresar. nombreUsuario != null
     */
    public void iniciarSesion( String nombreUsuario )
    {
        try
        {
            blog.iniciarSesion( nombreUsuario );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Envía al servidor la solicitud de registrar un nuevo usuario.
     * @param nombreUsuario El nombre del usuario a ingresar. nombreUsuario != null
     * @param nombre El nombre de pila del usuario. nombre != null
     * @param apellidos Los apellidos del usuario. apellidos != null
     */
    public void registrarUsuario( String nombreUsuario, String nombre, String apellidos )
    {
        try
        {
            blog.registrarUsuario( nombreUsuario, nombre, apellidos );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Actualiza la información a mostrar del artículo.
     * @param articulo El artículo a mostrar en el panel. articulo != null
     */
    public void actualizarArticulo( Articulo articulo )
    {
        try
        {
            blog.modificarArticuloActual( articulo );
            blog.solicitarComentariosArticuloActual( );
            ArrayList comentarios = blog.darListaComentariosArticuloActual( );
            panelArticulo.actualizarArticulo( articulo, comentarios );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Publica un artículo en el blog.
     * @param titulo El título del artículo. titulo != null
     * @param categoria La categoría del artículo. categoria != null
     * @param contenido El contenido del artículo. contenido != null
     */
    public void publicarArticulo( String titulo, String categoria, String contenido ) {
       try {
    	   blog.publicarArticulo(titulo, categoria, contenido);
       } catch( Exception e ) {
           JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
           e.printStackTrace( );
       }
    }

    /**
     * Publica un comentario de un artículo en el blog.
     * @param contenido El contenido del comentario. contenido != null
     */
    public void publicarComentario( String contenido ){
        try
        {
            blog.publicarComentario( contenido );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Busca los artículos que pertenezcan a la categoría introducida.
     * @param categoria La categoría a buscar. categoria != null
     */
    public void buscarArticuloPorCategoria( String categoria ){
        try
        {
            blog.buscarArticulosCategoria( categoria );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Solicita al servidor la lista de todos los artículos.
     */
    public void listarTodosArticulos(){
        try
        {
            blog.listarArticulos( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Solicita al servidor las estadísticas del usuario.
     */
    public void mostrarEstadisticas(){
        try
        {
            blog.solicitarEstadisticas( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Cierra la sesión del usuario
     */
    public void cerrarSesion(){
        try
        {
            blog.cerrarSesion( );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * El método que se llama al cerrar la aplicación
     */
    public void dispose(){
        cerrarSesion( );
        super.dispose( );
    }

    // -----------------------------------------------------------------
    // Métodos de observador
    // -----------------------------------------------------------------

    /**
     * Actualiza los eventos que suceden en el mundo.
     * @param articulos La lista de artículos. articulos != null
     */
    public void actualizarListaArticulos( ArrayList articulos )
    {
        panelArticulos.actualizarListaArticulos( articulos );
    }

    /**
     * Actualiza los eventos que suceden en el mundo.
     */
    public void actualizarComentarios( )
    {
        panelArticulo.actualizarComentarios( blog.darListaComentariosArticuloActual( ) );
    }

    /**
     * Notifica al usuario la notificación de un usuarios
     * @param mensaje El mensaje que se va a notificar. mensaje != null
     */
    public void notificarMensaje( String mensaje )
    {
        JOptionPane.showMessageDialog( this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra la información de la excepción.
     * @param e La excepción de la aplicación. e != null
     */
    public void notificarExcepcion( Exception e )
    {
        JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
    }
    
    /**
     * Notifica el inicio de sesión del usuario
     * @param estadoSesion El estado de sesión del usuario. estadoSesion != null
     */
    public void cambiarEstadoSesion( boolean estadoSesion ){
        panelComandos.sesionAbierta( estadoSesion );
        if( !estadoSesion ){
            panelArticulo.desactivar( );
            panelArticulos.limpiarLista( );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = blog.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = blog.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Los argumentos de ejecución de la aplicación
     */
    public static void main( String[] args )
    {

        InterfazClienteBlog interfaz = new InterfazClienteBlog( );
        interfaz.setVisible( true );
    }
}