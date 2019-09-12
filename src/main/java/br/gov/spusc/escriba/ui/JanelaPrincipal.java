package br.gov.spusc.escriba.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.openqa.selenium.TimeoutException;

import br.gov.spusc.escriba.CredencialAcesso;
import br.gov.spusc.escriba.EscribaApp;
import br.gov.spusc.escriba.OpcaoObjetivoRequerimentoEnum;
import br.gov.spusc.escriba.pojo.Requerimento;

public class JanelaPrincipal {	
	
	private JFrame frame;
	private JTextArea textAreaConsole;
	private JTextField textAtendimento;
	private JTextField textNupSei;
	
	private JTextField textCredencialSPUnetLogin;
	private JPasswordField textCredencialSPUnetSenha;
	private JComboBox<OpcaoObjetivoRequerimentoEnum> comboObjetivoRequerimento;
	private JTextField textObjetivoOutro;

	private EscribaApp escriba;
	private CredencialAcessoDialog credencialAcessoSEIDialog;
	private CredencialAcessoDialog credencialAcessoSPUnetDialog;
	private Requerimento requerimento;

	public JanelaPrincipal(EscribaApp escriba) {
		this.escriba = escriba;
		this.initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		
		montarMenuPrincipal();
		JPanel panelPrincipal = montarPainelPrincipal();
		JPanel panelRodape = montarPainelRodape();
		
		JSplitPane divisorPainelPrincipalPainelRodape = montarDivisorPainelPrincipalPainelRodape();		
		divisorPainelPrincipalPainelRodape.setRightComponent(panelRodape);
		divisorPainelPrincipalPainelRodape.setLeftComponent(panelPrincipal);		
		
		frame.setVisible(true);
	}

	private void montarMenuPrincipal() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnConfiguraes = new JMenu("Configurações");
		menuBar.add(mnConfiguraes);
		
		JMenu mnCredenciaisDeAcesso = new JMenu("Credenciais de Acesso");
		mnConfiguraes.add(mnCredenciaisDeAcesso);
		
		JMenuItem mntmSei = new JMenuItem("SEI");
		mntmSei.addActionListener(clicouCredencialSEI());
		mnCredenciaisDeAcesso.add(mntmSei);
		
		JMenuItem mntmSpunet = new JMenuItem("SPUnet");
		mntmSpunet.addActionListener(clicouCredencialSPUnet());
		mnCredenciaisDeAcesso.add(mntmSpunet);
	}

	private JPanel montarPainelRodape() {
		JPanel panelRodape = new JPanel();
		panelRodape.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Console:");
		panelRodape.add(lblNewLabel, BorderLayout.NORTH);
		
		textAreaConsole = new JTextArea();
		textAreaConsole.setEditable(false);
		lblNewLabel.setLabelFor(textAreaConsole);
		panelRodape.add(textAreaConsole, BorderLayout.CENTER);
		return panelRodape;
	}

	private JSplitPane montarDivisorPainelPrincipalPainelRodape() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		return splitPane;
	}

	private JPanel montarPainelPrincipal() {
		JPanel panelPrincipal = new JPanel();
		GridLayout gl_panelPrincipal = new GridLayout(0, 4);
		gl_panelPrincipal.setVgap(5);
		gl_panelPrincipal.setHgap(5);
		panelPrincipal.setLayout(gl_panelPrincipal);
		
		// Credenciais do SPUnet
		panelPrincipal.add(new JLabel("Credencial SPUnet", JLabel.RIGHT));
		panelPrincipal.add(new JLabel());
		panelPrincipal.add(new JLabel());
		panelPrincipal.add(new JLabel());
		
		panelPrincipal.add(new JLabel("Login:", JLabel.RIGHT));
		textCredencialSPUnetLogin = new JTextField();
		panelPrincipal.add(textCredencialSPUnetLogin);
		
		panelPrincipal.add(new JLabel("Senha: ", JLabel.RIGHT));
		textCredencialSPUnetSenha = new JPasswordField();
		panelPrincipal.add(textCredencialSPUnetSenha);
		
		
		// Atendimento SPUnet
		panelPrincipal.add(new JLabel());
		panelPrincipal.add(new JLabel());
		panelPrincipal.add(new JLabel("Nº de Atendimento: ", JLabel.RIGHT));
		textAtendimento = new JTextField();
		textAtendimento.setText("SC03148/2019");
		panelPrincipal.add(textAtendimento);
		
		// Botao Obter Dados
		panelPrincipal.add(new JLabel());
		panelPrincipal.add(new JLabel());
		panelPrincipal.add(new JLabel());		
		JButton botaoObterDadosSPUnet = new JButton("Obter dados do requerimento");
		botaoObterDadosSPUnet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validarInputObrigatorio(getTextCredencialSPUnetLogin(), "Informe o login do SPUnet");
					validarInputObrigatorio(getTextCredencialSPUnetSenha(), "Informe a senha do SPUnet"); 
					if(textAtendimento.getText().isBlank()) {
						exibirMensagem("Informe o número do atendimento SPUnet");
						textAtendimento.requestFocus();
						return;
					}
					escriba.extrair();
				} catch (TimeoutException timeoutException) {
					exibirMensagem("O SPUnet parece não estar respondendo.\n\rVerifique sua conexão de rede ou aguarde o retorno do sistema.");
				} 
				catch (Exception e1) {
					exibirMensagem(e1.getMessage());
				}
			}
		});
		panelPrincipal.add(botaoObterDadosSPUnet);
		
		
		// NUP SEi
		panelPrincipal.add(new JLabel("Objetivo do Requerimento:"));
		
		comboObjetivoRequerimento = new JComboBox<OpcaoObjetivoRequerimentoEnum>();
		comboObjetivoRequerimento.setModel(new DefaultComboBoxModel<OpcaoObjetivoRequerimentoEnum>(OpcaoObjetivoRequerimentoEnum.values()));
		panelPrincipal.add(comboObjetivoRequerimento);
		
		panelPrincipal.add(new JLabel("Outro: ", JLabel.RIGHT));
		textObjetivoOutro = new JTextField();
		panelPrincipal.add(textObjetivoOutro);
		
		panelPrincipal.add(new JLabel(""));
		panelPrincipal.add(new JLabel(""));
		panelPrincipal.add(new JLabel("NUP SEI: ", JLabel.RIGHT));
		textNupSei = new JTextField();
		panelPrincipal.add(textNupSei);
		
		return panelPrincipal;
	}

	protected void validarInputObrigatorio(JTextField inputField, String mensagem) throws Exception {
		if(inputField.getText() == null || inputField.getText().isEmpty()) {
			inputField.requestFocus();
			throw new Exception(mensagem);
		}		
	}

	public void exibirMensagem(String string) {
		JOptionPane.showMessageDialog(frame, string + "\n\r");		
	}

	private ActionListener clicouCredencialSEI() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCredencialAcessoDialog(credencialAcessoSEIDialog, escriba.getCredencialAcessoSEI());
			}
		};
	}
	
	private ActionListener clicouCredencialSPUnet() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCredencialAcessoDialog(credencialAcessoSPUnetDialog, escriba.getCredencialAcessoSPUnet());
			}

		};
	}

	private void abrirCredencialAcessoDialog(CredencialAcessoDialog credencialAcessoDialog, CredencialAcesso credencial) { 
		if(credencialAcessoDialog == null) {
			credencialAcessoDialog = new CredencialAcessoDialog(this, "Credencial de Acesso", true);
			credencialAcessoDialog.setCredencialAcesso(credencial);
		}
		credencialAcessoDialog.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	public JTextArea getConsole() {
		return this.textAreaConsole;
	}

	public EscribaApp getEscriba() {
		return escriba;
	}

	public JTextField getTextCredencialSPUnetLogin() {
		return textCredencialSPUnetLogin;
	}

	public JPasswordField getTextCredencialSPUnetSenha() {
		return textCredencialSPUnetSenha;
	}

	public CredencialAcesso obterCredencialSPUnet() {
		return new CredencialAcesso(
				textCredencialSPUnetLogin.getText(), 
				new String(textCredencialSPUnetSenha.getPassword()));
	}

	public String obterNumeroAtendimento() {
		return textAtendimento.getText();
	}

	public void setRequerimento(Requerimento requerimento) {
		this.requerimento = requerimento;
		aplicarDadosRequerimento(requerimento);
	}

	private void aplicarDadosRequerimento(Requerimento requerimento) {
		comboObjetivoRequerimento.setSelectedItem(OpcaoObjetivoRequerimentoEnum.INDEFINIDO);
		textObjetivoOutro.setText(null);
		textNupSei.setText(null);
		
		if(this.requerimento != null) {
			Optional<OpcaoObjetivoRequerimentoEnum> opcaoEnum = EnumSet.allOf(OpcaoObjetivoRequerimentoEnum.class)
				.stream()
					.filter(e -> e.getId().equals(requerimento.getObjetivoRequerimento().getId()))
					.findFirst();
			if(opcaoEnum.isPresent()) {
				comboObjetivoRequerimento.setSelectedItem(opcaoEnum.get());
			}
			
			if(this.requerimento.getObjetivoRequerimento() != null) {
				textObjetivoOutro.setText(this.requerimento.getObjetivoRequerimento().getDescricao());
			}
			
			textNupSei.setText(this.requerimento.getProcedimentoFormatado());
		}
	}

}
