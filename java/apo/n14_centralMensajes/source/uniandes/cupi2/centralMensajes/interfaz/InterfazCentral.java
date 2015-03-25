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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import uniandes.cupi2.centralMensajes.mundo.Central;
import uniandes.cupi2.centralMensajes.mundo.ICentral;
import uniandes.cupi2.centralMensajes.mundo.PersistenciaException;
import uniandes.cupi2.collections.iterador.Iterador;

/**
 * Interfaz principal de la aplicación.
 */
public class InterfazCentral extends JFrame
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = -4623325118968587725L;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Referencia a la central de mensajes
	 */
	private ICentral central;

	/**
	 * Referencia a la llave del cliente actual
	 */
	private String clienteActual;

	/**
	 * Indicador para mostrar todos los mensajes o solamente los no leídos
	 */
	private boolean mostrarNoLeidos;

	/**
	 * Mensaje que se está mostrando actualmente.
	 */
	private String mensajeActual;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------

	/**
	 * División Principal de la pantalla.
	 */
	private JSplitPane divisorPrincipal;

	/**
	 * Panel donde está la lista de clientes.
	 */
	private PanelListaClientes panelListaClientes;

	/**
	 * División central de la pantalla.
	 */
	private JSplitPane divisorCentro;

	/**
	 * Panel donde está la lista de mensajes.
	 */
	private PanelListaMensajes panelListaMensajes;

	/**
	 * Panel donde se muestra el mensaje actual.
	 */
	private PanelMensaje panelMensaje;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor de la aplicación.
	 */
	public InterfazCentral( )
	{
		super( );
		try
		{
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			central = crearCentral(); 
			setSize( new Dimension( 800, 600 ) );
			setResizable( true );
			setLocationByPlatform( true );
			setTitle( "Central Mensajes" );

			setLayout( new BorderLayout( ) );
			JPanel panelCentral = new JPanel( );
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints( );
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.weighty = 1.0;
			gridBagConstraints5.gridx = 0;
			panelCentral.setLayout( new GridBagLayout( ) );
			// Armar el panel central
			divisorPrincipal = new JSplitPane( );

			Object[] o = new Object[central.darCantidadClientes( )];
			Iterador<String> clientes = central.darClientes( );
			for( int i = 0; clientes.haySiguiente( ); i++ )
				o[ i ] = clientes.darSiguiente( );
			panelListaClientes = new PanelListaClientes( this, o );
			divisorPrincipal.setLeftComponent( panelListaClientes );

			divisorCentro = new JSplitPane( );
			divisorCentro.setOrientation( JSplitPane.VERTICAL_SPLIT );
			panelMensaje = new PanelMensaje( this, central.darCodificaciones( ) );
			divisorCentro.setBottomComponent( panelMensaje );
			panelListaMensajes = new PanelListaMensajes( this );
			divisorCentro.setTopComponent( panelListaMensajes );

			divisorPrincipal.setRightComponent( divisorCentro );

			panelCentral.add( divisorPrincipal, gridBagConstraints5 );

			add( panelCentral, BorderLayout.CENTER );

			pack( );

		}		
		catch( PersistenciaException e )
		{
			JOptionPane.showMessageDialog( this, e.getMessage( ) );
		}

	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------


	/**
	 * Crea una central
	 * @return ICentral
	 * @throws PersistenciaException
	 */
	public ICentral crearCentral( ) throws PersistenciaException
	{
		try
		{
			Properties archivoClaves = new Properties( );
			FileInputStream in = new FileInputStream( new File( "./data/claves.properties" ) );
			archivoClaves.load( in );
			in.close( );

			
			ICentral c = new Central( );
			return c;
		}
		catch( Exception e )
		{
			e.printStackTrace( );
			return null;
		}

	}

	/**
	 * Muestra la lista de mensajes del cliente asociado a la llave dada.
	 * @param llaveCliente Llave asociada al cliente del que se quieren ver los mensajes.
	 */
	public void mostrarMensajes( String llaveCliente )
	{
		clienteActual = llaveCliente;
		Iterador<String> llaves = null;
		if( mostrarNoLeidos )
			llaves = central.darLlavesMensajesNoLeidos( clienteActual );
		else
			llaves = central.darLlavesMensajes( clienteActual );
		panelListaMensajes.refrescarListaMensajes( llaves );
		panelListaMensajes.refrescarNumeroMensajes( contarIterador( central.darLlavesMensajes( clienteActual ) ), contarIterador( central.darLlavesMensajesNoLeidos( clienteActual ) ) );
		panelListaMensajes.agregarClienteATitulo( clienteActual );
		panelMensaje.refrescarMensaje( "", "" );
		mensajeActual = null;
	}

	/**
	 * Refresca la lista de mensajes del cliente actualmente seleccionado.
	 */
	public void refrescarClienteActual( )
	{
		if( clienteActual != null )
		{
			Iterador<String> lista = mostrarNoLeidos ? central.darLlavesMensajesNoLeidos( clienteActual ) : central.darLlavesMensajes( clienteActual );
			panelListaMensajes.refrescarListaMensajes( lista );
			panelListaMensajes.refrescarNumeroMensajes( contarIterador( central.darLlavesMensajes( clienteActual ) ), contarIterador( central.darLlavesMensajes( clienteActual ) ) );
			panelMensaje.refrescarMensaje( "", "" );
		}
	}

	/**
	 * Muestra el contenido descifrado del mensaje asociado a la llave dada.
	 * @param llaveMensaje Llave asociada al mensaje que se quiere mostrar.
	 */
	public void mostrarMensaje( String llaveMensaje )
	{
		mensajeActual = llaveMensaje;
		central.marcarMensajeLeido( clienteActual, llaveMensaje );
		panelListaMensajes.refrescarNumeroMensajes( contarIterador( central.darLlavesMensajes( clienteActual ) ), contarIterador( central.darLlavesMensajes( clienteActual ) ) );
		String decodificador = panelMensaje.darDecodificador( );
		String remitente = central.darRemitenteMensaje( mensajeActual );
		String contenido = central.recuperarMensaje( clienteActual, llaveMensaje, decodificador );
		panelMensaje.refrescarMensaje( remitente, contenido );
	}

	/**
	 * Muestra el contenido de un mensaje, decodificándolo con otro decodificador.
	 */
	public void cambiarDecodificador( )
	{
		if( mensajeActual != null )
		{
			String decodificador = panelMensaje.darDecodificador( );
			String remitente = central.darRemitenteMensaje( mensajeActual );
			String contenido = central.recuperarMensaje( clienteActual, mensajeActual, decodificador );
			panelMensaje.refrescarMensaje( remitente, contenido );
		}
	}

	/**
	 * Envía un mensaje al cliente destinatario.
	 * @param llaveDestinatario Llave asociada al cliente destinatario.
	 * @param mensaje Contenido del mensaje que se quiere enviar.
	 * @param codificacion Nombre de la clave de codificación para codificar el contenido del mensaje.
	 */
	public void enviarMensaje( String llaveDestinatario, String mensaje, String codificacion )
	{
		central.enviarMensaje( clienteActual, llaveDestinatario, mensaje, codificacion );
		if( mostrarNoLeidos )
			panelListaMensajes.refrescarListaMensajes( central.darLlavesMensajesNoLeidos( panelListaClientes.darClienteSeleccionado( ) ) );
		else
			panelListaMensajes.refrescarListaMensajes( central.darLlavesMensajes( panelListaClientes.darClienteSeleccionado( ) ) );
	}

	/**
	 * Elimina el mensaje asociado a la llave dada. Al cliente actual, se le elimina el mensaje de la posición dada.
	 * @param llaveMensaje Llave del mensaje que se quiere eliminar
	 */
	public void eliminarMensaje( String llaveMensaje )
	{
		central.eliminarMensaje( llaveMensaje, clienteActual );
		mensajeActual = null;
	}

	/**
	 * Retorna los nombre de las claves de codificación.
	 * @return los nombre de las claves de codificación.
	 */
	public Iterador<String> darListaEncriptaciones( )
	{
		return central.darCodificaciones( );
	}

	/**
	 * Muestra una ventana para escribir el mensaje
	 */
	public void nuevoMensaje( )
	{
		if( clienteActual != null )
			new DialogoNuevoMensaje( this, central.darCodificaciones( ), central.darClientes( ) );
		else
			JOptionPane.showMessageDialog( this, "Debe seleccionar al cliente remitente", "Error", JOptionPane.ERROR_MESSAGE );
	}

	/**
	 * Cambia el estado del indicador mostrarNoLeidos por su negación
	 */
	public void toggleMostrarLeidos( )
	{
		mostrarNoLeidos = !mostrarNoLeidos;
	}

	/**
	 * Crear un nuevo cliente.
	 * @param identificador Identificador del cliente.
	 */
	public void nuevoCliente( String identificador )
	{
		central.nuevoCliente( identificador );
		panelListaClientes.actualizarListaClientes( central.darClientes( ) );
	}

	// -----------------------------------------------------------------
	// Métodos Auxiliares
	// -----------------------------------------------------------------

	/**
	 * Cuenta cuantos elementos vienen en un iterador.
	 * @param iter Iterador del cual se quiere saber el número de elementos - iter != null.
	 * @return El número de elementos contenidos en el iterador.
	 */
	private int contarIterador( Iterador<?> iter )
	{
		int i;
		for( i = 0; iter.haySiguiente( ); i++ )
			iter.darSiguiente( );
		iter.reiniciar( );
		return i;
	}

	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------

	/**
	 * Main
	 * @param args Argumentos para correr la aplicación.
	 */
	public static void main( String[] args )
	{
		 new InterfazCentral( ).setVisible( true );
	}

}