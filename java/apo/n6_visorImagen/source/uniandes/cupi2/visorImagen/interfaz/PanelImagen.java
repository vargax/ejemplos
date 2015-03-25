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
import java.awt.image.*;
import java.io.*;

import javax.swing.*;

import uniandes.cupi2.visorImagen.mundo.*;

/**
 * Panel para dibujar la imagen
 */
public class PanelImagen extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Imagen que se presenta en el panel
     */
    private Imagen imagen;

    /**
     * Imagen en un buffer
     */
    private BufferedImage imagenPintar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel donde se proyectará la imagen. Si no encuentra la imagen se presenta el panel vacío
     */
    public PanelImagen( )
    {
        try
        {
            imagen = new Imagen( "data/imagen.bmp" );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Cargando imagen ...", JOptionPane.ERROR_MESSAGE );
            return;
        }
        imagenPintar = imagen.darImagenBuffer( );
        setPreferredSize( new Dimension( imagenPintar.getWidth( ), imagenPintar.getHeight( ) ) );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el color promedio de la imagen
     * @return color promedio de la imagen
     */
    public Color colorPromedio( )
    {
        if( imagen != null )
        {
            return imagen.colorPromedio( );
        }
        return null;
    }

    /**
     * Procesa la imagen con el método que permite convertirla en su negativo
     */
    public void convertirNegativo( )
    {
        if( imagen != null )
        {
            imagen.convertirNegativo( );
            repaint( );
        }
    }

    /**
     * Procesa la imagen con el método que permite calcular su reflejo
     */
    public void reflejarImagen( )
    {
        if( imagen != null )
        {
            imagen.reflejarImagen( );
            repaint( );
        }
    }

    /**
     * Procesa la imagen con el método que permite hacer una binarización
     * @param umbral Umbral de modificación
     */
    public void binarizarImagen( double umbral )
    {
        if( imagen != null )
        {
            imagen.binarizarImagen( umbral );
            repaint( );
        }
    }

    /**
     * Procesa la imagen con el método que permite hacer un pixelamiento
     */
    public void pixelarImagen( )
    {
        if( imagen != null )
        {
            imagen.pixelarImagen( );
            repaint( );
        }
    }

    /**
     * Procesa la imagen con el método que permite convertirla a tonos de gris
     */
    public void convertirAGrises( )
    {
        if( imagen != null )
        {
            imagen.convertirAGrises( );
            repaint( );
        }
    }

    /**
     * Procesa la imagen con el método que permite aplicar un operador de convolución, expresado como una matriz de valores
     * @param conv Matriz de convolución. conv != null.
     */
    public void aplicarOperadorConvolución( double conv[][] )
    {
        if( imagen != null )
        {
            //Aplica la matriz de convolución
            imagen.aplicarOperadorConvolucion( conv, InterfazVisorImagen.DIMENSION_CONVOLUCION );
            repaint( );
        }
    }

    /**
     * Pinta la imagen
     * @param g Gráficas del panel
     */
    public void paint( Graphics g )
    {
        super.paint( g );
        if( imagen != null )
        {
            imagenPintar = imagen.darImagenBuffer( );
            g.drawImage( imagenPintar, 0, 0, null, null );
        }
    }

    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Procesa la imagen con el método de extensión 1
     */
    public void extension1( )
    {
        if( imagen != null )
        {
            String respuesta = imagen.metodo1( );
            JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
            repaint( );
        }
    }

    /**
     * Procesa la imagen con el método de extensión 2
     */
    public void extension2( )
    {
        if( imagen != null )
        {
            String respuesta = imagen.metodo2( );
            JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
            repaint( );
        }
    }

}