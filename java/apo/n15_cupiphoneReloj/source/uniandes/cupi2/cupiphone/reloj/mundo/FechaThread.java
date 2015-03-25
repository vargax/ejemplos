package uniandes.cupi2.cupiphone.reloj.mundo;


/**
 * Este Thread se encarga de hacer la consulta del tiempo cada segundo
 * y cambiar la fecha del reloj
 */
public class FechaThread extends Thread
{
	
	private boolean seguir;
	private Reloj reloj;
	
	/**
	 * Construye un nuevo hilo 
	 * @param papa
	 */
	public FechaThread (Reloj papa)
	{
		seguir = true;
		reloj = papa;	
	}
	
	/**
	 * Ejecuci—n del hilo. Pregunta el tiempo cada segundo y
	 * cambia esta fecha en el reloj
	 */
	public void run()
	{
		while(seguir)
		{ 					
			reloj.actualizar(System.currentTimeMillis());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Para terminar la ejecuci—n del thread cambia el estado de seguir
	 */
	public void terminar()
	{
		seguir = false;
	}
}

