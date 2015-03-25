package uniandes.cupi2.procesadoraCafe.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.procesadoraCafe.mundo.Cliente;

/**
 * Panel con la información de los clientes
 */
public class PanelClientes extends JPanel implements ActionListener, ListSelectionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Comando Agregar Cliente
	 */
	private final static String AGREGAR = "Agregar";
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación
	 */
	private InterfazProcesadoraCafe principal;
	
	//-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

	/**
	 * Scroll de clientes
	 */
	private JScrollPane scrollDesplazamiento;
	
	/**
	 * Lista de clientes
	 */
	private JList listaClientes;
	
	/**
	 * Campo de texto del nombre del cliente
	 */
	private JTextField txtNombre;
	
	/**
	 * Campo de texto del NIt del cliente
	 */
	private JTextField txtNit;
	
	/**
	 * Campo de texto del teléfono del cliente
	 */
	private JTextField txtTelefono;
	
	/**
	 * Campo de texto de kilos vendidos al cliente
	 */
	private JTextField txtKilosVendidos;
	
	/**
	 * Botón Agregar Cliente
	 */
	private JButton btnAgregar;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
	public PanelClientes(InterfazProcesadoraCafe ventana)
	{
		principal = ventana;
		
		setLayout(new GridLayout(1, 2));
		
		JPanel lista = new JPanel();
		lista.setLayout(new BorderLayout());
		lista.setBorder(new TitledBorder(" Clientes "));
		
		scrollDesplazamiento = new JScrollPane( );
		listaClientes = new JList( );
		listaClientes.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION );
		listaClientes.addListSelectionListener(this);
		scrollDesplazamiento.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollDesplazamiento.setViewportView( listaClientes );
        lista.add(scrollDesplazamiento, BorderLayout.CENTER);

		JPanel auxBotones = new JPanel();
		auxBotones.setLayout(new GridLayout(1, 1));
		
		btnAgregar = new JButton(AGREGAR);
		btnAgregar.setActionCommand(AGREGAR);
		btnAgregar.addActionListener(this);
		auxBotones.add(btnAgregar);
		
		lista.add(auxBotones, BorderLayout.SOUTH);
		add(lista);
		
		JPanel info = new JPanel();
		info.setLayout( new GridLayout(4, 2));
		info.setBorder( new TitledBorder(" Información Cliente "));
		
		JLabel temp = new JLabel(" Nombre: ");
		temp.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp);
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		info.add(txtNombre);
		
		JLabel temp2 = new JLabel(" NIT: ");
		temp2.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp2);
		txtNit = new JTextField();
		txtNit.setEditable(false);
		info.add(txtNit);
		
		JLabel temp3 = new JLabel(" Teléfono: ");
		temp3.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp3);
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		info.add(txtTelefono);
				
		JLabel temp4 = new JLabel(" Vendidos: ");
		temp4.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp4);
		txtKilosVendidos = new JTextField();
		txtKilosVendidos.setEditable(false);
		info.add(txtKilosVendidos);
				
		add(info);
	}

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Actualiza la lista de clientes
     * @param clientes La nueva lista de los clientes
     */
    public void actualizarClientes( ArrayList clientes )
    {
        listaClientes.setListData( clientes.toArray( ) );
        if(!clientes.isEmpty())
        {
        	listaClientes.setSelectedIndex(0);
        	actualizarInfoCliente();
        }
        else
        {
        	txtKilosVendidos.setText("");
        	txtNit.setText("");
        	txtTelefono.setText("");
        	txtNombre.setText("");
        }
    }
    
    /**
     * Actualiza la información del cliente
     */
    public void actualizarInfoCliente()
    {
    	Cliente c = (Cliente) listaClientes.getSelectedValue();
    	txtKilosVendidos.setText( c.darCantidadVendida() + " kilos" );
    	txtNit.setText( c.darNit() );
    	txtTelefono.setText( c.darTelefono() );
    	txtNombre.setText( c.darNombre() );
    }
	
    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
    	String comando = e.getActionCommand();
    	if( comando.equals(AGREGAR) )
    	{
    		DialogoAgregarCliente d = new DialogoAgregarCliente(principal);
    		d.setVisible(true);
    	}
    }

	/**
	 * Actualiza la información del nuevo cliente seleccionado
	 * @param e Es el evento de cambio el ítem seleccionado en la lista
	 */
	public void valueChanged(ListSelectionEvent e) 
	{
		if(listaClientes.getSelectedValue() != null)
		{
			actualizarInfoCliente();	
		}
	}
}
