
package logicaNegocio;

import java.util.ArrayList;

import Value.Ciudad;
import Value.Reserva;
import Value.Vuelo;

import conexionBDdao.ConsultaDAO;


/**
 * Clase VideoAndes, que representa la fachada de comunicación entre
 * la interfaz y la conexión con la base de datos. Atiende todas
 * las solicitudes.
 */
public class TurismoAlpes 
{
	/**
	 * Conexión con la clase que maneja la base de datos
	 */
	private ConsultaDAO dao;    
    // -----------------------------------------------------------------
    // Singleton
    // -----------------------------------------------------------------
    /**
     * Instancia única de la clase
     */
    private static TurismoAlpes instancia;
    
    /**
     * Devuelve la instancia única de la clase
     * @return Instancia única de la clase
     */
    public static TurismoAlpes darInstancia( )
    {
        if( instancia == null )
        {
            instancia = new TurismoAlpes( );
        }
        return instancia;
    }
	
	/**
	 * contructor de la clase. Inicializa el atributo dao.
	 */
	private TurismoAlpes()
	{
		dao = new ConsultaDAO();
	}
	
	/**
	 * inicializa el dao, dándole la ruta en donde debe encontrar
	 * el archivo properties.
	 * @param ruta ruta donde se encuentra el archivo properties
	 */
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
	public void inicializarUsuario(String nombre, String identificacion){
		dao.inicializarUsuario(nombre, identificacion);
	}
	
    // ---------------------------------------------------
    // Métodos asociados a los casos de uso: Consulta,reserva
    // ---------------------------------------------------
	
	public ArrayList<String> consultarCiudades() throws Exception {
		ArrayList<String> respuesta = new ArrayList<String>();
		ArrayList<Ciudad> ciudades = dao.consultarCiudades();
		for(Ciudad c : ciudades) respuesta.add(c.getNombre());
		return respuesta;
	}
	/**
	 * método que retorna vuelos.
	 * invoca al DAO para obtener los resultados.
	 * @return ArrayList lista vuelos pedidos por el usuario en un vector en orden 
	 * id,origen,destino,fecha,sillasdisponibles y sillas totales.
	 * @throws Exception 
	 * @throws Exception pasa la excepción generada por el DAO
	 */
	public ArrayList<String[]> consultarVuelos(String origen,String destino,String fecha) throws Exception{
		
		ArrayList<String[]> vuelosparseados=new ArrayList<String[]>();
		
		ArrayList<Vuelo> vuelos= dao.consultarVuelos(origen,destino,fecha);
		for (int i = 0; i < vuelos.size(); i++) {
			Vuelo vuelo=vuelos.get(i);
			String [] datos=new String[6];
			datos[0]= vuelo.getId()+"";
			datos[1]= vuelo.getOrigen().getNombre();
			datos[2]= vuelo.getDestino().getNombre();
			datos[3]= vuelo.getFecha()+"";
			datos[4]= vuelo.getSillasDisponibles()+"";
			datos[5]= vuelo.getSillasTotales()+"";
			vuelosparseados.add(datos);
		}
		
		return vuelosparseados;
	}
	/**
	 * Método que devuelve al info de las reservas de un usuario
	 * devuelve id de la reserva
	 * ciudad de origen
	 * ciudad de destino
	 * fecha de la reserva
	 * num de posiciones
	 * @throws Exception 
	 */
	public ArrayList<String[]> consultarReservas() throws Exception {
		ArrayList<String[]> respuesta = new ArrayList<String[]>();
		
		
		ArrayList<Reserva> reserva= dao.consultarReservas();
		for (int i = 0; i < reserva.size(); i++) {
			Reserva reserv=reserva.get(i);
			String [] datos=new String[5];
			datos[0]= reserv.getid()+"";
			datos[1]= reserv.getVuelo().getOrigen().getNombre();
			datos[2]=reserv.getVuelo().getDestino().getNombre();
			datos[3]= reserv.getFechaReserva();
			datos[4]= reserv.getNumPosiciones()+"";
			
			respuesta.add(datos);
		}
		
		
		
		return respuesta;
	}
	/**
	 * método que reserva un vuelo.
	 * invoca al DAO para obtener los resultados.
	 * @return String identificador de la reserva o -1 si no se completo.
	 * @throws Exception 
	 * @throws Exception pasa la excepción generada por el DAO
	 */
	public String reservarVuelo( int identificadorvuelo, int numPosiciones) throws Exception{
		
		return dao.reservarVuelo(identificadorvuelo,numPosiciones);
	}
	
	/**
	 * método que cancela una reserva.
	 * invoca al DAO para obtener los resultados.
	 * @return true o false si si o no se cancelo.
	 * @throws Exception 
	 * @throws Exception pasa la excepción generada por el DAO
	 */
	public boolean cancelarReserva( String identificadorreserva) throws Exception{
		
		return dao.cancelarReserva(identificadorreserva);
	}
	/**
	 * método que ccompra un vuelo.
	 * invoca al DAO para obtener los resultados.
	 * @param identificacionreserva la reserva a pagar
	 * @param infopago, el numero de la cuenta, cuenta maestra de pagos gratis 93052308842
	 * @return true o false si si o no se completo.
	 * @throws Exception 
	 * @throws Exception pasa la excepción generada por el DAO
	 */
	public boolean comprarVuelo(String identificacionreserva,String infoPago) throws Exception{
		
		
		return dao.comprarVueloConReserva(identificacionreserva,infoPago);
		
		
	}
	
	/**
	 * método que prueba la conexion de la bases de datos sin hacerle cambios
	 * @throws Exception pasa la excepción generada por el DAO
	 */
	public void darVuelosDefault() throws Exception
	{
	     dao.darVuelosDefault();
	}
	
}
