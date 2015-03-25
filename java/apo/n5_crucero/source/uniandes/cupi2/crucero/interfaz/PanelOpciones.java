package uniandes.cupi2.crucero.interfaz;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

/**
 * Panel para mostrar las opciones generales del programa
 */
public class PanelOpciones extends JPanel implements ActionListener {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	/** Comando para pasar al destino anterior**/
	public final static String ANTERIOR = "anterior";
	/** Comando para pasar al destino siguiente**/
	public final static String SIGUIENTE = "siguiente";
	/** Comando para agregar un destino **/
	public final static String AGREGAR = "agregar";
	/** Comando para buscar un destino **/
	public final static String BUSCAR = "buscar";
	/** Comando para eliminar un destino **/
	public final static String ELIMINAR = "eliminar";
	/** Comando para la Opción 1 **/
	public final static String OPCION1 = "opcion1";
	/** Comando para la Opción 2 **/
	public final static String OPCION2 = "opcion2";

	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/** Ventana principal del programa **/
	private InterfazCrucero principal;
	
	//-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	/** Botón para ir al destino anterior **/
	private JButton butAnterior;
	/** Botón para ir al destino siguiente **/
	private JButton butSiguiente;
	/** Botón para agregar un destino **/
	private JButton butAgregar;
	/** Botón para buscar un destino **/
	private JButton butBuscar;
	/** Botón para eliminar un destino **/
	private JButton butEliminar;
	/** Botón para la Opción 1 **/
	private JButton butOpcion1;
	/** Botón para la Opción 2 **/
	private JButton butOpcion2;
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	/**
	 * Crea el panel opciones con el frame raiz.
	 * @param principalP Ventana Principal. principalP != null.
	 */
	public PanelOpciones(InterfazCrucero principalP) {
		// Definiendo la interfaz principal del programa
		principal = principalP;
		// Estableciendo el layout del panel
		setLayout(new GridLayout(1,7));
		// Estableciendo el título del panel
		TitledBorder border = BorderFactory.createTitledBorder( " Opciones " );
		setBorder(border);
		// Crea los botones
		butAnterior = new JButton("<");
		butAnterior.setActionCommand(ANTERIOR);
		butAnterior.addActionListener(this);
		
		butSiguiente = new JButton(">");
		butSiguiente.setActionCommand(SIGUIENTE);
		butSiguiente.addActionListener(this);
		
		butAgregar = new JButton("Agregar");
		butAgregar.setActionCommand(AGREGAR);
		butAgregar.addActionListener(this);
		
		butBuscar = new JButton("Buscar");
		butBuscar.setActionCommand(BUSCAR);
		butBuscar.addActionListener(this);
		
		butEliminar = new JButton("Eliminar");
		butEliminar.setActionCommand(ELIMINAR);
		butEliminar.addActionListener(this);
		
		butOpcion1 = new JButton("Opción 1");
		butOpcion1.setActionCommand(OPCION1);
		butOpcion1.addActionListener(this);
		
		butOpcion2 = new JButton("Opción 2");
		butOpcion2.setActionCommand(OPCION2);
		butOpcion2.addActionListener(this);	
		
		// Creando la fuente para los botones
		Font fuente = new Font(Font.DIALOG,Font.PLAIN,10);
		butAgregar.setFont(fuente);
		butBuscar.setFont(fuente);
		butEliminar.setFont(fuente);
		butOpcion1.setFont(fuente);
		butOpcion2.setFont(fuente);
		// Agregando los botones
		add(butAnterior); add(butSiguiente); 
		add(butAgregar); add(butBuscar); add(butEliminar);
		add(butOpcion1); add(butOpcion2);
	}
	//-----------------------------------------------------------------
    // Manejo de eventos
    //-----------------------------------------------------------------
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		
		if(comando.equals(ANTERIOR))
			principal.destinoAnterior();
		else if(comando.equals(SIGUIENTE))
			principal.destinoSiguiente();
		else if(comando.equals(AGREGAR)) {
			String nombrePais = JOptionPane.showInputDialog("País:");
			String nombreCiudad = JOptionPane.showInputDialog("Ciudad:");
			principal.agregar(nombrePais,nombreCiudad);	
		} else if(comando.equals(BUSCAR)) {
			String nombrePais = JOptionPane.showInputDialog("País:");
			String nombreCiudad = JOptionPane.showInputDialog("Ciudad:");
			principal.buscar(nombrePais,nombreCiudad);
		} else if(comando.equals(ELIMINAR))
			principal.eliminar();
		else if(comando.equals(OPCION1))
			principal.opcion1();
		else if(comando.equals(OPCION2))
			principal.opcion2();
	}
}
