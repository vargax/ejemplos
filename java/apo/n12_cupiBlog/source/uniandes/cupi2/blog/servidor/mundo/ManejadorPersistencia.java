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

package uniandes.cupi2.blog.servidor.mundo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import uniandes.cupi2.blog.comun.Articulo;
import uniandes.cupi2.blog.comun.Comentario;
import uniandes.cupi2.blog.comun.InfoUsuario;
import uniandes.cupi2.blog.excepciones.CupiBlogPersistenciaException;

/**
 * Clase que se encarga del manejo de la persistencia
 */
public class ManejadorPersistencia
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Conexión a la base de datos
     */
    private Connection conexion;

    /**
     * Conjunto de propiedades que contienen la configuración de la aplicación
     */
    private Properties configuracion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    public ManejadorPersistencia(Properties propiedades) {
    	configuracion = propiedades;
    	File data = new File( configuracion.getProperty( "admin.db.path" ) );
    	System.setProperty( "derby.system.home", data.getAbsolutePath( ) );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Conecta el administrador a la base de datos
     * @throws Exception 
     */
    public void conectarDB() throws Exception {
    	String driver = configuracion.getProperty( "admin.db.driver" );
        Class.forName( driver ).newInstance( );

        String url = configuracion.getProperty( "admin.db.url" );
        conexion = DriverManager.getConnection( url );
        verificarInvariante();
    }
    /**
     * Desconecta el administrador de la base de datos
     */
    public void desconectarBD( ) throws SQLException { 
        conexion.close( );
        String down = configuracion.getProperty( "admin.db.shutdown" );
        try {
            DriverManager.getConnection( down );
        } 
        catch( SQLException e ) {
        }
    }
    /**
     * Crea las tablas de la base de datos, si estas no existen.
     */
    public void crearTablas() throws SQLException {
    	Statement s = conexion.createStatement();
    	try {
    		System.out.println("PERSISTENCIA: Mirando si la base de datos existe...");
    		s.executeQuery("select * from usuarios where 1=2");
    		System.out.println("PERSISTENCIA: La base de datos existe...");
    	} catch (SQLException e){
    		System.out.println("PERSISTENCIA: La base de datos no existe: Entro a crear tablas...");
    		
    		s.execute("create table usuarios(usuario varchar(32), nombre varchar(32), apellidos varchar(32), " +
    				"articulos_publicados int, comentarios_publicados int, primary key (usuario))");
    		System.out.println("PERSISTENCIA: Tabla 'usuarios' creada...");
    		
    		s.execute("create table articulos(usuario varchar(32), titulo varchar(32), categoria varchar(32), " +
    				"contenido long varchar, fecha_publicacion varchar(19))");
    		System.out.println("PERSISTENCIA: Tabla 'articulos' creada...");
    		
    		s.execute("create table comentarios(usuario varchar(32), titulo_articulo varchar(32), " +
    				"contenido long varchar, fecha_publicacion varchar(19))");
    		System.out.println("PERSISTENCIA: Tabla 'comentarios' creada...");
    		System.out.println("PERSISTENCIA: Finaliza la creación de las tablas...");
    	}
    	s.close();
    	verificarInvariante();
    }
    /**
     * Registra un nuevo usuario en el sistema, si este no existe.
     */
    public InfoUsuario registrarUsuario(String nombreUsuario, String nombresPila, String apellidos) throws SQLException, CupiBlogPersistenciaException {
    	System.out.println("PERSISTENCIA: Intentando registro de "+nombreUsuario);
    	Statement s = conexion.createStatement();
    	String consulta = "insert into usuarios(usuario, nombre, apellidos, articulos_publicados, comentarios_publicados) values ('"
    			+nombreUsuario+"','"+nombresPila+"','"+apellidos+"',0,0)";
    	try {
    		s.executeUpdate(consulta);
    		System.out.println("PERSISTENCIA: Registro exitoso...");
    	} catch (SQLException e) {
    		System.out.println("PERSISTENCIA: Registro fallido <"+e.getMessage()+">");
    		throw new CupiBlogPersistenciaException("Usuario Existe");
    	}
    	s.close();
    	return loginUsuario(nombreUsuario);
    }
    /**
     * Realiza el login de un usuario.
     * @throws SQLException 
     * @throws CupiBlogPersistenciaException 
     */
    public InfoUsuario loginUsuario(String nombreUsuario) throws SQLException, CupiBlogPersistenciaException {
    	System.out.println("PERSISTENCIA: Intentando login de "+nombreUsuario);
    	Statement s = conexion.createStatement();
    	String consulta = "select usuario, nombre, apellidos from usuarios where usuario = '"+nombreUsuario+"'";
    	ResultSet r = s.executeQuery(consulta);
    	
    	if (r.next()) {
    		InfoUsuario respuesta = new InfoUsuario(r.getString(1), r.getString(2), r.getString(3));
    		s.close();
    		return respuesta;
    	}
    	else {
    		s.close();
    		throw new CupiBlogPersistenciaException("Usuario no existe");
    	}
    }
    /**
     * Devuelve la lista de los articulos disponibles
     * @throws ParseException 
     */
    public ArrayList<Articulo> listarArticulos() throws SQLException, ParseException {
    	Statement s = conexion.createStatement();
    	String consulta = "select * from articulos";
    	ResultSet r = s.executeQuery(consulta);
    	ArrayList<Articulo> respuesta = new ArrayList<Articulo>();
    	
    	while (r.next()) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm"); 
    		Date fecha = sdf.parse(r.getString(5));
    		respuesta.add(new Articulo(r.getString(1), r.getString(2), r.getString(3), r.getString(4), fecha));
    	}
    	s.close();
    	return respuesta;
    }
    /**
     * Devuelve la lista de los articulos publicados por un usuario específico
     * @throws SQLException 
     * @throws ParseException 
     */
    public ArrayList<Articulo> listarArticulosUsuario(String nombreUsuario) throws SQLException, ParseException {
    	System.out.println("PERSISTENCIA: Listando artículos para el usuario "+nombreUsuario);
    	Statement s = conexion.createStatement();
    	String consulta = "select * from articulos where usuario ='"+nombreUsuario+"'";
    	ResultSet r = s.executeQuery(consulta);
    	ArrayList<Articulo> respuesta = new ArrayList<Articulo>();
    	
    	while (r.next()) {
    		System.out.println("PERSISTENCIA: Se encontraron artículos para el usuario...");
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm"); 
    		Date fecha = sdf.parse(r.getString(5));
    		respuesta.add(new Articulo(r.getString(1), r.getString(2), r.getString(3), r.getString(4), fecha));
    	}
    	s.close();
    	return respuesta;
    }
    /**
     * Devuelve los comentarios de un artículo
     * @throws CupiBlogPersistenciaException 
     * @throws ParseException 
     */
    public ArrayList<Comentario> listarComentarios(String tituloArticulo) throws SQLException, CupiBlogPersistenciaException, ParseException {
    	Statement s = conexion.createStatement();
    	Articulo articulo;
    	
    	String consulta = "select * from articulos where titulo ='"+tituloArticulo+"'";
    	ResultSet r = s.executeQuery(consulta);
    	
    	if (r.next()) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm"); 
    		Date fecha = sdf.parse(r.getString(5));
    		articulo = new Articulo(r.getString(1), r.getString(2), r.getString(3), r.getString(4), fecha);
    	} else throw new CupiBlogPersistenciaException("Artículo no encontrado");
    	
    	consulta = "select * from comentarios where titulo_articulo = '"+ tituloArticulo+"'";
    	r = s.executeQuery(consulta);
    	
    	ArrayList<Comentario> respuesta = new ArrayList<Comentario>();
    	while (r.next()) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm"); 
    		Date fecha = sdf.parse(r.getString(4));
    		respuesta.add(new Comentario(r.getString(1), articulo, r.getString(3), fecha));
    	}
    	s.close();
    	return respuesta;
    }
    /**
     * Publica un artículo
     * @throws ParseException 
     */
    public ArrayList<Articulo> publicarArticulo(String nombreUsuario, String tituloArticulo, String categoriaArticulo, String contenidoArticulo) throws SQLException, ParseException {
    	Statement s = conexion.createStatement();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");    	
    	String fechaPublicacion = sdf.format(new Date(System.currentTimeMillis()));
    	String consulta = "insert into articulos(usuario, titulo, categoria, contenido, fecha_publicacion) values ('"+nombreUsuario+"','"
    			+tituloArticulo+"','"+categoriaArticulo+"','"+contenidoArticulo+"','"+fechaPublicacion+"')";
    	s.executeUpdate(consulta);
    	consulta = "update usuarios set articulos_publicados = articulos_publicados + 1 where usuario ='"+nombreUsuario+"'";
    	s.executeUpdate(consulta);
    	
    	s.close();
    	return listarArticulos();
    }
    /**
     * Publica un comentario
     * @throws ParseException 
     * @throws CupiBlogPersistenciaException 
     */
    public ArrayList<Comentario> publicarComentario(String nombreUsuario, String nombreArticulo, String contenidoComentario) throws SQLException, CupiBlogPersistenciaException, ParseException {
    	Statement s = conexion.createStatement();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");    	
    	String fechaPublicacion = sdf.format(new Date(System.currentTimeMillis()));
    	String consulta = "insert into comentarios(usuario, titulo_articulo, contenido, fecha_publicacion) values('"+nombreUsuario+"','"
    			+nombreArticulo+"','"+contenidoComentario+"','"+fechaPublicacion+"')";
    	s.executeUpdate(consulta);
    	consulta = "update usuarios set comentarios_publicados = comentarios_publicados + 1 where usuario ='"+nombreUsuario+"'";
    	s.executeUpdate(consulta);
    	s.close();
    	return listarComentarios(nombreArticulo);
    }
    /**
     * Realiza una búsqueda por categoría
     * @throws ParseException 
     */
    public ArrayList<Articulo> buscarPorCategoria(String nombreCategoria) throws SQLException, ParseException {
    	Statement s = conexion.createStatement();
    	String consulta = "select * from articulos where categoria ='"+nombreCategoria+"'";
    	ResultSet r = s.executeQuery(consulta);
    	ArrayList<Articulo> respuesta = new ArrayList<Articulo>();
    	
    	while (r.next()) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm"); 
    		Date fecha = sdf.parse(r.getString(5));
    		respuesta.add(new Articulo(r.getString(1), r.getString(2), r.getString(3), r.getString(4), fecha));
    	}
    	s.close();
    	return respuesta;
    }
    /**
     * Devuelve las estadísticas del usuario
     * @throws SQLException 
     */
    public int[] estadisticasUsuario(String nombreUsuario) throws SQLException {
    	System.out.println("PERSISTENCIA: Solicitando estadísticas para "+nombreUsuario);
    	Statement s = conexion.createStatement();
    	String consulta = "select articulos_publicados, comentarios_publicados from usuarios where usuario='"+nombreUsuario+"'";
    	ResultSet r = s.executeQuery(consulta);
    	int[] respuesta = new int[2];
    	
    	if (r.next()) {
    		System.out.println("PERSISTENCIA: Se han encontrado estadísticas para el usuario:"+r.getInt(1)+" "+r.getInt(2));
    		respuesta[0] = r.getInt(1);	// Articulos publicados
    		respuesta[1] = r.getInt(2);	// Comentarios publicados
    	}
    	s.close();
    	return respuesta;
    }
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b><br>
     * configuracion!=null <br>
     */
    private void verificarInvariante( )
    {
        assert configuracion != null : "Conjunto de propiedades inválidas";
    }

}