package br.gov.spusc.escriba;

public enum OpcaoDeclaracaoDeDominioEnum {
	
	INDEFINIDO(
			OpcaoObjetivoRequerimentoEnum.INDEFINIDO,
			"Indefinido",
			"[Parágrafo de conclusão]"),
	
	USUCAPIAO_EXTRAJUDICIAL_CARTORIO_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL,
			"Usucapião Extrajudicial - Cartório - Sem Interesse",
			"Conforme análise técnica constante do Parecer  XX/2019, DECLARAMOS para fins de Usucapião Extrajudicial,  que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.\r\n" + 
			"\r\n" + 
			"Diante do exposto, não existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC na impugnação do referido procedimento de Usucapião Extrajudicial.\r\n" + 
			"\r\n" + 
			"A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI XXXX, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.\r\n" + 
			"\r\n" + 
			""),
	
	USUCAPIAO_EXTRAJUDICIAL_CARTORIO_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL,
			"Usucapião Extrajudicial - Cartório - Com Interesse",
			"Conforme análise técnica constante do Parecer  XX/2019, DECLARAMOS para fins de Usucapião Extrajudicial, que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.\r\n" + 
			"\r\n" + 
			"Diante do exposto, existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC na impugnação do referido procedimento de Usucapião Extrajudicial.\r\n" + 
			"\r\n" + 
			"A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI XXXX, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos."),
	
	USUCAPIAO_EXTRAJUDICIAL_PARTICULAR_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL,
			"Usucapião Extrajudicial - Particular - Sem Interesse",
			"Conforme análise técnica constante do Parecer  XX/2019, DECLARAMOS para fins de Usucapião Extrajudicial,  que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.\r\n" + 
			"\r\n" + 
			"A presente declaração declaração não dispensa a observância do disposto no § 3º do Art. 216-A da Lei nº 6.015, de 31 de dezembro de 1973, pelo Oficial Registrador.\r\n" + 
			"\r\n" + 
			"A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI XXXX, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos."),
	
	USUCAPIAO_EXTRAJUDICIAL_PARTICULAR_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL,
			"Usucapião Extrajudicial - Particular - Com Interesse",
			""),
	
	USUCAPIAO_JUDICIAL_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_JUDICIAL,
			"Usucapião Judicial - Sem interesse",
			""),
	
	USUCAPIAO_JUDICIAL_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.USUCAPIAO_JUDICIAL,
			"Usucapião Judicial - Com interesse",
			""),
	
	TRANSFERENCIA_TITULARIDADE_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.TRANSFERENCIA_TITULARIDADE,
			"Transferência de Titularidade - Sem interesse",
			""),
	
	TRANSFERENCIA_TITULARIDADE_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.TRANSFERENCIA_TITULARIDADE,
			"Transferência de Titularidade - Com interesse",
			""),
	
	INSTRUCAO_INQUERIO_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.INSTRUCAO_INQUERITO,
			"Instrução de Inquérito - Sem interesse",
			""),
	
	INSTRUCAO_INQUERIO_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.INSTRUCAO_INQUERITO,
			"Instrução de Inquérito - Com interesse",
			""),
	
	JUDICIAL_AUDITORIA_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.JUDICIAL_AUDITORIA,
			"Judicial/Auditoria - Sem interesse",
			""),
	
	JUDICIAL_AUDITORIA_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.JUDICIAL_AUDITORIA,
			"Judicial/Auditoria - Com interesse",
			""),
	
	REGISTRO_CARTORIAL_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.REGISTRO_CARTORIAL,
			"Registro Cartorial - Sem interesse",
			""),
	
	REGISTRO_CARTORIAL_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.REGISTRO_CARTORIAL,
			"Registro Cartorial - Com interesse",
			""),
	
	OUTRO_SEM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.OUTRO,
			"Outro - Sem interesse",
			""),
	
	OUTRO_COM_INTERESSE(
			OpcaoObjetivoRequerimentoEnum.OUTRO,
			"Outro - Com interesse",
			"");
	
	private OpcaoObjetivoRequerimentoEnum objetivoRequerimentoEnum;
	
	private String rotulo;

	private String conclusao;

	private OpcaoDeclaracaoDeDominioEnum(
			OpcaoObjetivoRequerimentoEnum objetivoRequerimentoEnum, 
			String rotulo,
			String conclusao) {
		this.objetivoRequerimentoEnum = objetivoRequerimentoEnum;
		this.rotulo = rotulo;
		this.conclusao = conclusao;
	}

	public OpcaoObjetivoRequerimentoEnum getObjetivoRequerimentoEnum() {
		return objetivoRequerimentoEnum;
	}
	
	public String toString() {
		return this.rotulo;
	}

	public String getConclusao() {
		return conclusao;
	}

}