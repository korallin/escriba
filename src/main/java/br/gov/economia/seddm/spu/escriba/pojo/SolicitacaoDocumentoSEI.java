package br.gov.economia.seddm.spu.escriba.pojo;

import java.util.List;
import java.util.Map;

public class SolicitacaoDocumentoSEI {
	
	private String tipoDocumento;
	private Integer numeroDocumentoModelo;
	private Map<String, List<String>> mapaDeMarcacoes;
	private String identificacaoDocumentoGerado;
	private String numeroDocumentoGerado;
	private String nupSei;
	
	public SolicitacaoDocumentoSEI(String nupSei, String tipoDocumento, Integer numeroDocumentoModelo, Map<String, List<String>> mapaDeMarcacoes) {
		this.nupSei = nupSei;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumentoModelo = numeroDocumentoModelo;
		this.mapaDeMarcacoes = mapaDeMarcacoes;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Integer getNumeroDocumentoModelo() {
		return numeroDocumentoModelo;
	}
	public void setNumeroDocumentoModelo(Integer numeroDocumentoModelo) {
		this.numeroDocumentoModelo = numeroDocumentoModelo;
	}
	public Map<String, List<String>> getMapaDeMarcacoes() {
		return mapaDeMarcacoes;
	}
	public void setMapaDeMarcacoes(Map<String, List<String>> mapaDeMarcacoes) {
		this.mapaDeMarcacoes = mapaDeMarcacoes;
	}

	public void setIdentificacaoDocumentoGerado(String obterIdentificacaoDocumentoCriado) {
		this.identificacaoDocumentoGerado = obterIdentificacaoDocumentoCriado;		
	}

	public String getIdentificacaoDocumentoGerado() {
		return identificacaoDocumentoGerado;
	}

	public void setNumeroDocumentoGerado(String numeroDocumentoGerado) {
		this.numeroDocumentoGerado = numeroDocumentoGerado;
	}

	public String getNumeroDocumentoGerado() {
		return numeroDocumentoGerado;
	}

	public String getNupSei() {
		return this.nupSei;
	}

	public void setNupSei(String nupSei) {
		this.nupSei = nupSei;
	}

}
