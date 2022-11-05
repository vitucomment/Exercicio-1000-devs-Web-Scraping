package br.com.mildevs.WebScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.com.mildevs.WebScraping.passing.Passing;

public class WebScrapingApplication {

	public static void main(String[] args) throws IOException {
		
		String url = "https://br.indeed.com/jobs?q=desenvolvedor+junior";
		
		Document doc = Jsoup.connect(url).userAgent("Edge").get();
		
		Element ul = doc.getElementsByClass("jobsearch-ResultsList css-0").first();
		
		List<Element> vagas = ul.getElementsByTag("li");
		
		List<Passing> vagasObject = new ArrayList<>();
		
		for(Element vaga: vagas) {
			System.out.println(vaga);
		}
		
	}

}
