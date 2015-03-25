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

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import uniandes.cupi2.cupiFinca.mundo.excepciones.PersistenciaException;

/**
 * Clase que representa un cultivo de zanahoria
 */
public class Zanahoria extends Cultivable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * El nombre del producto que representa la zanahoria
     */
    public static final String NOMBRE_CULTIVO = "Zanahoria";

    /**
     * El costo de instalación de la zanahoria
     */
    public static final int COSTO_CULTIVO = 200;

    /**
     * La ganancia por instalación de la zanahoria
     */
    public static final int GANANCIA_CULTIVO = 350;

    /**
     * La ruta del archivo con la imagen de la zanahoria
     */
    public static final String RUTA_IMAGEN = "./data/imagenes/zanahoria.png";

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva zanahoria al cultivo.<br>
     * <b>pre: </b>El precio del producto no supera la cantidad de dinero que tiene la finca.<br>
     * @param pX La coordenada x del terreno. pX >= 0
     * @param pY La coordenada y del terreno. pY >= 0
     * @param pAncho El ancho del campo del terreno. pAncho > 0
     * @param pTiempo El tiempo que lleva de iniciada la producción de la zanahoria. pTiempo >= 0
     * @throws PersistenciaException En caso de no leer la imagen del cultivo
     */
    public Zanahoria( int pX, int pY, int pAncho, int pTiempo ) throws PersistenciaException
    {
        super( NOMBRE_CULTIVO, RUTA_IMAGEN, pX, pY, pAncho, COSTO_CULTIVO, GANANCIA_CULTIVO, pTiempo );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Dibuja el producto en el pedazo del campo
     * @param graphics El campo de dibujo. graphics != null
     */
    public void dibujar( Graphics2D graphics )
    {
        if( darEstado( ) == PREPARAR )
        {
            dibujarPreparacion( graphics );
        }
        else if( darEstado( ) == SEMBRAR )
        {
            dibujarInstalacion( graphics );
        }
        else if( darEstado( ) == CRECER )
        {
            dibujarMaduracion( graphics );
        }
        else if( darEstado( ) == COSECHAR )
        {
            dibujarRecoleccion( graphics );
        }
        else if( darEstado( ) == PUDRIR )
        {
            dibujarPutrefaccion( graphics );
        }
        
        // Dibuja la zanahoria
        AffineTransform transform = new AffineTransform( );
        transform.translate( x * ancho + 50, y * ancho + 50 );
        transform.scale( 0.5, 0.5 );

        graphics.drawRenderedImage( imagen, transform );
    }
}
