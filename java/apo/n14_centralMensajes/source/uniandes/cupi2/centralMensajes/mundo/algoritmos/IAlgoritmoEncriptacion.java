/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Yeisson Oviedo - 10 de Mayo, 2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.mundo.algoritmos;

/**
 * 
 * Interface que define los servicios de los algorimtos
 *
 */
public interface IAlgoritmoEncriptacion {
	
    /**
     * Codifica el texto dado
     * @param mensaje Mensaje que se quiere codificar - mensaje != null.
     * @return El texto codificado.
     */
    public String codificarMensaje( String mensaje );
    
    /**
     * Decodifica el texto dado
     * @param mensaje Mensaje que se quiere decodificar - mensaje != null.
     * @return El texto decodificado.
     */
    public String decodificarMensaje( String mensaje );

}
