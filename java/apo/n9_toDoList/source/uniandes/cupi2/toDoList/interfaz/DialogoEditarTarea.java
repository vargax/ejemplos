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
import java.util.Date;

import javax.swing.JDialog;

/**
 * Diálogo que se usa para editar la información de una tarea
 */
public class DialogoEditarTarea extends JDialog
{

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
     * Panel que contiene los campos de la tarea que se quiere editar
     */
    private PanelDatosEditarTarea panel;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo diálogo para editar la tarea dada
     * 
     * @param ventana Ventana principal de la aplicación
     */
    public DialogoEditarTarea( InterfazToDoList ventana )
    {
        principal = ventana;
        setLayout( new GridBagLayout( ) );
        setTitle( "Editar Tarea" );
        setSize( 250, 350 );
        int gridx = 0;
        int gridy = 0;
        GridBagConstraints gbc = new GridBagConstraints( gridx, gridy, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        panel = new PanelDatosEditarTarea( this );
        add( panel, gbc );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que se llama cuando se quiere editar una tarea
     */
    public void editarTarea( )
    {
        principal.editarTarea( this, panel.darNombre( ), panel.darDescripcion( ), panel.darCalendarioFechaInicio( ), panel.darCalendarioFechaFin( ) );
    }

    /**
     * Método que se llama para actualizar la información de una tarea en el panel
     * 
     * @param nombre Nombre de la tarea. nombre != null
     * @param descripcion Descripción de la tarea. descripcion != null
     * @param fechaInicio Fecha de inicio de la tarea. fechaInicio != null
     * @param fechaFin Fecha de finalización de la tarea. Puede ser null
     */
    public void actualizar( String nombre, String descripcion, Date fechaInicio, Date fechaFin )
    {
        panel.actualizar( nombre, descripcion, fechaInicio, fechaFin );
    }
}
