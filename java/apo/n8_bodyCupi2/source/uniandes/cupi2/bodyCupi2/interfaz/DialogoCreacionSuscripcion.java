package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.freixas.jcalendar.JCalendar;

import uniandes.cupi2.bodyCupi2.mundo.Suscripcion;

/**
 * Dialogo para crear una nueva suscripción
 */
public class DialogoCreacionSuscripcion extends JDialog implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    
    /**
     * Constante asociada a la creación de una suscripción
     */
    private final static String CREAR_SUSCRIPCION="Crear suscripción";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La ventana principal de la aplicación
     */
    private InterfazBodyCupi2 interfaz;
    
    /**
     * El id del usuario al cual se le crea la suscripción
     */
    private int idUsuario;
    
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
 
    /**
     * Botón para seleccionar fecha de inicio de la suscripción
     */
    private JButton btnSeleccionar;
    
    /**
     * Calendario para seleccionar una fecha
     */
    private JCalendar calendario;
    
    /**
     * Panel con la información del dialogo
     */
    private JPanel panelInformacion;
    
    /**
     * Combobox con los tipos de suscripciones
     */
    private JComboBox comboTipoSuscripciones;


    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    
    /**
     * Construye un dialogo para crear una nueva suscripcón 
     * @param ventanaPrincipal La ventana principal de la aplicación
     * @param elIdUsuario El id del usuario al cual se le crea la suscripción
     */
    public DialogoCreacionSuscripcion(InterfazBodyCupi2 ventanaPrincipal, int elIdUsuario)
    {
        interfaz=ventanaPrincipal;
        idUsuario=elIdUsuario;
        setTitle( "Crear suscripción" );
        setLayout( new BorderLayout( ) );
        setSize(350,350);
        
            
        btnSeleccionar= new JButton(CREAR_SUSCRIPCION);
        btnSeleccionar.addActionListener( this );
        btnSeleccionar.setActionCommand( CREAR_SUSCRIPCION );
        
        comboTipoSuscripciones=new JComboBox();
        comboTipoSuscripciones.addItem( Suscripcion.SUSCRIPCION_AMATEUR );
        comboTipoSuscripciones.addItem( Suscripcion.SUSCRIPCION_REGULAR );
        comboTipoSuscripciones.addItem( Suscripcion.SUSCRIPCION_MASTER );
        
        calendario = new JCalendar( JCalendar.DISPLAY_DATE , false );
        panelInformacion=new JPanel( );
        panelInformacion.setLayout( new BorderLayout( ));
        panelInformacion.add( calendario, BorderLayout.CENTER );
        panelInformacion.add( comboTipoSuscripciones, BorderLayout.NORTH);
        panelInformacion.add(btnSeleccionar,BorderLayout.SOUTH);
        panelInformacion.setBorder( new TitledBorder( "Crear Suscripción" ) );
        add(panelInformacion,BorderLayout.CENTER);
        setLocationRelativeTo(null);
        
    }
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Atiende el evento disparado por el usuario
     * @param e El evento disparado por el usuario
     */
    public void actionPerformed( ActionEvent e )
    {
        if(e.getActionCommand( ).equals( CREAR_SUSCRIPCION ))
        {
            String tipoSuscripcion=(String)comboTipoSuscripciones.getSelectedItem( );
            Date fechaTemp = calendario.getDate( );
            Calendar fechaReserva = Calendar.getInstance( );
            fechaReserva.setTime( fechaTemp );
            int diaMes=fechaReserva.get( Calendar.DAY_OF_MONTH );
            int mes=fechaReserva.get( Calendar.MONTH)+1;
            int anho=fechaReserva.get( Calendar.YEAR );
            interfaz.crearSuscripcion(idUsuario,tipoSuscripcion, diaMes,mes, anho);
        }
        
    }
    
}
