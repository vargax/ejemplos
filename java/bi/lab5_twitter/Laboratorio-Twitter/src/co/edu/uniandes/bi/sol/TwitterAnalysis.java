package co.edu.uniandes.bi.sol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import co.edu.uniandes.bi.data.TweetsDAO;
import co.edu.uniandes.bi.data.TwitterStats;
import co.edu.uniandes.bi.exception.ChartBuilderException;
import co.edu.uniandes.bi.graph.ChartBuilder;
import co.edu.uniandes.bi.nlp.AnalysisResult;
import co.edu.uniandes.bi.nlp.EmotionsAnalyzer;
import co.edu.uniandes.bi.twitter.TwitterAsyncAdapter;
import co.edu.uniandes.bi.twitter.TwitterAsyncListener;

/**
 * Laboratorio de twitter
 * @author Curso de BI Uniandes
 */
public class TwitterAnalysis extends TwitterAsyncListener implements Runnable{
	
	// --------------------------------------------------------------------------------------------------
	// Atributos
	// --------------------------------------------------------------------------------------------------
	/**
	 * Persistencia de la aplicación
	 */
	private TweetsDAO persistence;
	
	/**
	 * Servicios de Twitter
	 */
	private TwitterAsyncAdapter twitter;
	
	/**
	 * Página actual de la consulta
	 */
	private int currentPage;
	
	/**
	 * Flag que indica si se está desarrollando el caso de uso 1
	 */
	private boolean useCase1;
	
	
	/**
	 * Log de la instancia
	 */
	private static Logger log;
	
	// --------------------------------------------------------------------------------------------------
	// Constructor
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public TwitterAnalysis() {
		currentPage = 1;
		persistence = TweetsDAO.getInstance();
		try {
			persistence.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		twitter = TwitterAsyncAdapter.getInstance();
		twitter.addTwitterListener(this);
		log = Logger.getLogger(this.getClass().getCanonicalName());
		
	}
	// --------------------------------------------------------------------------------------------------
	// Métodos privados
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Construye un query String dado el conjunto de usuarios dados y el topico dado.
	 * El query construido se envía como topico al método search
	 * @see Search Operators: https://dev.twitter.com/rest/public/search</a> 

	 * 
	 * @param users
	 * @param topic
	 */
	private void searchTweetsFromUsers(String[] users, String topic) 
	{
	    for (String user : users) {
	    	String query = "#"+topic+" @"+user;
	    	twitter.search(query,currentPage);
	    }
	}
	
	private void searchTweetsOfTopic(String topic)
	{	   
        twitter.search(topic,currentPage);
	}
	
	/**
	 * Calcular y generar grafico de los estados de los sentimientos hacia el tema analizado
	 */
	private void getStats() 
	{
		
		try {
			TwitterStats stats = persistence.calculateStats();
			log.info("Stats:");
			log.info("total positive: "+stats.getPositiveCount());
			log.info("total negative: "+stats.getNegativeCount());
			log.info("total neutral: "+stats.getNeutralCount());
			log.info("total tweets: "+stats.getTotalTweets());
			log.info("emotions: "+Arrays.toString(stats.getEmotions()));
			log.info("emotionsOrder: "+Arrays.toString(stats.getEmotionsOrder()));
			
			ChartBuilder.buildEmotionsChart(stats.getEmotions());
			ChartBuilder.buildValenceChart(stats.getPositiveCount(), stats.getNeutralCount(), stats.getNegativeCount(),(useCase1 ? 10: 1500));
			ChartBuilder.buildEmotionsOrderChart(stats.getEmotionsOrder(),(useCase1 ? 10: 1500));
			
			persistence.cleanUp();
			persistence.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ChartBuilderException e) {
			e.printStackTrace();
		}
	}
	
	//Caso de uso 1
	private void searchUsers() {
		String[] friends = new String[]{"WHO", "CDC", "CNN", "cnnbrk", "FoxNews", "dw_english" };
		String topic = "ebola";
		this.searchTweetsFromUsers(friends, topic);
	}
	
	//Caso de uso 2
	private void searchTopic() 
	{
		String topic = "ebola";
		searchTweetsOfTopic(topic);
		//TODO Crear el tópico correspondiente de acuerdo a la descripción del enunciado y llamar al método searchTweetsOfTopic
	    
	    
	
	    
	}
	
	// --------------------------------------------------------------------------------------------------
	// Getters y Setters
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * @return the useCase1
	 */
	public boolean isUseCase1() {
		return useCase1;
	}

	/**
	 * @param useCase1 the useCase1 to set
	 */
	public void setUseCase1(boolean useCase1) {
		this.useCase1 = useCase1;
	}
	
	// --------------------------------------------------------------------------------------------------
	// TwitterAsyncListener
	// --------------------------------------------------------------------------------------------------
	
	@Override
	public void searched(QueryResult result) {
		List<Status> tweets = result.getTweets();
		Collection<AnalysisResult> results = new LinkedList<AnalysisResult>();
		
		AnalysisResult analysis = null;
		log.info("page: "+currentPage+"since_id:"+result.getSinceId()+" max_id:"+result.getMaxId());
		try {
			persistence.storeTweets(tweets);
			for (Status tweet : tweets) {
				analysis = EmotionsAnalyzer.analyze(tweet.getText());
				analysis.setMsgId(tweet.getId());
				results.add(analysis);
			}
			persistence.storeSentiments(results);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Numero de páginas a analizar
		if ( currentPage < 150 && tweets.size() > 0) {
			currentPage++;
			System.out.println("Pasando a página: "+currentPage);
			if ( useCase1 ) {
				searchUsers();
			} else {
				searchTopic();
			}
		} else {
			System.out.println("Consulta terminada. Página: "+currentPage);
			currentPage = 0;
			getStats();
		}
	}
	
	// --------------------------------------------------------------------------------------------------
	// Runnable
	// --------------------------------------------------------------------------------------------------
	
	@Override
	public void run() {
		if (isUseCase1()) {
			executeUseCase1();
		} else {
			executeUseCase2();
		}
		
	}
	
	// --------------------------------------------------------------------------------------------------
	// Casos de uso
	// --------------------------------------------------------------------------------------------------
	
	public void executeUseCase1(){
		useCase1 = true;
		searchUsers();
	}
	
	public void executeUseCase2() {
		useCase1 = false;
		searchTopic();
	}
	
	// --------------------------------------------------------------------------------------------------
	// Main
	// --------------------------------------------------------------------------------------------------
	
	public static void main(String... args) {
		TwitterAnalysis analysis = new TwitterAnalysis();
		String useCase1 ="Caso de uso 1";
		String useCase2 ="Caso de uso 2";
		String[] possibleValues = new String[]{useCase1,useCase2};
		String selectedValue = (String)JOptionPane.showInputDialog(null,
	            "Elija un caso de uso", "Laboratorio - Twitter",
	            JOptionPane.INFORMATION_MESSAGE, null,
	            possibleValues, possibleValues[0]);
		if (selectedValue != null && selectedValue.equals(useCase1)) {
			analysis.setUseCase1(true);
		} else if (selectedValue != null && selectedValue.equals(useCase2)){
			analysis.setUseCase1(false);
		} else {
			System.exit(0);
		}
		new Thread(analysis).start();
		JOptionPane.showMessageDialog(null, "Oprima aceptar cuando el proceso haya terminado");
	}
	
}
