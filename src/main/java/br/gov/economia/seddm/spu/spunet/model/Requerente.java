package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_requerente", schema = "servico")
public class Requerente {
	
	@Id
	@Column(name = "id_requerente")
	private Integer id;
	
	@Column(name = "ds_nome")
	private String nome;
	
	@Column(name = "nu_cpf_cnpj")
	private String cpfCnpf;
	
	public String getCpfCnpf() {
		return cpfCnpf;
	}

	public void setCpfCnpf(String cpfCnpf) {
		this.cpfCnpf = cpfCnpf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
