package uniandes.cupi2.componenteCompartir.interfaz;

import javax.mail.MessagingException;
import javax.swing.JPanel;

import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JProgressBar;

import uniandes.cupi2.componenteContactos.mundo.Contacto;
import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;
import uniandes.cupi2.mailer.EnvioException;
import javax.swing.JLabel;

public class PanelArchivos extends JPanel {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private PanelComponenteCompartir principal;
	public JList listaArchivosRemotos;
	public JList listaArchivosLocales;
	public JLabel lblDescargando;
	public JLabel lblTamao;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public PanelArchivos(PanelComponenteCompartir principalP) {
		principal = principalP;
		
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		listaArchivosRemotos = new JList();
		listaArchivosRemotos.setBorder(new TitledBorder(null, "Archivos Remotos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		JScrollPane scrollPane = new JScrollPane(listaArchivosRemotos);
		splitPane.setRightComponent(scrollPane);
		
		listaArchivosRemotos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					String archivoSeleccionado = (String) listaArchivosRemotos.getModel().getElementAt(listaArchivosRemotos.locationToIndex(e.getPoint()));
					if(!archivoSeleccionado.equals("Desconectado")) {
						int i = JOptionPane.showConfirmDialog(null, "¿Desea descargar "+archivoSeleccionado+"?", "Descargar", JOptionPane.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION)
							try {
								principal.mundo.descargar(archivoSeleccionado);
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Se ha producido un error: \n"+e1.getMessage(),"Descargar Archivo",JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
					}
				}
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);
		
		listaArchivosLocales = new JList();
		listaArchivosLocales.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Archivos Locales", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		scrollPane_1.setViewportView(listaArchivosLocales);
		
		listaArchivosLocales.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					String archivoSeleccionado = (String) listaArchivosLocales.getModel().getElementAt(listaArchivosLocales.locationToIndex(e.getPoint()));
					if(!archivoSeleccionado.equals("Ningún archivo en el directorio local")) {
						int i = JOptionPane.showConfirmDialog(null, "¿Desea enviar por correo "+archivoSeleccionado+"?", "Correo Electrónico", JOptionPane.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION) {
							try {
								
								Object[] contactos = principal.mundo.darContactos().toArray();
								Contacto c = (Contacto) JOptionPane.showInputDialog(null,"Seleccione un contacto","Componente Contactos",JOptionPane.QUESTION_MESSAGE,null,contactos,contactos[0]);
								principal.mundo.enviarCorreo(archivoSeleccionado,c.darCorreo());
							} catch (NullPointerException e0) {
								JOptionPane.showMessageDialog(null, "No hay contactos disponibles","Correo Electrónico",JOptionPane.INFORMATION_MESSAGE);
								e0.printStackTrace();
							} catch (MessagingException e01) {
								JOptionPane.showMessageDialog(null, "Se presentó un error al enviar el mensaje","Correo Electrónico",JOptionPane.ERROR_MESSAGE);
								e01.printStackTrace();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Se ha producido un error: \n"+e1.getMessage(),"Correo Electrónico",JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setActionCommand("conectar");
		btnConectar.addActionListener(principal);
		panel.add(btnConectar);
		
		JProgressBar progressBar = new JProgressBar();
		panel.add(progressBar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setActionCommand("buscar");
		btnBuscar.addActionListener(principal);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblDescargando = new JLabel("Descargando:");
		panel_1.add(lblDescargando);
		
		lblTamao = new JLabel("Tama\u00F1o:");
		panel_1.add(lblTamao);
	}
}
