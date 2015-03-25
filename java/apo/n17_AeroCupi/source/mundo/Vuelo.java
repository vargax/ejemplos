package mundo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vuelo implements Comparable<Vuelo> {
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	/**
	 * La aerolinea que opera el vuelo
	 */
	private String aerolinea;
	/**
	 * El origen del vuelo
	 */
	private String origen;
	/**
	 * El destino del vuelo
	 */
	private String destino;
	/**
	 * El número del vuelo
	 */
	private int numero;
	/**
	 * La hora de salida
	 */
	private Date salida;
	/**
	 * La hora de llegada
	 */
	private Date llegada;
	/**
	 * La duración del vuelo en minutos
	 */
	private long duracion;
	/**
	 * El tipo de equipo
	 */
	private String equipo;
	/**
	 * El número de sillas
	 */
	private int sillas;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	public Vuelo(String aerolinea, String origen, String destino, String numero, Date salida, Date llegada, String equipo, String sillas) throws ParseException {
		this.aerolinea = aerolinea;
		this.origen = origen;
		this.destino = destino;
		
		this.numero = Integer.parseInt(numero);
		
		this.salida = salida;
		this.llegada = llegada;		

		this.duracion = (this.llegada.getTime() - this.salida.getTime())/(60*1000);
		
		if (this.llegada.getTime() > new Date(1000*60*60*24*7).getTime()) this.llegada = new Date(this.llegada.getTime()-1000*60*60*24*7);
		
		this.equipo = equipo;
		this.sillas = Integer.parseInt(sillas);
		
//		System.out.println("Vuelo.Vuelo(): Creado el vuelo número "+this.numero+" de "+this.aerolinea);
//		System.out.println("  "+"Ruta: "+origen+" => "+destino);
//		SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm");
//		System.out.println("  "+"Salida: "+sdf.format(this.salida)+" Llegada: "+sdf.format(this.llegada));
//		System.out.println("  "+"Duración: "+this.duracion);
	}
	// --------------------------------------------------------
	// Métodos Interfaz
	// --------------------------------------------------------
	@Override
	public int compareTo(Vuelo o) {
		return salida.compareTo(o.getSalida());
	}
	// --------------------------------------------------------
	// Getters/Setters
	// --------------------------------------------------------
	protected long getDuracion() {
		return duracion;
	}
	public String getOrigen() {
		return origen;
	}
	public String getDestino() {
		return destino;
	}
	public String getAerolinea() {
		return aerolinea;
	}
	public int getNumero() {
		return numero;
	}
	public Date getSalida() {
		return salida;
	}
	public Date getLlegada() {
		return llegada;
	}
	public String getEquipo() {
		return equipo;
	}
	public int getSillas() {
		return sillas;
	}
}
