/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodríguez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelComandos extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes extensiones
    // -----------------------------------------------------------------

    /**
     * Comando para inactivar las acciones en el terreno
     */
    private static final String INACTIVAR = "Inactivar";

    /**
     * Comando para producir en un terreno
     */
    private static final String PRODUCIR = "Producir";

    /**
     * Comando para recoger de un terreno
     */
    private static final String RECOGER = "Recoger";

    /**
     * Comando para eliminar lo que se está produciendo en un terreno
     */
    private static final String ELIMINAR = "eliminar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupiFinca principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Texto con el contenido de la cantidad de dinero de la finca
     */
    private JTextField txtDinero;

    /**
     * Botón con la opción de producir
     */
    private JToggleButton btnInactivar;

    /**
     * Botón con la opción de producir
     */
    private JToggleButton btnProducir;

    /**
     * Botón con la opción de recoger
     */
    private JToggleButton btnRecoger;

    /**
     * Botón con la opción de eliminar
     */
    private JToggleButton btnEliminar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con los comandos
     * @param ventana Vínculo a la interfaz principal de la aplicación
     */
    public PanelComandos( InterfazCupiFinca ventana )
    {
        principal = ventana;

        setPreferredSize( new Dimension( 100, 530 ) );
        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;

        // El texto
        JLabel labDinero = new JLabel( "Dinero" );
        add( labDinero, gbc );

        txtDinero = new JTextField( );
        txtDinero.setEditable( false );
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy++;
        add( txtDinero, gbc );

        // El espacio Vacío
        JLabel labVacio = new JLabel( " " );
        gbc.gridy++;
        add( labVacio, gbc );

        // El grupo de botones
        ButtonGroup buttonGroup = new ButtonGroup( );

        // Botón de seleccionar
        btnInactivar = new JToggleButton( new ImageIcon( InterfazCupiFinca.RUTA_FLECHA ) );
        btnInactivar.setActionCommand( INACTIVAR );
        btnInactivar.addActionListener( this );
        btnInactivar.setPreferredSize( new Dimension( 60, 60 ) );
        btnInactivar.setSelected( true );
        buttonGroup.add( btnInactivar );
        gbc.gridy++;
        add( btnInactivar, gbc );

        // Botón de producir
        btnProducir = new JToggleButton( new ImageIcon( InterfazCupiFinca.RUTA_MARTILLO ) );
        btnProducir.setActionCommand( PRODUCIR );
        btnProducir.addActionListener( this );
        btnProducir.setPreferredSize( new Dimension( 60, 60 ) );
        buttonGroup.add( btnProducir );
        gbc.gridy++;
        add( btnProducir, gbc );

        // Botón de recoger
        btnRecoger = new JToggleButton( new ImageIcon( InterfazCupiFinca.RUTA_PALA ) );
        btnRecoger.setActionCommand( RECOGER );
        btnRecoger.addActionListener( this );
        btnRecoger.setPreferredSize( new Dimension( 60, 60 ) );
        buttonGroup.add( btnRecoger );
        gbc.gridy++;
        add( btnRecoger, gbc );

        // Botón de eliminar
        btnEliminar = new JToggleButton( new ImageIcon( InterfazCupiFinca.RUTA_CANECA ) );
        btnEliminar.setActionCommand( ELIMINAR );
        btnEliminar.addActionListener( this );
        btnEliminar.setPreferredSize( new Dimension( 60, 60 ) );
        buttonGroup.add( btnEliminar );
        gbc.gridy++;
        add( btnEliminar, gbc );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el texto con el dinero que tiene la finca
     * @param dinero El dinero que tiene la finca. dinero >= 0
     */
    public void actualizar( double dinero )
    {
        txtDinero.setText( "$ " + dinero );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que genera el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( INACTIVAR.equals( e.getActionCommand( ) ) )
        {
            principal.modificarEstado( InterfazCupiFinca.ESTADO_INACTIVO );
        }
        else if( PRODUCIR.equals( e.getActionCommand( ) ) )
        {
            principal.modificarEstado( InterfazCupiFinca.ESTADO_PRODUCCION );
        }
        else if( RECOGER.equals( e.getActionCommand( ) ) )
        {
            principal.modificarEstado( InterfazCupiFinca.ESTADO_RECOLECCION );
        }
        else if( ELIMINAR.equals( e.getActionCommand( ) ) )
        {
            principal.modificarEstado( InterfazCupiFinca.ESTADO_ELIMINACION );
        }
    }
}
