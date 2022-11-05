package br.com.mildevs.WebScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.com.mildevs.WebScraping.passing.Passing;

public class WebScrapingApplication {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);

		EnviaEmail enviaEmail = new EnviaEmail();

		System.out.println("Busca de vagas: ");

		List<Passing> vagasObject = new ArrayList<>();
		String url = parametrosDeBusca(input);
		String urlCopia = url;
		List<Element> vagas = configuracao(urlCopia);

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
				if (!salarioIsEmpty) {
					salario = vaga.getElementsByClass("metadata salary-snippet-container").get(0).text();
				}

				Passing passing = new Passing(empresa, titulo, tipoDeVaga, salario, link);

				vagasObject.add(passing);
			}
		}

		String vagasString = "";
		for (Passing vaga : vagasObject) {
			vagasString += vaga.toString();
			vagasString += "\n";
		}

		System.out.print("Tudo pronto, agora informe seu email: ");
		String email = input.nextLine();
		System.out.println();
		System.out.println(vagasString);
		enviaEmail.EnviarEmail(vagasString, email);
		System.out.println("Olhe seu email :)");
	}

	private static List<Element> configuracao(String url) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Edge").get();
		Element ul = doc.getElementsByClass("jobsearch-ResultsList css-0").first();
		List<Element> vagas = ul.getElementsByTag("li");
		return vagas;
	}

	private static String parametrosDeBusca(Scanner input) {
		System.out.print("Digite quais vagas deseja buscar: ");
		String parametro = input.nextLine();
		String[] parametros = parametro.split(" ");
		System.out.println("Aguarde um instante...");
		String url = "https://br.indeed.com/jobs?q=";

		for (int i = 0; i < parametros.length; i++) {
			url += parametros[i];
			url += "+";
		}
		return url;
	}

}