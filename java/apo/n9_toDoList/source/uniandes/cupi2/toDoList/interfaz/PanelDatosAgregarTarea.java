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

import java.awt.Dimension;
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
public class PanelDatosAgregarTarea extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica que sí
     */
    public static final String SI = "Si";

    /**
     * Constante que indica que no
     */
    public static final String NO = "No";

    /**
     * Comando para el botón btnAgregarTarea
     */
    public static final String AGREGAR_TAREA = "Agregar Tarea";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Diálogo al que pertenece el panel
     */
    private DialogoAgregarTarea principal;

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
    private JButton btnAgregar;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el panel para agregar una nueva tarea
     * 
     * @param dialogo Diálogo al que pertenece el panel. ventana != null
     */
    public PanelDatosAgregarTarea( DialogoAgregarTarea dialogo )
    {
        principal = dialogo;
        
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField( );

        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextField( );
        
        JLabel lblFechaInicio = new JLabel("Fecha de Inicio:");
        calendarioFechaInicio = new JDateChooser( );
        comboHoraInicio = new JComboBox( );
        for( int i = 0; i <= 23; i++ )
        {
            comboHoraInicio.addItem( i + ":00" );
            comboHoraInicio.addItem( i + ":30" );
        }
        
        JLabel lblFechaFinalizacion = new JLabel("Fecha de Finalización:");
        
        radioFechaFinSi = new JRadioButton(SI);
        
        radioFechaFinNo = new JRadioButton(NO);

        ButtonGroup grupoRadioBotones = new ButtonGroup( );
        
        grupoRadioBotones.add(radioFechaFinSi);
        grupoRadioBotones.add(radioFechaFinNo);
        
        radioFechaFinSi.setSelected( true );
        
        
        calendarioFechaFin = new JDateChooser( );
        comboHoraFinal = new JComboBox( );
        for( int i = 0; i <= 23; i++ )
        {
            comboHoraFinal.addItem( i + ":00" );
            comboHoraFinal.addItem( i + ":30" );
        }
        
        btnAgregar = new JButton( "Agregar Tarea" );
        btnAgregar.addActionListener( this );
        btnAgregar.setActionCommand( AGREGAR_TAREA );


       setLayout(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();
       
       c.gridx=0;
       c.gridy=0;
       c.gridwidth=2;
       c.gridheight=1;
       c.weightx=0;
       c.weighty=0;
       c.anchor=c.CENTER;
       c.fill=c.BOTH;

       
       add(lblNombre,c);
       
       c.gridy++;
       add(txtNombre,c);
       
       c.gridy++;
       add(lblDescripcion,c);
       
       c.gridy++;
       c.fill=c.BOTH;
       add(txtDescripcion,c);
       
       c.gridy++;
       add(lblFechaInicio,c);
       
       c.gridy++;
       c.gridwidth=1;
       add(calendarioFechaInicio,c);
       
       c.gridx++;
       add(comboHoraInicio,c);
       
       c.gridwidth=2;
       c.gridx=0;
       c.gridy++;
       add(lblFechaFinalizacion,c);
       
       c.gridy++;
       c.gridwidth=1;
       add(radioFechaFinSi,c);
       
       c.gridx++;
       add(radioFechaFinNo,c);
       
       c.gridy++;
       c.gridx=0;
       add(calendarioFechaFin,c);
       
       c.gridx++;
       add(comboHoraFinal,c);
       
       c.gridwidth=2;
       c.gridx=0;
       c.gridy++;
       add(btnAgregar,c);
       
       
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
     * Manejo de los eventos de los botones
     * 
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( AGREGAR_TAREA ) )
        {
            principal.agregarTarea( );
        }

    }
}
