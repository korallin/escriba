package br.gov.spusc.escriba.pojo;

import br.gov.spusc.escriba.MarcacaoDeTexto;

public class RequerimentoSPUnet extends MarcacaoDeTexto {
	
	private String numeroAtendimento;
	
	private String codigoIdentificacao;
	
	private String procedimentoFormatado;
	
	private String nomeRequerente;
	
	private Requerente requerente;

	private ObjetivoRequerimento objetivoRequerimento;

	private Imovel imovel;
	
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		retorno.append("Requerimento:[");
		retorno.append("numeroAtendimento: " + numeroAtendimento);
		retorno.append("nomeRequerente: " + nomeRequerente);
		retorno.append("procedimentoFormatado: " + procedimentoFormatado);
		retorno.append("codigoIdentificacao: " + codigoIdentificacao);
		retorno.append("requerente: " + requerente);
		retorno.append("]");
		return retorno.toString();
	}

	public String getNumeroAtendimento() {
		return numeroAtendimento;
	}

	public void setNumeroAtendimento(String numeroAtendimento) {
		this.numeroAtendimento = numeroAtendimento;
	}

	public String getProcedimentoFormatado() {
		return procedimentoFormatado;
	}

	public void setProcedimentoFormatado(String procedimentoFormatado) {
		this.procedimentoFormatado = procedimentoFormatado;
	}

	public String getNomeRequerente() {
		return nomeRequerente;
	}

	public void setNomeRequerente(String nomeRequerente) {
		this.nomeRequerente = nomeRequerente;
	}

	public Requerente getRequerente() {
		return requerente;
	}

	public void setRequerente(Requerente requerente) {
		this.requerente = requerente;
	}

	public String getCodigoIdentificacao() {
		return codigoIdentificacao;
	}

	public void setCodigoIdentificacao(String codigoIdentificacao) {
		this.codigoIdentificacao = codigoIdentificacao;
	}

	public ObjetivoRequerimento getObjetivoRequerimento() {
		return this.objetivoRequerimento;
	}

	public void setObjetivoRequerimento(ObjetivoRequerimento objetivoRequerimento) {
		this.objetivoRequerimento = objetivoRequerimento;		
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;	
	}

	public Imovel getImovel() {
		return this.imovel;		
	}

}
