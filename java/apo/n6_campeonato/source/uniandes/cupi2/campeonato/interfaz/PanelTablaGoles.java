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

import javax.swing.*;
import javax.swing.border.*;

import uniandes.cupi2.campeonato.mundo.*;

/**
 * Este es el panel donde se muestra la tabla de goles del campeonato
 */
public class PanelTablaGoles extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Una referencia al objeto del modelo del mundo que representa el campeonato
     */
    private Campeonato campeonato;

    /**
     * Número de equipos que juega en el campeonato
     */
    private int numeroEquipos;

    /**
     * Objeto para manejar el tipo de los caracteres en la visualización
     */
    private Font fuenteNegrita;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Las etiquetas donde se escriben los resultados
     */
    private JLabel[][] tablaEtiquetasGoles;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye el panel sin ningún campeonato
     */
    public PanelTablaGoles( )
    {
        campeonato = null;
        numeroEquipos = 0;
        fuenteNegrita = new Font( "Arial", Font.BOLD, 11 );
        setBorder( new TitledBorder( "Tabla de Goles" ) );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Inicializa la visualización de la tabla de goles del campeonato.
     * @param pCampeonato Objeto del modelo del mundo que representa el campeonato. pCampeonato != null.
     */
    public void iniciarTablaGoles( Campeonato pCampeonato )
    {
        campeonato = pCampeonato;
        numeroEquipos = campeonato.darNumeroEquipos( );
        setLayout( new GridLayout( numeroEquipos + 1, numeroEquipos + 1 ) );
        tablaEtiquetasGoles = new JLabel[numeroEquipos + 1][numeroEquipos + 1];

        // Crear las etiquetas de los marcadores
        for( int i = 0; i < numeroEquipos + 1; i++ )
        {
            for( int j = 0; j < numeroEquipos + 1; j++ )
            {
                if( i == 0 && j == 0 )
                    tablaEtiquetasGoles[ 0 ][ 0 ] = new JLabel( "" );
                else if( i == 0 )
                {
                    tablaEtiquetasGoles[ 0 ][ j ] = new JLabel( campeonato.darEquipo( j - 1 ).darNombre( ) + " " );
                }
                else if( j == 0 )
                {
                    tablaEtiquetasGoles[ i ][ 0 ] = new JLabel( campeonato.darEquipo( i - 1 ).darNombre( ) + " " );
                }
                else if( i == j )
                {
                    tablaEtiquetasGoles[ i ][ i ] = new JLabel( "X" );
                }
                else
                {
                    tablaEtiquetasGoles[ i ][ j ] = new JLabel( "-" );

                }
                tablaEtiquetasGoles[ i ][ j ].setFont( fuenteNegrita );
                tablaEtiquetasGoles[ i ][ j ].setHorizontalAlignment( JLabel.CENTER );
                add( tablaEtiquetasGoles[ i ][ j ] );
            }
        }
        validate( );
    }

    /**
     * Actualiza la información mostrada en la tabla de goles.
     */
    public void refrescar( )
    {
        if( campeonato == null )
            return;
        // Desplegar los resultados de los partidos
        for( int i = 0; i < numeroEquipos; i++ )
        {
            for( int j = 0; j < numeroEquipos; j++ )
            {
                int resultado = campeonato.darGolesMarcados( i, j );
                if( i != j && resultado != Campeonato.SIN_JUGAR )
                {
                    tablaEtiquetasGoles[ i + 1 ][ j + 1 ].setText( "" + resultado );
                }
            }
        }
    }
}