package uniandes.cupi2.sistemaLockers.mundo;

import java.util.ArrayList;

/**
 * Representa una locación de casilleros
 */
public class Locacion 
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Nombre de la locación 1
	 */
	public final static String NOMBRE_LOC_1 = "ML";
	
	/**
	 * Nombre de la locación 2
	 */
	public final static String NOMBRE_LOC_2 = "O";
	
	/**
	 * Nombre de la locación 3
	 */
	public final static String NOMBRE_LOC_3 = "LL";
	
	/**
	 * Nombre de la locación 4
	 */
	public final static String NOMBRE_LOC_4 = "Q";

	/**
	 * Nombre de la locación 5
	 */
	public final static String NOMBRE_LOC_5 = "Caneca";
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Nombre de la locación
	 */
	private String nombre;
	
	/**
	 * Contenedor con los casilleros de la locación
	 */
	private ArrayList casilleros;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

	/**
	 * Método constructor. Inicializa el nombre con el parámetro. Inicializa la lista de casilleros vacía 
	 * @param nombreP nombre de la locación. nombreP != null y nombreP != ""
	 */
	public Locacion(String nombreP)
	{
		nombre = nombreP;
		casilleros = new ArrayList();
	}
	
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
  	
	/**
	 * Retorna el nombre de la locación
	 * @return Nombre de la locación
	 */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
	 * Retorna la lista de casilleros
	 * @return Lista de casilleros
	 */
	public ArrayList darCasilleros()
	{
		return casilleros;
	}
	
	/**
	 * Retorna el casillero que se encuentra en la posición pos de la lista. <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retornó el casillero en la posición dada por parámetro <br>
	 * @param pos Posición del casillero. Entero mayor a cero y menor al tamaño de la lista
	 * @return Casillero en la posición pos
	 */
	public Casillero darCasillero(int pos)
	{
		return (Casillero)casilleros.get(pos);
	}
	
	/**
	 * Crea nuevos casilleros para la locación. 
	 * El id que se asigna a los casillero creados debe ser el nombre de la locación unido al número (un consecutivo) del casillero (Por ejemplo: ML1)
	 * El número del casillero es un número consecutivo que inicia en 1 y se va incrementando a medida que se crea un casillero <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se agregaron nuevos casilleros a la locación <br>
	 * @param tipo Tipo de casilleros a crear. Debe corresponder a alguna de las constantes de la clase Casillero
	 * @param numCasilleros Número de casillero a crear. Entero mayor a cero.
	 */
	public void crearCasilleros(String tipo, int numCasilleros)
	{
		// Recuperando númaro de casilleros ya creados
		int cuantos = darNumCasilleros();
		
		for (int i = 0; i < numCasilleros; i++) {
			Casillero casillero = new Casillero(tipo, nombre+(cuantos+i+1));
			casilleros.add(casillero);
		}
	}
	
	/**
	 * Asigna el casillero cuyo id entra por parámetro <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se cambió a ocupado el casillero con id dado <br>
	 * @param id Id del casillero. id != null y id != ""
	 */
	public void asignarCasillero(String id)
	{
		boolean continuar = true;
		for (int i = 0; i < casilleros.size() && continuar; i++) {
			// Recuperando casillero i
			Casillero temp = (Casillero)casilleros.get(i);
			// Comparando id casillero i con parámetro
			if (temp.darId().equals(id)) {
			// Asignando casillero en caso de coincidir
				temp.asignarCasillero();
				continuar = false;
			}
		}
	}
	
	/**
	 * Des-asigna el casillero cuyo id entra por parámetro <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se cambió a desocupado el casillero con id dado <br>
	 * @param id Id del casillero. id != null y id != ""
	 */
	public void desasignarCasillero(String id)
	{
		boolean continuar = true;
		for (int i = 0; i < casilleros.size() && continuar; i++) {
			// Recuperando casillero i
			Casillero temp = (Casillero)casilleros.get(i);
			// Comparando id casillero i con parámetro
			if (temp.darId().equals(id)) {
			// Desasignando casillero en caso de coincidir
				temp.desasignarCasillero();
				continuar = false;
			}
		}
	}
	
	/**
	 * Devuelve una lista con los IDs de los casilleros disponibles de la locación del mismo tipo que el que entra por parámetro. <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retornó una lista con los casilleros disponibles del tipo dado <br>
	 * @param tipo Tipo de casillero. Corresponde a una de las constantes de la clase Casillero
	 * @return Lista con los id de los casilleros
	 */
	public ArrayList buscarCasilleros(String tipo)
	{
		ArrayList respuesta = new ArrayList();
		
		for (int i = 0; i < casilleros.size(); i++) {
			Casillero temp = (Casillero)casilleros.get(i);
			if (temp.darTipo().equals(tipo) && !temp.estaAsignado()) {
				respuesta.add(temp.darId());
			}
		}
		
		return respuesta;
	}
	
	/**
	 * Devuelve el número de casilleros de la locación <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retornó en número de casilleros en la locación <br>
	 * @return Número de casilleros
	 */
	public int darNumCasilleros()
	{
		return casilleros.size();
	}
	
	/**
	 * Devuelve el número de casilleros asignados en la locación <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retornó el número de casilleros ocupados <br>
	 * @return Número de casilleros
	 */
	public int darNumCasillerosAsignado()
	{
		int respuesta = 0;
		for (int i = 0; i < casilleros.size(); i++) {
			if (((Casillero)casilleros.get(i)).estaAsignado()) {
				respuesta ++;
			}
		}
		return respuesta;
	}
	
	/**
	 * Calcula el porcentaje de casillero asignados (porcentaje de casillero ocupados en la locación #casillerosAsignados/#casilleros) <br>
	 * Si no hay casilleros retorna 0.0 <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retornó el porcentaje de casilleros ocupados <br>
	 * @return Porcentaje de asignados. Valor real mayor o igual cero y menor o igual a 100
	 */
	public double calcularPorcentajeAsignado()
	{
		double respuesta = 0;
		
		if (darNumCasilleros() != 0)
			respuesta = 100*(darNumCasillerosAsignado()/(double)darNumCasilleros());
		
		return respuesta;
	}
	
	/**
	 * Devuelve el nombre del tipo más popular (tipo con mayor número de casilleros asignados) <br>
	 * Si no hay casilleros retorna la constante NINGUNO <br>
	 * Si hay varios tipos populares, informa el primer tipo popular <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retornó el tipo de casillero con mayor número de asignados <br>
	 * @return Nombre del tipo. Corresponde a alguna de las constantes de la clase Casillero
	 */
	public String consultarTipoPopular()
	{
		int tipo1 = 0;
		int tipo2 = 0;
		int tipo3 = 0;
		
		for (int i = 0; i < casilleros.size(); i++) {
			Casillero temp = (Casillero)casilleros.get(i);
			if (temp.darTipo().equals(Casillero.TIPO_1) && temp.estaAsignado())
				tipo1++;
			else if (temp.darTipo().equals(Casillero.TIPO_2) && temp.estaAsignado())
				tipo2++;
			else if (temp.darTipo().equals(Casillero.TIPO_3) && temp.estaAsignado())
				tipo3++;
		}
		
		if (tipo1 == 0 && tipo2 == 0 && tipo3 == 0)
			return Casillero.NINGUNO;
		else if (tipo1 >= tipo2 && tipo1 >= tipo3)
			return Casillero.TIPO_1;
		else if (tipo2 >= tipo1 && tipo2 >= tipo3)
			return Casillero.TIPO_2;
		else
			return Casillero.TIPO_3;
	}

	/**
	 * Indica si la locación tiene casilleros de todos los tipos <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retornó un booleano indicando si la locación tiene o no casilleros de todos los tipos<br>
	 * @return Booleano que indica si la locación tiene casilleros de todos los tipos
	 */
	public boolean tieneTodoTipo() 
	{		
		boolean tipo1 = false;
		boolean tipo2 = false;
		boolean tipo3 = false;
		
		for (int i = 0; i < casilleros.size() && !(tipo1 && tipo2 && tipo3); i++) {
			Casillero temp = (Casillero)casilleros.get(i);
			if (temp.darTipo().equals(Casillero.TIPO_1))
				tipo1 = true;
			else if (temp.darTipo().equals(Casillero.TIPO_2))
				tipo2 = true;
			else if (temp.darTipo().equals(Casillero.TIPO_3))
				tipo3 = true;
		}
		
		return tipo1 && tipo2 && tipo3;
	}

	/**
	 * Devuelve el número de casilleros desocupados del tipo dado por parámetro <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retornó el número de casilleros del tipo dado <br>
	 * @param tipoCasillero Tipo de casillero. Corresponde a una de las constantes de la clase Casillero
	 * @return Número de casilleros. Entero mayor o igual a cero
	 */
	public int darNumCasillerosDesocupados(String tipoCasillero) 
	{
		int respuesta = 0;
		
		for (int i = 0; i < casilleros.size(); i++) {
			Casillero temp = (Casillero)casilleros.get(i);
			if (!temp.estaAsignado() && temp.darTipo().equals(tipoCasillero))
				respuesta++;
		}
		
		return respuesta;
	}
}