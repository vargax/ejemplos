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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * En este panel se muestran los combos para seleccionar los equipos
 */
public class PanelComboEquipos extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * El combo donde se selecciona el equipo 1
     */
    private JComboBox comboEquipo1;

    /**
     * El combo donde se selecciona el equipo 2
     */
    private JComboBox comboEquipo2;

    /**
     * Etiqueta VS
     */
    private JLabel etiquetaVS;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye el panel en el que se muestran los combos para seleccionar los equipos
     * @param nombreEquipos Es un arreglo con los nombres de los equipos del campeonato
     */
    public PanelComboEquipos( String[] nombreEquipos )
    {
        comboEquipo1 = new JComboBox( new DefaultComboBoxModel( nombreEquipos ) );
        comboEquipo2 = new JComboBox( new DefaultComboBoxModel( nombreEquipos ) );
        etiquetaVS = new JLabel( " vs " );
        add( comboEquipo1 );
        add( etiquetaVS );
        add( comboEquipo2 );
        setBorder( new TitledBorder( "Equipos" ) );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el número del equipo 1
     * @return equipo1
     */
    public int darEquipo1( )
    {
        return comboEquipo1.getSelectedIndex( );
    }

    /**
     * Retorna el número del equipo 2
     * @return equipo2
     */
    public int darEquipo2( )
    {
        return comboEquipo2.getSelectedIndex( );
    }

    /**
     * Inicializa los campos del panel
     */
    public void limpiar( )
    {
        comboEquipo1.setSelectedIndex( 0 );
        comboEquipo2.setSelectedIndex( 0 );
    }
}