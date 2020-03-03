package br.gov.economia.seddm.spu.escriba;

public enum OpcaoObjetivoDeclaracaoDominio {
	
	INDEFINIDO(-1, "Indefinido"),
	
	USUCAPIAO_EXTRAJUDICIAL(1, "Usucapião Extrajudicial"),
	
	USUCAPIAO_JUDICIAL(2, "Usucapião Judicial"),
	
	TRANSFERENCIA_TITULARIDADE(3, "Transferência de Titularidade"),
	
	INSTRUCAO_INQUERITO(4, "Instrução de Inquérito/Processo"),
	
	JUDICIAL_AUDITORIA(5, "Judicial/Auditoria"),
	
	REGISTRO_CARTORIAL(7, "Registro Cartorial"),
	
	OUTRO(6, "Outros");
	
	private Integer id;
	private String objetivo;
	
	OpcaoObjetivoDeclaracaoDominio(Integer id, String objetivo) {
		this.id = id;
		this.objetivo = objetivo;
	}

	public Integer getId() {
		return id;
	}

	public String getObjetivo() {
		return objetivo;
	}
	
	public Object getValue() {
		return this.id;
	}
	
	public String toString() {
		return this.objetivo;
	}

}
