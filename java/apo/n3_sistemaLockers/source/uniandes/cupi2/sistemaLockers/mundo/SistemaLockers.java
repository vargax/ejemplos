/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_sistemaLockers
 * Autor: Catalina Rodriguez - 23-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sistemaLockers.mundo;

import java.util.ArrayList;

/**
 *  Representa el sistema de lockers
 */
public class SistemaLockers
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Número de locaciones en el sistema
	 */
	public final static int NUM_LOCACIONES = 5;
	
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * Arreglo que contiene las diferentes locaciones del sistema de lockers
	 */
	private Locacion[] locaciones;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     *  Constructor del sistema de lockers. <br>
	 * <b>post: </b> Se inicializa el arreglo de locaciones, así como cada 
	 * una de las locaciones es su posición respectiva <br>
     *  [Posición 0] Locación: ML
     *  [Posición 1] Locación: O
     *  [Posición 2] Locación: LL
     *  [Posición 3] Locación: Q
     *  [Posición 4] Locación: Caneca
     */
    public SistemaLockers( )
    {
		locaciones = new Locacion[ NUM_LOCACIONES ];
		
		locaciones[0] = new Locacion(Locacion.NOMBRE_LOC_1);
		locaciones[1] = new Locacion(Locacion.NOMBRE_LOC_2);
		locaciones[2] = new Locacion(Locacion.NOMBRE_LOC_3);
		locaciones[3] = new Locacion(Locacion.NOMBRE_LOC_4);
		locaciones[4] = new Locacion(Locacion.NOMBRE_LOC_5);
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna la locación en la posición que entra como parámetro <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retornó la locación en la posición dada <br>
     * @param pos Posición de la locación dentro del arreglo. pos >= 0 y pos < NUM_LOCACIONES 
     * @return Locación en la posición pos
     */
	public Locacion darLocacion(int pos) 
	{
		return locaciones[pos];
	}
    
    /**
     * Retorna una lista con los id de los casilleros disponibles en la locación y el tipo dados por parámetro <br>
     * Si no hay resultados retorna una lista vacia.
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retornó la lista con los casilleros en la locación y del tipo dados <br>
     * @param nombreLocacion Nombre de la locación. nombreLocacion != null y nombreLocacion != ""
     * @param tipoCasillero Tipo del casillero. Corresponde a una de las constantes de la clase Casillero
     * @return Lista diferente de null, con los id de los casilleros
     */
    public ArrayList buscarCasilleros(String nombreLocacion, String tipoCasillero)
    {
    	ArrayList respuesta = new ArrayList();
    	
    	boolean continuar = true;
    	for (int i = 0; i < locaciones.length && continuar; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			for (int j = 0; j < tempLocacion.darNumCasilleros(); j++) {
    				Casillero tempCasillero = tempLocacion.darCasillero(j);
    				if (!tempCasillero.estaAsignado() && tempCasillero.darTipo().equals(tipoCasillero))
    					respuesta.add(tempCasillero.darId());
    			}
    			continuar = false;
    		}
    	}
    	
    	return respuesta;
    }
    
    /**
     * Método que dado el nombre de una locación, crea una cantidad de casillero de cierto tipo en dicha locación <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se crearon los casilleros en la locación y el tipo dado <br>
     * @param nombreLocacion Nombre de la locación. nombreLocacion != null y nombreLocacion != ""
     * @param tipoCasillero Tipo de los casilleros a crear. Corresponde a una de las constantes de la clase Casillero
     * @param numCasilleros Número de casilleros a crear. Entero mayor a cero
     */
    public void crearCasilleros(String nombreLocacion, String tipoCasillero, int numCasilleros)
    {
    	boolean continuar = true;
    	for (int i = 0; i < locaciones.length && continuar; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			tempLocacion.crearCasilleros(tipoCasillero, numCasilleros);
    			continuar = false;
    		}
    	}
    }
    
    /**
     * Método que dado el nombre de una locación, asigna de acuerdo al id un casillero de dicha locación <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se asignó el casillero en la locación dada <br>
     * @param nombreLocacion Nombre de la locación. nombreLocacion != null y nombreLocacion != ""
     * @param idCasillero Identificador del casillero a asignar. idCasillero != null y idCasillero != ""
     */
    public void asignarCasillero(String nombreLocacion, String idCasillero)
    {
    	boolean continuari = true;
    	for (int i = 0; i < locaciones.length && continuari; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			boolean continuarj = true;
    			for (int j = 0; j < tempLocacion.darNumCasilleros() && continuarj; j++){
    				Casillero tempCasillero = tempLocacion.darCasillero(j);
    				if (tempCasillero.darId().equals(idCasillero)) {
    					tempCasillero.asignarCasillero();
    					continuarj = false;
    				}
    			}
    			continuari = false;
    		}
    	}
    }
    
    /**
     * Método que dado el nombre de una locación, des-asigna de acuerdo al id un casillero de dicha locación <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se desasignó el casillero en la locación dada <br>
     * @param nombreLocacion Nombre de la locación. nombreLocacion != null y nombreLocacion != ""
     * @param idCasillero Identificador del casillero a asignar. idCasillero != null y idCasillero != ""
     */
    public void desasignarCasillero(String nombreLocacion, String idCasillero)
    {
    	boolean continuari = true;
    	for (int i = 0; i < locaciones.length && continuari; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			boolean continuarj = true;
    			for (int j = 0; j < tempLocacion.darNumCasilleros() && continuarj; j++){
    				Casillero tempCasillero = tempLocacion.darCasillero(j);
    				if (tempCasillero.darId().equals(idCasillero)) {
    					tempCasillero.desasignarCasillero();
    					continuarj = false;
    				}
    			}
    			continuari = false;
    		}
    	}
    }
    
    /**
     * Retorna el porcentaje de casilleros asignados de la locación cuyo nombre entra por parámetro <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retornó el porcentaje de casilleros ocupados de la locación dada <br>
     * @param nombreLocacion Nombre de la locación. nombreLocacion != null y nombreLocacion != ""
     * @return Porcentaje de casillero asignados. Valor real mayor o igual a cero y menor o igual a 100
     */
    public double calcularPorcentajeAsignado(String nombreLocacion)
    {
    	double respuesta = 0;
    	boolean continuar = true;
    	for (int i = 0; i < locaciones.length && continuar; i++) {
    		Locacion tempLocacion = locaciones[i]; 
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			respuesta = tempLocacion.calcularPorcentajeAsignado();
    			continuar = false;
    		}
    	}
    	return respuesta;
    }
    
    /**
     * Retorna el nombre del tipo más popular de la locación cuyo nombre entra por parámetro <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retornó el nombre del tipo de casillero más popular de la locación dada <br>
     * @param nombreLocacion Nombre de la locación. nombreLocacion != null y nombreLocacion != ""
     * @return Nombre del tipo. Corresponde a una de las constantes de la clase Casillero
     */
    public String consultarTipoPopular(String nombreLocacion)
    {
    	String respuesta = "";
    	boolean continuar = true;
    	
    	for (int i = 0; i < locaciones.length && continuar; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			continuar = false;
    			respuesta = tempLocacion.consultarTipoPopular();
    		}
    			
    	}
    	return respuesta;
    }
    
    /**
     * Retorna el porcentaje total de casilleros asignados <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retornó el porcentaje de casilleros asignados en todo el sistema <br>
     * @return Porcentaje total de casilleros asignados. Valor real mayor o igual a cero y menor o igual que 100
     */
    public double calcularPorcentajeTotalAsignado()
    {
    	double respuesta = 0;
    	double asignados = 0;
    	double total = 0;
		
		for (int i = 0; i < locaciones.length; i++) {
			Locacion tempLocacion = locaciones[i];
			asignados += tempLocacion.darNumCasillerosAsignado();
			total += tempLocacion.darNumCasilleros();
		}
		if (total != 0)
			respuesta = 100*(asignados/total);
		
		return respuesta;
    }
    
    /**
     * Retorna el nombre de la locación popular (locación con mayor porcentaje de casilleros asignados) <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retornó el nombre de la locación popular en el sistemas <br>
     * @return Nombre de la locación.
     */
    public String consultarLocacionPopular()
    {	
    	double[] porcentaje;
    	String[] nombre;
    	
    	porcentaje = new double[ NUM_LOCACIONES ];
    	nombre = new String[ NUM_LOCACIONES ];
    	
    	for (int i = 0; i < locaciones.length; i++) {
    		Locacion tempLocacion = locaciones[i];
    		
    		porcentaje[i] = tempLocacion.calcularPorcentajeAsignado();
    		nombre[i] = tempLocacion.darNombre();
    	}
    	
    	String respuesta = nombre[0];    	
    	double tempPorcentaje = porcentaje[0];
    	
    	for (int j = 1; j < NUM_LOCACIONES; j++) {
    		if (porcentaje[j] > tempPorcentaje) {
    			respuesta = nombre[j];
    			tempPorcentaje = porcentaje[j];
    		}
    	}
    	return respuesta;
    }
    
    /**
     * Retorna el nombre de la locación con mayor número de casilleros desocupados del tipo dado por parámetro <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retornó el nombre de la locación con más casilleros desocupados del tipo dado <br>
     * @param tipoCasillero Tipo del casillero. Corresponde a una de las constantes de la clase Casillero
     * @return Nombre de la locación
     */
    public String darLocacionConMasCasillerosDesocupadosTipo(String tipoCasillero)
    {
    	int[] desocupados;
    	String[] nombre;
    	
    	desocupados = new int[ NUM_LOCACIONES ];
    	nombre = new String[ NUM_LOCACIONES ];
    	
    	for (int i = 0; i < locaciones.length; i++) {
    		Locacion tempLocacion = locaciones[i];
    		nombre[i] = tempLocacion.darNombre();
    		desocupados[i] = 0;
    		
    		for (int j = 0; j < tempLocacion.darNumCasilleros(); j++) {
    			Casillero tempCasillero = tempLocacion.darCasillero(j);

    			if (tempCasillero.darTipo().equals(tipoCasillero) && !tempCasillero.estaAsignado())
    				desocupados[i]++;
    		}
    	}
    	
    	String respuesta = Casillero.NINGUNO;
    	int desocupado = 0;
    	
    	for (int k = 0; k < NUM_LOCACIONES; k++) {
    		if (desocupados[k] > desocupado) {
    			desocupado = desocupados[k];
    			respuesta = nombre[k];
    		}
    	}
    	return respuesta;
    }
    
    /**
     * Retorna el número de locaciones con todos los tipos de casilleros <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retornó el número de locaciones con todos los tipos de casilleros <br>
     * @return Número de locaciones con todos los tipos de casilleros. Entero mayor o igual a cero y menor o igual a NUM_LOCACIONES
     */
    public int darNumLocacionesTodoTipo()
    {
    	int respuesta = 0;
    	
    	for (int i = 0; i < locaciones.length; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.tieneTodoTipo())
    			respuesta++;
    	}
    	
    	return respuesta;
    }
    
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1 
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}