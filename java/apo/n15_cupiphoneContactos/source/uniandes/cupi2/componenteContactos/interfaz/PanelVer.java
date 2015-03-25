/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelVer.java 1133 2010-09-14 22:57:13Z carl-veg $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupiphoneComponenteContactos
 * Autor: Yeisson Oviedo - 23-feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.componenteContactos.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import uniandes.cupi2.componenteContactos.mundo.Contacto;

/**
 * Clase donde se coloca la imagen encabezado
 */
public class PanelVer extends JPanel implements ActionListener
{

	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Comando volver
	 */
	private static final String VOLVER = "Volver";

	/**
	 * Comando agregar
	 */
	private static final String AGREGAR = "Agregar";

	/**
	 * Comando aceptar
	 */
	private static final String ACEPTAR = "Aceptar";

	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------

	/**
	 * Campo nombre
	 */
	private JTextField txtNombre;

	/**
	 * Campo móvil
	 */
	private JTextField txtMovil;

	/**
	 * Campo casa
	 */
	private JTextField txtCasa;

	/**
	 * Campo nombre
	 */
	private JTextField txtTrabajo;

	/**
	 * Campo teléfono personal
	 */
	private JTextField txtPersonal;

	/**
	 * Campo correo
	 */
	private JTextField txtCorreo;

	/**
	 * Referencia al panel principal
	 */
	private ComponenteContactosPanel principal;

	/**
	 * Campo nombre
	 */
	private Contacto contacto;

	//-----------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------

	/**
	 * Método constructor por defecto. Coloca la imagen del encabezado de la aplicación.
	 * @param principal 
	 * @param c 
	 */
	public PanelVer( ComponenteContactosPanel principal, Contacto c )
	{
		this.principal = principal;
		this.contacto = c;
		setLayout(new GridBagLayout ());        

		agregarEtiqueta("Nombre: ", 0);

		txtNombre = agregarTexto(1);        

		agregarEtiqueta("Móvil: ", 2);

		txtMovil = agregarTexto(3);

		agregarEtiqueta("Casa: ", 4);

		txtCasa = agregarTexto(5);

		agregarEtiqueta("Trabajo: ", 6);

		txtTrabajo = agregarTexto(7);

		agregarEtiqueta("Personal: ", 8);

		txtPersonal = agregarTexto(9);

		agregarEtiqueta("Correo", 10);

		txtCorreo = agregarTexto(11);

		agregarBoton((c != null? ACEPTAR : AGREGAR), 0 );
		agregarBoton(VOLVER, 1 );

		if (c != null)
			mostrarContacto(c);
	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	private void mostrarContacto(Contacto c) {
		txtNombre.setEditable(false);
		txtNombre.setText(c.darNombre());
		txtMovil.setText(c.darTelefonoMovil());
		txtCasa.setText(c.darTelefonoCasa());
		txtTrabajo.setText(c.darTelefonoTrabajo());
		txtPersonal.setText(c.darTelefonoPersonal());
		txtCorreo.setText(c.darCorreo());
	}

	/**
	 * Utilitario para agregar un botón
	 */
	private JButton agregarBoton(String string, int i) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = i;
		gbc.gridy = 14;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		JButton res = new JButton(string);
		res.addActionListener(this);
		res.setActionCommand(string);
		add(res, gbc);
		return res;
	}

	/**
	 * Utilitario para agregar un campo de texto
	 */
	private JTextField agregarTexto(int i) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridwidth=2;
		gbc.gridy = i;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		JTextField res = new JTextField();
		add(res, gbc);
		return res;
	}

	/**
	 * Utilitario para agregar una etiqueta
	 */
	private void agregarEtiqueta(String string, int i) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridwidth=2;
		gbc.gridy = i;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		add(new JLabel(string), gbc);		
	}

	/**
	 * Manejo de eventos
	 * @param arg0 
	 */
	public void actionPerformed(ActionEvent arg0) {
		String c = arg0.getActionCommand();		
		try{
			if (c.equals(AGREGAR) && (contacto = verificar(new Contacto())) != null)			
				principal.agregar(contacto);		
			else if ((c.equals(ACEPTAR) && (contacto = verificar(contacto)) != null) 
					|| c.equals(VOLVER))
				principal.volver();
		}catch (IllegalArgumentException e){
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al verificar datos", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Utilitario para verificar los datos
	 */
	protected Contacto verificar(Contacto c){
		int num = 0, err = 0;
		if (!txtMovil.getText().isEmpty()){
			try{Long.parseLong(txtMovil.getText()); num++;}catch(NumberFormatException e){ err++;}
		}
		if (!txtCasa.getText().isEmpty()){
			try{Long.parseLong(txtCasa.getText());num++;}catch(NumberFormatException e){err++;}
		}
		if (!txtTrabajo.getText().isEmpty()){
			try{Long.parseLong(txtTrabajo.getText());num++;}catch(NumberFormatException e){err++;}
		}
		if (!txtPersonal.getText().isEmpty()){
			try{Long.parseLong(txtPersonal.getText());num++;}catch(NumberFormatException e){err++;}
		}

		if ( err != 0 )
			throw new IllegalArgumentException("Los datos son incorrectos");

		if ( num == 0)
			throw new IllegalArgumentException("Se requiere al menos un teléfono");
				
		c.cambiarNombre(txtNombre.getText());
		c.cambiarTelefonoMovil(txtMovil.getText());
		c.cambiarTelefonoCasa(txtCasa.getText());
		c.cambiarTelefonoTrabajo(txtTrabajo.getText());
		c.cambiarTelefonoPersonal(txtPersonal.getText());
		c.cambiarCorreo(txtCorreo.getText());

		return c;



	}

}
