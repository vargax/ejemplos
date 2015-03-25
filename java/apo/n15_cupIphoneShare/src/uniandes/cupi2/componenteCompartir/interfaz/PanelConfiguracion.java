package uniandes.cupi2.componenteCompartir.interfaz;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;

public class PanelConfiguracion extends JPanel {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private PanelComponenteCompartir principal;
	//-----------------------------------------------------------------
    // Atributos Interfaz
    //-----------------------------------------------------------------
	public JTextField txtNombreCliente;
	public JTextField txtPuerto;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	public JList listaUsuarios;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	public JList listaDescargas;
	public JList listaArchivos;
	private JButton btnAplicar;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public PanelConfiguracion(PanelComponenteCompartir principalP) {
		principal = principalP;
		
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Configuraci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		JLabel lblNombreDelCliente = new JLabel("Nombre del cliente:");
		GridBagConstraints gbc_lblNombreDelCliente = new GridBagConstraints();
		gbc_lblNombreDelCliente.fill = GridBagConstraints.BOTH;
		gbc_lblNombreDelCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDelCliente.gridx = 0;
		gbc_lblNombreDelCliente.gridy = 0;
		add(lblNombreDelCliente, gbc_lblNombreDelCliente);
		
		txtNombreCliente = new JTextField(principal.mundo.darConfiguracion()[0]);
		GridBagConstraints gbc_txtNombreCliente = new GridBagConstraints();
		gbc_txtNombreCliente.fill = GridBagConstraints.BOTH;
		gbc_txtNombreCliente.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombreCliente.gridx = 1;
		gbc_txtNombreCliente.gridy = 0;
		add(txtNombreCliente, gbc_txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		JLabel lblPuertoServidor = new JLabel("Puerto Servidor:");
		GridBagConstraints gbc_lblPuertoServidor = new GridBagConstraints();
		gbc_lblPuertoServidor.fill = GridBagConstraints.BOTH;
		gbc_lblPuertoServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblPuertoServidor.gridx = 0;
		gbc_lblPuertoServidor.gridy = 1;
		add(lblPuertoServidor, gbc_lblPuertoServidor);
		
		txtPuerto = new JTextField(principal.mundo.darConfiguracion()[1]);
		GridBagConstraints gbc_txtPuerto = new GridBagConstraints();
		gbc_txtPuerto.insets = new Insets(0, 0, 5, 0);
		gbc_txtPuerto.fill = GridBagConstraints.BOTH;
		gbc_txtPuerto.gridx = 1;
		gbc_txtPuerto.gridy = 1;
		gbc_txtPuerto.weightx = 1;
		add(txtPuerto, gbc_txtPuerto);
		txtPuerto.setColumns(10);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.setActionCommand("aplicar");
		btnAplicar.addActionListener(principal);
		GridBagConstraints gbc_btnAplicar = new GridBagConstraints();
		gbc_btnAplicar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAplicar.gridx = 1;
		gbc_btnAplicar.gridy = 2;
		add(btnAplicar, gbc_btnAplicar);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estad\u00EDsticas Archivos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		gbc_panel.gridwidth = 2;
		gbc_panel.gridheight = 1;
		gbc_panel.weighty = 0.4;
		add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane);
		
		scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		listaArchivos = new JList();
		scrollPane.setViewportView(listaArchivos);
		
		scrollPane_2 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_2);
		
		listaDescargas = new JList();
		scrollPane_2.setViewportView(listaDescargas);
		
		listaArchivos.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listaDescargas.setListData(principal.mundo.estadisticasArchivo((String)listaArchivos.getSelectedValue()).toArray());
			}
		});
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Usuarios Conectados", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 5;
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.gridheight = 1;
		gbc_panel_1.weighty = 0.4;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		listaUsuarios = new JList();
		scrollPane_1.setViewportView(listaUsuarios);
		
		listaUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					String usuarioSeleccionado = (String) listaUsuarios.getModel().getElementAt(listaUsuarios.locationToIndex(e.getPoint()));
					if(!usuarioSeleccionado.equals("Ningún usuario conectado")) {
						int i = JOptionPane.showConfirmDialog(null, "¿Desea desconectar a "+usuarioSeleccionado+"?", "Desconectar", JOptionPane.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION) principal.mundo.desconectarUsuario(usuarioSeleccionado);
					}
				}
			}
		});

	}

}
