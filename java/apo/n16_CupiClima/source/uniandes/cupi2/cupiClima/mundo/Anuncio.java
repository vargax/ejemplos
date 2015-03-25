package uniandes.cupi2.cupiClima.mundo;

import java.io.Serializable;

public class Anuncio implements Serializable {
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------
	// Constantes
	// ----------------------------------------------------
	
	// ----------------------------------------------------
	// Atributos
	// ----------------------------------------------------
	/**
	 * La ciudad a la cual el anuncio se encuentra ligado
	 */
	private Ciudad ciudad;
	/**
	 * El número de veces que este anuncio ha sido desplegado
	 */
	private int desplieges;
	/**
	 * El texto del anuncio
	 */
	private String texto;
	/**
	 * La ubicación de la imagen del anuncio en el servidor
	 */
	private String imagen;
	/**
	 * El link del anuncio
	 */
	private String link;
	// ----------------------------------------------------
	// Constructor
	// ----------------------------------------------------
	public Anuncio(Ciudad ciudad, String texto, String imagen, String link) {
		this.ciudad = ciudad;
		this.texto = texto;
		this.imagen = imagen;
		this.link = link;
		desplieges = 0;
	}
	// ----------------------------------------------------
	// Métodos
	// ----------------------------------------------------
	public String[] darAnuncioDesplegar() {
		String[] respuesta = {texto, imagen, link};
		desplieges++;
		return respuesta;
	}
	public String[] darAnuncioAdministrar() {
		String[] respuesta = {texto,ciudad.getNombreCiudad(),""+desplieges};
		return respuesta;
	}
}
