/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 
 * Ejercicio: n6_buscaminas 
 * Autor: Mario Sánchez - 15/07/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.buscaminas.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Este panel se usa para mostrar la barra de estado del juego
 */
public class PanelEstado extends JPanel
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Es la etiqueta donde se muestra el número de minas restantes
     */
    private JLabel etiquetaMinas;

    /**
     * Es la etiqueta donde se muestra el modo actual de juego
     */
    private JLabel etiquetaModo;

    /**
     * Es la etiqueta donde se muestra el tiempo empleado hasta la última jugada
     */
    private JLabel etiquetaTiempo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye e inicializa el panel que sirve para mostrar la barra de estado
     */
    public PanelEstado( )
    {
        setLayout( new BorderLayout( ) );

        Font fuente = new Font( "Tahoma", Font.PLAIN, 10 );

        etiquetaModo = new JLabel( "Modo: --------" );
        etiquetaModo.setFont( fuente );
        etiquetaModo.setBorder( new CompoundBorder( new EmptyBorder( 2, 2, 2, 2 ), new LineBorder( Color.GRAY ) ) );
        add( etiquetaModo, BorderLayout.WEST );

        etiquetaMinas = new JLabel( "Quedan: -- minas" );
        etiquetaMinas.setFont( fuente );
        etiquetaMinas.setBorder( new CompoundBorder( new EmptyBorder( 2, 2, 2, 2 ), new LineBorder( Color.GRAY ) ) );
        add( etiquetaMinas, BorderLayout.CENTER );

        etiquetaTiempo = new JLabel( "Van: 0''" );
        etiquetaTiempo.setFont( fuente );
        etiquetaTiempo.setBorder( new CompoundBorder( new EmptyBorder( 2, 2, 2, 2 ), new LineBorder( Color.GRAY ) ) );
        add( etiquetaTiempo, BorderLayout.EAST );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el número de minas restantes
     * @param numeroMinas El número de minas restantes que deben mostrarse
     */
    public void actualizarMinas( int numeroMinas )
    {
        etiquetaMinas.setText( "Quedan " + numeroMinas + " minas" );

    }

    /**
     * Actualiza el modo actual de juego
     * @param modo El nombre del modo actual. modo!=null
     */
    public void actualizarModo( String modo )
    {
        etiquetaModo.setText( "Modo: " + modo );
    }

    /**
     * Actualiza el tiempo transcurrido hasta la última jugada
     * @param tiempo El tiempo transcurrido desde el inicio del juego hasta la última jugada realizada
     */
    public void actualizarTiempo( int tiempo )
    {
        etiquetaTiempo.setText( "Van " + tiempo + "''" );
    }
}
