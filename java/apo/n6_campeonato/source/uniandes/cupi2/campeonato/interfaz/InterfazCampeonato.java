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

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import uniandes.cupi2.campeonato.mundo.*;

/**
 * Es la clase principal de la interfaz
 */
public class InterfazCampeonato extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es una referencia al campeonato
     */
    private Campeonato campeonato;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Es el panel donde se muestra el estado actual de la tabla de goles
     */
    private PanelTablaGoles panelTablaGoles;

    /**
     * Es el panel donde se muestra el estado actual de la tabla de posiciones
     */
    private PanelTablaPosiciones panelTablaPosiciones;

    /**
     * Es el panel donde se muestran los botones para controlar la aplicación
     */
    private PanelBotones panelBotones;

    /**
     * Es la ventana de diálogo para obtener los datos de un partido
     */
    private DialogoResultado dialogo;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa el campeonato indicando que no se ha cargado la información de ningún archivo
     */
    public InterfazCampeonato( )
    {
        campeonato = null;
        panelTablaGoles = new PanelTablaGoles( );
        panelBotones = new PanelBotones( this );
        panelTablaPosiciones = new PanelTablaPosiciones( );
        add( panelTablaGoles, BorderLayout.CENTER );
        add( panelTablaPosiciones, BorderLayout.EAST );
        add( panelBotones, BorderLayout.SOUTH );
        setSize( 820, 230 );
        setTitle( "Tabla de Resultados" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Muestra el diálogo usado para registrar el resultado de un partido. Este diálogo solo se despliega si el campeonato ya ha sido inicializado
     */
    public void mostarDialogoResultado( )
    {
        if( campeonato == null )
        {
            JOptionPane.showMessageDialog( this, "Debe cargar un campeonato primero", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            dialogo.limpiar( );
            dialogo.setVisible( true );
        }
    }

    /**
     * Registra un nuevo resultado en el campeonato
     * @param equipo1 Es el número del equipo 1
     * @param equipo2 Es el número del equipo 2
     * @param goles1 Es el número de goles marcados por el equipo 1
     * @param goles2 Es el número de goles marcados por el equipo 2
     */
    public void registrarResultado( int equipo1, int equipo2, int goles1, int goles2 )
    {
        if( campeonato != null )
        {
            try
            {
                campeonato.registrarResultado( equipo1, equipo2, goles1, goles2 );
                refrescar( );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ) );
            }
        }
    }

    /**
     * Carga los equipos a partir de un archivo seleccionado por el usuario
     */
    public void cargarEquipos( )
    {
        JFileChooser fc = new JFileChooser( "./data" );
        fc.setDialogTitle( "Abrir archivo de campeonato" );
        int resultado = fc.showOpenDialog( this );
        if( resultado == JFileChooser.APPROVE_OPTION )
        {
            File archivoCampeonato = fc.getSelectedFile( );
            try
            {
                // Crea el modelo del mundo, pasándole como parámetro el archivo del cual se debe cargar
                campeonato = new Campeonato( archivoCampeonato );
                // Crea el diálogo de captura de resultados, pasando como parámetro el nombre de los equipos del campeonato
                String[] nombreEquipos = new String[campeonato.darNumeroEquipos( )];
                for( int i = 0; i < campeonato.darNumeroEquipos( ); i++ )
                {
                    nombreEquipos[ i ] = campeonato.darEquipo( i ).toString( );
                }
                dialogo = new DialogoResultado( this, nombreEquipos );
                // Refresca la visualización de los paneles con los resultados
                panelTablaGoles.iniciarTablaGoles( campeonato );
                refrescar( );
                // Desactiva el botón con el que se cargó el campeonato
                panelBotones.desactivarBotonCargar( );
            }
            catch( Exception e )
            {
                campeonato = null;
                JOptionPane.showMessageDialog( this, "Hubo problemas cargando el campeonato: \n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                e.printStackTrace( );
            }
        }
    }

    /**
     * Actualiza los paneles con la tabla de goles y la tabla de posiciones
     */
    private void refrescar( )
    {
        panelTablaGoles.refrescar( );
        // Calcula la información necesaria y solicita el refresco de la información del panel que tiene la tabla de posiciones
        ArrayList tablaPosiciones = new ArrayList( );
        for( int i = 0; i < campeonato.darNumeroEquipos( ); i++ )
        {
            // La información de cada equipo la almacena en un arreglo de 8 cadenas de caracteres
            Equipo e = campeonato.darEquipo( i );
            String[] calculosEquipo = new String[8];
            calculosEquipo[ 0 ] = e.darNombre( );
            calculosEquipo[ 1 ] = "" + campeonato.darTotalPuntos( i );
            calculosEquipo[ 2 ] = "" + campeonato.darPartidosJugados( i );
            calculosEquipo[ 3 ] = "" + campeonato.darPartidosGanados( i );
            calculosEquipo[ 4 ] = "" + campeonato.darPartidosEmpatados( i );
            calculosEquipo[ 5 ] = "" + campeonato.darPartidosPerdidos( i );
            calculosEquipo[ 6 ] = "" + campeonato.darGolesAFavor( i );
            calculosEquipo[ 7 ] = "" + campeonato.darGolesEnContra( i );
            tablaPosiciones.add( calculosEquipo );
        }
        panelTablaPosiciones.refrescar( tablaPosiciones );
    }

    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Ejecuta el punto de extensión 1
     */
    public void reqFuncOpcion1( )
    {
        if( campeonato == null )
        {
            JOptionPane.showMessageDialog( this, "Debe cargar un campeonato primero", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            String resultado = campeonato.metodo1( );
            JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    /**
     * Ejecuta el punto de extensión 2
     */
    public void reqFuncOpcion2( )
    {
        if( campeonato == null )
        {
            JOptionPane.showMessageDialog( this, "Debe cargar un campeonato primero", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            String resultado = campeonato.metodo2( );
            JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    //-----------------------------------------------------------------
    // Programa principal
    //-----------------------------------------------------------------

    /**
     * Ejecuta la aplicación
     * @param args Estos parámetros no se usan.
     */
    public static void main( String[] args )
    {
        InterfazCampeonato ic = new InterfazCampeonato( );
        ic.setVisible( true );
    }
}