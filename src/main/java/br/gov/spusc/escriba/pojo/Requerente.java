package br.gov.spusc.escriba.pojo;

public class Requerente {
	
	private String nome;
	
	private String cpfCnpj;
	
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		retorno.append("Requerente:[");
		retorno.append("nome: " + nome);
		retorno.append("cpfCnpj: " + cpfCnpj);
		retorno.append("]");
		return retorno.toString();
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
