package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_objetivo_requerimento", schema = "servico")
public class ObjetivoRequerimento {
	
	@Id
	@Column(name = "id_objetivo_requerimento")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_objetivo_requerimento")
	private TipoObjetivoRequerimento tipoObjetivoRequerimento;
	
	@Column(name = "ds_objetivo_requerimento")
	private String objetivoRequerimento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoObjetivoRequerimento getTipoObjetivoRequerimento() {
		return tipoObjetivoRequerimento;
	}

	public void setTipoObjetivoRequerimento(TipoObjetivoRequerimento tipoObjetivoRequerimento) {
		this.tipoObjetivoRequerimento = tipoObjetivoRequerimento;
	}

	public String getObjetivoRequerimento() {
		return objetivoRequerimento;
	}

	public void setObjetivoRequerimento(String objetivoRequerimento) {
		this.objetivoRequerimento = objetivoRequerimento;
	}
	
	

}
