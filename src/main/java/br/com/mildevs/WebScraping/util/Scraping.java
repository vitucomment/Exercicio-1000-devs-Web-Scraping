package br.com.mildevs.WebScraping.util;

import org.jsoup.nodes.Element;

public class Scraping {

	public String empresa(Element vaga, String htmlClass) {
		String empresa = vaga.getElementsByClass(htmlClass).get(0).text();
		if (empresa.equalsIgnoreCase("empresa confidencialpor que?")) {
			empresa = "Confidencial";
		}
		return empresa;
	}

	public String titulo(Element vaga, String htmlClass) {
		return vaga.getElementsByTag(htmlClass).get(0).text();
	}

	public String tipoDeVaga(Element vaga, String htmlClass) {
		return vaga.getElementsByClass(htmlClass).get(0).text().split("\\(")[0];
	}

	
	public String salario(Element vaga, String htmlClass) {
		String salario = "Não informado";
		if (!salarioIsEmpty(vaga)) {
			return salario = vaga.getElementsByClass(htmlClass).get(0).text();
		} else {
			return salario;
		}

	}

	public String link(Element vaga) {
		return vaga.select("a[href]").attr("abs:href");
	}

	public boolean tituloIsEmpty(Element vaga, String htmlClass) {
		return vaga.getElementsByTag(htmlClass).text().equalsIgnoreCase("");
	}

	public boolean empresaIsEmpty(Element vaga, String htmlClass) {
		return vaga.getElementsByClass(htmlClass).text().equalsIgnoreCase("");
	}

	public boolean tipoDeVagaIsEmpty(Element vaga, String htmlClass) {
		return vaga.getElementsByClass(htmlClass).text().equalsIgnoreCase("");
	}

	boolean salarioIsEmpty(Element vaga) {
		return vaga.getElementsByClass("sc-esjQYD kYLuC").isEmpty();
	}
}
