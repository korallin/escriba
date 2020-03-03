package br.gov.economia.seddm.spu.spunet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.gov.economia.seddm.spu.spunet.model.Atendimento;
import br.gov.economia.seddm.spu.spunet.model.Requerimento;

public interface RequerimentoRepository extends CrudRepository<Requerimento, Integer> {
	
	public Optional<Requerimento> findByAtendimento(Atendimento atendimento);

}
