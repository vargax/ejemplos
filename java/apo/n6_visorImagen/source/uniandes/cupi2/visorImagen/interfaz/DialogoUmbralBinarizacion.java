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
 * Diálogo para pedir el umbral para la binarización
 */
public class DialogoUmbralBinarizacion extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    /**
     * Panel del umbral
     */
    private PanelUmbral panelUmbral;

    /**
     * Panel de los botones
     */
    private PanelBotonesUmbral panelBotones;

    /**
     * Interfaz padre
     */
    private InterfazVisorImagen ventana;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el diálogo para el umbral de la binarización
     * @param laVentana Ventana del diálogo
     */
    public DialogoUmbralBinarizacion( InterfazVisorImagen laVentana )
    {
        //Establece el distribuidor gráfico
        setLayout( new BorderLayout( ) );

        //Establece el tamaño de la ventana
        setPreferredSize( new Dimension( 190, 80 ) );

        //Asigna la referencia a la ventana del programa
        ventana = laVentana;

        //Crea, inicializa y adiciona el panel
        panelUmbral = new PanelUmbral( );

        //Sugiere como umbral el color promedio de toda la imagen
        Color promedio = ventana.colorPromedio( );
        double umbral = ( promedio.getBlue( ) + promedio.getGreen( ) + promedio.getRed( ) ) / 3;
        panelUmbral.asignarUmbral( umbral );
        add( panelUmbral, BorderLayout.CENTER );

        //Crea y adiciona el panel de botones
        panelBotones = new PanelBotonesUmbral( this );
        add( panelBotones, BorderLayout.SOUTH );

        setTitle( "Umbral de binarización" );
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
        double umbral = panelUmbral.darUmbral( );
        if( umbral != -1 )
            ventana.binarizarImagen( umbral );
        setVisible( false );
    }

    /**
     * Procesa el cancelar del panel de botones
     */
    public void cancelar( )
    {
        setVisible( false );
    }
}