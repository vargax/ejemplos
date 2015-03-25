package co.edu.uniandes.bi.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import twitter4j.Status;
import co.edu.uniandes.bi.nlp.AnalysisResult;
import co.edu.uniandes.bi.nlp.LangDetector;

/**
 * Ofrece los servicios básicos de persistencia
 * 
 * @author Sebastian
 * 
 */
public class TweetsDAO {

	// --------------------------------------------------------------------------------------------------
	// Constantes
	// --------------------------------------------------------------------------------------------------

	/**
	 * Log de la instancia
	 */
	private Logger log;

	/**
	 * Nombre del driver JDBC de la base de datos
	 */
	private static final String DEFAULT_DATABASE_DRIVER = "org.h2.Driver";

	/**
	 * URL de conexión por defecto
	 */
	private static final String DEFAULT_URL = "jdbc:h2:data/";

	/**
	 * Catálogo con el que se trabaja por defecto
	 */
	private static final String DEFAULT_CATALOG = "taller";

	/**
	 * Tabla de tweets
	 */
	private static final String TWEETS_TABLE = "tweets";

	/**
	 * Usuario por defecto
	 */
	private static final String DEFAULT_USER = "sa";

	/**
	 * Contraseña por defecto
	 */
	private static final String DEFAULT_PASSWORD = "";

	// --------------------------------------------------------------------------------------------------
	// Miembros de la clase
	// --------------------------------------------------------------------------------------------------

	/**
	 * Instancia compartida de la clase
	 */
	private static TweetsDAO instance;

	// --------------------------------------------------------------------------------------------------
	// Atributos
	// --------------------------------------------------------------------------------------------------

	/**
	 * Conexión con la base de datos
	 */
	private Connection connection;

	// --------------------------------------------------------------------------------------------------
	// Constructores
	// --------------------------------------------------------------------------------------------------

	/**
	 * Constructor por defecto
	 */
	private TweetsDAO() {
		log = Logger.getLogger(this.getClass().getCanonicalName());
		try {
			connect();
			setup();
		} catch (SQLException e) {
			log.severe("Error de conexión a la base de datos");
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			log.severe("Driver JDBC no encontrado");
			e.printStackTrace();
			System.exit(1);
		}
	}

	// --------------------------------------------------------------------------------------------------
	// Métodos privados
	// --------------------------------------------------------------------------------------------------

	/**
	 * Abre una conexión a la base de datos
	 */
	private void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DEFAULT_DATABASE_DRIVER);
		connection = DriverManager.getConnection(DEFAULT_URL + DEFAULT_CATALOG,
				DEFAULT_USER, DEFAULT_PASSWORD);
		
	}

	/**
	 * Crea la tabla utilizada si no existe
	 * @throws SQLException
	 */
	private void setup() throws SQLException {
		ResultSet rs = connection.getMetaData().getTables(DEFAULT_CATALOG,
				null, TWEETS_TABLE, null);
		
		if (!rs.next()) {
			Statement stm = connection.createStatement();
			stm.execute("CREATE TABLE IF NOT EXISTS tweets (id bigint NOT NULL,created_at date NOT NULL," +
					"from_user varchar(50) NOT NULL, from_user_id bigint NOT NULL, " +
					"iso_lang_code varchar(5), profile_img_url varchar(200) NOT NULL," +
					"message_text varchar(200) NOT NULL)");
			stm.execute("CREATE TABLE IF NOT EXISTS tweet_analytics (id bigint NOT NULL," +
					" polarity double NOT NULL, happiness double NOT NULL, " +
					"sadness double NOT NULL, fear double NOT NULL, anger double NOT NULL," +
					" disgust double NOT NULL, surprise double NOT NULL)");
			stm.execute("CREATE TABLE IF NOT EXISTS tweet_sentiments (id bigint NOT NULL," +
					" happiness INT NOT NULL, " +
					"sadness INT NOT NULL, fear INT NOT NULL, anger INT NOT NULL," +
					" disgust INT NOT NULL, surprise INT NOT NULL)");
			stm.close();
		}
		rs.close();
	}
	
	/**
	 * Calcula la distribución de los sentimientos
	 * según su orden
	 * @param stats
	 * @throws SQLException
	 */
	private void calculateOrderStats(TwitterStats stats) throws SQLException {
		Statement stm = connection.createStatement();
		int emotionsOrder[] = new int[6];
		ResultSet rs = stm.executeQuery("SELECT (SELECT COUNT(*) FROM tweet_sentiments " +
				"WHERE happiness = 1) AS happiness_first," +
				"(SELECT COUNT(*) FROM tweet_sentiments " +
				"WHERE sadness = 1) AS sadness_first, " +
				"(SELECT COUNT(*) FROM tweet_sentiments " +
				"WHERE fear = 1) AS fear_first," +
				"(SELECT COUNT(*) FROM tweet_sentiments " +
				"WHERE anger = 1) AS anger_first," +
				"(SELECT COUNT(*) FROM tweet_sentiments " +
				"WHERE disgust = 1) AS disgust_first," +
				"(SELECT COUNT(*) FROM tweet_sentiments " +
				"WHERE surprise = 1) AS surprise_first");
		
		if (rs.next()) {
			emotionsOrder[AnalysisResult.HAPPINESS] = rs.getInt("happiness_first");
			emotionsOrder[AnalysisResult.SADNESS] =rs.getInt("sadness_first");
			emotionsOrder[AnalysisResult.FEAR] =rs.getInt("fear_first");
			emotionsOrder[AnalysisResult.ANGER] = rs.getInt("anger_first");
			emotionsOrder[AnalysisResult.DISGUST] =rs.getInt("disgust_first");
			emotionsOrder[AnalysisResult.SURPRISE] =rs.getInt("surprise_first");
			
			stats.setEmotionsOrder(emotionsOrder);
			
			rs.close();
		}
		
		stm.close();
	}
	
	
	/**
	 * Calcula la distribución de la polaridad de los tweets y 
	 * los sentimientos
	 * @param stats
	 * @throws SQLException
	 */
	private void calculateBasicStats(TwitterStats stats) throws SQLException {
		Statement stm = connection.createStatement();
		double emotions[] = new double[6];
		ResultSet rs = stm.executeQuery("SELECT (SELECT COUNT(*) FROM tweet_analytics " +
				"WHERE polarity = 1) AS positive_count, (SELECT COUNT(*) " +
				"FROM tweet_analytics WHERE polarity = -1) AS negative_count," +
				"(SELECT COUNT(*) FROM tweet_analytics WHERE polarity = 0) " +
				"AS neutral_count,(SELECT AVG(happiness) FROM tweet_analytics)" +
				" AS avg_happiness,(SELECT AVG(sadness) FROM tweet_analytics) " +
				"AS avg_sadness,(SELECT AVG(fear) FROM tweet_analytics) " +
				"AS avg_fear,(SELECT AVG(anger) FROM tweet_analytics) AS avg_anger," +
				"(SELECT AVG(disgust) FROM tweet_analytics) AS avg_disgust," +
				"(SELECT AVG(surprise) FROM tweet_analytics) AS avg_surprise," +
				" (SELECT COUNT(*) FROM tweet_analytics) AS general_count");
		
		if (rs.next()) {
			stats.setPositiveCount(rs.getInt("positive_count"));
			stats.setNegativeCount(rs.getInt("negative_count"));
			stats.setNeutralCount(rs.getInt("neutral_count"));
			
			emotions[AnalysisResult.HAPPINESS] = rs.getDouble("avg_happiness");
			emotions[AnalysisResult.SADNESS] =rs.getDouble("avg_sadness");
			emotions[AnalysisResult.FEAR] =rs.getDouble("avg_fear");
			emotions[AnalysisResult.ANGER] = rs.getDouble("avg_anger");
			emotions[AnalysisResult.SURPRISE] =rs.getDouble("avg_surprise");
			emotions[AnalysisResult.DISGUST] =rs.getDouble("avg_disgust");
			
			stats.setTotalTweets(rs.getInt("general_count"));
			
			stats.setSentimentRatio( stats.getPositiveCount() / (double) stats.getNegativeCount());
			
			stats.setEmotions(emotions);
			rs.close();
		}
		
		stm.close();
	}

	// --------------------------------------------------------------------------------------------------
	// Métodos públicos
	// --------------------------------------------------------------------------------------------------

	/**
	 * Public void almacena la lista de tweets en la base de datos
	 * @param statuses
	 * @throws SQLException
	 */
	public void storeStatuses(List<Status> statuses) throws SQLException {
		PreparedStatement pstm = null;
		String insert = "INSERT INTO tweets(id,created_at,from_user," +
				"from_user_id,iso_lang_code,profile_img_url," +
				"message_text) VALUES (?,?,?,?,?,?,?)";
		pstm = connection.prepareStatement(insert);
		for (Status status:statuses) {	
			pstm.setLong(1, status.getId());
			pstm.setDate(2, new Date(status.getCreatedAt().getTime()));
			pstm.setString(3, status.getUser().getScreenName());
			pstm.setLong(4, status.getUser().getId());
			pstm.setString(5, LangDetector.detectLanguage(status.getText()));
			pstm.setString(6, status.getUser().getProfileImageURL().toString());
			pstm.setString(7, status.getText());
		
			pstm.executeUpdate();
		}
		pstm.close();
		
	}
	
	
	/**
	 * Public void almacena la lista de tweets en la base de datos
	 * @param tweets 
	 * @throws SQLException
	 */
	public void storeTweets(List<Status> tweets) throws SQLException {
		PreparedStatement pstm = null;
		String insert = "INSERT INTO tweets(id,created_at,from_user," +
				"from_user_id,iso_lang_code,profile_img_url," +
				"message_text) VALUES (?,?,?,?,?,?,?)";
		pstm = connection.prepareStatement(insert);
		int i = 0;
		for (Status tweet:tweets) {
			System.err.println("Estoy en el tweet "+i);
			i++;
			pstm.setLong(1, tweet.getId());
			pstm.setDate(2, new Date(tweet.getCreatedAt().getTime()));
			pstm.setString(3, "");
			pstm.setLong(4, tweet.getId( ));
			pstm.setString(5, tweet.getLang( )!= null ? tweet.getLang( ): LangDetector.detectLanguage(tweet.getText()));
			pstm.setString(6, "");
			pstm.setString(7, tweet.getText());
			
			pstm.executeUpdate();
		}
		pstm.close();
		
	}
	
	/**
	 * Alamacena los sentimientos encontrados en los tweets que se analizaron
	 * @param result
	 * @throws SQLException 
	 */
	public void storeSentiments(Collection<AnalysisResult> result) throws SQLException {
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		
		String insert1 = "INSERT INTO tweet_analytics(id,polarity,happiness,sadness,fear," +
				"anger,disgust,surprise) VALUES (?,?,?,?,?,?,?,?)";
		String insert2 = "INSERT INTO tweet_sentiments(id,happiness,sadness,fear," +
		"anger,disgust,surprise) VALUES (?,?,?,?,?,?,?)";
		
		pstm1 = connection.prepareStatement(insert1);
		pstm2 = connection.prepareStatement(insert2);
		
		double[] emotionsWeight = null;
		int[] emotionsOrder = null;
		
		for(AnalysisResult analysis : result) {
			pstm1.setLong(1, analysis.getMsgId());
			pstm1.setInt(2, analysis.getValence());
			
			emotionsWeight = analysis.getEmotionsWeight();
			
			pstm1.setDouble(3, emotionsWeight[AnalysisResult.HAPPINESS]); 
			pstm1.setDouble(4, emotionsWeight[AnalysisResult.SADNESS]); 
			pstm1.setDouble(5, emotionsWeight[AnalysisResult.FEAR]); 
			pstm1.setDouble(6, emotionsWeight[AnalysisResult.ANGER]); 
			pstm1.setDouble(7, emotionsWeight[AnalysisResult.DISGUST]); 
			pstm1.setDouble(8, emotionsWeight[AnalysisResult.SURPRISE]); 
			
			pstm1.executeUpdate();
			
			emotionsOrder = analysis.getEmotionsOrder();
			
			pstm2.setLong(1, analysis.getMsgId());
			pstm2.setInt(2, emotionsOrder[AnalysisResult.HAPPINESS]); 
			pstm2.setInt(3, emotionsOrder[AnalysisResult.SADNESS]); 
			pstm2.setInt(4, emotionsOrder[AnalysisResult.FEAR]); 
			pstm2.setInt(5, emotionsOrder[AnalysisResult.ANGER]); 
			pstm2.setInt(6, emotionsOrder[AnalysisResult.DISGUST]);
			pstm2.setInt(7, emotionsOrder[AnalysisResult.SURPRISE]); 
			
			pstm2.executeUpdate();
		}

		
		pstm1.close();
		pstm2.close();
	}
	
	/**
	 * Calcula las estadísticas para los tweets analizados
	 * @return stats
	 * @throws SQLException
	 */
	public TwitterStats calculateStats() throws SQLException {
		TwitterStats stats = new TwitterStats();
		calculateBasicStats(stats);
		calculateOrderStats(stats);
		return stats;
	}
	
	/**
	 * Cierra la conexión a la base de datos
	 * 
	 */
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.warning("Ocurrió un error al cerrar la conexión");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Borra todos los registros de las tablas de la base de datos
	 * @throws SQLException
	 */
	public void cleanUp() throws SQLException {
		Statement stm = connection.createStatement();
		stm.execute("DELETE FROM tweets");
		stm.execute("DELETE FROM tweet_analytics");
		stm.execute("DELETE FROM tweet_sentiments");
	}
	
	// --------------------------------------------------------------------------------------------------
	// Métodos estáticos
	// --------------------------------------------------------------------------------------------------

	/**
	 * Obtiene una instancia de la persistencia
	 */
	public static TweetsDAO getInstance() {
		if (instance == null) {
			instance = new TweetsDAO();
		}
		return instance;
	}

}
