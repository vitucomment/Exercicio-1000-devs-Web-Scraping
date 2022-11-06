package br.com.mildevs.WebScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.com.mildevs.WebScraping.passing.Passing;

public class ScrapingCatho {
	
	Scraping scrap = new Scraping();
	ConfiguracaoWeb configuracao = new ConfiguracaoWeb();
	
	String parametrosDeBusca(String[] parametros) {
		String url = "https://www.catho.com.br/vagas/?pais_id=31&q=";
		for (int i = 0; i < parametros.length; i++) {
			url += parametros[i];
			url += "%20";
		}
		return url;
	}

	List<Passing> listaDeVagas(String url) throws IOException {
		Document doc = ConfiguracaoWeb.configuracao(url);
		Element ul = doc.getElementById("search-result");
		List<Element> vagas = ul.getElementsByTag("li");
		List<Passing> vagasObject = new ArrayList<>();
		
		for (Element vaga : vagas) {
			
			boolean tituloIsEmpty = scrap.tituloIsEmpty(vaga, "h2");
			boolean empresaIsEmpty = scrap.empresaIsEmpty(vaga, "sc-cmTdod hVSlrJ");
			boolean tipoDeVagaIsEmpty = scrap.tipoDeVagaIsEmpty(vaga, "sc-iyvyFf kIwChr");

			if (!tituloIsEmpty && !empresaIsEmpty && !tipoDeVagaIsEmpty) {
				String empresa = scrap.empresa(vaga, "sc-cmTdod hVSlrJ");
				String titulo = scrap.titulo(vaga, "h2");
				String tipoDeVaga = scrap.tipoDeVaga(vaga, "sc-iyvyFf kIwChr");
				String salario = scrap.salario(vaga, "sc-esjQYD kYLuC");
				String link = scrap.link(vaga);
				Passing passing = new Passing(empresa, titulo, tipoDeVaga, salario, link);

				vagasObject.add(passing);
			}

		}
		return vagasObject;
	}

}
