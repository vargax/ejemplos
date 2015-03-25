package uniandes.cupi2.crucero.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel para mostrar las fotos del destino
 */
public class PanelDestino extends JPanel implements ActionListener {
	
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	/** Comando para pasar a la anterior imagen **/
	public final static String ANTERIOR = "anterior";
	/** Comando para pasar a la siguiente imagen **/
	public final static String SIGUIENTE = "siguiente";
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/** Ventana principal del programa **/
	private InterfazCrucero principal;
	
	//-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	/** Etiqueta para mostrar el nombre del destino **/
	private JLabel labNombreDestino;
	/** Etiqueta para mostrar la imagen del destino **/
	private JLabel labImagenDestino;
	/** Etiqueta para mostrar el nombre de la imagen del destino **/
	private JLabel labNombreImagen;
	/** Botón para ir a la imagen anterior **/
	private JButton butAnterior;
	/** Botón para ir a la imagen siguiente **/
	private JButton butSiguiente;

	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	/**
	 * Crea el panel destino con el frame raíz.
	 * @param principalP Ventana Principal. principalP != null.
	 */
	public PanelDestino (InterfazCrucero principalP) {
		// Definiendo la interfaz principal del programa
		principal = principalP;
		// Estableciendo el layout del panel
		setLayout (new BorderLayout());
		// Estableciendo el título del panel
		TitledBorder border = BorderFactory.createTitledBorder( " Destino " );
		setBorder(border);
		// Crea la etiqueta para el título del destino
		labNombreDestino = new JLabel("");
		labNombreDestino.setForeground(Color.BLUE);
		labNombreDestino.setHorizontalAlignment(JLabel.CENTER);
		add(labNombreDestino, BorderLayout.NORTH);
		
		// Crea el ícono para la imagen, crea la etiqueta para la imagen
		labImagenDestino = new JLabel();
		labImagenDestino.setHorizontalAlignment(JLabel.CENTER);
		labNombreImagen = new JLabel();
		labNombreImagen.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel contenedorImagen = new JPanel();
		contenedorImagen.setLayout(new BorderLayout());
		contenedorImagen.add(labImagenDestino, BorderLayout.CENTER);
		contenedorImagen.add(labNombreImagen, BorderLayout.SOUTH);
				
		add(contenedorImagen, BorderLayout.CENTER);
		
		// Crea los botones
		butAnterior = new JButton("Imagen Anterior");
		butAnterior.setActionCommand(ANTERIOR);
		butAnterior.addActionListener(this);
		
		butSiguiente = new JButton("Imagen Siguiente");
		butSiguiente.setActionCommand(SIGUIENTE);
		butSiguiente.addActionListener(this);
		
		// Crea un panel para los botones y los agrega
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(1,2));
		panelBotones.add(butAnterior);
		panelBotones.add(butSiguiente);
		
		// Agrega el panel de los botones al panel Destino
		add(panelBotones, BorderLayout.SOUTH);
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Cambia la imagen del destino
	 */
	public void cambiarImagen(Icon foto, String nombreImagen) {
		labImagenDestino.setIcon(foto);
		labNombreImagen.setText(nombreImagen);
	}
	/**
	 * Cambia el destino
	 */
	public void cambiarDestino(String nombreDestino) {
		labNombreDestino.setText(nombreDestino);
	}
	//-----------------------------------------------------------------
    // Manejo de eventos
    //-----------------------------------------------------------------
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		
		if(comando.equals(ANTERIOR))
			principal.imagenAnterior();
		else if(comando.equals(SIGUIENTE))
			principal.imagenSiguiente();
	}
}