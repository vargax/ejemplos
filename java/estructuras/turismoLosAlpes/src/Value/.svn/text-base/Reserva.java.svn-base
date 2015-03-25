package Value;

import java.util.Date;

public class Reserva {
	
	public static final int VIGENTE=0;
	public static final int CANCELADA=-1;
	public static final int PAGADA=1;
	/**
	 * El usuario que hizo la reserva
	 */
	private Usuario usuario;
	/**
	 * fecha en el que se hizo la reserva
	 */
	private Date fechaReserva;
	/**
	 * el vuelo que se reservo
	 */
	private Vuelo vuelo;
	/**
	 * numero de posiciones que se reservo, se descuenta de las sillas disponibles del vuelo
	 */
	private int numPosiciones;
	/**
	 * el pago de la reserva si ya se pago
	 */
	private Pago pago;
	/**
	 * estado de la reserva
	 * 0 si no se ha pagado y sigue vigente
	 * 1 si ya se pago
	 * -1 si se cancelo la reserva
	 */
	private  int estadoReserva;
	private int id;
	
	public Reserva(Usuario user,String fecha, Vuelo vuelop,int posicion,Pago pagop,int estado, int idp){
		usuario=user;
		try{
			 String []fechaarr=fecha.split("/");
			 fechaReserva=new Date();
			 fechaReserva.setDate(Integer.parseInt(fechaarr[0]));
			 fechaReserva.setMonth(Integer.parseInt(fechaarr[1]));
			 fechaReserva.setYear(Integer.parseInt(fechaarr[2]));
			}
			catch(Exception e){
				e.printStackTrace();
			}
		vuelo=vuelop;
		numPosiciones=posicion;
		pago=pagop;
		estadoReserva=estado;
		id=idp;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the fechaReserva
	 */
	@SuppressWarnings("deprecation")
	public String getFechaReserva() {
		return fechaReserva.getDate()+"/"+fechaReserva.getMonth()+"/"+(fechaReserva.getYear()+1900);
	}

	/**
	 * @param fechaReserva the fechaReserva to set
	 */
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	/**
	 * @return the vuelo
	 */
	public Vuelo getVuelo() {
		return vuelo;
	}

	/**
	 * @param vuelo the vuelo to set
	 */
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	/**
	 * @return the numPosiciones
	 */
	public int getNumPosiciones() {
		return numPosiciones;
	}

	/**
	 * @param numPosiciones the numPosiciones to set
	 */
	public void setNumPosiciones(int numPosiciones) {
		this.numPosiciones = numPosiciones;
	}

	/**
	 * @return the pago
	 */
	public Pago getPago() {
		return pago;
	}

	/**
	 * @param pago the pago to set
	 */
	public void setPago(Pago pago) {
		this.pago = pago;
	}

	/**
	 * @return the estadoReserva
	 */
	public int getEstadoReserva() {
		return estadoReserva;
	}

	/**
	 * @param estadoReserva the estadoReserva to set
	 */
	public void setEstadoReserva(int estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public int getid() {
		
		return id;
	}

	public void setid(int idReserva) {
		// TODO Auto-generated method stub
		id=idReserva;
	}
	
	

}
