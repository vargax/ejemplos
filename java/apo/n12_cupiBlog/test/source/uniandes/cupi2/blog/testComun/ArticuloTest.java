/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.testComun;

import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.blog.comun.Articulo;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Artículo estén correctamente implementados
 */
public class ArticuloTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Articulo articulo;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Artículo vacío
     *  
     */
    private void setupEscenario1( )
    {
        articulo = new Articulo( "Usuario", "Titulo", "Categoria", "Contenido", new Date(2011, 02, 10) );
    }

    /**
     * Prueba del constructor del artículo
     */
    public void testArticulo( )
    {
        setupEscenario1( );
        
        assertEquals("El nombre de usuario debe ser el mismo", articulo.darUsuario( ), "Usuario");
        assertEquals("El título deben ser el mismo", articulo.darTitulo( ), "Titulo");
        assertEquals("La categoria debe ser la misma", articulo.darCategoria( ), "Categoria");
        assertEquals("El contenido debe ser lo mismo", articulo.darContenido( ), "Contenido");
        assertEquals("La fecha debe ser la misma", articulo.darFechaPublicacion( ), new Date( 2011, 02, 10 ));
    }

}