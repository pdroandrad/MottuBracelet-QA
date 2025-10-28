package br.com.fiap.MottuBracelet.controller;

import br.com.fiap.MottuBracelet.dto.response.DispositivoResponseDto;
import br.com.fiap.MottuBracelet.exception.ResourceNotFoundException;
import br.com.fiap.MottuBracelet.model.Dispositivo;
import br.com.fiap.MottuBracelet.service.DispositivoService;
import br.com.fiap.MottuBracelet.util.StatusDispositivo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {

    private final DispositivoService dispositivoService;

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DispositivoResponseDto criarDispositivo(@RequestBody Dispositivo dispositivo) {
        Dispositivo salvo = dispositivoService.criarDispositivo(dispositivo);
        return converterParaDto(salvo);
    }

    // READ - Listar todos
    @GetMapping
    public List<DispositivoResponseDto> listarTodosDispositivos() {
        return dispositivoService.listarTodosDispositivos()
                .stream()
                .map(this::converterParaDto)
                .toList();
    }

    // READ - Buscar por ID
    @GetMapping("/{id}")
    public DispositivoResponseDto buscarDispositivoPorId(@PathVariable Long id) {
        Dispositivo dispositivo = dispositivoService.buscarDispositivoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo", "id", id));
        return converterParaDto(dispositivo);
    }

    // READ - Buscar por status
    @GetMapping("/status/{status}")
    public List<Dispositivo> buscarDispositivosPorStatus(@PathVariable StatusDispositivo status) {
        return dispositivoService.buscarDispositivosPorStatus(status);
    }

    // UPDATE
    @PutMapping("/{id}")
    public DispositivoResponseDto atualizarDispositivo(@PathVariable Long id, @RequestBody Dispositivo dispositivoAtualizado) {
        Dispositivo atualizado = dispositivoService.atualizarDispositivo(id, dispositivoAtualizado);
        return converterParaDto(atualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarDispositivo(@PathVariable Long id) {
        dispositivoService.deletarDispositivo(id);
    }

    private DispositivoResponseDto converterParaDto(Dispositivo dispositivo) {
        DispositivoResponseDto dto = new DispositivoResponseDto();
        dto.setId(dispositivo.getId());
        dto.setStatus(dispositivo.getStatus());

        // Evita NullPointerException
        if (dispositivo.getMoto() != null) {
            dto.setMotoId(dispositivo.getMoto().getId());
        }

        if (dispositivo.getPatio() != null) {
            dto.setPatioId(dispositivo.getPatio().getId());
        }

        return dto;
    }
}