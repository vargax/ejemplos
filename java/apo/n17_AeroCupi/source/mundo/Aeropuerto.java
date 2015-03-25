package mundo;

import estructurasDatos.IIdentificable;

public class Aeropuerto implements IIdentificable {
	// --------------------------------------------------------
	// Atributos
	// --------------------------------------------------------
	/**
	 * El identificador del aeropuerto
	 */
	private String nombre;
	// --------------------------------------------------------
	// Constructor
	// --------------------------------------------------------
	public Aeropuerto(String nombre) {
		this.nombre = nombre;
	}
	// --------------------------------------------------------
	// Métodos Interfaz
	// --------------------------------------------------------
	@Override
	public String getId() {
		return this.nombre;
	}
	// --------------------------------------------------------
	// Métodos
	// --------------------------------------------------------
	@Override
	public String toString() {
		return nombre;
	}
}
