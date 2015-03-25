package mundo;

import java.io.Serializable;

public class Huesped implements Comparable<Huesped>, Serializable {
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = 3039630958479118226L;
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	/**
	 * El nombre del huesped
	 */
	private String nombre;
	/**
	 * La edad del huesped
	 */
	private int edad;
	/**
	 * La identificación del huesped
	 */
	private int documentoIdentificacion;
	/**
	 * La dirección de residencia del huesped
	 */
	private String direccionResidencia;
	/**
	 * El teléfono del huesped
	 */
	private int telefono;
	/**
	 * El número de noches que el huesped permanecerá en el hotel
	 */
	private int numeroNochesEstadia;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	public Huesped(String nombreP, int edadP, int documentoIdentificacionP, String direccionResidenciaP, int telefonoP, int numeroNochesEstadiaP) {
		nombre = nombreP;
		edad = edadP;
		documentoIdentificacion = documentoIdentificacionP;
		direccionResidencia = direccionResidenciaP;
		telefono = telefonoP;
		numeroNochesEstadia = numeroNochesEstadiaP;
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	@Override
	public int compareTo(Huesped H) {
		return nombre.compareToIgnoreCase(H.getNombre());
	}
	public String[] getInfoHuesped() {
		String[] respuesta = new String[6];
		
		respuesta[0] = nombre;
		respuesta[1] = edad+"";
		respuesta[2] = documentoIdentificacion+"";
		respuesta[3] = direccionResidencia;
		respuesta[4] = telefono+"";
		respuesta[5] = numeroNochesEstadia+"";
		
		return respuesta;
	}
	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Duevuelve el documento de identificacion del huesped
	 * @return
	 */
	public int darDI()
	{
		return documentoIdentificacion;
	}

}
