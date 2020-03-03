package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_atendimento", schema = "servico")
public class Atendimento {
	
	@Id
	@Column(name = "id_atendimento")
	private Integer id;
	
	@Column(name = "nu_atendimento")
	private String nuAtendimento;
	
	@Column(name = "nu_dias_prorrogacao")
	private String nu_dias_prorrogacao;
	
	@Column(name = "nu_termo_permissao")
	private String nu_termo_permissao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNuAtendimento() {
		return nuAtendimento;
	}

	public void setNuAtendimento(String nuAtendimento) {
		this.nuAtendimento = nuAtendimento;
	}

	public String getNu_dias_prorrogacao() {
		return nu_dias_prorrogacao;
	}

	public void setNu_dias_prorrogacao(String nu_dias_prorrogacao) {
		this.nu_dias_prorrogacao = nu_dias_prorrogacao;
	}

	public String getNu_termo_permissao() {
		return nu_termo_permissao;
	}

	public void setNu_termo_permissao(String nu_termo_permissao) {
		this.nu_termo_permissao = nu_termo_permissao;
	}

}
