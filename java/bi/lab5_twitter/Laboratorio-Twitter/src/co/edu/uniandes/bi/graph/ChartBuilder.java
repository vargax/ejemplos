package co.edu.uniandes.bi.graph;

import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Logger;

import org.apache.http.client.utils.URIUtils;

import co.edu.uniandes.bi.exception.ChartBuilderException;
import co.edu.uniandes.bi.nlp.AnalysisResult;

/**
 * Clase que crea gráficos usando Google Charts API
 * @author Sebastian
 * 
 */
public class ChartBuilder {
	
	//--------------------------------------------------------------------------------------------------
	// Constantes
	//--------------------------------------------------------------------------------------------------	
	
	/**
	 * Instancia del log
	 */
	private static final Logger log = Logger.getLogger(ChartBuilder.class.getCanonicalName());
	
	/**
	 * Separador "|"
	 */
	private static final String OR_SEP="%7C";
	
	/**
	 * Esquema por defecto para la url de conexión
	 */
	private static final String DEFAULT_SCHEME="https";
	
	/**
	 * Puerto por defecto para la conexión
	 */
	private static final int DEFAULT_PORT=-1;
	
	/**
	 * Host para crear los gráficos
	 */
	private static final String GOOGLE_CHARTS_HOST="chart.googleapis.com";
	
	/**
	 * Path para crear el gráfico
	 */
	private static final String GOOGLE_CHARTS_PATH="/chart";
	
	//--------------------------------------------------------------------------------------------------
	// Métodos estáticos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Crea un gráfico de radar para mostrar las emociones dadas en el arreglo
	 * @param emotions
	 * @throws ChartBuilderException
	 */
	public static void buildEmotionsChart(double[] emotions) throws ChartBuilderException{
		URI uri;
		try {
			uri = URIUtils.createURI(DEFAULT_SCHEME, GOOGLE_CHARTS_HOST, DEFAULT_PORT, GOOGLE_CHARTS_PATH,
					"chxl=0:"+OR_SEP+"Happiness"+OR_SEP+"Sadness"+OR_SEP+"Fear"+OR_SEP+"Anger"+OR_SEP+"Disgust"+OR_SEP+"Surprise" +
					"&chxs=0,676767,10.5,0,lt,676767" +
					"&chxt=x" +
					"&chs=400x300" +
					"&cht=r"+
					"&chco=FF0000" +
					"&chds=0,1" +
					"&chd=t:"+emotions[AnalysisResult.HAPPINESS]+","+
					emotions[AnalysisResult.SADNESS]+","+
					emotions[AnalysisResult.FEAR]+","+
					emotions[AnalysisResult.ANGER]+","+
					emotions[AnalysisResult.DISGUST]+","+
					emotions[AnalysisResult.SURPRISE]+
					"&chdlp=t"+
					"&chls=1"+
					"&chtt=Emotions"
					, null);
			
			Desktop.getDesktop().browse(uri);
		} catch (Exception e) {
			log.severe("Error al crear la URL para crear el gráfico en Google Chart. Mensaje: "+e.getMessage());
			e.printStackTrace();
			throw new ChartBuilderException("Error al crear la URL para crear el gráfico en Google Chart. Mensaje: "+e.getMessage());
		}
	}
	/**
	 * Crea y muestra en el explorador un gráfico de torta que muestra la distribución de sentimientos dada
	 * @param positiveCount
	 * @param neutralCount
	 * @param negativeCount
	 * @param maxValue rango máximo de los parámetros anteriores
	 * @throws ChartBuilderException
	 */
	public static void buildValenceChart(int positiveCount, int neutralCount, int negativeCount, int maxValue) throws ChartBuilderException {
		URI uri;
		try {
			uri = URIUtils.createURI(DEFAULT_SCHEME, GOOGLE_CHARTS_HOST, DEFAULT_PORT, GOOGLE_CHARTS_PATH,
					"http://chart.apis.google.com/chart" +
					"?chxt=x" +
					"&chbh=a" +
					"&chs=280x225" +
					"&cht=bhg" +
					"&chco=224499,FF0000,E0E0E0" +
					"&chds=0,"+maxValue+",0,"+maxValue+",0,"+maxValue +
					"&chd=t:"+positiveCount+OR_SEP+negativeCount+OR_SEP+neutralCount +
					"&chdl=Positive"+OR_SEP+"Negative"+OR_SEP+"Neutral" +
					"&chdlp=b" +
					"&chtt=Sentiment+distribution", null);
			
			Desktop.getDesktop().browse(uri);
		} catch (Exception e) {
			log.severe("Error al crear la URL para crear el gráfico en Google Chart. Mensaje: "+e.getMessage());
			e.printStackTrace();
			throw new ChartBuilderException("Error al crear la URL para crear el gráfico en Google Chart. Mensaje: "+e.getMessage());
		} 
		
	}
	
	/**
	 * Crea un gráfico que muestra la frencuencia en la que una emoción aparece de primero
	 * @param emotionsFrecuency
	 * @param maxValue
	 * @throws ChartBuilderException
	 */
	public static void buildEmotionsOrderChart(int[] emotionsFrecuency, int maxValue) throws ChartBuilderException{
		URI uri;
		try {
			uri = URIUtils.createURI(DEFAULT_SCHEME, GOOGLE_CHARTS_HOST, DEFAULT_PORT, GOOGLE_CHARTS_PATH,
					"http://chart.apis.google.com/chart" +
					"?chxt=y" +
					"&chbh=a" +
					"&chs=320x285" +
					"&cht=bvg" +
					"&chco=224499,FF0000,008000,FF9900,49188F,990066" +
					"&chds=0,"+maxValue+",0,"+maxValue+",100,"+maxValue+",0,"+maxValue+",0,"+maxValue+",0,"+maxValue +
					"&chd=t:"+emotionsFrecuency[AnalysisResult.HAPPINESS]+OR_SEP+emotionsFrecuency[AnalysisResult.SADNESS]+OR_SEP+emotionsFrecuency[AnalysisResult.FEAR]+OR_SEP+emotionsFrecuency[AnalysisResult.ANGER]+OR_SEP+emotionsFrecuency[AnalysisResult.DISGUST]+OR_SEP+emotionsFrecuency[AnalysisResult.SURPRISE] +
					"&chdl=Happiness"+OR_SEP+"Sadness"+OR_SEP+"Fear"+OR_SEP+"Anger"+OR_SEP+"Disgust"+OR_SEP+"Surprise" +
					"&chdlp=b" +
					"&chtt=First+Sentiment+Frecuency"
					, null);
			
			Desktop.getDesktop().browse(uri);
		} catch (Exception e) {
			log.severe("Error al crear la URL para crear el gráfico en Google Chart. Mensaje: "+e.getMessage());
			e.printStackTrace();
			throw new ChartBuilderException("Error al crear la URL para crear el gráfico en Google Chart. Mensaje: "+e.getMessage());
		}
	}

}
