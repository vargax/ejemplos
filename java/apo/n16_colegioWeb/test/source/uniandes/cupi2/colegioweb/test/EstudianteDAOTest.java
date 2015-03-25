/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: EstudianteDAOTest.java,v 1.1 2006/06/19 00:18:00 cupi2ejemplos Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - 18/06/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.colegioweb.test;

import java.util.ArrayList;

import uniandes.cupi2.colegioweb.mundo.DatosException;
import uniandes.cupi2.colegioweb.mundo.Estudiante;
import uniandes.cupi2.colegioweb.mundo.EstudianteDAO;
import junit.framework.TestCase;

/**
 * Pruebas a la clase EstudianteDAO
 */
public class EstudianteDAOTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * DAO para realizar las pruebas
     */
    private EstudianteDAO dao;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo DAO
     */
    private void setupEscenario1( )
    {
        try
        {
            dao = new EstudianteDAO( );
            limpiar( );
        }
        catch( DatosException e )
        {
            fail( "Error al crear el DAO:" + e.getMessage( ) );
        }
    }

    /**
     * Limpia el DAO y lo deja vacío
     */
    private void limpiar( )
    {
        try
        {
            ArrayList<Estudiante> estudiantes = dao.darEstudiantes( );
            for( int i = 0; i < estudiantes.size( ); i++ )
            {
                Estudiante est = estudiantes.get( i );
                dao.eliminarEstudiante( est.darCodigo( ) );
            }
        }
        catch( DatosException e )
        {
            fail( "Debe poder limpiar el DAO" );
        }
    }

    /**
     * Crea un nuevo DAO y agrega 2 estudiantes
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        //
        // Crea 2 estudiantes
        Estudiante est1 = new Estudiante( "1", "Pedro", "Picapiedra", "1", "2.5", "Comentarios1" );
        Estudiante est2 = new Estudiante( "2", "Pablo", "Marmol", "2", "3.5", "Comentarios2" );
        try
        {
            dao.agregarEstudiante( est1 );
            dao.agregarEstudiante( est2 );
        }
        catch( DatosException e )
        {
            fail( "Debe poder agregar los estudiantes" );
        }
    }

    /**
     * Prueba 1 - Crear estudiantes
     */
    public void testCrear( )
    {
        setupEscenario1( );
        //
        // Crea 2 estudiantes
        Estudiante est1 = new Estudiante( "1", "Pedro", "Picapiedra", "1", "2.5", "Comentarios1" );
        Estudiante est2 = new Estudiante( "2", "Pablo", "Marmol", "2", "3.5", "Comentarios2" );
        try
        {
            dao.agregarEstudiante( est1 );
            dao.agregarEstudiante( est2 );
        }
        catch( DatosException e )
        {
            fail( "Debe poder agregar los estudiantes" );
        }
        //
        // Busca todos los estudiantes
        try
        {
            ArrayList<Estudiante> estudiantes = dao.darEstudiantes( );
            assertEquals( "Debe tener 2 estudiantes", 2, estudiantes.size( ) );
            for( int i = 0; i < estudiantes.size( ); i++ )
            {
                Estudiante est = estudiantes.get( i );
                if( "1".equals( est.darCodigo( ) ) )
                {
                    assertEquals( "El nombre es incorrecto", "Pedro", est.darNombre( ) );
                    assertEquals( "El apellido es incorrecto", "Picapiedra", est.darApellido( ) );
                    assertEquals( "El curso es incorrecto", "1", est.darCurso( ) );
                    assertEquals( "El promedio es incorrecto", "2.5", est.darPromedio( ) );
                    assertEquals( "Los comentarios son incorrectos", "Comentarios1", est.darComentarios( ) );
                }
                else if( "2".equals( est.darCodigo( ) ) )
                {
                    assertEquals( "El nombre es incorrecto", "Pablo", est.darNombre( ) );
                    assertEquals( "El apellido es incorrecto", "Marmol", est.darApellido( ) );
                    assertEquals( "El curso es incorrecto", "2", est.darCurso( ) );
                    assertEquals( "El promedio es incorrecto", "3.5", est.darPromedio( ) );
                    assertEquals( "Los comentarios son incorrectos", "Comentarios2", est.darComentarios( ) );
                }
                else
                {
                    fail( "Estudiante inválido!" );
                }
            }

        }
        catch( DatosException e )
        {
            fail( "Debe poder buscar los estudiantes" );
        }
    }

    /**
     * Prueba 2 - Buscar estudiantes estudiantes
     */
    public void testBuscar( )
    {
        setupEscenario2( );
        //
        // Busca todos los estudiantes
        try
        {
            ArrayList<Estudiante> estudiantes = dao.darEstudiantes( );
            assertEquals( "Debe tener 2 estudiantes", 2, estudiantes.size( ) );
            for( int i = 0; i < estudiantes.size( ); i++ )
            {
                Estudiante est = estudiantes.get( i );
                if( "1".equals( est.darCodigo( ) ) )
                {
                    assertEquals( "El nombre es incorrecto", "Pedro", est.darNombre( ) );
                    assertEquals( "El apellido es incorrecto", "Picapiedra", est.darApellido( ) );
                    assertEquals( "El curso es incorrecto", "1", est.darCurso( ) );
                    assertEquals( "El promedio es incorrecto", "2.5", est.darPromedio( ) );
                    assertEquals( "Los comentarios son incorrectos", "Comentarios1", est.darComentarios( ) );
                }
                else if( "2".equals( est.darCodigo( ) ) )
                {
                    assertEquals( "El nombre es incorrecto", "Pablo", est.darNombre( ) );
                    assertEquals( "El apellido es incorrecto", "Marmol", est.darApellido( ) );
                    assertEquals( "El curso es incorrecto", "2", est.darCurso( ) );
                    assertEquals( "El promedio es incorrecto", "3.5", est.darPromedio( ) );
                    assertEquals( "Los comentarios son incorrectos", "Comentarios2", est.darComentarios( ) );
                }
                else
                {
                    fail( "Estudiante inválido!" );
                }
            }

        }
        catch( DatosException e )
        {
            fail( "Debe poder buscar los estudiantes" );
        }
        //
        // Busca por curso
        try
        {
            ArrayList<Estudiante> estudiantes = dao.darEstudiantes( "CURSO", "1" );
            assertEquals( "Debe tener 1 estudiantes", 1, estudiantes.size( ) );
            for( int i = 0; i < estudiantes.size( ); i++ )
            {
                Estudiante est = estudiantes.get( i );
                if( "1".equals( est.darCodigo( ) ) )
                {
                    assertEquals( "El nombre es incorrecto", "Pedro", est.darNombre( ) );
                    assertEquals( "El apellido es incorrecto", "Picapiedra", est.darApellido( ) );
                    assertEquals( "El curso es incorrecto", "1", est.darCurso( ) );
                    assertEquals( "El promedio es incorrecto", "2.5", est.darPromedio( ) );
                    assertEquals( "Los comentarios son incorrectos", "Comentarios1", est.darComentarios( ) );
                }
                else
                {
                    fail( "Estudiante inválido!" );
                }
            }

        }
        catch( DatosException e )
        {
            fail( "Debe poder buscar los estudiantes" );
        }
        //
        // Busca por código los estudiantes
        try
        {
            ArrayList<Estudiante> estudiantes = dao.darEstudiantes( "CODIGO", "2" );
            assertEquals( "Debe tener 1 estudiantes", 1, estudiantes.size( ) );
            for( int i = 0; i < estudiantes.size( ); i++ )
            {
                Estudiante est = estudiantes.get( i );
                if( "2".equals( est.darCodigo( ) ) )
                {
                    assertEquals( "El nombre es incorrecto", "Pablo", est.darNombre( ) );
                    assertEquals( "El apellido es incorrecto", "Marmol", est.darApellido( ) );
                    assertEquals( "El curso es incorrecto", "2", est.darCurso( ) );
                    assertEquals( "El promedio es incorrecto", "3.5", est.darPromedio( ) );
                    assertEquals( "Los comentarios son incorrectos", "Comentarios2", est.darComentarios( ) );
                }
                else
                {
                    fail( "Estudiante inválido!" );
                }
            }

        }
        catch( DatosException e )
        {
            fail( "Debe poder buscar los estudiantes" );
        }
    }

    /**
     * Prueba 3 - Modificar estudiantes estudiantes
     */
    public void testModificar( )
    {
        setupEscenario2( );
        //
        // Modifica el estudiante
        Estudiante est1 = new Estudiante( "1", "PedroM", "PicapiedraM", "1M", "2.5M", "Comentarios1M" );
        try
        {
            dao.modificarEstudiante( est1 );
            ArrayList<Estudiante> estudiantes = dao.darEstudiantes( "CODIGO", "1" );
            assertEquals( "Debe tener 1 estudiantes", 1, estudiantes.size( ) );
            Estudiante est = estudiantes.get( 0 );
            assertEquals( "El código es incorrecto", "1", est.darCodigo( ) );
            assertEquals( "El nombre es incorrecto", "PedroM", est.darNombre( ) );
            assertEquals( "El apellido es incorrecto", "PicapiedraM", est.darApellido( ) );
            assertEquals( "El curso es incorrecto", "1M", est.darCurso( ) );
            assertEquals( "El promedio es incorrecto", "2.5M", est.darPromedio( ) );
            assertEquals( "Los comentarios son incorrectos", "Comentarios1M", est.darComentarios( ) );
        }
        catch( DatosException e )
        {
            fail( "Debe poder modificar el estudiante" );
        }
        //
        // Intenta modificar un estudiante que no existe
        Estudiante est2 = new Estudiante( "3", "PedroM", "PicapiedraM", "1M", "2.5M", "Comentarios1M" );
        try
        {
            dao.modificarEstudiante( est2 );
            fail( "No debe poder modificar el estudiante si no existe" );
        }
        catch( DatosException e )
        {
            //
            // OK!
        }
    }

    /**
     * Prueba 4 - Eliminar estudiantes
     */
    public void testEliminar( )
    {
        setupEscenario2( );
        //
        // Elimina un estudiante
        try
        {
            dao.eliminarEstudiante( "1" );
            //
            // Verifica la eliminación
            assertEquals( "Solo debe tener 1 estudiante", 1, dao.darEstudiantes( ).size( ) );
        }
        catch( DatosException e )
        {
            fail( "Debe poder eliminar el estudiante: " + e.getMessage( ) );
        }
        //
        // Intenta eliminar un estudiante que no existe
        try
        {
            dao.eliminarEstudiante( "3" );
            fail( "No debe poder eliminar un estudiante que no existe" );
        }
        catch( DatosException e )
        {
            //
            // OK!
        }
    }
}
