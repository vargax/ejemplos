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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Panel donde se va a mostrar la lista de clientes.
 */
public class PanelListaClientes extends JPanel implements KeyListener, ListSelectionListener, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = 6729948272590246225L;

    /**
     * Constante para crear un nuevo cliente.
     */
    private static final String NUEVO_CLIENTE = "Nuevo Cliente";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Identificadores de los clientes.
     */
    private Object[] clientes;

    /**
     * Interfaz principal de la aplicación.
     */
    private InterfazCentral principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta "Buscar Cliente".
     */
    private JLabel lblBuscar;

    /**
     * Cuadro de texto buscar.
     */
    private JTextField txtBuscar;

    /**
     * Scroll donde está la lista de clientes.
     */
    private JScrollPane jScrollPane;

    /**
     * Lista donde se muestran los clientes.
     */
    private JList listaClientes;

    /**
     * Botón para insertar un nuevo cliente.
     */
    private JButton btnInsertarCliente;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param nPrincipal Interfaz principal de la aplicación.
     * @param nClientes Identificadores de los clientes.
     */
    public PanelListaClientes( InterfazCentral nPrincipal, Object[] nClientes )
    {
        super( );
        principal = nPrincipal;
        clientes = nClientes;

        setSize( 200, 200 );
        BorderLayout layout = new BorderLayout( );
        layout.setHgap( 3 );
        layout.setVgap( 3 );
        setLayout( layout );
        setBorder( BorderFactory.createTitledBorder( "Clientes" ) );
        setPreferredSize( new Dimension( 250, 250 ) );

        // Insertar los elementos de la interfaz.
        JPanel panelNorte = new JPanel( );
        BorderLayout layoutNorte = new BorderLayout( );
        panelNorte.setLayout( layoutNorte );
        lblBuscar = new JLabel( "Buscar:" );
        panelNorte.add( lblBuscar, BorderLayout.WEST );

        txtBuscar = new JTextField( );
        txtBuscar.addKeyListener( this );
        panelNorte.add( txtBuscar, BorderLayout.CENTER );

        btnInsertarCliente = new JButton( new ImageIcon( "./data/icons/add-user.png" ) );
        btnInsertarCliente.addActionListener( this );
        btnInsertarCliente.setActionCommand( NUEVO_CLIENTE );
        panelNorte.add( btnInsertarCliente, BorderLayout.EAST );
        add( panelNorte, BorderLayout.NORTH );

        listaClientes = new JList( clientes );
        listaClientes.addListSelectionListener( this );
        listaClientes.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
        jScrollPane = new JScrollPane( );
        jScrollPane.setViewportView( listaClientes );
        add( jScrollPane, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Filtra la lista de los clientes.
     * @param cliente Identificador buscado.
     */
    private void filtrarLista( String cliente )
    {
        if( cliente.equals( "" ) )
            listaClientes.setListData( clientes );
        else
        {
            Vector<String> nuevaLista = new Vector<String>( );
            for( int i = 0; i < clientes.length; i++ )
                if( clientes[ i ].toString( ).startsWith( cliente ) )
                    nuevaLista.add( clientes[ i ].toString( ) );
            listaClientes.setListData( nuevaLista );
        }
    }

    /**
     * Actualiza la lista de clientes con los clientes dados.
     * @param clientesAct Lista de clientes a mostrar.
     */
    public void actualizarListaClientes( Iterador<String> clientesAct )
    {
        Vector<String> o = new Vector<String>( );
        clientesAct.reiniciar( );
        while( clientesAct.haySiguiente( ) )
            o.add( clientesAct.darSiguiente( ) );
        clientes = o.toArray( );
        listaClientes.setListData( clientes );
    }

    /**
     * Retorna la llave del cliente seleccionado.
     * @return La llave del cliente seleccionado.
     */
    public String darClienteSeleccionado( )
    {
        return ( String )listaClientes.getSelectedValue( );
    }

    /**
     * Manejo de los eventos sobre el campo de texto de búsqueda.
     * @param e Acción que generó el evento.
     */
    public void keyPressed( KeyEvent e )
    {
        // No se necesita
    }

    /**
     * Manejo de los eventos sobre el campo de texto de búsqueda.
     * @param e Acción que generó el evento.
     */
    public void keyReleased( KeyEvent e )
    {
        filtrarLista( txtBuscar.getText( ) );
    }

    /**
     * Manejo de los eventos sobre el campo de texto de búsqueda.
     * @param e Acción que generó el evento.
     */
    public void keyTyped( KeyEvent e )
    {
        // No se necesita
    }

    /**
     * Manejo de los eventos sobre la lista de clientes.
     * @param e Acción que generó el evento.
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( !e.getValueIsAdjusting( ) && listaClientes.getSelectedValue( ) != null )
            principal.mostrarMensajes( listaClientes.getSelectedValue( ).toString( ) );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( NUEVO_CLIENTE ) )
            ( new DialogoNuevoCliente( principal ) ).setVisible( true );
    }

}
