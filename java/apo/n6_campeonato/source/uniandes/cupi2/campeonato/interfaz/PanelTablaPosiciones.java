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
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Es el panel donde se muestra la tabla de posiciones
 */
public class PanelTablaPosiciones extends JScrollPane
{
    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Es el área donde se escribe la tabla de posiciones
     */
    private JTextArea txtTabla;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     */
    public PanelTablaPosiciones( )
    {
        txtTabla = new JTextArea( 10, 60 );
        txtTabla.setEditable( false );
        txtTabla.setFont( new Font( "Courier New", Font.PLAIN, 12 ) );
        txtTabla.setBackground( getBackground( ) );
        getViewport( ).add( txtTabla );
        setBorder( new TitledBorder( "Tabla de Posiciones" ) );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Actualiza la tabla de posiciones mostrada
     * @param tabla Es un vector con arreglos de cadenas que tienen la información de la tabla de posiciones organizadas así: <br>
     *        <i>nombre_equipo puntos partidos_jugados partidos_ganados partidos_empatados partidos_perdidos goles_a_Favor goles_en_contra </i>
     */
    public void refrescar( ArrayList tabla )
    {
        txtTabla.setText( "" );
        String mensaje = "   Nombre del Equipo  Pts PJ  PG  PE  PP  GF  GC \n\n";
        for( int i = 0; i < tabla.size( ); i++ )
        {
            String[] datos = ( String[] )tabla.get( i );
            mensaje += cambiarTamanio( datos[ 0 ], 20 ) + cambiarTamanio( datos[ 1 ], 3 ) + cambiarTamanio( datos[ 2 ], 3 ) + cambiarTamanio( datos[ 3 ], 3 ) + cambiarTamanio( datos[ 4 ], 3 ) + cambiarTamanio( datos[ 5 ], 3 )
                    + cambiarTamanio( datos[ 6 ], 3 ) + cambiarTamanio( datos[ 7 ], 3 ) + "\n";
        }
        txtTabla.setText( mensaje );
    }

    /**
     * Este método sirve para cambiar el tamaño de una cadena. <br>
     * Si la cadena es muy corta, el espacio restante se completa con espacios. <br>
     * Si es muy larga se trunca hasta el tamaño deseado.
     * @param cadena Es la cadena que va a cambiar de tamaño. cadena != null.
     * @param tamano Es el tamaño que tiene que tener la cadena al finalizar.
     * @return Cadena modificada.
     */
    private String cambiarTamanio( String cadena, int tamano )
    {
        if( cadena.length( ) >= tamano )
            return cadena.substring( 0, tamano );
        else
        {
            String prefijo = "";
            for( int numFaltantes = tamano - cadena.length( ); numFaltantes > 0; numFaltantes-- )
            {
                prefijo += " ";
            }
            return prefijo + cadena + " ";
        }
    }
}