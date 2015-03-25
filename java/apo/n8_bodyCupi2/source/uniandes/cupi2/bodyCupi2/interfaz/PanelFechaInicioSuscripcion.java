package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.freixas.jcalendar.JCalendar;

/**
 * Construye un nuevo panel con un calendario para seleccionar
 * la fecha de inicio de la suscripción
 *
 */
public class PanelFechaInicioSuscripcion extends JPanel implements ActionListener
{
    // ------------------------------------------------------------------
    // Constantes
    // ------------------------------------------------------------------
    
    /**
     * Constante asociada al comando del botón para seleccionar la fecha
     */
    private final static String SELECCIONAR_FECHA="Seleccionar";
    
    
    // ------------------------------------------------------------------
    // Atributos de la interfaz
    // ------------------------------------------------------------------
    
    /**
     * Botón seleccionar fecha de apertura
     */
    private JButton btnSeleccionar;
    
    /**
     * Calendario para seleccionar una fecha
     */
    private JCalendar calendario;
    
    /**
     * Etiqueta seleccionar fecha
     */
    private JLabel lblSeleccionarFecha;
    
    /**
     * Panel para crear un usuario
     */
    private PanelCreacionUsuario panelCreacionUsuario;
    
    
    // ------------------------------------------------------------------
    // Constructores
    // ------------------------------------------------------------------
    
    
    /**
     * Construye el nuevo panel
     * @param panelCreacion El panel que contiene este panel
     */
    public PanelFechaInicioSuscripcion(PanelCreacionUsuario panelCreacion )
    {
        panelCreacionUsuario=panelCreacion;
        lblSeleccionarFecha=new JLabel("Seleccione la fecha de inicio de la suscripción" );    
        btnSeleccionar= new JButton("Seleccionar");
        btnSeleccionar.addActionListener( this );
        btnSeleccionar.setActionCommand( SELECCIONAR_FECHA );
        
        calendario = new JCalendar( JCalendar.DISPLAY_DATE , false );
        
        setLayout( new GridBagLayout( ) );
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets( 5, 5, 5, 5 );
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add( lblSeleccionarFecha, gridBagConstraints );
        
        gridBagConstraints.gridy = 1;
        add( calendario, gridBagConstraints );
        
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        
        add( btnSeleccionar, gridBagConstraints );
        
    }

    // ------------------------------------------------------------------
    // Metodos
    // ------------------------------------------------------------------
    
    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if(e.getActionCommand( ).equals( SELECCIONAR_FECHA ))
        {
            Date fechaTemp = calendario.getDate( );
            Calendar fechaReserva = Calendar.getInstance( );
            fechaReserva.setTime( fechaTemp );
            int diaMes=fechaReserva.get( Calendar.DAY_OF_MONTH );
            int mes=fechaReserva.get( Calendar.MONTH)+1;
            int anho=fechaReserva.get( Calendar.YEAR );
            panelCreacionUsuario.actualizarFechaReserva( anho, mes, diaMes );
            JOptionPane.showMessageDialog( this, "Fecha de suscripción actualizada", "Fecha de suscripción", JOptionPane.INFORMATION_MESSAGE );
        }
        
    }
}
