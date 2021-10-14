package br.com.zup.springdata.service;

import br.com.zup.springdata.model.Cargo;
import br.com.zup.springdata.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Transactional
    private void salvar(Cargo cargo) {
        cargoRepository.save(cargo);
    }

    @Transactional
    private void atualizar(Long cargoId, String newDesc) {
        Cargo cargo = cargoRepository.getById(cargoId);
        cargo.setDescricao(newDesc);
    }

    @Transactional
    private void delete(Long cargoId) {
        cargoRepository.deleteById(cargoId);
    }

    private Cargo getOneCargo(Long cargoId) {
        return cargoRepository.getById(cargoId);
    }

}
