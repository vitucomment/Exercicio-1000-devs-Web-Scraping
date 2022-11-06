package br.com.mildevs.WebScraping;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConfiguracaoWeb {

	static Document configuracao(String url) throws IOException {
		Connection connection = Jsoup.connect(url).referrer("http://www.google.com");
		connection.userAgent("Chrome");
		Document doc = connection.get();
		return doc;
		
	}
	
	String[] parametroDeBusca() {
		Scanner input = new Scanner(System.in);
		System.out.print("Digite quais vagas deseja buscar: ");
		String parametro = input.nextLine();
		String[] parametros = parametro.split(" ");
		System.out.println("Aguarde um instante...");
		return parametros;
	}
}
