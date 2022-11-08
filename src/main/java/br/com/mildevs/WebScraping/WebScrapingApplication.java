package br.com.mildevs.WebScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.HttpStatusException;

import br.com.mildevs.WebScraping.config.ConfiguracaoWeb;
import br.com.mildevs.WebScraping.passing.Passing;
import br.com.mildevs.WebScraping.sites.ScrapingCatho;
import br.com.mildevs.WebScraping.sites.ScrapingIndeed;
import br.com.mildevs.WebScraping.util.EnviaEmail;

public class WebScrapingApplication {

	public static void main(String[] args) throws IOException, InterruptedException {

		ScrapingIndeed indeed = new ScrapingIndeed();
		ScrapingCatho catho = new ScrapingCatho();
		ConfiguracaoWeb config = new ConfiguracaoWeb();

		System.out.println("Busca de vagas: ");
		List<Passing> vagasObject = new ArrayList<>();

		String[] parametros = config.parametroDeBusca();
		
		String urlIndeed = indeed.parametrosDeBusca(parametros);
		Thread.sleep(500);
		String urlCatho = catho.parametrosDeBusca(parametros);
		
		try {
			vagasObject.addAll(indeed.listaDeVagas(urlIndeed));
			Thread.sleep(500);
			vagasObject.addAll(catho.listaDeVagas(urlCatho));
		} catch (HttpStatusException ex) {
			vagasObject.addAll(catho.listaDeVagas(urlCatho));
		} catch (Exception ex) {
			vagasObject.addAll(indeed.listaDeVagas(urlIndeed));
		}

		Thread.sleep(500);
		String vagasString = "\n";
		for (Passing vaga : vagasObject) {
			vagasString += vaga.toString();
			vagasString += "\n";
		}

		EnviaEmail.enviar(vagasString);
	}
}