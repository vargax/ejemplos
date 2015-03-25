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

import javax.swing.*;

/**
 * Diálogo para pedir la matriz de convolución
 */
public class DialogoMatrizConvolucion extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    /**
     * Panel de la matriz
     */
    private PanelMatriz panelMatriz;

    /**
     * Panel de los botones
     */
    private PanelBotonesMatriz panelBotones;

    /**
     * Interfaz padre
     */
    private InterfazVisorImagen ventana;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el diálogo para los valores de la matriz de convolución
     * @param laVentana Ventana de la interfaz de la cual hace parte este diálogo. laVentana != null.
     */
    public DialogoMatrizConvolucion( InterfazVisorImagen laVentana )
    {
        //Establece el distribuidor gráfico
        setLayout( new BorderLayout( ) );

        //Almacena la referencia a la ventana a la cual pertenece el diálogo
        ventana = laVentana;

        //Crea y adiciona el panel de la imagen
        panelMatriz = new PanelMatriz( );
        add( panelMatriz, BorderLayout.CENTER );

        //Crea y adiciona el panel de botones
        panelBotones = new PanelBotonesMatriz( this );
        add( panelBotones, BorderLayout.SOUTH );

        setTitle( "Matriz de Convolución" );
        pack( );
        setResizable( false );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Procesa el aceptar del panel de botones
     */
    public void aceptar( )
    {
        double conv[][] = panelMatriz.darMatriz( );
        if( conv != null )
            ventana.aplicarOperadorConvolucion( conv );
        setVisible( false );
    }

    /**
     * Procesa el limpiar del panel de botones
     */
    public void limpiar( )
    {
        panelMatriz.limpiar( );
    }

    /**
     * Procesa el cancelar del panel de botones
     */
    public void cancelar( )
    {
        setVisible( false );
    }
}