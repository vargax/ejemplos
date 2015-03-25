/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Pablo Marquez - 13 Jun, 2007
 * Autor: Juan Erasmo Gómez - 6 Ago, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.mundo;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.collections.tablaHashing.tablaHashingEstatica.TablaHashingEstatica;

/**
 * Tabla de Hashing para el ejercicio.
 * @param <K> Tipo de dato de las llaves
 * @param <T> Tipo de dato de los valores
 */
public class TablaHashingCentralMensajes<K, T> extends TablaHashingEstatica<K, T>
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = -8001064000819010607L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista de llaves de la tabla de hashing.
     */
    private Lista<K> llaves;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la tabla.
     */
    public TablaHashingCentralMensajes( )
    {
        super( );
        llaves = new Lista<K>( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Sobreescritura del método agregar de la clase uniandes.cupi2.collections.tablaHashing.TablaHashingEstatica.
     */
    public T agregar( K llave, T elemento )
    {
        llaves.agregar( llave );
        return super.agregar( llave, elemento );
    }

    /**
     * Sobreescritura del método eliminar de la clase uniandes.cupi2.collections.tablaHashing.TablaHashingEstatica.
     */
    public T eliminar( K llave )
    {
        llaves.eliminar( llave );
        return super.eliminar( llave );
    }

    /**
     * Retorna las llaves contenidas en la tabla.
     * @return Las llaves contenidas en la tabla.
     */
    public Iterador<K> darLlaves( )
    {
        return llaves.darIterador( );
    }

}
