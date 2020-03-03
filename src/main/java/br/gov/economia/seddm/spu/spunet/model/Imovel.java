package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_imovel", schema = "servico")
public class Imovel {
	
	
	@Id
	@Column(name = "id_imovel")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	@Column(name = "nu_matricula_transcricao")
	private String matriculaTranscricao; 
	
	@Column(name = "ds_classificacao_imovel")
	private String classificacaoImovel;
	
	@Column(name = "nu_processo")
	private String processo;
	
	@Column(name = "nu_inscricao")
	private String inscricao;
	
	@Column(name = "ds_tipo_imovel")
	private String tipoImovel;
	
	
	@Column(name = "nu_area_terreno")
	private double areaTerreno;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatriculaTranscricao() {
		return matriculaTranscricao;
	}

	public void setMatriculaTranscricao(String nuMatriculaTransicao) {
		this.matriculaTranscricao = nuMatriculaTransicao;
	}

	public String getClassificacaoImovel() {
		return classificacaoImovel;
	}

	public void setClassificacaoImovel(String classificacaoImovel) {
		this.classificacaoImovel = classificacaoImovel;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public double getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(double areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
