package uniandes.cupi2.recetario.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.recetario.mundo.Receta;

/**
 * Panel con la lista de recetas del recetario
 */
public class PanelListaRecetas extends JPanel implements ListSelectionListener, ActionListener
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
	/**
	 * Constante que representa el comando de agregar una receta
	 */
    private final static String AGREGAR = "Agregar";
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Ventana principal de la aplicación
     */
    private InterfazRecetario interfaz;
        
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
    
    /**
     * Lista con las recetas
     */
    private JList listaRecetas;
    
    /**
     * Panel con un scroll que contiene a listaRecetas
     */
    private JScrollPane scroll;
    
    /**
     * Botón para agregar una nueva receta
     */
    private JButton botonAgregar;
    
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    
    /**
     * Constructor del panel
     * @param ventanaPrincipal La ventana principal de la aplicación
     */
    public PanelListaRecetas(InterfazRecetario ventanaPrincipal)
    {   
        interfaz=ventanaPrincipal;
        
        setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 5, 0, 5), new TitledBorder( " Recetas " ) ) );
        setPreferredSize(new Dimension(250, 0));
        
        listaRecetas = new JList();
        listaRecetas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaRecetas.addListSelectionListener(this);
        
        scroll = new JScrollPane(listaRecetas);       
        
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 3, 3 ), new LineBorder( Color.BLACK, 1 ) ) );
        
        
        botonAgregar=new JButton( AGREGAR );
        botonAgregar.setActionCommand( AGREGAR );
        botonAgregar.addActionListener( this );

        add( scroll, BorderLayout.CENTER );
        add( botonAgregar, BorderLayout.SOUTH);
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de recetas con la lista recibida por parámetro
     * @param nuevaLista La lista con las recetas
     */
    public void refrescarLista(ArrayList nuevaLista)
    {
        listaRecetas.setListData( nuevaLista.toArray( ) );
        if(!nuevaLista.isEmpty())
        	listaRecetas.setSelectedIndex(0 );   
    }
    
    /**
     * Actualiza la receta seleccionada
     * @param index La posición de la receta que se debe seleccionar
     */
    public void seleccionar(int index)
    {
        listaRecetas.setSelectedIndex(index);
        listaRecetas.ensureIndexIsVisible(index);
    }
    
    /**
     * Método para atender el evento cuando un usuario selecciona una receta de la lista
     * @param evento El evento de selección de un elemento de la lista de recetas. evento != null
     */
    public void valueChanged( ListSelectionEvent evento )
    {
        if (listaRecetas.getSelectedValue() != null) {
        	int posRecetaSeleccionada = (int) listaRecetas.getSelectedIndex();
        	seleccionar(posRecetaSeleccionada);
        	Receta recetaSeleccionada = (Receta) listaRecetas.getSelectedValue();
        	interfaz.actualizarInfoReceta(recetaSeleccionada);
        }
    }

    /**
     * Manejo de los eventos de los botones
     * @param evento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent evento )
    {
       String comando = evento.getActionCommand();
       if(comando.equals(AGREGAR))
       {
    	   DialogoAgregarReceta dialogoAgregar = new DialogoAgregarReceta(interfaz);
    	   dialogoAgregar.setVisible(true);
       } 
    }
    
}
