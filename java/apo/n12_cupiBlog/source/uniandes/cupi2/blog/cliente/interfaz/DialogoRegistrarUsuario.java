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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Clase que representa el diálogo donde se registra un usuario al blog
 */
public class DialogoRegistrarUsuario extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Es el comando de la opción para registrar el Usuario
     */
    private static final String REGISTRAR_USUARIO = "Registrar Usuario";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal de la interfaz
     */
    private InterfazClienteBlog principal;

    /**
     * Campo para el nombre de usuario
     */
    private JTextField txtNombreUsuario;
    
    /**
     * Campo para los nombres de usuario
     */
    private JTextField txtNombres;
    
    /**
     * Campo para los apellidos de usuario
     */
    private JTextField txtApellidos;

    /**
     * Botón de ingresar al blog
     */
    private JButton btnIngresarBlog;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el dialogo para registrar un nuevo usuario
     * @param interfaz La interfaz del blog. interfaz != null
     */
    public DialogoRegistrarUsuario( InterfazClienteBlog interfaz )
    {
        principal = interfaz;

        setTitle( "Registrar Usuario" );
        setSize( 300, 200 );
        setLayout( new BorderLayout( ) );
        
        setLocationRelativeTo( null );

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout( new GridLayout( 3, 2, 10, 10 ) );
        panelCampos.setBorder( new TitledBorder( "Campos" ) );
        
        JLabel labNombreUsuario = new JLabel( "Nombre de Usuario:" );
        txtNombreUsuario = new JTextField( );
        panelCampos.add( labNombreUsuario );
        panelCampos.add( txtNombreUsuario );

        JLabel labNombres = new JLabel( "Nombre:" );
        txtNombres = new JTextField( );
        panelCampos.add( labNombres );
        panelCampos.add( txtNombres );
        
        JLabel labApellidos = new JLabel( "Apellidos:" );
        txtApellidos = new JTextField( );
        panelCampos.add( labApellidos );
        panelCampos.add( txtApellidos );
        
        add( panelCampos, BorderLayout.CENTER );
        
        btnIngresarBlog = new JButton( "Registrar" );
        btnIngresarBlog.setActionCommand( REGISTRAR_USUARIO );
        btnIngresarBlog.addActionListener( this );
        add( btnIngresarBlog, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Ejecuta una acción según la opción del menú que haya sido seleccionada
     * @param evento El evento de click en una opción
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if(comando.equals( REGISTRAR_USUARIO )){
            principal.registrarUsuario( txtNombreUsuario.getText( ), txtNombres.getText( ), txtApellidos.getText( ) );
            this.dispose( );
        }
    }
}
