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
 * Línea del vehículo
 */
public class Linea
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Nombre de la línea */
    private String nombre;
    /** Modelos de la línea */
    private ArrayList modelos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una línea de vehículo con el nombre dado.
     * @param elNombre Nombre de la línea.
     */
    public Linea( String elNombre )
    {
        nombre = elNombre;
        modelos = new ArrayList( );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el nombre de la línea.
     * @return nombre de la línea.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna los modelos de la línea.
     * @return modelos.
     */
    public ArrayList darModelos( )
    {
        return modelos;
    }

    /**
     * Busca una modelo de la línea dado su año.
     * @param anio Año del modelo. anio != null.
     * @return el modelo si lo encuentra o null si no lo encuentra.
     */
    public Modelo buscarModelo( String anio )
    {
        Modelo modelo = null;
        for( int i = 0; i < modelos.size( ) && modelo == null; i++ )
        {
            Modelo modeloAux = ( Modelo )modelos.get( i );
            if( modeloAux.darAnio( ).equals( anio ) )
                modelo = modeloAux;
        }
        return modelo;
    }

    /**
     * Adiciona un modelo a la línea. <br>
     * <b>post: </b> se agrega un nuevo modelo a la lista de modelos de la línea.
     * @param unModelo Modelo a adicionar. unModelo != null.
     */
    public void adicionarModelo( Modelo unModelo )
    {
        modelos.add( unModelo );
    }
}