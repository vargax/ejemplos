package uniandes.cupi2.recetario.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.recetario.mundo.Receta;

/**
 * Dialogo que permite agregar una nueva receta al recetario
 */
public class DialogoAgregarReceta extends JDialog implements ActionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Comando Agregar
	 */
	private final static String AGREGAR = "Agregar";
	
	/**
	 * Comando Seleccionar Imagen
	 */
	private final static String SELECCIONAR = "...";
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazRecetario principal;
    
	// -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
    
    /**
     * Campo de texto con la imagen de la receta
     */
    private JTextField txtImagen;

    /**
     * Campo de texto con el nombre de la receta
     */
    private JTextField txtNombre;
    
    /**
     * Combo box con la categorías del recetario
     */
    private JComboBox comboCategoria;
    
    /**
     * Campo de texto con dificultad de la receta
     */
    private JTextField txtDificultad;
    
    /**
     * Campo de texto con el tiempo de preparación de la receta
     */
    private JTextField txtTiempo;
    
    /**
     * Área de texto con los ingredientes de la receta
     */
    private JTextArea areaIngredientes;
    
    /**
     * Área de texto con las instrucciones de la receta
     */
    private JTextArea areaInstrucciones;

    /**
     * Panel con un scroll que contiene a areaIngredientes
     */
    private JScrollPane scrollIngredientes;
    
    /**
     * Panel con un scroll que contiene a areaInstrucciones
     */
    private JScrollPane scrollInstrucciones;
    
    /**
     * Botón Agregar
     */
    private JButton btnAgregar;
    
    /**
     * Botón Seleccionar Imagen
     */
    private JButton btnSeleccionar;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del dialogo
     * @param ventana Ventana principal
     */
    public DialogoAgregarReceta(InterfazRecetario ventana) 
    {
    	principal = ventana;
    	
    	setLayout( new BorderLayout( ) );
        setSize(335, 400);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Agregar Receta");
        
        JPanel panelInfo1 = new JPanel();
        panelInfo1.setLayout(new GridLayout(5, 2));
        
        panelInfo1.add(new JLabel(" Nombre: "));
        txtNombre = new JTextField();
        panelInfo1.add(txtNombre);
        
        panelInfo1.add(new JLabel(" Categoría: "));
        comboCategoria = new JComboBox();
        comboCategoria.addItem(Receta.CATEGORIA_1);
        comboCategoria.addItem(Receta.CATEGORIA_2);
        comboCategoria.addItem(Receta.CATEGORIA_3);
        comboCategoria.addItem(Receta.CATEGORIA_4);
        comboCategoria.addItem(Receta.CATEGORIA_5);
        comboCategoria.addItem(Receta.CATEGORIA_6);
        panelInfo1.add(comboCategoria);
        
        panelInfo1.add(new JLabel(" Dificultad: "));
        txtDificultad = new JTextField();
        panelInfo1.add(txtDificultad);
        
        panelInfo1.add(new JLabel(" Tiempo preparación (min): "));
        txtTiempo = new JTextField();
        panelInfo1.add(txtTiempo);

        JPanel aux = new JPanel();
        aux.setLayout(new BorderLayout());
        
        panelInfo1.add(new JLabel(" Imagen: "));
        txtImagen = new JTextField();
        txtImagen.setEditable(false);
        aux.add(txtImagen, BorderLayout.CENTER);
        btnSeleccionar = new JButton(SELECCIONAR);
        btnSeleccionar.setActionCommand(SELECCIONAR);
        btnSeleccionar.addActionListener(this);
        btnSeleccionar.setPreferredSize(new Dimension(50, 0));
        aux.add(btnSeleccionar, BorderLayout.EAST);
        
        panelInfo1.add(aux);        
        
        add(panelInfo1, BorderLayout.NORTH);
        
        JPanel panelInfo2 = new JPanel();
        panelInfo2.setLayout(new BorderLayout());
        
        areaIngredientes = new JTextArea();
        areaIngredientes.setLineWrap(true);
        
        scrollIngredientes = new JScrollPane( areaIngredientes );
        scrollIngredientes.setPreferredSize(new Dimension(0, 80));
        scrollIngredientes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollIngredientes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollIngredientes.setBorder(new TitledBorder("Ingredientes (separados con coma): "));
        panelInfo2.add(scrollIngredientes, BorderLayout.NORTH);
        
        areaInstrucciones = new JTextArea();
        areaInstrucciones.setLineWrap(true);
        
        scrollInstrucciones = new JScrollPane( areaInstrucciones );
        scrollInstrucciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollInstrucciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollInstrucciones.setBorder(new TitledBorder("Instrucciones: "));
        panelInfo2.add(scrollInstrucciones, BorderLayout.CENTER);
        
        add(panelInfo2, BorderLayout.CENTER);
        
        btnAgregar = new JButton(AGREGAR);
        btnAgregar.setActionCommand(AGREGAR);
        btnAgregar.addActionListener(this);
                
        add(btnAgregar, BorderLayout.SOUTH);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
		if(comando.equals(AGREGAR))
		{
			String nombre = txtNombre.getText();
			String categoria = (String) comboCategoria.getSelectedItem();
			String dificultadS = txtDificultad.getText();
			String tiempoS = txtTiempo.getText();
			String imagen = txtImagen.getText();
			String ingredientesS = areaIngredientes.getText();
			String instrucciones = areaInstrucciones.getText();
			
			if(nombre != null && !nombre.isEmpty() && dificultadS != null && !dificultadS.isEmpty() && 
					tiempoS != null && !tiempoS.isEmpty() && imagen != null && !imagen.isEmpty() && 
					ingredientesS != null && !ingredientesS.isEmpty() && instrucciones != null && 
					!instrucciones.isEmpty())
			{
				try
				{
					int dificultad = Integer.parseInt(dificultadS);
					int tiempo = Integer.parseInt(tiempoS);
					String[] ingredientes = ingredientesS.split(",");
					
					if(tiempo > 0)
					{
						if(dificultad > 0 && dificultad <= 10)
						{
							principal.agregarReceta(nombre, imagen, dificultad, categoria, tiempo, ingredientes, instrucciones);
							this.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(this, "La dificultad debe estar entre 1 y 10", "Error", JOptionPane.ERROR_MESSAGE);													
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, "El tiempo de preparación debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);						
					}
				}
				catch (NumberFormatException ex) 
				{
					JOptionPane.showMessageDialog(this, "El tiempo de preparación y la dificultad de la receta deben ser valores numéricos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Por favor ingrese la información completa de la receta", "Error", JOptionPane.ERROR_MESSAGE);				
			}
		}
		else if(comando.equals(SELECCIONAR))
		{
			JFileChooser fileChooser = new JFileChooser( "./data/imagenes" );
			if( fileChooser.showOpenDialog( principal ) == JFileChooser.APPROVE_OPTION )
			{
				txtImagen.setText(fileChooser.getSelectedFile( ).getAbsolutePath( ));                
			}			
		}
	}
}
