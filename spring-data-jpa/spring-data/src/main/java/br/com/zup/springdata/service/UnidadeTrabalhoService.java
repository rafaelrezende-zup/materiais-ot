package br.com.zup.springdata.service;

import br.com.zup.springdata.model.UnidadeTrabalho;
import br.com.zup.springdata.repository.UnidadeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UnidadeTrabalhoService {

    @Autowired
    private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    @Transactional
    private void salvar(UnidadeTrabalho unidadeTrabalho) {
        unidadeTrabalhoRepository.save(unidadeTrabalho);
    }

    @Transactional
    private void atualizar(Long unidadeTrabalhoId, UnidadeTrabalho unidadeTrabalho) {
        UnidadeTrabalho UnidadeTrabalho = unidadeTrabalhoRepository.getById(unidadeTrabalhoId);
        UnidadeTrabalho.setDescricao(unidadeTrabalho.getDescricao());
    }

    @Transactional
    private void delete(Long unidadeTrabalhoId) {
        unidadeTrabalhoRepository.deleteById(unidadeTrabalhoId);
    }

    private UnidadeTrabalho getOneUnidadeTrabalho(Long unidadeTrabalhoId) {
        return unidadeTrabalhoRepository.getById(unidadeTrabalhoId);
    }

}
