package br.gov.spusc.escriba;

public enum OpcaoParecerTecnicoEnum {
	
	USUCAPIAO_EXTRAJUDICIAL_CARTORIO_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL,
			"Usucapião Extrajudicial - Cartório - Sem Interesse"),
	
	USUCAPIAO_EXTRAJUDICIAL_CARTORIO_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL,
			"Usucapião Extrajudicial - Cartório - Com Interesse"),
	
	USUCAPIAO_EXTRAJUDICIAL_PARTICULAR_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL,
			"Usucapião Extrajudicial - Particular - Sem Interesse"),
	
	USUCAPIAO_EXTRAJUDICIAL_PARTICULAR_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL,
			"Usucapião Extrajudicial - Particular - Com Interesse"),
	
	USUCAPIAO_JUDICIAL_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_JUDICIAL,
			"Usucapião Judicial - Sem interesse"),
	
	USUCAPIAO_JUDICIAL_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_JUDICIAL,
			"Usucapião Judicial - Com interesse"),
	
	TRANSFERENCIA_TITULARIDADE_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.TRANSFERENCIA_TITULARIDADE,
			"Transferência de Titularidade - Sem interesse"),
	
	TRANSFERENCIA_TITULARIDADE_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.TRANSFERENCIA_TITULARIDADE,
			"Transferência de Titularidade - Com interesse"),
	
	INSTRUCAO_INQUERIO_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.INSTRUCAO_INQUERITO,
			"Instrução de Inquérito - Sem interesse"),
	
	INSTRUCAO_INQUERIO_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.INSTRUCAO_INQUERITO,
			"Instrução de Inquérito - Com interesse"),
	
	JUDICIAL_AUDITORIA_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.JUDICIAL_AUDITORIA,
			"Judicial/Auditoria - Sem interesse"),
	
	JUDICIAL_AUDITORIA_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.JUDICIAL_AUDITORIA,
			"Judicial/Auditoria - Com interesse"),
	
	REGISTRO_CARTORIAL_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.REGISTRO_CARTORIAL,
			"Registro Cartorial - Sem interesse"),
	
	REGISTRO_CARTORIAL_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.REGISTRO_CARTORIAL,
			"Registro Cartorial - Com interesse"),
	
	OUTRO_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.OUTRO,
			"Outro - Sem interesse"),
	
	OUTRO_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.OUTRO,
			"Outro - Com interesse");
	
	private OpcaoObjetivoRequerimentoEnum objetivoRequerimentoEnum;
	
	private String rotulo;

	private OpcaoParecerTecnicoEnum(OpcaoObjetivoRequerimentoEnum objetivoRequerimentoEnum, String rotulo) {
		this.objetivoRequerimentoEnum = objetivoRequerimentoEnum;
		this.rotulo = rotulo;
	}

	public OpcaoObjetivoRequerimentoEnum getObjetivoRequerimentoEnum() {
		return objetivoRequerimentoEnum;
	}
	
	public String toString() {
		return this.rotulo;
	}

}