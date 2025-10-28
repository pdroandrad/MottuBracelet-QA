package br.com.fiap.MottuBracelet.controller;

import br.com.fiap.MottuBracelet.dto.request.HistoricoPatioRequestDto;
import br.com.fiap.MottuBracelet.dto.response.HistoricoPatioResponseDto;
import br.com.fiap.MottuBracelet.model.HistoricoPatio;
import br.com.fiap.MottuBracelet.model.Moto;
import br.com.fiap.MottuBracelet.model.Patio;
import br.com.fiap.MottuBracelet.service.HistoricoPatioService;
import br.com.fiap.MottuBracelet.service.MotoService;
import br.com.fiap.MottuBracelet.service.PatioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historicos")
public class HistoricoPatioController {

    private final HistoricoPatioService historicoService;
    private final MotoService motoService;
    private final PatioService patioService;

    public HistoricoPatioController(HistoricoPatioService historicoService, MotoService motoService, PatioService patioService) {
        this.historicoService = historicoService;
        this.motoService = motoService;
        this.patioService = patioService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoPatioResponseDto criar(@RequestBody HistoricoPatioRequestDto dto) {
        Moto moto = motoService.buscarMotoPorId(dto.getMotoId())
                .orElseThrow(() -> new RuntimeException("Moto não encontrada com ID: " + dto.getMotoId()));
        Patio patio = patioService.buscarPatioPorId(dto.getPatioId())
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado com ID: " + dto.getPatioId()));

        HistoricoPatio historico = new HistoricoPatio();
        historico.setMoto(moto);
        historico.setPatio(patio);
        historico.setDataEntrada(dto.getDataEntrada());
        historico.setDataSaida(dto.getDataSaida());

        HistoricoPatio salvo = historicoService.salvar(historico);

        return toDto(salvo);
    }

    // LISTAR TODOS
    @GetMapping
    public List<HistoricoPatioResponseDto> listarTodos() {
        return historicoService.listarTodos()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // CONVERSOR
    private HistoricoPatioResponseDto toDto(HistoricoPatio historico) {
        HistoricoPatioResponseDto dto = new HistoricoPatioResponseDto();
        dto.setId(historico.getId());
        dto.setMotoId(historico.getMoto().getId());
        dto.setPatioId(historico.getPatio().getId());
        dto.setDataEntrada(historico.getDataEntrada());
        dto.setDataSaida(historico.getDataSaida());
        return dto;
    }
}
