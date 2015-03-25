package excepciones;

public class ElementoExisteException extends Exception {
	// Constantes
	private static final long serialVersionUID = 1L;
	// Atributos
	private Object elemento;
	// Constructor
	public ElementoExisteException(String mensaje, Object elemento) {
		super("El elemento existe en la estructura: "+mensaje);
		this.elemento = elemento;
	}
	// Constructor
	public ElementoExisteException(String mensaje) {
		super("El elemento existe en la estructura: "+mensaje);
	}
	// Metodos
	public Object getElemento() {
		return elemento;
	}
}
