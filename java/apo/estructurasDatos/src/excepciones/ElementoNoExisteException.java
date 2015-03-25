package excepciones;

public class ElementoNoExisteException extends Exception {
	// Constantes
	private static final long serialVersionUID = 1L;
	// Atributos
	private String idObjeto;
	// Constructor
	public ElementoNoExisteException(String mensaje, String idObjeto) {
		super("El elemento no existe en la estructura: "+mensaje);
		this.idObjeto = idObjeto;
	}
	// Métodos
	public String getIdObjeto() {
		return idObjeto;
	}
}
