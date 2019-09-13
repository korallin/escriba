package br.gov.spusc.escriba.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.border.TitledBorder;

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
	private JTextField textCredencialSEILogin;
	private JTextField textObjetivoOutro;
	
	private JPasswordField textCredencialSPUnetSenha;
	private JPasswordField textCredencialSEISenha;
	
	private JComboBox<OpcaoObjetivoRequerimentoEnum> comboObjetivoRequerimento;

	private EscribaApp escriba;
	private CredencialAcessoDialog credencialAcessoSEIDialog;
	private CredencialAcessoDialog credencialAcessoSPUnetDialog;
	private Requerimento requerimento;
	private JPanel panel;


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

	private JPanel montarPainelPrincipal() {
		JPanel panelPrincipal = new JPanel();
		GridLayout gl_panelPrincipal = new GridLayout(0, 1);
		gl_panelPrincipal.setVgap(5);
		gl_panelPrincipal.setHgap(5);
		panelPrincipal.setLayout(gl_panelPrincipal);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Credenciais de Acesso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPrincipal.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel = new JPanel();
		panel_2.add(panel);
		panel.setBorder(new TitledBorder(null, "SPUnet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {60, 200};
		gbl_panel.rowHeights = new int[] {30, 30};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0};
		panel.setLayout(gbl_panel);
		JLabel label_1 = new JLabel("Login:", JLabel.RIGHT);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		panel.add(label_1, gbc_label_1);
		textCredencialSPUnetLogin = new JTextField();
		GridBagConstraints gbc_textCredencialSPUnetLogin = new GridBagConstraints();
		gbc_textCredencialSPUnetLogin.fill = GridBagConstraints.BOTH;
		gbc_textCredencialSPUnetLogin.insets = new Insets(0, 0, 5, 0);
		gbc_textCredencialSPUnetLogin.gridx = 1;
		gbc_textCredencialSPUnetLogin.gridy = 0;
		panel.add(textCredencialSPUnetLogin, gbc_textCredencialSPUnetLogin);
		JLabel label_2 = new JLabel("Senha: ", JLabel.RIGHT);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		panel.add(label_2, gbc_label_2);
		textCredencialSPUnetSenha = new JPasswordField();
		GridBagConstraints gbc_textCredencialSPUnetSenha = new GridBagConstraints();
		gbc_textCredencialSPUnetSenha.fill = GridBagConstraints.BOTH;
		gbc_textCredencialSPUnetSenha.gridx = 1;
		gbc_textCredencialSPUnetSenha.gridy = 1;
		panel.add(textCredencialSPUnetSenha, gbc_textCredencialSPUnetSenha);
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "SEI", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {60, 200};
		gbl_panel_1.rowHeights = new int[] {30, 30};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		JLabel label_3 = new JLabel("Login:", JLabel.RIGHT);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 0;
		panel_1.add(label_3, gbc_label_3);
		textCredencialSEILogin = new JTextField();
		GridBagConstraints gbc_textCredencialSEILogin = new GridBagConstraints();
		gbc_textCredencialSEILogin.fill = GridBagConstraints.BOTH;
		gbc_textCredencialSEILogin.insets = new Insets(0, 0, 5, 0);
		gbc_textCredencialSEILogin.gridx = 1;
		gbc_textCredencialSEILogin.gridy = 0;
		panel_1.add(textCredencialSEILogin, gbc_textCredencialSEILogin);
		JLabel label_4 = new JLabel("Senha: ", JLabel.RIGHT);
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(0, 0, 0, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 1;
		panel_1.add(label_4, gbc_label_4);
		textCredencialSEISenha = new JPasswordField();
		GridBagConstraints gbc_textCredencialSEISenha = new GridBagConstraints();
		gbc_textCredencialSEISenha.fill = GridBagConstraints.BOTH;
		gbc_textCredencialSEISenha.gridx = 1;
		gbc_textCredencialSEISenha.gridy = 1;
		panel_1.add(textCredencialSEISenha, gbc_textCredencialSEISenha);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Requerimento SPUnet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPrincipal.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {120, 200, 120, 300};
		gbl_panel_3.rowHeights = new int[] {30, 30, 30};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_3.setLayout(gbl_panel_3);
		JLabel label = new JLabel("Nº de Atendimento: ", JLabel.RIGHT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_3.add(label, gbc_label);
		textAtendimento = new JTextField();
		GridBagConstraints gbc_textAtendimento = new GridBagConstraints();
		gbc_textAtendimento.fill = GridBagConstraints.BOTH;
		gbc_textAtendimento.insets = new Insets(0, 0, 5, 5);
		gbc_textAtendimento.gridx = 1;
		gbc_textAtendimento.gridy = 0;
		panel_3.add(textAtendimento, gbc_textAtendimento);
		textAtendimento.setText("SC03148/2019");
		JButton botaoObterDadosSPUnet = new JButton("Obter dados do requerimento");
		GridBagConstraints gbc_botaoObterDadosSPUnet = new GridBagConstraints();
		gbc_botaoObterDadosSPUnet.fill = GridBagConstraints.BOTH;
		gbc_botaoObterDadosSPUnet.insets = new Insets(0, 0, 5, 5);
		gbc_botaoObterDadosSPUnet.gridx = 2;
		gbc_botaoObterDadosSPUnet.gridy = 0;
		panel_3.add(botaoObterDadosSPUnet, gbc_botaoObterDadosSPUnet);
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
					escriba.obterDadosRequerimentoSPUnet();
				} catch (TimeoutException timeoutException) {
					exibirMensagem("O SPUnet parece não estar respondendo.\n\rVerifique sua conexão de rede ou aguarde o retorno do sistema.");
				} 
				catch (Exception e1) {
					exibirMensagem(e1.getMessage());
				}
			}
		});
		JLabel label_7 = new JLabel("NUP SEI: ", JLabel.RIGHT);
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.fill = GridBagConstraints.BOTH;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 1;
		panel_3.add(label_7, gbc_label_7);
		textNupSei = new JTextField();
		GridBagConstraints gbc_textNupSei = new GridBagConstraints();
		gbc_textNupSei.fill = GridBagConstraints.BOTH;
		gbc_textNupSei.insets = new Insets(0, 0, 5, 5);
		gbc_textNupSei.gridx = 1;
		gbc_textNupSei.gridy = 1;
		panel_3.add(textNupSei, gbc_textNupSei);
		
		
		// NUP SEi
		JLabel label_5 = new JLabel("Objetivo do Requerimento:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.BOTH;
		gbc_label_5.insets = new Insets(0, 0, 0, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 2;
		panel_3.add(label_5, gbc_label_5);
		
		comboObjetivoRequerimento = new JComboBox<OpcaoObjetivoRequerimentoEnum>();
		GridBagConstraints gbc_comboObjetivoRequerimento = new GridBagConstraints();
		gbc_comboObjetivoRequerimento.fill = GridBagConstraints.BOTH;
		gbc_comboObjetivoRequerimento.insets = new Insets(0, 0, 0, 5);
		gbc_comboObjetivoRequerimento.gridx = 1;
		gbc_comboObjetivoRequerimento.gridy = 2;
		panel_3.add(comboObjetivoRequerimento, gbc_comboObjetivoRequerimento);
		comboObjetivoRequerimento.setModel(new DefaultComboBoxModel<OpcaoObjetivoRequerimentoEnum>(OpcaoObjetivoRequerimentoEnum.values()));
		
		JLabel label_6 = new JLabel("Outro: ", JLabel.RIGHT);
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.fill = GridBagConstraints.BOTH;
		gbc_label_6.insets = new Insets(0, 0, 0, 5);
		gbc_label_6.gridx = 2;
		gbc_label_6.gridy = 2;
		panel_3.add(label_6, gbc_label_6);
		textObjetivoOutro = new JTextField();
		textObjetivoOutro.setEnabled(false);
		GridBagConstraints gbc_textObjetivoOutro = new GridBagConstraints();
		gbc_textObjetivoOutro.fill = GridBagConstraints.BOTH;
		gbc_textObjetivoOutro.gridx = 3;
		gbc_textObjetivoOutro.gridy = 2;
		panel_3.add(textObjetivoOutro, gbc_textObjetivoOutro);
		
		return panelPrincipal;
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
	
	public CredencialAcesso obterCredencialSEI() {
		return new CredencialAcesso(
				textCredencialSEILogin.getText(), 
				new String(textCredencialSEISenha.getPassword()));
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
		textObjetivoOutro.setText(null);
		textObjetivoOutro.setEnabled(false);
		
		if(this.requerimento != null) {
			Optional<OpcaoObjetivoRequerimentoEnum> opcaoEnum = EnumSet.allOf(OpcaoObjetivoRequerimentoEnum.class)
				.stream()
					.filter(e -> e.getId().equals(requerimento.getObjetivoRequerimento().getId()))
					.findFirst();
			if(opcaoEnum.isPresent()) {
				comboObjetivoRequerimento.setSelectedItem(opcaoEnum.get());
				if(opcaoEnum.equals(OpcaoObjetivoRequerimentoEnum.OUTRO)) {
					textObjetivoOutro.setEnabled(true);
				}
			}
			
			if(this.requerimento.getObjetivoRequerimento() != null) {
				textObjetivoOutro.setText(this.requerimento.getObjetivoRequerimento().getDescricao());
			}
			
			textNupSei.setText(this.requerimento.getProcedimentoFormatado());
		}
	}

}
