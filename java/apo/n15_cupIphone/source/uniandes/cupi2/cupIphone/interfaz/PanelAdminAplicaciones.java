package uniandes.cupi2.cupIphone.interfaz;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.cupIphone.componentes.ProxyAplicacion;
import uniandes.cupi2.cupIphone.componentes.excepciones.InstalacionException;

/**
 * Panel del administrador de aplicaciones
 * @author y-oviedo
 */
public class PanelAdminAplicaciones extends JPanel {

	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Directorio donde se encuentran las imágenes usadas en la aplicación
	 */
	public static final String DIR_IMAGENES = PanelPantalla.DIR_IMAGENES + "adminApps/";

	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------

	/**
	 * Referencia a la interfaz
	 */
	private InterfazCupIphone principal;

	private JLabel lblMensajes;

	private JComboBox comboAplicaciones;
	
	//-----------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------		

	/**
	 * Constructor
	 * @param principal Referencia a la interfaz
	 */
	public PanelAdminAplicaciones(InterfazCupIphone principal) {
		this.principal = principal;
		
		setBackground(Color.LIGHT_GRAY);
		setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Administrador de aplicaciones"));
		setLayout(new GridLayout(3,1));				
		
		JPanel pnlInstalar = new JPanel(new GridBagLayout());
		pnlInstalar.setBorder(new LineBorder(Color.DARK_GRAY));
		add(pnlInstalar);
		
		//Botón para instalar
		JButton btnInstalar = new JButton("Instalar", new ImageIcon(DIR_IMAGENES + "instalar.png"));
		//Manejo del evento
		btnInstalar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Seleccione el jar a instalar");
				fc.setFileFilter(new FileNameExtensionFilter("Archivos de aplicación java (*.jar)", "jar"));
				fc.setCurrentDirectory(new File("./dist/"));				
				if (fc.showOpenDialog(PanelAdminAplicaciones.this) == JFileChooser.APPROVE_OPTION){
					ProxyAplicacion ap;
					try {
						ap = PanelAdminAplicaciones.this.principal.instalarProxyAplicacion(fc.getSelectedFile());
						mostrarMensaje("Aplicación " + ap.darID() + " instalada exitosamente", false);
						actualizarComboAplicaciones();
					} catch (InstalacionException e1) {
						mostrarMensaje(e1.getMessage(), true);
					}					
				}
			}
		});
		//Ubicación del botón
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0.6;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		btnInstalar.setSize(200, 150);
		pnlInstalar.add(btnInstalar, gbc) ;

		//Panel des instalar
		JPanel pnlDesinstalar = new JPanel(new GridBagLayout());
		pnlDesinstalar.setBorder(new LineBorder(Color.DARK_GRAY));
		add(pnlDesinstalar);

		pnlDesinstalar.add(new JLabel("Seleccione la aplicación a desinstalar"), gbc);		
		comboAplicaciones = new JComboBox();
		comboAplicaciones.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (comboAplicaciones.getSelectedIndex()!= 0 && comboAplicaciones.getSelectedItem()!= null)
				{
					try {
						ProxyAplicacion app = (ProxyAplicacion)comboAplicaciones.getSelectedItem();
						PanelAdminAplicaciones.this.principal.desInstalarProxyAplicacion(app);
						mostrarMensaje("Aplicación " + app.darID() + " desinstalada exitosamente", false);
						actualizarComboAplicaciones();
					} catch (InstalacionException e1) {
						mostrarMensaje(e1.getMessage(), true);
					}
				}				
			}

		});
		gbc.gridy = 1;
		pnlDesinstalar.add(comboAplicaciones, gbc);
		
		//Panel des instalar
		JPanel pnlMensajes = new JPanel(new GridBagLayout());
		pnlMensajes.setBorder(new LineBorder(Color.DARK_GRAY));
		add(pnlMensajes);
				
		lblMensajes = new JLabel();
		pnlMensajes.add(lblMensajes, gbc);		
		
		actualizarComboAplicaciones();
	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	private void actualizarComboAplicaciones() {
		int num = 0;
		comboAplicaciones.removeAllItems();
		comboAplicaciones.addItem("");
		for (Iterador<ProxyAplicacion> it = principal.darAplicaciones(); it.haySiguiente();){
			ProxyAplicacion app = it.darSiguiente();
			comboAplicaciones.addItem(app);
			num++;
		}
		lblMensajes.setText("CupIphone: " + ( num == 1 ? "1 aplicación instalada" : num + " aplicaciones instaladas"));
	}

	protected void mostrarMensaje(String mensaje, boolean error)
	{
		int tipo = (error ? JOptionPane.ERROR_MESSAGE: JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(this, mensaje, "CupIphone", tipo);
	}
	
}
