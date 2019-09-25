package br.gov.spusc.escriba;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import br.gov.spusc.escriba.pojo.Imovel;
import br.gov.spusc.escriba.pojo.Requerente;
import br.gov.spusc.escriba.pojo.RequerimentoSPUnet;

public class Laboratorio {

	public static void main(String[] args) {
		RequerimentoSPUnet requerimento = new RequerimentoSPUnet();
		requerimento.setNomeRequerente("NOME DO REQUERENTE");
		requerimento.setCodigoIdentificacao("012345");
		requerimento.setNumeroAtendimento("SC0123458/2019");
		requerimento.setProcedimentoFormatado("10154.112761/2019-21");
		
		Requerente requerente = new Requerente();
		requerente.setNome(requerimento.getNomeRequerente());
		requerente.setCpfCnpj("XXX.XXX.XXX-XX");
		requerimento.setRequerente(requerente);
		
		Imovel imovel = new Imovel();
		requerimento.setImovel(imovel);
		requerimento.getImovel().setCep("88058-000");
		requerimento.getImovel().setTipoLogradouro("RUA");
		requerimento.getImovel().setLogradouro("DAS COUVES");
		requerimento.getImovel().setNumero("123");
		requerimento.getImovel().setComplemento("CASA");
		requerimento.getImovel().setMunicipio("SANTA ILUSAO");
		requerimento.getImovel().setBairro("FELICIDADE");
		requerimento.getImovel().setUf("SC");
		requerimento.getImovel().setNuMatriculaTransicao("N/I");
		requerimento.getImovel().setAreaTerreno(432.12);
		
		Map<String, List<String>> mapa = requerimento.preencherMapaDeMarcacoesValores();
		
		List<String> chaves = new ArrayList<String>(mapa.keySet());
		chaves.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
			
		});
		
		for(String chave : chaves) {
			System.out.println(chave + ": " + mapa.get(chave));
		}
	}

}
