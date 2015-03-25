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

import java.util.*;

/**
 * Marca del vehículo
 */
public class Marca
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Nombre de la marca */
    private String nombre;
    /** Líneas de vehículos de la marca */
    private ArrayList lineas;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una marca del nombre dado. <br>
     * <b>post: </b> La marca se crea con el conjunto de líneas vacío.
     * @param elNombre - nombre de la marca.
     */
    public Marca( String elNombre )
    {
        nombre = elNombre;
        lineas = new ArrayList( );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el nombre de la marca.
     * @return nombre de la marca.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna las líneas de la marca.
     * @return líneas.
     */
    public ArrayList darLineas( )
    {
        return lineas;
    }

    /**
     * Busca una línea de la marca dado su nombre.
     * @param nombreLinea Nombre de la línea. nombreLinea != null.
     * @return la línea si la encuentra o null si no lo encuentra.
     */
    public Linea buscarLinea( String nombreLinea )
    {
        Linea linea = null;
        for( int i = 0; i < lineas.size( ) && linea == null; i++ )
        {
            Linea lineaAux = ( Linea )lineas.get( i );
            if( lineaAux.darNombre( ).equalsIgnoreCase( nombreLinea ) )
                linea = lineaAux;
        }
        return linea;
    }

    /**
     * Adiciona una línea de vehículos a la marca si no existe. <br>
     * <b>post: </b> se agrega una nueva línea a la lista de líneas de la marca.
     * @param unaLinea Línea a adicionar. unaLinea != null.
     */
    public void adicionarLinea( Linea unaLinea )
    {
        if( buscarLinea( unaLinea.darNombre( ) ) == null )
        {
            lineas.add( unaLinea );
        }
    }
}