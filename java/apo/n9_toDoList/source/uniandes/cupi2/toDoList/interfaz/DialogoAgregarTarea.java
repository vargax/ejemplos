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

import javax.swing.JDialog;

/**
 * Diálogo usado para pedir la información necesaria para agregar una nueva tarea
 */
public class DialogoAgregarTarea extends JDialog
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
     * Panel que contiene los campos requeridos para agregar una nueva tarea
     */
    private PanelDatosAgregarTarea panel;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo diálogo para agregar una nueva tarea
     * 
     * @param ventana Ventana Principal
     */
    public DialogoAgregarTarea( InterfazToDoList ventana )
    {
        principal = ventana;
        setLayout( new GridBagLayout( ) );
        setTitle( "Agregar Tarea" );
        setSize( 250, 350 );
        int gridx = 0;
        int gridy = 0;
        GridBagConstraints gbc = new GridBagConstraints( gridx, gridy, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        panel = new PanelDatosAgregarTarea( this );
        add( panel, gbc );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que se llama cuando se quiere agregar una nueva tarea
     */
    public void agregarTarea( )
    {
        principal.agregarTarea( this, panel.darNombre( ), panel.darDescripcion( ), panel.darCalendarioFechaInicio( ), panel.darCalendarioFechaFin( ) );
    }
}
