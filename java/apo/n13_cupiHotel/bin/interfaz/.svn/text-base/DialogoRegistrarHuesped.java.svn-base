package interfaz;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class DialogoRegistrarHuesped extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3082183098570346123L;
	
	private InterfazCupiHotel ventana;
	
	private JTextField textoNombre;
	private JTextField textoEdad;
	private final JLabel etiquetaID = new JLabel("Identificación:");
	private JTextField textoID;
	private JTextField textoDireccion;
	private JTextField textoTelefono;
	private JTextField textoNumeroNoches;
	private final JLabel label_2 = new JLabel("");

	/**
	 * Create the dialog.
	 */
	public DialogoRegistrarHuesped(InterfazCupiHotel ventanaP) {
		setTitle("Registrar Huesped");
		setMaximumSize(new Dimension(2147483647, 700));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 302, 222);
		
		ventana = ventanaP;
		
		getContentPane().setLayout(new GridLayout(7, 2, 0, 0));
		
		JLabel etiquetaNombre = new JLabel("Nombre:");
		etiquetaNombre.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(etiquetaNombre);
		
		textoNombre = new JTextField();
		getContentPane().add(textoNombre);
		textoNombre.setColumns(10);
		
		JLabel etiquetaEdad = new JLabel("Edad:");
		etiquetaEdad.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(etiquetaEdad);
		
		textoEdad = new JTextField();
		getContentPane().add(textoEdad);
		textoEdad.setColumns(10);
		etiquetaID.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(etiquetaID);
		
		textoID = new JTextField();
		getContentPane().add(textoID);
		textoID.setColumns(10);
		
		JLabel etiquetaDireccion = new JLabel("Dirección:");
		etiquetaDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		etiquetaDireccion.setHorizontalTextPosition(SwingConstants.LEADING);
		getContentPane().add(etiquetaDireccion);
		
		textoDireccion = new JTextField();
		getContentPane().add(textoDireccion);
		textoDireccion.setColumns(10);
		
		JLabel etiquetaTelfono = new JLabel("Teléfono:");
		etiquetaTelfono.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(etiquetaTelfono);
		
		textoTelefono = new JTextField();
		getContentPane().add(textoTelefono);
		textoTelefono.setColumns(10);
		
		JLabel etiquetaNumeroDeNoches = new JLabel("Número de Noches:");
		etiquetaNumeroDeNoches.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(etiquetaNumeroDeNoches);
		
		textoNumeroNoches = new JTextField();
		getContentPane().add(textoNumeroNoches);
		textoNumeroNoches.setColumns(10);
		getContentPane().add(label_2);
		
		JButton btnChekin = new JButton("Agregar");
		btnChekin.setActionCommand("agregar");
		btnChekin.addActionListener(this);
		getContentPane().add(btnChekin);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("agregar")) 
			ventana.registrarHuesped(textoNombre.getText(), textoEdad.getText(), textoID.getText(), textoID.getText(), textoTelefono.getText(), textoNumeroNoches.getText());
	}
}