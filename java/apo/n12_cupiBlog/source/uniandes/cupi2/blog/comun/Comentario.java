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

package uniandes.cupi2.blog.comun;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La clase que representa un artículo del blog.<br>
 * <b>inv:</b><br>
 * El usuario del comentario debe existir.<br>
 * El artículo del comentario debe existir.<br>
 * El contenido del comentario debe existir.<br>
 * El comentario debe tener una fecha de publicación.<br>
 */
public class Comentario
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El texto del contenido del artículo
     */
    private String contenido;

    /**
     * El usuario que realiza el comentario
     */
    private String usuario;
    
    /**
     * El artículo que se está comentando
     */
    private Articulo articulo;
    
    /**
     * La fecha de publicación del comentario
     */
    private Date fechaPublicacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor del comentario que se crea con el contenido del comentario, el usuario que lo publicó y el artículo al que pertenece.
     * @param nArticulo El artículo que se ha comentado. nArticulo != null
     * @param nUsuario El usuario que ha publicado el comentario. nUsuario != null
     * @param nContenido El texto con el contenido del comentario. nContenido != null
     * @param nFechaPublicacion La fecha de publicación del comentario. nFechaPublicacion != null
     */
    public Comentario( String nUsuario, Articulo nArticulo, String nContenido, Date nFechaPublicacion )
    {
        usuario = nUsuario;
        articulo = nArticulo;
        contenido = nContenido;
        fechaPublicacion = nFechaPublicacion;
        
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el usuario que realizó el comentario
     * @return El usuario que realizó el comentario
     */
    public String darUsuario( )
    {
        return usuario;
    }

    /**
     * Devuelve el artículo comentado
     * @return El artículo comentado
     */
    public Articulo darArticulo( )
    {
        return articulo;
    }

    /**
     * Devuelve el texto del contenido del comentario
     * @return El contenido del comentario
     */
    public String darContenido( )
    {
        return contenido;
    }
    
    
    /**
     * Devuelve la fecha de publicación del comentario
     * @return La fecha de publicación del comentario
     */
    public Date darFechaPublicacion( )
    {
        return fechaPublicacion;
    }
    /**
     * Devuelve la fecha de publicación en el formato del protocolo
     */
    public String darFechaPublicacionP() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm"); 
		return sdf.format(fechaPublicacion);
    }
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Revisa la invariante de la clase.<br>
     * <b>inv:</b>
     * usuario != null<br>
     * articulo != null<br>
     * contenido != null<br>
     * fechaPublicacion != null
     */
    private void verificarInvariante( )
    {
        assert usuario != null: "El usuario dueño del comentario debe tener un valor no nulo"; 
        assert articulo != null: "El artículo comentado debe tener un valor no nulo"; 
        assert contenido != null: "El contenido del comentario debe tener un valor no nulo"; 
        assert fechaPublicacion != null: "La fecha de publicación del comentario debe tener un valor no nulo"; 
    }
}