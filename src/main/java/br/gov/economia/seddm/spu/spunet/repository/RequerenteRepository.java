package br.gov.economia.seddm.spu.spunet.repository;

import org.springframework.data.repository.CrudRepository;

import br.gov.economia.seddm.spu.spunet.model.Requerente;

public interface RequerenteRepository extends CrudRepository<Requerente, Integer>{
	
	// Optional<Requerente> findByRequerimento(Requerimento requerimento);	

}
