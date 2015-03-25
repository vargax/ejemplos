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

import java.util.ArrayList;

/**
 * Representa un colegio con sus operaciones simples
 */
public class Colegio
{

    // -----------------------------------------------------------------
    // Singleton
    // -----------------------------------------------------------------

    /**
     * Instancia única de la clase
     */
    private static Colegio instancia;

    /**
     * Devuelve la instancia única de la clase
     * @return Instancia única de la clase
     * @throws DatosException Excepción al crear la instancia del colegio
     */
    public static Colegio darInstancia( ) throws DatosException
    {
        if( instancia == null )
        {
            instancia = new Colegio( );
        }
        return instancia;
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * DAO de estudiantes
     */
    private EstudianteDAO dao;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del colegio
     * @throws DatosException Excepción al crear el DAO
     */
    private Colegio( ) throws DatosException
    {
        dao = new EstudianteDAO( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un estudiante al colegio
     * @param estudiante Estudiante a agregar
     * @throws DatosException Excepción de error al agregar el estudiante
     */
    public void agregarEstudiante( Estudiante estudiante ) throws DatosException
    {
        dao.agregarEstudiante( estudiante );
    }

    /**
     * Modifica la información del estudiante especificado <br>
     * <b>pre:</b> El código del estudiante no debe haber cambiado
     * @param estudiante Estudiante con la información actualizada
     * @throws DatosException Excepción de error en la modificación del estudiante
     */
    public void modificarEstudiante( Estudiante estudiante ) throws DatosException
    {
        dao.modificarEstudiante( estudiante );
    }

    /**
     * Elimina un estudiante del colegio
     * @param codigo Código del estudiante
     * @throws DatosException Excepción de error al eliminar el estudiante
     */
    public void eliminarEstudiante( String codigo ) throws DatosException
    {
        dao.eliminarEstudiante( codigo );
    }

    /**
     * Devuelve todos los estudiantes del colegio
     * @return Todos los estudiantes de la base de datos
     * @throws DatosException Excepción al cargar los estudiantes
     */
    public ArrayList<Estudiante> darEstudiantes( ) throws DatosException
    {
        return dao.darEstudiantes( );
    }

    /**
     * Devuelve los estudiantes del colegio que cumplen con la condición
     * @param campo Campo por el cual se va a realizar la búsqueda
     * @param valor Valor del campo
     * @return Todos los estudiantes de la base de datos
     * @throws DatosException Excepción al cargar los estudiantes
     */
    public ArrayList<Estudiante> darEstudiantes( String campo, String valor ) throws DatosException
    {
        //
        // Valida el campo
        boolean valido = false;
        valido = valido || campo.equalsIgnoreCase( "CODIGO" );
        valido = valido || campo.equalsIgnoreCase( "NOMBRE" );
        valido = valido || campo.equalsIgnoreCase( "APELLIDO" );
        valido = valido || campo.equalsIgnoreCase( "PROMEDIO" );
        valido = valido || campo.equalsIgnoreCase( "COMENTARIOS" );
        valido = valido || campo.equalsIgnoreCase( "CURSO" );
        if( !valido )
        {
            throw new DatosException( "El campo de la selección es inválido" );
        }
        return dao.darEstudiantes( campo, valor );
    }

}
