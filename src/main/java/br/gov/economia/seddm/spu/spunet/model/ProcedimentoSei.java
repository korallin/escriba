package br.gov.economia.seddm.spu.spunet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_procedimento_sei", schema = "servico")
public class ProcedimentoSei {
	
	@Id
	@Column(name = "id_procedimento_sei")
	private Integer id_procedimento_sei;
	
	@Column(name = "ds_numero_procedimento")
	private String procedimento;

	public Integer getId_procedimento_sei() {
		return id_procedimento_sei;
	}

	public void setId_procedimento_sei(Integer id_procedimento_sei) {
		this.id_procedimento_sei = id_procedimento_sei;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	
	

}
