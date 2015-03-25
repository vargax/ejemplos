package uniandes.cupi2.calculadoraPunnett.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelOpciones extends JPanel implements ActionListener {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	public final static String OPCION1 = "opcion1";
	public final static String OPCION2 = "opcion2";
	//-----------------------------------------------------------------
    // Atributos
	//-----------------------------------------------------------------
	/** Ventana principal del programa **/
	private InterfacePunnett principal;
	//-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	/** Botón para la opción 1**/
	private JButton butOpcion1;
	/** Botón para la opción 2 **/
	private JButton butOpcion2;
	/** Campo de texto **/
	private JTextField genBuscado;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public PanelOpciones(InterfacePunnett principalP) {
		// Asignando la interface principal
		principal = principalP;
		// Estableciendo el layout
		setLayout(new GridLayout(2,2));
		// Estableciendo el título del panel
		TitledBorder border = BorderFactory.createTitledBorder( " Opciones " );
		setBorder(border);
		// Creando los botones
		butOpcion1 = new JButton("¿Frecuencia Gen?");
		butOpcion1.setActionCommand(OPCION1);
		butOpcion1.addActionListener(this);
		butOpcion1.setEnabled(false);
		add(butOpcion1);
		
		butOpcion2 = new JButton("Heterocigotos");
		butOpcion2.setActionCommand(OPCION2);
		butOpcion2.addActionListener(this);
		butOpcion2.setEnabled(false);
		add(butOpcion2);
		
		add(new JLabel());
		
		genBuscado = new JTextField();
		add(genBuscado);
		
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	public void habilitarOpciones(boolean habilitado) {
		butOpcion1.setEnabled(habilitado);
		butOpcion2.setEnabled(habilitado);
	}
	//-----------------------------------------------------------------
    // Manejo de Eventos
    //-----------------------------------------------------------------
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(OPCION1)) {
			if (genBuscado.getText() != null) {
				float frecuencia = principal.opcion1(genBuscado.getText());
				genBuscado.setText(frecuencia+"%");
			}
			else JOptionPane.showMessageDialog( this, "Debe introducir un Gen válido para continuar", "Frecuencia Gen", JOptionPane.ERROR_MESSAGE );
		}else if(comando.equals(OPCION2)) {
			principal.opcion2();
		}
	}
}
