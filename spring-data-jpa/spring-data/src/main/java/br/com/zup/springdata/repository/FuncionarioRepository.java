package br.com.zup.springdata.repository;

import br.com.zup.springdata.model.Cargo;
import br.com.zup.springdata.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {

}
