package br.gov.economia.seddm.spu.escriba.pojo;

import br.gov.economia.seddm.spu.escriba.MarcacaoDeTexto;

public class Requerente extends MarcacaoDeTexto {
	
	public Requerente() {
		// TODO Auto-generated constructor stub
	}
	
	public Requerente(br.gov.economia.seddm.spu.spunet.model.Requerente requerente) {
		this.nome = requerente.getNome();
		this.cpfCnpj = requerente.getCpfCnpf();
	}
	
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
