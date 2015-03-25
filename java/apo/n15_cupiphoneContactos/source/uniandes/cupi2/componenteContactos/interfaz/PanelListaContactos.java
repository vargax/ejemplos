/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelListaContactos.java 1083 2010-03-01 15:32:52Z y-oviedo $
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

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.componenteContactos.mundo.Contacto;

/**
 * Panel de manejo de extensiones
 */
public class PanelListaContactos extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para el botón ver
	 */
	private static final String VER = "Ver";

	/**
	 * Constante para el botón buscar
	 */
	private static final String BUSCAR = "Buscar";

	/**
     * Constante para agregar
     */
    private static final String AGREGAR = "AGREGAR";

    /**
     * Constante para eliminar
     */
    private static final String ELIMINAR = "ELIMINAR";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private ComponenteContactosPanel principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

	private JList lista;

	private JTextField txtNombreBuscar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelListaContactos( ComponenteContactosPanel ventana )
    {
        principal = ventana;
        setLayout(new BorderLayout());
        
        //PANEL CON EL LISTADO Y BUSQUEDA
        JPanel panelListaYBuscar = new JPanel(new GridLayout(2, 1));
        JPanel panelLista = new JPanel(new BorderLayout());        
        lista = new JList();
        JScrollPane pane = new JScrollPane(lista);
        panelLista.add(pane, BorderLayout.CENTER);
        JButton btnVer = new JButton(VER);
        btnVer.addActionListener(this);
        btnVer.setActionCommand(VER);
        panelLista.add(btnVer, BorderLayout.SOUTH);
        
        JPanel panelBuscar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        txtNombreBuscar = new JTextField();        
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 5;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBuscar.add(txtNombreBuscar, gbc);
        
        JButton btnBuscar = new JButton(BUSCAR);
        btnBuscar.setActionCommand(BUSCAR);
        btnBuscar.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 2;
        gbc.gridwidth = 3;
        panelBuscar.add(btnBuscar, gbc);
        
        panelListaYBuscar.add(panelLista);
        
        panelListaYBuscar.add(panelBuscar);
        
        //PANEL BOTONES
        JPanel panelBotones = new JPanel(new GridLayout( 1, 2 ));
        panelBotones.setBorder( new TitledBorder( "Acciones" ) );

        //Botón agregar
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );
        panelBotones.add(btnAgregar);
        
        //Botón eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setActionCommand( ELIMINAR );
        btnEliminar.addActionListener( this );
        panelBotones.add(btnEliminar);
        
        
        add(panelListaYBuscar, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        
        actualizar();
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
    	String comando = e.getActionCommand();
    	if (comando.equals(AGREGAR))
    		principal.mostrarAgregar();
    	else if (comando.equals(VER) && lista.getSelectedValue() != null)
    		principal.ver((Contacto)lista.getSelectedValue());    	
    	else if (comando.equals(BUSCAR) && !txtNombreBuscar.getText().isEmpty())
    		principal.buscar(txtNombreBuscar.getText());
    	else if (comando.equals(ELIMINAR)){
    		if (lista.getSelectedValue() == null){
    			JOptionPane.showMessageDialog(this, "No se ha seleccionado un contacto", "Contacto no seleccionado", JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		if (JOptionPane.showOptionDialog(this, "¿Está seguro?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si") == JOptionPane.YES_OPTION)
    			principal.eliminarContacto(((Contacto)lista.getSelectedValue()).darNombre());
    	}
    }

    /**
     * Actualiza el contenido de las listas
     */
	public void actualizar() {		
		Object[] iter =  principal.darContactosArreglo();
		lista.setListData(iter);
	}

}
