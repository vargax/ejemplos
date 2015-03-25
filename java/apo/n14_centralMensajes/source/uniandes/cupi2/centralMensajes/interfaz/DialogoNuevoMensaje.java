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
package uniandes.cupi2.centralMensajes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Diálogo para ingresar un nuevo mensaje.
 */
public class DialogoNuevoMensaje extends JDialog implements ActionListener, KeyListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = 7272178206820772038L;

    /**
     * Constante para el botón enviar.
     */
    public static final String ENVIAR = "Enviar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicación.
     */
    private InterfazCentral principal;

    /**
     * Etiqueta "Destino".
     */
    private JLabel lblDestino;

    /**
     * Etiqueta "Codificación".
     */
    private JLabel lblCodificacion;

    /**
     * Combo donde se selecciona la codificación.
     */
    private JComboBox comboCodificacion;

    /**
     * Etiqueta "Mensaje".
     */
    private JLabel lblMensaje;

    /**
     * Scroll donde se va ubicar el área de texto del mensaje.
     */
    private JScrollPane spnMensaje;

    /**
     * Área de texto del mensaje.
     */
    private JTextArea txtMensaje;

    /**
     * Botón Enviar.
     */
    private JButton btnEnviar;

    /**
     * Combo donde se selecciona el destinatario.
     */
    private JComboBox comboDestinatarios;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo.
     * @param nPrincipal Interfaz principal de la aplicación.
     * @param codificaciones Lista de posibles codificaciones.
     * @param clientes Clientes de la aplicación.
     */
    public DialogoNuevoMensaje( InterfazCentral nPrincipal, Iterador<String> codificaciones, Iterador<String> clientes )
    {
        super( nPrincipal );
        principal = nPrincipal;
        setSize( 432, 277 );
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        setLayout( new BorderLayout( ) );
        setLocationRelativeTo( principal );
        setModal( true );
        setTitle( "Nuevo Mensaje" );
        // Construcción del panel central.
        JPanel panel = new JPanel( );
        GridBagConstraints gridBagConstraints6 = new GridBagConstraints( );
        gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.gridx = 1;
        GridBagConstraints gridBagConstraints11 = new GridBagConstraints( );
        gridBagConstraints11.gridx = 0;
        gridBagConstraints11.gridwidth = 4;
        gridBagConstraints11.ipady = 0;
        gridBagConstraints11.insets = new Insets( 10, 0, 0, 0 );
        gridBagConstraints11.gridy = 2;
        GridBagConstraints gridBagConstraints5 = new GridBagConstraints( );
        gridBagConstraints5.fill = GridBagConstraints.BOTH;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.weighty = 1.0;
        gridBagConstraints5.gridwidth = 3;
        gridBagConstraints5.insets = new Insets( 5, 0, 0, 0 );
        gridBagConstraints5.gridx = 1;
        GridBagConstraints gridBagConstraints4 = new GridBagConstraints( );
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints4.insets = new Insets( 5, 0, 0, 5 );
        gridBagConstraints4.gridy = 1;
        lblMensaje = new JLabel( );
        lblMensaje.setText( "Mensaje:" );
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints( );
        gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.gridx = 3;
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints( );
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.anchor = GridBagConstraints.EAST;
        gridBagConstraints2.insets = new Insets( 0, 5, 0, 5 );
        gridBagConstraints2.gridy = 0;
        lblCodificacion = new JLabel( );
        lblCodificacion.setText( "Codificación:" );
        GridBagConstraints gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets( 0, 0, 0, 5 );
        gridBagConstraints.gridy = 0;
        lblDestino = new JLabel( );
        lblDestino.setText( "Enviar a:" );
        // Adición de los elementos.
        panel = new JPanel( );
        panel.setLayout( new GridBagLayout( ) );
        panel.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );

        // Etiquetas
        panel.add( lblDestino, gridBagConstraints );
        panel.add( lblCodificacion, gridBagConstraints2 );
        panel.add( lblMensaje, gridBagConstraints4 );

        // Combo Codificación
        Vector<String> o = new Vector<String>( );
        codificaciones.reiniciar( );
        while( codificaciones.haySiguiente( ) )
            o.add( codificaciones.darSiguiente( ) );
        comboCodificacion = new JComboBox( o.toArray( ) );
        comboCodificacion.setSelectedIndex( 0 );
        panel.add( comboCodificacion, gridBagConstraints3 );

        // Área de texto del mensaje
        txtMensaje = new JTextArea( );
        txtMensaje.setLineWrap( true );
        txtMensaje.setWrapStyleWord( true );
        txtMensaje.addKeyListener( this );
        spnMensaje = new JScrollPane( );
        spnMensaje.setViewportView( txtMensaje );
        panel.add( spnMensaje, gridBagConstraints5 );

        btnEnviar = new JButton( );
        btnEnviar.setText( "Enviar" );
        btnEnviar.setIcon( new ImageIcon( "./data/icons/enviarMensaje.gif" ) );
        btnEnviar.addActionListener( this );
        btnEnviar.setActionCommand( ENVIAR );
        panel.add( btnEnviar, gridBagConstraints11 );

        Vector<String> o2 = new Vector<String>( );
        clientes.reiniciar( );
        while( clientes.haySiguiente( ) )
            o2.add( clientes.darSiguiente( ) );
        comboDestinatarios = new JComboBox( o2.toArray( ) );
        panel.add( comboDestinatarios, gridBagConstraints6 );

        add( panel, BorderLayout.CENTER );

        setVisible( true );
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Envía el mensaje.
     * @return true si el mensaje fue enviado o false en caso contrario.
     */
    public boolean enviar( )
    {
        if( txtMensaje.getText( ).equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Debe escribir su mensaje", "Error", JOptionPane.ERROR_MESSAGE );
            return false;
        }
        principal.enviarMensaje( comboDestinatarios.getSelectedItem( ).toString( ), txtMensaje.getText( ), comboCodificacion.getSelectedItem( ).toString( ) );
        return true;
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( ENVIAR ) && enviar( ) )
            setVisible( false );
    }

    /**
     * Manejo de los eventos del text area
     * @param e Acción que generó el evento.
     */
    public void keyPressed( KeyEvent e )
    {
        // No se necesita
    }

    /**
     * Manejo de los eventos del text area
     * @param e Acción que generó el evento.
     */
    public void keyReleased( KeyEvent e )
    {
        // No se necesita
    }

    /**
     * Manejo de los eventos del text area
     * @param e Acción que generó el evento.
     */
    public void keyTyped( KeyEvent e )
    {
        if( ( int )e.getKeyChar( ) > 255 )
        {
            JOptionPane.showMessageDialog( this, "Sólamente se aceptan caracteres ASC-II", "Error", JOptionPane.ERROR_MESSAGE );
            e.consume( );
        }
    }
}
