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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.toDoList.mundo.Tarea;

/**
 * Panel que muestra la información de las tareas de una categoría
 */
public class PanelTareas extends JPanel implements ListSelectionListener, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón agregar
     */
    public static final String AGREGAR = "Agregar";

    /**
     * Comando para el botón editar
     */
    public static final String EDITAR = "Editar";

    /**
     * Comando para el botón terminar
     */
    public static final String TERMINAR = "Terminar";

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
     * Lista de tareas
     */
    private JList listaTareas;

    /**
     * Label para el nombre de la tarea
     */
    private JLabel labelNombre;

    /**
     * Label para la descripción de la tarea
     */
    private JLabel labelDescripcion;

    /**
     * Label para la Fecha de inicio de la tarea
     */
    private JLabel labelCalendarioFechaInicio;

    /**
     * Label para la Fecha de inicio de la tarea
     */
    private JLabel labelCalendarioFechaFin;

    /**
     * Label para mostrar si la tarea está terminada
     */
    private JLabel labelTerminada;

    /**
     * Botón agregar tarea
     */
    private JButton btnAgregar;

    /**
     * Botón editar tarea
     */
    private JButton btnEditar;

    /**
     * Botón terminar tarea
     */
    private JButton btnTerminar;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un panel de tareas.
     * 
     * @param ventana Ventana principal de la aplicación. ventana != null
     */
    public PanelTareas( InterfazToDoList ventana )
    {

        principal = ventana;

        setLayout( new GridBagLayout( ) );
        setBorder( BorderFactory.createTitledBorder( " Tareas " ) );
        setPreferredSize( new Dimension( 560, 320 ) );
        setSize( getPreferredSize( ) );

        GridBagConstraints gbcG = new GridBagConstraints( 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        listaTareas = new JList( );
        listaTareas.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaTareas.addListSelectionListener( this );
        JScrollPane scroll = new JScrollPane( );
        scroll.setPreferredSize( new Dimension( 160, 250 ) );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scroll.getViewport( ).add( listaTareas );
        add( scroll, gbcG );

        JPanel info = new JPanel( );
        info.setPreferredSize( new Dimension( 340, 250 ) );
        info.setSize( info.getPreferredSize( ) );
        info.setLayout( new GridBagLayout( ) );
        info.setBorder( BorderFactory.createTitledBorder( " Información " ) );
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        info.add( new JLabel( "Nombre:" ), gbc );
        gbc.gridy++;

        labelNombre = new JLabel( );
        info.add( labelNombre, gbc );
        gbc.gridy++;

        info.add( new JLabel( "Descripción:" ), gbc );
        gbc.gridy++;

        labelDescripcion = new JLabel( );
        info.add( labelDescripcion, gbc );
        gbc.gridy++;

        info.add( new JLabel( "Inicio:" ), gbc );
        gbc.gridy++;

        labelCalendarioFechaInicio = new JLabel( );
        info.add( labelCalendarioFechaInicio, gbc );
        gbc.gridy++;

        info.add( new JLabel( "Finalización:" ), gbc );
        gbc.gridy++;

        labelCalendarioFechaFin = new JLabel( );
        info.add( labelCalendarioFechaFin, gbc );
        gbc.gridy++;

        info.add( new JLabel( "Terminada:" ), gbc );
        gbc.gridy++;

        labelTerminada = new JLabel( );
        info.add( labelTerminada, gbc );
        gbc.gridy++;

        gbcG.gridwidth = 2;
        gbcG.gridx = 1;
        add( info, gbcG );

        gbcG.gridy++;
        gbcG.gridx = 0;
        gbcG.gridwidth = 1;

        btnAgregar = new JButton( "Agregar" );
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );
        add( btnAgregar, gbcG );
        gbcG.gridx++;

        btnEditar = new JButton( "Editar" );
        btnEditar.setActionCommand( EDITAR );
        btnEditar.addActionListener( this );
        add( btnEditar, gbcG );
        gbcG.gridx++;

        btnTerminar = new JButton( "Terminar" );
        btnTerminar.setActionCommand( TERMINAR );
        btnTerminar.addActionListener( this );
        add( btnTerminar, gbcG );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de tareas
     * 
     * @param primeraTarea Primera tarea de la lista de tareas. Puede ser null
     */
    public void actualizarLista( Tarea primeraTarea )
    {
        ArrayList tareas = new ArrayList( );

        Tarea actual = primeraTarea;

        while( actual != null )
        {
            tareas.add( actual );
            actual = actual.darSiguienteTarea( );
        }

        listaTareas.setListData( tareas.toArray( ) );

        if( tareas.size( ) > 0 )
        {
            listaTareas.setSelectedIndex( 0 );
            Tarea t = ( Tarea )tareas.get( 0 );
            actualizarInformacion( t.darNombre( ), t.darDescripcion( ), t.darFechaInicio( ), t.darFechaFin( ), t.estaTerminada( ) );
        }
        else
        {
            limpiarInformacion( );
        }

    }

    /**
     * Actualiza la información de una tarea que se muestra en el panel con los datos dados por parámetro
     * 
     * @param nombre Nombre de la tarea. nombre != null
     * @param descripcion Descripción de la tarea. descripcion != null
     * @param inicio Fecha de inicio de la tarea. inicio != null
     * @param fin Fecha de finalización de la tarea. Si es null se muestra el texto "Indefinido"
     * @param terminada Indica si la tarea está terminada o no. true o false
     */
    public void actualizarInformacion( String nombre, String descripcion, Date inicio, Date fin, boolean terminada )
    {
        labelNombre.setText( nombre );
        labelDescripcion.setText( descripcion );
        labelCalendarioFechaInicio.setText( formatearFecha( inicio ) );
        if( fin != null )
        {
            labelCalendarioFechaFin.setText( formatearFecha( fin ) );
        }
        else
        {
            labelCalendarioFechaFin.setText( "Indefinido" );
        }
        if( terminada )
        {
            labelTerminada.setText( "Sí" );
        }
        else
        {
            labelTerminada.setText( "No" );
        }
    }

    /**
     * Limpia la información del panel que contiene los datos de una tarea
     */
    public void limpiarInformacion( )
    {
        labelNombre.setText( "" );
        labelDescripcion.setText( "" );
        labelCalendarioFechaInicio.setText( "" );
        labelCalendarioFechaFin.setText( "" );
        labelTerminada.setText( "" );
    }

    /**
     * Se llama cuando se cambia la selección en la lista de tareas<br>
     * Actualiza la información en el panel con los datos de la nueva tarea seleccionada.
     * 
     * @param e Evento que indica la nueva selección en la lista
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( listaTareas.getSelectedIndex( ) != -1 )
        {
            Tarea t = ( Tarea )listaTareas.getSelectedValue( );
            actualizarInformacion( t.darNombre( ), t.darDescripcion( ), t.darFechaInicio( ), t.darFechaFin( ), t.estaTerminada( ) );
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
        if( comando.equals( AGREGAR ) )
        {
            principal.mostrarDialogoAgregarTarea( );
        }
        else if( comando.equals( EDITAR ) )
        {
            principal.mostrarDialogoEditarTarea( );
        }
        else if( comando.equals( TERMINAR ) )
        {
            principal.terminarTarea( );
        }
    }

    /**
     * Retorna el String que representa a una fecha con el formato dd/MM/yyyy (hh:mm a)
     * 
     * @param fecha Fecha que se quiere formatear. fecha != null
     * @return String que representa la fecha dada
     */
    public String formatearFecha( Date fecha )
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy (hh:mm a)" );
        return sdf.format( fecha );
    }

    /**
     * Retorna el nombre de la tarea Seleccionada
     * 
     * @return nombre de la tarea seleccionada. Retorna null si no hay ninguna tarea seleccionadas
     */
    public String darNombreTareaSeleccionada( )
    {
        String nombre = null;
        if( listaTareas.getSelectedIndex( ) != -1 )
        {
            Tarea t = ( Tarea )listaTareas.getSelectedValue( );
            nombre = t.darNombre( );
        }
        return nombre;
    }
}
