package br.gov.economia.seddm.spu.escriba;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public enum OpcaoConteudoDeclaracaoDeDominio {
	
	INDEFINIDO(
			OpcaoObjetivoDeclaracaoDominio.INDEFINIDO,
			null,
			"Indefinido",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = -6258839041929782583L;

			{
				add("[Parágrafo de conclusão]");
			}}
			),
	
	USUCAPIAO_EXTRAJUDICIAL_CARTORIO_SEM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.USUCAPIAO_EXTRAJUDICIAL,
			false,
			"Usucapião Extrajudicial - Cartório - Sem Interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 6406121598259132577L;

			{
				add("Conforme análise técnica constante do {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Usucapião Extrajudicial,  que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.");
				add("Diante do exposto, não existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC na impugnação do referido procedimento de Usucapião Extrajudicial");
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	USUCAPIAO_EXTRAJUDICIAL_CARTORIO_COM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.USUCAPIAO_EXTRAJUDICIAL,
			true,
			"Usucapião Extrajudicial - Cartório - Com Interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = -4216033649859535629L;

			{
				add("Conforme análise técnica constante do {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Usucapião Extrajudicial, que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.");
				add("Diante do exposto, existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC na impugnação do referido procedimento de Usucapião Extrajudicial.");
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),				
	
	USUCAPIAO_EXTRAJUDICIAL_PARTICULAR_SEM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.USUCAPIAO_EXTRAJUDICIAL,
			false,
			"Usucapião Extrajudicial - Particular - Sem Interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = -4395882359289292976L;

			{
				add("Conforme análise técnica constante do {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Usucapião Extrajudicial,  que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.");
				add("A presente declaração declaração não dispensa a observância do disposto no § 3º do Art. 216-A da Lei nº 6.015, de 31 de dezembro de 1973, pelo Oficial Registrador.");
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");				
			}}),
	
	USUCAPIAO_EXTRAJUDICIAL_PARTICULAR_COM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.USUCAPIAO_EXTRAJUDICIAL,
			true,
			"Usucapião Extrajudicial - Particular - Com Interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = -6759451038011973610L;

			{
			 add("Conforme análise técnica constante do {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Usucapião Extrajudicial,  que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.");
			 add("Diante do exposto, existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC na impugnação do referido procedimento de Usucapião Extrajudicial.");
			 add("A presente declaração não dispensa a observância do disposto no § 3º do Art. 216-A da Lei nº 6.015, de 31 de dezembro de 1973 pelo Oficial Registrador.");
			 add("A utilização de bem imóvel de propriedade da União, sem a prévia autorização, configura infração administrativa contra do Patrimônio da União, passível de multa e indenização, nos termos do Art. 6º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987.");
			 add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	USUCAPIAO_JUDICIAL_SEM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.USUCAPIAO_JUDICIAL,
			false,
			"Usucapião Judicial - Sem interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 2121092096892158662L;

			{
				add("Conforme análise técnica constante do {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Usucapião Judicial,  que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.");
				add("A presente declaração não dispensa a citação da Advocacia Geral da União quando do ajuizamento de ação de usucapião, conforme determina o § 3º do Art. 242 da Lei nº 13.105, de 16 de março de 2015 - Código de Processo Civil.");
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	USUCAPIAO_JUDICIAL_COM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.USUCAPIAO_JUDICIAL,
			true,
			"Usucapião Judicial - Com interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = -206596973216903734L;

			{
				add("Conforme análise técnica constante do {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Usucapião Judicial,  que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.");
				add("Diante do exposto, existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC no referido procedimento de Usucapião Judicial.");
				add("A presente declaração não dispensa a citação da Advocacia Geral da União quando do ajuizamento de ação de usucapião, conforme determina o § 3º do Art. 242 da Lei nº 13.105, de 16 de março de 2015 - Código de Processo Civil.");
				add("A utilização de bem imóvel de propriedade da União, sem a prévia autorização, configura infração administrativa contra do Patrimônio da União, passível de multa e indenização, nos termos do Art. 6º do Decreto-Lei nº 2.398, de 21 de Dezembro de 1987.");
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");				
			}}),
	
	TRANSFERENCIA_TITULARIDADE_SEM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.TRANSFERENCIA_TITULARIDADE,
			false,
			"Transferência de Titularidade - Sem interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 5320904772519133015L;

			{
				add("Conforme análise técnica constante do {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Transferência de Titularidade,  que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC.");
				add("Diante do exposto, não existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC no procedimento de Transferência de Titularidade.");
				add("O procedimento de Registro Cartorial deverá obedecer o disposto no § 2º do Art. 3º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987.");
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}), 
	
	TRANSFERENCIA_TITULARIDADE_COM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.TRANSFERENCIA_TITULARIDADE,
			true,
			"Transferência de Titularidade - Com interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 5152155633838399697L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Transferência de Titularidade,  que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("Diante do exposto, o procedimento de Transferência de Titularidade deverá obedecer o disposto no § 2º do Art. 3º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987, sendo necessária a apresentação de Certidão de Autorização para Transferência - CAT, nos termos da Instrução Normativa nº 01, de 09 de março de 2018."); 
				add("A utilização de bem imóvel de propriedade da União, sem a prévia autorização, configura infração administrativa contra do Patrimônio da União, passível de multa e indenização, nos termos do Art. 6º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	INSTRUCAO_INQUERIO_SEM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.INSTRUCAO_INQUERITO,
			false,
			"Instrução de Inquérito - Sem interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 4474140957407727058L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Instrução de Inquérito/Processo,  que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("Diante do exposto, não existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC no procedimento de Instrução de Inquérito/Processo."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	INSTRUCAO_INQUERIO_COM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.INSTRUCAO_INQUERITO,
			true,
			"Instrução de Inquérito - Com interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = -7961121740623668178L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Instrução de Inquérito/Processo, que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("Diante do exposto, existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC no procedimento de Instrução de Inquérito/Processo."); 
				add("A utilização de bem imóvel de propriedade da União, sem a prévia autorização, configura infração administrativa contra do Patrimônio da União, passível de multa e indenização, nos termos do Art. 6º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	JUDICIAL_AUDITORIA_SEM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.JUDICIAL_AUDITORIA,
			false,
			"Judicial/Auditoria - Sem interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 8402344551549371257L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Usucapião Judicial,  que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("A presente declaração não dispensa a citação da Advocacia Geral da União quando do ajuizamento de ação de usucapião, conforme determina o § 3º do Art. 242 da Lei nº 13.105, de 16 de março de 2015 - Código de Processo Civil."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	JUDICIAL_AUDITORIA_COM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.JUDICIAL_AUDITORIA,
			true,
			"Judicial/Auditoria - Com interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1747148694847712997L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Usucapião Judicial, que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("Diante do exposto, existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC no referido procedimento de Usucapião Judicial."); 
				add("A presente declaração não dispensa a citação da Advocacia Geral da União quando do ajuizamento de ação de usucapião, conforme determina o § 3º do Art. 242 da Lei nº 13.105, de 16 de março de 2015 - Código de Processo Civil."); 
				add("A utilização de bem imóvel de propriedade da União, sem a prévia autorização, configura infração administrativa contra do Patrimônio da União, passível de multa e indenização, nos termos do Art. 6º do Decreto-Lei nº 2.398, de 21 de Dezembro de 1987."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	REGISTRO_CARTORIAL_SEM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.REGISTRO_CARTORIAL,
			false,
			"Registro Cartorial - Sem interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 4594146230005229310L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Registro Cartorial, que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("Diante do exposto, não existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC no procedimento de Registro Cartorial."); 
				add("O procedimento de Registro Cartorial deverá obedecer o disposto no § 2º do Art. 3º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987.");
				add("A presente declaração não dispensa a observância do disposto no § 3º do Art. 216-A da Lei nº 6.015, de 31 de dezembro de 1973, pelo Oficial Registrador."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	REGISTRO_CARTORIAL_COM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.REGISTRO_CARTORIAL,
			true,
			"Registro Cartorial - Com interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = -3765984360009807000L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de Registro Cartorial, que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("Diante do exposto, existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC na impugnação do procedimento de Registro Cartorial."); 
				add("O procedimento de Registro Cartorial deverá obedecer o disposto no § 2º do Art. 3º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987."); 
				add("A presente declaração não dispensa a observância do disposto no § 3º do Art. 216-A da Lei nº 6.015, de 31 de dezembro de 1973, pelo Oficial Registrador."); 
				add("A utilização de bem imóvel de propriedade da União, sem a prévia autorização, configura infração administrativa contra do Patrimônio da União, passível de multa e indenização, nos termos do Art. 6º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	OUTRO_SEM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.OUTRO,
			false,
			"Outro - Sem interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = -449718575789423947L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de {{RequerimentoSPUnet.ObjetivoRequerimento.Descricao}}, que o imóvel acima descrito não se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("Todo e qualquer procedimento de Registro Cartorial deverá obedecer o disposto no § 2º do Art. 3º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987."); 
				add("A presente declaração não dispensa a observância do disposto no § 3º do Art. 216-A da Lei nº 6.015, de 31 de dezembro de 1973 pelo Oficial Registrador em procedimentos de Usucapião Extrajudicial, ou qualquer outro procedimento de registro cartorial."); 
				add("A presente declaração não dispensa a citação da Advocacia Geral da União quando do ajuizamento de ação de usucapião, conforme determina o § 3º do Art. 242 da Lei nº 13.105, de 16 de março de 2015 - Código de Processo Civil."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}}),
	
	OUTRO_COM_INTERESSE(
			OpcaoObjetivoDeclaracaoDominio.OUTRO,
			true,
			"Outro - Com interesse",
			new ArrayList<String>() {/**
				 * 
				 */
				private static final long serialVersionUID = 8362707310150117245L;

			{
				add("Conforme análise técnica constante do Parecer {{Parecer.IdentificacaoDocumento}}, DECLARAMOS para fins de {{RequerimentoSPUnet.ObjetivoRequerimento.Descricao}}, que o imóvel acima descrito se sobrepõe a bens de propriedade da União, caracterizados como Terreno de Marinha e Acrescidos ou Próprios Nacionais administrados pela Superintendência do Patrimônio da União em Santa Catarina - SPU/SC."); 
				add("Diante do exposto, existe interesse da Superintendência do Patrimônio da União em Santa Catarina - SPU/SC no imóvel questão.");
				add("Todo e qualquer procedimento de Registro Cartorial deverá obedecer o disposto no § 2º do Art. 3º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987."); 
				add("A utilização de bem imóvel de propriedade da União, sem a prévia autorização, configura infração administrativa contra do Patrimônio da União, passível de multa e indenização, nos termos do Art. 6º do Decreto-Lei nº 2.398, de 21 de dezembro de 1987."); 
				add("A presente declaração não dispensa a citação da Advocacia Geral da União quando do ajuizamento de ação de usucapião, conforme determina o § 3º do Art. 242 da Lei nº 13.105, de 16 de março de 2015 - Código de Processo Civil."); 
				add("A presente declaração foi elaborada com base na documentação apresentada pelo interessado no Processo SEI {{RequerimentoSPUnet.ProcedimentoFormatado}}, sob as penas da lei, sendo que a integralidade do referido processo administrativo pode ser consultada no endereço eletrônico http://www.fazenda.gov.br/sei, na opção Consulta de Processos.");
			}});
	
	private OpcaoObjetivoDeclaracaoDominio objetivoRequerimentoEnum;
	
	private Boolean comInteresse;
	
	private String rotulo;

	private List<String> conclusao;
	
	public static OpcaoConteudoDeclaracaoDeDominio obter(OpcaoObjetivoDeclaracaoDominio objetivo, OpcaoParecerTecnicoDeclaracaoDominio opcaoParecerTecnicoDeclaracaoDominio) {
		
		Stream<OpcaoConteudoDeclaracaoDeDominio> opcoes = EnumSet.allOf(OpcaoConteudoDeclaracaoDeDominio.class)
				.stream().filter(
						e -> {
							if(e != null) {
								return e.getObjetivoRequerimentoEnum().getId().equals(objetivo.getId());															
							} else {
								return false;
							}
						}).distinct();
		
		Optional<OpcaoConteudoDeclaracaoDeDominio> opcaoEnum = opcoes.filter(e -> e.mesmoInteresse(opcaoParecerTecnicoDeclaracaoDominio.getComInteresse())).findFirst();
		if(opcaoEnum.isPresent())
			return opcaoEnum.get();
		return null;
	}


	private OpcaoConteudoDeclaracaoDeDominio(
			OpcaoObjetivoDeclaracaoDominio objetivoRequerimentoEnum, 
			Boolean comInteresse,
			String rotulo,
			List<String> conclusao) {
		this.objetivoRequerimentoEnum = objetivoRequerimentoEnum;
		this.comInteresse = comInteresse;
		this.rotulo = rotulo;
		this.conclusao = conclusao;
	}
	
	private boolean mesmoInteresse(Boolean interesse) {
		if(this.comInteresse == null && interesse == null) {
			return true;
		}
		if(this.comInteresse == null || interesse == null) {
			return false;
		}
		return this.comInteresse.booleanValue() == interesse.booleanValue();		
	}

	public OpcaoObjetivoDeclaracaoDominio getObjetivoRequerimentoEnum() {
		return objetivoRequerimentoEnum;
	}
	
	public String toString() {
		return this.rotulo;
	}

	public List<String> getConclusao() {
		return conclusao;
	}

	public Boolean getComInteresse() {
		return comInteresse;
	}

}