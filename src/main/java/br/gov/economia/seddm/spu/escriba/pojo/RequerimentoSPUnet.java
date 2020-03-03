package br.gov.economia.seddm.spu.escriba.pojo;

import br.gov.economia.seddm.spu.escriba.MarcacaoDeTexto;
import br.gov.economia.seddm.spu.spunet.model.Requerimento;

public class RequerimentoSPUnet extends MarcacaoDeTexto {
	
	private String numeroAtendimento;
	
	private String codigoIdentificacao;
	
	private String procedimentoFormatado;
	
	private String nomeRequerente;
	
	private Requerente requerente;

	private ObjetivoRequerimento objetivoRequerimento;

	private Imovel imovel;
	
	public RequerimentoSPUnet() {
	}
	
	public RequerimentoSPUnet(Requerimento requerimento) {
		this.numeroAtendimento = requerimento.getAtendimento().getNuAtendimento();
		this.codigoIdentificacao = requerimento.getIdentificacao();
		if(requerimento.getProcedimentoSei() != null) {
			this.procedimentoFormatado = requerimento.getProcedimentoSei().getProcedimento();			
		}
		this.nomeRequerente = requerimento.getRequerente().getNome();
		this.requerente = new Requerente(requerimento.getRequerente());
		this.imovel = new Imovel(requerimento.getImovel());
		this.objetivoRequerimento = new ObjetivoRequerimento(requerimento.getObjetivoRequerimento());
	}
	
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
