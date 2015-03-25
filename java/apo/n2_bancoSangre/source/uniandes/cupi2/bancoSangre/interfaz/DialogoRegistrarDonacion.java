package uniandes.cupi2.bancoSangre.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.bancoSangre.mundo.BancoSangre;
import uniandes.cupi2.bancoSangre.mundo.TipoSangre;

/**
 * Dialogo con el formulario para registrar una donación
 */
public class DialogoRegistrarDonacion extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Comando para registrar la donación
	 */
	private final static String DONAR = "Donar";	

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación
	 */
	private InterfazBancoSangre principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

	/**
	 * Campo de texto de la edad del donador
	 */
	private JTextField txtEdad;
	
	/**
	 * ComboBox del sexo del donador
	 */
	private JComboBox comboSexo;
	
	/**
	 * Campo de texto del peso del donador
	 */
	private JTextField txtPeso;
	
	/**
	 * ComboBox del tipo de sangre del donador
	 */
	private JComboBox comboTipo;
	
	/**
	 * ComboBox del factor RH del donador
	 */
	private JComboBox comboRh;
	
	/**
	 * Botón Donar
	 */
	private JButton btnDonar;
	
	/**
	 * CheckBox de la enfermedad Fiebre reumática 
	 */
	private JCheckBox checkEnfermedad1;	
	
	/**
	 * CheckBox de la enfermedad Epilepsia 
	 */
	private JCheckBox checkEnfermedad2;
	
	/**
	 * CheckBox de la enfermedad Hepatitis
	 */
	private JCheckBox checkEnfermedad3;	
	
	/**
	 * CheckBox de la enfermedad Ictericia 
	 */
	private JCheckBox checkEnfermedad4;
	
	/**
	 * CheckBox de la enfermedad Sífilis u otras enfermedades venéreas
	 */
	private JCheckBox checkEnfermedad5;
	
	/**
	 * CheckBox de la enfermedad Cáncer 
	 */
	private JCheckBox checkEnfermedad6;
	
	/**
	 * CheckBox de la enfermedad Problemas cardíacos
	 */
	private JCheckBox checkEnfermedad7;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

	/**
	 * Método constructor del dialogo
	 * @param laPrincipal Ventana principal
	 */
	public DialogoRegistrarDonacion(InterfazBancoSangre laPrincipal)
	{
		principal = laPrincipal;
		
		setLayout( new BorderLayout() );
		setTitle( "Información Donador" );
		setSize( 300,400 );
		setLocationRelativeTo( principal );
		setModal( true );
		
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(5,2));
		temp.setBorder(new TitledBorder(" Información General "));
				
		temp.add(new JLabel(" Edad: "));
		txtEdad = new JTextField();
		temp.add(txtEdad);
		
		temp.add(new JLabel(" Sexo: "));
		comboSexo = new JComboBox(new String[]{BancoSangre.FEMENINO, BancoSangre.MASCULINO});
		temp.add(comboSexo);
		
		temp.add(new JLabel(" Peso (Kg): "));
		txtPeso = new JTextField();
		temp.add(txtPeso);
		
		temp.add(new JLabel(" Tipo de Sangre: "));
		comboTipo = new JComboBox(new String[]{TipoSangre.TIPO_A, TipoSangre.TIPO_B, TipoSangre.TIPO_AB, TipoSangre.TIPO_O});
		temp.add(comboTipo);
		
		temp.add(new JLabel(" RH: "));
		comboRh = new JComboBox(new String[]{TipoSangre.RH_P, TipoSangre.RH_N});
		temp.add(comboRh);
		
		add(temp, BorderLayout.NORTH);
		
		JPanel temp2 = new JPanel();
		temp2.setLayout(new GridLayout(8, 1));
		temp2.setBorder(new TitledBorder(" Historial médico "));
		
		temp2.add(new JLabel(" Padece o ha sufrido de: "));
		
		checkEnfermedad1 = new JCheckBox(" Fiebre reumática ");
		temp2.add(checkEnfermedad1);
		
		checkEnfermedad2 = new JCheckBox(" Epilepsia ");
		temp2.add(checkEnfermedad2);
		
		checkEnfermedad3 = new JCheckBox(" Hepatitis (después de los 12 años de edad) ");
		temp2.add(checkEnfermedad3);
		
		checkEnfermedad4 = new JCheckBox(" Ictericia (color amarillo de la piel) ");
		temp2.add(checkEnfermedad4);
		
		checkEnfermedad5 = new JCheckBox(" Sífilis u otras enfermedades venéreas ");
		temp2.add(checkEnfermedad5);
		
		checkEnfermedad6 = new JCheckBox(" Cáncer ");
		temp2.add(checkEnfermedad6);
		
		checkEnfermedad7 = new JCheckBox(" Problemas cardíacos ");
		temp2.add(checkEnfermedad7);
		
		add(temp2, BorderLayout.CENTER);
		
		btnDonar = new JButton(DONAR);
		btnDonar.setActionCommand(DONAR);
		btnDonar.addActionListener(this);
		add(btnDonar, BorderLayout.SOUTH);
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
		String evento = e.getActionCommand();
		if(evento.equals(DONAR))
		{
			String edadS = txtEdad.getText();
			String sexo = (String)comboSexo.getSelectedItem();
			String pesoS = txtPeso.getText();
			String tipo = (String)comboTipo.getSelectedItem();
			String rh = (String)comboRh.getSelectedItem();
			
			if(edadS!=null && pesoS!=null)
			{
				try
				{
					int edad = Integer.parseInt(edadS);
					double peso = Double.parseDouble(pesoS);
					boolean tieneEnfermedad = principal.donadorPadeceEnfermedad(checkEnfermedad1.isSelected(), checkEnfermedad2.isSelected(), checkEnfermedad3.isSelected(), checkEnfermedad4.isSelected(), checkEnfermedad5.isSelected(), checkEnfermedad6.isSelected(), checkEnfermedad7.isSelected());
					
					if(edad>0 && peso>0)
					{
						principal.registrarDonacion(edad, sexo, peso, tipo, rh, tieneEnfermedad);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(this, "La edad y el peso deben ser mayores a cero", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(this, "La edad y el peso deben ser valores numéricos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Debe llenar todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
