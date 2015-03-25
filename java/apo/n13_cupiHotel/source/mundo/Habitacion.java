package mundo;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import estructurasDatos.IListaSimpleEncadenada;
import estructurasDatos.ListaSimpleEncadenada;

public class Habitacion implements Serializable {
	/**
	 * Constante de Serialización
	 */
	private static final long serialVersionUID = 2669312963022234538L;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El id de la habitación
	 */
	private int id;
	/**
	 * Corresponde al tipo de habitación
	 */
	private String tipo;
	/**
	 * Corresponde al número de la habitación
	 */
	private int numero;
	/**
	 * Corresponde al precio de la habitación por noche
	 */
	private double precioNoche;
	/**
	 * Corresponde a los huespedes de la habitación
	 */
	private IListaSimpleEncadenada<Huesped> huespedes;
	/**
	 * Corresponde al total de huespedes en la habitación
	 */
	private int numeroHuespedes; 
	/**
	 * Corresponde a los consumos de la habitacióm
	 */
	private IListaSimpleEncadenada<Consumo> consumos;
	/**
	 * Corresponde al valor total adeudado en consumos por la habitación
	 */
	private double totalConsumos;
	/**
	 * Corresponde a las reservas para una habitación
	 */
	private IListaSimpleEncadenada<Reserva> reservas;
	/**
	 * Corresponde al total historico de reservas de la habitación
	 */
	private int totalHistoricoReservas;
	//--------------------------------------------------------------------------
	//Constructor
	//--------------------------------------------------------------------------
	/**
	 * Constructor de la clase habitación<br>
	 * Inicializa la lista de reservas
	 * @param tipoH- Corresponde al tipo de la habitación - tipoH !=null && tipoH!=""
	 * @param numeroH- Corresponde al número de la habitación - numeroH !=null
	 * @param precioH - Corresponde al precio de la habitación - precioH !=null && precioH!=0
	 */
	public Habitacion(int idP, String tipoH,int numeroH,double precioH)
	{
		id = idP;
		tipo = tipoH;
		numero = numeroH;
		precioNoche  = precioH;
		huespedes = null;
		consumos = null;
		reservas = null;
		
		numeroHuespedes = 0;
		totalConsumos = 0;
		totalHistoricoReservas = 0;
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Entrega toda la información de la habitación en un arreglo de Strings
	 * @return String[] que contiene la información de los atributos de la habitación 
	 */
	public String[] getInfoHabitacion() {
		String[] respuesta = new String[7];
		respuesta[0] = id+"";
		respuesta[1] = numero+"";
		respuesta[2] = tipo;
		respuesta[3] = precioNoche+"";
		respuesta[4] = numeroHuespedes+"";
		respuesta[5] = totalConsumos+"";
		respuesta[6] = totalHistoricoReservas+"";
		
		return respuesta;
	}
	/**
	 * Entrega la información de todos los huespedes registrados en la habitación
	 * @return 
	 */
	public String[][] getInfoHuespedes() {
		if (huespedes == null) {
			String[][] respuesta = {{" "," "," "," "," "," "}};
			return respuesta;
		}
		
		String[][]	respuesta = new String[huespedes.darNumeroObjetos()][6];
			Iterator<Huesped> iterador = huespedes.iterator();
			for (int i = 0; i < huespedes.darNumeroObjetos(); i++)
				respuesta[i] = iterador.next().getInfoHuesped();
		
		return respuesta;
	}
	/**
	 * Entrega la información de todos los consumos registrados en la habitación
	 * @return 
	 */
	public String[][] getInfoConsumos() {
		if (consumos == null) {
			String[][] respuesta = {{" "," "}};
			return respuesta;
		}
		
		String [][]respuesta = new String[consumos.darNumeroObjetos()][2];
		Iterator<Consumo> iterador = consumos.iterator();
		for(int i = 0; i< consumos.darNumeroObjetos();i++) {
			respuesta[i]= iterador.next().getInfoConsumo();
		}
		return respuesta;
	}
	public String[][] getInfoReservas() {
		if (reservas == null) {
			String[][] respuesta = {{" "," "," "}};
			return respuesta;
		}
		
		String [][]respuesta = new String[reservas.darNumeroObjetos()][3];
		Iterator<Reserva> iterador = reservas.iterator();
		for(int i = 0; i< reservas.darNumeroObjetos();i++) {
			respuesta [i]= iterador.next().getInfoReserva();
		}
		return respuesta;
	}
	/**
	 * Registra un huesped
	 */
	public void registrarHuesped(String nombreP, int edadP, int identificacionP, String direccionP, int telefonoP, int nochesP) {
		numeroHuespedes++;
		Huesped nuevoHuesped = new Huesped(nombreP, edadP, identificacionP, direccionP, telefonoP, nochesP);
		if (huespedes == null) huespedes = new ListaSimpleEncadenada<Huesped>(nuevoHuesped);
		else huespedes.agregarObjeto(nuevoHuesped);
	}
	/**
	 * Registra una reserva
	 */
	public void registrarReserva(String nombreP, Date fechaInicio, int diasReservados) {
		Reserva nuevaReserva = new Reserva(nombreP, diasReservados, fechaInicio);
		totalHistoricoReservas++;
		if (reservas == null) reservas = new ListaSimpleEncadenada<Reserva>(nuevaReserva);
		else reservas.agregarObjeto(nuevaReserva);
	}
	/** Registra un consumo
	 * @param nombre-corresponde al nombre del consumo para la habitación-nombre!=null && nombre!=""
	 * @param valor-corresponde al valor del consumo-valor!=0 && valor!=null
	 */
	public void registrarConsumo(String nombre, double valor) {
		Consumo nuevoConsumo = new Consumo(nombre, valor);
		totalConsumos += valor;
		if (consumos == null) consumos = new ListaSimpleEncadenada<Consumo>(nuevoConsumo);
		else consumos.agregarObjeto(nuevoConsumo);
	}
	/**
	 * Indica  si la habitación tiene huespedes
	 */
	public boolean estaOcupada()
	{
		return (huespedes!=null)?true:false;
	}
	/**
	 * Indica si la habitacion esta reservada 
	 */
	public boolean estaReservada()
	{
		return (reservas!=null)?true:false;
	}
	/**
	 * Devuelve el id de la habitacion
	 */
	public int darId()
	{
		return id;
	}
	/**
	 * Devuelve los huespedes en un arreglo
	 */
	public Huesped[]darHuespedes()
	{
		return (Huesped[]) huespedes.darObjetos();
	}
	/**
	 * Devuelve las reservas en un arreglo
	 */
	public Reserva[]darReservas()
	{
		return (Reserva[])reservas.darObjetos();
	}
	/**
	 * devuelve el total de consumos de la habitación
	 */
	public double darConsumos()
	{
		return totalConsumos;
	}

}
