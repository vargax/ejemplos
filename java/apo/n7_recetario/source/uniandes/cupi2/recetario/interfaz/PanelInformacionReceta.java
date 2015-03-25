package uniandes.cupi2.recetario.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.recetario.mundo.Receta;

/**
 * Panel con la información detallada de una receta
 */
public class PanelInformacionReceta extends JPanel 
{
	// -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
    
    /**
     * Etiqueta con la imagen de la receta
     */
    private JLabel etiquetaImagen;
    
    /**
     * Campo de texto con la categoría de la receta
     */
    private JTextField txtCategoria;
    
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
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    
    /**
     * Constructor del panel
     */
    public PanelInformacionReceta()
    {
    	setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 5, 0, 5 ), new TitledBorder( "" ) ) );

        JPanel panelImagen = new JPanel( );
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBorder( new EmptyBorder( 0, 0, 0, 5 ));
        panelImagen.setBackground( new Color(239, 250, 252) );
        panelImagen.setBorder( new LineBorder( Color.BLACK ) );
        
        etiquetaImagen = new JLabel( );
        etiquetaImagen.setPreferredSize(new Dimension(250, 0));
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);
        
        panelImagen.add( etiquetaImagen );
        add( panelImagen, BorderLayout.WEST );

        JPanel panelInfo1 = new JPanel();
        panelInfo1.setLayout(new GridLayout(1, 8));
        panelInfo1.setBorder(new EmptyBorder(5,0,0,0));
        
        JLabel etiquetaCategoria = new JLabel(" Categoría:  ");
        etiquetaCategoria.setHorizontalAlignment(JLabel.RIGHT);
        panelInfo1.add(etiquetaCategoria);
        txtCategoria = new JTextField();
        txtCategoria.setEditable(false);
        panelInfo1.add(txtCategoria);
        
        JLabel etiquetaDificultad = new JLabel(" Dificultad:  ");
        etiquetaDificultad.setHorizontalAlignment(JLabel.RIGHT);
        panelInfo1.add(etiquetaDificultad);
        txtDificultad = new JTextField();
        txtDificultad.setEditable(false);
        panelInfo1.add(txtDificultad);
        
        JLabel etiquetaTiempo = new JLabel(" Tiempo:  ");
        etiquetaTiempo.setHorizontalAlignment(JLabel.RIGHT);
        panelInfo1.add(etiquetaTiempo);
        txtTiempo = new JTextField();
        txtTiempo.setEditable(false);
        panelInfo1.add(txtTiempo);
        
        add(panelInfo1, BorderLayout.SOUTH);
        
        JPanel panelInfo2 = new JPanel();
        panelInfo2.setLayout(new BorderLayout());
        
        areaIngredientes = new JTextArea();
        areaIngredientes.setEditable(false);
        areaIngredientes.setLineWrap(true);
        
        scrollIngredientes = new JScrollPane( areaIngredientes );
        scrollIngredientes.setPreferredSize(new Dimension(380, 100));
        scrollIngredientes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollIngredientes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollIngredientes.setBorder(new TitledBorder("Ingredientes: "));
        panelInfo2.add(scrollIngredientes, BorderLayout.NORTH);
        
        areaInstrucciones = new JTextArea();
        areaInstrucciones.setEditable(false);
        areaInstrucciones.setLineWrap(true);
        
        scrollInstrucciones = new JScrollPane( areaInstrucciones );
        scrollInstrucciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollInstrucciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollInstrucciones.setBorder(new TitledBorder("Instrucciones: "));
        panelInfo2.add(scrollInstrucciones, BorderLayout.CENTER);
        
        add(panelInfo2, BorderLayout.CENTER);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Actualiza la información del panel con la receta que entra por parámetro
     * @param receta Receta cuya información debe ser mostrada. receta != null
     */
	public void actualizarInformacion(Receta receta) 
	{
		setBorder( new CompoundBorder( new EmptyBorder( 0, 5, 0, 5 ), new TitledBorder( receta.darNombre() ) ) );
		txtCategoria.setText(receta.darCategoria());
		txtDificultad.setText("" + receta.darDificultad());
		txtTiempo.setText("" + receta.darTiempoPreparacion() + " Minutos");
		
		etiquetaImagen.setIcon(new ImageIcon(receta.darFoto()));
		
		String[] ingredientes = receta.darIngredientes();
		String ingredientesS = "";
		for (int i = 0; i < ingredientes.length; i++) 
		{
			ingredientesS += "  - " + ingredientes[i];	
			if(i != ingredientes.length-1)
			{
				ingredientesS += "\n";
			}
		}
		
		scrollIngredientes.setBorder(new TitledBorder("Ingredientes (" + ingredientes.length + "): "));
        areaIngredientes.setText(ingredientesS);
        areaInstrucciones.setText(receta.darInstrucciones());
        areaIngredientes.setCaretPosition(0);
        areaInstrucciones.setCaretPosition(0);  
	}
    
}
