package br.com.fiap.MottuBracelet.service;

import br.com.fiap.MottuBracelet.model.Dispositivo;
import br.com.fiap.MottuBracelet.model.Moto;
import br.com.fiap.MottuBracelet.model.Patio;
import br.com.fiap.MottuBracelet.repository.DispositivoRepository;
import br.com.fiap.MottuBracelet.repository.MotoRepository;
import br.com.fiap.MottuBracelet.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    @Autowired
    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private PatioRepository patioRepository;

    // CREATE
    public Moto criarMoto(Moto moto) {
        // Busca o dispositivo e o pátio do banco para garantir que estão gerenciados
        Dispositivo dispositivo = dispositivoRepository.findById(moto.getDispositivo().getId())
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));

        Patio patio = patioRepository.findById(moto.getPatio().getId())
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));

        moto.setDispositivo(dispositivo);
        moto.setPatio(patio);

        dispositivo.setMoto(moto);

        return motoRepository.save(moto);
    }

    // READ - Todos
    public List<Moto> listarTodasMotos() {
        return motoRepository.findAll();
    }

    // READ - Por ID
    public Optional<Moto> buscarMotoPorId(Long id) {
        return motoRepository.findById(id);
    }

    // READ - Por IMEI
    public Optional<Moto> buscarMotoPorImei(String imei) {
        return motoRepository.findByImei(imei);
    }

    // READ - Por Placa
    public Optional<Moto> buscarMotoPorPlaca(String placa) {
        return motoRepository.findByPlaca(placa);
    }

    // READ - Por Chassi
    public Optional<Moto> buscarMotoPorChassi(String chassi) {
        return motoRepository.findByChassi(chassi);
    }

    // UPDATE
    public Moto atualizarMoto(Long id, Moto motoAtualizada) {
        return motoRepository.findById(id)
                .map(moto -> {
                    // Não atualiza o IMEI (único e imutável)
                    moto.setPatio(motoAtualizada.getPatio());
                    moto.setDispositivo(motoAtualizada.getDispositivo());
                    return motoRepository.save(moto);
                })
                .orElseThrow(() -> new RuntimeException("Moto não encontrada com o ID: " + id));
    }

    // DELETE
    public void deletarMoto(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new RuntimeException("Moto não encontrada com o ID: " + id);
        }
        motoRepository.deleteById(id);
    }
}