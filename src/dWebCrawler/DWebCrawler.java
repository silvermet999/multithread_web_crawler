package dWebCrawler;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class DWebCrawler implements Runnable{ //distributed
	private static final int MAX_DEPTH = 3; //depth
	private Thread thread;
	private String first_link;
	private ArrayList<String> visitedLinks = new ArrayList<String>();
	private int ID;
	
	public DWebCrawler(String link, int num) { //constructor
		System.out.print("web crawler created");
		first_link = link;
		ID = num;
		
		thread = new Thread(this); //bot runs
		thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		crawl(1, first_link);
	}
	private void crawl(int level, String url) {
		if(level <= MAX_DEPTH) {
			Document doc = request(url); //get doc
			if(doc!= null) {
				for(Element link : doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if(visitedLinks.contains(next_link) == false) {
						crawl(level++, next_link);
					}
				}
			}
		} 
	}

	private Document request(String url) {
		try {
			Connection con = Jsoup.connect(url); 
			Document doc = con.get();
			
			if (con.response().statusCode()==200) {
				System.out.println("\n**Bot ID:"+ ID +" Received Webpage at "+ url);
				String title = doc.title();
				System.out.println(title);
				visitedLinks.add(url);
				return doc;
			}
			return null;
		}
		catch(IOException e) {
			return null;
		}
	}
	
	public Thread getThread() {
		return thread;
	}
}
