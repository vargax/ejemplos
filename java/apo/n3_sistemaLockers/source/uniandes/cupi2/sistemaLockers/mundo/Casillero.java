package uniandes.cupi2.sistemaLockers.mundo;

public class Casillero {
	
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	
	/**
	 * Tipo de casillero Grande
	 */
	public final static String TIPO_1 = "Grande";
	/**
	 * Tipo de casillero Mediano
	 */
	public final static String TIPO_2 = "Mediano";
	/**
	 * Tipo de casillero Pequeño
	 */
	public final static String TIPO_3 = "Pequeño";
	/**
	 * Casillero sin tipo asignado
	 */
	public final static String NINGUNO = "Ninguno";
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * Tipo del casillero
	 */
	private String tipo;
	/**
	 * Estado de asignación del casillero
	 */
	private boolean ocupado;
	/**
	 * Identificación del casillero
	 */
	private String id;

	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	
	/**
	 * Método constructor de la clase casillero
	 * @param tipoCasillero tipo del casillero
	 * @param identificador identificación del casillero
	 */
	public Casillero(String tipoCasillero, String identificador){
		
		// Determinando el tipo del casillero
		tipo = tipoCasillero;

		// Asignando identificador
		id = identificador;
		
		// Marcando el casillero como no asignado
		ocupado = false;
	}
	/**
	 * Devuelve el tipo del casillero
	 * @return Tipo del casillero
	 */
	public String darTipo(){
		return tipo;
	}
	/**
	 * Devuelve la identificación del casillero
	 * @return Id del casillero
	 */
	public String darId(){
		return id;
	}
	/**
	 * Devuelve el estado de asignación del casillero
	 * @return True si el casillero está asignado, False de lo contrario
	 */
	public boolean estaAsignado(){
		return ocupado;
	}
	/**
	 * Asigna el casillero
	 */
	public void asignarCasillero(){
		ocupado = true;
	}
	/**
	 * Desasigna el casillero
	 */
	public void desasignarCasillero(){
		ocupado = false;
	}
}
