package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipo_logradouro", schema = "servico")
public class TipoLogradourdo {
	
	@Id
	@Column(name = "id_tipo_logradouro")
	private Integer id;
	
	@Column(name = "ds_tipo_logradouro")
	private String tipoLogradouro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	
	

}
