/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 *Autor: Luis Ricardo Ruiz Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Clase que representa el diálogo donde se hace la publicación de un artículo del blog
 */
public class DialogoPublicarArticulo extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Es el comando de la opción para publicar el artículo
     */
    private static final String PUBLICAR_ARTICULO = "Publicar Articulo";
    
    /**
     * Es el comando de la opción para cancelar la publicación del artículo
     */
    private static final String CANCELAR_PUBLICAR_ARTICULO = "Cancelacion Articulo";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal de la interfaz
     */
    private InterfazClienteBlog principal;

    /**
     * Campo para el título del artículo
     */
    private JTextField txtTituloArticulo;
    
    /**
     * Campo para la categoría del artículo
     */
    private JTextField txtCategoria;
    
    /**
     * Campo para el contenido del artículo
     */
    private JTextArea txtContenido;

    /**
     * Botón de publicar el artículo
     */
    private JButton btnAceptarPublicacion;
    
    /**
     * Botón para cancelar la publicación del artículo
     */
    private JButton btnCancelarPublicacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el dialogo para registrar un usuario
     * @param interfaz La interfaz del blog. interfaz != null
     */
    public DialogoPublicarArticulo( InterfazClienteBlog interfaz )
    {
        principal = interfaz;

        setTitle( "Publicar Artículo" );
        setSize( 400, 300 );
        setResizable( false );
        setLayout( new BorderLayout( ) );
        
        setLocationRelativeTo( null );

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout( new GridBagLayout( ) );
        panelCampos.setBorder( new TitledBorder( "Campos" ) );
        
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel labTituloArticulo = new JLabel( "Título:" );
        gbc.ipadx = 100;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add( labTituloArticulo, gbc );

        txtTituloArticulo = new JTextField( );
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCampos.add( txtTituloArticulo, gbc );

        JLabel labCategoria = new JLabel( "Categoría:" );
        gbc.ipadx = 100;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add( labCategoria, gbc );

        txtCategoria = new JTextField( );
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCampos.add( txtCategoria, gbc );
        
        JLabel labContenido = new JLabel( "Contenido:" );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        panelCampos.add( labContenido, gbc );

        txtContenido = new JTextArea( );
        txtContenido.setWrapStyleWord( true );
        JScrollPane scroll = new JScrollPane( txtContenido, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 200;
        gbc.ipady = 100;
        
        panelCampos.add( scroll, gbc );
        
        add( panelCampos, BorderLayout.CENTER );
        
        //El panel con los botones de aceptar y cancelar
        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 2 ) );
        
        btnAceptarPublicacion = new JButton( "Publicar" );
        btnAceptarPublicacion.setActionCommand( PUBLICAR_ARTICULO );
        btnAceptarPublicacion.addActionListener( this );
        panelBotones.add( btnAceptarPublicacion, BorderLayout.SOUTH );

        btnCancelarPublicacion = new JButton( "Cancelar" );
        btnCancelarPublicacion.setActionCommand( CANCELAR_PUBLICAR_ARTICULO );
        btnCancelarPublicacion.addActionListener( this );
        panelBotones.add( btnCancelarPublicacion, BorderLayout.SOUTH );

        add( panelBotones, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Ejecuta una acción según la opción del menú que haya sido seleccionada
     * @param evento El evento de click en una opción. evento != null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if(comando.equals( PUBLICAR_ARTICULO )){
        	principal.publicarArticulo(txtTituloArticulo.getText(), txtCategoria.getText(), txtContenido.getText());
            this.dispose( );
        }
    }
}
