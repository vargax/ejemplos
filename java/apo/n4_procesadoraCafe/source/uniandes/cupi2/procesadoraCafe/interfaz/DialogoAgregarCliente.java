package uniandes.cupi2.procesadoraCafe.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Dialogo que permite agregar un nuevo cliente
 */
public class DialogoAgregarCliente extends JDialog implements ActionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Comando Agregar Clientes
	 */
	private static final String AGREGAR = "Agregar";
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Venta principal de la aplicación
	 */
	private InterfazProcesadoraCafe principal;
		
	//-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

	/**
	 * Campo de texto del nombre del cliente
	 */
	private JTextField txtNombre;
	
	/**
	 * Campo de texto del NIT del cliente
	 */
	private JTextField txtNit;
	
	/**
	 * Campo de texto del teléfono del cliente
	 */
	private JTextField txtTelefono;
	
	/**
	 * Botón Agregar Cliente
	 */
	private JButton btnAgregar;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor del dialogo
     * @param ventana Ventana principal
     */
	public DialogoAgregarCliente(InterfazProcesadoraCafe ventana)
	{
		principal = ventana;
		
		setSize(250, 130);
		setLayout(new GridLayout(4, 2));
		setTitle("Agregar Clientes");
		setModal(true);
		setLocationRelativeTo(principal);
		
		JLabel temp = new JLabel(" Nombre: ");
		add(temp);
		txtNombre = new JTextField();
		add(txtNombre);
		
		JLabel temp2 = new JLabel(" NIT: ");
		add(temp2);
		txtNit = new JTextField();
		add(txtNit);
		
		JLabel temp3 = new JLabel(" Teléfono: ");
		add(temp3);
		txtTelefono = new JTextField();
		add(txtTelefono);
				
		add(new JLabel());
		btnAgregar = new JButton(AGREGAR);
		btnAgregar.setActionCommand(AGREGAR);
		btnAgregar.addActionListener(this);
		add(btnAgregar);
	}
	
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
    	String comando = e.getActionCommand();
    	if( comando.equals(AGREGAR) )
    	{
    		String nombre = txtNombre.getText();
    		if(nombre != null && !nombre.equals(""))
    		{
    			String nit = txtNit.getText();
    			if(nit != null && !nit.equals(""))
    			{
    				String telefono = txtTelefono.getText();
    					
    				if(telefono != null && !telefono.equals(""))
    				{
    					principal.agregarCliente(nombre, nit, telefono);
    					dispose();
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(this, "Ingrese el teléfono del cliente", "Agregar Cliente", JOptionPane.ERROR_MESSAGE);
    				}
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(this, "Ingrese el NIT del cliente", "Agregar Cliente", JOptionPane.ERROR_MESSAGE);    					
    			}
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(this, "Ingrese el nombre del cliente", "Agregar Cliente", JOptionPane.ERROR_MESSAGE);
    		} 
    	}
    }
}