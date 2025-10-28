package br.com.fiap.MottuBracelet.service;

import br.com.fiap.MottuBracelet.model.HistoricoPatio;
import br.com.fiap.MottuBracelet.repository.HistoricoPatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoPatioService {

    private final HistoricoPatioRepository historicoRepository;

    @Autowired
    public HistoricoPatioService(HistoricoPatioRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    // Salvar um novo histórico
    public HistoricoPatio salvar(HistoricoPatio historico) {
        return historicoRepository.save(historico);
    }

    // Listar todos
    public List<HistoricoPatio> listarTodos() {
        return historicoRepository.findAll();
    }
}