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
 * Codifica usando el algoritmo de complemento a 2
 */
public class AlgoritmoComplementoA2 extends AlgoritmoAbstracto {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/* (non-Javadoc)
	 * @see uniandes.cupi2.centralMensajes.mundo.algoritmos.AlgoritmoAbstracto#codificar(byte)
	 */
	protected byte codificar(byte b) {
		b = (byte) (~b + 1);
		return b;
	}

	/* (non-Javadoc)
	 * @see uniandes.cupi2.centralMensajes.mundo.algoritmos.AlgoritmoAbstracto#decodificar(byte)
	 */
	protected byte decodificar(byte b) {
		b = (byte) ~(b - 1);
		return b;
	}

}
