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
public class Camion 
{
	/**
	 * Orden del id del camion (numero de digitos)
	 */
	private static final int ID_ORDER = 10000000;

	/**
	 * Valor minimo del id del camion
	 */
	private static final int ID_MIN = 123456789;

	/**
	 * Valor maximo de los grados de la latitud
	 */
	private static final int LIM_LATITUD = 180;

	/**
	 * Valor maximo de los grados de la longitud
	 */
	private static final int LIM_LONGITUD = 90;

	/**
	 * Valor maximo de los minutos
	 */
	private static final int LIM_MINUTOS = 60;

	/**
	 * Valor maximo de los segundos
	 */
	private static final int LIM_SEGUNDOS = 60;

	/**
	 * Id del camion
	 */
	private String id;

	/**
	 * Grados de la latitud
	 */
	private int latitud_grados;

	/**
	 * Minutos de la latitud
	 */
	private int latitud_minutos;

	/**
	 * Segundos de la latitud
	 */
	private int latitud_segundos;

	/**
	 * Grados de la longitud
	 */
	private int longitud_grados;

	/**
	 * Minutos de la longitud
	 */
	private int longitud_minutos;

	/**
	 * Segundos de la longitud
	 */
	private int longitud_segundos;


	/**
	 * Genera un camion con valores aleatorios
	 * @param r Objeto de donde provienen los valores aleatorios
	 */
	public Camion(Random r) {
		id = "" + (r.nextInt(ID_ORDER) + ID_MIN);
		latitud_grados = r.nextInt(LIM_LATITUD);
		latitud_minutos = r.nextInt(LIM_MINUTOS);
		latitud_segundos = r.nextInt(LIM_SEGUNDOS);
		longitud_grados = r.nextInt(LIM_LONGITUD);
		longitud_minutos = r.nextInt(LIM_MINUTOS);
		longitud_segundos = r.nextInt(LIM_SEGUNDOS);
	}


	/**
	 * Retorna la ID
	 * @return
	 */
	public String getId() 
	{
		return id;
	}

	/**
	 * Retorna la Latitud
	 * @return grados-minutos-segundos
	 */
	public String getLatitud()
	{
		return latitud_grados + "-" + latitud_minutos + "-" + latitud_segundos;
	}

	/**
	 * Retorna la longitud
	 * @return grados-minutos-segundos
	 */
	public String getLongitud()
	{
		return longitud_grados + "-" + longitud_minutos + "-" + longitud_segundos;
	}

	/**
	 * Modifica las coordenadas de acuerdo a los valores por parametro
	 * @param nLat segundos a mover en la latitud
	 * @param nLon segundos a mover en la longitud
	 */
	public void mover( int nLat, int nLon )
	{

		int lat = nLat;//(nLat!=0)?nLat/Math.abs(nLat):0;
		int lon = nLon;//(nLon!=0)?nLon/Math.abs(nLon):0;

		// Chequea un cambio de latitud válida, caso contrario no hace nada
		if( latitud_segundos + lat >= 0 && latitud_segundos + lat < LIM_SEGUNDOS )
			latitud_segundos += lat;
		else if( latitud_segundos + lat >= LIM_SEGUNDOS )
		{
			int seg = (latitud_segundos+lat)%LIM_SEGUNDOS;
			int min = (latitud_segundos+lat)/LIM_SEGUNDOS;
			if( latitud_minutos + min >= 0 && latitud_minutos + min < LIM_MINUTOS )
			{
				latitud_segundos = seg;
				latitud_minutos += min;
			}
			else if( latitud_minutos + min >= LIM_MINUTOS )
			{
				int grad = (latitud_minutos+min)/LIM_MINUTOS;
				min = (latitud_minutos+min)%LIM_MINUTOS;
				if( latitud_grados + grad < LIM_LATITUD )
				{
					latitud_segundos = seg;
					latitud_minutos = min;
					latitud_grados += grad;
				}
			}
		}
		else if( latitud_segundos + lat < 0 )
		{
			int seg = (latitud_segundos+lat)%LIM_SEGUNDOS + LIM_SEGUNDOS;
			int min = (latitud_segundos+lat)/LIM_SEGUNDOS-1;
			if( latitud_minutos + min >= 0 && latitud_minutos + min < LIM_MINUTOS )
			{
				latitud_segundos = seg;
				latitud_minutos += min;
			}
			else if( latitud_minutos + min < 0 )
			{
				int grad = (latitud_minutos+min)/LIM_MINUTOS-1;
				min = (latitud_minutos+min)%LIM_MINUTOS + LIM_MINUTOS;
				if( latitud_grados + grad < 0 )
				{
					latitud_segundos = seg;
					latitud_minutos = min;
					latitud_grados += grad;
				}
			}
		}

		//Chequea un cambio de longitud válida, caso contrario no hace nada
		if( longitud_segundos + lat >= 0 && longitud_segundos + lat < LIM_SEGUNDOS )
			longitud_segundos += lat;
		else if( longitud_segundos + lat >= LIM_SEGUNDOS )
		{
			int seg = (longitud_segundos+lat)%LIM_SEGUNDOS;
			int min = (longitud_segundos+lat)/LIM_SEGUNDOS;
			if( longitud_minutos + min >= 0 && longitud_minutos + min < LIM_MINUTOS )
			{
				longitud_segundos = seg;
				longitud_minutos += min;
			}
			else if( longitud_minutos + min >= LIM_MINUTOS )
			{
				int grad = (longitud_minutos+min)/LIM_MINUTOS;
				min = (longitud_minutos+min)%LIM_MINUTOS;
				if( longitud_grados + grad < LIM_LONGITUD )
				{
					longitud_segundos = seg;
					longitud_minutos = min;
					longitud_grados += grad;
				}
			}
		}
		else if( longitud_segundos + lat < 0 )
		{
			int seg = (longitud_segundos+lat)%LIM_SEGUNDOS + LIM_SEGUNDOS;
			int min = (longitud_segundos+lat)/LIM_SEGUNDOS-1;
			if( longitud_minutos + min >= 0 && longitud_minutos + min < LIM_MINUTOS )
			{
				longitud_segundos = seg;
				longitud_minutos += min;
			}
			else if( longitud_minutos + min < 0 )
			{
				int grad = (longitud_minutos+min)/LIM_MINUTOS-1;
				min = (longitud_minutos+min)%LIM_MINUTOS + LIM_MINUTOS;
				if( longitud_grados + grad < 0 )
				{
					longitud_segundos = seg;
					longitud_minutos = min;
					longitud_grados += grad;
				}
			}
		}
	}
}
