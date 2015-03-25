package uniandes.cupi2.procesadoraCafe.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.procesadoraCafe.mundo.Producto;

/**
 * Panel con la información de los productos
 */
public class PanelProductos extends JPanel implements ActionListener, ListSelectionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Comando Eliminar Producto
	 */
	private final static String ELIMINAR = "Eliminar";
	
	/**
	 * Comando Agregar Producto
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
	 * Scroll de productos
	 */
	private JScrollPane scrollDesplazamiento;
	
	/**
	 * Lista de productos
	 */
	private JList listaProductos;
	
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
	 * Campo de texto de kilos disponibles del producto
	 */
	private JTextField txtDisponibles;
	
	/**
	 * Campo de texto del factor de conversión del producto
	 */
	private JTextField txtConversion;
	
	/**
	 * Botón Agregar Producto
	 */
	private JButton btnAgregar;
	
	/**
	 * Botón Eliminar Producto
	 */
	private JButton btnEliminar;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
	public PanelProductos(InterfazProcesadoraCafe ventana)
	{
		principal = ventana;
		
		setLayout(new GridLayout(1, 2));
		
		JPanel lista = new JPanel();
		lista.setLayout(new BorderLayout());
		lista.setBorder(new TitledBorder(" Productos "));
		
		scrollDesplazamiento = new JScrollPane( );
		listaProductos = new JList( );
		listaProductos.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION );
		listaProductos.addListSelectionListener(this);
		scrollDesplazamiento.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollDesplazamiento.setViewportView( listaProductos );
        lista.add(scrollDesplazamiento, BorderLayout.CENTER);

		JPanel auxBotones = new JPanel();
		auxBotones.setLayout(new GridLayout(1, 2));
		
		btnAgregar = new JButton(AGREGAR);
		btnAgregar.setActionCommand(AGREGAR);
		btnAgregar.addActionListener(this);
		auxBotones.add(btnAgregar);
		
		btnEliminar = new JButton(ELIMINAR);
		btnEliminar.setActionCommand(ELIMINAR);
		btnEliminar.addActionListener(this);
		auxBotones.add(btnEliminar);
		
		lista.add(auxBotones, BorderLayout.SOUTH);
		add(lista);
		
		JPanel info = new JPanel();
		info.setLayout( new GridLayout(7, 2));
		info.setBorder( new TitledBorder(" Información Producto "));
		
		JLabel temp = new JLabel(" Nombre: ");
		temp.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp);
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		info.add(txtNombre);
		
		JLabel temp2 = new JLabel(" Aroma: ");
		temp2.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp2);
		txtAroma = new JTextField();
		txtAroma.setEditable(false);
		info.add(txtAroma);
		
		JLabel temp3 = new JLabel(" Acidez: ");
		temp3.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp3);
		txtAcidez = new JTextField();
		txtAcidez.setEditable(false);
		info.add(txtAcidez);
				
		JLabel temp4 = new JLabel(" Cuerpo: ");
		temp4.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp4);
		txtCuerpo = new JTextField();
		txtCuerpo.setEditable(false);
		info.add(txtCuerpo);
		
		JLabel temp5 = new JLabel(" Precio: ");
		temp5.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp5);
		txtPrecio = new JTextField(" $ ");
		txtPrecio.setEditable(false);
		info.add(txtPrecio);	
		
		JLabel temp6 = new JLabel(" Disponibles: ");
		temp6.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp6);
		txtDisponibles = new JTextField();
		txtDisponibles.setEditable(false);
		info.add(txtDisponibles);
		
		JLabel temp7 = new JLabel(" Conversión: ");
		temp7.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp7);
		txtConversion = new JTextField();
		txtConversion.setEditable(false);
		info.add(txtConversion);
		
		add(info);
	}

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Actualiza la lista de productos
     * @param productos La nueva lista de los productos
     */
    public void actualizarProductos( ArrayList productos )
    {
        listaProductos.setListData( productos.toArray( ) );
        if(!productos.isEmpty())
        {
        	listaProductos.setSelectedIndex(0);
        	actualizarInfoProducto();
        }
        else
        {
        	txtAcidez.setText("");
        	txtAroma.setText("");
        	txtCuerpo.setText("");
        	txtDisponibles.setText("");
        	txtNombre.setText("");
        	txtPrecio.setText("");
        	txtConversion.setText("");
        }
    }
    
    /**
     * Actualiza la información del producto
     */
    public void actualizarInfoProducto()
    {
    	Producto p = (Producto) listaProductos.getSelectedValue();
    	txtAcidez.setText( p.darAcidez() );
    	txtAroma.setText( p.darAroma() );
    	txtCuerpo.setText( p.darCuerpo() );
    	txtDisponibles.setText( p.darCantidadDisponible() + " kilos");
    	txtNombre.setText( p.darNombre() );
    	txtPrecio.setText( "$ " + p.darPrecio() );
    	txtConversion.setText( "" + p.darConversion() );
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
    		DialogoAgregarProducto d = new DialogoAgregarProducto(principal);
    		d.setVisible(true);
    	}
    	else if( comando.equals(ELIMINAR) )
    	{
    		if(listaProductos.getSelectedValue() != null)
    		{
    			Producto p = (Producto) listaProductos.getSelectedValue();
    			principal.eliminarProducto(p.darNombre());
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(this, "Seleccione un producto", "Eliminar Producto", JOptionPane.ERROR_MESSAGE);
    		}
    		
    	}
    }

	/**
	 * Actualiza la información del nuevo producto seleccionado
	 * @param e Es el evento de cambio el ítem seleccionado en la lista
	 */
	public void valueChanged(ListSelectionEvent e) 
	{
		if(listaProductos.getSelectedValue() != null)
		{
			actualizarInfoProducto();	
		}
	}
}
