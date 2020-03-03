package br.gov.economia.seddm.spu.escriba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.gov.economia.seddm.spu.spunet.model.Requerimento;
import br.gov.economia.seddm.spu.spunet.service.RequerimentoService;

@RestController
@RequestMapping(path = "/spunet")
public class SpuNetController {
	
	@Autowired
	private RequerimentoService requerimentoServico;
	
	@GetMapping("/requerimento/{atendimento}/{ano}")
	public Requerimento obterRequerimento(@PathVariable String atendimento, @PathVariable Integer ano) {
		Requerimento requerimento = requerimentoServico.obterPorAtendimento(atendimento + "/" + ano);
		if(requerimento == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);			
		}
		return requerimento;
	}

}
