package uniandes.cupi2.componenteContactos.mundo;

import java.io.Serializable;

/**
 * Clase que modela un contacto
 * @author Yeisson Oviedo
 *
 */
public class Contacto implements Serializable{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	private String nombre;
	
	private String telCasa;

	private String telTrabajo;

	private String telPersonal;

	private String telMovil;

	private String correo;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------	
	
	/**
	 * Constructor
	 */
	public Contacto(){
		
	}
	
	/**
	 * Constructor
	 * @param nombre Nombre del contacto 
	 * @param telCasa Teléfono de la casa
	 * @param telTrabajo Teléfono del trabajo
	 * @param telPersonal Teléfono personal
	 * @param telMovil Teléfono móvil
	 * @param correo Correo electrónico
	 */
	public Contacto(String nombre, String telCasa, String telTrabajo,
			String telPersonal, String telMovil, String correo) {
		this.nombre = nombre;
		this.telCasa = telCasa;
		this.telTrabajo = telTrabajo;
		this.telPersonal = telPersonal;
		this.telMovil = telMovil;
		this.correo = correo;
	}

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

	/**
	 * Retorna el nombre del contacto 
	 * @return Nombre del contacto
	 */
	public String darNombre() {
		return nombre;
	}

	/**
	 * Permite cambiar el nombre del contacto
	 * @param nombre Nombre a cambiar
	 */
	public void cambiarNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el teléfono de la casa del contacto 
	 * @return Teléfono de la casa del contacto
	 */
	public String darTelefonoCasa() {
		return telCasa;
	}

	/**
	 * Permite cambiar el teléfono de la casa del contacto
	 * @param telCasa Nuevo número de teléfono
	 */
	public void cambiarTelefonoCasa(String telCasa) {
		this.telCasa = telCasa;
	}

	/**
	 * Retorna el teléfono del trabajo del contacto 
	 * @return Teléfono del trabajo del contacto
	 */
	public String darTelefonoTrabajo() {
		return telTrabajo;
	}

	/**
	 * Permite cambiar el teléfono del trabajo del contacto
	 * @param telTrabajo Nuevo número de teléfono
	 */
	public void cambiarTelefonoTrabajo(String telTrabajo) {
		this.telTrabajo = telTrabajo;
	}

	/**
	 * Retorna el teléfono personal del contacto 
	 * @return Teléfono personal del contacto
	 */
	public String darTelefonoPersonal() {
		return telPersonal;
	}

	/**
	 * Permite cambiar el teléfono personal del contacto
	 * @param telPersonal Nuevo número de teléfono
	 */
	public void cambiarTelefonoPersonal(String telPersonal) {
		this.telPersonal = telPersonal;
	}

	/**
	 * Retorna el teléfono móvil del contacto 
	 * @return Teléfono de móvil del contacto
	 */
	public String darTelefonoMovil() {
		return telMovil;
	}

	/**
	 * Permite cambiar el teléfono móvil del contacto
	 * @param telMovil Nuevo número de teléfono
	 */
	public void cambiarTelefonoMovil(String telMovil) {
		this.telMovil = telMovil;
	}

	/**
	 * Retorna el correo del contacto
	 * @return Correo del contacto
	 */
	public String darCorreo() {
		return correo;
	}

	/**
	 * permite cambiar el correo del contacto
	 * @param correo Correo del contacto
	 */
	public void cambiarCorreo(String correo) {
		this.correo = correo;
	}
	
    //-----------------------------------------------------------------
    // Métodos Auxiliares
    //-----------------------------------------------------------------

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals (Object o){
		if (o instanceof Contacto && ((Contacto)o).darNombre().equals(nombre))
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return nombre + " " + telMovil;
	}
}
