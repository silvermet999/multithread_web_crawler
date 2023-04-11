package dWebCrawler;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<DWebCrawler> bots = new ArrayList<>();
		bots.add(new DWebCrawler("https://abcnews.go.com/", 1));
		bots.add(new DWebCrawler("https://www.npr.org/", 2));
		bots.add(new DWebCrawler("https://www.nytimes.com/", 3));
		
		for(DWebCrawler w : bots) {
			try {
				w.getThread().join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
