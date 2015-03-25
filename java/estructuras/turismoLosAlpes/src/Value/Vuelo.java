package Value;

import java.util.Date;

public class Vuelo {
	
	/**
	 * fecha en la que sale el vuelo, formato dd/mm/yyyy
	 */
	private Date fecha;
	/**
	 * Ciudad de donde sale el vuelo
	 */
	private Ciudad origen;
	/**
	 * Ciudad a donde llega el vuelo
	 */
	private Ciudad destino;
	/**
	 * Sillas totales del avion
	 */
	private int sillasTotales;
	/**
	 * Sillas libres del vuelo
	 */
	private int sillasDisponibles;
	private int id;
	/**
	 * Constructor de la clase vuelo
	 * @param fecha con formato dd/mm/yyyy
	 * @param origen ciudad de origen
	 * @param destino ciudad a donde llega el destino
	 * @param totales el total de puestos del vuelo
	 * @param disponibles las sillas disponibles del vuelo
	 * @param idp 
	 * 
	 */
	@SuppressWarnings("deprecation")
	public Vuelo (String fechap,Ciudad origenp,Ciudad destinop,int totales,int disponibles, int idp){
		try{
		 String []fechaarr=fechap.split("/");
		 fecha=new Date();
		 fecha.setDate(Integer.parseInt(fechaarr[0]));
		 fecha.setMonth(Integer.parseInt(fechaarr[1]));
		 fecha.setYear(Integer.parseInt(fechaarr[2]));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		origen=origenp;
		destino=destinop;
		sillasTotales=totales;
		sillasDisponibles=disponibles;
		id=idp;
		
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		String fechap=fecha.getDay()+"/"+fecha.getMonth()+"/"+fecha.getYear();
		return fechap;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the origen
	 */
	public Ciudad getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}
	/**
	 * @return the destino
	 */
	public Ciudad getDestino() {
		return destino;
	}
	/**
	 * @param destino the destino to set
	 */
	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}
	/**
	 * @return the sillasTotales
	 */
	public int getSillasTotales() {
		return sillasTotales;
	}
	/**
	 * @param sillasTotales the sillasTotales to set
	 */
	public void setSillasTotales(int sillasTotales) {
		this.sillasTotales = sillasTotales;
	}
	/**
	 * @return the sillasDisponibles
	 */
	public int getSillasDisponibles() {
		return sillasDisponibles;
	}
	/**
	 * @param sillasDisponibles the sillasDisponibles to set
	 */
	public void setSillasDisponibles(int sillasDisponibles) {
		this.sillasDisponibles = sillasDisponibles;
	}
	
	

}
