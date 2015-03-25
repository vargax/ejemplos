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
import java.io.PrintWriter;

/**
 * Clase abstracta que me representa un terreno cultivable
 */
public abstract class Construccion extends Terreno
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    
    /**
     * El estado donde se construye la casa
     */
    public static final int CONSTRUIDO = 0;
    
    /**
     * El tiempo de mantenimiento en la construcción
     */
    public static final int TIEMPO_PUTREFACCION = -1;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo producto cultivable a la finca.<br>
     * <b>pre: </b>El precio del producto no supera el que tiene la finca.<br>
     * @param pNombre El nombre del producto. pNombre != null
     * @param pX La coordenada x del terreno. pX >= 0
     * @param pY La coordenada y del terreno. pY >= 0
     * @param pAncho El ancho del campo del terreno. pAncho > 0
     * @param pCosto El costo de producir. pCosto >= 0
     */
    public Construccion( String pNombre, int pX, int pY, int pAncho, int pCosto )
    {
        super( pNombre, pX, pY, pAncho, pCosto, 0, false, 0 );
        
        tiempos = new int[1];
        estado = CONSTRUIDO;

        tiempos[ CONSTRUIDO ] = TIEMPO_PUTREFACCION;
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
     * @return Los resultados de haber recogido el terreno.
     */
    public int darGanancia(){
        return ganancia;
    }

    /**
     * Guarda la información del terreno en el archivo que entra por parámetro
     * @param pw El archivo donde se escribe la información del terreno. pw != null
     */
    public void guardarTerreno( PrintWriter pw )
    {
        pw.println( darNombre( ) + ";" + x + ";" + y );
    }
}
