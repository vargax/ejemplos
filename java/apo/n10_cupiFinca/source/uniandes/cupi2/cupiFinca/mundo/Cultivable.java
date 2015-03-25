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

package uniandes.cupi2.cupiFinca.mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import uniandes.cupi2.cupiFinca.mundo.excepciones.PersistenciaException;

/**
 * Clase abstracta que me representa un terreno cultivable
 */
public abstract class Cultivable extends Terreno
{
    
    // -----------------------------------------------------------------
    // Constantes de los estados
    // -----------------------------------------------------------------
    
    /**
     * El estado donde se prepara el terreno
     */
    public static final int PREPARAR = 0;

    /**
     * El estado donde el producto se instala en la casilla
     */
    public static final int SEMBRAR = 1;
    
    /**
     * El estado donde madura el producto
     */
    public static final int CRECER = 2;
    
    /**
     * El estado donde se recoge lo obtenido del producto 
     */
    public static final int COSECHAR = 3;
    
    /**
     * El estado donde el producto ya pierde utilidad
     */
    public static final int PUDRIR = 4;
    
    // -----------------------------------------------------------------
    // Constantes de los tiempos
    // -----------------------------------------------------------------

    /**
     * El tiempo de preparación del producto
     */
    public static final int TIEMPO_PREPARACION = 24;

    /**
     * El tiempo de instalación del producto
     */
    public static final int TIEMPO_SIEMBRA = 45;

    /**
     * El tiempo de maduración del producto
     */
    public static final int TIEMPO_CRECIMIENTO = 50;

    /**
     * El tiempo de recolección del producto
     */
    public static final int TIEMPO_COSECHA = 70;

    /**
     * El tiempo de putrefacción del producto
     */
    public static final int TIEMPO_PUTREFACCION = -1;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La imagen del producto
     */
    protected BufferedImage imagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo producto cultivable a la finca.<br>
     * <b>pre: </b>El precio del producto no supera el que tiene la finca.<br>
     * @param pNombre El nombre del producto. pNombre != null
     * @param pImagen La ruta del imagen del producto. pNombre != null
     * @param pX La coordenada x del terreno. pX >= 0
     * @param pY La coordenada y del terreno. pY >= 0
     * @param pAncho El ancho del campo del terreno. pAncho > 0
     * @param pCosto El costo de producir. pCosto >= 0
     * @param pGanancia La ganancia obtenida al producir. pGanancia >= 0
     * @param pTiempo El tiempo de inicio del cultivo. pTiempo >= 0
     * @throws PersistenciaException En caso de no leer la imagen del cultivo 
     */
    public Cultivable( String pNombre, String pImagen, int pX, int pY, int pAncho, int pCosto, int pGanancia, int pTiempo ) throws PersistenciaException
    {
        super( pNombre, pX, pY, pAncho, pCosto, pGanancia, true, pTiempo );

        try
        {
            imagen = ImageIO.read( new File( pImagen ) );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "No ha podido cargar la imagen: " + e.getMessage( ) );
        }
        
        tiempos = new int[5];
        estado = PREPARAR;

        tiempos[ PREPARAR ] = TIEMPO_PREPARACION;
        tiempos[ SEMBRAR ] = TIEMPO_SIEMBRA;
        tiempos[ CRECER ] = TIEMPO_CRECIMIENTO;
        tiempos[ COSECHAR ] = TIEMPO_COSECHA;
        tiempos[ PUDRIR ] = TIEMPO_PUTREFACCION;
    }

    // -----------------------------------------------------------------
    // Métodos abstractos
    // -----------------------------------------------------------------

    /**
     * Dibuja el producto en el pedazo del campo
     * @param graphics El campo de dibujo. graphics != null
     */
    public abstract void dibujar( Graphics2D graphics );

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el resultado obtenido por recoger el producto.
     * @return Los resultados de haber recogido el terreno. -1 en caso de no poder recoger todavía el producto.
     */
    public int darGanancia(){
        if(estado == PUDRIR){
            return 0;
        }else if( estado == COSECHAR ){
            return ganancia;
        }else{
            return -1;
        }
    }
    
    /**
     * Guarda la información del terreno en el archivo que está de parámetro
     * @param pw El archivo donde se guarda la información del terreno. pw != null
     */
    public void guardarTerreno( PrintWriter pw )
    {
        pw.println( darNombre( ) + ";" + x + ";" + y  + ";" + tiempo);
    }
    
    /**
     * Dibuja el estado de preparación del cultivo de la cebolla
     * @param graphics El lienzo de dibujo del terreno. graphics != null
     */
    protected void dibujarPreparacion( Graphics2D graphics )
    {
        Graphics2D g = ( Graphics2D )graphics;

        int cotaX = x * ancho;
        int cotaY = y * ancho;

        g.setColor( new Color( 160, 82, 45 ) );
        g.fillRect( cotaX, cotaY, ancho, ancho );

        g.setStroke( new BasicStroke( 4.0f ) );
        g.setColor( new Color( 142, 72, 14 ) );
        g.drawRect( cotaX, cotaY, ancho, ancho );

        int riegos = 4;
        double delta = ( ancho / riegos );

        cotaX += delta;
        cotaY += delta;
        for( int i = 0; i < riegos - 1; i++ )
        {
            g.setStroke( new BasicStroke( 15.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
            g.setColor( new Color( 142, 72, 14 ) );
            g.drawLine( ( int ) ( cotaX + ( i * delta ) ), ( int )cotaY, ( int ) ( cotaX + ( i * delta ) ), ( int ) ( cotaY + ( ancho - ( 2 * delta ) ) ) );
        }
    }

    /**
     * Dibuja el estado de instalación del cultivo de la cebolla
     * @param graphics El lienzo de dibujo del terreno. graphics != null
     */
    protected void dibujarInstalacion( Graphics2D graphics )
    {
        Graphics2D g = ( Graphics2D )graphics;

        int cotaX = x * ancho;
        int cotaY = y * ancho;

        g.setColor( new Color( 160, 82, 45 ) );
        g.fillRect( cotaX, cotaY, ancho, ancho );

        g.setStroke( new BasicStroke( 4.0f ) );
        g.setColor( new Color( 142, 72, 14 ) );
        g.drawRect( cotaX, cotaY, ancho, ancho );

        int riegos = 4;
        double delta = ( ancho / riegos );

        // Los rastros del color
        Color[] colores = new Color[]{ new Color( 142, 72, 14 ), new Color( 107, 49, 0 ), new Color( 62, 28, 0 ) };

        cotaX += delta;
        cotaY += delta;
        for( int i = 0; i < colores.length; i++ )
        {
            Color color = colores[ i ];

            // Dibuja el rastro grueso
            for( int j = 0; j < riegos - 1; j++ )
            {
                g.setStroke( new BasicStroke( 15.0f / ( i + 1 ), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
                g.setColor( color );

                double diferencia = ancho - ( 2 * delta );
                double radio = 2;

                int previoX = cotaX + ( int ) ( delta * j );
                int previoY = cotaY;
                for( int k = 0; k < diferencia; k++ )
                {
                    int pX = ( ( cotaX + ( int ) ( delta * j ) ) - ( int ) ( radio * Math.sin( k * ( Math.PI / 8 ) ) ) );
                    int pY = ( int ) ( cotaY + k );

                    g.drawLine( previoX, previoY, pX, pY );

                    previoX = pX;
                    previoY = pY;
                }
            }
        }
    }

    /**
     * Dibuja el estado de maduración del cultivo de la cebolla
     * @param graphics El lienzo de dibujo del terreno. graphics != null
     */
    protected void dibujarMaduracion( Graphics2D graphics )
    {
        int cotaX = x * ancho;
        int cotaY = y * ancho;

        graphics.setColor( new Color( 160, 82, 45 ) );
        graphics.fillRect( cotaX, cotaY, ancho, ancho );

        graphics.setStroke( new BasicStroke( 4.0f ) );
        graphics.setColor( new Color( 142, 72, 14 ) );
        graphics.drawRect( cotaX, cotaY, ancho, ancho );

        int riegos = 4;
        double delta = ( ancho / riegos );

        // Los rastros del color
        Color[] colores = new Color[]{ new Color( 142, 72, 14 ), new Color( 107, 49, 0 ), new Color( 62, 28, 0 ) };

        cotaX += delta;
        cotaY += delta;
        for( int i = 0; i < colores.length; i++ )
        {
            Color color = colores[ i ];

            // Dibuja el rastro grueso
            for( int j = 0; j < riegos - 1; j++ )
            {

                double diferencia = ancho - ( 2 * delta );
                double radio = 2;

                int previoX = cotaX + ( int ) ( delta * j );
                int previoY = cotaY;
                
                //Dibuja el labrado
                for( int k = 0; k < diferencia; k++ )
                {
                    int pX = ( ( cotaX + ( int ) ( delta * j ) ) - ( int ) ( radio * Math.sin( k * ( Math.PI / 8 ) ) ) );
                    int pY = ( int ) ( cotaY + k );

                    graphics.setStroke( new BasicStroke( 15.0f / ( i + 1 ), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
                    graphics.setColor( color );
                    graphics.drawLine( previoX, previoY, pX, pY );

                    previoX = pX;
                    previoY = pY;
                }
                
                //Dibuja las plantas
                for( int k = 0; k < diferencia; k++ )
                {
                    int pX = ( ( cotaX + ( int ) ( delta * j ) ) - ( int ) ( radio * Math.sin( k * ( Math.PI / 8 ) ) ) );
                    int pY = ( int ) ( cotaY + k );
                    if( k % 15 == 0 )
                    {
                        dibujarPlantasMaduracion( graphics, pX, pY );
                    }
                }
            }
        }
    }

    /**
     * Dibuja el estado de recolección del cultivo de la cebolla
     * @param graphics El lienzo de dibujo del terreno. graphics != null
     */
    protected void dibujarRecoleccion( Graphics2D graphics )
    {
        int cotaX = x * ancho;
        int cotaY = y * ancho;
        
        graphics.setColor( new Color( 160, 82, 45 ) );
        graphics.fillRect( cotaX, cotaY, ancho, ancho );
        
        graphics.setStroke( new BasicStroke( 4.0f ) );
        graphics.setColor( new Color( 142, 72, 14 ) );
        graphics.drawRect( cotaX, cotaY, ancho, ancho );
        
        int riegos = 4;
        double delta = ( ancho / riegos );
        
        // Los rastros del color
        Color[] colores = new Color[]{ new Color( 142, 72, 14 ), new Color( 107, 49, 0 ), new Color( 62, 28, 0 ) };
        
        cotaX += delta;
        cotaY += delta;
        for( int i = 0; i < colores.length; i++ )
        {
            Color color = colores[ i ];
            
            // Dibuja el rastro grueso
            for( int j = 0; j < riegos - 1; j++ )
            {
                
                double diferencia = ancho - ( 2 * delta );
                double radio = 2;
                
                int previoX = cotaX + ( int ) ( delta * j );
                int previoY = cotaY;
                
                //Dibuja el labrado
                for( int k = 0; k < diferencia; k++ )
                {
                    int pX = ( ( cotaX + ( int ) ( delta * j ) ) - ( int ) ( radio * Math.sin( k * ( Math.PI / 8 ) ) ) );
                    int pY = ( int ) ( cotaY + k );
                    
                    graphics.setStroke( new BasicStroke( 15.0f / ( i + 1 ), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
                    graphics.setColor( color );
                    graphics.drawLine( previoX, previoY, pX, pY );
                    
                    previoX = pX;
                    previoY = pY;
                }
                
                //Dibuja las plantas
                for( int k = 0; k < diferencia; k++ )
                {
                    int pX = ( ( cotaX + ( int ) ( delta * j ) ) - ( int ) ( radio * Math.sin( k * ( Math.PI / 8 ) ) ) );
                    int pY = ( int ) ( cotaY + k );
                    if( k % 15 == 0 )
                    {
                        dibujarPlantasRecoleccion( graphics, pX, pY );
                    }
                }
            }
        }
    }
    
    /**
     * Dibuja el estado de putrefacción del cultivo de la cebolla
     * @param graphics El lienzo de dibujo del terreno. graphics != null
     */
    protected void dibujarPutrefaccion( Graphics2D graphics )
    {
        int cotaX = x * ancho;
        int cotaY = y * ancho;
        
        graphics.setColor( new Color( 160, 82, 45 ) );
        graphics.fillRect( cotaX, cotaY, ancho, ancho );
        
        graphics.setStroke( new BasicStroke( 4.0f ) );
        graphics.setColor( new Color( 142, 72, 14 ) );
        graphics.drawRect( cotaX, cotaY, ancho, ancho );
        
        int riegos = 4;
        double delta = ( ancho / riegos );
        
        // Los rastros del color
        Color[] colores = new Color[]{ new Color( 142, 72, 14 ), new Color( 107, 49, 0 ), new Color( 62, 28, 0 ) };
        
        cotaX += delta;
        cotaY += delta;
        for( int i = 0; i < colores.length; i++ )
        {
            Color color = colores[ i ];
            
            // Dibuja el rastro grueso
            for( int j = 0; j < riegos - 1; j++ )
            {
                
                double diferencia = ancho - ( 2 * delta );
                double radio = 2;
                
                int previoX = cotaX + ( int ) ( delta * j );
                int previoY = cotaY;
                
                //Dibuja el labrado
                for( int k = 0; k < diferencia; k++ )
                {
                    int pX = ( ( cotaX + ( int ) ( delta * j ) ) - ( int ) ( radio * Math.sin( k * ( Math.PI / 8 ) ) ) );
                    int pY = ( int ) ( cotaY + k );
                    
                    graphics.setStroke( new BasicStroke( 15.0f / ( i + 1 ), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
                    graphics.setColor( color );
                    graphics.drawLine( previoX, previoY, pX, pY );
                    
                    previoX = pX;
                    previoY = pY;
                }
                
                //Dibuja las plantas
                for( int k = 0; k < diferencia; k++ )
                {
                    int pX = ( ( cotaX + ( int ) ( delta * j ) ) - ( int ) ( radio * Math.sin( k * ( Math.PI / 8 ) ) ) );
                    int pY = ( int ) ( cotaY + k );
                    if( k % 15 == 0 )
                    {
                        dibujarPlantasPutrefaccion( graphics, pX, pY );
                    }
                }
            }
        }
    }

    /**
     * Dibuja unas plantas en la posición especificada en estado de maduración
     * @param graphics El lienzo donde se dibujan las plantas. graphics != null
     */
    private void dibujarPlantasMaduracion( Graphics2D graphics, int posX, int posY )
    {
        graphics.setColor( Color.GREEN );
        graphics.setStroke( new BasicStroke( 2.0f ) );

        double pasoRadio = 1;
        double minipaso = Math.PI / 16;
        double paso = Math.PI / 2;

        for( int i = 0; i < 4; i++ )
        {

            double previoX = 0;
            double previoY = 0;

            int ang = 3;
            for( int j = 2; j < 7; j += 3 )
            {
                double angulo1 = ( i * paso ) + ( ang * minipaso );
                double pX = ( j * pasoRadio ) * Math.cos( angulo1 );
                double pY = ( j * pasoRadio ) * Math.sin( angulo1 );
                graphics.drawLine( ( int ) ( posX + previoX ), ( int ) ( posY + previoY ), ( int ) ( posX + pX ), ( int ) ( posY + pY ) );

                double angulo2 = ( i * paso ) + ( ang - 1 ) * minipaso;
                double qX = ( ( j - 1 ) * pasoRadio ) * Math.cos( angulo2 );
                double qY = ( ( j - 1 ) * pasoRadio ) * Math.sin( angulo2 );
                graphics.drawLine( ( int ) ( posX + pX ), ( int ) ( posY + pY ), ( int ) ( posX + qX ), ( int ) ( posY + qY ) );

                previoX = qX;
                previoY = qY;

                j--;
                ang--;
            }

        }
    }
    
    /**
     * Dibuja unas plantas en la posición especificada en estado de recolección
     * @param graphics El lienzo donde se dibujan las plantas. graphics != null
     */
    private void dibujarPlantasRecoleccion( Graphics2D graphics, int posX, int posY )
    {
        graphics.setColor( new Color( 0, 128, 0 ) );
        graphics.setStroke( new BasicStroke( 2.0f ) );
        
        double pasoRadio = 1.5;
        double minipaso = Math.PI / 16;
        double paso = Math.PI / 2;
        
        for( int i = 0; i < 4; i++ )
        {
            
            double previoX = 0;
            double previoY = 0;
            
            int ang = 3;
            for( int j = 2; j < 7; j += 3 )
            {
                double angulo1 = ( i * paso ) + ( ang * minipaso );
                double pX = ( j * pasoRadio ) * Math.cos( angulo1 );
                double pY = ( j * pasoRadio ) * Math.sin( angulo1 );
                graphics.drawLine( ( int ) ( posX + previoX ), ( int ) ( posY + previoY ), ( int ) ( posX + pX ), ( int ) ( posY + pY ) );
                
                double angulo2 = ( i * paso ) + ( ang - 1 ) * minipaso;
                double qX = ( ( j - 1 ) * pasoRadio ) * Math.cos( angulo2 );
                double qY = ( ( j - 1 ) * pasoRadio ) * Math.sin( angulo2 );
                graphics.drawLine( ( int ) ( posX + pX ), ( int ) ( posY + pY ), ( int ) ( posX + qX ), ( int ) ( posY + qY ) );
                
                previoX = qX;
                previoY = qY;
                
                j--;
                ang--;
            }
            
        }
    }
    
    /**
     * Dibuja unas plantas en la posición especificada en estado de putrefacción
     * @param graphics El lienzo donde se dibujan las plantas. graphics != null
     */
    private void dibujarPlantasPutrefaccion( Graphics2D graphics, int posX, int posY )
    {
        graphics.setColor( new Color( 175, 141, 81 ) );
        graphics.setStroke( new BasicStroke( 4.0f ) );
        
        double pasoRadio = 2;
        double minipaso = Math.PI / 16;
        double paso = Math.PI / 2;
        
        for( int i = 0; i < 4; i++ )
        {
            
            double previoX = 0;
            double previoY = 0;
            
            int ang = 3;
            for( int j = 2; j < 7; j += 3 )
            {
                double angulo1 = ( i * paso ) + ( ang * minipaso );
                double pX = ( j * pasoRadio ) * Math.cos( angulo1 );
                double pY = ( j * pasoRadio ) * Math.sin( angulo1 );
                graphics.drawLine( ( int ) ( posX + previoX ), ( int ) ( posY + previoY ), ( int ) ( posX + pX ), ( int ) ( posY + pY ) );
                
                double angulo2 = ( i * paso ) + ( ang - 1 ) * minipaso;
                double qX = ( ( j - 1 ) * pasoRadio ) * Math.cos( angulo2 );
                double qY = ( ( j - 1 ) * pasoRadio ) * Math.sin( angulo2 );
                graphics.drawLine( ( int ) ( posX + pX ), ( int ) ( posY + pY ), ( int ) ( posX + qX ), ( int ) ( posY + qY ) );
                
                previoX = qX;
                previoY = qY;
                
                j--;
                ang--;
            }
        }
    }
}
