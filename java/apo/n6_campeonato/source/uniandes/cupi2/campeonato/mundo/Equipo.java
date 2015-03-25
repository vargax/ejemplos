/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_campeonato 
 * Autor: Mario Sánchez - 21/07/2005 
 * Autor: J. Villalobos - 28/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.campeonato.mundo;

/**
 * Es la clase que representa a un equipo
 */
public class Equipo
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es el nombre del equipo
     */
    private String nombre;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye una nueva instancia de un equipo
     * @param nombreEquipo Es el nombre del equipo. nombreEquipo != null
     */
    public Equipo( String nombreEquipo )
    {
        nombre = nombreEquipo;
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el nombre del equipo
     * @return nombre
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna una representación como cadena de caracteres del equipo
     * @return nombre
     */
    public String toString( )
    {
        return darNombre( );
    }
}