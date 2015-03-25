package co.edu.uniandes.bi.nlp;

import java.util.Hashtable;

import org.knallgrau.utils.textcat.TextCategorizer;

import co.edu.uniandes.bi.util.Commons;

/**
 * Encuentra el idioma de un texto dado
 * @author Sebastian
 *
 */
public class LangDetector {

	// -------------------------------------------------------------------------------------
	// Constantes
	// -------------------------------------------------------------------------------------
	
	/**
	 * Idioma inglés
	 */
	public static final String ENGLISH="english";
	
	/**
	 * Idioma español
	 */
	public static final String SPANISH="spanish";
	
	/**
	 * Idioma francés
	 */
	public static final String FRENCH="french";

	// -------------------------------------------------------------------------------------
	// Miembros de la clase
	// -------------------------------------------------------------------------------------
	
	/**
	 * Clase que detecta los idiomas
	 */
	private static TextCategorizer categorizer;
	
	/**
	 * Traduce los lenguajes detectados a códigos ISO 639-1
	 */
	private static Hashtable<String, String> textToCode;
	
	static {
		categorizer = new TextCategorizer();
		
		textToCode = new Hashtable<String, String>();
		textToCode.put(ENGLISH, Commons.ENGLISH_LANG);
		textToCode.put(SPANISH, Commons.SPANISH_LANG);
		textToCode.put(FRENCH, Commons.FRENCH_LANG);
	}
	
	// -------------------------------------------------------------------------------------
	// Métodos estáticos
	// -------------------------------------------------------------------------------------
	
	/**
	 * Encuentra el idioma del texto dado
	 * @param text
	 * @return
	 */
	public static String detectLanguage(String text) {
		return textToCode.get(categorizer.categorize(text));
	}

}
