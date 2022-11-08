package br.com.mildevs.WebScraping.sites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.com.mildevs.WebScraping.config.ConfiguracaoWeb;
import br.com.mildevs.WebScraping.passing.Passing;
import br.com.mildevs.WebScraping.util.Scraping;

public class ScrapingIndeed {

	Scraping scrap = new Scraping();
	ConfiguracaoWeb configuracao = new ConfiguracaoWeb();

	public String parametrosDeBusca(String [] parametros) {
		String url = "https://br.indeed.com/jobs?q=";
		for (int i = 0; i < parametros.length; i++) {
			url += parametros[i];
			url += "+";
		}
		return url;
	}

	public List<Passing> listaDeVagas(String url) throws IOException, InterruptedException {
		Document doc = ConfiguracaoWeb.configuracao(url);
		Element ul = doc.getElementsByClass("jobsearch-ResultsList css-0").first();
		List<Element> vagas = ul.getElementsByTag("li");
		List<Passing> vagasObject = new ArrayList<>();
		for (Element vaga : vagas) {

			boolean tituloIsEmpty = scrap.tituloIsEmpty(vaga, "h2");
			boolean empresaIsEmpty = scrap.empresaIsEmpty(vaga, "companyName");
			boolean tipoDeVagaIsEmpty = scrap.tipoDeVagaIsEmpty(vaga, "companyLocation");

			if (!tituloIsEmpty && !empresaIsEmpty && !tipoDeVagaIsEmpty) {
				String empresa = scrap.empresa(vaga, "companyName");
				String titulo = scrap.titulo(vaga, "h2");
				String tipoDeVaga = scrap.tipoDeVaga(vaga, "companyLocation");
				String salario = scrap.salario(vaga, "metadata salary-snippet-container");
				String link = scrap.link(vaga);

				Passing passing = new Passing(empresa, titulo, tipoDeVaga, salario, link);

				vagasObject.add(passing);
			}
		}
		return vagasObject;
	}

}
