package excepciones;

public class ObjetoNoEncontradoExcepcion extends Exception {
	
	private static final long serialVersionUID = -2205976356653751546L;

	public ObjetoNoEncontradoExcepcion(String excepcion) {
		super(excepcion);
	}
}
