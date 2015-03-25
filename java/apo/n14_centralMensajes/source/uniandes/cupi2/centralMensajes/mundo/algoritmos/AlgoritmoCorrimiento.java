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
 * Codifica usando el algoritmo de corrimiento 
 *
 */
public class AlgoritmoCorrimiento extends AlgoritmoAbstracto {

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Constante para determinar cuanto va a ser el corrimiento en alguna
	 * ejecución en particular
	 */
	public static short corrimiento = -1;
	
	/**
	 * Inicializa la constante de corrimiento 
	 */
	public void inicializarCorrimiento(){
		if (corrimiento != -1)
			return;
		corrimiento = (short)(Math.random() * 6 + 1);
	}
	
	
	/* (non-Javadoc)
	 * @see uniandes.cupi2.centralMensajes.mundo.algoritmos.AlgoritmoAbstracto#codificar(byte)
	 */
	protected byte codificar(byte b) {
		int value = 0x00ff & b;
		int temp = value << corrimiento;
		int bAlto = ((temp & 0xff00)>>8);
		int bBajo = (temp & 0x00ff);	
		return (byte) (bBajo | bAlto);
	}

	
	/* (non-Javadoc)
	 * @see uniandes.cupi2.centralMensajes.mundo.algoritmos.AlgoritmoAbstracto#decodificar(byte)
	 */
	protected byte decodificar(byte b) {
		int value = 0x00ff & b;
		int temp = value << 8 - corrimiento;
		int bAlto = ((temp & 0xff00)>>8);
		int bBajo = (temp & 0x00ff);	
		
		Integer.toBinaryString(b);
		Integer.toBinaryString(value);
		Integer.toBinaryString(temp);
		Integer.toBinaryString(bAlto);
		Integer.toBinaryString(bBajo);
		Integer.toBinaryString(bAlto | bBajo);
		return (byte) (bBajo | bAlto);
	}

}
