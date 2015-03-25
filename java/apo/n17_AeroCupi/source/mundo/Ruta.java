package mundo;

import java.util.Date;
import java.util.Iterator;

import estructurasDatos.IIdentificableComparable;
import estructurasDatos.listas.IListaEncadenada;
import estructurasDatos.listas.ListaEncadenada;
import estructurasDatos.listas.ListaEncadenadaOrdenada;

public class Ruta implements IIdentificableComparable<Ruta> {
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	/**
	 * El identificador de la ruta
	 */
	private String id;
	/**
	 * El aeropuerto de origen
	 */
	private Aeropuerto origen;
	/**
	 * El aeropuerto de destino
	 */
	private Aeropuerto destino;
	/**
	 * Una contenedora para almacenar los vuelos organizados por duración
	 */
	private ListaEncadenadaOrdenada<Vuelo> vuelos;
	/**
	 * Atributo para almacenar el cálculo del costo en minutos de tomar
	 * el siguiente vuelo disponible al aeropuerto de destino dado un día
	 *  y hora de salida.
	 */
	private long costoMinimo;
	/**
	 * Atributo para almacenar una referencia el vuelo que miniza el costo
	 * almacenado en el atributo anterior.
	 */
	private Vuelo vueloCtoMinimo;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	/**
	 * Crea una nueva ruta entre el aeropuerto de origen y el de destino
	 * @param id El identificador de la ruta
	 * @param origen El aeropuerto de origen
	 * @param destino El aeropuerto de destino
	 */
	public Ruta(String id, Aeropuerto origen, Aeropuerto destino) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		
		vuelos = new ListaEncadenadaOrdenada<Vuelo>();
	}
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	/**
	 * Agrega un nuevo vuelo a esta ruta
	 * @param nuevoVuelo
	 */
	public void agregarVuelo(Vuelo nuevoVuelo) {
		vuelos.agregar(nuevoVuelo);
	}
	// --------------------------------------------------------
	// Métodos Interfaz
	// --------------------------------------------------------
	@Override
	// Retorno > 0 si YO soy mayor que la ruta pasada como parámetro
	// Retorno < 0 si YO soy menor que la ruta pasada como parámetro
	// Retorno 0 si YO y la ruta pasada como parámetro tenemos la misma duración
	public int compareTo(Ruta o) {
		return (int) (costoMinimo - o.darCosto());
	}

	@Override
	public String getId() {
		return id;
	}
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	@Override
	public String toString() {
		return origen.toString()+" ==> "+destino.toString();
	}
	// --------------------------------------------------------
	// Getters/Setters
	// --------------------------------------------------------
	/**
	 * Este método recibe el costo acumulado hasta el momento (tiempo en minutos desde
	 * la salida del aeropuerto de origen) y en base a este calcula cual es el mínimo 
	 * costo que puede ofrecer tomando esta ruta.
	 * Primero determina cual es el primer vuelo que se podría tomar tras calcular la hora 
	 * de llegada al aeropuerto, luego suma la duración del vuelo con el tiempo de espera.
	 * @param costo El costo en minutos acumulado hasta el momento.
	 */
	public void setCosto(Date horaLlegada) {
		Iterator<Vuelo> i = this.vuelos.iterator();
		this.vueloCtoMinimo = i.next();
		while(i.hasNext() && this.vueloCtoMinimo.getSalida().before(horaLlegada)) {
			this.vueloCtoMinimo = i.next();
		}
		
		if(this.vueloCtoMinimo.getSalida().before(horaLlegada)) {
			// Si entré aquí es porque horaLlegada es posterior getSalida() del último vuelo de la semana
			// Debo tomar entonces el primer vuelo de la semana siguiente
			this.vueloCtoMinimo = vuelos.darPrimerElemento();
		}
		// El costo de tomar esta ruta va a ser el timpo de transcurrido desde la llegada al aeropuerto
		// hasta el arribo del vuelo a su destino
		this.costoMinimo = this.vueloCtoMinimo.getLlegada().getTime() - horaLlegada.getTime();
		// Si el costo mínimo calculado es negativo es porque vueloCtoMinimo.getLlegada() es menor que horaLlegada
		// -> Ocurrió un cambio de semana durante el vuelo
		// -> El costo real es el complemento entre una semana completa y el resultado obtenido
		if (this.costoMinimo < 0) this.costoMinimo += 1000*60*60*24*7;
	}
	
	public Vuelo darVueloMenorDuracion() {
		return vueloCtoMinimo;
	}
	
	@Override
	public long darCosto() {
		return this.costoMinimo;
	}
}
