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
 * Panel de los botones
 */
public class PanelBotones extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * NEGATIVO
     */
    public final static String NEGATIVO = "NEGATIVO";

    /**
     * REFLEJAR
     */
    public final static String REFLEJAR = "REFLEJAR";

    /**
     * BINARIZAR
     */
    public final static String BINARIZAR = "BINARIZAR";

    /**
     * PIXELAR
     */
    public final static String PIXELAR = "PIXELAR";

    /**
     * ESCALA_GRISES
     */
    public final static String ESCALA_GRISES = "ESCALA_GRISES";

    /**
     * CONVOLUCION
     */
    public final static String CONVOLUCION = "CONVOLUCION";

    /**
     * Extensión 1
     */
    public final static String EXTENSION_1 = "EXTENSION_1";

    /**
     * Extensión 2
     */
    public final static String EXTENSION_2 = "EXTENSION_2";

    //-----------------------------------------------------------------
    // Elementos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Botón negativo
     */
    private JButton butNegativo;

    /**
     * Botón reflejar
     */
    private JButton butReflejar;

    /**
     * Botón binarizar
     */
    private JButton butBinarizar;

    /**
     * Botón pixelar
     */
    private JButton butPixelar;

    /**
     * Botón escala de grises
     */
    private JButton butEscalaGrises;

    /**
     * Botón operador de convolución
     */
    private JButton butConvolucion;

    /**
     * Botón extensión 1
     */
    private JButton butExtension1;

    /**
     * Botón extensión 2
     */
    private JButton butExtension2;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Una referencia a la clase principal de la interfaz
     */
    private InterfazVisorImagen ventana;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye el panel de los botones
     * @param laVentana Una referencia a la clase principal de la interfaz. laVentana != null.
     */
    public PanelBotones( InterfazVisorImagen laVentana )
    {
        //Guarda la referencia al padre
        ventana = laVentana;

        //Establece el distribuidor gráfico
        setLayout( new GridLayout( 6, 1 ) );

        //Crea e inicializa los elementos de la interfaz
        butNegativo = new JButton( "Negativo" );
        butNegativo.setActionCommand( NEGATIVO );
        butNegativo.addActionListener( this );

        butReflejar = new JButton( "Reflejar" );
        butReflejar.setActionCommand( REFLEJAR );
        butReflejar.addActionListener( this );

        butBinarizar = new JButton( "Binarización" );
        butBinarizar.setActionCommand( BINARIZAR );
        butBinarizar.addActionListener( this );

        butPixelar = new JButton( "Píxeles" );
        butPixelar.setActionCommand( PIXELAR );
        butPixelar.addActionListener( this );

        butEscalaGrises = new JButton( "Escala de Grises" );
        butEscalaGrises.setActionCommand( ESCALA_GRISES );
        butEscalaGrises.addActionListener( this );

        butConvolucion = new JButton( "Convolución" );
        butConvolucion.setActionCommand( CONVOLUCION );
        butConvolucion.addActionListener( this );

        butExtension1 = new JButton( "Extensión 1" );
        butExtension1.setActionCommand( EXTENSION_1 );
        butExtension1.addActionListener( this );

        butExtension2 = new JButton( "Extensión 2" );
        butExtension2.setActionCommand( EXTENSION_2 );
        butExtension2.addActionListener( this );

        //Adiciona los elementos al panel
        add( butNegativo );
        add( butReflejar );
        add( butBinarizar );
        add( butPixelar );
        add( butEscalaGrises );
        add( butConvolucion );
        add( butExtension1 );
        add( butExtension2 );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Ejecuta las acciones de los elementos de la interfaz
     * @param evento Evento de la acción. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( NEGATIVO ) )
        {
            ventana.convertirNegativo( );
        }
        else if( comando.equals( REFLEJAR ) )
        {
            ventana.reflejarImagen( );
        }
        else if( comando.equals( BINARIZAR ) )
        {
            ventana.presentarDialogoUmbral( );
        }
        else if( comando.equals( PIXELAR ) )
        {
            ventana.pixelarImagen( );
        }
        else if( comando.equals( ESCALA_GRISES ) )
        {
            ventana.convertirAGrises( );
        }
        else if( comando.equals( CONVOLUCION ) )
        {
            ventana.presentarDialogoMatrizConvolucion( );
        }
        else if( comando.equals( EXTENSION_1 ) )
        {
            ventana.reqFuncOpcion1( );
        }
        else if( comando.equals( EXTENSION_2 ) )
        {
            ventana.reqFuncOpcion2( );
        }
    }
}