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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author cvargasc
 *
 */
public class DialogoRegistrarConsumo extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3504384702737683161L;
	
	private InterfazCupiHotel ventana;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textoDescripcion;
	private JTextField textoValor;

	/**
	 * Create the dialog.
	 */
	public DialogoRegistrarConsumo(InterfazCupiHotel ventanaP) {
		ventana = ventanaP;
		setTitle("Registrar Consumo");
		setBounds(100, 100, 279, 121);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));
		{
			JLabel lblDescripcin = new JLabel("Descripción");
			contentPanel.add(lblDescripcin);
		}
		{
			textoDescripcion = new JTextField();
			contentPanel.add(textoDescripcion);
			textoDescripcion.setColumns(10);
		}
		{
			JLabel lblValor = new JLabel("Valor");
			contentPanel.add(lblValor);
		}
		{
			textoValor = new JTextField();
			contentPanel.add(textoValor);
			textoValor.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar Consumo");
				okButton.setActionCommand("registrar");
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
		if (comando.equals("registrar")){
			ventana.registrarConsumo(textoDescripcion.getText(), textoValor.getText());
			textoDescripcion.setText("");
			textoValor.setText("");
		} else if (comando.equals("cancelar")) {
			dispose();
		}
		
	}

}
