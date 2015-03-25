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

package uniandes.cupi2.visorImagen.test;

import java.awt.*;
import java.io.*;

import junit.framework.*;
import uniandes.cupi2.visorImagen.mundo.*;

/**
 * Clase de prueba para el visor de imágenes
 */
public class ImagenTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Imagen de prueba
     */
    private Imagen imagen;

    /**
     * alto de la imagen de prueba
     */
    private int alto;

    /**
     * ancho de la imagen de prueba
     */
    private int ancho;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Prepara un escenario con una imagen blanca de 10 x 20
     */
    private void setupEscenario1( )
    {
        try
        {
            //Crea y prepara al calculador de impuestos
            imagen = new Imagen( "test/data/imagen1.bmp" );
            ancho = 10;
            alto = 20;
        }
        catch( IOException e )
        {
            fail( "no pudo crear la imagen" );
        }
    }

    /**
     * Prepara un escenario con una imagen negra del ancho y el alto limite La imagen debe tener tales dimensiones.
     */
    private void setupEscenario2( )
    {
        try
        {
            //Crea y prepara al calculador de impuestos
            imagen = new Imagen( "test/data/imagen2.bmp" );
            ancho = Imagen.ANCHO_MAXIMO;
            alto = Imagen.ALTO_MAXIMO;
        }
        catch( IOException e )
        {
            fail( "no pudo crear la imagen" );
        }
    }

    /**
     * Prepara un escenario con una imagen azul del ancho y el alto mayores a los limites. La imagen es de 1000 x 900
     */
    private void setupEscenario3( )
    {
        try
        {
            //Crea y prepara al calculador de impuestos
            imagen = new Imagen( "test/data/imagen3.bmp" );
            ancho = 1000;
            alto = 900;
        }
        catch( IOException e )
        {
            fail( "no pudo crear la imagen" );
        }
    }

    /**
     * Prueba la carga de una imagen menor al máximo
     */
    public void testCargaImagenPequenia( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //El ancho y el alto de la imagen deben ser el esperado
        assertEquals( ancho, imagen.darAncho( ) );
        assertEquals( alto, imagen.darAlto( ) );
        //los límites de la imagen deben ser menores a los máximos
        assertTrue( imagen.darAlto( ) < Imagen.ALTO_MAXIMO );
        assertTrue( imagen.darAncho( ) < Imagen.ANCHO_MAXIMO );
    }

    /**
     * Prueba la carga de una imagen con los limites exactos
     */
    public void testCargaImagenExacta( )
    {
        //Configura el escenario de prueba
        setupEscenario2( );

        //El ancho y el alto de la imagen deben ser el esperado
        assertEquals( ancho, imagen.darAncho( ) );
        assertEquals( alto, imagen.darAlto( ) );
    }

    /**
     * Prueba la carga de una imagen mayor al máximo
     */
    public void testCargaImagenGrande( )
    {
        //Configura el escenario de prueba
        setupEscenario3( );

        //El ancho y alto de la imagen original son mayores a los máximos
        assertTrue( ancho > Imagen.ANCHO_MAXIMO );
        assertTrue( alto > Imagen.ALTO_MAXIMO );

        //El ancho y el alto de la imagen cargada deben ser los máximos
        //porque se trunca
        assertEquals( Imagen.ANCHO_MAXIMO, imagen.darAncho( ) );
        assertEquals( Imagen.ALTO_MAXIMO, imagen.darAlto( ) );
    }

    /**
     * Prueba que la imagen cargada corresponda a la imagen totalmente blanca
     */
    public void testCargaImagenBlanca( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //Todos los bits de la imagen deben ser blancos
        for( int i = 0; i < imagen.darAncho( ); i++ )
        {
            for( int j = 0; j < imagen.darAlto( ); j++ )
            {
                assertEquals( Color.white.getRGB( ), imagen.darColorPixel( i, j ).getRGB( ) );
            }
        }
    }

    /**
     * Prueba del cálculo del color promedio
     */
    public void testColorPromedioImagenBlanca( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );
        //Valida que el color promedio sea blanco
        assertEquals( Color.white.getRGB( ), imagen.colorPromedio( ).getRGB( ) );
    }

    /**
     * Prueba que la imagen cargada corresponda a la imagen totalmente negra
     */
    public void testCargaImagenNegra( )
    {
        //Configura el escenario de prueba
        setupEscenario2( );

        //Todos los bits de la imagen deben ser blancos
        for( int i = 0; i < imagen.darAncho( ); i++ )
        {
            for( int j = 0; j < imagen.darAlto( ); j++ )
            {
                assertEquals( Color.black.getRGB( ), imagen.darColorPixel( i, j ).getRGB( ) );
            }
        }
    }
}