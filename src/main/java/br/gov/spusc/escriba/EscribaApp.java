package br.gov.spusc.escriba;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.TimeoutException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.gov.spusc.escriba.pojo.ObjetivoRequerimento;
import br.gov.spusc.escriba.pojo.Requerente;
import br.gov.spusc.escriba.pojo.Requerimento;
import br.gov.spusc.escriba.ui.JanelaPrincipal;

@SpringBootApplication
@ComponentScan(basePackages = { "br.gov.economia.seddm.spu" })
public class EscribaApp {

	private static final String CONFIG_ARQ_COMENTARIO = "Arquivo de Configuração do Escriba";
	private static final String CONFIG_ARQ_CAMINHO = "escriba.conf";
	private static final String CONFIG_CREDENCIAL_SPUNET_LOGIN = "credencial.spunet.login";
	private static final String CONFIG_CREDENCIAL_SPUNET_SENHA = "credencial.spunet.senha";
	private static final String CONFIG_CREDENCIAL_SEI_LOGIN = "credencial.sei.login";
	private static final String CONFIG_CREDENCIAL_SEI_SENHA = "credencial.sei.senha";

	private JanelaPrincipal janelaPrincipal;
	private CredencialAcesso credencialAcessoSPUnet;
	private CredencialAcesso credencialAcessoSEI;
	private Requerimento requerimento;

	private Properties config;
	private boolean mockSPUnet = false;

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
			janelaPrincipal.getTextCredencialSPUnetLogin().setText(getCredencialAcessoSPUnet().getLogin());
		}
		if (config.containsKey(CONFIG_CREDENCIAL_SPUNET_SENHA)) {
			credencialAcessoSPUnet.setSenha(config.getProperty((String) CONFIG_CREDENCIAL_SPUNET_SENHA));
			janelaPrincipal.getTextCredencialSPUnetSenha().setText(getCredencialAcessoSPUnet().getSenha());
		}
		if (config.containsKey(CONFIG_CREDENCIAL_SEI_LOGIN)) {
			credencialAcessoSEI.setLogin(config.getProperty((String) CONFIG_CREDENCIAL_SEI_LOGIN));
			janelaPrincipal.getTextCredencialSEILogin().setText(getCredencialAcessoSEI().getLogin());
		}
		if (config.containsKey(CONFIG_CREDENCIAL_SEI_SENHA)) {
			credencialAcessoSEI.setSenha(config.getProperty((String) CONFIG_CREDENCIAL_SEI_SENHA));
			janelaPrincipal.getTextCredencialSEISenha().setText(getCredencialAcessoSEI().getSenha());
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
			requerimento = new Requerimento();
			requerimento.setNumeroAtendimento("SC/012345");
			requerimento.setProcedimentoFormatado("10154.125690/2019-27");
			
			requerimento.setObjetivoRequerimento(new ObjetivoRequerimento());
			requerimento.getObjetivoRequerimento().setId(OpcaoObjetivoRequerimentoEnum.USUCAPIAO_EXTRAJUDICIAL.getId());
			
			requerimento.setRequerente(new Requerente());
			requerimento.getRequerente().setNome("JOÃO JOSÉ DA SILVA E SOUZA");
			requerimento.getRequerente().setCpfCnpj("071.533.494-80");
			
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
					
					log("Inicializando driver... ", false);
					operador.inicializarDriver();
					log("OK!");
					
					log("Iniciando autenticação no sistema... ", false);
					operador.fazerLogin(janelaPrincipal.obterCredencialSEI());
					log("OK!");
					
					operador.fecharPopup();
					
					operador.acessarProcesso(requerimento.getProcedimentoFormatado());
					
					String documentoGerado = operador.incluirDocumento("Parecer", 3864629, obterMapaSubstituicoes());
					log("Documento gerado: " + documentoGerado);
					
				} catch (TimeoutException te) {
					log("O SEI parece não estar funcionando. Verifique sua conexão de rede e/ou aguarde o retorno do sistema.");
				} catch (Exception e) {
					log(e.getMessage());
				} finally {
					operador.encerrarDriver();
				}				
				
			}

			private Map<String, String> obterMapaSubstituicoes() {
				Map<String, String> mapa = new HashMap<String, String>();
				OpcaoConclusaoParecerTecnicoDeclaracaoDominioEnum conclusaoSelecionada = 
						(OpcaoConclusaoParecerTecnicoDeclaracaoDominioEnum) janelaPrincipal.getComboParecerTecnico().getSelectedItem(); 
				mapa.put("Analise.Conclusao", conclusaoSelecionada.getConclusao());
				mapa.put("Requerimento.ProcedimentoFormatado", requerimento.getProcedimentoFormatado());
				mapa.put("Requerimento.NumeroAtendimento", requerimento.getNumeroAtendimento());
				mapa.put("Requerimento.Requerente.nome", requerimento.getRequerente().getNome());
				mapa.put("Requerimento.Requerente.cpfCnpj", requerimento.getRequerente().getCpfCnpj());
				return mapa;
			}
		}).start();
	}

}