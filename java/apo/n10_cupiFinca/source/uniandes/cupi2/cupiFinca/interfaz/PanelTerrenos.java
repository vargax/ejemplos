/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodríguez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.interfaz;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Panel de manejo de extensiones
 */
public class PanelTerrenos extends JPanel implements MouseListener
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupiFinca principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel donde se dibujan los terrenos
     * @param ventana Ventana principal de la CupiFinca
     */
    public PanelTerrenos( InterfazCupiFinca ventana )
    {
        principal = ventana;

        setPreferredSize( new Dimension( 800, 500 ) );
        addMouseListener( this );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Dibuja los elementos del panel
     * @param g Es la superficie del panel
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        Graphics2D g2 = ( Graphics2D )g;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2.clearRect( 0, 0, getPreferredSize( ).width, getPreferredSize( ).height );
        
        try
        {
            BufferedImage imagen = ImageIO.read( new File( "./data/imagenes/fondo.png" ) );
            g2.drawRenderedImage( imagen, new AffineTransform( ) );
        }
        catch( IOException e )
        {
                //No hace nada
        }
        
        principal.actualizarDibujos( g2 );
    }

    /**
     * Refresca la vista del panel
     */
    public void refrescar( )
    {
        repaint( );
    }

    // -----------------------------------------------------------------
    // Métodos de eventos del mouse
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos del click del mouse
     * @param e Acción que generó el evento. e != null
     */
    public void mouseClicked( MouseEvent e )
    {
        int posicionX = e.getX( ) / InterfazCupiFinca.DIMENSION;
        int posicionY = e.getY( ) / InterfazCupiFinca.DIMENSION;

        if( principal.darEstado( ).equals( InterfazCupiFinca.ESTADO_PRODUCCION ) )
        {
            principal.mostrarDialogoProducirTerreno( posicionX, posicionY );
        }
        if( principal.darEstado( ).equals( InterfazCupiFinca.ESTADO_RECOLECCION ) )
        {
            principal.recolectarTerreno( posicionX, posicionY );
        }
        if( principal.darEstado( ).equals( InterfazCupiFinca.ESTADO_ELIMINACION ) )
        {
            principal.eliminarTerreno( posicionX, posicionY );
        }
    }

    /**
     * Manejo de los eventos cuando el mouse entra al panel
     * @param e Acción que generó el evento. e != null
     */
    public void mouseEntered( MouseEvent e )
    {
        String rutaImagen = null;
        
        if( principal.darEstado( ).equals( InterfazCupiFinca.ESTADO_PRODUCCION ) )
        {
            rutaImagen = InterfazCupiFinca.RUTA_MARTILLO;
        }
        if( principal.darEstado( ).equals( InterfazCupiFinca.ESTADO_RECOLECCION ) )
        {
            rutaImagen = InterfazCupiFinca.RUTA_PALA;
        }
        if( principal.darEstado( ).equals( InterfazCupiFinca.ESTADO_ELIMINACION ) )
        {
            rutaImagen = InterfazCupiFinca.RUTA_CANECA;
        }

        if( rutaImagen != null )
        {
            Toolkit toolkit = Toolkit.getDefaultToolkit( );
            Image image = new ImageIcon( rutaImagen ).getImage( );
            Cursor c = toolkit.createCustomCursor( image, new Point( 0, 0 ), "cursor" );
            setCursor( c );
        }else{
            setCursor( null );
        }
    }

    /**
     * Manejo de los eventos cuando el mouse sale al panel
     * @param e Acción que generó el evento. e != null
     */
    public void mouseExited( MouseEvent e )
    {

    }

    /**
     * Manejo de los eventos cuando el mouse es presionado
     * @param e Acción que generó el evento. e != null
     */
    public void mousePressed( MouseEvent e )
    {

    }

    /**
     * Manejo de los eventos cuando el mouse se suelta
     * @param e Acción que generó el evento. e != null
     */
    public void mouseReleased( MouseEvent e )
    {

    }
}
