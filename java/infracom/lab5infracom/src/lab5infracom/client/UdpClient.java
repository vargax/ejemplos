package lab5infracom.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class UdpClient {
	// ----------------------------------------------------------
	// CONSTANTES
	// ----------------------------------------------------------
	private final static String DIRECCION_MULTICAST = "230.0.0.1";
	private final static String PUERTO_INICIAL = "5004";
	
	// ----------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------	
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	
	private boolean conectado = false;
	private boolean reproduciendo = false;
	
	private JTextField direccion;
	private JTextField puerto;
	
	private Button conectarDesconectar;
	private Button playStop;

	// ----------------------------------------------------------
	// CONSTRUCTOR
	// ----------------------------------------------------------
	private UdpClient() {
		final JFrame frame = new JFrame("Cliente UDP");
		frame.setLayout(new BorderLayout());
		frame.setLocation(100, 100);
		frame.setSize(1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		frame.add(mediaPlayerComponent, BorderLayout.CENTER);

		JPanel controles = new JPanel();
		controles.setLayout(new GridLayout());
		frame.add(controles, BorderLayout.SOUTH);

		direccion = new JTextField();
		direccion.setText(DIRECCION_MULTICAST);
		controles.add(direccion);

		puerto = new JTextField();
		puerto.setText(PUERTO_INICIAL);
		controles.add(puerto);

		conectarDesconectar = new Button("Conectar");
		conectarDesconectar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("conectarDesconectar :: Recibida solicitud de conexión a multicast...");
				
				if (direccion.getText().length() > 0 && puerto.getText().length() > 0) {
					String dir = "rtp://@"+direccion.getText()+":"+puerto.getText();
					conectarDesconectar(dir);
				} else {
					System.err.println("Servidor / puerto inválido!");
					new JOptionPane();
					JOptionPane.showMessageDialog(frame,"Introduzca la dirección del servidor y el puerto!");
				}
			}
		});
		controles.add(conectarDesconectar);

		playStop = new Button("Stop");
		playStop.setEnabled(false);
		playStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playStop();
			}
		});
		controles.add(playStop);
	}

	// ----------------------------------------------------------
	// METODOS
	// ----------------------------------------------------------
	private void conectarDesconectar(String direccion) {
		if (!conectado) {
			System.out.println("UdpClient.conectar() :: Conectando a "+direccion);
			mediaPlayerComponent.getMediaPlayer().playMedia(direccion);
			
			conectado = true;
			reproduciendo = true;
			
			conectarDesconectar.setLabel("Desconectar");
			playStop.setEnabled(true);	
		} else {
			playStop();
			conectado = false;
			conectarDesconectar.setLabel("Conectar");
			playStop.setEnabled(false);
		}
	}
	
	private void playStop() {
		if (reproduciendo) {
			mediaPlayerComponent.getMediaPlayer().stop();
			reproduciendo = false;
			playStop.setLabel("Reproducir");
		} else {
			mediaPlayerComponent.getMediaPlayer().play();
			reproduciendo = true;
			playStop.setLabel("Detener");
		}
	}
	
	// ----------------------------------------------------------
	// MAIN
	// ----------------------------------------------------------	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new UdpClient();
			}
		});
	}
}
