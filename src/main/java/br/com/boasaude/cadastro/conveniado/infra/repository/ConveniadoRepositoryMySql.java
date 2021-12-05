package br.com.boasaude.cadastro.conveniado.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boasaude.cadastro.conveniado.infra.repository.orm.ConveniadoJpa;

@Repository
public interface ConveniadoRepositoryMySql extends JpaRepository<ConveniadoJpa, Long> {

	ConveniadoJpa findByCpf(String cpf);


}
