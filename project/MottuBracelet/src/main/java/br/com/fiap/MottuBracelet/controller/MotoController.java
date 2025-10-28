package br.com.fiap.MottuBracelet.controller;

import br.com.fiap.MottuBracelet.dto.response.MotoResponseDto;
import br.com.fiap.MottuBracelet.exception.ResourceNotFoundException;
import br.com.fiap.MottuBracelet.model.Moto;
import br.com.fiap.MottuBracelet.service.MotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MotoResponseDto criarMoto(@RequestBody Moto moto) {
        Moto salva = motoService.criarMoto(moto);
        return converterParaDto(salva);
    }

    // READ - Listar todas
    @GetMapping
    public List<MotoResponseDto> listarTodasMotos() {
        return motoService.listarTodasMotos()
                .stream()
                .map(this::converterParaDto)
                .toList();
    }

    // READ - Buscar por ID
    @GetMapping("/{id}")
    public MotoResponseDto buscarMotoPorId(@PathVariable Long id) {
        Moto moto = motoService.buscarMotoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto", "id", id));
        return converterParaDto(moto);
    }

    // READ - Buscar por IMEI
    @GetMapping("/imei/{imei}")
    public Moto buscarMotoPorImei(@PathVariable String imei) {
        return motoService.buscarMotoPorImei(imei)
                .orElseThrow(() -> new ResourceNotFoundException("Moto", "IMEI", imei));
    }

    // READ - Buscar por Placa
    @GetMapping("/placa/{placa}")
    public Moto buscarMotoPorPlaca(@PathVariable String placa) {
        return motoService.buscarMotoPorPlaca(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Moto", "placa", placa));
    }

    // READ - Buscar por Chassi
    @GetMapping("/chassi/{chassi}")
    public Moto buscarMotoPorChassi(@PathVariable String chassi) {
        return motoService.buscarMotoPorChassi(chassi)
                .orElseThrow(() -> new ResourceNotFoundException("Moto", "chassi", chassi));
    }

    // UPDATE
    @PutMapping("/{id}")
    public MotoResponseDto atualizarMoto(@PathVariable Long id, @RequestBody Moto motoAtualizada) {
        Moto atualizada = motoService.atualizarMoto(id, motoAtualizada);
        return converterParaDto(atualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMoto(@PathVariable Long id) {
        motoService.deletarMoto(id);
    }

    private MotoResponseDto converterParaDto(Moto moto) {
        MotoResponseDto dto = new MotoResponseDto();
        dto.setId(moto.getId());
        dto.setImei(moto.getImei());
        dto.setChassi(moto.getChassi());
        dto.setPlaca(moto.getPlaca());

        if (moto.getPatio() != null) {
            dto.setPatioId(moto.getPatio().getId());
        }

        if (moto.getDispositivo() != null) {
            dto.setDispositivoId(moto.getDispositivo().getId());
        }

        return dto;
    }
}