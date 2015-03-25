/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConsultaDAO.java,v 1.10 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 *
 * Ejercicio: VideoAndes
 * Autor: Juan Diego Toro - 1-Marzo-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package conexionBDdao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import Value.Ciudad;
import Value.Pago;
import Value.Reserva;
import Value.Usuario;
import Value.Vuelo;





/**
 * Clase ConsultaDAO, encargada de hacer las consultas básicas para el cliente
 */
public class ConsultaDAO {

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
	public ConsultaDAO() 
	{		
		
	}
	
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
    	System.out.println(nombrep);
    	System.out.println(identificacionp);
    	usuarioactual=user;
	 }
    // ---------------------------------------------------
    // Métodos asociados a los casos de uso: Consulta
    // ---------------------------------------------------
    /**
     * Método que se encarga de realizar la consulta en la base de datos
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
    public void darVuelosDefault() throws Exception
    {
    	PreparedStatement prepStmt = null;
    	
    	
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT * FROM ciudad");
			
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next()){
				System.out.println("cambio buildver2");
				String titVid = rs.getString("nombre");
				
				
			System.out.println(titVid);
							
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		
    }
    
    public ArrayList<Ciudad> consultarCiudades() throws Exception {
		PreparedStatement prepStmt = null;
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT DISTINCT id_origen FROM vuelo ORDER BY id_origen");
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next()) {
				Ciudad tmpCiudad = new Ciudad(rs.getString("id_origen"));
				ciudades.add(tmpCiudad);
			}
		} catch (SQLException e) {
			e.printStackTrace();			
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		return ciudades;
    }
    /**
     * Método que se encarga de consultar vuelos en la base de datos
     * @param origen la ciudad de origen, consulta tambien la ciudades que contengan esta cadena de caracteres
     * @param destino la ciudad de llegada, consulta tambien la ciudades que contengan esta cadena de caracteres
     * @param fecha fecha dle vuelo con formato dd/mm/yyyy
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
	public ArrayList<Vuelo> consultarVuelos(String origen, String destino, String fecha) throws Exception {
       
		PreparedStatement prepStmt = null;
		ArrayList<Vuelo> vuelos=new ArrayList<Vuelo>();
    	
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT * FROM vuelo WHERE id_origen='"+origen+"' AND id_destino='"+destino+"'");
			System.out.println("inicia consulta");
			ResultSet rs = prepStmt.executeQuery();
			System.out.println("termina consulta");
			
			while(rs.next()){
				System.out.println("agregando vuelos a la consulta");
				String fechap = rs.getString("fecha");
				String origenpp = rs.getString("id_origen");
				String destinopp = rs.getString("id_destino");
				int totales = Integer.parseInt(rs.getString("totales"));
				int disponibles = Integer.parseInt(rs.getString("disponibles"));
				int id=Integer.parseInt(rs.getString("id"));
				Ciudad origenp=new Ciudad(origenpp);
				Ciudad destinop=new Ciudad(destinopp);
				System.out.println(fechap+origenpp+destinopp+totales+disponibles+id);
				Vuelo vuelo=new Vuelo(fechap, origenp, destinop, totales, disponibles,id);
				
			    vuelos.add(vuelo);
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		return vuelos;
	}

	
	 /**
     * Método que se encarga de realizar la consulta en la base de datos
	 * @throws Exception 
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
	@SuppressWarnings("deprecation")
	public String reservarVuelo(int identificadorvuelo, int numPosiciones) throws Exception {
		
		PreparedStatement prepStmt = null;
		int id_reserva=-1;
		
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT * FROM vuelo where id="+identificadorvuelo);
			
			ResultSet rs = prepStmt.executeQuery();
			//guardamos el vuelo consultado
			if(rs.next()){
				
				String fechap = rs.getString("fecha");
				System.out.println("reservando vuelos");
				String origenpp = rs.getString("id_origen");
				String destinopp = rs.getString("id_destino");
				int totales = Integer.parseInt(rs.getString("totales"));
				int disponibles = Integer.parseInt(rs.getString("disponibles"));
				int id=Integer.parseInt(rs.getString("id"));
				Ciudad origenp=new Ciudad(origenpp);
				Ciudad destinop=new Ciudad(destinopp);
				
				Vuelo vuelo=new Vuelo(fechap, origenp, destinop, totales, disponibles,id);
				// se valida la reserva
				int totalresult=vuelo.getSillasDisponibles()-numPosiciones;
				if(totalresult<0){
					return "-1";
				}
				else{
					vuelo.setSillasDisponibles(totalresult);
				}
				//se crea la reserva
				Date fec=new Date();
				String fecha= fec.getDate()+"/"+fec.getMonth()+"/"+ fec.getYear();
				Reserva reserva= new Reserva(usuarioactual, fecha, vuelo, numPosiciones, null, 0,1);
			   
				System.out.println(fecha);
				
		 	    
			  
				//valida que el usuario ya exista
				 System.out.println(usuarioactual.getIdentificacion());
				PreparedStatement prepStmt3 = conexion.prepareStatement("SELECT id FROM usuario where identificacion='"+usuarioactual.getIdentificacion()+"'");
						
				ResultSet rs3 = prepStmt3.executeQuery();
				//TODO agregar rs.next a todos
				String iduser="0";
				if(rs3.next()){
			      iduser=rs3.getString("id");
			      System.out.println(iduser+"existe");
			    System.out.println("trueeeee");  
				}
				else{
				//si no existe se crea y se agrega a la basees de datos
				System.out.println("false");
					PreparedStatement prepStmt31 = conexion.prepareStatement("INSERT INTO usuario (id,nombre,identificacion) VALUES((select count(id) from usuario)+1,'"+usuarioactual.getNombre()+"','"+usuarioactual.getIdentificacion()+"')");
							
					 prepStmt31.executeQuery();
				
				PreparedStatement prepStmt32 = conexion.prepareStatement("SELECT id FROM usuario where identificacion='"+usuarioactual.getIdentificacion()+"'");
				
				ResultSet rs32 = prepStmt32.executeQuery();
				if(rs32.next()){
				iduser=rs32.getString("id");
				 System.out.println(iduser+" no existe");
				}}
				System.out.println("se esta insertando");
				PreparedStatement prepStmt4 = conexion.prepareStatement("INSERT INTO reserva (id,id_usuario,id_vuelo,fecha,posiciones,estado) VALUES((select count(id) from reserva)+1,"+iduser+","+reserva.getVuelo().getId()+",'"+reserva.getFechaReserva()+"',"+reserva.getNumPosiciones()+","+reserva.getEstadoReserva()+")");
				
				prepStmt4.executeQuery();
				
                PreparedStatement prepStmt5 = conexion.prepareStatement("SELECT id FROM reserva where id=(select count(id)from reserva)");
				
				ResultSet rs5 = prepStmt5.executeQuery();
				if(rs5.next()){
				id_reserva=Integer.parseInt(rs5.getString("id"));
				reserva.setid(id_reserva);
				 //actualiza el vuelo descontando las sillas disponibles
			    PreparedStatement prepStmt2 = conexion.prepareStatement("UPDATE vuelo SET disponibles="+vuelo.getSillasDisponibles()+"WHERE id="+identificadorvuelo);
				
				 prepStmt2.executeQuery();
				usuarioactual.setReservas(reserva);
			    for (int i = 0; i < usuarios.size(); i++) {
		    		
		    		if( usuarioactual.equals(usuarios.get(i))){
		    			usuarios.remove(i);
		    			usuarios.add(usuarioactual);
		    		}
		    		else{
		    			usuarios.add(usuarioactual);
		    		}
			    }
			    
				
			}
				System.out.println(id_reserva);
				return id_reserva+"";}
			return "-1";
				
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		
	}
	 /**
     * Método que se encarga de realizar la consulta en la base de datos
	 * @throws Exception 
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     * @return retorna true si se cancelo la reserva o false sino se pudo debido a que ya estaba pagada
     */
	public boolean cancelarReserva(String identificadorreserva) throws Exception {
		PreparedStatement prepStmt = null;
		int id_reserva=-1;
		
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT * FROM reserva where id="+Integer.parseInt(identificadorreserva));
			
			ResultSet rs = prepStmt.executeQuery();
			//selecionar las posiciones de la reserva
			if(rs.next()){
				System.out.println("eliminando reserva");
				int posiciones = Integer.parseInt(rs.getString("posiciones"));
				int idvuelo=Integer.parseInt(rs.getString("id_vuelo"));
				int idreserva=Integer.parseInt(rs.getString("id"));
				int estado=Integer.parseInt(rs.getString("estado"));
				if(estado==Reserva.VIGENTE){
				//se aumenta las sillas disponibles en los vuelos
				PreparedStatement prepStmt2 = conexion.prepareStatement("SELECT * FROM vuelo where id="+idvuelo);
				ResultSet rs2 = prepStmt2.executeQuery();
				if(rs2.next()){
				int disponibles = Integer.parseInt(rs2.getString("disponibles"));
				int id=Integer.parseInt(rs2.getString("id"));
				int totalresult=disponibles+posiciones;
				
			    //se elimina la reserva del usuario
			    
			    PreparedStatement prepStmt4 = conexion.prepareStatement("DELETE reserva WHERE id="+idreserva);
			    prepStmt4.executeQuery();
			    
			    for (int i = 0; i < usuarioactual.getReservas().size(); i++) {
			    	
			    	Reserva re=usuarioactual.getReservas().get(i);
			    	if(re.getid()==id_reserva){
			    		
			    		usuarioactual.getReservas().remove(i);
			    		
			    	}
					
				}

			    PreparedStatement prepStmt5 = conexion.prepareStatement("DELETE reserva WHERE id="+idreserva);
			    prepStmt5.executeQuery();
			    PreparedStatement prepStmt3 = conexion.prepareStatement("UPDATE vuelo SET disponibles="+totalresult+"WHERE id="+id);
			    prepStmt3.executeQuery();
			    
			    System.out.println(true);
			    return true;
				}}}
				System.out.println(false);
				return false;
			    
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
			
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
					
				}
			}
			closeConnection(conexion);
		}		
		
	}
	 /**
     * Método que se encarga de comprar un vuelo con reserva
     * @param identificacionreserva la reserva a pagar
     * @param infoPago informacion del pago, numero de la cuenta, la cuenta gratis es 93052308842
	 * @throws Exception 
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
	@SuppressWarnings("deprecation")
	public boolean comprarVueloConReserva(String identificacionreserva, String infoPago) throws Exception {
		
PreparedStatement prepStmt = null;
    	
    	
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			if(infoPago.equals("93052308842")){
				
			prepStmt = conexion.prepareStatement("SELECT * FROM reserva where id="+Integer.parseInt(identificacionreserva));
			
			ResultSet rs = prepStmt.executeQuery();
			if(rs.next()){
			System.out.println("comprar vuelo");
				int estado = Integer.parseInt(rs.getString("estado"));
				int id = Integer.parseInt(rs.getString("id"));
				int posiciones=Integer.parseInt(rs.getString("posiciones"));
				
				Date fec=new Date();
				String fecha= fec.getDate()+"/"+fec.getMonth()+"/"+ fec.getYear();
				Pago pago=new Pago(fecha,( VALORVUELO * posiciones), id);
				
				if(estado==Reserva.VIGENTE){
				PreparedStatement prepStmt2 = conexion.prepareStatement("UPDATE reserva SET estado="+Reserva.PAGADA+"WHERE id="+id);
				prepStmt2.executeQuery();
				
                PreparedStatement prepStmt4 = conexion.prepareStatement("INSERT INTO pago (id_reserva,pago,fecha_pago) VALUES("+id+","+pago.getValor()+",'"+pago.getFechaPago()+"')");
				
				prepStmt4.executeQuery();
				return true;
				}}
				return false;
			}
			return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		
		
		
	}
	
	/**
	* Método encargado de recuperar las reservas de un usuario específico
	* @param nombreUsuario El nombre del usuario
	* @return
	*/
	public ArrayList<Reserva> consultarReservas() throws Exception {
		String nombreUsuario = usuarioactual.getNombre();
		
	PreparedStatement prepStmt = null;
	ArrayList<Reserva> reservas = new ArrayList<Reserva>();

	try {
	establecerConexion(cadenaConexion, usuario, clave);
	PreparedStatement tmpUsuario = conexion.prepareStatement("SELECT id FROM usuario WHERE nombre='"+nombreUsuario+"'");
	ResultSet tmpRs = tmpUsuario.executeQuery();
    
	if(tmpRs.next()){

	prepStmt = conexion.prepareStatement("SELECT * FROM reserva WHERE id_usuario='"+tmpRs.getInt("id")+"'");
	ResultSet rs = prepStmt.executeQuery();

	while(rs.next()) {
		
		PreparedStatement prepStmt3 = conexion.prepareStatement("SELECT * FROM vuelo WHERE id="+rs.getString("id_vuelo"));
		System.out.println("inicia consulta");
		ResultSet rs3 = prepStmt3.executeQuery();
		System.out.println("termina consulta");
		
		if(rs3.next()){
			System.out.println("agregando vuelos a la consulta");
			String fechap = rs3.getString("fecha");
			String origenpp = rs3.getString("id_origen");
			String destinopp = rs3.getString("id_destino");
			int totales = Integer.parseInt(rs3.getString("totales"));
			int disponibles = Integer.parseInt(rs3.getString("disponibles"));
			int id=Integer.parseInt(rs3.getString("id"));
			Ciudad origenp=new Ciudad(origenpp);
			Ciudad destinop=new Ciudad(destinopp);
			System.out.println(fechap+origenpp+destinopp+totales+disponibles+id);
			Vuelo vuelo=new Vuelo(fechap, origenp, destinop, totales, disponibles,id);
			
			 Reserva tmpReserva = new Reserva(usuarioactual, rs.getString("fecha"), vuelo, rs.getInt("posiciones"), null, rs.getInt("estado"),rs.getInt("id"));
			 
			 reservas.add(tmpReserva);
		}
		
		
	}
	}
	}
	
	
	catch (SQLException e) {
	e.printStackTrace();	
	throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
	} finally {
	if (prepStmt != null) {
	try {
	prepStmt.close();
	} catch (SQLException exception) {
	throw new Exception("ERROR: ConsultaDAO: loadRow() = cerrando una conexión.");
	}
	}
	closeConnection(conexion);
	}	
	return reservas;
	}
	
	
    
}
