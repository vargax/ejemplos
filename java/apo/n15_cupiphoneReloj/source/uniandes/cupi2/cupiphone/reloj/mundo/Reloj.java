package uniandes.cupi2.cupiphone.reloj.mundo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import uniandes.cupi2.cupIphone.core.ICore;
/**
 * Clase Principal del Mundo. Maneja una fecha que se actualiza 
 * en un hilo de ejecución. También representa el sujeto observable de la interfaz.
 */
public class Reloj extends Observable
{	
	
	private ICore core;
	private Date fecha;
	private DateFormat formatoFecha;
	private FechaThread hilo;
	
	/**
	 * Constructor del Reloj. Por ser una aplicación para cupiphone recibe
	 * la interface del core como parámetro en caso de necesitar localizar
	 * un nuevo componente o el directorio de datos.
	 * @param core
	 */
	public Reloj(ICore core)
	{
		this.core = core;
		formatoFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		fecha =  new Date();
		hilo = new FechaThread(this);
		hilo.start();
	}

	/**
	 * Actualiza su fecha y notifica a los observadores el cambio
	 * @param actual
	 */
	public void actualizar(long actual)
	{ 
		fecha.setTime(actual);
		setChanged();
		notifyObservers(formatoFecha.format(fecha));
	}
	
	/**
	 * Termina la ejecución del reloj finalizando el thread.
	 */
	public void terminar()
	{
		hilo.terminar();
	}
}
