package br.com.zup.springdata.repository;

import br.com.zup.springdata.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
