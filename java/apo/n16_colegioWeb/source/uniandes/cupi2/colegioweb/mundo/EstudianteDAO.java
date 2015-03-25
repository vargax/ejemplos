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

import uniandes.cupi2.minidbc.mundo.FabricaConexiones;
import uniandes.cupi2.minidbc.mundo.IConexion;
import uniandes.cupi2.minidbc.mundo.IResultado;
import uniandes.cupi2.minidbc.mundo.IStatement;
import uniandes.cupi2.minidbc.mundo.MiniDBCException;

/**
 * Clase encargada de cargar los estudiantes de la base de datos
 */
public class EstudianteDAO
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Conexión a la base de datos
     */
    private IConexion conexion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del DAO
     * @throws DatosException Excepción generada cuando sucede un error al conectarse a la base de datos
     */
    public EstudianteDAO( ) throws DatosException
    {
        try
        {
            FabricaConexiones fabrica = FabricaConexiones.darInstancia( );
            conexion = fabrica.conectar( "oraculo;archivo='C:/temp/prueba.oraculo'" );
        }
        catch( Exception e )
        {
            throw new DatosException( "Error al crear la conexión a la base de datos", e );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cierra la conexión a la base de datos
     * @throws DatosException Excepción de error al cerrar la conexión
     */
    public void cerrar( ) throws DatosException
    {
        try
        {
            conexion.cerrar( );
            conexion = null;
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( "Error al cerrar la conexión", e );
        }
    }

    /**
     * Agrega un estudiante a la base de datos
     * @param estudiante Estudiante a agregar
     * @throws DatosException Excepción de error en las operaciones
     */
    public void agregarEstudiante( Estudiante estudiante ) throws DatosException
    {
        try
        {
            //
            // Crea el statement y el miniSQL de la operación
            IStatement statement = crearStatement( );
            String operacion = "INSERT LLAVE_PRIMARIA='" + estudiante.darCodigo( ) + "', NOMBRE='" + estudiante.darNombre( ) + "', APELLIDO='" + estudiante.darApellido( ) + "', CURSO='" + estudiante.darCurso( ) + "', PROMEDIO='"
                    + estudiante.darPromedio( ) + "', COMENTARIOS='" + estudiante.darComentarios( ) + "'";
            //
            // Realiza la operación
            statement.ejecutarUpdate( operacion );
            //
            // Cierra el statement antes de terminar
            statement.cerrar( );
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( "Error al agregar el estudiante: " + e.getMessage( ), e );
        }
    }

    /**
     * Modifica la información del estudiante especificado <br>
     * <b>pre:</b> El código del estudiante no debe haber cambiado
     * @param estudiante Estudiante con la información actualizada
     * @throws DatosException Excepción de error en la modificación del estudiante
     */
    public void modificarEstudiante( Estudiante estudiante ) throws DatosException
    {
        try
        {
            //
            // Verifica que el estudiante exista
            ArrayList<Estudiante> estudiantes = darEstudiantes( "CODIGO", estudiante.darCodigo( ) );
            if( estudiantes.size( ) == 0 )
            {
                //
                // El estudiante no existe
                throw new DatosException( "El estudiante a modificar no existe" );
            }
            //
            // Crea el statement y el miniSQL de la operación
            IStatement statement = crearStatement( );
            String operacion = "UPDATE NOMBRE='" + estudiante.darNombre( ) + "', APELLIDO='" + estudiante.darApellido( ) + "', CURSO='" + estudiante.darCurso( ) + "', PROMEDIO='" + estudiante.darPromedio( ) + "', COMENTARIOS='"
                    + estudiante.darComentarios( ) + "' WHERE LLAVE_PRIMARIA='" + estudiante.darCodigo( ) + "'";
            //
            // Realiza la operación
            statement.ejecutarUpdate( operacion );
            //
            // Cierra el statement antes de terminar
            statement.cerrar( );
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( "Error al agregar el estudiante: " + e.getMessage( ), e );
        }
    }

    /**
     * Elimina un estudiante de la base de datos
     * @param codigo Código del estudiante
     * @throws DatosException Excepción de error al eliminar el estudiante
     */
    public void eliminarEstudiante( String codigo ) throws DatosException
    {
        try
        {
            //
            // Verifica que el estudiante exista
            ArrayList<Estudiante> estudiantes = darEstudiantes( "CODIGO", codigo );
            if( estudiantes.size( ) == 0 )
            {
                //
                // El estudiante no existe
                throw new DatosException( "El estudiante a eliminar no existe" );
            }
            //
            // Crea el statement y el miniSQL de la operación
            IStatement statement = crearStatement( );
            String operacion = "DELETE WHERE LLAVE_PRIMARIA='" + codigo + "'";
            //
            // Realiza la operación
            statement.ejecutarUpdate( operacion );
            //
            // Cierra el statement antes de terminar
            statement.cerrar( );
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( "Error al agregar el estudiante: " + e.getMessage( ), e );
        }
    }

    /**
     * Devuelve todos los estudiantes de la base de datos
     * @return Todos los estudiantes de la base de datos
     * @throws DatosException Excepción al cargar los estudiantes
     */
    public ArrayList<Estudiante> darEstudiantes( ) throws DatosException
    {
        try
        {
            //
            // Crea el statement y el miniSQL de la operación
            IStatement statement = crearStatement( );
            String operacion = "SELECT *";
            //
            // Realiza la operación
            IResultado resultado = statement.ejecutarConsulta( operacion );
            //
            // Cierra el statement antes de terminar
            statement.cerrar( );
            //
            // Devuelve los estudiantes
            return cargarEstudiantes( resultado );
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( "Error al agregar el estudiante: " + e.getMessage( ), e );
        }
    }

    /**
     * Devuelve todos los estudiantes de la base de datos que cumplen con la condición
     * @param campo Campo por el cual se va a realizar la búsqueda
     * @param valor Valor del campo
     * @return Todos los estudiantes de la base de datos
     * @throws DatosException Excepción al cargar los estudiantes
     */
    public ArrayList<Estudiante> darEstudiantes( String campo, String valor ) throws DatosException
    {
        try
        {
            //
            // Realiza el mapeo del código
            if( "CODIGO".equalsIgnoreCase( campo ) )
            {
                campo = "LLAVE_PRIMARIA";
            }
            //
            // Crea el statement y el miniSQL de la operación
            IStatement statement = crearStatement( );
            String operacion = "SELECT * WHERE " + campo.toUpperCase( ) + "='" + valor + "'";
            //
            // Realiza la operación
            IResultado resultado = statement.ejecutarConsulta( operacion );
            //
            // Cierra el statement antes de terminar
            statement.cerrar( );
            //
            // Devuelve los estudiantes
            return cargarEstudiantes( resultado );
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( "Error al buscar los estudiantes: " + e.getMessage( ), e );
        }
    }

    /**
     * Carga todos los estudiantes del resultado en memoria
     * @param resultado Resultado de la operación de la base de datos
     * @return Estudiantes cargados
     * @throws DatosException Excepción de error al cargar los datos
     */
    private ArrayList<Estudiante> cargarEstudiantes( IResultado resultado ) throws DatosException
    {
        try
        {
            ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>( );
            //
            // Para cada uno de los estudiantes
            while( resultado.siguiente( ) )
            {
                Estudiante estudiante = cargarEstudiante( resultado );
                estudiantes.add( estudiante );
            }
            //
            // Devuelve todos los estudiantes
            return estudiantes;
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( " Error al recorrer los resultados de la base de datos ", e );
        }
    }

    /**
     * Carga el estudiantes en la posición actual del resultado en memoria
     * @param resultado Resultado de la operación de la base de datos
     * @return Estudiante cargado
     * @throws DatosException Excepción de error al cargar los datos
     */
    private Estudiante cargarEstudiante( IResultado resultado ) throws DatosException
    {
        try
        {
            String codigo = resultado.darValorColumna( "LLAVE_PRIMARIA" );
            String nombre = resultado.darValorColumna( "NOMBRE" );
            String apellido = resultado.darValorColumna( "APELLIDO" );
            String curso = resultado.darValorColumna( "CURSO" );
            String promedio = resultado.darValorColumna( "PROMEDIO" );
            String comentarios = resultado.darValorColumna( "COMENTARIOS" );
            return new Estudiante( codigo, nombre, apellido, curso, promedio, comentarios );
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( "Error al cargar un estudiante", e );
        }
    }

    /**
     * Crea un nuevo statement para realizar operaciones sobre la base de datos
     * @return Statement creado
     * @throws DatosException Excepción generada cuando sucede un error al crear el statement
     */
    private IStatement crearStatement( ) throws DatosException
    {
        try
        {
            return conexion.crearStatement( );
        }
        catch( MiniDBCException e )
        {
            throw new DatosException( "Error al crear el statement", e );
        }
    }

}
