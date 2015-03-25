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
 * Rango de impuesto de vehículos
 */
public class RangoImpuesto
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Inicio del rango */
    private double inicio;
    /** Fin del rango */
    private double fin;
    /** Porcentaje */
    private double porcentaje;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un rango de impuesto.
     * @param unInicio Inicio del rango.
     * @param unFin Fin del rango.
     * @param unPorcentaje Porcentaje de impuesto que aplica para el rango.
     */
    public RangoImpuesto( double unInicio, double unFin, double unPorcentaje )
    {
        inicio = unInicio;
        fin = unFin;
        porcentaje = unPorcentaje;
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Indica si el valor está en el rango.
     * @param valor Valor que se va a buscar en el rango.
     * @return true si el valor está en el rango, false en caso contrario.
     */
    public boolean contieneA( double valor )
    {
        return ( valor >= inicio && valor < fin );
    }

    /**
     * Retorna el porcentaje.
     * @return porcentaje.
     */
    public double darPorcentaje( )
    {
        return porcentaje;
    }
}