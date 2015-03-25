package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import mundo.Pelicula;

public class InfoPelicula extends JDialog implements ActionListener {
	private Pelicula pelicula;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textAño;
	private JTextField textAutor;
	private JTextArea textDescripcion;

	public InfoPelicula(Pelicula peliculaP) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pelicula = peliculaP;
		
		setTitle("Información Película");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{73, 73, 73, 73, 73, 73, 0};
		gbl_contentPanel.rowHeights = new int[]{36, 36, 36, 36, 36, 36, 36, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
			
			JLabel lblNombre = new JLabel("Nombre");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.fill = GridBagConstraints.BOTH;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 0;
			contentPanel.add(lblNombre, gbc_lblNombre);
			
			textNombre = new JTextField();
			textNombre.setText(pelicula.darNombre());
			GridBagConstraints gbc_textNombre = new GridBagConstraints();
			gbc_textNombre.gridwidth = 3;
			gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_textNombre.insets = new Insets(0, 0, 5, 5);
			gbc_textNombre.gridx = 1;
			gbc_textNombre.gridy = 0;
			contentPanel.add(textNombre, gbc_textNombre);
			
			JLabel lblAo = new JLabel("Año");
			GridBagConstraints gbc_lblAo = new GridBagConstraints();
			gbc_lblAo.fill = GridBagConstraints.BOTH;
			gbc_lblAo.insets = new Insets(0, 0, 5, 5);
			gbc_lblAo.gridx = 4;
			gbc_lblAo.gridy = 0;
			contentPanel.add(lblAo, gbc_lblAo);
			
			textAño = new JTextField(pelicula.darAño()+"");
			GridBagConstraints gbc_textAño = new GridBagConstraints();
			gbc_textAño.fill = GridBagConstraints.HORIZONTAL;
			gbc_textAño.insets = new Insets(0, 0, 5, 0);
			gbc_textAño.gridx = 5;
			gbc_textAño.gridy = 0;
			contentPanel.add(textAño, gbc_textAño);
			
			JLabel lblAutor = new JLabel("Autor");
			GridBagConstraints gbc_lblAutor = new GridBagConstraints();
			gbc_lblAutor.fill = GridBagConstraints.BOTH;
			gbc_lblAutor.insets = new Insets(0, 0, 5, 5);
			gbc_lblAutor.gridx = 0;
			gbc_lblAutor.gridy = 1;
			contentPanel.add(lblAutor, gbc_lblAutor);
		
			textAutor = new JTextField(pelicula.darAutor());
			GridBagConstraints gbc_textAutor = new GridBagConstraints();
			gbc_textAutor.gridwidth = 5;
			gbc_textAutor.fill = GridBagConstraints.HORIZONTAL;
			gbc_textAutor.insets = new Insets(0, 0, 5, 5);
			gbc_textAutor.gridx = 1;
			gbc_textAutor.gridy = 1;
			contentPanel.add(textAutor, gbc_textAutor);
		
			JLabel lblDescripcin = new JLabel("Descripción");
			GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
			gbc_lblDescripcin.gridwidth = 2;
			gbc_lblDescripcin.fill = GridBagConstraints.BOTH;
			gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripcin.gridx = 0;
			gbc_lblDescripcin.gridy = 2;
			contentPanel.add(lblDescripcin, gbc_lblDescripcin);
		
			textDescripcion = new JTextArea(pelicula.darDescripcion());
			textDescripcion.setLineWrap(true);
			JScrollPane jsp = new JScrollPane(textDescripcion);
			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			GridBagConstraints gbc_textDescripcion = new GridBagConstraints();
			gbc_textDescripcion.gridheight = 4;
			gbc_textDescripcion.gridwidth = 6;
			gbc_textDescripcion.fill = GridBagConstraints.BOTH;
			gbc_textDescripcion.insets = new Insets(0, 0, 5, 5);
			gbc_textDescripcion.gridx = 0;
			gbc_textDescripcion.gridy = 3;
			contentPanel.add(jsp, gbc_textDescripcion);
		
		// Panel Botones
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK")) dispose();
	}
}
