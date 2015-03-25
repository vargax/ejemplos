package Value;

import java.util.Date;

public class Pago {
	/**
	 * fecha en la que se pago
	 */
	private Date fechaPago;
	/**
	 * valor del pago
	 */
	private double valor;
	/**
	 * reserva que se paga
	 */
	private int reserva;
	/**
	 * constructor pago
	 * @param fecha con formato dd/mm/yyyy
	 * @param valorp valor a pagar
	 * @param id reserva a pagar
	 */
	public Pago(String fecha, double valorp, int id){
		
		valor=valorp;
		
		reserva=id;
		
		try{
			 String []fechaarr=fecha.split("/");
			 fechaPago=new Date();
			 fechaPago.setDate(Integer.parseInt(fechaarr[0]));
			 fechaPago.setMonth(Integer.parseInt(fechaarr[1]));
			 fechaPago.setYear(Integer.parseInt(fechaarr[2]));
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
		
		
		
	}

	/**
	 * @return the fechaPago
	 */
	@SuppressWarnings("deprecation")
	public String getFechaPago() {
		return fechaPago.getDate()+"/"+fechaPago.getMonth()+"/"+(fechaPago.getYear()+1900);
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @return the reserva
	 */
	public int getReserva() {
		return reserva;
	}

	/**
	 * @param reserva the reserva to set
	 */
	public void setReserva(int reserva) {
		this.reserva = reserva;
	}

	
	
	
}
