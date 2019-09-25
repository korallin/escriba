package br.gov.spusc.escriba;

public enum OpcaoParecerTecnicoDeclaracaoDominio {
	
	INDEFINIDO(
			"Selecione",
			null,
			"[Parágrafo de conclusão]"),
	
	FORA_FAIXA_SEGURANCA_COM_INTERFERENCIA("Fora da Faixa de Segurança - Com interferência",
			true,
			"Constatamos que o imóvel em questão não se encontra situado em Zona de Segurança Nacional e se sobrepõe totalmente/parcialmente a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional."),
	
	FORA_FAIXA_SEGURANCA_SEM_INTERFERENCIA("Fora da Faixa de Segurança - Sem interferência",
			false,
			"Constatamos que o imóvel em questão não se encontra situado em Zona de Segurança Nacional e não se sobrepõe a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional."),
	
	DENTRO_FAIXA_SEGURANCA_COM_INTERFERENCIA("Dentro da Faixa de Segurança - Com interferência",
			true,
			"Constatamos que o imóvel em questão se encontra situado em Zona de Segurança Nacional e se sobrepõe totalmente/parcialmente a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional."),
	
	DENTRO_FAIXA_SEGURANCA_SEM_INTERFERENCIA("Dentro da Faixa de Segurança - Sem interferência",
			false,
			"Constatamos que o imóvel em questão se encontra situado em Zona de Segurança Nacional e não se sobrepõe a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional."),
	
	DOCUMENTACAO_INSUFICIENTE("Documentação Insuficiente",
			null,
			"Constatamos que a documentação apresentada pelo requerente não permite a correta individualização do imóvel e a verificação de possíveis interferências com bens de propriedade da União, uma vez que não foram apresentadas as coordenadas georreferenciadas dos vértices delimitadores do imóvel."),
	
	MANIFESTACAO_DNIT("Manifestação do DNIT",
			null,
			"Constatamos que o imóvel em questão não se encontra situado em Zona de Segurança Nacional e não se sobrepõe a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional, porém, está situado nas proximidade de rodovia federal, sendo necessária a manifestação prévia do Departamento Nacional de Infraestrutura de Transportes - DNIT.");
	
	private String rotulo;

	private String conclusao;
	
	private Boolean comInteresse;

	private OpcaoParecerTecnicoDeclaracaoDominio(
			String rotulo,
			Boolean comInteresse,
			String conclusao) {
		
		this.rotulo = rotulo;
		this.comInteresse = comInteresse;
		this.conclusao = conclusao;
	}
	
	public String toString() {
		return this.rotulo;
	}

	public String getConclusao() {
		return conclusao;
	}

	public Boolean getComInteresse() {
		return comInteresse;
	}

}