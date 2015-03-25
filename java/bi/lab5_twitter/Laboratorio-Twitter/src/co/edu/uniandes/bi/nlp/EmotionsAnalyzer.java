package co.edu.uniandes.bi.nlp;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import synesketch.emotion.Emotion;
import synesketch.emotion.EmotionalState;
import synesketch.emotion.Empathyscope;

/**
 * Permite hacer análisis de sentimientos sobre un texto
 * @author Sebastian
 *
 */
public class EmotionsAnalyzer {
	
	//--------------------------------------------------------------------------------------------------
	// Miembros de la clase
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Instancia de la clase principal de Synesketch
	 * @see <a href="http://www.synesketch.krcadinac.com/wiki/index.php?title=Main_Page">Sysnesketch</a>
	 */
	private static Empathyscope empathy;
	
	/**
	 * Log de la instancia
	 */
	private static Logger log;
	

	
	//--------------------------------------------------------------------------------------------------
	// Static initializers
	//--------------------------------------------------------------------------------------------------
	static {
		try {
			log = Logger.getLogger(EmotionsAnalyzer.class.getCanonicalName());
			empathy = Empathyscope.getInstance();
		} catch (IOException e) {
			log.severe("Error al instanciar la clase principal de Synesketch");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	//--------------------------------------------------------------------------------------------------
	// Métodos estáticos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Obtiene la polaridad/valencia para el texto dado
	 * @param text
	 * @return result
	 * @throws IOException
	 */
	public static AnalysisResult analyze(String text) throws IOException {
		AnalysisResult result = new AnalysisResult();
		double emotionsWeight[] = new double[6];
		int emotionsOrder[] = new int[6];
		Arrays.fill(emotionsOrder, 0);
		EmotionalState state = empathy.feel(text);
		
		result.setValence(state.getValence());
		
		emotionsWeight[AnalysisResult.HAPPINESS] = state.getHappinessWeight();
		emotionsWeight[AnalysisResult.SADNESS] = state.getSadnessWeight();
		emotionsWeight[AnalysisResult.FEAR] = state.getFearWeight();
		emotionsWeight[AnalysisResult.ANGER] = state.getAngerWeight();
		emotionsWeight[AnalysisResult.DISGUST] = state.getDisgustWeight();
		emotionsWeight[AnalysisResult.SURPRISE] = state.getSurpriseWeight();
		
		result.setEmotionsWeight(emotionsWeight);
		
		log.info("encontrando emoción más fuerte");
				
		Emotion emotion = state.getStrongestEmotion();
		
		log.info("emoción encontrada es: "+emotion.getType());
		if (emotion.getType() >= 0 ) {
			emotionsOrder[emotion.getType()] = 1;
		}
			
		log.info("se estableció la emoción más fuerte");
		
		result.setEmotionsOrder(emotionsOrder);
		
		return result;
	}
	
}
