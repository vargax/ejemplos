package uniandes.cupi2.cupiClima.mundo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase que representa el clima de una ciudad
 * @author cvargasc
 */
public class Clima implements Serializable {
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------
	// Atributos
	// ----------------------------------------------------
	/**
	 * La fecha en la cual se recuperó el feed
	 */
	private Date fechaRecuperacion;
	/**
	 * La fecha del pronóstico
	 */
	private String fecha;
	/**
	 * La condición del clima
	 */
	private String condicion;
	/**
	 * La temperatura
	 */
	private int temperatura;
	/**
	 * El código de la condición
	 */
	private int codigo;
	/**
	 * Temperatura mínima
	 */
	private String minima;
	/**
	 * Temperatura máxima
	 */
	private String maxima;
	/**
	 * Dia pronóstico
	 */
	private String pronostico;
	// ----------------------------------------------------
	// Constructor
	// ----------------------------------------------------
	/**
	 * Carga la información del clima
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public Clima(final String woeid) {
		String url = "http://weather.yahooapis.com/forecastrss?w="+woeid+"&u=c";
		//System.out.println(url);
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			sp.parse(url,new DefaultHandler() {
				int actual = 0;
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (qName.equals("yweather:condition")) {
						condicion = attributes.getValue(0);
						codigo = Integer.parseInt(attributes.getValue(1));
						temperatura = Integer.parseInt(attributes.getValue(2));
						fecha = attributes.getValue(3);
					} else if(qName.equals("yweather:forecast")) {
						actual++;
						if (actual == 2) {
							minima = attributes.getValue(2);
							maxima = attributes.getValue(3);
							pronostico = attributes.getValue(0);
						}
					}
				}
			});
			
			fechaRecuperacion = new Date();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ----------------------------------------------------
	// Métodos
	// ----------------------------------------------------
	public String[] darCondiciones() {
		String[] respuesta = new String[6];
		respuesta[2] = ""+fechaRecuperacion;
		respuesta[3] = fecha;
		respuesta[4] = condicion;
		respuesta[5] = temperatura+" "+minima+" "+maxima+" "+pronostico;
		return respuesta;
	}
	public String toString() {
		return "CLIMA: Condición: "+condicion+" Temperatura: "+temperatura+" Fecha: "+fecha+" Código: "+codigo;
	}
	public Date getFechaRecuperacion() {
		return fechaRecuperacion;
	}
	public int getTemperatura() {
		return temperatura;
	}
}
