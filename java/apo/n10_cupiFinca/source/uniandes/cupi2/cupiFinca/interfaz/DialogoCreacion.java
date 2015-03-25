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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import uniandes.cupi2.cupiFinca.mundo.Casa;
import uniandes.cupi2.cupiFinca.mundo.Cebolla;
import uniandes.cupi2.cupiFinca.mundo.Cerca;
import uniandes.cupi2.cupiFinca.mundo.Zanahoria;

/**
 * El diálogo de la asignación de nuevos productos a producir en la finca
 */
public class DialogoCreacion extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante del comando para indicar que se puede ocupar el terreno
     */
    private static final String ACEPTAR = "Aceptar";

    /**
     * Constante del comando para indicar que se cancela la acción de producir
     */
    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupiFinca principal;

    /**
     * El identificador del producto a producir en la finca
     */
    private String producto;

    /**
     * La coordenada x del terreno en donde producir
     */
    private int x;

    /**
     * La coordenada y del terreno en donde producir
     */
    private int y;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * El panel con la lista de productos
     */
    private JPanel panelProductos;

    /**
     * El scroll con la lista de los productos
     */
    private JScrollPane scrollProductos;

    /**
     * El botón para la producción
     */
    private JButton btnProducir;

    /**
     * El botón para la cancelación
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Atributos Lista
    // -----------------------------------------------------------------

    /**
     * El grupo de botones de los productos que se pueden producir
     */
    private ButtonGroup buttonGroup;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea el diálogo con la lista de productos que se pueden producir
     * @param ventana La ventana principal de la Finca. ventana != null
     * @param pX La coordenada x del terreno en la finca. pX >= 0
     * @param pY La coordenada x del terreno en la finca. pY >= 0
     */
    public DialogoCreacion( InterfazCupiFinca ventana, int pX, int pY )
    {
        principal = ventana;

        x = pX;
        y = pY;

        setTitle( "Producir..." );
        setModal( true );
        setSize( new Dimension( 500, 500 ) );
        setResizable( false );
        setLayout( new BorderLayout( ) );

        // Panel de los productos
        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 2 ) );

        btnProducir = new JButton( "Aceptar" );
        btnProducir.setActionCommand( ACEPTAR );
        btnProducir.addActionListener( this );
        panelBotones.add( btnProducir );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelBotones.add( btnCancelar );

        add( panelBotones, BorderLayout.SOUTH );

        // Panel de productos
        buttonGroup = new ButtonGroup( );
        panelProductos = new JPanel( );
        panelProductos.setLayout( new GridLayout( 0, 1 ) );

        scrollProductos = new JScrollPane( panelProductos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        add( scrollProductos, BorderLayout.CENTER );
        actualizarLista( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualizar la lista de productos de la Finca
     */
    public void actualizarLista( )
    {

        double dinero = principal.darCantidadDineroFinca( );
        
        // El panel para la cebolla
        boolean activado = dinero >= Cebolla.COSTO_CULTIVO;
        JPanel panelCebolla = crearPanelProducto( Cebolla.NOMBRE_CULTIVO, Cebolla.COSTO_CULTIVO, Cebolla.RUTA_IMAGEN, activado );
        panelProductos.add( panelCebolla );

        // El panel para la zanahoria
        activado = dinero >= Zanahoria.COSTO_CULTIVO;
        JPanel panelZanahoria = crearPanelProducto( Zanahoria.NOMBRE_CULTIVO, Zanahoria.COSTO_CULTIVO, Zanahoria.RUTA_IMAGEN, activado );
        panelProductos.add( panelZanahoria );

        // El panel para la casa
        activado = dinero >= Casa.COSTO_CONSTRUCCION;
        JPanel panelCasa = crearPanelProducto( Casa.NOMBRE_CONSTRUCCION, Casa.COSTO_CONSTRUCCION, Casa.RUTA_IMAGEN, activado );
        panelProductos.add( panelCasa );

        // El panel para la cerca
        activado = dinero >= Cerca.COSTO_CONSTRUCCION;
        JPanel panelCerca = crearPanelProducto(Cerca.NOMBRE_CONSTRUCCION, Cerca.COSTO_CONSTRUCCION, Cerca.RUTA_IMAGEN, activado );
        panelProductos.add(panelCerca);
       
    }

    /**
     * Crea el panel con la información del producto que se puede instalar
     * @param nombreProducto El nombre del producto. nombreProducto != null
     * @param costoProducto El costo del producto. costoProducto >= 0
     * @param rutaImagen La ruta de la imagen del producto. rutaImagen != null
     * @param activo Si el producto está activo para instalar.
     * @param seleccionado Si ese producto está seleccionado.
     * @return El panel con la información del producto
     */
    private JPanel crearPanelProducto( String nombreProducto, double costoProducto, String rutaImagen, boolean activo )
    {
        JPanel panelProducto = new JPanel( );
        panelProducto.setLayout( new GridLayout( 1, 3 ) );

        JLabel labPrecio = new JLabel( nombreProducto );
        panelProducto.add( labPrecio );

        JTextField txtPrecio = new JTextField( "$" + costoProducto );
        txtPrecio.setEditable( false );
        panelProducto.add( txtPrecio );

        JToggleButton btnProducto = new JToggleButton( new ImageIcon( rutaImagen, nombreProducto ) );
        btnProducto.setEnabled( activo );
        btnProducto.setActionCommand( nombreProducto );
        btnProducto.addActionListener( this );
        buttonGroup.add( btnProducto );
        panelProducto.add( btnProducto );

        return panelProducto;
    }

    /**
     * Método que maneja los eventos de los botones
     * @param e El evento del botón. e != null
     */
    public void actionPerformed( ActionEvent e )
    {

        // Al producir
        if( e.getActionCommand( ).equals( ACEPTAR ) )
        {
            if( producto != null )
            {
                principal.producirEnTerreno( producto, x, y );
                dispose( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe seleccionar un producto", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }

        // Al cancelar
        else if( e.getActionCommand( ).equals( CANCELAR ) )
        {
            dispose( );
        }

        // Para seleccionar producto
        else
        {
            producto = e.getActionCommand( );
        }
    }
}
