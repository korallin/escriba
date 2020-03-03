package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;

@Entity
@Table(name = "tb_requerimento", schema = "servico")
public class Requerimento {
	
	@Id
	@Column(name = "id_requerimento")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_atendimento")
	private Atendimento atendimento;
	
	@ManyToOne
	@JoinColumn(name = "id_imovel")
	private Imovel imovel;
	
	@ManyToOne
	@JoinColumn(name = "id_objetivo_requerimento")
	private ObjetivoRequerimento objetivoRequerimento;

	@ManyToOne
	@JoinFormula(
			value = "(SELECT rqte.id_requerente FROM servico.tb_requerente rqte WHERE rqte.id_requerimento = id_requerimento LIMIT 1)")
	private Requerente requerente;
	
	@ManyToOne
	@JoinColumn(name = "id_procedimento_sei")
	private ProcedimentoSei procedimentoSei;
	
	public ProcedimentoSei getProcedimentoSei() {
		return procedimentoSei;
	}

	public void setProcedimentoSei(ProcedimentoSei procedimentoSei) {
		this.procedimentoSei = procedimentoSei;
	}

	@Column(name = "nu_identificacao")
	private String identificacao;

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Atendimento getAtendimento() {
		return atendimento;
	}


	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}


	public Imovel getImovel() {
		return imovel;
	}


	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	public void setRequerente(Requerente requerente) {
		this.requerente = requerente;
	}
	
	public Requerente getRequerente() {
		return this.requerente;
	}

	public ObjetivoRequerimento getObjetivoRequerimento() {
		return objetivoRequerimento;
	}

	public void setObjetivoRequerimento(ObjetivoRequerimento objetivoRequerimento) {
		this.objetivoRequerimento = objetivoRequerimento;
	}
	
	

}
