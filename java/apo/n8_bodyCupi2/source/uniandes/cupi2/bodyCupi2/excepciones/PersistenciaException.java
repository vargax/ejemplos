package uniandes.cupi2.bodyCupi2.excepciones;

/**
 * Excepción en caso que no sea posible realizar el procedimiento de persistencia de la aplicación
 */

public class PersistenciaException extends Exception {
	
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	private String nombreArchivo;

	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	
	public PersistenciaException(String nArchivo, String mensajeError){
		super("Error de persistencia en "+nArchivo+"\n"+mensajeError);
		nombreArchivo = nArchivo;
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	public String darNombreArchivo() {
		return nombreArchivo;
	}
}
