package br.gov.spusc.escriba;

import br.gov.spusc.escriba.exception.CredencialAcessoInvalidaException;

public class CredencialAcesso {
	
	private String login, senha, propriedadeLogin, propriedadeSenha;

	public CredencialAcesso() {
	}
	
	public CredencialAcesso(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("CredencialAcesso[login:");
		toString.append(login);
		toString.append(" | senha: ");
		toString.append(senha);
		toString.append("]");
		return toString.toString();
	}

	public void validar() throws CredencialAcessoInvalidaException {
		if(login == null || login.isBlank()) {
			throw new CredencialAcessoInvalidaException("Credencial de acesso inválida: login não informado");
		}
		if(senha == null || senha.isBlank()) {
			throw new CredencialAcessoInvalidaException("Credencial de acesso inválida: senha não informada");
		}
		
	}

	public String getPropriedadeLogin() {
		return propriedadeLogin;
	}

	public String getPropriedadeSenha() {
		return propriedadeSenha;
	}

	public void setPropriedadeLogin(String propriedadeLogin) {
		this.propriedadeLogin = propriedadeLogin;
	}

	public void setPropriedadeSenha(String propriedadeSenha) {
		this.propriedadeSenha = propriedadeSenha;
	}

}
