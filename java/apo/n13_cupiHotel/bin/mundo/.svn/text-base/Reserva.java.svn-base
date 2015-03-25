package mundo;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable {
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = 5368648088467155475L;
	/**
	 * Corresponde al nombre de la persona que reserva
	 */
	private String nombrePersona;
	/**
	 * Corresponde a la fecha de inicio de las reservas
	 */
	private Date fechaInicio;
	/**
	 * Corresponde al número de dias reservados a partir de la fecha de inicio
	 */
	private int diasReservados;
	/**
	 * Constructor de la clase Reserva
	 * @param nombreP -Corresponde al nombre de la persona que reserva nombreP!=null&& nombreP!=""
	 * @param diasR-Corresponde al número de dias reservados por una persona-diasR!=null && diasR!=0
	 * @param fechaI-Corresponde a la fecha de inicio de la reserva-fechaI!=null 
	 */
	public Reserva(String nombreP,int diasR,Date fechaI)
	{
		nombrePersona =  nombreP;
		diasReservados = diasR;
		fechaInicio = fechaI;
	}
	public String[] getInfoReserva()
	{
		String []a = new String[3];
		a[0]=nombrePersona;
		a[1]= ""+diasReservados;
		a[2]= fechaInicio.toString();
		return a;
		
	}
	/**
	 * Devuelve el nombre de la persona que hizo la reserva
	 */
	public String darNombreP()
	{
		return nombrePersona;
	}
	/**
	 * Devuelve la fecha de inicio de la reserva
	 */
	public String darFechaInicio()
	{
		return fechaInicio.toString();
	}

}
