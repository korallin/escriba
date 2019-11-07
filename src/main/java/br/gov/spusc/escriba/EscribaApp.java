package br.gov.spusc.escriba;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.TimeoutException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.gov.spusc.escriba.pojo.Imovel;
import br.gov.spusc.escriba.pojo.ObjetivoRequerimento;
import br.gov.spusc.escriba.pojo.Requerente;
import br.gov.spusc.escriba.pojo.RequerimentoSPUnet;
import br.gov.spusc.escriba.pojo.SolicitacaoDocumentoSEI;
import br.gov.spusc.escriba.ui.JanelaPrincipal;

@SpringBootApplication
@ComponentScan(basePackages = { "br.gov.economia.seddm.spu" })
public class EscribaApp {

	private static final int NUMERO_DOCUMENTO_MODELO_PARECER = 3864629;
	private static final int NUMERO_DOCUMENTO_MODELO_DECLARACAO = 3865012;
	
	private static final String CONFIG_ARQ_COMENTARIO = "Arquivo de Configuração do Escriba";
	private static final String CONFIG_ARQ_CAMINHO = "escriba.conf";
	private static final String CONFIG_CREDENCIAL_SPUNET_LOGIN = "credencial.spunet.login";
	private static final String CONFIG_CREDENCIAL_SPUNET_SENHA = "credencial.spunet.senha";
	private static final String CONFIG_CREDENCIAL_SEI_LOGIN = "credencial.sei.login";
	private static final String CONFIG_CREDENCIAL_SEI_SENHA = "credencial.sei.senha";

	private JanelaPrincipal janelaPrincipal;
	private CredencialAcesso credencialAcessoSPUnet;
	private CredencialAcesso credencialAcessoSEI;
	private RequerimentoSPUnet requerimento;

	private Properties config;
	private boolean mockSPUnet = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					EscribaApp app = new EscribaApp();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EscribaApp() {
		inicializar();
	}

	private void inicializar() {
		credencialAcessoSPUnet = new CredencialAcesso();
		credencialAcessoSPUnet.setPropriedadeLogin(CONFIG_CREDENCIAL_SPUNET_LOGIN);
		credencialAcessoSPUnet.setPropriedadeSenha(CONFIG_CREDENCIAL_SPUNET_SENHA);

		credencialAcessoSEI = new CredencialAcesso();
		credencialAcessoSEI.setPropriedadeLogin(CONFIG_CREDENCIAL_SEI_LOGIN);
		credencialAcessoSEI.setPropriedadeSenha(CONFIG_CREDENCIAL_SEI_SENHA);

		janelaPrincipal = new JanelaPrincipal(this);
		carregarConfiguracoes();
	}

	private void carregarConfiguracoes() {
		config = new Properties();
		try {
			config.load(new FileInputStream(CONFIG_ARQ_CAMINHO));
			carregarCredenciais();
		} catch (IOException e) {
			log(e.getMessage());
			try {
				log("Criando arquivo de configuração: " + CONFIG_ARQ_CAMINHO);
				criarArquivoConfiguracao();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void carregarCredenciais() {

		if (config.containsKey(CONFIG_CREDENCIAL_SPUNET_LOGIN)) {
			credencialAcessoSPUnet.setLogin(config.getProperty((String) CONFIG_CREDENCIAL_SPUNET_LOGIN));
			// janelaPrincipal.getTextCredencialSPUnetLogin().setText(getCredencialAcessoSPUnet().getLogin());
		}
		if (config.containsKey(CONFIG_CREDENCIAL_SPUNET_SENHA)) {
			credencialAcessoSPUnet.setSenha(config.getProperty((String) CONFIG_CREDENCIAL_SPUNET_SENHA));
			// janelaPrincipal.getTextCredencialSPUnetSenha().setText(getCredencialAcessoSPUnet().getSenha());
		}
		if (config.containsKey(CONFIG_CREDENCIAL_SEI_LOGIN)) {
			credencialAcessoSEI.setLogin(config.getProperty((String) CONFIG_CREDENCIAL_SEI_LOGIN));
			// janelaPrincipal.getTextCredencialSEILogin().setText(getCredencialAcessoSEI().getLogin());
		}
		if (config.containsKey(CONFIG_CREDENCIAL_SEI_SENHA)) {
			credencialAcessoSEI.setSenha(config.getProperty((String) CONFIG_CREDENCIAL_SEI_SENHA));
			// janelaPrincipal.getTextCredencialSEISenha().setText(getCredencialAcessoSEI().getSenha());
		}

	}

	private void criarArquivoConfiguracao() throws IOException {
		config.store(new FileWriter(CONFIG_ARQ_CAMINHO), CONFIG_ARQ_COMENTARIO);
	}

	public CredencialAcesso getCredencialAcessoSEI() {
		return credencialAcessoSEI;
	}

	public CredencialAcesso getCredencialAcessoSPUnet() {
		return credencialAcessoSPUnet;
	}

	public Properties getConfig() {
		return config;
	}

	public void salvarConfiguracao(CredencialAcesso credencialAcesso) {
		config = new Properties();
		File file = new File(CONFIG_ARQ_CAMINHO);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			try {
				criarArquivoConfiguracao();
			} catch (IOException e) {
				log("Não foi possível criar arquivo de configuração: '" + CONFIG_ARQ_CAMINHO + "'");
				return;
			}
		}
		if (input != null) {
			try {
				config.load(input);
				config.setProperty(credencialAcesso.getPropriedadeLogin(), credencialAcesso.getLogin());
				config.setProperty(credencialAcesso.getPropriedadeSenha(), credencialAcesso.getSenha());
				config.store(new FileWriter(CONFIG_ARQ_CAMINHO), CONFIG_ARQ_COMENTARIO);
				log("Configurações salvas com sucesso!");
				carregarCredenciais();
			} catch (IOException e) {
				log("Não foi possível salvar configurações no arquivo '" + CONFIG_ARQ_CAMINHO + "'");
			}
		}

	}

	public void log(String mensagem) {
		log(mensagem, true);
	}

	public void log(String mensagem, boolean quebrarLinha) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				janelaPrincipal.getConsole().append(mensagem);
				if(quebrarLinha) {
					janelaPrincipal.getConsole().append("\n\r");	
				}
			}
		}).start();
		;
	}

	public void obterDadosRequerimentoSPUnet() {
		if(mockSPUnet) {
			requerimento = new RequerimentoSPUnet();
			requerimento.setNumeroAtendimento("SC/012345");
			requerimento.setProcedimentoFormatado("10154.125690/2019-27");
			
			requerimento.setObjetivoRequerimento(new ObjetivoRequerimento());
			requerimento.getObjetivoRequerimento().setId(OpcaoObjetivoDeclaracaoDominio.OUTRO.getId());
			requerimento.getObjetivoRequerimento().setObjetivo(OpcaoObjetivoDeclaracaoDominio.OUTRO.getObjetivo());
			requerimento.getObjetivoRequerimento().setDescricao("Outro objetivo não listado");
			
			requerimento.setRequerente(new Requerente());
			requerimento.getRequerente().setNome("JOÃO JOSÉ DA SILVA E SOUZA");
			requerimento.getRequerente().setCpfCnpj("071.533.494-80");
			
			requerimento.setImovel(new Imovel());
			requerimento.getImovel().setCep("88058-000");
			requerimento.getImovel().setTipoLogradouro("RUA");
			requerimento.getImovel().setLogradouro("DAS COUVES");
			requerimento.getImovel().setNumero("123");
			requerimento.getImovel().setComplemento("CASA");
			requerimento.getImovel().setMunicipio("SANTA ILUSAO");
			requerimento.getImovel().setBairro("FELICIDADE");
			requerimento.getImovel().setUf("SC");			
			
			janelaPrincipal.setRequerimento(requerimento);
		} else {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						log("Iniciando leitura do requerimento do SPUnet...");
						OperadorSPUnet operador = new OperadorSPUnet();
						
						log("Inicializando driver... ", false);
						operador.inicializarDriver();
						log("OK!");
						
						log("Iniciando autenticação no sistema... ", false);
						operador.fazerLogin(janelaPrincipal.obterCredencialSPUnet());
						log("OK!");
						
						log("Obtendo dados do requerimento...");
						requerimento = operador.obterRequerimentoPorNumeroAtendimento(janelaPrincipal.obterNumeroAtendimento());
						log(requerimento.toString());
						
						janelaPrincipal.setRequerimento(requerimento);
						operador.encerrarDriver();				
					} catch (TimeoutException te) {
						log("O SPUnet parece não estar funcionando. Verifique sua conexão de rede e/ou aguarde o retorno do sistema.");
					} catch (Exception e) {
						log(e.getMessage());
					}				
				}
			}).start();
		}
	}
	
	public void instruirProcessoSEI() {
		new Thread(new Runnable() {
	
			@Override
			public void run() {
				log("Iniciando a instrução do processo SEI...");
				OperadorSEI operador = new OperadorSEI();
				try {
					log("Inicializando driver...");
					operador.inicializarDriver();
					
					log("Iniciando autenticação no sistema...");
					operador.fazerLogin(janelaPrincipal.obterCredencialSEI());
					
					operador.fecharPopup();
					
					operador.acessarProcesso(requerimento.getProcedimentoFormatado());
					
					Map<String, List<String>> mapaDeMarcacoes = obterMapaSubstituicoes();
					SolicitacaoDocumentoSEI parecerGerado = inserirParecer(operador, mapaDeMarcacoes);
					inserirDeclaracao(operador, mapaDeMarcacoes, parecerGerado);
				} catch (TimeoutException te) {
					log("O SEI parece não estar funcionando. Verifique sua conexão de rede e/ou aguarde o retorno do sistema.");
				} catch (Exception e) {
					e.printStackTrace();
					log(e.getMessage());
				} finally {
					operador.encerrarDriver();
				}				
				
			}

			private SolicitacaoDocumentoSEI inserirDeclaracao(OperadorSEI operador, Map<String, List<String>> mapaDeMarcacoes,
					SolicitacaoDocumentoSEI parecerGerado) throws Exception {
				
				
				OpcaoParecerTecnicoDeclaracaoDominio parecerSelecionado = (OpcaoParecerTecnicoDeclaracaoDominio) janelaPrincipal.getComboParecerTecnico().getSelectedItem();
				if(parecerSelecionado.equals(OpcaoParecerTecnicoDeclaracaoDominio.DOCUMENTACAO_INSUFICIENTE)
						|| parecerSelecionado.equals(OpcaoParecerTecnicoDeclaracaoDominio.MANIFESTACAO_DNIT)) {
					return null;
				}
				
				OpcaoConteudoDeclaracaoDeDominio declaracaoSelecionada = OpcaoConteudoDeclaracaoDeDominio.obter(
						(OpcaoObjetivoDeclaracaoDominio) janelaPrincipal.getComboObjetivoRequerimento().getSelectedItem(),
						(OpcaoParecerTecnicoDeclaracaoDominio) janelaPrincipal.getComboParecerTecnico().getSelectedItem());
				
				// a ordem das chaves deste mapa será importante, por isso LinkedHashMap
				LinkedHashMap<String, List<String>> mapaOrdenado = new LinkedHashMap<String, List<String>>();
				mapaOrdenado.put("Declaracao.Conteudo", declaracaoSelecionada.getConclusao());
				
				List<String> valorIdentificacaoDocumento = new ArrayList<String>();
				valorIdentificacaoDocumento.add(parecerGerado.getIdentificacaoDocumentoGerado());
				mapaOrdenado.put("Parecer.IdentificacaoDocumento", valorIdentificacaoDocumento);
				
				mapaOrdenado.putAll(mapaDeMarcacoes);
				SolicitacaoDocumentoSEI declaracaoACriar = new SolicitacaoDocumentoSEI(
						requerimento.getProcedimentoFormatado(),
						"Declaração", 
						NUMERO_DOCUMENTO_MODELO_DECLARACAO, 
						mapaOrdenado);
				SolicitacaoDocumentoSEI declaracaoGerada = operador.inserirDocumento(declaracaoACriar);
				log("Documento gerado: " + declaracaoGerada.getIdentificacaoDocumentoGerado() + "(" + declaracaoGerada.getNumeroDocumentoGerado() + ")");
				return declaracaoACriar;
			}

			private SolicitacaoDocumentoSEI inserirParecer(OperadorSEI operador,
					Map<String, List<String>> mapaDeMarcacoes) throws Exception {
				SolicitacaoDocumentoSEI parecerACriar = new SolicitacaoDocumentoSEI(
						requerimento.getProcedimentoFormatado(), 
						"Parecer", 
						NUMERO_DOCUMENTO_MODELO_PARECER, 
						mapaDeMarcacoes);
				SolicitacaoDocumentoSEI parecerGerado = operador.inserirDocumento(parecerACriar);
				log("Documento gerado: " + parecerGerado.getIdentificacaoDocumentoGerado() + "(" + parecerGerado.getNumeroDocumentoGerado() + ")");
				return parecerGerado;
			}

		}).start();
	}

	private Map<String, List<String>> obterMapaSubstituicoes() {
		OpcaoParecerTecnicoDeclaracaoDominio conclusaoSelecionada = 
				(OpcaoParecerTecnicoDeclaracaoDominio) janelaPrincipal.getComboParecerTecnico().getSelectedItem();
		Map<String, List<String>> mapa = requerimento.preencherMapaDeMarcacoesValores();
		preencherMapaComConclusao(conclusaoSelecionada, mapa);		
		preencherMapaComCalendario(mapa);		
		return mapa;
	}

	private void preencherMapaComConclusao(OpcaoParecerTecnicoDeclaracaoDominio conclusaoSelecionada,
			Map<String, List<String>> mapa) {
		List<String> conteudoParecerTecnico = new ArrayList<String>();
		conteudoParecerTecnico.add(conclusaoSelecionada.getConclusao());
		mapa.put("Analise.Conclusao", conteudoParecerTecnico);
	}

	private void preencherMapaComCalendario(Map<String, List<String>> mapa) {
		Calendar hoje = Calendar.getInstance();
		mapa.put("Calendario.diaMes", new ArrayList<String>() {{
			add(String.valueOf(hoje.get(Calendar.DAY_OF_MONTH)));
		}});
		mapa.put("Calendario.mesExtenso", new ArrayList<String>() {{
			add(DateFormatSymbols.getInstance().getMonths()[hoje.get(Calendar.MONTH)]);
		}});
	}
}