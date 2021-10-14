package br.com.zup.springdata.repository;

import br.com.zup.springdata.model.Cargo;
import br.com.zup.springdata.model.UnidadeTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeTrabalhoRepository extends JpaRepository<UnidadeTrabalho, Long> {
}
