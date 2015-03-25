/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ContactosTest.java 1083 2010-03-01 15:32:52Z y-oviedo $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupiphoneComponenteContactos
 * Autor: Yeisson Oviedo - 23-feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiphoneComponenteContactos.test;

import junit.framework.TestCase;
import uniandes.cupi2.componenteContactos.mundo.ComponenteContactos;
import uniandes.cupi2.componenteContactos.mundo.IComponenteContactos;

/**
 * Esta es la clase usada para verificar que los métodos de la clase ComponenteContactos estén correctamente implementados
 */
public class ContactosTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private IComponenteContactos componenteContactos;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva ComponenteContactos vacía
     *  
     */
    private void setupEscenario1( )
    {
        componenteContactos = new ComponenteContactos( null );
    }

    /**
     * Prueba 1
     */
    public void testContactos( )
    {
        setupEscenario1( );
        
        //
        //Código de la prueba
    }

}