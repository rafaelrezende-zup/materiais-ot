package br.com.zup.springdata.service;

import br.com.zup.springdata.model.Cargo;
import br.com.zup.springdata.model.Funcionario;
import br.com.zup.springdata.repository.CargoRepository;
import br.com.zup.springdata.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    private void salvar(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    @Transactional
    private void atualizar(Long funcId, Funcionario funcionario) {
        Funcionario func = funcionarioRepository.getById(funcId);
        func.setNome(funcionario.getNome());
    }

    @Transactional
    private void delete(Long funcId) {
        funcionarioRepository.deleteById(funcId);
    }

    private Funcionario getOneCargo(Long funcId) {
        return funcionarioRepository.getById(funcId);
    }

}
