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
 * Es el panel donde se muestran los botones para registrar un partido
 */
public class PanelBotonesDialogo extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    private static final String OK = "ok";
    private static final String CANCELAR = "Cancelar";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es una referencia al diálogo que contiene este panel
     */
    private DialogoResultado dialogo;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Es el botón para registrar el resultado
     */
    private JButton botonOk;

    /**
     * Es el botón para cancelar el registro del resultado
     */
    private JButton botonCancelar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye el panel para lo botones de OK y Cancelar
     * @param dr Es el diálogo en el que se va a mostrar el panel. dr != null.
     */
    public PanelBotonesDialogo( DialogoResultado dr )
    {
        dialogo = dr;
        botonOk = new JButton( "Ok" );
        botonOk.setActionCommand( OK );
        botonOk.addActionListener( this );
        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        add( botonOk );
        add( botonCancelar );
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
        if( OK.equals( comando ) )
        {
            dialogo.registrarPartido( );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.setVisible( false );
        }
    }
}