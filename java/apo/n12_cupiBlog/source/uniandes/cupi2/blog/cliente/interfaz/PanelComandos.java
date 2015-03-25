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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Panel de manejo de extensiones
 */
public class PanelComandos extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para ingresar al sistema
     */
    private static final String INGRESAR = "INGRESAR USUARIO";

    /**
     * Comando para registrarse en el sistema
     */
    private static final String REGISTRAR_USUARIO = "REGISTRAR USUARIO";

    /**
     * Comando para publicar un artículo
     */
    private static final String PUBLICAR_ARTICULO = "PUBLICAR_ARTICULO";

    /**
     * Comando para buscar un artículo por el nombre de la categoría
     */
    private static final String BUSCAR_ARTICULO_CATEGORIA = "BUSCAR_ARTICULO_CATEGORIA";

    /**
     * Comando para buscar un artículo por el nombre de la categoría
     */
    private static final String LISTAR_TODOS_ARTICULOS = "LISTAR_TODOS_ARTICULOS";
    
    /**
     * Comando para mostrar las estadísticas del usuario
     */
    private static final String ESTADISTICAS = "ESTADISTICAS";

    /**
     * Comando para cerrar la sesión del usuario
     */
    private static final String CERRAR_SESION = "CERRAR_SESION";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazClienteBlog principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón para iniciar sesión
     */
    private JButton btnIniciarSesion;

    /**
     * Botón para registrar un Usuario
     */
    private JButton btnRegistrarUsuario;

    /**
     * Botón para publicar un artículo
     */
    private JButton btnPublicarArticulo;

    /**
     * Botón para buscar un artículo por la categoría
     */
    private JButton btnBuscarCategoria;

    /**
     * Botón para listar todos los artículos
     */
    private JButton btnListarTodos;
    
    /**
     * Botón para mostrar las estadísticas
     */
    private JButton btnEstadisticas;

    /**
     * Botón para cerrar la sesión del usuario
     */
    private JButton btnCerrarSesion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con los comandos básicos del blog
     * @param ventana La interfaz del blog. ventana != null
     */
    public PanelComandos( InterfazClienteBlog ventana )
    {
        principal = ventana;

        setBorder( null );
        setLayout( new GridLayout( 7, 1 ) );

        // Botón para iniciar sesión
        btnIniciarSesion = new JButton( "Iniciar" );
        btnIniciarSesion.setActionCommand( INGRESAR );
        btnIniciarSesion.addActionListener( this );
        add( btnIniciarSesion );

        // Botón para registrar un Usuario
        btnRegistrarUsuario = new JButton( "Registrar" );
        btnRegistrarUsuario.setActionCommand( REGISTRAR_USUARIO );
        btnRegistrarUsuario.addActionListener( this );
        add( btnRegistrarUsuario );

        // Botón para publicar un artículo
        btnPublicarArticulo = new JButton( "Publicar" );
        btnPublicarArticulo.setActionCommand( PUBLICAR_ARTICULO );
        btnPublicarArticulo.setEnabled( false );
        btnPublicarArticulo.addActionListener( this );
        add( btnPublicarArticulo );

        // Botón para buscar artículos por categoría
        btnBuscarCategoria = new JButton( "Buscar" );
        btnBuscarCategoria.setActionCommand( BUSCAR_ARTICULO_CATEGORIA );
        btnBuscarCategoria.setEnabled( false );
        btnBuscarCategoria.addActionListener( this );
        add( btnBuscarCategoria );

        // Botón para listar todos los artículos
        btnListarTodos = new JButton( "Listar" );
        btnListarTodos.setActionCommand( LISTAR_TODOS_ARTICULOS );
        btnListarTodos.setEnabled( false );
        btnListarTodos.addActionListener( this );
        add( btnListarTodos );

        // Botón para mostrar las estadísticas del usuario
        btnEstadisticas = new JButton( "Estadisticas" );
        btnEstadisticas.setActionCommand( ESTADISTICAS );
        btnEstadisticas.setEnabled( false );
        btnEstadisticas.addActionListener( this );
        add( btnEstadisticas );

        // Botón para cerrar la sesión del Usuario
        btnCerrarSesion = new JButton( "Cerrar" );
        btnCerrarSesion.setActionCommand( CERRAR_SESION );
        btnCerrarSesion.setEnabled( false );
        btnCerrarSesion.addActionListener( this );
        add( btnCerrarSesion );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Activa y desactiva si la sesión está abierta.
     * @param abierta El estado de la sesión.
     */
    public void sesionAbierta( boolean abierta ){
        btnIniciarSesion.setEnabled( !abierta );
        btnRegistrarUsuario.setEnabled( !abierta );
        btnPublicarArticulo.setEnabled( abierta );
        btnBuscarCategoria.setEnabled( abierta );
        btnListarTodos.setEnabled( abierta );
        btnEstadisticas.setEnabled( abierta );
        btnCerrarSesion.setEnabled( abierta );
    }
    
    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento. e != null
     */
    public void actionPerformed( ActionEvent e )
    {
        if( INGRESAR.equals( e.getActionCommand( ) ) )
        {
            String nombreUsuario = JOptionPane.showInputDialog( null, "Nombre de usuario:", "Iniciar Sesión", JOptionPane.QUESTION_MESSAGE );
            if(nombreUsuario != null){
                principal.iniciarSesion( nombreUsuario );
            }
        }
        else if( REGISTRAR_USUARIO.equals( e.getActionCommand( ) ) )
        {
            DialogoRegistrarUsuario dialogo = new DialogoRegistrarUsuario( principal );
            dialogo.setVisible( true );
        }
        else if( PUBLICAR_ARTICULO.equals( e.getActionCommand( ) ) )
        {
            DialogoPublicarArticulo dialogo = new DialogoPublicarArticulo( principal );
            dialogo.setVisible( true );
        }
        else if( BUSCAR_ARTICULO_CATEGORIA.equals( e.getActionCommand( ) ) )
        {
            String categoria = JOptionPane.showInputDialog( null, "Nombre de la categoria:", "Buscar Articulo", JOptionPane.QUESTION_MESSAGE );
            if(categoria != null){
                principal.buscarArticuloPorCategoria( categoria );
            }
        }
        else if( LISTAR_TODOS_ARTICULOS.equals( e.getActionCommand( ) ) )
        {
            principal.listarTodosArticulos( );
        }
        else if( ESTADISTICAS.equals( e.getActionCommand( ) ) )
        {
            principal.mostrarEstadisticas( );
        }
        else if( CERRAR_SESION.equals( e.getActionCommand( ) ) )
        {
            principal.cerrarSesion( );
        }
        
    }

}
