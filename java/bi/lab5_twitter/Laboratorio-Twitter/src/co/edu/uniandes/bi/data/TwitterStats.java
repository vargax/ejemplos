package co.edu.uniandes.bi.data;

/**
 * Estadísticas para el ejercicio de Sentiment Analysis 
 * @author Sebastian
 *
 */
public class TwitterStats {
	
	//--------------------------------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------------------------------

	/**
	 * Cuenta de tweets con sentimiento positivo
	 */
	private int positiveCount;
	
	/**
	 * Cuenta de tweets con sentimiento negativo
	 */
	private int negativeCount;
	
	/**
	 * Cuenta de tweets con sentimiento neutral
	 */
	private int neutralCount;
	
	/**
	 * Cociente entre tweets positivos y negativos
	 */
	private double sentimentRatio;
	
	/**
	 * Tamaño de la muestra analizada
	 */
	private int totalTweets;
	
	/**
	 * Arreglo de emociones
	 */
	private double[] emotions;
	
	/**
	 * Arreglo que indica la posición de cada emoción
	 */
	private int[] emotionsOrder;

	//--------------------------------------------------------------------------------------------------
	// Getters y Setters
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * @return the positiveCount
	 */
	public int getPositiveCount() {
		return positiveCount;
	}

	/**
	 * @param positiveCount the positiveCount to set
	 */
	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}

	/**
	 * @return the negativeCount
	 */
	public int getNegativeCount() {
		return negativeCount;
	}

	/**
	 * @param negativeCount the negativeCount to set
	 */
	public void setNegativeCount(int negativeCount) {
		this.negativeCount = negativeCount;
	}

	/**
	 * @return the neutralCount
	 */
	public int getNeutralCount() {
		return neutralCount;
	}

	/**
	 * @param neutralCount the neutralCount to set
	 */
	public void setNeutralCount(int neutralCount) {
		this.neutralCount = neutralCount;
	}

	/**
	 * @return the sentimentRatio
	 */
	public double getSentimentRatio() {
		return sentimentRatio;
	}

	/**
	 * @param sentimentRatio the sentimentRatio to set
	 */
	public void setSentimentRatio(double sentimentRatio) {
		this.sentimentRatio = sentimentRatio;
	}

	/**
	 * @return the totalTweets
	 */
	public int getTotalTweets() {
		return totalTweets;
	}

	/**
	 * @param totalTweets the totalTweets to set
	 */
	public void setTotalTweets(int totalTweets) {
		this.totalTweets = totalTweets;
	}

	/**
	 * @param emotions the emotions to set
	 */
	public void setEmotions(double[] emotions) {
		this.emotions = emotions;
	}

	/**
	 * @return the emotions
	 */
	public double[] getEmotions() {
		return emotions;
	}

	/**
	 * @param emotionsOrder the emotionsOrder to set
	 */
	public void setEmotionsOrder(int[] emotionsOrder) {
		this.emotionsOrder = emotionsOrder;
	}

	/**
	 * @return the emotionsOrder
	 */
	public int[] getEmotionsOrder() {
		return emotionsOrder;
	}

	
}
