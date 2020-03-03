package br.gov.economia.seddm.spu.escriba.pojo;

import br.gov.economia.seddm.spu.escriba.MarcacaoDeTexto;
import br.gov.economia.seddm.spu.escriba.OpcaoObjetivoDeclaracaoDominio;

public class ObjetivoRequerimento extends MarcacaoDeTexto {

	private Integer id;
	
	private String objetivo;

	private String descricao;
	
	public ObjetivoRequerimento() {
	}
	
	public ObjetivoRequerimento(br.gov.economia.seddm.spu.spunet.model.ObjetivoRequerimento objetivoRequerimento) {
		this.id = objetivoRequerimento.getId();
		this.descricao = objetivoRequerimento.getObjetivoRequerimento();
		if(objetivoRequerimento.getTipoObjetivoRequerimento() != null) {
			this.objetivo = objetivoRequerimento.getTipoObjetivoRequerimento().getTipoObjetivo();			
		}
	}

	public String toString() {
		StringBuilder retorno =  new StringBuilder("ObjetivoRequerimento[");
		retorno.append("id: " + id);
		retorno.append("objetivo: " + objetivo);
		retorno.append("descricao: " + descricao);
		retorno.append("]");
		return retorno.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
		atualizarObjetivoDescricao();
	}

	private void atualizarObjetivoDescricao() {
		if(this.id != null) {
			for (OpcaoObjetivoDeclaracaoDominio opcaoObjetivo: OpcaoObjetivoDeclaracaoDominio.values()) {
				if(this.id.equals(opcaoObjetivo.getId())) {
					this.objetivo = opcaoObjetivo.getObjetivo();
				}
			}			
			if(this.id.equals(OpcaoObjetivoDeclaracaoDominio.OUTRO.getId().intValue()) 
					&& this.descricao != null 
					&& !this.descricao.isEmpty()) {
				this.objetivo += " (" + this.descricao + ")";
			}
		} else {
			this.objetivo = null;
			this.descricao = null;
		}
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
		atualizarObjetivoDescricao();
	}

	public String getDescricao() {
		return this.descricao;
	}

}
