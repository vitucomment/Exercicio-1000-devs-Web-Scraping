package br.com.mildevs.WebScraping.passing;

import java.math.BigDecimal;

public class Passing {

	private String empresa;
	private String vaga;
	private String cidade;
	private String tipoDeVaga;
	private BigDecimal salario;
	private String link;

	public Passing(String empresa, String vaga, String cidade, String tipoDeVaga, BigDecimal salario, String link) {
		super();
		this.empresa = empresa;
		this.vaga = vaga;
		this.cidade = cidade;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTipoDeVaga() {
		return tipoDeVaga;
	}

	public void setTipoDeVaga(String tipoDeVaga) {
		this.tipoDeVaga = tipoDeVaga;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
