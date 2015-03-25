package uniandes.infracom.SisTrans;

import java.util.Random;

/**
 * @author David Susa Gutierrez
 * @author Juan Sebastian Rojas.
 * Infraestructura Computacional
 * Universidad de los Andes
 * Algoritmos tomados de: http://www.java2s.com/Code/Java/Security/CatalogSecurity.htm
 * http://www.bouncycastle.org/
 */
public class MueveCamiones extends Thread 
{
	/**
	 * Milisegundos entre refrescos
	 */
	public static final long TIEMPO_REFRESCO = 5000;
	
	/**
	 * Radio de movimiento de los camiones. Se recomienda numero par
	 */
	public static final int RADIO = 20;
	
	/**
	 * Camiones en el sistema
	 */
	private Camion[] camiones;
	
	/**
	 * Objeto que genera los numeros pseudoaleatorios
	 */
	private Random random;
	
	/**
	 * Valor auxiliar para el radio
	 */
	private int i1;
	
	/**
	 * Valor auxiliar para el radio
	 */
	private int i2;
	
	/**
	 * Constructor de la clase
	 * @param c
	 */
	public MueveCamiones( Camion[] c )
	{
		camiones = c;
		random = new Random();
		i1 = RADIO+1;
		i2 = RADIO/2;
	}
	
	/**
	 * Metodo del thread
	 * Cambia las posiciones de los camiones de acuerdo a los valores de las constantes
	 */
	public void run()
	{
		while( true )
		{
			try {
				sleep(TIEMPO_REFRESCO);
				for (int i = 0; i < camiones.length; i++) {
					camiones[i].mover(random.nextInt(i1)-i2, random.nextInt(i1)-i2);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
