/**
 * 
 */
package co.edu.uniandes.bi.sol;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collection;



import twitter4j.Status;
import co.edu.uniandes.bi.exception.DataAccessException;
import co.edu.uniandes.bi.twitter.TwitterSearchAdapter;

/**
 * @author Sebastian
 *
 */
public class TwitterExport {
	
	public static void writeTweets(TwitterSearchAdapter twitter, String[] keywords, String path) throws FileNotFoundException, DataAccessException, InterruptedException {
		
		PrintWriter out = new PrintWriter(path);
		for ( String keyword : keywords) {
			Collection<Status> tweets = twitter.searchPopularInEnglish(keyword);
			System.out.println(tweets.size());
			for ( Status t : tweets ) {
				out.println(t.getText());
			}
			Thread.sleep(2000);
		}
		
		out.close();
		
	}
	
	
	
	public static void main (String...args) {
		TwitterSearchAdapter twitter  = TwitterSearchAdapter.getInstance();
		
		
		try {
			
			//writeTweets(twitter, new String[] {"good", "great", "awesome", "fantastic","terrific", "beautiful", "excellent", "wonderful", "well", "like", ":)"},"./data/pos.txt");
			writeTweets(twitter, new String[] {"delicious","admirable","clever"," gorgeous", "virtuous", "enjoyable", "amusing", "genuine",  "valuable", "sweet", "worthy","yummy", "exemplary", "competent", "respectable", "reliable", "exquisite", "creditable", "lovely", "agreeable", "skilled","beneficial"},"./data/positive.txt");
			System.out.println("hold on");
			Thread.sleep(5000);
			System.out.println("again");
			//writeTweets(twitter, new String[] {"bad", "lame", "sad", "horrible", "awful", "ugly", ":(", "sorrow", "broken", "depressing"},"./data/neg.txt");
			writeTweets(twitter, new String[] {"wrong", "damaging", "corrupt", "badness", "defective", "disobedient", "faulty", "harmful", "immoral", "inadequate", "inferior", "lousy","miserable", "miserably","offensive","painful","poor", "poorly", "regretful", "unpleasant", "vicious", "vile" },"./data/negative.txt");
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
