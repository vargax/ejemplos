/**
 * 
 */
package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author cvargasc
 *
 */
public class DialogoRegistrarReserva extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3766848855356584229L;
	
	private InterfazCupiHotel ventana;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombrePersona;
	private JTextField textFechaInicio;
	private JTextField textDiasReservados;

	/**
	 * Create the dialog.
	 */
	public DialogoRegistrarReserva(InterfazCupiHotel ventanaP) {
		setTitle("Registrar Reserva");
		setBounds(100, 100, 316, 140);
		
		ventana = ventanaP;
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(3, 2, 0, 0));
		{
			JLabel lblNombre = new JLabel("Nombre");
			contentPanel.add(lblNombre);
		}
		{
			txtNombrePersona = new JTextField();
			contentPanel.add(txtNombrePersona);
			txtNombrePersona.setColumns(10);
		}
		{
			JLabel lblFechaInicio = new JLabel("Fecha de Inicio");
			contentPanel.add(lblFechaInicio);
		}
		{
			textFechaInicio = new JTextField();
			contentPanel.add(textFechaInicio);
			textFechaInicio.setColumns(10);
		}
		{
			JLabel lblDasReservados = new JLabel("Días Reservados");
			contentPanel.add(lblDasReservados);
		}
		{
			textDiasReservados = new JTextField();
			contentPanel.add(textDiasReservados);
			textDiasReservados.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Reservar");
				okButton.setActionCommand("reservar");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("cancelar");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("reservar"))
			ventana.registrarReserva(txtNombrePersona.getText(), textFechaInicio.getText(), textDiasReservados.getText());
		else if (comando.equals("cancelar"))
			dispose();
	}
}
