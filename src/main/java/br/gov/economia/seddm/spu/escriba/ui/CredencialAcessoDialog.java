package br.gov.economia.seddm.spu.escriba.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.gov.economia.seddm.spu.escriba.CredencialAcesso;

public class CredencialAcessoDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CredencialAcesso credencialAcesso;
	
	private JTextField textLogin;
	private JPasswordField textSenha;

	private JanelaPrincipal janelaPrincipal;
	private JPanel panel;
	private JCheckBox salvarCredencial;
	private Callable<Void> callbackCredencialInformada;

	public CredencialAcessoDialog(JanelaPrincipal janelaPrincipal, String frameTitle, boolean modal, Callable<Void> callbackCredencialInformada) {
		super(janelaPrincipal.getFrame(), frameTitle, modal);
		this.callbackCredencialInformada = callbackCredencialInformada;
		this.janelaPrincipal = janelaPrincipal;
		
		this.incialize();
	}

	private void incialize() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {60, 100, 100};
		gbl_panel.rowHeights = new int[] {23, 0, 0, 0, 23};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label = new JLabel("Usuário: ", JLabel.RIGHT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		textLogin = new JTextField();
		GridBagConstraints gbc_textLogin = new GridBagConstraints();
		gbc_textLogin.gridwidth = 2;
		gbc_textLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLogin.insets = new Insets(0, 0, 5, 5);
		gbc_textLogin.gridx = 1;
		gbc_textLogin.gridy = 0;
		panel.add(textLogin, gbc_textLogin);
		
		JLabel label_1 = new JLabel("Senha: ", JLabel.RIGHT);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panel.add(label_1, gbc_label_1);
		textSenha = new JPasswordField();
		GridBagConstraints gbc_textSenha = new GridBagConstraints();
		gbc_textSenha.gridwidth = 2;
		gbc_textSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSenha.insets = new Insets(0, 0, 5, 5);
		gbc_textSenha.gridx = 1;
		gbc_textSenha.gridy = 1;
		panel.add(textSenha, gbc_textSenha);
		
		salvarCredencial = new JCheckBox("salvar");
		GridBagConstraints gbc_salvarCredencial = new GridBagConstraints();
		gbc_salvarCredencial.gridwidth = 2;
		gbc_salvarCredencial.anchor = GridBagConstraints.WEST;
		gbc_salvarCredencial.insets = new Insets(0, 0, 5, 5);
		gbc_salvarCredencial.gridx = 1;
		gbc_salvarCredencial.gridy = 2;
		panel.add(salvarCredencial, gbc_salvarCredencial);
		
		JButton botaoCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_botaoCancelar = new GridBagConstraints();
		gbc_botaoCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botaoCancelar.anchor = GridBagConstraints.NORTH;
		gbc_botaoCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_botaoCancelar.gridx = 1;
		gbc_botaoCancelar.gridy = 3;
		panel.add(botaoCancelar, gbc_botaoCancelar);
		
		JButton botaoSalvar = new JButton("Continuar");
		GridBagConstraints gbc_botaoSalvar = new GridBagConstraints();
		gbc_botaoSalvar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botaoSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_botaoSalvar.anchor = GridBagConstraints.NORTH;
		gbc_botaoSalvar.gridx = 2;
		gbc_botaoSalvar.gridy = 3;
		panel.add(botaoSalvar, gbc_botaoSalvar);
		botaoSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					janelaPrincipal.validarInputObrigatorio(textLogin, "Informe o login");
					janelaPrincipal.validarInputObrigatorio(textSenha, "Informe a senha");
					
					credencialAcesso.setLogin(textLogin.getText());
					credencialAcesso.setSenha(new String(textSenha.getPassword()));
					
					if(salvarCredencial.isSelected()) {
						janelaPrincipal.getEscriba().salvarConfiguracao(credencialAcesso);									
					}
					setVisible(false);
					callbackCredencialInformada.call();
				} catch (Exception e1) {
					janelaPrincipal.exibirMensagem(e1.getMessage());
				}
			}
		});
		botaoCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		this.setBounds(new Rectangle(400, 180));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		this.pack();
		this.setVisible(false);
	}

	
	public void setCredencialAcesso(CredencialAcesso credencialAcesso) {
		this.credencialAcesso = credencialAcesso;
		textLogin.setText(this.credencialAcesso.getLogin());
		textSenha.setText(this.credencialAcesso.getSenha());
	}

	public JTextField getTextLogin() {
		return textLogin;
	}

	public JPasswordField getTextSenha() {
		return textSenha;
	}

}
