/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelPantalla.java 1085 2010-03-01 21:04:50Z y-oviedo $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupIphone
 * Autor: Yeisson Oviedo - 23-feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupIphone.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.cupIphone.componentes.ProxyAplicacion;
import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;

/**
 * Clase donde se coloca la imagen encabezado
 */
public class PanelPantalla extends JPanel implements ActionListener
{
	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Número máximo de iconos a mostrar en el menú principal
	 * a parte del administrador de aplicaciones
	 */
	private static final int MAX_ICONOS = 19;
	
	/**
	 * Directorio donde se encuentran las imágenes a mostrar en la pantalla
	 */
	public static final String DIR_IMAGENES = "data/imagenes/";

	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------

	/**
	 * Comando correspondiente al botón del administrador de aplicaciones
	 * desplegado en el menú principal
	 */
	private static final String ADMINISTRADOR_APLICACIONES = "ADMINISTRADOR_APLICACIONES";

	/**
	 * Referencia a la interfaz
	 */
	private InterfazCupIphone principal;	
	
	/**
	 * Indica que la aplicación se encuentra ejecutando
	 */
	private boolean corriendo = true;

	//-----------------------------------------------------------------
	// Atributos de la interfaz
	//-----------------------------------------------------------------

	/**
	 * Panel que representa la pantalla del CupIphone.
	 * Muestra el encabezado de la pantalla y el panel correspondiente
	 * al menú principal o a la aplicación que se esté ejecutando actualmente 
	 */
	private JPanel pnlPantalla;

	/**
	 * Panel que contiene el menú principal
	 */
	private JPanel pnlMenuPrincipal;

	/**
	 * Botón para el administrador de aplicaciones
	 */
	private JButton btnAdminAplicaciones;

	/**
	 * Referencia al panel que se está mostrando, sea el 
	 * administrador de aplicaciones, el menú principal
	 * o el panel de una aplicación en ejecución
	 */
	private JPanel pnlAplicacionActual;

	//-----------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------

	/**
	 * Método constructor por defecto. Coloca la imagen del encabezado de la aplicación.
	 * @param principal Referencia a la interfaz
	 */
	public PanelPantalla( InterfazCupIphone principal )
	{
		this.principal = principal;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		//Parte superior del teléfono
		JLabel imagen = new JLabel( );                
		ImageIcon icono = new ImageIcon( DIR_IMAGENES + "encabezado.png" );
		imagen.setBackground(Color.BLACK);
		// La agrega a la etiqueta
		imagen = new JLabel( "" );

		imagen.setIcon( icono );
		add( imagen, BorderLayout.NORTH );

		//PARTE CENTRAL DEL TELEFONO
		JPanel pnlAux = new JPanel();
		pnlAux.setLayout(new BorderLayout());
		add(pnlAux, BorderLayout.CENTER);
		//Lateral izquierda 
		pnlAux.add(new JLabel(new ImageIcon(DIR_IMAGENES + "lateralI.png")), BorderLayout.WEST);

		//Pantalla
		pnlPantalla = new JPanel(new BorderLayout(0,10));
		pnlPantalla.setBackground(Color.BLACK);
		pnlAux.add(pnlPantalla, BorderLayout.CENTER);

		//Encabezado de la pantalla. El encabezado nunca cambia, pero la 
		//hora se actualiza
		JPanel pnlEncabezado = new JPanel(new BorderLayout());
		pnlEncabezado.setBackground(Color.BLACK);
		pnlPantalla.add(pnlEncabezado, BorderLayout.NORTH);
		//Imagen con la señal
		pnlEncabezado.add(crearLabelEncabezado("CupIphone     ", DIR_IMAGENES + "senial.png"), BorderLayout.WEST);
		//Reloj que se muestra en la pantalla
		final JLabel lblHora = crearLabelEncabezado("", null);
		Thread t = new Thread(new Runnable() {						

			public void run() {
				while (corriendo)
					actualizarHora();
			}

			public synchronized void actualizarHora()
			{				
				try {
					lblHora.setText(new Date().toString());
					wait(1000);
				} catch (InterruptedException e) {
				}
			}
		});
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
		pnlEncabezado.add(lblHora, BorderLayout.CENTER);
		//Imagen con la batería
		pnlEncabezado.add(crearLabelEncabezado("", DIR_IMAGENES + "bateria.png"), BorderLayout.EAST);

		//Lateral derecha
		pnlAux.add(new JLabel(new ImageIcon(DIR_IMAGENES + "lateralD.png")), BorderLayout.EAST);

		//CONFIGURACIÓN DEL MENÚ
		//Inicialización
		pnlAplicacionActual = pnlMenuPrincipal = new JPanel();
		pnlMenuPrincipal.setLayout(new GridLayout(5,4));
		pnlMenuPrincipal.setBackground(Color.BLACK);		

		//Configuración inicial del botón del administrador de aplicaciones
		try {
			btnAdminAplicaciones = crearBotonMenu("Apps", new URL("file:" + DIR_IMAGENES + "adminApps/adminApps.png"), ADMINISTRADOR_APLICACIONES);
		} catch (Exception e){}

		//Mostrar los elementos del menú
		mostrarMenuPrincipal();
	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();		
		if (comando.equals(ADMINISTRADOR_APLICACIONES))
		{
			//mostrar el panel correspondiente al administrador de aplicaciones
			mostrarPanel(new PanelAdminAplicaciones(principal));
		}
		else {
			//ejecutar la aplicación correspondiente, lo cual implicar
			//mostrar su panel
			try {
				mostrarPanel(principal.ejecutarAplicacion(comando));
			} catch (EjecucionException e) {
				e.printStackTrace();
				mostrarMensaje(e.getMessage(), true);
			}
		}

	}
	
	/**
	 * Detiene el reloj de la aplicación
	 */
	public void detener()
	{
		corriendo = false;
	}	

	/**
	 * Muestra un mensaje
	 * @param mensaje
	 * @param error
	 */
	public void mostrarMensaje(String mensaje, boolean error) {
		int tipo = (error ? JOptionPane.ERROR_MESSAGE: JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(this, mensaje, "CupIphone", tipo);
	}

	/**
	 * Muestra el menú principal. Si ya se está mostrando 
	 * el menú, no pasa nada. Si se encontraba ejecutando una
	 * aplicación, ésta se cierra, incluso si es el administrador 
	 * de aplicaciones
	 */
	public void mostrarMenuPrincipal(){
		mostrarPanel(pnlMenuPrincipal);
	}
	
	/**
	 * Indica si se debe mostrar u ocultar la pantalla principal
	 * @param pnl Panel que se desea mostrar
	 */
	protected void mostrarPanel (JPanel pnl){
		pnlPantalla.remove(pnlAplicacionActual);
		pnlMenuPrincipal.removeAll();
		pnlPantalla.add(pnl, BorderLayout.CENTER);
		pnlAplicacionActual = pnl;
		if (pnl == pnlMenuPrincipal)
			actualizarMenu();
		pnlPantalla.repaint();
	}

	//-----------------------------------------------------------------
	// Métodos auxiliares
	//-----------------------------------------------------------------

	/**
	 * Actualiza el contenido del menú
	 */
	protected void actualizarMenu() {	
		pnlMenuPrincipal.add(btnAdminAplicaciones);

		//Se agregan los botones correspondientes a cada una de las
		//aplicaciones instaladas
		int contador = 0;
		for(Iterador<ProxyAplicacion> iter = principal.darAplicaciones(); iter.haySiguiente(); )
		{
			ProxyAplicacion app = iter.darSiguiente();
			JButton btn = new JButton(app.darNombreEnPantalla(), new ImageIcon(app.darIcono()));
			btn.setBackground(Color.BLACK);
			btn.setActionCommand(app.darID());
			btn.setVerticalTextPosition(SwingConstants.BOTTOM);
			btn.setHorizontalTextPosition(SwingConstants.CENTER);
			btn.setForeground(Color.WHITE);
			btn.addActionListener(this);
			pnlMenuPrincipal.add(crearBotonMenu(app.darNombreEnPantalla(), app.darIcono(), app.darID()));
			contador++;
		}
		//Los demás espacios se rellenan con etiquetas vacías
		for (int i = contador; i < MAX_ICONOS; i++){
			pnlMenuPrincipal.add(new JLabel());
		}
		repaint();
	}

	/**
	 * Método auxiliar para facilitar la creación de los labels del encabezado
	 * @param texto
	 * @param imagen
	 * @return
	 */
	protected JLabel crearLabelEncabezado(String texto, String imagen){
		JLabel label = new JLabel(texto);
		if (imagen != null){
			label.setIcon(new ImageIcon(imagen));
		}
		label.setForeground(Color.WHITE);		
		return label;
	}

	/**
	 * Método auxiliar para facilitar la creación de los botones del menú
	 * @param texto
	 * @param urlIcono
	 * @param actionCommand
	 * @return
	 */
	protected JButton crearBotonMenu(String texto, URL urlIcono, String actionCommand ){
		final JButton btn = new JButton(texto, new ImageIcon(urlIcono));
		btn.setBackground(Color.BLACK);
		btn.setActionCommand(actionCommand);
		btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn.setHorizontalTextPosition(SwingConstants.CENTER);
		btn.setForeground(Color.WHITE);
		btn.addActionListener(this);
		btn.setBorder(null);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {			
				btn.setBackground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(Color.BLACK);
			}
		});
		return btn;
	}
}
