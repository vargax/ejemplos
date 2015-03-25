package conexionBDdao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Value.Usuario;

public class ConsultaDaoTest {
	//----------------------------------------------------
	//Constantes
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexión.
	 */
	private static final String ARCHIVO_CONEXION = "/conexion.properties";
	
	private static final double VALORVUELO=300000;
	//----------------------------------------------------
	//Atributos
	//----------------------------------------------------
	/**
	/**
	 * lista usuarios recientes conectados
	 */
	private ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
	
	
	
	

	//----------------------------------------------------
	//Consultas
	//----------------------------------------------------
	
//	/**
//	 * Consulta que devuelve isan, titulo, y año de los videos en orden alfabetico
//	 */
//	private static final String consultaVideosDefault="SELECT *, FROM "+tablaVideo;
	

	//----------------------------------------------------
	//Atributos
	//----------------------------------------------------
	/**
	 * conexion con la base de datos
	 */
	public Connection conexion;
	
	/**
	 * nombre del usuario para conectarse a la base de datos.
	 */
	private String usuario;
	
	/**
	 * clave de conexión a la base de datos.
	 */
	private String clave;
	
	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;

	private Usuario usuarioactual;
	
	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 */
	
	// -------------------------------------------------
    // Métodos
    // -------------------------------------------------
    
	/**
	 * obtiene ls datos necesarios para establecer una conexion
	 * Los datos se obtienen a partir de un archivo properties.
	 * @param path ruta donde se encuentra el archivo properties.
	 */
	public void inicializar(String path)
	{
		try
		{
			File arch= new File(path+ARCHIVO_CONEXION);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream( arch );

	        prop.load( in );
	        in.close( );

			cadenaConexion = prop.getProperty("url");	// El url, el usuario y passwd deben estar en un archivo de propiedades.
												// url: "jdbc:oracle:thin:@chie.uniandes.edu.co:1521:chie10";
			usuario = prop.getProperty("usuario");	// "s2501aXX";
			clave = prop.getProperty("clave");	// "c2501XX";
			final String driver = prop.getProperty("driver");
			Class.forName(driver);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * Método que se encarga de crear la conexión con el Driver Manager
	 * a partir de los parametros recibidos.
	 * @param url direccion url de la base de datos a la cual se desea conectar
	 * @param usuario nombre del usuario que se va a conectar a la base de datos
	 * @param clave clave de acceso a la base de datos
	 * @throws SQLException si ocurre un error generando la conexión con la base de datos.
	 */
    private void establecerConexion(String url, String usuario, String clave) throws SQLException
    {
    	try
        {
			conexion = DriverManager.getConnection(url,usuario,clave);
        }
        catch( SQLException exception )
        {
            throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexi—n." );
        }
    }
    
    /**
 	 *Cierra la conexión activa a la base de datos. Además, con=null.
     * @param con objeto de conexión a la base de datos
     * @throws SistemaCinesException Si se presentan errores de conexión
     */
    public void closeConnection(Connection connection) throws Exception {        
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexión.");
		}
    } 
    
    public void inicializarUsuario(String nombrep,String identificacionp){
    	Usuario user= new Usuario(nombrep, identificacionp);
    	
    	usuarioactual=user;
	 }
    
    
    // ---------------------------------------------------
    // Métodos asociados a pruebas de la bases de datos
    // ---------------------------------------------------
	
      public boolean cargarUsuario() throws Exception{
		
    		PreparedStatement prepStmt = null;
        	
        	
    		try {
    			establecerConexion(cadenaConexion, usuario, clave);
    			PreparedStatement prepStmt31 = conexion.prepareStatement("INSERT INTO usuario (id,nombre,identificacion) VALUES((select count(id) from usuario)+1,'ricardo','930523112')");
				
				 prepStmt31.executeQuery();
			
			  return true;
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    			return false;
    		}finally 
    		{
    			if (prepStmt != null) 
    			{
    				try {
    					prepStmt.close();
    				} catch (SQLException exception) {
    					exception.printStackTrace();
    					return false;
    				}
    			}
    			closeConnection(conexion);
    		}		
	  }
      
      public boolean cargarUsuariodoble() throws Exception{
  		
  		PreparedStatement prepStmt = null;
      	
      	
  		try {
  			establecerConexion(cadenaConexion, usuario, clave);
  			PreparedStatement prepStmt31 = conexion.prepareStatement("INSERT INTO usuario (id,nombre,identificacion) VALUES((select count(id) from usuario),'sebastian','930423111')");
				
				 prepStmt31.executeQuery();
			
			    PreparedStatement prepStmt32 = conexion.prepareStatement("SELECT id FROM usuario where identificacion='"+usuarioactual.getIdentificacion()+"'");
			
			ResultSet rs32 = prepStmt32.executeQuery();
			if(rs32.next()){
			String iduser=rs32.getString("id");
			 System.out.println(iduser+" no existe");
			 return true;
			}
			else{
		    return false;
			}
  		
  		} catch (SQLException e) {
  			e.printStackTrace();
  			return false;
  		}finally 
  		{
  			if (prepStmt != null) 
  			{
  				try {
  					prepStmt.close();
  				} catch (SQLException exception) {
  					exception.printStackTrace();
  					return false;
  				}
  			}
  			closeConnection(conexion);
  		}		
	  }
	
      public boolean cargarCiudad() throws Exception{
  		
  		PreparedStatement prepStmt = null;
      	
      	
  		try {
  			establecerConexion(cadenaConexion, usuario, clave);
  			PreparedStatement prepStmt31 = conexion.prepareStatement("INSERT INTO ciudad (id,nombre) VALUES((select count(id) from usuario)+1,'ciudadprueba1')");
				
				 prepStmt31.executeQuery();
			
			 return true;
  		
  		} catch (SQLException e) {
  			e.printStackTrace();
  			return false;
  		}finally 
  		{
  			if (prepStmt != null) 
  			{
  				try {
  					prepStmt.close();
  				} catch (SQLException exception) {
  					exception.printStackTrace();
  					return false;
  				}
  			}
  			closeConnection(conexion);
  		}		
	  }
      public boolean cargarCiudaddoble() throws Exception{
    		
    		PreparedStatement prepStmt = null;
        	
        	
    		try {
    			establecerConexion(cadenaConexion, usuario, clave);
    			PreparedStatement prepStmt31 = conexion.prepareStatement("INSERT INTO usuario (id,nombre) VALUES((select count(id) from usuario),'ciudadprueba1')");
  				
  				 prepStmt31.executeQuery();
  			
  			 
  			 return true;
  			
  		
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    			return false;
    		}finally 
    		{
    			if (prepStmt != null) 
    			{
    				try {
    					prepStmt.close();
    				} catch (SQLException exception) {
    					exception.printStackTrace();
    					return false;
    				}
    			}
    			closeConnection(conexion);
    		}		
  	  }
	
      public boolean cargarVuelo() throws Exception{
    		
    		PreparedStatement prepStmt = null;
        	
        	
    		try {
    			establecerConexion(cadenaConexion, usuario, clave);
    			PreparedStatement prepStmt31 = conexion.prepareStatement("INSERT INTO vuelo (id,id_origen,id_destino,fecha,disponibles,totales) VALUES((select count(id) from usuario)+1,'kabul','tirana','5/12/2013',200,200)");
  				
  				 prepStmt31.executeQuery();
  			
  			 return true;
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    			return false;
    		}finally 
    		{
    			if (prepStmt != null) 
    			{
    				try {
    					prepStmt.close();
    				} catch (SQLException exception) {
    					exception.printStackTrace();
    					return false;    				}
    			}
    			closeConnection(conexion);
    		}		
  	  }
        public boolean cargarVuelodoble() throws Exception{
      		
      		PreparedStatement prepStmt = null;
          	
          	
      		try {
      			establecerConexion(cadenaConexion, usuario, clave);
      			PreparedStatement prepStmt31 = conexion.prepareStatement("INSERT INTO vuelo (id,id_origen,id_destino,fecha,disponibles,totales) VALUES((select count(id) from usuario),'kabul','Tirana',5/12/2013,200,200)");
    				
    				 prepStmt31.executeQuery();
    			
    			 
    			 return true;
    			
    		
      		
      		} catch (SQLException e) {
      			e.printStackTrace();
      			return false;
      		}finally 
      		{
      			if (prepStmt != null) 
      			{
      				try {
      					prepStmt.close();
      				} catch (SQLException exception) {
      					exception.printStackTrace();
      					return false;
      				}
      			}
      			closeConnection(conexion);
      		}		
    	  }
  	
	
}
