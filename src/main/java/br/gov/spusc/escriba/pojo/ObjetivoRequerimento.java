package br.gov.spusc.escriba.pojo;

public class ObjetivoRequerimento {
	
	private Integer id;
	
	private String objetivo;

	private String descricao;
	
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
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
		
	}

	public String getDescricao() {
		return this.descricao;
	}

}
