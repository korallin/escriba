package br.gov.spusc.escriba;

public enum OpcaoConclusaoParecerTecnicoDeclaracaoDominioEnum {
	
	INDEFINIDO(
			"Indefinido",
			"[Parágrafo de conclusão]"),
	
	FORA_FAIXA_SEGURANCA_COM_INTERFERENCIA("Fora da Faixa de Segurança - Com interferência", 
			"Constatamos que o imóvel em questão não se encontra situado em Zona de Segurança Nacional e se sobrepõe totalmente/parcialmente a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional."),
	
	FORA_FAIXA_SEGURANCA_SEM_INTERFERENCIA("Fora da Faixa de Segurança - Sem interferência", 
			"Constatamos que o imóvel em questão não se encontra situado em Zona de Segurança Nacional e não se sobrepõe a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional."),
	
	DENTRO_FAIXA_SEGURANCA_COM_INTERFERENCIA("Dentro da Faixa de Segurança - Com interferência", 
			"Constatamos que o imóvel em questão se encontra situado em Zona de Segurança Nacional e se sobrepõe totalmente/parcialmente a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional."),
	
	DENTRO_FAIXA_SEGURANCA_SEM_INTERFERENCIA("Dentro da Faixa de Segurança - Sem interferência", 
			"Constatamos que o imóvel em questão se encontra situado em Zona de Segurança Nacional e não se sobrepõe a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional."),
	
	DOCUMENTACAO_INSUFICIENTE("Documentação Insuficiente", 
			"Constatamos que a documentação apresentada pelo requerente não permite a correta individualização do imóvel e a verificação de possíveis interferências com bens de propriedade da União, uma vez que não foram apresentadas as coordenadas georreferenciadas dos vértices delimitadores do imóvel."),
	
	MANIFESTACAO_DNIT("Manifestação do DNIT", 
			"Constatamos que o imóvel em questão não se encontra situado em Zona de Segurança Nacional e não se sobrepõe a bens de propriedade União, caracterizados como Terreno de Marinha e Acrescidos / Próprio Nacional, porém, está situado nas proximidade de rodovia federal, sendo necessária a manifestação prévia do Departamento Nacional de Infraestrutura de Transportes - DNIT.");
	
	private String rotulo;

	private String conclusao;

	private OpcaoConclusaoParecerTecnicoDeclaracaoDominioEnum(
			String rotulo,
			String conclusao) {
		
		this.rotulo = rotulo;
		this.conclusao = conclusao;
	}
	
	public String toString() {
		return this.rotulo;
	}

	public String getConclusao() {
		return conclusao;
	}

}