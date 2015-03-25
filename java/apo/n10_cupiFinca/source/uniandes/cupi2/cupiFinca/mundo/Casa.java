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
import java.awt.Polygon;
import java.awt.geom.Line2D;

/**
 * Clase que representa una casa en la finca
 */
public class Casa extends Construccion
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * El nombre de la construcción
     */
    public static final String NOMBRE_CONSTRUCCION = "Casa";

    /**
     * El costo de construcción de la casa
     */
    public static final int COSTO_CONSTRUCCION = 100;
    

    /**
     * La ruta del archivo con la imagen de la casa
     */
    public static final String RUTA_IMAGEN = "./data/imagenes/casa.png";

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Agrega una nueva casa a los terrenos.<br>
     * <b>pre: </b>El precio del producto no supera la cantidad de dinero que tiene la finca.<br>
     * @param pX La coordenada x del terreno. pX >= 0
     * @param pY La coordenada y del terreno. pY >= 0
     * @param pAncho El ancho del campo del terreno. pAncho > 0
     */
    public Casa( int pX, int pY, int pAncho )
    {
        super( NOMBRE_CONSTRUCCION, pX, pY, pAncho, COSTO_CONSTRUCCION );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Dibuja la casa en el pedazo del campo
     * @param graphics El campo de dibujo. graphics != null
     */
    public void dibujar( Graphics2D graphics )
    {
    	Graphics2D g = (Graphics2D) graphics;
    	
    	// Coordenadas de la esquina del cuadrado de la casa
        int cotaX = x * ancho;
        int cotaY = y * ancho;
        
        /*/ -- DEBUG --
        g.setColor( Color.BLUE );
        g.fillRect( cotaX, cotaY, ancho, ancho );
        
        g.setColor(Color.RED);
        g.drawRect(cotaX, cotaY, 1, 1);
        //*/
        
        // Poligono que representa la pared de la casa
        int[] coorParedX = {cotaX+20, cotaX+41, cotaX+62, cotaX+62, cotaX+20};
        int[] coorParedY = {cotaY+47, cotaY+20, cotaY+47, cotaY+72, cotaY+72};
        
        Polygon pared = new Polygon(coorParedX, coorParedY, 5);
        g.setColor(Color.WHITE);
        g.fill(pared);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2.0f));
        g.draw(pared);
        
        // Poligono que representa la puerta de la casa
        int[] coorPuertaX = {cotaX+30,cotaX+52,cotaX+52,cotaX+30};
        int[] coorPuertaY = {cotaY+50,cotaY+50,cotaY+72,cotaY+72};
        
        Polygon puerta = new Polygon(coorPuertaX, coorPuertaY, 4);
        g.setColor(new Color(67,48,35));
        g.fill(puerta);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2.0f));
        g.draw(puerta);
        
        // Poligono que representa el techo de la casa
        int[] coorTechoX = {cotaX+8,cotaX+41,cotaX+74,cotaX+62,cotaX+41,cotaX+20};
        int[] coorTechoY = {cotaY+35,cotaY+10,cotaY+35,cotaY+47,cotaY+20,cotaY+47};
        
        Polygon techo = new Polygon(coorTechoX, coorTechoY, 6);
        g.setColor(new Color(67,48,35));
        g.fill(techo);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2.0f));
        g.draw(techo);
       
    }    
}
