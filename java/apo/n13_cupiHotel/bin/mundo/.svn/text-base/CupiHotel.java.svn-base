package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

public class CupiHotel implements ICupiHotel, Serializable
{
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * Corresponde a la colección de habitaciones del hotel
	 */
	private Habitacion[] habitaciones;
	/**
	 * Corresponde a las habitaciones actualmente desplegadas en la interfaz gráfica
	 */
	//private Habitacion[] habitacionesDesplegadas;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Crea un nuevo hotel
	 */
	public CupiHotel()	{
		
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/* (non-Javadoc)
	 * @see mundo.ICupiHotel#inicializarHotel(File archivoCargar)
	 */
	@Override
	public void inicializarHotel(File archivoCargar) throws Exception {
		BufferedReader lector = new BufferedReader(new FileReader(archivoCargar));
		String linea = lector.readLine();
		String []datos = linea.split("_");
		habitaciones = new Habitacion[Integer.parseInt(datos[1])];
		linea = lector.readLine();
		String tipoH = null;
		int contador= 0;
		int posicion=0;
		while(linea != null) {
			contador++;
			
			if(contador == 1) tipoH = "Pequeña";
			else if(contador == 2) tipoH = "Mediana";
			else tipoH = "Grande";
			
			String []hab= linea.split("_");
			int p = Integer.parseInt(hab[1]);
			for(int i = 0;i<p;i++) {
				linea = lector.readLine();
				String []a= linea.split("_");
				Habitacion h = new Habitacion(posicion,tipoH, Integer.parseInt(a[0]),Double.parseDouble(a[1]));
				habitaciones[posicion]=h;
				posicion++;
			}
			linea = lector.readLine();
		}
		lector.close();
	}
	/* (non-Javadoc)
	 * @see mundo.ICupiHotel#darListaHabitaciones()
	 */
	@Override
	public String[][] darListaHabitaciones() {
		
		String[][] respuesta = new String[habitaciones.length][7];
				
		for (int i = 0; i < habitaciones.length; i++)
			respuesta[i] = habitaciones[i].getInfoHabitacion();
	
		return respuesta;	
	}
	/* (non-Javadoc)
	 * @see mundo.ICupiHotel#darListaHuespedes(int)
	 */
	@Override
	public String[][] darListaHuespedes(int idHabitacion) {
		return habitaciones[idHabitacion].getInfoHuespedes();
	}
	@Override
	public String[][] darListaConsumos(int idHabitacion) {
		return habitaciones[idHabitacion].getInfoConsumos();
	}
	@Override
	public String[][] darListaReservas(int idHabitacion) {
		return habitaciones[idHabitacion].getInfoReservas();
	}
	/* (non-Javadoc)
	 * @see mundo.ICupiHotel#registrarReserva(int, java.lang.String, java.util.Date, int)
	 */
	@Override
	public void registrarReserva(int idHabitacion, String nombreP, Date fechaInicio, int diasReservados) {
		habitaciones[idHabitacion].registrarReserva(nombreP, fechaInicio, diasReservados);
	}
	/* (non-Javadoc)
	 * @see mundo.ICupiHotel#registrarHuesped(int, java.lang.String, int, int, java.lang.String, int)
	 */
	@Override
	public void registrarHuesped(int idHabitacion, String nombreP, int edadP, int identificacionP, String direccionP, int telefonoP, int nochesP) {
		habitaciones[idHabitacion].registrarHuesped(nombreP, edadP, identificacionP, direccionP, telefonoP, nochesP);
	}
	/* (non-Javadoc)
	 * @see mundo.ICupiHotel#registrarConsumo(java.lang.String, double)
	 */
	@Override
	public void registrarConsumo(int idHabitacion, String nombreP, double valorP) {
		habitaciones[idHabitacion].registrarConsumo(nombreP, valorP);
	}
	public void generarReporte(File archivoGenerar) throws Exception
	{	
		PrintWriter escritor =new PrintWriter(archivoGenerar);
		for(int i = 0; i< habitaciones.length;i++)
		{
			escritor.println("Habitacion: "+ habitaciones[i].darId());
			if(habitaciones[i].estaOcupada())
			{
				for(int j = 0; j<habitaciones[i].darHuespedes().length;j++)
				{
					Huesped [] a =habitaciones[i].darHuespedes();
					escritor.println("Huesped: "+a[j].getNombre()+" con identificación "+a[j].darDI());
				}
				escritor.println("-------------------------------------------------------");
			}
			if(habitaciones[i].estaReservada())
			{
				for(int j = 0; j<habitaciones[i].darReservas().length;j++)
			{
				Reserva [] a = habitaciones[i].darReservas();
				escritor.println("Reserva a nombre de: "+a[j].darNombreP()+" y empieza el "+a[j].darFechaInicio());
			}
			escritor.println("-------------------------------------------------------");
		}
		escritor.println("El total de consumos de la habitacion es: "+habitaciones[i].darConsumos());
	}
	escritor.close();
}

}