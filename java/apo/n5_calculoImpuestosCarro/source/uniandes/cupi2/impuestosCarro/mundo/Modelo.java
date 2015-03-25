/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Impuestos de Carros
 * Autor: Katalina Marcos - Abr 15, 2005
 * Autor: Diana Puentes - Jun 23, 2005
 * Autor: Jorge Villalobos - Jul 10, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.impuestosCarro.mundo;

/**
 * Modelo del vehículo
 */
public class Modelo
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Año del modelo */
    private String anio;
    /** Precio del vehículo de este modelo */
    private double precio;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un modelo con el año y su precio.
     * @param elAnio Año del modelo.
     * @param elPrecio Precio de un vehículo de este modelo.
     */
    public Modelo( String elAnio, double elPrecio )
    {
        anio = elAnio;
        precio = elPrecio;
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el año del modelo.
     * @return año.
     */
    public String darAnio( )
    {
        return anio;
    }

    /**
     * Retorna el precio del modelo.
     * @return precio.
     */
    public double darPrecio( )
    {
        return precio;
    }
}