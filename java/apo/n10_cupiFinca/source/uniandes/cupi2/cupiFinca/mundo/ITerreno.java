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
 * La interfaz que representa cada uno de los terrenos del cultivo
 */
public interface ITerreno
{      
    /**
     * Devuelve el nombre del producto
     * @return El nombre del producto
     */
    public String darNombre();

    /**
     * Devuelve el costo de instalar este producto
     * @return El costo de instalación del producto
     */
    public int darCosto();

    /**
     * Devuelve la ganancia por recoger este producto
     * @return La ganancia de recolección del producto
     */
    public int darGanancia();
    
    /**
     * Devuelve el tiempo que lleva el producto
     * @return El tiempo que lleva el producto instalado
     */
    public int darTiempo();
    
    /**
     * Devuelve el estado en una etapa del proceso de producción
     * @return El estado en el tiempo de producción
     */
    public int darEstado();
    
    /**
     * Devuelve si el producto del terreno se puede recoger
     * @return true si el producto se puede recoger, false en caso contrario
     */
    public boolean esRecolectable();
    
    /**
     * Avanza en el ciclo de vida del terreno
     */
    public void avanzarCiclo();
    
    /**
     * Dibuja el producto en el pedazo del campo
     * @param graphics El campo de dibujo. graphics != null
     */
    public void dibujar( Graphics2D graphics );
    
    /**
     * Guarda la información del terreno en el archivo que está de parámetro
     * @param pw El archivo donde se escribe la información del terreno. pw != null
     */
    public void guardarTerreno( PrintWriter pw );
}
