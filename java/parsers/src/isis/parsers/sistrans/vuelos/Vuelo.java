package isis.parsers.sistrans.vuelos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vuelo {
	// --------------------------------------------------------
	// Constantes
	// --------------------------------------------------------
	private final long MINUTO = 60*1000;
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

		this.duracion = (this.llegada.getTime() - this.salida.getTime())/(this.MINUTO);
		
		this.equipo = equipo;
		this.sillas = Integer.parseInt(sillas);
		
		System.out.println("Vuelo.Vuelo(): Creado el vuelo número "+this.numero+" de "+this.aerolinea);
		System.out.println("  "+"Ruta: "+origen+" => "+destino);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd-MMMM-yyyy HH:mm");
		System.out.println("  "+"Salida: "+sdf.format(this.salida)+" Llegada: "+sdf.format(this.llegada));
		System.out.println("  "+"Duración: "+this.duracion);
	}
	
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	public String sql(int numeroSemanas) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		String sql = "INSERT INTO vuelo(id, aerolinea, numeroVuelo, equipo," +
    			" origen, destino, duracion, salida, llegada, sillasDisponibles, sillasTotales)" +
    			" VALUES";
		sql = sql+"(id_vuelo.nextval,'"
    			+ this.aerolinea+"',"
    			+ this.numero+",'"
    			+ this.equipo+"','"
    			+ this.origen+"','"
    			+ this.destino+"',"
    			+ this.duracion+","
    			+ "to_date('"+sdf.format(salida)+"','DD/MM/YYYY HH24:MI')," 
    			+ "to_date('"+sdf.format(llegada)+"','DD/MM/YYYY HH24:MI')," 
    			+ this.sillas+","
    			+ this.sillas+"); \n";
		if(numeroSemanas > 1) {
			long semana = 1000*60*60*24*7;
			long salidaMS = this.salida.getTime()+semana;
			long llegadaMS = this.llegada.getTime()+semana;			
			
			for(int i = 1; i < numeroSemanas; i++) {
				salidaMS += semana;
				llegadaMS += semana;
				Date salida = new Date(salidaMS); 
				Date llegada = new Date(llegadaMS);
				sql = sql + "INSERT INTO vuelo(id, aerolinea, numeroVuelo, equipo," +
		    			" origen, destino, duracion, salida, llegada, sillasDisponibles, sillasTotales)" +
		    			" VALUES";
				sql = sql+"(id_vuelo.nextval,'"
		    			+ this.aerolinea+"',"
		    			+ this.numero+",'"
		    			+ this.equipo+"','"
		    			+ this.origen+"','"
		    			+ this.destino+"',"
		    			+ this.duracion+","
		    			+ "to_date('"+sdf.format(salida)+"','DD/MM/YYYY HH24:MI')," 
		    			+ "to_date('"+sdf.format(llegada)+"','DD/MM/YYYY HH24:MI')," 
		    			+ this.sillas+","
		    			+ this.sillas+"); \n";
			}
		}
//		System.out.println(sql);
		return sql;
	}

	public int getSillas() {
		return this.sillas;
	}
}
