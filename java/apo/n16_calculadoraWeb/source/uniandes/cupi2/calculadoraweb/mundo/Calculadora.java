/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_calculadoraWeb
 * Autor: Pablo Barvo - Mayo 4/2006
 * Autor: Jorge Villalobos - Noviembre 13/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.calculadoraweb.mundo;

/**
 * Representa una calculadora simple
 */
public class Calculadora
{
    // -----------------------------------------------------------------
    // Implementación del patrón Singleton
    // -----------------------------------------------------------------

    /**
     * Guarda una referencia a la única instancia de la clase
     */
    private static Calculadora instancia = null;

    /**
     * Define el único constructor de la clase como privado, para evitar que se creen nuevas instancias
     */
    private Calculadora( )
    {
    }

    /**
     * Este método permite localizar la única instancia existente de la clase. Esto permite que todos compartan el mismo objeto.
     */
    public static Calculadora getInstance( )
    {
        if( instancia == null )
            instancia = new Calculadora( );
        return instancia;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Realiza la suma de los dos parámetros
     * @param v1 Valor 1
     * @param v2 Valor 2
     * @return Suma de los dos valores recibidos como parámetro
     */
    public double sumar( double v1, double v2 )
    {
        return v1 + v2;
    }

    /**
     * Realiza la resta de los dos parámetros
     * @param v1 Valor 1
     * @param v2 Valor 2
     * @return Resta de los dos valores recibidos como parámetro
     */
    public double restar( double v1, double v2 )
    {
        return v1 - v2;
    }

    /**
     * Realiza la multiplicación de los dos parámetros
     * @param v1 Valor 1
     * @param v2 Valor 2
     * @return Multiplicación de los dos valores recibidos como parámetro
     */
    public double multiplicar( double v1, double v2 )
    {
        return v1 * v2;
    }

    /**
     * Realiza la división de los dos parámetros
     * @param v1 Valor 1
     * @param v2 Valor 2
     * @return Resultado de la división v1 / v2
     */
    public double division( double v1, double v2 )
    {
        return v1 / v2;
    }
}
