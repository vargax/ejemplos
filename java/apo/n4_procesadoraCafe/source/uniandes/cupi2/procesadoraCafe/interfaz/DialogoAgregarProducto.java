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
 * Dialogo que permite agregar un nuevo producto
 */
public class DialogoAgregarProducto extends JDialog implements ActionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Comando Agregar Producto
	 */
	private static final String AGREGAR = "Agregar";
	
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
	 * Campo de texto del nombre del producto
	 */
	private JTextField txtNombre;
	
	/**
	 * Campo de texto del aroma del producto
	 */
	private JTextField txtAroma;
	
	/**
	 * Campo de texto de la acidez del producto
	 */
	private JTextField txtAcidez;
	
	/**
	 * Campo de texto del cuerpo del producto
	 */
	private JTextField txtCuerpo;
	
	/**
	 * Campo de texto del precio del producto
	 */
	private JTextField txtPrecio;
	
	/**
	 * Campo de texto del factor de conversión del producto
	 */
	private JTextField txtConversion;
	
	/**
	 * Botón Agregar Producto
	 */
	private JButton btnAgregar;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

	/**
     * Constructor del dialogo
     * @param ventana Ventana principal
     */
	public DialogoAgregarProducto(InterfazProcesadoraCafe ventana)
	{
		principal = ventana;
		
		setSize(250, 210);
		setLayout(new GridLayout(7, 2));
		setTitle("Agregar Producto");
		setModal(true);
		setLocationRelativeTo(principal);
		
		JLabel temp = new JLabel(" Nombre: ");
		add(temp);
		txtNombre = new JTextField();
		add(txtNombre);
		
		JLabel temp2 = new JLabel(" Aroma: ");
		add(temp2);
		txtAroma = new JTextField();
		add(txtAroma);
		
		JLabel temp3 = new JLabel(" Acidez: ");
		add(temp3);
		txtAcidez = new JTextField();
		add(txtAcidez);
				
		JLabel temp4 = new JLabel(" Cuerpo: ");
		add(temp4);
		txtCuerpo = new JTextField();
		add(txtCuerpo);
		
		JLabel temp5 = new JLabel(" Precio: ");
		add(temp5);
		txtPrecio = new JTextField();
		add(txtPrecio);	
		
		JLabel temp6 = new JLabel(" Conversión: ");
		add(temp6);
		txtConversion = new JTextField();
		add(txtConversion);	
		
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
    		try
    		{
    			
    			String nombre = txtNombre.getText();
    			if(nombre != null && !nombre.equals(""))
    			{
    				String aroma = txtAroma.getText();
    				String acidez = txtAcidez.getText();
    				String cuerpo = txtCuerpo.getText();
    				if(aroma != null && !aroma.equals("") && acidez != null && !acidez.equals("") && cuerpo != null && !cuerpo.equals(""))
    				{
    					String precioS = txtPrecio.getText();
    					double precio = Double.parseDouble(precioS);
    					String conversionS = txtConversion.getText();
    					double conversion = Double.parseDouble(conversionS);
    					
    					if(precio > 0 && conversion > 0)
    					{
    						principal.agregarProducto(nombre, aroma, acidez, cuerpo, precio, conversion);
    						dispose();
    					}
    					else
    					{
    						JOptionPane.showMessageDialog(this, "El precio y el factor de conversión deben ser mayores a cero", "Agregar Producto", JOptionPane.ERROR_MESSAGE);
    					}
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(this, "Ingrese las propiedades de aroma, acidez y cuerpo del producto", "Agregar Producto", JOptionPane.ERROR_MESSAGE);    					
    				}
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(this, "Ingrese el nombre del producto", "Agregar Producto", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    		catch (NumberFormatException ne) 
    		{
    			JOptionPane.showMessageDialog(this, "El precio y el factor de conversión deben ser valores numéricos", "Agregar Producto", JOptionPane.ERROR_MESSAGE);
			}
    	}
    }
}
