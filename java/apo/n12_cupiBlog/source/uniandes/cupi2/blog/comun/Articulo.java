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
 * El usuario autor del artículo debe existir.<br>
 * El título del artículo debe existir.<br>
 * La categoría del artículo debe existir.<br>
 * El contenido del artículo debe existir.<br>
 * La fecha de publicación del artículo debe existir.<br>
 */
public class Articulo
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El usuario que publicó del artículo
     */
    private String usuario;

    /**
     * El título del artículo
     */
    private String titulo;

    /**
     * La categoría del artículo
     */
    private String categoria;

    /**
     * El texto del contenido del artículo
     */
    private String contenido;
    
    /**
     * La fecha de publicación del artículo
     */
    private Date fechaPublicacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor del artículo, con el título, la categoría y el contenido del artículo.
     * @param nUsuario El usuario autor del artículo. nUsuario != null
     * @param nTitulo El título del artículo. nTítulo != null
     * @param nCategoria La categoría del artículo. nCategoria != null
     * @param nContenido El texto con el contenido del artículo. nContenido != null
     * @param nFechaPublicacion La fecha de publicación del artículo. nFechaPublicacion != null
     */
    public Articulo( String nUsuario, String nTitulo, String nCategoria, String nContenido, Date nFechaPublicacion )
    {
        usuario = nUsuario;
        titulo = nTitulo;
        categoria = nCategoria;
        contenido = nContenido;
        fechaPublicacion = nFechaPublicacion;
        
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Devuelve el usuario autor del artículo
     * @return El usuario autor del artículo
     */
    public String darUsuario( )
    {
        return usuario;
    }

    /**
     * Devuelve el título del artículo
     * @return El título del artículo
     */
    public String darTitulo( )
    {
        return titulo;
    }

    /**
     * Devuelve la categoría del artículo
     * @return La categoria del artículo
     */
    public String darCategoria( )
    {
        return categoria;
    }

    /**
     * Devuelve el texto del contenido del artículo
     * @return El contenido del artículo
     */
    public String darContenido( )
    {
        return contenido;
    }
    
    /**
     * Devuelve la fecha de publicación del artículo
     * @return La fecha de publicación del artículo
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
    /**
     * Modifica el título del artículo
     * @param nTitulo El nuevo título del artículo. nTitulo != null
     */
    public void modificarTitulo( String nTitulo )
    {
        titulo = nTitulo;
        verificarInvariante( );
    }

    /**
     * Modifica la categoría del artículo
     * @param nCategoria la nueva categoría del artículo. nCategoria != null
     */
    public void modificarCategoria( String nCategoria )
    {
        categoria = nCategoria;
        verificarInvariante( );
    }

    /**
     * Modifica el texto de contenido del artículo
     * @param nContenido El nuevo contenido del artículo. nContenido != null
     */
    public void modificarApellidos( String nContenido )
    {
        contenido = nContenido;
        verificarInvariante( );
    }
    
    /**
     * Devuelve la descripción del artículo
     * @return La descripción del artículo
     */
    public String toString(){
        return titulo + " ( " + usuario + " )";
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Revisa la invariante de la clase.<br>
     * <b>inv:</b>
     * usuario != null<br>
     * titulo != null<br>
     * categoria != null<br>
     * contenido != null<br>
     * fechaPublicacion != null
     */
    private void verificarInvariante( )
    {
        assert usuario != null: "El usuario autor del artículo debe tener un valor no nulo"; 
        assert titulo != null: "El título del artículo debe tener un valor no nulo"; 
        assert categoria != null: "La categoría del artículo debe tener un valor no nulo"; 
        assert contenido != null: "El contenido del artículo debe tener un valor no nulo"; 
        assert fechaPublicacion != null: "La fecha de publicación del artículo debe tener un valor no nulo"; 
    }
}