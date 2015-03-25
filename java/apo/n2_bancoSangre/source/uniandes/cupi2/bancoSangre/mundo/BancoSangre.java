/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_bancoSangre
 * Autor: Catalina Rodríguez - 11-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bancoSangre.mundo;

/**
 *  Clase que representa el banco de donación de sangre
 */
public class BancoSangre
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Representa el sexo femenino del donador.
	 */
	public final static String FEMENINO = "Femenino";
	
	/**
	 * Representa el sexo masculino del donador
	 */
	public final static String MASCULINO = "Masculino";
	
	/**
	 * Indica un mensaje que se lanzará cuando no encuentre ningún tipo de sangre
	 */
	public final static String NINGUNO = "Ninguno";
	
	/**
	 * Indica un mensaje que se lanzará cuando el donador no cumple los requisitos exigidos
	 */
	public final static String ERROR_DONADOR = "No cumple con los requisitos para ser donador";
	
	/**
	 * Indica un mensaje que se lanzará cuando se registre una donación
	 */
	public final static String DONACION = "Donación registrada exitosamente";
	
	/**
	 * Indica un mensaje que se lanzará cuando se intente registrar un tipo de sangre no admitido
	 */
	public final static String ERROR_TIPO_SANGRE = "El banco no recibe donaciones del tipo de sangre ";
	
	/**
	 * Indica un mensaje que se lanzará cuando se suministren bolsas de sangre
	 */
	public final static String SUMINITRAR = "Bolsas suministradas";
	
	/**
	 * Indica un mensaje que se lanzará cuando la cantidad de bolsas no sea suficiente para suministrar
	 */
	public final static String ERROR_CANTIDAD = "No es posible suministrar la sangre. Cantidad disponible insuficiente.";
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Tipo de sangre número 1
	 */
	private TipoSangre tipo1;
	
	/**
	 * Tipo de sangre número 2
	 */
	private TipoSangre tipo2;
	
	/**
	 * Tipo de sangre número 3
	 */
	private TipoSangre tipo3;
	
	/**
	 * Tipo de sangre número 4
	 */
	private TipoSangre tipo4;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Inicializa los tipos de sangre que recolecta el banco <br>
     * <b>post: </b> Se inicializan cuatro tipos de sangre
     * 			     tipo1: Tipo de sangre A-.
     * 				 tipo2: Tipo de sangre B-.
     * 			     tipo3: Tipo de sangre O+.
     * 				 tipo4: Tipo de sangre O-.
     */
    public BancoSangre()
    {
    	tipo1 = new TipoSangre(TipoSangre.TIPO_A, TipoSangre.RH_N);
    	tipo2 = new TipoSangre(TipoSangre.TIPO_B, TipoSangre.RH_N);
    	tipo3 = new TipoSangre(TipoSangre.TIPO_O, TipoSangre.RH_P);
    	tipo4 = new TipoSangre(TipoSangre.TIPO_O, TipoSangre.RH_N);
    	
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
   /**
     * Método que informa cual es el tipo de sangre con el mayor número de bolsas disponibles. 
     * En caso que ningún tipo haya sido suministrado, informa con la constante NINGUNO.
     * En caso de tener varios tipos con las misma cantidad se retorna el primer tipo encontrado
     * @return Cadena indicando el tipo y el RH del tipo de sangre con mayor disponibilidad o la constante NINGUNO
     */
    public String obtenerTipoSangreMasDisponible()
    {
    	int disponible = tipo1.darCantidadDisponible();
    	String tipo = tipo1.darTipo()+tipo1.darRh();
    	
    	if (disponible < tipo2.darCantidadDisponible()){
    		disponible = tipo2.darCantidadDisponible();
    		tipo = tipo2.darTipo()+tipo2.darRh();
    	}
    	
    	if (disponible < tipo3.darCantidadDisponible()){
    		disponible = tipo3.darCantidadDisponible();
    		tipo = tipo3.darTipo()+tipo3.darRh();
    	}
    	
    	if (disponible < tipo4.darCantidadDisponible()){
    		disponible = tipo4.darCantidadDisponible();
    		tipo = tipo4.darTipo()+tipo4.darRh();
    	}
    	
    	if (disponible == 0)
    		tipo = NINGUNO;
    	
    	return tipo;
    }
    
    /**
      * Método que informa cual es el tipo de sangre con el menor número de bolsas disponibles. 
      * En caso que ningún tipo haya sido donado, informa con la constante NINGUNO.
      * En caso de tener varios tipos con las misma cantidad se retorna el primer tipo encontrado
      * @return Cadena indicando el tipo y RH del tipo de sangre con menor disponibilidad o la constante NINGUNO
      */
    public String obtenerTipoSangreMenosDisponible()
    {
    	if (0 == tipo1.darCantidadDisponible() && 0 == tipo2.darCantidadDisponible() && 0 == tipo3.darCantidadDisponible() && 0 == tipo4.darCantidadDisponible())
    		return NINGUNO;
    	
    	int disponible = tipo1.darCantidadDisponible();
    	String tipo = tipo1.darTipo()+tipo1.darRh();
    	
    	if (tipo2.darCantidadDisponible() < disponible){
    		disponible = tipo2.darCantidadDisponible();
    		tipo = tipo2.darTipo()+tipo2.darRh();
    	}
    	
    	if (tipo3.darCantidadDisponible() < disponible){
    		disponible = tipo3.darCantidadDisponible();
    		tipo = tipo3.darTipo()+tipo3.darRh();
    	}
    	
    	if (tipo4.darCantidadDisponible() < disponible){
    		disponible = tipo4.darCantidadDisponible();
    		tipo = tipo4.darTipo()+tipo4.darRh();
    	}
    	
    	return tipo;    	
    }
     
    /**
      * Método que informa cual es el tipo de sangre con el mayor número de bolsas despachadas. 
      * En caso que ningún tipo haya sido donado, informa con la constante NINGUNO.
      * En caso de tener varios tipos con las misma cantidad se retorna el primer tipo encontrado
      * @return Cadena indicando el tipo y el RH del tipo de sangre más suministrado o la constante NINGUNO
      */
    public String obtenerTipoSangreMasSuministrado()
    {
    	int suministrada = tipo1.darCantidadSuministrada();
    	String tipo = tipo1.darTipo()+tipo1.darRh();
    	
    	if (suministrada < tipo2.darCantidadSuministrada()){
    		suministrada = tipo2.darCantidadSuministrada();
    		tipo = tipo2.darTipo()+tipo2.darRh();
    	}
    	
    	if (suministrada < tipo3.darCantidadSuministrada()){
    		suministrada = tipo3.darCantidadSuministrada();
    		tipo = tipo3.darTipo()+tipo3.darRh();
    	}
    	
    	if (suministrada < tipo4.darCantidadSuministrada()){
    		suministrada = tipo4.darCantidadSuministrada();
    		tipo = tipo4.darTipo()+tipo4.darRh();
    	}
    	
    	if (suministrada == 0)
    		tipo = NINGUNO;
    	
    	return tipo;
    }
    
    /**
     * Método que indica si un donador padece o no enfermedades que impidan el proceso de donación
     * @param enferm1 Indica si el donador padece o no de la enfermedad Fiebre reumática 
     * @param enferm2 Indica si el donador padece o no de la enfermedad Epilepsia 
     * @param enferm3 Indica si el donador padece o no de la enfermedad Hepatitis 
     * @param enferm4 Indica si el donador padece o no de la enfermedad Ictericia 
     * @param enferm5 Indica si el donador padece o no de la enfermedad Sífilis u otras enfermedades venéreas
     * @param enferm6 Indica si el donador padece o no de la enfermedad Cáncer 
     * @param enferm7 Indica si el donador padece o no de la enfermedad Problemas cardíacos
     * @return Booleano indicando si el donador padece o no enfermedades
     */
    public boolean donadorPadeceEnfermedad(boolean enferm1, boolean enferm2, boolean enferm3, boolean enferm4, boolean enferm5, boolean enferm6, boolean enferm7)
    {
    	if (enferm1) return true;
    	if (enferm2) return true;
    	if (enferm3) return true;
    	if (enferm4) return true;
    	if (enferm5) return true;
    	if (enferm6) return true;
    	if (enferm7) return true;
    	return false;
    }
    
    /**
     * Método que registra la donación de sangre. Aumenta la cantidad de bolsas disponibles del tipo de sangre donado. <br>
     * Verifica que el donador no tenga ninguna enfermedad y cumpla los requisitos de edad y peso definidos en el documento de descripción
     * @param edad Edad del donador. Entero mayor a cero
     * @param sexo Sexo del donador. Corresponde a una de las constantes de la clase
     * @param peso Peso del donador. Valor real mayor a cero
     * @param tipoSangre Tipo de sangre del donador. Corresponde a una de las constantes de la clase TipoSangre
     * @param rh Factor RH del donador. Corresponde a alguna de las constantes de la clase TipoSangre
     * @param padeceEnfermedad Indica si el donador padece o no de alguna enfermedad que impida realizar la donación
     * @return Cadena indicando el resultado del registro utilizando las constantes de la clase: 
     * 		   Retorna la constante DONACION si la donación fue exitosa.
     * 		   Retorna la constante ERROR_DONADOR si el donador no cumple con los requisitos estipulados. 
     *         Retorna la constante ERROR_TIPO_SANGRE mas el tipo de sangre y RH recibido por parámetro, si este tipo de sangre no existe en el banco.
     */
    public String registarDonacion(int edad, String sexo, double peso, String tipoSangre, String rh, boolean padeceEnfermedad)
    {
    	// Inicializando variable a retornar
    	String respuesta = DONACION;
    	// Calculando tipo de sangre donada
    	String tipoDonante = tipoSangre+rh;
    	// Verificando características del donante
    	if (	edad > 18 && edad < 65
    			&& !padeceEnfermedad
    			&& ((sexo.equals(MASCULINO) && peso > 53) || (sexo.equals(FEMENINO) && peso > 50))) {
    		
    		// Calculando cadenas tipoSangre
    		String t1 = tipo1.darTipo()+tipo1.darRh();
    		String t2 = tipo2.darTipo()+tipo2.darRh();
    		String t3 = tipo3.darTipo()+tipo3.darRh();
    		String t4 = tipo4.darTipo()+tipo4.darRh();
    		
    		// Comparando cadenas tipoSangre contra tipoDonante
    		if (tipoDonante.equals(t1))
    			tipo1.registrarDonacion();
    		else if (tipoDonante.equals(t2))
    			tipo2.registrarDonacion();
    		else if (tipoDonante.equals(t3))
    			tipo3.registrarDonacion();
    		else if (tipoDonante.equals(t4))
    			tipo4.registrarDonacion();
    		else respuesta = ERROR_TIPO_SANGRE+tipoSangre+rh;
    	
    	} else respuesta = ERROR_DONADOR;
    	
    	return respuesta;
    }
    
    /**
     * Método que suministra bolsas de sangre disponibles en el banco de un tipo de sangre y rh recibido por parámetro.
     * @param tipoSangre Tipo de sangre que se desea suministrar. Corresponde a una de las constantes de la clase TipoSangre
     * @param rh Factor RH. Corresponde a una de las constantes de la clase TipoSangre
     * @param cantidad Número de bolsas a suministrar. Entero mayor a cero
     * @return Mensaje indicando el resultado utilizando las constantes de la clase.
     *  	   Retorna la constante SUMINISTRAR si fue exitoso el proceso.
     *         retorna la constante ERROR_CANTIDAD, si no fue posible suministrar las bolsas de sangre.
     */
    public String suministrarSangre(String tipoSangre, String rh, int cantidad)
    {
    	// Inicializando variable a retornar
    	String respuesta = ERROR_CANTIDAD;
    	// Calculando tipo solicitado
    	String tipoSolicitado = tipoSangre+rh;
    	
    	// Calculando cadenas tipoSangre
		String t1 = tipo1.darTipo()+tipo1.darRh();
		String t2 = tipo2.darTipo()+tipo2.darRh();
		String t3 = tipo3.darTipo()+tipo3.darRh();
		String t4 = tipo4.darTipo()+tipo4.darRh();
		
		if (tipoSolicitado.equals(t1)) {
			if (tipo1.suministrar(cantidad)) respuesta = SUMINITRAR;
		} else if (tipoSolicitado.equals(t2)) {
			if (tipo2.suministrar(cantidad)) respuesta = SUMINITRAR;
		} else if (tipoSolicitado.equals(t3)) {
			if (tipo3.suministrar(cantidad)) respuesta = SUMINITRAR;
		} else if (tipoSolicitado.equals(t4)) {
			if (tipo4.suministrar(cantidad)) respuesta = SUMINITRAR;
		}
		return respuesta;
    }
    
   
    
    /**
     * Devuelve el tipo de sangre 1
     * @return Tipo de sangre 1
     */
    public TipoSangre darTipo1()
    {
    	return tipo1;
    }
    
    /**
     * Devuelve el tipo de sangre 2
     * @return Tipo de sangre 2
     */
    public TipoSangre darTipo2()
    {
    	return tipo2;
    }
    
    /**
     * Devuleve el tipo de sangre 3
     * @return Tipo de sangre 3
     */
    public TipoSangre darTipo3()
    {
    	return tipo3;
    }
    
    /**
     * Devuleve el tipo de sangre 4
     * @return Tipo de sangre 4
     */
    public TipoSangre darTipo4()
    {
		return tipo4;
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