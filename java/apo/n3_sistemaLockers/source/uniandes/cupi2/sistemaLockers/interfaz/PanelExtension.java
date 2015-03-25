/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_sistemaLockers
 * Autor: Catalina Rodriguez - 23-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sistemaLockers.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";
    
    /**
     * Comando Locación Popular 
     */
    private static final String LOCACION_POPULAR = "Locacion";
    
    /**
     * Comando Total Asignado
     */
    private static final String PORCENTAJE_ASIGNACION = "Porcentaje";
    
    /**
     * Comando Locación Casilleros Desocupados
     */
    private static final String CASILLEROS_DESOCUPADOS = "Desocupados";
    
    /**
     * Comando Locaciones Todo Tipo
     */
    private static final String TODO_TIPO = "Todos";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazSistemaLockers principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;
    
    /**
     * Botón Porcentaje Total Asignado
     */
    private JButton btnPorcentaje;
    
    /**
     * Botón Locación Popular  
     */
    private JButton btnPopular;
    
    /**
     * Botón Locación Casilleros Desocupados
     */
    private JButton btnDesocupados;
    
    /**
     * Botón Locaciones Todo Tipo
     */
    private JButton btnTodoTipo;
   
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazSistemaLockers ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 2, 3 ) );

        //Botón Porcentaje Total Asignado
        btnPorcentaje = new JButton("Total Asignado");
        btnPorcentaje.setActionCommand( PORCENTAJE_ASIGNACION );
        btnPorcentaje.addActionListener(this);
        add(btnPorcentaje);
        
        //Botón Locación Popular
        btnPopular = new JButton("Locación Popular");
        btnPopular.setActionCommand(LOCACION_POPULAR);
        btnPopular.addActionListener(this);
        add(btnPopular);
        
        //Botón Locación casilleros desocupados
        btnDesocupados = new JButton("Locación más Desocupada");
        btnDesocupados.setActionCommand(CASILLEROS_DESOCUPADOS);
        btnDesocupados.addActionListener(this);
        add(btnDesocupados);
        
        //Botón Locaciones todo tipo
        btnTodoTipo = new JButton("Todo Tipos de Casilleros");
        btnTodoTipo.setActionCommand(TODO_TIPO);
        btnTodoTipo.addActionListener(this);
        add(btnTodoTipo);
        
        //Botón opción 1
        btnOpcion1 = new JButton("Opción 1");
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add(btnOpcion1);
        
        //Botón opción 2
        btnOpcion2 = new JButton("Opción 2");
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add(btnOpcion2);
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if(OPCION_1.equals( e.getActionCommand() ))
        {
            principal.reqFuncOpcion1();
        }
        else if(OPCION_2.equals( e.getActionCommand() ))
        {
            principal.reqFuncOpcion2();
        }
        else if(PORCENTAJE_ASIGNACION.equals( e.getActionCommand() ))
        {
        	principal.darPorcentajeTotalAsignado();
        }
        else if(LOCACION_POPULAR.equals( e.getActionCommand() ))
        {
        	principal.darLocacionPopular();
        }
        else if(CASILLEROS_DESOCUPADOS.equals( e.getActionCommand() ))
        {
        	principal.darLocacionConMasDesocupados();
        }
        else if(TODO_TIPO.equals( e.getActionCommand() ))
        {
        	principal.darNumLocacionesTodoTipo();
        }
    }

}
