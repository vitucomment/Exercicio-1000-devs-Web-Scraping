package br.com.mildevs.WebScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.mildevs.WebScraping.passing.Passing;

public class WebScrapingApplication {

	public static void main(String[] args) throws IOException {

		String url = "https://br.indeed.com/jobs?q=desenvolvedor+junior&l=&vjk=ccb0e3243058cffe";

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
				List<Element> titulo = vaga.getElementsByTag("h2");
				
				Elements link = vaga.select("a[href]");
				
				List<Element> empresa = vaga.getElementsByClass("companyName");
				List<Element> tipoDeVaga = vaga.getElementsByClass("companyLocation");
				
				System.out.println(titulo.get(0).text());
				System.out.println(link.attr("abs:href"));
				System.out.println(empresa.get(0).text());
				System.out.println(tipoDeVaga.get(0).text().split(",")[0]);
				if(!salarioIsEmpty) {
					List<Element> salario = vaga.getElementsByClass("metadata salary-snippet-container");
					System.out.println(salario.get(0).text());
				}
				System.out.println();
			}

			
		}

	}

}
