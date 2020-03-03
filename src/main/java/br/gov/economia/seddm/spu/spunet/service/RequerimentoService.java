package br.gov.economia.seddm.spu.spunet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.economia.seddm.spu.spunet.model.Atendimento;
import br.gov.economia.seddm.spu.spunet.model.Requerimento;
import br.gov.economia.seddm.spu.spunet.repository.AtendimentoRepository;
import br.gov.economia.seddm.spu.spunet.repository.RequerimentoRepository;

@Service
public class RequerimentoService {

	@Autowired
	private AtendimentoRepository atendimentoRepositorio;

	@Autowired
	private RequerimentoRepository requerimentoRepositorio;

	public Requerimento obterPorAtendimento(String atendimento) {
		Iterable<Atendimento> atendimentos = atendimentoRepositorio.findByNuAtendimento(atendimento);
		if (atendimentos.iterator().hasNext()) {
			Atendimento atendimentoSpuNet = atendimentos.iterator().next();
			Optional<Requerimento> optRequerimento = requerimentoRepositorio.findByAtendimento(atendimentoSpuNet);
			if (optRequerimento.isPresent()) {
				return optRequerimento.get();
			}
		}
		return null;
	}

}
