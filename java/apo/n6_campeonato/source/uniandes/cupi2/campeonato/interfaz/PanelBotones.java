/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_campeonato 
 * Autor: Mario Sánchez - 21/07/2005 
 * Autor: J. Villalobos - 28/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.campeonato.interfaz;

import java.awt.event.*;

import javax.swing.*;

/**
 * Este es el panel donde están los botones que controlan la aplicación
 */
public class PanelBotones extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    private static final String CARGAR_EQUIPOS = "CARGAR_EQUIPOS";
    private static final String REGISTRAR_RESULTADO = "REGISTRAR_RESULTADO";
    private static final String OPCION_1 = "OPCION_1";
    private static final String OPCION_2 = "OPCION_2";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazCampeonato ventana;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Es el botón para cargar los equipos
     */
    private JButton botonCargarEquipos;

    /**
     * Es el botón para registrar un nuevo resultado
     */
    private JButton botonRegistrarResultado;

    /**
     * Es el botón para ejecutar el punto de extensión 1
     */
    private JButton botonOpcion1;

    /**
     * Es el botón para ejecutar el punto de extensión 2
     */
    private JButton botonOpcion2;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * @param ic - Es una referencia a la clase principal de la interfaz
     */
    public PanelBotones( InterfazCampeonato ic )
    {
        ventana = ic;
        // Inicializa el botón de cargar equipos
        botonCargarEquipos = new JButton( "Cargar Equipos" );
        botonCargarEquipos.setActionCommand( CARGAR_EQUIPOS );
        botonCargarEquipos.addActionListener( this );
        // Inicializa el botón de registrar un partido
        botonRegistrarResultado = new JButton( "Registrar Partido" );
        botonRegistrarResultado.setActionCommand( REGISTRAR_RESULTADO );
        botonRegistrarResultado.addActionListener( this );
        // Inicializa los botones de extensión
        botonOpcion1 = new JButton( "Opción 1" );
        botonOpcion1.setActionCommand( OPCION_1 );
        botonOpcion1.addActionListener( this );
        botonOpcion2 = new JButton( "Opción 2" );
        botonOpcion2.setActionCommand( OPCION_2 );
        botonOpcion2.addActionListener( this );
        // Adiciona los botones al panel
        add( botonCargarEquipos );
        add( botonRegistrarResultado );
        add( botonOpcion1 );
        add( botonOpcion2 );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Este método se ejecuta cuando se hace click sobre un botón
     * @param evento El evento del click sobre un botón. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( REGISTRAR_RESULTADO.equals( comando ) )
        {
            ventana.mostarDialogoResultado( );
        }
        else if( CARGAR_EQUIPOS.equals( comando ) )
        {
            ventana.cargarEquipos( );
        }
        else if( OPCION_1.equals( comando ) )
        {
            ventana.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( comando ) )
        {
            ventana.reqFuncOpcion2( );
        }
    }

    /**
     * Desactiva el botón de cargar, después de que el campeonato ya ha sido inicializado
     */
    public void desactivarBotonCargar( )
    {
        botonCargarEquipos.setEnabled( false );
    }
}