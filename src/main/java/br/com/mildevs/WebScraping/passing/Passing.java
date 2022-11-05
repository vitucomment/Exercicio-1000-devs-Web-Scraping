package br.com.mildevs.WebScraping.passing;

public class Passing {

	private String empresa;
	private String vaga;
	private String tipoDeVaga;
	private String salario;
	private String link;

	public Passing(String empresa, String vaga, String tipoDeVaga, String salario, String link) {
		this.empresa = empresa;
		this.vaga = vaga;
		this.tipoDeVaga = tipoDeVaga;
		this.salario = salario;
		this.link = link;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getVaga() {
		return vaga;
	}

	public void setVaga(String vaga) {
		this.vaga = vaga;
	}

	public String getTipoDeVaga() {
		return tipoDeVaga;
	}

	public void setTipoDeVaga(String tipoDeVaga) {
		this.tipoDeVaga = tipoDeVaga;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		String toString = ("Empresa: " + this.empresa + "\n"
				+ "Titulo da vaga: " + this.vaga + "\n"
				+ "Modo de trabalho: " + this.tipoDeVaga + "\n"
				+ "Salario: " + this.salario + "\n"
				+ "Link da vaga: " + this.link + "\n");
		return toString;
	}
}
