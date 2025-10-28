package br.com.fiap.MottuBracelet.service;

import br.com.fiap.MottuBracelet.model.Patio;
import br.com.fiap.MottuBracelet.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatioService {

    private final PatioRepository patioRepository;

    @Autowired
    public PatioService(PatioRepository patioRepository) {
        this.patioRepository = patioRepository;
    }

    // CREATE
    public Patio criarPatio(Patio patio) {
        return patioRepository.save(patio);
    }

    // READ - Todos
    public List<Patio> listarTodosPatios() {
        return patioRepository.findAll();
    }

    // READ - Por ID
    public Optional<Patio> buscarPatioPorId(Long id) {
        return patioRepository.findById(id);
    }

    // UPDATE
    public Patio atualizarPatio(Long id, Patio patioAtualizado) {
        return patioRepository.findById(id)
                .map(patio -> {
                    patio.setNome(patioAtualizado.getNome());
                    patio.setEndereco(patioAtualizado.getEndereco());
                    patio.setCapacidadeMaxima(patioAtualizado.getCapacidadeMaxima());
                    patio.setAdministradorResponsavel(patioAtualizado.getAdministradorResponsavel());
                    return patioRepository.save(patio);
                })
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado com o ID: " + id));
    }

    // DELETE
    public void deletarPatio(Long id) {
        if (!patioRepository.existsById(id)) {
            throw new RuntimeException("Pátio não encontrado com o ID: " + id);
        }
        patioRepository.deleteById(id);
    }
}