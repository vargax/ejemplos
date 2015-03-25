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

import javax.swing.*;
import javax.swing.border.*;

/**
 * En este panel el usuario escribe los goles del partido que está registrando
 */
public class PanelResultadoPartido extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Etiqueta "-"
     */
    private JLabel etiquetaGuion;

    /**
     * El campo de texto para los goles del equipo 1
     */
    private JTextField txtGoles1;

    /**
     * El campo de texto para los goles del equipo 2
     */
    private JTextField txtGoles2;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye el panel
     */
    public PanelResultadoPartido( )
    {
        etiquetaGuion = new JLabel( " - " );
        txtGoles1 = new JTextField( 2 );
        txtGoles2 = new JTextField( 2 );
        add( txtGoles1 );
        add( etiquetaGuion );
        add( txtGoles2 );
        setBorder( new TitledBorder( "Resultado" ) );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el número de goles del equipo 1
     * @return goles1
     */
    public String darGolesEquipo1( )
    {
        return txtGoles1.getText( );
    }

    /**
     * Retorna el número de goles del equipo 2
     * @return goles2
     */
    public String darGolesEquipo2( )
    {
        return txtGoles2.getText( );
    }

    /**
     * Inicializa el contenido de los dos campos de texto
     */
    public void limpiar( )
    {
        txtGoles1.setText( "" );
        txtGoles2.setText( "" );
    }
}