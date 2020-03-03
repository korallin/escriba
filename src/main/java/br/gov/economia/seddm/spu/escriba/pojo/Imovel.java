package br.gov.economia.seddm.spu.escriba.pojo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import br.gov.economia.seddm.spu.escriba.MarcacaoDeTexto;

public class Imovel extends MarcacaoDeTexto {

	private String nuMatriculaTransicao, classificacaoImovel, nuProcesso, nuInscricao, tipoImovel, areaTerrenoFormatada;
	
	private String cep, tipoLogradouro, logradouro, numero, complemento, municipio, bairro, uf;
	
	private double areaTerreno;
	
	public Imovel() {
		// TODO Auto-generated constructor stub
	}

	public Imovel(br.gov.economia.seddm.spu.spunet.model.Imovel imovel) {
		this.setNuMatriculaTransicao(imovel.getMatriculaTranscricao()); 
		this.setClassificacaoImovel(imovel.getClassificacaoImovel());
		this.setNuProcesso(imovel.getProcesso());
		this.setNuInscricao(imovel.getInscricao());
		this.setTipoImovel(imovel.getTipoImovel());
		this.setAreaTerreno(imovel.getAreaTerreno());
		if(imovel.getEndereco() != null) {
			this.setCep(imovel.getEndereco().getCep());
			this.setLogradouro(imovel.getEndereco().getLogradouro());
			this.setNumero(imovel.getEndereco().getNumero());
			this.setMunicipio(imovel.getEndereco().getMunicipio());
			this.setBairro(imovel.getEndereco().getBairro());
			this.setUf(imovel.getEndereco().getUf());
			if(imovel.getEndereco().getTipoLogradouro() != null) {
				this.setTipoLogradouro(imovel.getEndereco().getTipoLogradouro().getTipoLogradouro());
			}
		}
	}

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
