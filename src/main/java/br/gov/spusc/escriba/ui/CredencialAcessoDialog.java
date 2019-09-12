package br.gov.spusc.escriba.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.gov.spusc.escriba.CredencialAcesso;

public class CredencialAcessoDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CredencialAcesso credencialAcesso;
	
	private JTextField textLogin;
	private JTextField textSenha;

	private JanelaPrincipal janelaPrincipal;

	public CredencialAcessoDialog(JanelaPrincipal janelaPrincipal, String frameTitle, boolean modal) {
		super(janelaPrincipal.getFrame(), frameTitle, modal);
		this.janelaPrincipal = janelaPrincipal;
		this.incialize();
	}

	private void incialize() {
		GridLayout gridLayout = new GridLayout(3, 2);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		this.getContentPane().setLayout(gridLayout);
		
		this.getContentPane().add(new JLabel("Usuário: ", JLabel.RIGHT));
		textLogin = new JTextField();
		this.getContentPane().add(textLogin);
		
		this.getContentPane().add(new JLabel("Senha: ", JLabel.RIGHT));
		textSenha = new JPasswordField();
		this.getContentPane().add(textSenha);
		
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		this.getContentPane().add(botaoCancelar);		
		
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				credencialAcesso.setLogin(textLogin.getText());
				credencialAcesso.setSenha(textSenha.getText());
				janelaPrincipal.getEscriba().salvarConfiguracao(credencialAcesso);				
				setVisible(false);
			}
		});
		this.getContentPane().add(botaoSalvar);
		
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

}
