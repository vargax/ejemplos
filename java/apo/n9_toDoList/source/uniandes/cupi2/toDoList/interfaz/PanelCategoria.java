/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_toDoList
 * Autor: Carlos Navarrete Puentes - 24-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.toDoList.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel que muestra la información de una categoría y permite la navegación entre las diferentes categorías
 */
public class PanelCategoria extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Anterior
     */
    public static final String ANTERIOR = "Anterior";

    /**
     * Comando Agregar
     */
    public static final String AGREGAR = "Agregar";

    /**
     * Comando Siguiente
     */
    public static final String SIGUIENTE = "Siguiente";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazToDoList principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Label para el nombre de la categoría
     */
    private JLabel labelNombre;

    /**
     * Botón anterior
     */
    private JButton btnAnterior;

    /**
     * Botón agregar
     */
    private JButton btnAgregar;

    /**
     * Botón Siguiente
     */
    private JButton btnSiguiente;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel que muestra la información de una categoría
     * 
     * @param i Ventana principal de la aplicación
     */
    public PanelCategoria( InterfazToDoList i )
    {
        principal = i;

        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        setBorder( BorderFactory.createTitledBorder( " Categoría " ) );
        labelNombre = new JLabel( );
        add( labelNombre, gbc );

        gbc.gridy++;
        gbc.gridwidth = 1;

        btnAnterior = new JButton( "<" );
        btnAnterior.addActionListener( this );
        btnAnterior.setActionCommand( ANTERIOR );
        add( btnAnterior, gbc );
        gbc.gridx++;

        btnAgregar = new JButton( "Agregar" );
        btnAgregar.addActionListener( this );
        btnAgregar.setActionCommand( AGREGAR );
        add( btnAgregar, gbc );
        gbc.gridx++;

        btnSiguiente = new JButton( ">" );
        btnSiguiente.addActionListener( this );
        btnSiguiente.setActionCommand( SIGUIENTE );
        add( btnSiguiente, gbc );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la categoría
     * 
     * @return nombre de la categoría
     */
    public String darNombreCategoria( )
    {
        return labelNombre.getText( );
    }

    /**
     * Actualiza la información de la categoría
     * 
     * @param nombre Nombre de la categoría. nombre != null y nombre != ""
     */
    public void actualizar( String nombre )
    {
        labelNombre.setText( nombre );
    }

    /**
     * Limpia la información de la categoría
     */
    public void limpiar( )
    {
        labelNombre.setText( "" );
    }

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( comando.equals( ANTERIOR ) )
        {
            principal.anteriorCategoria( );
        }
        else if( comando.equals( AGREGAR ) )
        {
            principal.agregarCategoria( );
        }
        else if( comando.equals( SIGUIENTE ) )
        {
            principal.siguienteCategoria( );
        }
    }
}
