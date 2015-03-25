/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_visorImagen 
 * Autor: Katalina Marcos
 * Modificación: Mario Sánchez - 28/06/2005
 * Modificación: Pablo Barvo - 1-Sep-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.visorImagen.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Panel de los botones del umbral de binarización
 */
public class PanelBotonesUmbral extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Aceptar
     */
    public final static String ACEPTAR = "aceptar";

    /**
     * Cancelar
     */
    public final static String CANCELAR = "cancelar";

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Botón Aceptar
     */
    private JButton botonAceptar;

    /**
     * Botón Cancelar
     */
    private JButton botonCancelar;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Diálogo al que pertenece el panel
     */
    private DialogoUmbralBinarizacion dialogo;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel de botones del umbral de binarización
     * @param elDialogo Diálogo al que pertenece este panel. elDialogo != null.
     */
    public PanelBotonesUmbral( DialogoUmbralBinarizacion elDialogo )
    {
        //Guarda la referencia al diálogo
        dialogo = elDialogo;

        //Establece el distribuidor gráfico
        setLayout( new GridLayout( 1, 2 ) );

        //Crea e inicializa los elementos de la interfaz
        botonAceptar = new JButton( "Aceptar" );
        botonAceptar.setActionCommand( ACEPTAR );
        botonAceptar.addActionListener( this );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );

        //Adiciona los elementos al panel
        add( botonAceptar );
        add( botonCancelar );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Ejecuta las acciones de los elementos de la interfaz
     * @param evento Evento que generó la acción. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( ACEPTAR ) )
        {
            dialogo.aceptar( );
        }
        if( comando.equals( CANCELAR ) )
        {
            dialogo.cancelar( );
        }
    }
}