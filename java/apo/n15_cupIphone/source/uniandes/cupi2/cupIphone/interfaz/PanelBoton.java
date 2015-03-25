/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBoton.java 1082 2010-02-26 20:51:59Z y-oviedo $
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel de manejo de extensiones
 */
public class PanelBoton extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
     * Ventana principal de la aplicación
     */
    private InterfazCupIphone principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnMenu;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelBoton( InterfazCupIphone ventana )
    {
        principal = ventana;

        setLayout( new BorderLayout() );
        setBackground(Color.BLACK);
        add(new JLabel(new ImageIcon("data/imagenes/inferior1.png")), BorderLayout.WEST);
        
        //Botón
        btnMenu = new JButton(new ImageIcon("data/imagenes/boton.png"));        
        btnMenu.addActionListener( this );
        btnMenu.setBackground(Color.BLACK);
        btnMenu.setToolTipText("Volver al menú principal");
        btnMenu.setBorder(null);
        add(btnMenu, BorderLayout.CENTER);
        
        add(new JLabel(new ImageIcon("data/imagenes/inferior2.png")), BorderLayout.EAST);
        
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
            principal.mostrarMenuPrincipal();
    }

}
