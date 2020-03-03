package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_endereco", schema = "servico")
public class Endereco {
	
	@Id
	@Column(name = "id_endereco")
	private Integer id;
	
	@Column(name = "nu_cep")
	private String cep; 
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_logradouro")
	private TipoLogradourdo tipoLogradouro;
	
	@Column(name = "ds_logradouro")
	private String logradouro;
	
	@Column(name = "nu_numero")
	private String numero;
	
	@Column(name = "ds_complemento")
	private String complemento;
	
	@Column(name = "ds_municipio")
	private String municipio;
	
	@Column(name = "ds_bairro")
	private String bairro;
	
	@Column(name = "ds_uf")
	private String uf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public TipoLogradourdo getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradourdo tipoLogradouro) {
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

}
