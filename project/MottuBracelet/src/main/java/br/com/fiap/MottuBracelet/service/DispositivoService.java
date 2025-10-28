package br.com.fiap.MottuBracelet.service;

import br.com.fiap.MottuBracelet.model.Dispositivo;
import br.com.fiap.MottuBracelet.repository.DispositivoRepository;
import br.com.fiap.MottuBracelet.util.StatusDispositivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;

    @Autowired
    public DispositivoService(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    // CREATE
    public Dispositivo criarDispositivo(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    // READ - Todos
    public List<Dispositivo> listarTodosDispositivos() {
        return dispositivoRepository.findAll();
    }

    // READ - Por ID
    public Optional<Dispositivo> buscarDispositivoPorId(Long id) {
        return dispositivoRepository.findById(id);
    }

    // READ - Por Status
    public List<Dispositivo> buscarDispositivosPorStatus(StatusDispositivo status) {
        return dispositivoRepository.findByStatus(status);
    }

    // UPDATE
    public Dispositivo atualizarDispositivo(Long id, Dispositivo dispositivoAtualizado) {
        return dispositivoRepository.findById(id)
                .map(dispositivo -> {
                    dispositivo.setStatus(dispositivoAtualizado.getStatus());
                    dispositivo.setMoto(dispositivoAtualizado.getMoto());
                    dispositivo.setPatio(dispositivoAtualizado.getPatio());
                    return dispositivoRepository.save(dispositivo);
                })
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado com o ID: " + id));
    }

    // DELETE
    public void deletarDispositivo(Long id) {
        if (!dispositivoRepository.existsById(id)) {
            throw new RuntimeException("Dispositivo não encontrado com o ID: " + id);
        }
        dispositivoRepository.deleteById(id);
    }
}