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
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Panel que muestra la información de una tarea
 */
public class PanelDatosEditarTarea extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica que si
     */
    public static final String SI = "Si";

    /**
     * Constante que indica que no
     */
    public static final String NO = "No";

    /**
     * Comando Editar Tarea
     */
    public static final String EDITAR_TAREA = "Editar Tarea";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Diálogo al que pertenece el panel
     */
    private DialogoEditarTarea principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Campo para el nombre de la tarea
     */
    private JTextField txtNombre;

    /**
     * Campo para la descripción de la tarea
     */
    private JTextField txtDescripcion;

    /**
     * Fecha de inicio de la tarea
     */
    private JDateChooser calendarioFechaInicio;

    /**
     * Combobox para seleccionar la hora de inicio
     */
    private JComboBox comboHoraInicio;

    /**
     * Fecha de inicio de la tarea
     */
    private JDateChooser calendarioFechaFin;

    /**
     * Combobox para seleccionar la hora de finalización
     */
    private JComboBox comboHoraFinal;

    /**
     * RadioButton que indica si hay fecha de finalización
     */
    private JRadioButton radioFechaFinSi;

    /**
     * RadioButton que indica si no hay fecha de finalización
     */
    private JRadioButton radioFechaFinNo;

    /**
     * Botón para agregar una tarea
     */
    private JButton btnEditar;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el panel para agregar una nueva tarea
     * 
     * @param dialogo Diálogo al que pertenece el panel. ventana != null
     */
    public PanelDatosEditarTarea( DialogoEditarTarea dialogo )
    {
        principal = dialogo;
        setLayout( new GridBagLayout( ) );
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        add( new JLabel( "Nombre:" ), gbc );
        gbc.gridy++;

        txtNombre = new JTextField( );
        add( txtNombre, gbc );
        gbc.gridy++;

        add( new JLabel( "Descripción:" ), gbc );
        gbc.gridy++;

        txtDescripcion = new JTextField( );
        add( txtDescripcion, gbc );
        gbc.gridy++;

        add( new JLabel( "Fecha de Inicio:" ), gbc );
        gbc.gridy++;

        gbc.gridwidth = 1;
        calendarioFechaInicio = new JDateChooser( );
        add( calendarioFechaInicio, gbc );
        gbc.gridx++;

        comboHoraInicio = new JComboBox( );
        for( int i = 0; i <= 23; i++ )
        {
            comboHoraInicio.addItem( i + ":00" );
            comboHoraInicio.addItem( i + ":30" );
        }
        add( comboHoraInicio, gbc );
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;

        add( new JLabel( "Fecha de Finalización:" ), gbc );
        gbc.gridy++;

        gbc.gridwidth = 1;

        radioFechaFinSi = new JRadioButton( SI );
        radioFechaFinSi.setSelected( true );
        add( radioFechaFinSi, gbc );
        gbc.gridx++;

        radioFechaFinNo = new JRadioButton( NO );
        add( radioFechaFinNo, gbc );
        gbc.gridy++;
        gbc.gridx = 0;

        ButtonGroup grupoRadioBotones = new ButtonGroup( );
        grupoRadioBotones.add( radioFechaFinSi );
        grupoRadioBotones.add( radioFechaFinNo );

        calendarioFechaFin = new JDateChooser( );
        add( calendarioFechaFin, gbc );
        gbc.gridx++;

        comboHoraFinal = new JComboBox( );
        for( int i = 0; i <= 23; i++ )
        {
            comboHoraFinal.addItem( i + ":00" );
            comboHoraFinal.addItem( i + ":30" );
        }
        add( comboHoraFinal, gbc );

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;

        btnEditar = new JButton( "Editar Tarea" );
        btnEditar.addActionListener( this );
        btnEditar.setActionCommand( EDITAR_TAREA );
        add( btnEditar, gbc );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la tarea
     * 
     * @return nombre de la tarea
     */
    public String darNombre( )
    {
        return txtNombre.getText( );
    }

    /**
     * Retorna la descripción de la tarea
     * 
     * @return descripcion de la tarea
     */
    public String darDescripcion( )
    {
        return txtDescripcion.getText( );
    }

    /**
     * Fecha de inicio de la tarea
     * 
     * @return Fecha de inicio
     */
    public Date darCalendarioFechaInicio( )
    {
        Date fecha = calendarioFechaInicio.getDate( );
        String horaS = ( String )comboHoraInicio.getSelectedItem( );
        int hora = Integer.parseInt( horaS.split( ":" )[ 0 ] );
        int minutos = Integer.parseInt( horaS.split( ":" )[ 1 ] );

        return new GregorianCalendar( fecha.getYear( ) + 1900, fecha.getMonth( ), fecha.getDate( ), hora, minutos ).getTime( );
    }

    /**
     * Fecha de finalización de la tarea
     * 
     * @return fecha de finalización
     */
    public Date darCalendarioFechaFin( )
    {

        if( radioFechaFinSi.isSelected( ) )
        {
            Date fecha = calendarioFechaFin.getDate( );
            String horaS = ( String )comboHoraFinal.getSelectedItem( );
            int hora = Integer.parseInt( horaS.split( ":" )[ 0 ] );
            int minutos = Integer.parseInt( horaS.split( ":" )[ 1 ] );
            return new GregorianCalendar( fecha.getYear( ) + 1900, fecha.getMonth( ), fecha.getDate( ), hora, minutos ).getTime( );
        }
        else
        {
            return null;
        }
    }

    /**
     * Actualiza la información mostrada de una tarea con los datos dados por parámetro
     * 
     * @param nombre Nombre de la tarea. nombre != null
     * @param descripcion Descripción de la tarea. descripcion != null
     * @param fechaInicio Fecha de inicio de la tarea. fechaInicio != null
     * @param fechaFin fecha de finalización de la tarea. fechaFin puede ser null
     */
    public void actualizar( String nombre, String descripcion, Date fechaInicio, Date fechaFin )
    {
        txtNombre.setText( nombre );
        txtDescripcion.setText( descripcion );
        calendarioFechaInicio.setDate( fechaInicio );
        if( fechaFin != null )
        {
            calendarioFechaFin.setDate( fechaFin );
        }
    }

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( EDITAR_TAREA ) )
        {
            principal.editarTarea( );
        }
    }
}
