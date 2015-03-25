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

/**
 * La clase que representa un usuario.<br>
 * <b>inv:</b> El nombre del usuario debe existir.<br>
 * El nombre de pila del usuario debe existir.<br>
 * Los apellidos del usuario debe existir.
 */
public class InfoUsuario
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nombre de usuario
     */
    private String nombreUsuario;

    /**
     * EL nombre de pila del usuario
     */
    private String nombre;

    /**
     * Los apellidos del usuario
     */
    private String apellidos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor del usuario, con el nombre de usuario, el nombre de pila y los apellidos.
     * @param nNombreUsuario El nombre de usuario. nNombreUsuario != null
     * @param nNombre El nombre de pila del usuario. nNombre != null
     * @param nApellidos Los apellidos del usuario. nApellidos != null
     */
    public InfoUsuario( String nNombreUsuario, String nNombre, String nApellidos )
    {
        nombreUsuario = nNombreUsuario;
        nombre = nNombre;
        apellidos = nApellidos;
        
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el nombre de usuario
     * @return El nombre de usuario
     */
    public String darNombreUsuario( )
    {
        return nombreUsuario;
    }

    /**
     * Devuelve el nombre de pila del usuario
     * @return El nombre de pila del usuario
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Devuelve los apellidos del usuario
     * @return Los apellidos del usuario
     */
    public String darApellidos( )
    {
        return apellidos;
    }

    /**
     * Modifica el nombre de usuario
     * @param nNombreUsuario El nuevo nombre de usuario. nNombreUsuario != null
     */
    public void modificarNombreUsuario( String nNombreUsuario )
    {
        nombreUsuario = nNombreUsuario;
        verificarInvariante( );
    }

    /**
     * Modifica el nombre de pila del usuario
     * @param nNombre el nuevo nombre de pila del usuario. nNombre != null
     */
    public void modificarNombre( String nNombre )
    {
        nombre = nNombre;
        verificarInvariante( );
    }

    /**
     * Modifica los apellidos del usuario
     * @param nApellidos Los nuevos apellidos del usuario. nApellidos != null
     */
    public void modificarApellidos( String nApellidos )
    {
        apellidos = nApellidos;
        verificarInvariante( );
    }
    
    /**
     * Devuelve la descripción del objeto
     * @return La descripción del objeto.
     */
    public String toString(){
        return nombreUsuario + ": " + apellidos + ", " + nombre;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Revisa la invariante de la clase.<br>
     * <b>inv:</b>
     * nombreUsuario != null<br>
     * nombre != null<br>
     * apellidos != null
     */
    private void verificarInvariante( )
    {
        assert nombreUsuario != null: "El nombre de usuario debe tener un valor no nulo"; 
        assert nombre != null: "El nombre debe tener un valor no nulo"; 
        assert apellidos != null: "Los apellidos deben tener un valor no nulo"; 
    }
}