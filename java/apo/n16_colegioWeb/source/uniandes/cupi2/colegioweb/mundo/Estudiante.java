/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - Apr 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.colegioweb.mundo;

/**
 * Representa un estudiante del colegio
 */
public class Estudiante
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Código del estudiante
     */
    private String codigo;

    /**
     * Nombre del estudiante
     */
    private String nombre;

    /**
     * Apellido del estudiante
     */
    private String apellido;

    /**
     * Curso del estudiante
     */
    private String curso;

    /**
     * Promedio actual del estudiante
     */
    private String promedio;

    /**
     * Comentarios del estudiante
     */
    private String comentarios;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un estudiante vacío
     * @param pCodigo Código del estudiante
     */
    public Estudiante( String pCodigo )
    {
        codigo = pCodigo;
    }

    /**
     * Constructor de un estudiante con todas sus propiedades
     * @param pCodigo Código del estudiante
     * @param pNombre Nombre del estudiante
     * @param pApellido Apellido del estudiante
     * @param pCurso Curso actual del estudiante
     * @param pPromedio Promedio actual del estudiante
     * @param pComentarios Comentarios adicionales
     */
    public Estudiante( String pCodigo, String pNombre, String pApellido, String pCurso, String pPromedio, String pComentarios )
    {
        codigo = pCodigo;
        nombre = pNombre;
        apellido = pApellido;
        curso = pCurso;
        promedio = pPromedio;
        comentarios = pComentarios;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el apellido del estudiante
     * @return Apellido del estudiante
     */
    public String darApellido( )
    {
        return apellido;
    }

    /**
     * Cambia el apellido del estudiante
     * @param pApellido Nuevo apellido
     */
    public void cambiarApellido( String pApellido )
    {
        apellido = pApellido;
    }

    /**
     * Devuelve los comentarios adicionales
     * @return Comentarios adicionales
     */
    public String darComentarios( )
    {
        return comentarios;
    }

    /**
     * Cambia los comentarios adicionales
     * @param pComentarios Comentarios adicionales
     */
    public void cambiarComentarios( String pComentarios )
    {
        comentarios = pComentarios;
    }

    /**
     * Devuelve el curso del estudiante
     * @return Curso del estudiante
     */
    public String darCurso( )
    {
        return curso;
    }

    /**
     * Cambia el curso del estudiante
     * @param pCurso Nuevo curso del estudiante
     */
    public void cambiarCurso( String pCurso )
    {
        curso = pCurso;
    }

    /**
     * Devuelve el nombre del estudiante
     * @return Nombre del estudiante
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Cambia el nombre del estudiante
     * @param pNombre Nuevo nombre
     */
    public void cambiarNombre( String pNombre )
    {
        nombre = pNombre;
    }

    /**
     * Devuelve el promedio actual del estudiante
     * @return Promedio actual del estudiante
     */
    public String darPromedio( )
    {
        return promedio;
    }

    /**
     * Cambia el promedio actual del estudiante
     * @param pPromedio Nuevo promedio
     */
    public void cambiarPromedio( String pPromedio )
    {
        promedio = pPromedio;
    }

    /**
     * Devuelve el código del estudiante
     * @return Código del estudiante
     */
    public String darCodigo( )
    {
        return codigo;
    }

    @Override
    public String toString( )
    {
        return codigo + " - " + nombre + " " + apellido + " - Curso:" + curso + ", Promedio:" + promedio + " - Comentarios:" + comentarios;
    }

}
