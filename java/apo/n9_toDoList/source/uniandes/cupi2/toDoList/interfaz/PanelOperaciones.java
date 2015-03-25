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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelOperaciones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando ver tareas pendientes
     */
    private static final String VER_PENDIENTES = "Pendientes";

    /**
     * Comando ver tareas terminadas
     */
    private static final String VER_TERMINADAS = "Terminadas";

    /**
     * Comando ver tareas Vencidas
     */
    private static final String VER_VENCIDAS = "Vencidas";

    /**
     * Comando eliminar tareas terminadas
     */
    private static final String ELIMINAR_TERMINADAS = "Eliminar Terminadas";

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazToDoList principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón ver tareas pendientes
     */
    private JButton btnVerTareasPendientes;

    /**
     * Botón ver tareas terminadas
     */
    private JButton btnVerTareasTerminadas;

    /**
     * Botón ver tareas vencidas
     */
    private JButton btnVerTareasVencidas;

    /**
     * Botón eliminar tareas terminadas
     */
    private JButton btnEliminarTareasTerminadas;

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * 
     * @param ventana Ventana principal
     */
    public PanelOperaciones( InterfazToDoList ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        // Botón ver Tareas Pendientes
        btnVerTareasPendientes = new JButton( "Ver Tareas Pendientes" );
        btnVerTareasPendientes.setActionCommand( VER_PENDIENTES );
        btnVerTareasPendientes.addActionListener( this );
        add( btnVerTareasPendientes, gbc );
        gbc.gridx++;

        // Botón ver tareas terminadas
        btnVerTareasTerminadas = new JButton( "Ver Tareas Terminadas" );
        btnVerTareasTerminadas.setActionCommand( VER_TERMINADAS );
        btnVerTareasTerminadas.addActionListener( this );
        add( btnVerTareasTerminadas, gbc );
        gbc.gridx++;

        // Botón ver tareas vencidas
        btnVerTareasVencidas = new JButton( "Ver Tareas Vencidas" );
        btnVerTareasVencidas.setActionCommand( VER_VENCIDAS );
        btnVerTareasVencidas.addActionListener( this );
        add( btnVerTareasVencidas, gbc );
        gbc.gridx = 0;
        gbc.gridy++;

        // Botón ver tareas vencidas
        btnEliminarTareasTerminadas = new JButton( "Eliminar Tareas Terminadas" );
        btnEliminarTareasTerminadas.setActionCommand( ELIMINAR_TERMINADAS );
        btnEliminarTareasTerminadas.addActionListener( this );
        add( btnEliminarTareasTerminadas, gbc );
        gbc.gridx++;

        // Botón opción 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1, gbc );
        gbc.gridx++;

        // Botón opción 2
        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2, gbc );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( VER_PENDIENTES.equals( e.getActionCommand( ) ) )
        {
            principal.verTareasPendientes( );
        }
        else if( VER_TERMINADAS.equals( e.getActionCommand( ) ) )
        {
            principal.verTareasTerminadas( );
        }
        else if( VER_VENCIDAS.equals( e.getActionCommand( ) ) )
        {
            principal.verTareasVencidas( );
        }
        else if( ELIMINAR_TERMINADAS.equals( e.getActionCommand( ) ) )
        {
            principal.eliminarTareasTerminadas( );
        }
        else if( OPCION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
    }

}
