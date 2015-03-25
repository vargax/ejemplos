package isis.parsers.bi.weka.entrega6;

import java.util.ArrayList;

public class Tweet {

	// ---------------------------------------------
	// Atributos
	// ---------------------------------------------
	private String tweetid;
	private String user;
	private String content;
	private String date;
	private String lang;
	private ArrayList <Polarity> sentiments;
	private ArrayList <String> topics;
	
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	public Tweet() {
		this.sentiments = new ArrayList<Polarity>();
		this.topics = new ArrayList<String>();
	}
	
	// ---------------------------------------------
	// Métodos
	// ---------------------------------------------
	public String toString() {
		String string = "ID: "+tweetid+" | Sentiments: "+sentiments.size()+" | Topics: "+topics.size();
		String polarities = " |--> "+this.getSentiments().size()+":";
		for (Polarity p : this.getSentiments()) polarities+=p;
		
		return string+"\n  "+polarities;
	}
	
	// ---------------------------------------------
	// Getters and Setters
	// ---------------------------------------------
	public String getTweetid() {
		return tweetid;
	}

	public void setTweetid(String tweetid) {
		this.tweetid = tweetid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public ArrayList<Polarity> getSentiments() {
		return sentiments;
	}

	public void addSentiment(Polarity sentiment) {
		this.sentiments.add(sentiment);
	}

	public ArrayList<String> getTopics() {
		return topics;
	}

	public void addTopic(String topic) {
		this.topics.add(topic);
	}
}