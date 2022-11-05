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

		String url = "https://br.indeed.com/jobs?q=desenvolvedor+java";

		Document doc = Jsoup.connect(url).userAgent("Edge").get();

		Element ul = doc.getElementsByClass("jobsearch-ResultsList css-0").first();

		List<Element> vagas = ul.getElementsByTag("li");

		List<Passing> vagasObject = new ArrayList<>();

		for (Element vaga : vagas) {
			
			boolean tituloIsEmpty = vaga.getElementsByTag("h2").text().equalsIgnoreCase("");
			boolean empresaIsEmpty = vaga.getElementsByClass("companyName").text().equalsIgnoreCase("");
			boolean tipoDeVagaIsEmpty = vaga.getElementsByClass("companyLocation").text().equalsIgnoreCase("");
			boolean salarioIsEmpty = vaga.getElementsByClass("metadata salary-snippet-container").isEmpty();
			
			if (!tituloIsEmpty && !empresaIsEmpty && !tipoDeVagaIsEmpty) {
				String empresa = vaga.getElementsByClass("companyName").get(0).text();
				String titulo = vaga.getElementsByTag("h2").get(0).text();
				String tipoDeVaga = vaga.getElementsByClass("companyLocation").get(0).text().split(",")[0];
				String salario = "Não informado";
				String link = vaga.select("a[href]").attr("abs:href");
				if(!salarioIsEmpty) {
					salario = vaga.getElementsByClass("metadata salary-snippet-container").get(0).text();
				}
				
				Passing passing = new Passing(empresa, titulo, tipoDeVaga, salario, link);
				
				vagasObject.add(passing);
			}
		}
		
		for(Passing vaga : vagasObject) {
			System.out.println(vaga);
		}
	}

}