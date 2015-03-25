package uniandes.cupi2.componenteCompartir.mundo;

import java.io.Serializable;
import java.util.Date;

import estructurasDatos.IListaEncadenada;
import estructurasDatos.ListaEncadenada;

/**
 * Clase que representa las estadísticas de un archivo
 */
public class Archivo implements Serializable {
	/**
	 * Constante para serialización
	 */
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El nombre del archivo
	 */
	private String nombreArchivo;
	/**
	 * Una lista que contiene las descargas del archivo
	 */
	private IListaEncadenada<String> descargas;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public Archivo(String nombreArchivoP) {
		nombreArchivo = nombreArchivoP;
		descargas = new ListaEncadenada<String>();
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	public void registrarDescarga(String usuarioP) {
		String tmpDescarga = usuarioP +" ("+ (new Date(System.currentTimeMillis())).toString()+")";
		descargas.agregar(tmpDescarga);
	}
	public IListaEncadenada<String> darDescargas() {
		return descargas;
	}
	public String darNombreArchivo() {
		return nombreArchivo;
	}
}