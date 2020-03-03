package br.gov.economia.seddm.spu.spunet.repository;

import org.springframework.data.repository.CrudRepository;

import br.gov.economia.seddm.spu.spunet.model.Atendimento;

public interface AtendimentoRepository extends CrudRepository<Atendimento, Integer> {

	public Iterable<Atendimento> findByNuAtendimento(String atendimento);

}
