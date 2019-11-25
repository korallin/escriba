package br.gov.spusc.escriba.pojo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import br.gov.spusc.escriba.MarcacaoDeTexto;

public class Imovel extends MarcacaoDeTexto {

	private String nuMatriculaTransicao, classificacaoImovel, nuProcesso, nuInscricao, tipoImovel, areaTerrenoFormatada;
	
	private String cep, tipoLogradouro, logradouro, numero, complemento, municipio, bairro, uf;
	
	private double areaTerreno;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNuMatriculaTransicao() {
		return nuMatriculaTransicao;
	}

	public void setNuMatriculaTransicao(String nuMatriculaTransicao) {
		this.nuMatriculaTransicao = nuMatriculaTransicao;
	}

	public String getClassificacaoImovel() {
		return classificacaoImovel;
	}

	public void setClassificacaoImovel(String classificacaoImovel) {
		this.classificacaoImovel = classificacaoImovel;
	}

	public String getNuProcesso() {
		return nuProcesso;
	}

	public void setNuProcesso(String nuProcesso) {
		this.nuProcesso = nuProcesso;
	}

	public String getNuInscricao() {
		return nuInscricao;
	}

	public void setNuInscricao(String nuInscricao) {
		this.nuInscricao = nuInscricao;
	}

	public String getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public double getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(double areaTerreno) {
		this.areaTerreno = areaTerreno;
		
		DecimalFormat decimalFormat = new DecimalFormat("#,###.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		this.setAreaTerrenoFormatada(decimalFormat.format(areaTerreno));			
	}

	public String getAreaTerrenoFormatada() {
		return areaTerrenoFormatada;
	}

	public void setAreaTerrenoFormatada(String areaTerrenoFormatada) {
		this.areaTerrenoFormatada = areaTerrenoFormatada;
	}
	

}
