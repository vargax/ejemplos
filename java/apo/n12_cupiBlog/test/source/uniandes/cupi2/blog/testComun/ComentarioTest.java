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
import uniandes.cupi2.blog.comun.Comentario;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Comentario estén correctamente implementados
 */
public class ComentarioTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Comentario comentario;
    
    /**
     * El artículo relacionado con el comentario
     */
    private Articulo articulo;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Comentario vacío
     *  
     */
    private void setupEscenario1( )
    {
        articulo = new Articulo("Usuario 1", "El título", "La Categoría", "El contenido", new Date());
        comentario = new Comentario( "Usuario", articulo, "El contenido del comentario", new Date(2010, 02, 10) );
    }

    /**
     * Prueba del constructor del comentario
     */
    public void testComentario( )
    {
        setupEscenario1( );
        
        assertEquals("El nombre de usuario debe ser el mismo", comentario.darUsuario( ), "Usuario");
        assertEquals("El artículo debe ser el mismo", comentario.darArticulo( ), articulo);
        assertEquals("El contenido del comentario es el mismo", comentario.darContenido( ), "El contenido del comentario");
        assertEquals("La fecha debe ser la misma", comentario.darFechaPublicacion( ), new Date(2010, 02, 10));
    }

}