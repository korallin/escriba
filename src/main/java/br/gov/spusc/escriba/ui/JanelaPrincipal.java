package br.gov.spusc.escriba.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.gov.spusc.escriba.CredencialAcesso;
import br.gov.spusc.escriba.EscribaApp;
import br.gov.spusc.escriba.OpcaoObjetivoRequerimentoEnum;
import br.gov.spusc.escriba.OpcaoParecerTecnicoEnum;
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
	private JComboBox<OpcaoParecerTecnicoEnum> comboParecerTecnico;

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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setVgap(20);
		borderLayout.setHgap(20);
		frame.setResizable(false);
		frame.setBounds(100, 100, 840, 768);
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
		menuBar.setMargin(new Insets(10, 5, 10, 5));
		menuBar.setBackground(Color.WHITE);
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
		panelPrincipal.setBorder(new EmptyBorder(10, 5, 10, 5));
		panelPrincipal.setBackground(Color.WHITE);
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[] {0};
		gbl_panelPrincipal.rowHeights = new int[] {0, 0, 0};
		gbl_panelPrincipal.columnWeights = new double[]{1.0};
		gbl_panelPrincipal.rowWeights = new double[]{0.0, 0.0, 1.0};
		panelPrincipal.setLayout(gbl_panelPrincipal);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Credenciais de Acesso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panelPrincipal.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
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
		panel_1.setBackground(Color.WHITE);
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
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(null, "Requerimento SPUnet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panelPrincipal.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {200, 120, 160, 300};
		gbl_panel_3.rowHeights = new int[] {30, 30, 30};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
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
		
		JButton botaoObterDadosSPUnet = new JButton("Obter Requerimento");
		GridBagConstraints gbc_botaoObterDadosSPUnet = new GridBagConstraints();
		gbc_botaoObterDadosSPUnet.anchor = GridBagConstraints.WEST;
		gbc_botaoObterDadosSPUnet.gridwidth = 2;
		gbc_botaoObterDadosSPUnet.insets = new Insets(0, 0, 5, 0);
		gbc_botaoObterDadosSPUnet.gridx = 2;
		gbc_botaoObterDadosSPUnet.gridy = 0;
		panel_3.add(botaoObterDadosSPUnet, gbc_botaoObterDadosSPUnet);
		botaoObterDadosSPUnet.addActionListener(clicouObterRequerimento());
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
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.BOTH;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 2;
		panel_3.add(label_5, gbc_label_5);
		
		comboObjetivoRequerimento = new JComboBox<OpcaoObjetivoRequerimentoEnum>();
		comboObjetivoRequerimento.addActionListener(selecionouObjetivoRequerimento());
		GridBagConstraints gbc_comboObjetivoRequerimento = new GridBagConstraints();
		gbc_comboObjetivoRequerimento.gridwidth = 2;
		gbc_comboObjetivoRequerimento.fill = GridBagConstraints.BOTH;
		gbc_comboObjetivoRequerimento.insets = new Insets(0, 0, 5, 5);
		gbc_comboObjetivoRequerimento.gridx = 1;
		gbc_comboObjetivoRequerimento.gridy = 2;
		panel_3.add(comboObjetivoRequerimento, gbc_comboObjetivoRequerimento);
		comboObjetivoRequerimento.setModel(new DefaultComboBoxModel<OpcaoObjetivoRequerimentoEnum>(OpcaoObjetivoRequerimentoEnum.values()));
		textObjetivoOutro = new JTextField();
		textObjetivoOutro.setEnabled(false);
		GridBagConstraints gbc_textObjetivoOutro = new GridBagConstraints();
		gbc_textObjetivoOutro.insets = new Insets(0, 0, 5, 0);
		gbc_textObjetivoOutro.fill = GridBagConstraints.BOTH;
		gbc_textObjetivoOutro.gridx = 3;
		gbc_textObjetivoOutro.gridy = 2;
		panel_3.add(textObjetivoOutro, gbc_textObjetivoOutro);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "An\u00E1lise e Instru\u00E7\u00E3o do Processo no SEI", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 2;
		panelPrincipal.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] {80, 400, 120};
		gbl_panel_4.rowHeights = new int[] {30};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel_4.rowWeights = new double[]{0.0};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblAnlise = new JLabel("Análise:", JLabel.RIGHT);
		GridBagConstraints gbc_lblAnlise = new GridBagConstraints();
		gbc_lblAnlise.anchor = GridBagConstraints.EAST;
		gbc_lblAnlise.insets = new Insets(0, 0, 0, 5);
		gbc_lblAnlise.gridx = 0;
		gbc_lblAnlise.gridy = 0;
		panel_4.add(lblAnlise, gbc_lblAnlise);
		
		comboParecerTecnico = new JComboBox<OpcaoParecerTecnicoEnum>();
		comboParecerTecnico.setModel(new DefaultComboBoxModel<OpcaoParecerTecnicoEnum>(OpcaoParecerTecnicoEnum.values()));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_4.add(comboParecerTecnico, gbc_comboBox);
		
		JButton btnNewButton = new JButton("Instruir Processo");
		btnNewButton.addActionListener(clicouInstruirProcesso());
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		panel_4.add(btnNewButton, gbc_btnNewButton);
		
		return panelPrincipal;
	}

	private ActionListener clicouObterRequerimento() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validarInputObrigatorio(getTextCredencialSPUnetLogin(), "Informe o login do SPUnet");
					validarInputObrigatorio(getTextCredencialSPUnetSenha(), "Informe a senha do SPUnet");
					validarInputObrigatorio(textAtendimento, "Informe o número do atendimento SPUnet");
					escriba.obterDadosRequerimentoSPUnet();
				} catch (Exception e1) {
					exibirMensagem(e1.getMessage());
				}
			}
		};
	}

	private ActionListener clicouInstruirProcesso() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validarInputObrigatorio(getTextCredencialSEILogin(), "Informe o login do SEI");
					validarInputObrigatorio(getTextCredencialSEISenha(), "Informe a senha do SEI");
					validarInputObrigatorio(textNupSei, "NUP SEI não informado");
					if(comboParecerTecnico.getSelectedItem() == null) {
						exibirMensagem("Opção de parecer selecionada é inválida");
						comboParecerTecnico.requestFocus();
						return;
					}
					requerimento.setProcedimentoFormatado(textNupSei.getText());
					escriba.instruirProcessoSEI();					
				} catch (Exception e1) {
					exibirMensagem(e1.getMessage());
				}
				
			}
		};
	}
	
	private ActionListener selecionouObjetivoRequerimento() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcaoObjetivoRequerimentoEnum opcaoSelecionada = (OpcaoObjetivoRequerimentoEnum) comboObjetivoRequerimento.getSelectedItem();
				popularOpcoesParecerTecnico(opcaoSelecionada);
			}
		};
	}

	private JPanel montarPainelRodape() {
		JPanel panelRodape = new JPanel();
		panelRodape.setBackground(Color.WHITE);
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
		splitPane.setBackground(Color.WHITE);
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

	public JTextField getTextCredencialSEILogin() {
		return textCredencialSEILogin;
	}

	public JPasswordField getTextCredencialSEISenha() {
		return textCredencialSEISenha;
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
			OpcaoObjetivoRequerimentoEnum objetivoDoRequerimento = opcaoEnum.get();
			popularOpcoesParecerTecnico(objetivoDoRequerimento);
			if(opcaoEnum.isPresent()) {
				comboObjetivoRequerimento.setSelectedItem(objetivoDoRequerimento);
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

	private void popularOpcoesParecerTecnico(OpcaoObjetivoRequerimentoEnum opcaoEnum) {
		Stream<OpcaoParecerTecnicoEnum> opcoesParecer = EnumSet.allOf(OpcaoParecerTecnicoEnum.class)
			.stream()
				.filter(e -> e.getObjetivoRequerimentoEnum().equals(opcaoEnum));
		DefaultComboBoxModel<OpcaoParecerTecnicoEnum> model = new DefaultComboBoxModel<OpcaoParecerTecnicoEnum>();
		Iterator<OpcaoParecerTecnicoEnum> iterator = opcoesParecer.iterator();
		while(iterator.hasNext()) {
			model.addElement(iterator.next());
		}
		comboParecerTecnico.setModel(model);		
	}

}
