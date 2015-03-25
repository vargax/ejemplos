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

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

/**
 * Interfaz del visor de imágenes
 */
public class InterfazVisorImagen extends JFrame
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Dimensión para la convolución
     */
    public static final int DIMENSION_CONVOLUCION = 3;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Panel de la imagen
     */
    private PanelImagen panelImagen;

    /**
     * Panel de los botones
     */
    private PanelBotones panelBotones;

    /**
     * Diálogo para pedir la matriz de convolución
     */
    private DialogoMatrizConvolucion dialogoMatriz;

    /**
     * Diálogo para pedir el umbral de binarización
     */
    private DialogoUmbralBinarizacion dialogoUmbral;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea la interfaz para el visor de imágenes
     */
    public InterfazVisorImagen( )
    {
        //Establece el distribuidor gráfico
        setLayout( new BorderLayout( ) );

        //Crea y adiciona el panel de la imagen
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.CENTER );

        //Crea y adiciona el panel de botones
        panelBotones = new PanelBotones( this );
        add( panelBotones, BorderLayout.EAST );

        //Crea el diálogo de la matriz de convolución
        dialogoMatriz = new DialogoMatrizConvolucion( this );

        //Crea el diálogo del umbral de binarización
        dialogoUmbral = new DialogoUmbralBinarizacion( this );

        setTitle( "Visor de Imágenes" );
        pack( );
        setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Da el color promedio de la imagen
     * @return color promedio
     */
    public Color colorPromedio( )
    {
        return panelImagen.colorPromedio( );
    }

    /**
     * Opción 1: convertir la imagen en su negativo
     */
    public void convertirNegativo( )
    {
        panelImagen.convertirNegativo( );
    }

    /**
     * Opción 2: reflejar la imagen
     */
    public void reflejarImagen( )
    {
        panelImagen.reflejarImagen( );
    }

    /**
     * Presenta el diálogo de definición del umbral de binarización
     */
    public void presentarDialogoUmbral( )
    {
        dialogoUmbral.setVisible( true );
    }

    /**
     * Opción 3: binarizar la imagen
     * @param umbral de binarización
     */
    public void binarizarImagen( double umbral )
    {
        panelImagen.binarizarImagen( umbral );
    }

    /**
     * Opción 4: pixelar la imagen
     */
    public void pixelarImagen( )
    {
        panelImagen.pixelarImagen( );
    }

    /**
     * Opción 5: convertir a tonos de gris la imagen
     */
    public void convertirAGrises( )
    {
        panelImagen.convertirAGrises( );
    }

    /**
     * Presenta el diálogo de definición de la matriz de convolución
     */
    public void presentarDialogoMatrizConvolucion( )
    {
        dialogoMatriz.setVisible( true );
    }

    /**
     * Opción 6: aplicar el operador de convolución representado en la matriz
     * @param conv Matriz de convolución
     */
    public void aplicarOperadorConvolucion( double conv[][] )
    {
        panelImagen.aplicarOperadorConvolución( conv );
    }

    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Extensión 1
     */
    public void reqFuncOpcion1( )
    {
        panelImagen.extension1( );
    }

    /**
     * Extensión 2
     */
    public void reqFuncOpcion2( )
    {
        panelImagen.extension2( );
    }

    //-----------------------------------------------------------------
    // Programa principal
    //-----------------------------------------------------------------
    /**
     * Método para la ejecución del programa.
     * @param args No hay argumentos para la ejecución.
     */
    public static void main( String[] args )
    {
        InterfazVisorImagen i = new InterfazVisorImagen( );
        i.setVisible( true );
    }
}