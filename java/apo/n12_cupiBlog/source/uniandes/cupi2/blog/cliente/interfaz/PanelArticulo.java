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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import uniandes.cupi2.blog.comun.Articulo;
import uniandes.cupi2.blog.comun.Comentario;

/**
 * Panel donde se muestra el contenido y la información del artículo
 */
public class PanelArticulo extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Es el comando de la opción para comentar el artículo
     */
    private static final String COMENTAR_ARTICULO = "Comentar artículo";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La interfaz principal de la aplicación
     */
    private InterfazClienteBlog principal;

    /**
     * La etiqueta con el título del artículo
     */
    private JLabel labTitulo;

    /**
     * La etiqueta con el nombre de usuario del autor del artículo
     */
    private JLabel labUsuario;

    /**
     * La etiqueta con la fecha de publicación del ejercicio
     */
    private JLabel labFechaPublicacion;

    /**
     * La etiqueta con la categoría del artículo
     */
    private JLabel labCategoria;

    /**
     * El campo con el contenido del artículo
     */
    private JTextArea txtContenido;

    /**
     * El panel donde guardo la lista de comentarios
     */
    private JPanel panelComentarios;
    
    /**
     * El scroll con los comentarios
     */
    private JScrollPane scrollComentarios;

    /**
     * El campo con el comentario del artículo
     */
    private JTextArea txtComentario;

    /**
     * El botón encargado de comentar el artículo
     */
    private JButton btnComentarArticulo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por defecto. Crea el panel del contenido del artículo.
     * @param interfaz La interfaz principal de la aplicación del cliente. interfaz != null
     */
    public PanelArticulo( InterfazClienteBlog interfaz )
    {
        principal = interfaz;

        setLayout( new BorderLayout( ) );

        // El panel con el contenido de la información del artículo
        JPanel panelContenido = new JPanel( );
        panelContenido.setPreferredSize( new Dimension( 350, 250 ) );
        panelContenido.setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( );

        // Ubicando el título
        labTitulo = new JLabel( "El Titulo del Artículo", SwingConstants.RIGHT );
        labTitulo.setHorizontalAlignment( JLabel.RIGHT );
        labTitulo.setFont( new Font( "Verdana", Font.BOLD, 24 ) );
        labTitulo.setBackground( Color.BLUE );

        gbc.gridwidth = 3;
        gbc.weightx = 0.7;
        gbc.ipadx = 320;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 10, 0, 0 );
        panelContenido.add( labTitulo, gbc );

        // Ubicando el nombre del Autor
        labUsuario = new JLabel( "El Nombre del Autor" );
        labUsuario.setFont( new Font( "Arial", Font.BOLD, 15 ) );

        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.ipadx = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets( 0, 0, 0, 0 );
        panelContenido.add( labUsuario, gbc );

        // Ubicando la fecha de publicación
        labFechaPublicacion = new JLabel( "Fecha de publicación" );
        labFechaPublicacion.setHorizontalAlignment( JLabel.RIGHT );
        labFechaPublicacion.setFont( new Font( "Arial", Font.BOLD, 12 ) );

        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.ipadx = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        panelContenido.add( labFechaPublicacion, gbc );

        // Ubicando la categoría
        labCategoria = new JLabel( "Categoria" );
        labCategoria.setHorizontalAlignment( JLabel.RIGHT );
        labCategoria.setFont( new Font( "Arial", Font.BOLD, 10 ) );

        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        panelContenido.add( labCategoria, gbc );

        // Ubicando el contenido del artículo
        txtContenido = new JTextArea( "Acá va el contenido del artículo" );
        txtContenido.setBackground( getBackground( ) );
        txtContenido.setLineWrap( true );

        JScrollPane scroll = new JScrollPane( txtContenido, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setBorder( null );
        gbc.gridwidth = 3;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 120;
        gbc.ipadx = 300;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets( 10, 0, 0, 0 );
        panelContenido.add( scroll, gbc );

        add( panelContenido, BorderLayout.CENTER );

        JPanel panelTareasComentarios = new JPanel( );
        panelTareasComentarios.setLayout( new BorderLayout( ) );
        panelTareasComentarios.setPreferredSize( new Dimension( 300, 250 ) );

        // El label del título de la sección de comentarios
        JLabel labTituloComentarios = new JLabel( "Comentarios" );
        labTituloComentarios.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        panelTareasComentarios.add( labTituloComentarios, BorderLayout.NORTH );

        // Crea un panel para la lista de los comentarios
        panelComentarios = new JPanel( );
//        panelComentarios.setPreferredSize( new Dimension( 300, 100 ) );
        panelComentarios.setLayout( new GridBagLayout( ) );

        JLabel labComentarios = new JLabel( "Actualmente no hay comentarios" );
        labComentarios.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        panelComentarios.add( labComentarios, new GridBagConstraints( ) );

        scrollComentarios = new JScrollPane( panelComentarios, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        // scrollComentarios.setBorder( null );
        panelTareasComentarios.add( scrollComentarios, BorderLayout.CENTER );

        // El fragmento para realizar el comentario
        JPanel panelComentar = new JPanel( );
        panelComentar.setPreferredSize( new Dimension( 300, 100 ) );
        panelComentar.setLayout( new BorderLayout( ) );

        txtComentario = new JTextArea( "Para comentar..." );
        txtComentario.setLineWrap( true );
        txtComentario.setEnabled( false );

        JScrollPane scrollCampoComentario = new JScrollPane( txtComentario, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollComentarios.setBorder( null );
        panelComentar.add( scrollCampoComentario, BorderLayout.CENTER );

        btnComentarArticulo = new JButton( "Comentar" );
        btnComentarArticulo.setActionCommand( COMENTAR_ARTICULO );
        btnComentarArticulo.addActionListener( this );
        btnComentarArticulo.setEnabled( false );
        panelComentar.add( btnComentarArticulo, BorderLayout.SOUTH );

        panelTareasComentarios.add( panelComentar, BorderLayout.SOUTH );

        add( panelTareasComentarios, BorderLayout.SOUTH );

        desactivar( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Se actualiza la información según el artículo que llega por parámetro.
     * @param articulo El artículo con la información a mostrar. articulo != null
     * @param comentarios Los comentarios correspondientes a ese artículo. comentarios != null
     */
    public void actualizarArticulo( Articulo articulo, ArrayList comentarios )
    {

        // Actualiza la información principal del artículo
        labTitulo.setText( articulo.darTitulo( ) );
        labUsuario.setText( articulo.darUsuario( ) );
        labCategoria.setText( articulo.darCategoria( ) );

        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd kk:mm" );
        String fecha = dateFormat.format( articulo.darFechaPublicacion( ) );
        labFechaPublicacion.setText( fecha );

        txtContenido.setText( articulo.darContenido( ) );
        txtComentario.setEnabled( true );
        btnComentarArticulo.setEnabled( true );
    }

    /**
     * Crea los comentarios según la lsita que llega por parámetro
     * @param comentarios La lista de comentarios. comentarios != null
     */
    public void actualizarComentarios( ArrayList comentarios )
    {
        panelComentarios.removeAll( );
        
        if( !comentarios.isEmpty( ) )
        {
            panelComentarios.setLayout( new GridLayout( comentarios.size( ), 1, 0, 10 ) );
            GridBagConstraints gbc = new GridBagConstraints( );

            int i = 0;
            while( i < comentarios.size( ) )
            {
                Comentario comentario = ( Comentario ) comentarios.get( i );
                
                JPanel panelComentario = new JPanel();
                panelComentario.setLayout( new GridBagLayout( ) );
                panelComentario.setPreferredSize( new Dimension( 300, 120) );

                //El nombre del autor del comentario
                JLabel nombreUsuario = new JLabel( comentario.darUsuario( ) );
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipady = 20;
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 1;
                gbc.weightx = 0.9;
                panelComentario.add( nombreUsuario, gbc );

                SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd kk:mm" );
                String fecha = dateFormat.format( comentario.darFechaPublicacion( ) );

                //La fecha de publicación del comentario
                JLabel fechaPublicacion = new JLabel( fecha );
                gbc.gridx = 2;
                gbc.gridy = 0;
                gbc.gridwidth = 1;
                gbc.weightx = 0.9;
                panelComentario.add( fechaPublicacion, gbc );

                //EL contenido del comentario
                JTextArea txtComentario = new JTextArea( );
                txtComentario.setPreferredSize( new Dimension( 250, 50) );
                txtComentario.setEditable( false );
                txtComentario.setLineWrap( true );
                txtComentario.setBackground( getBackground( ) );
                txtComentario.setText( comentario.darContenido( ) );
                
                JScrollPane scrollComentario = new JScrollPane( txtComentario, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
                gbc.ipady = 40;
                gbc.ipadx = 250;
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 3;
                gbc.weightx = 0.9;
                panelComentario.add( scrollComentario, gbc );
                
                panelComentarios.add( panelComentario );
                
                i++;
            }
        }
        else
        {
            panelComentarios.setLayout( new GridBagLayout( ) );

            JLabel labComentarios = new JLabel( "Actualmente no hay comentarios" );
            labComentarios.setFont( new Font( "Arial", Font.BOLD, 12 ) );
            panelComentarios.add( labComentarios, new GridBagConstraints( ) );
        }
        panelComentarios.validate( );
        scrollComentarios.validate( );
        scrollComentarios.repaint( );
    }

    /**
     * Desactiva todas las opciones del panel.
     */
    public void desactivar( )
    {
        labTitulo.setText( "" );
        labUsuario.setText( "" );
        labCategoria.setText( "" );
        labFechaPublicacion.setText( "" );
        txtContenido.setText( "" );
        txtComentario.setText( "" );
        txtComentario.setEnabled( false );
        btnComentarArticulo.setEnabled( false );
        
        //Limpia los comentarios
        panelComentarios.removeAll( );
        panelComentarios.setLayout( new GridBagLayout( ) );

        JLabel labComentarios = new JLabel( "Actualmente no hay comentarios" );
        labComentarios.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        panelComentarios.add( labComentarios, new GridBagConstraints( ) );

    }

    /**
     * El método que controla los eventos de los botones
     * @param e El evento que se ha lanzado. e != null
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( COMENTAR_ARTICULO ) )
        {
            String contenidoComentario = txtComentario.getText( );

            if( contenidoComentario != null && ! ( contenidoComentario.isEmpty( ) ) )
            {
                principal.publicarComentario( contenidoComentario );
            }
            txtComentario.setText( "" );
        }
    }
}
