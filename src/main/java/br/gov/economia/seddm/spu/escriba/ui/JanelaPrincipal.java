package br.gov.economia.seddm.spu.escriba.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import br.gov.economia.seddm.spu.escriba.CredencialAcesso;
import br.gov.economia.seddm.spu.escriba.EscribaApp;
import br.gov.economia.seddm.spu.escriba.OpcaoObjetivoDeclaracaoDominio;
import br.gov.economia.seddm.spu.escriba.OpcaoParecerTecnicoDeclaracaoDominio;
import br.gov.economia.seddm.spu.escriba.pojo.RequerimentoSPUnet;

public class JanelaPrincipal {

	private JFrame frame;

	private JTextArea textAreaConsole;

	private JTextField textAtendimento;
	private JTextField textNupSei;
	private JTextField textObjetivoOutro;

	private JComboBox<OpcaoObjetivoDeclaracaoDominio> comboObjetivoRequerimento;
	private JComboBox<OpcaoParecerTecnicoDeclaracaoDominio> comboParecerTecnico;

	private EscribaApp escriba;
	private CredencialAcessoDialog credencialAcessoSEIDialog;
	private CredencialAcessoDialog credencialAcessoSPUnetDialog;
	// private RequerimentoSPUnet requerimento;

	public JanelaPrincipal(EscribaApp escriba) {
		this.escriba = escriba;
		this.initialize();
	}

	private void initialize() {
		frame = new JFrame("Escriba - Declaração de Domínio de Imóvel da União");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setVgap(20);
		borderLayout.setHgap(20);
		frame.setResizable(false);
		frame.setBounds(100, 100, 840, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

		montarMenuPrincipal();
		JPanel panelPrincipal = montarPainelPrincipal();
		JPanel panelRodape = montarPainelRodape();

		JSplitPane divisorPainelPrincipalPainelRodape = montarDivisorPainelPrincipalPainelRodape();
		divisorPainelPrincipalPainelRodape.setRightComponent(panelRodape);
		divisorPainelPrincipalPainelRodape.setLeftComponent(panelPrincipal);

		frame.setVisible(true);
	}

	private void montarMenuPrincipal() {
	}

	private JPanel montarPainelPrincipal() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(10, 5, 10, 5));
		panelPrincipal.setBackground(Color.WHITE);
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[] { 0 };
		gbl_panelPrincipal.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelPrincipal.columnWeights = new double[] { 1.0 };
		gbl_panelPrincipal.rowWeights = new double[] { 0.0, 0.0, 1.0 };
		panelPrincipal.setLayout(gbl_panelPrincipal);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(
				new TitledBorder(null, "Requerimento SPUnet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panelPrincipal.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 200, 120, 160, 300 };
		gbl_panel_3.rowHeights = new int[] { 30, 30, 30 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0 };
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
		gbc_textNupSei.gridwidth = 2;
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

		comboObjetivoRequerimento = new JComboBox<OpcaoObjetivoDeclaracaoDominio>();
		comboObjetivoRequerimento.addActionListener(selecionouObjetivoRequerimento());
		GridBagConstraints gbc_comboObjetivoRequerimento = new GridBagConstraints();
		gbc_comboObjetivoRequerimento.gridwidth = 2;
		gbc_comboObjetivoRequerimento.fill = GridBagConstraints.BOTH;
		gbc_comboObjetivoRequerimento.insets = new Insets(0, 0, 5, 5);
		gbc_comboObjetivoRequerimento.gridx = 1;
		gbc_comboObjetivoRequerimento.gridy = 2;
		panel_3.add(comboObjetivoRequerimento, gbc_comboObjetivoRequerimento);
		popularOpcoesObjetivoRequerimento();
		
		textObjetivoOutro = new JTextField();
		textObjetivoOutro.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				atualizarDescricaoDoObjetivoDoRequerimento(e);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				atualizarDescricaoDoObjetivoDoRequerimento(e);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				atualizarDescricaoDoObjetivoDoRequerimento(e);				
			}

			private void atualizarDescricaoDoObjetivoDoRequerimento(DocumentEvent documentEvent) {
				String novoValor = ""; 
				try {
					if(documentEvent.getDocument().getLength()-1 > 0) {
						novoValor = documentEvent.getDocument().getText(0, documentEvent.getDocument().getLength()-1);						
					}
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				
				if(getRequerimento() != null 
						&& getRequerimento().getObjetivoRequerimento() != null) {
					getRequerimento().getObjetivoRequerimento().setDescricao(novoValor);						
				}
			}
		});
		textObjetivoOutro.setEnabled(false);
		GridBagConstraints gbc_textObjetivoOutro = new GridBagConstraints();
		gbc_textObjetivoOutro.insets = new Insets(0, 0, 5, 0);
		gbc_textObjetivoOutro.fill = GridBagConstraints.BOTH;
		gbc_textObjetivoOutro.gridx = 3;
		gbc_textObjetivoOutro.gridy = 2;
		panel_3.add(textObjetivoOutro, gbc_textObjetivoOutro);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"An\u00E1lise e Instru\u00E7\u00E3o do Processo no SEI", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 2;
		panelPrincipal.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 80, 400, 120 };
		gbl_panel_4.rowHeights = new int[] { 30 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panel_4.rowWeights = new double[] { 0.0 };
		panel_4.setLayout(gbl_panel_4);

		JLabel lblAnlise = new JLabel("Análise:", JLabel.RIGHT);
		GridBagConstraints gbc_lblAnlise = new GridBagConstraints();
		gbc_lblAnlise.anchor = GridBagConstraints.EAST;
		gbc_lblAnlise.insets = new Insets(0, 0, 0, 5);
		gbc_lblAnlise.gridx = 0;
		gbc_lblAnlise.gridy = 0;
		panel_4.add(lblAnlise, gbc_lblAnlise);

		comboParecerTecnico = new JComboBox<OpcaoParecerTecnicoDeclaracaoDominio>();
		popularOpcoesParecerTecnico((OpcaoObjetivoDeclaracaoDominio) comboObjetivoRequerimento.getSelectedItem());
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

	private void popularOpcoesObjetivoRequerimento() {
		comboObjetivoRequerimento.setModel(
				new DefaultComboBoxModel<OpcaoObjetivoDeclaracaoDominio>(OpcaoObjetivoDeclaracaoDominio.values()));
	}

	private ActionListener clicouObterRequerimento() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(EscribaApp.USAR_ROBO) {
					credencialAcessoSPUnetDialog = abrirCredencialAcessoDialog(credencialAcessoSPUnetDialog, escriba.getCredencialAcessoSPUnet(), new Callable<Void>() {
						public Void call() {
							obterDadosRequerimentoSPUnet();
							return null;													
						}

					});					
				} else {
					obterDadosRequerimentoSPUnet();
				}
			}
	
		};
	}
	
	private void obterDadosRequerimentoSPUnet() {
		try {
			validarInputObrigatorio(textAtendimento, "Informe o número do atendimento SPUnet");
			escriba.obterDadosRequerimentoSPUnet();
		} catch (Exception e) {
			exibirMensagem(e.getMessage());
		}
	}

	private ActionListener clicouInstruirProcesso() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credencialAcessoSEIDialog = abrirCredencialAcessoDialog(credencialAcessoSEIDialog, escriba.getCredencialAcessoSEI(), new Callable<Void>() {
					public Void call() throws Exception {
						
	
						try {
							validarInputObrigatorio(textNupSei, "NUP SEI não informado");
							if (comboParecerTecnico.getSelectedItem() == null) {
								exibirMensagem("Opção de parecer selecionada é inválida");
								comboParecerTecnico.requestFocus();
								return null;
							}
							if (getRequerimento() == null) {
								throw new Exception("Requerimento SPUnet não carregado");
							}
							if(((OpcaoParecerTecnicoDeclaracaoDominio) comboParecerTecnico.getSelectedItem()).equals(OpcaoParecerTecnicoDeclaracaoDominio.INDEFINIDO)) {
								throw new Exception("Selecione uma opção de análise");
							}
							getRequerimento().setProcedimentoFormatado(textNupSei.getText());
							getRequerimento().getObjetivoRequerimento().setDescricao(textObjetivoOutro.getText());
							escriba.instruirProcessoSEI();
						} catch (Exception e1) {
							exibirMensagem("Falha ao instruir processo: " + e1.getMessage());
						}
						
						
						return null;						
					}
				});
			}
		};
	}

	private ActionListener selecionouObjetivoRequerimento() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcaoObjetivoDeclaracaoDominio opcaoSelecionada = (OpcaoObjetivoDeclaracaoDominio) comboObjetivoRequerimento.getSelectedItem();
				getRequerimento().getObjetivoRequerimento().setId(opcaoSelecionada.getId());
				textObjetivoOutro.setEnabled(opcaoSelecionada.equals(OpcaoObjetivoDeclaracaoDominio.OUTRO));
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
		panelRodape.add(new JScrollPane(textAreaConsole), BorderLayout.CENTER);
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
		if (inputField.getText() == null || inputField.getText().isEmpty()) {
			inputField.requestFocus();
			throw new Exception(mensagem);
		}
	}

	public void exibirMensagem(String string) {
		JOptionPane.showMessageDialog(frame, string + "\n\r");
	}

	private CredencialAcessoDialog abrirCredencialAcessoDialog(CredencialAcessoDialog credencialAcessoDialog,
			CredencialAcesso credencial, 
			Callable<Void> callback) {
		if (credencialAcessoDialog == null) {
			credencialAcessoDialog = new CredencialAcessoDialog(this, "Credencial de Acesso", true, callback);
			credencialAcessoDialog.setCredencialAcesso(credencial);
		}
		credencialAcessoDialog.setVisible(true);
		return credencialAcessoDialog;
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

	public CredencialAcesso obterCredencialSPUnet() {
		
		return new CredencialAcesso(
				credencialAcessoSPUnetDialog.getTextLogin().getText(),
				new String(credencialAcessoSPUnetDialog.getTextSenha().getPassword()));
	}

	public CredencialAcesso obterCredencialSEI() {
		return new CredencialAcesso(
				credencialAcessoSEIDialog.getTextLogin().getText(), 
				new String(credencialAcessoSEIDialog.getTextSenha().getPassword()));
	}

	public String obterNumeroAtendimento() {
		return textAtendimento.getText();
	}

	public void aplicarDadosRequerimento(RequerimentoSPUnet requerimentoSPUnet) {
		// limparCamposRequerimento();
		if (requerimentoSPUnet != null) {
			Optional<OpcaoObjetivoDeclaracaoDominio> opcaoEnum = EnumSet.allOf(OpcaoObjetivoDeclaracaoDominio.class)
					.stream().filter(e -> e.getObjetivo().equals(requerimentoSPUnet.getObjetivoRequerimento().getObjetivo())).findFirst();
			OpcaoObjetivoDeclaracaoDominio objetivoDoRequerimento = opcaoEnum.get();
			
			popularOpcoesParecerTecnico(objetivoDoRequerimento);
			if (opcaoEnum.isPresent()) {
				comboObjetivoRequerimento.setSelectedItem(objetivoDoRequerimento);
				if (objetivoDoRequerimento.equals(OpcaoObjetivoDeclaracaoDominio.OUTRO)) {
					textObjetivoOutro.setEnabled(true);
					textObjetivoOutro.setText(requerimentoSPUnet.getObjetivoRequerimento().getDescricao());
				}
			} else {
				comboObjetivoRequerimento.setSelectedItem(OpcaoObjetivoDeclaracaoDominio.INDEFINIDO);
			}

			if (requerimentoSPUnet.getObjetivoRequerimento() != null) {
			}
			textNupSei.setText(requerimentoSPUnet.getProcedimentoFormatado());
		}
	}

	private void popularOpcoesParecerTecnico(OpcaoObjetivoDeclaracaoDominio opcaoEnum) {
		DefaultComboBoxModel<OpcaoParecerTecnicoDeclaracaoDominio> model = new DefaultComboBoxModel<OpcaoParecerTecnicoDeclaracaoDominio>(
				OpcaoParecerTecnicoDeclaracaoDominio.values());
		comboParecerTecnico.setModel(model);
	}

	public JComboBox<OpcaoParecerTecnicoDeclaracaoDominio> getComboParecerTecnico() {
		return comboParecerTecnico;
	}

	public JComboBox<OpcaoObjetivoDeclaracaoDominio> getComboObjetivoRequerimento() {
		return comboObjetivoRequerimento;
	}

	public void setComboObjetivoRequerimento(JComboBox<OpcaoObjetivoDeclaracaoDominio> comboObjetivoRequerimento) {
		this.comboObjetivoRequerimento = comboObjetivoRequerimento;
	}

	public void setComboParecerTecnico(JComboBox<OpcaoParecerTecnicoDeclaracaoDominio> comboParecerTecnico) {
		this.comboParecerTecnico = comboParecerTecnico;
	}

	public RequerimentoSPUnet getRequerimento() {
		return getEscriba().getRequerimento();
	}

}
