package isis.parsers.bi.weka.entrega6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser {
	
	// ---------------------------------------------
	// Constantes
	// ---------------------------------------------	
	private final boolean DEBUG = false;
	
	private final static String ORIGEN_XML_PATH_ENTRENAMIENTO = "./data/bi/entrega6/info-general-tweets-train-tagged.xml"; 
	private final static String DESTINO_ARFF_PATH_ENTRENAMIENTO = "./data/bi/entrega6/datosEntrenamiento.arff";
	
	private final static String ORIGEN_CSV_PATH_PRUEBA = "./data/bi/entrega6/datos-prueba-aethos-post.csv"; 
	private final static String DESTINO_ARFF_PATH_PRUEBA = "./data/bi/entrega6/datosPrueba.arff";
	
	private final static String ATTRIBUTE = "@attribute";
	private final static String STRING = "string";
	private final static String NUMERIC = "numeric";
	private final static String DATE = "date";
	
	private final static String RELATION = "@relation tweets-sentimientos-consistentes-español";
	private final static String DATA = "@data";
	// ---------------------------------------------
	// Atributos
	// ---------------------------------------------
	private ArrayList<Tweet> tweets; // Incluye todos los Tweets de la base datos
	
	private ArrayList<Tweet> tiposDiferentes; // Solo los Tweets que tienen diferentes tipos en el mismo Tweet
	private ArrayList<Tweet> valoresDiferentes; // Solo los Tweets que tienen diferentes valores en el mismo Tweet
	private ArrayList<Tweet> tiposValoresConsistentes; // Solo los tweets que tienen el mismo TIPO y VALOR (todos (P y P+) o (NEU) o (N y N+) en todos sus sentimientos.
	
	// Atributos
	private ArrayList<String> atributosNoCategoricos;
	private ArrayList<String> atributosCategoricos;
	/// Atributos categóricos
	private String lang;
	private String value;
	private String type;
	
	
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	public Parser() throws Exception {
		System.out.println("Inteligencia de Negocios: Entrega 6");
		System.out.println("Parser XML to ARFF");
		this.tweets = new ArrayList<Tweet>();
		this.tiposDiferentes = new ArrayList<Tweet>();
		this.valoresDiferentes = new ArrayList<Tweet>();
		this.tiposValoresConsistentes = new ArrayList<Tweet>();
		
		this.atributosNoCategoricos = new ArrayList<String>();
		this.atributosCategoricos = new ArrayList<String>();
		
		this.lang = ATTRIBUTE+" 'lang' { ";
		this.value = ATTRIBUTE+" 'value' { ";
		this.type = ATTRIBUTE+" 'type' { ";
		
		this.atributosNoCategoricos.add(ATTRIBUTE +" 'tweetid' "+ NUMERIC);
		this.atributosNoCategoricos.add(ATTRIBUTE +" 'user' "+ STRING);
		this.atributosNoCategoricos.add(ATTRIBUTE + " 'content' "+ STRING);
		this.atributosNoCategoricos.add(ATTRIBUTE + " 'date' "+ DATE);
		
		System.err.println("-----------------------------------------");
		System.err.println("DATOS ENTRENAMIENTO");
		System.err.println("-----------------------------------------");
		lector();
		estudio(); // Utilizado para mirar las características de los datos
		escritor();
		
		System.err.println("-----------------------------------------");
		System.err.println("DATOS PRUEBA");
		System.err.println("-----------------------------------------");
		generarARFFprueba();
	}
	
	// ---------------------------------------------
	// Métodos
	// ---------------------------------------------
	private void lector() throws Exception {
		System.err.println("Inicializando SAXParserFactory...");
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		sp.parse(new File(ORIGEN_XML_PATH_ENTRENAMIENTO), new DefaultHandler() {
			// Atributos
			private String tempVal;
			private Tweet newTweet;
			private Polarity newPolarity;
			
			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				tempVal = new String(ch,start,length).replace("'","").replace("\n", "");
			}
			
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//				System.err.println("startElement: "+qName);
				
				if (qName.equals("tweet")) {
					if (newTweet != null) {
						newTweet.addSentiment(newPolarity);
						newPolarity = null;
						tweets.add(newTweet);
						
						if (DEBUG) System.out.println(newTweet);
						
					}
					newTweet = new Tweet(); 
				} else if (qName.equals("polarity")) {
					if (newPolarity != null) {
						newTweet.addSentiment(newPolarity);
					}
					newPolarity = new Polarity();
				}
			}

			@Override
			public void endDocument() throws SAXException {
				if (newTweet != null) {
					newTweet.addSentiment(newPolarity);
					newPolarity = null;
					tweets.add(newTweet);
					
					if (DEBUG) System.out.println("  "+newTweet);
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
//				System.err.println("endElement: "+qName);
				
				if (qName.equals("tweetid")) newTweet.setTweetid(tempVal);
				else if (qName.equals("user")) newTweet.setUser(tempVal);
				else if (qName.equals("content")) newTweet.setContent(tempVal);
				else if (qName.equals("date")) newTweet.setDate(tempVal);
				else if (qName.equals("lang")) newTweet.setLang(tempVal);
				else if (qName.equals("sentiment")) newPolarity = new Polarity();
				else if (qName.equals("entity")) newPolarity.setEntity(tempVal);
				else if (qName.equals("value")) {
					if (tempVal.equals("NONE"))	newPolarity.setValue("O");
					else if (tempVal.equals("NEU"))	newPolarity.setValue("E");
					else if (tempVal.equals("N+"))	newPolarity.setValue("n+");
					else if (tempVal.equals("P+"))	newPolarity.setValue("p+");
					else newPolarity.setValue(tempVal);
				}
				else if (qName.equals("type")) newPolarity.setType(tempVal);
				else if (qName.equals("topic")) newTweet.addTopic(tempVal);
			}
		});
		
		System.out.println("Se han imporado "+tweets.size()+" tweets... ");
	}
	
	// En este método se verifica que si para todos los Tweets el valor del tipo y la polaridad es el mismo
	// para todas las subinstancias de sentimientos de cada Tweet
	private void estudio() {
		System.err.println("Estudiando estructura de los datos...");
		
		for (Tweet tweet : tweets) {
			Stack<String> tipos = new Stack<String>();
			Stack<String> valores = new Stack<String>();
			
			// Se extraen los tipos y valores para este Tweet
			for (Polarity polarity : tweet.getSentiments()) {
				if (tipos.empty() || !tipos.peek().equals(polarity.getType())) tipos.push(polarity.getType());
				if (valores.empty() || !valores.peek().equals(polarity.getValue())) valores.push(polarity.getValue());
			}
			
			// Se procesan estos tipos y valores para conformar los atributos CATEGÓRICOS
			if (!lang.contains(tweet.getLang())) lang=lang+"'"+tweet.getLang()+"', ";
			
			for (String tipo : tipos)
				if (!type.contains(tipo)) type=type+"'"+tipo+"', ";
			
			for (String valor : valores)
				if (!value.contains(valor)) value=value+"'"+valor+"', ";
			
			// Se clasifican los Tweets en base a su "utilidad" para el entrenamiento 
			if (tipos.size() > 1) {
				if (DEBUG) System.out.println("  Tweet "+tweet.getTweetid()+" tiene "+tipos.size()+" tipos: "+tipos.toString());
				tiposDiferentes.add(tweet);
			}
			if (valores.size() > 1) {
				if (DEBUG) System.out.println("  Tweet "+tweet.getTweetid()+" tiene "+valores.size()+" valores: "+valores.toString());
				valoresDiferentes.add(tweet);
			}
			if (tipos.size() == 1) { // Estos son lo tweets cuyo sentimiento es siempre el mismo (AGREE o DISAGREE)
				// Se va a evaluar ahora si su fuerza es siempre consistente (siempre positivo, siempre neutral o siempre negativo):
				String valor = valores.pop();
				//valor = valor.length() > 2 ? ""+valor.charAt(1) : ""+valor.charAt(0); // valor para P y P+ es P, para NEU es E y para N y N+ es N
				
				boolean agregar = true;
				for (String v : valores)
					if (!v.contains(valor.toLowerCase())) {
						agregar = false;
						break;
					}
				
				if (agregar) tiposValoresConsistentes.add(tweet);
			}
		}
		if (tiposDiferentes.size() != 0) System.out.println("Existen "+tiposDiferentes.size()+" Tweets con tipos de polaridades diferentes en el mismo Tweet!");
		if (valoresDiferentes.size() != 0) System.out.println("Existen "+valoresDiferentes.size()+" Tweets con valores de polaridades diferentes en el mismo Tweet!");
		System.out.println("Se han identificado "+tiposValoresConsistentes.size()+" tweets con 'sentimientos consistentes'...");
		if (DEBUG) for (Tweet t : tiposValoresConsistentes) System.out.println(t);
		
		atributosCategoricos.add(this.lang.substring(0, this.lang.length()-2)+"}");
		atributosCategoricos.add(this.value.substring(0, this.value.length()-2)+"}");
		atributosCategoricos.add(this.type.substring(0, this.type.length()-2)+"}");
		System.out.println("Los atributos no categóricos son:");
		for (String atributoNoCategorico : atributosNoCategoricos) System.out.println("  "+atributoNoCategorico);
		System.out.println("Los atributos categóricos conformados son:");
		for (String atributoCategorico : atributosCategoricos) System.out.println("  "+atributoCategorico);
	}
	
	private void escritor() throws Exception {
		System.err.println("Generando ARFF...");
		PrintWriter pw = new PrintWriter(new File(DESTINO_ARFF_PATH_ENTRENAMIENTO));
		
		// Encabezados
		pw.println(RELATION);
		for (String s : atributosNoCategoricos) pw.println(s);
		for (String s : atributosCategoricos) pw.println(s);
		pw.println(DATA);
		for (Tweet t : tiposValoresConsistentes)
			pw.println(t.getTweetid()+",'"+t.getUser()+"','"+t.getContent()+"','"+t.getDate()+"',"
					+t.getLang()+",'"+t.getSentiments().get(0).getValue()+"','"+t.getSentiments().get(0).getType()+"'");
		
		pw.close();
	}
	
	private void generarARFFprueba() throws Exception {
		BufferedReader bf = new BufferedReader(new FileReader(new File(ORIGEN_CSV_PATH_PRUEBA)));
		PrintWriter pw = new PrintWriter(new File(DESTINO_ARFF_PATH_PRUEBA));
		
		pw.println("@relation posts-aethos");
		pw.println(ATTRIBUTE+" post string");
		
		int i = 0;
		String lineaActual = bf.readLine();
		pw.println(DATA);
		while (lineaActual != null) {
			pw.println("'"+lineaActual.replace("'","")+"'");
			lineaActual = bf.readLine();
			i++;
		}
		System.out.println("Se importaron "+i+" posts...");
		bf.close();
		pw.close();
	}
	// ---------------------------------------------
	// MAIN
	// ---------------------------------------------
	public static void main(String[] args) {
		try {
			new Parser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
