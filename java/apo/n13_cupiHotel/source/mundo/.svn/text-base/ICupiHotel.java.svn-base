package mundo;

import java.io.File;
import java.util.Date;

public interface ICupiHotel {
	/**
	 * Iniciliza el hotel
	 * @param archivoCargar: archivo que contiene la información de las habitaciones
	 */
	public void inicializarHotel(File archivoCargar) throws Exception;
	/**
	 * Entrega la lista de habitaciones
	 */
	public String[][] darListaHabitaciones();
	/**
	 * Entrega la lista de huespedes de una habitación específica
	 */
	public String[][] darListaHuespedes(int idHabitacion);
	/**
	 * Entrega la lista de reservas de una habitación específica
	 */
	public String[][] darListaReservas(int idHabitacion);
	/**
	 * Entrega la lista de consumos de una habitación específica
	 */
	public String[][] darListaConsumos(int idHabitacion);
	/**
	 * Registra una nueva reserva
	 */
	public void registrarReserva(int idHabitacion, String nombreP, Date fechaInicio, int diasReservados);
	/**
	 * Registra un nuevo huesped
	 */
	public void registrarHuesped(int idHabitacion, String nombreP, int edadP, int identificacionP, String direccionP, int telefonoP, int nochesP);
	/**
	 * Registra un nuevo consumo
	 */
	public void registrarConsumo(int idHabitacion, String nombreP, double valorP);
	/**
	 * Genera un reporte de las habitaciones ocupadas
	 */
	public void generarReporte(File archivoGenerar)throws Exception;

}
