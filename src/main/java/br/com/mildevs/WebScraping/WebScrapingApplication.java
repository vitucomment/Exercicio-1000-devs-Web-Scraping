package br.com.mildevs.WebScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.HttpStatusException;

import br.com.mildevs.WebScraping.passing.Passing;

public class WebScrapingApplication {

	public static void main(String[] args) throws IOException, InterruptedException {

		ScrapingIndeed indeed = new ScrapingIndeed();
		ScrapingCatho catho = new ScrapingCatho();
		ConfiguracaoWeb config = new ConfiguracaoWeb();

		System.out.println("Busca de vagas: ");
		List<Passing> vagasObject = new ArrayList<>();

		String[] parametros = config.parametroDeBusca();

		try {
			String url = indeed.parametrosDeBusca(parametros);
			vagasObject = indeed.listaDeVagas(url);
		} catch (HttpStatusException ex) {
			String url = catho.parametrosDeBusca(parametros);
			vagasObject = catho.listaDeVagas(url);
		}
		
		Thread.sleep(1500);
		
		String vagasString = "\n";
		for (Passing vaga : vagasObject) {
			vagasString += vaga.toString();
			vagasString += "\n";
		}
		
		EnviaEmail.enviar(vagasString);
	}
}