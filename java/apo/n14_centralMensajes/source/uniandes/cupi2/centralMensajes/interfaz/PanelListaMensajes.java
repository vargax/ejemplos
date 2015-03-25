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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Panel donde se van a mostrar los mensajes del cliente seleccionado.
 */
public class PanelListaMensajes extends JPanel implements ActionListener, ListSelectionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización.
     */
    private static final long serialVersionUID = -7035108863477725389L;

    /**
     * Constante para el botón Eliminar.
     */
    public static final String ELIMINAR = "Eliminar";

    /**
     * Constante para el botón Nuevo Mensaje.
     */
    public static final String NUEVO_MENSAJE = "Nuevo Mensaje";

    /**
     * Constante para el botón Refrescar.
     */
    public static final String REFRESCAR = "Refrescar";

    /**
     * Constante para el botón mostrar Leídos.
     */
    public static final String MOSTRAR_LEIDOS = "Mostrar Leídos";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicación.
     */
    private InterfazCentral principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Scroll donde va estar la lista de los mensajes.
     */
    private JScrollPane scrollListaMensajes;

    /**
     * Lista de mensajes.
     */
    private JList listaMensajes;

    /**
     * Etiqueta "Mensaje Nuevos".
     */
    private JLabel lblMensajesNuevos;

    /**
     * Barra de herramientas donde va estar los botones.
     */
    private JToolBar toolBar;

    /**
     * Botón para enviar un nuevo mensaje.
     */
    private JButton btnNuevoMensaje;

    /**
     * Botón para eliminar mensaje.
     */
    private JButton btnEliminarMensaje;

    /**
     * Botón para refrescar la lista de mensajes.
     */
    private JButton btnRefrescar;

    /**
     * Botón para mostrar todos los mensajes o los que no se han leído.
     */
    private JToggleButton tglbtnMostrarLeidos;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param nPrincipal Interfaz principal de la aplicación.
     */
    public PanelListaMensajes( InterfazCentral nPrincipal )
    {
        super( );
        principal = nPrincipal;
        setLayout( new GridBagLayout( ) );
        setSize( 600, 250 );
        setPreferredSize( new Dimension( 600, 250 ) );
        setBorder( BorderFactory.createTitledBorder( "Mensajes" ) );
        // Inicialización del panel
        GridBagConstraints gridBagConstraints11 = new GridBagConstraints( );
        gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints11.gridy = 0;
        gridBagConstraints11.weightx = 1.0;
        gridBagConstraints11.anchor = GridBagConstraints.WEST;
        gridBagConstraints11.gridx = 0;
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints( );
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.anchor = GridBagConstraints.EAST;
        gridBagConstraints1.gridy = 2;
        lblMensajesNuevos = new JLabel( );
        lblMensajesNuevos.setText( "No hay mensajes" );
        GridBagConstraints gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;

        // Inserción de los elementos.
        listaMensajes = new JList( );
        listaMensajes.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
        listaMensajes.addListSelectionListener( this );
        scrollListaMensajes = new JScrollPane( );
        scrollListaMensajes.setViewportView( listaMensajes );
        add( scrollListaMensajes, gridBagConstraints );
        add( lblMensajesNuevos, gridBagConstraints1 );

        // Inicialización de los Botones
        btnNuevoMensaje = new JButton( );
        btnNuevoMensaje.setIcon( new ImageIcon( "./data/icons/crearMensaje.gif" ) );
        btnNuevoMensaje.setToolTipText( "Nuevo menaje" );
        btnNuevoMensaje.addActionListener( this );
        btnNuevoMensaje.setActionCommand( NUEVO_MENSAJE );

        btnEliminarMensaje = new JButton( );
        btnEliminarMensaje.setIcon( new ImageIcon( "./data/icons/eliminarMensaje.gif" ) );
        btnEliminarMensaje.setToolTipText( "Eliminar mensaje" );
        btnEliminarMensaje.addActionListener( this );
        btnEliminarMensaje.setActionCommand( ELIMINAR );

        btnRefrescar = new JButton( );
        btnRefrescar.setIcon( new ImageIcon( "./data/icons/refrescar.gif" ) );
        btnRefrescar.setToolTipText( "Refrescar" );
        btnRefrescar.addActionListener( this );
        btnRefrescar.setActionCommand( REFRESCAR );

        tglbtnMostrarLeidos = new JToggleButton( );
        tglbtnMostrarLeidos.setIcon( new ImageIcon( "./data/icons/leerMensaje.gif" ) );
        tglbtnMostrarLeidos.setText( "Todos" );
        tglbtnMostrarLeidos.setToolTipText( "Mostrar no leídos" );
        tglbtnMostrarLeidos.addActionListener( this );
        tglbtnMostrarLeidos.setActionCommand( MOSTRAR_LEIDOS );

        toolBar = new JToolBar( );
        toolBar.setFloatable( false );
        toolBar.setFloatable( false );
        toolBar.add( btnNuevoMensaje );
        toolBar.add( btnEliminarMensaje );
        toolBar.add( btnRefrescar );
        toolBar.add( tglbtnMostrarLeidos );

        add( toolBar, gridBagConstraints11 );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método para eliminar un mensaje.
     */
    public void eliminarMensaje( )
    {
        if( listaMensajes.getSelectedValue( ) != null )
        {
            int resp = JOptionPane.showConfirmDialog( principal, "¿Desea eliminar el mensaje seleccionado?", "Eliminar Mensaje", JOptionPane.YES_NO_OPTION );
            if( resp == JOptionPane.YES_OPTION )
            {
                String llaveMensaje = listaMensajes.getSelectedValue( ).toString( );
                principal.eliminarMensaje( llaveMensaje );
                refrescar( );
            }
        }
    }

    /**
     * Refresca la lista de mensajes.
     */
    private void refrescar( )
    {
        principal.refrescarClienteActual( );
    }

    /**
     * Muestra en la lista de mensajes los mensajes ingresados como parámetro.
     * @param mensajes Mensajes a mostrar en a lista de mensajes.
     */
    public void refrescarListaMensajes( Iterador<String> mensajes )
    {
        Vector<String> o = new Vector<String>( );
        mensajes.reiniciar( );
        while( mensajes.haySiguiente( ) )
            o.add( mensajes.darSiguiente( ) );
        listaMensajes.setListData( o.toArray( ) );
    }

    /**
     * Método para refrescar la lista de mensajes.
     * @param num Número total de mensajes.
     * @param numNoLeidos Número de mensajes no leídos.
     */
    public void refrescarNumeroMensajes( int num, int numNoLeidos )
    {
        if( num == 0 && numNoLeidos == 0 )
            lblMensajesNuevos.setText( "No hay mensajes" );
        else if( num == 1 && numNoLeidos == 1 )
            lblMensajesNuevos.setText( "Hay 1 mensaje por leer" );
        else if( num == numNoLeidos )
            lblMensajesNuevos.setText( "Hay " + num + " mensajes por leer" );
        else if( num == 1 && numNoLeidos == 0 )
            lblMensajesNuevos.setText( "Hay 1 mensaje" );
        else if( num > 1 && numNoLeidos == 0 )
            lblMensajesNuevos.setText( "Hay " + num + " mensajes" );
        else
            lblMensajesNuevos.setText( "Hay " + num + " mensajes, " + numNoLeidos + " por leer" );
    }

    /**
     * Método para poner de título el identificador del cliente.
     * @param cliente Identificador del cliente.
     */
    public void agregarClienteATitulo( String cliente )
    {
        this.setBorder( BorderFactory.createTitledBorder( "Mensajes para el cliente " + cliente ) );
    }

    /**
     * Manejo de los eventos sobre la lista de mensajes.
     * @param e Acción que generó el evento.
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( !e.getValueIsAdjusting( ) && listaMensajes.getSelectedValue( ) != null )
            principal.mostrarMensaje( listaMensajes.getSelectedValue( ).toString( ) );
    }

    /**
     * Manejo de los eventos sobre los botones.
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( NUEVO_MENSAJE ) )
            principal.nuevoMensaje( );
        if( e.getActionCommand( ).equals( ELIMINAR ) )
            eliminarMensaje( );
        if( e.getActionCommand( ).equals( REFRESCAR ) )
            refrescar( );
        if( e.getActionCommand( ).equals( MOSTRAR_LEIDOS ) )
        {
            if( tglbtnMostrarLeidos.isSelected( ) )
            {
                tglbtnMostrarLeidos.setText( "No leídos" );
                tglbtnMostrarLeidos.setToolTipText( "Mostrar todos" );
            }
            else
            {
                tglbtnMostrarLeidos.setText( "Todos" );
                tglbtnMostrarLeidos.setToolTipText( "Mostrar no leídos" );
            }
            principal.toggleMostrarLeidos( );
            principal.refrescarClienteActual( );
        }
    }

}
