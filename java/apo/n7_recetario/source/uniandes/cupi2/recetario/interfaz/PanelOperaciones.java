package uniandes.cupi2.recetario.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel con las operaciones de ordenamiento y búsqueda
 */
public class PanelOperaciones extends JPanel implements ActionListener
{

	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Comando Ordenar
	 */
	private final static String ORDENAR = "Ordenar";
	
	/**
	 * Comando Buscar
	 */
	private final static String BUSCAR = "Buscar";
	
	/**
	 * Constante que representa la opción de ordenamiento por nombre
	 */
	private final static String ORDERNAR_NOMBRE = "Nombre";

	/**
	 * Constante que representa la opción de ordenamiento por nombre
	 */
	private final static String ORDERNAR_CATEGORIA = "Categoría";

	/**
	 * Constante que representa la opción de ordenamiento por nombre
	 */
	private final static String ORDERNAR_DIFICULTAD = "Dificultad";
	
	/**
	 * Constante que representa la opción de ordenamiento por nombre
	 */
	private final static String ORDERNAR_INGREDIENTES = "Número de Ingredientes";

	/**
	 * Constante que representa la opción de ordenamiento por nombre
	 */
	private final static String ORDERNAR_TIEMPO = "Tiempo de preparación";
	
	/**
	 * Constante que representa la opción de búsqueda por nombre
	 */
	private final static String BUSCAR_NOMBRE = "Nombre";
	
	/**
	 * Constante que representa la opción de búsqueda por ingrediente
	 */
	private final static String BUSCAR_INGREDIENTE = "Ingrediente";
	
	/**
	 * Constante que representa la opción de búsqueda de la receta más fácil
	 */
	private final static String BUSCAR_MAS_FACIL = "Más Fácil";
	
	/**
	 * Constante que representa la opción de búsqueda de la receta más difícil
	 */
	private final static String BUSCAR_MAS_DIFICIL = "Más Difícil";
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazRecetario principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Combo box con las opciones de ordenamiento
     */
    private JComboBox comboOrdenamiento;
    
    /**
     * Combo box con las opciones de búsqueda
     */
    private JComboBox comboBusqueda;
    
    /**
     * Botón Ordenar
     */
    private JButton btnOrdenar;
    
    /**
     * Botón Buscar
     */
    private JButton btnBuscar;
	
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelOperaciones(InterfazRecetario ventana)
    {
    	principal = ventana;
    	
    	setLayout(new GridLayout(1, 2));
    	
    	JPanel aux = new JPanel();
    	aux.setLayout(new GridLayout(1, 2));
    	aux.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10), new TitledBorder(" Ordenar ")));
    	
    	comboOrdenamiento = new JComboBox();
    	comboOrdenamiento.setBorder(new EmptyBorder(0, 0, 0, 10));
    	comboOrdenamiento.addItem(ORDERNAR_NOMBRE);
    	comboOrdenamiento.addItem(ORDERNAR_CATEGORIA);
    	comboOrdenamiento.addItem(ORDERNAR_DIFICULTAD);
    	comboOrdenamiento.addItem(ORDERNAR_TIEMPO);
    	comboOrdenamiento.addItem(ORDERNAR_INGREDIENTES);
    	aux.add(comboOrdenamiento);
    	
    	btnOrdenar = new JButton(ORDENAR);
    	btnOrdenar.setActionCommand(ORDENAR);
    	btnOrdenar.addActionListener(this);
    	aux.add(btnOrdenar);
    	
    	add(aux);
    	
    	JPanel aux2 = new JPanel();
    	aux2.setLayout(new GridLayout(1, 2));
    	aux2.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10), new TitledBorder(" Buscar ")));
    	
    	comboBusqueda = new JComboBox();
    	comboBusqueda.setBorder(new EmptyBorder(0, 0, 0, 10));
    	comboBusqueda.addItem(BUSCAR_NOMBRE);
    	comboBusqueda.addItem(BUSCAR_MAS_FACIL);
    	comboBusqueda.addItem(BUSCAR_MAS_DIFICIL);
    	comboBusqueda.addItem(BUSCAR_INGREDIENTE);
    	aux2.add(comboBusqueda);
    	
    	btnBuscar = new JButton(BUSCAR);
    	btnBuscar.setActionCommand(BUSCAR);
    	btnBuscar.addActionListener(this);
    	aux2.add(btnBuscar);
    	
    	add(aux2);
    }
    
    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
		if(comando.equals(ORDENAR))
		{
			String ordenamiento = (String) comboOrdenamiento.getSelectedItem();
			if(ordenamiento.equals(ORDERNAR_NOMBRE))
			{
				principal.ordenarPorNombre();
			}
			else if(ordenamiento.equals(ORDERNAR_CATEGORIA))
			{
				principal.ordenarPorCategoria();
			}
			else if(ordenamiento.equals(ORDERNAR_DIFICULTAD))
			{
				principal.ordenarPorDificultad();
			}
			else if(ordenamiento.equals(ORDERNAR_INGREDIENTES))
			{
				principal.ordenarPorNumeroIngredientes();
			}
			else if(ordenamiento.equals(ORDERNAR_TIEMPO))
			{
				principal.ordenarPorTiempoPreparacion();
			}
		}
		else if(comando.equals(BUSCAR))
		{
			String busqueda = (String) comboBusqueda.getSelectedItem();
			if(busqueda.equals(BUSCAR_NOMBRE))
			{
				String nombre = JOptionPane.showInputDialog(null, "Nombre: ", "Buscar receta", JOptionPane.QUESTION_MESSAGE);
				if(nombre != null && !nombre.isEmpty())
				{
					principal.buscarPorNombre(nombre);					
				}
			}
			else if(busqueda.equals(BUSCAR_INGREDIENTE))
			{
				String ingrediente = JOptionPane.showInputDialog(null, "Ingrediente: ", "Buscar receta", JOptionPane.QUESTION_MESSAGE);
				if(ingrediente != null && !ingrediente.isEmpty())
				{
					principal.buscarPorIngrediente(ingrediente);
				}
			}
			else if(busqueda.equals(BUSCAR_MAS_DIFICIL))
			{
				principal.buscarRecetaMasDificil();
			}
			else if(busqueda.equals(BUSCAR_MAS_FACIL))
			{
				principal.buscarRecetaMasFacil();
			}
		}
	}
}
