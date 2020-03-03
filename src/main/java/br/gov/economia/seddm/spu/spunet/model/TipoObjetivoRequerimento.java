package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipo_objetivo_requerimento", schema = "servico")
public class TipoObjetivoRequerimento {
	
	@Id
	@Column(name = "id_tipo_objetivo_requerimento")
	private Integer id;
	
	@Column(name = "ds_tipo_objetivo")
	private String tipoObjetivo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoObjetivo() {
		return tipoObjetivo;
	}

	public void setTipoObjetivo(String tipoObjetivo) {
		this.tipoObjetivo = tipoObjetivo;
	}
	
	

}
