package excepciones;

public class CriterioOrdenamientoInvalidoException extends Exception {
	// Constante serialización
	private static final long serialVersionUID = 1L;
	// El objeto para el cual no fue posible generar el criterio de ordenamiento
	private Object objetoAlmacenado;
	// Constructores
	public CriterioOrdenamientoInvalidoException(String mensaje) {
		super(mensaje);
	}
	public CriterioOrdenamientoInvalidoException(String mensaje, Object objetoAlmacenar) {
		super(mensaje);
		this.objetoAlmacenado = objetoAlmacenar;
	}
	//Getters/Setters
	public void setObjetoAlmacenado(Object objetoAlmacenar) {
		this.objetoAlmacenado = objetoAlmacenar;
	}
	public Object getObjetoAlmacenado() {
		return this.objetoAlmacenado;
	}
}
