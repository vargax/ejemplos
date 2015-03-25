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
 * construye una {@link AlgoritmoNegacion}
 *
 */
public class FabricaAlgoritmoNegacion implements IFabricaAlgoritmo {

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/* (non-Javadoc)
	 * @see uniandes.cupi2.centralMensajes.mundo.algoritmos.IFabricaAlgoritmo#crearAlgoritmo()
	 */
	public IAlgoritmoEncriptacion crearAlgoritmo(){
		return new AlgoritmoNegacion();
	}
}
