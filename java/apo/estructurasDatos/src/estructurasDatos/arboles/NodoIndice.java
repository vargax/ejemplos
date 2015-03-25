package estructurasDatos.arboles;

import java.io.Serializable;

import excepciones.CriterioOrdenamientoInvalidoException;

public class NodoIndice<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * El objeto almacenado
	 */
	private T objetoAlmacenado;
	/**
	 * El criterio lexicográfico de ordenamiento
	 */
	private String criterioOrdenamiento;
	/**
	 * El criterio de ordenamiento reducido solo a números y letras
	 */
	private String criterioReducido;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public NodoIndice(String criterio, T objetoAlmacenar) throws CriterioOrdenamientoInvalidoException {
		objetoAlmacenado = objetoAlmacenar;
		criterioOrdenamiento = criterio;
		
		try {
			criterioReducido = generarCriterioReducido(criterio);
		} catch (CriterioOrdenamientoInvalidoException e) {
			e.setObjetoAlmacenado(objetoAlmacenar);
			throw e;
		}
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Devuelve el objto almacenado
	 * @return El objeto almacenado en el nodo
	 */
	public T darObjetoAlmacenado() {
		return objetoAlmacenado;
	}
	/**
	 * Devuelve el criterio de ordenamiento asociado al nodo
	 * @return Cadena que representa el criterio de ordenamiento
	 */
	public String darCriterioOrdenamiento() {
		return criterioOrdenamiento;
	}
	/**
	 * Devuelve el criterio de ordenamiento reducido generado para el nodo
	 * @return Cadena que representa el criterio de ordenamiento reducido
	 */
	public String darCriterioReducido() {
		return criterioReducido;
	}
	/**
	 * Calcula el criterio de ordenamiento reducido para una cadena dada
	 * @param criterioOrdenamientoP
	 * @return
	 */
	public static String generarCriterioReducido(String criterio) throws CriterioOrdenamientoInvalidoException {
		if (criterio.length() == 0) throw new CriterioOrdenamientoInvalidoException("El criterio de ordenamiento parámetro tiene una longitud de cero caracteres.");
		criterio = criterio.toLowerCase();
		String respuesta = "";
		for (char a : criterio.toCharArray())
			if (Character.isLetterOrDigit(a)) respuesta = respuesta + a;
				
		if (respuesta.length() == 0)
			throw new CriterioOrdenamientoInvalidoException("El criterio de ordenamiento generado para '"+criterio+"' tiene una longitud de cero caracteres.");
				
		return respuesta;
	}
}
