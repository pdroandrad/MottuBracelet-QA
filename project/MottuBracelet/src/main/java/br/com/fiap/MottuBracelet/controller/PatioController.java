package br.com.fiap.MottuBracelet.controller;

import br.com.fiap.MottuBracelet.dto.EnderecoDto;
import br.com.fiap.MottuBracelet.dto.response.PatioResponseDto;
import br.com.fiap.MottuBracelet.exception.ResourceNotFoundException;
import br.com.fiap.MottuBracelet.model.Patio;
import br.com.fiap.MottuBracelet.service.PatioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patios")
public class PatioController {

    private final PatioService patioService;

    public PatioController(PatioService patioService) {
        this.patioService = patioService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatioResponseDto criarPatio(@RequestBody Patio patio) {
        Patio salvo = patioService.criarPatio(patio);
        return converterParaDto(salvo);
    }

    // READ - Listar todos
    @GetMapping
    public List<PatioResponseDto> listarTodosPatios() {
        return patioService.listarTodosPatios().stream().map(patio -> {
            PatioResponseDto dto = new PatioResponseDto();
            dto.setId(patio.getId());
            dto.setNome(patio.getNome());
            dto.setAdministradorResponsavel(patio.getAdministradorResponsavel());
            dto.setCapacidadeMaxima(patio.getCapacidadeMaxima());

            if (patio.getEndereco() != null) {
                EnderecoDto enderecoDto = new EnderecoDto();
                enderecoDto.setLogradouro(patio.getEndereco().getLogradouro());
                enderecoDto.setNumero(patio.getEndereco().getNumero());
                enderecoDto.setCep(patio.getEndereco().getCep());
                enderecoDto.setComplemento(patio.getEndereco().getComplemento());
                enderecoDto.setCidade(patio.getEndereco().getCidade());
                enderecoDto.setPais(patio.getEndereco().getPais());
                dto.setEndereco(enderecoDto);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PatioResponseDto buscarPatioPorId(@PathVariable Long id) {
        Patio patio = patioService.buscarPatioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pátio", "id", id));

        PatioResponseDto dto = new PatioResponseDto();
        dto.setId(patio.getId());
        dto.setNome(patio.getNome());
        dto.setAdministradorResponsavel(patio.getAdministradorResponsavel());
        dto.setCapacidadeMaxima(patio.getCapacidadeMaxima());

        if (patio.getEndereco() != null) {
            EnderecoDto enderecoDto = new EnderecoDto();
            enderecoDto.setLogradouro(patio.getEndereco().getLogradouro());
            enderecoDto.setNumero(patio.getEndereco().getNumero());
            enderecoDto.setCep(patio.getEndereco().getCep());
            enderecoDto.setComplemento(patio.getEndereco().getComplemento());
            enderecoDto.setCidade(patio.getEndereco().getCidade());
            enderecoDto.setPais(patio.getEndereco().getPais());
            dto.setEndereco(enderecoDto);
        }

        return dto;
    }

    // UPDATE
    @PutMapping("/{id}")
    public PatioResponseDto atualizarPatio(@PathVariable Long id, @RequestBody Patio patioAtualizado) {
        Patio atualizado = patioService.atualizarPatio(id, patioAtualizado);
        return converterParaDto(atualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPatio(@PathVariable Long id) {
        patioService.deletarPatio(id);
    }

    private PatioResponseDto converterParaDto(Patio patio) {
        PatioResponseDto dto = new PatioResponseDto();
        dto.setId(patio.getId());
        dto.setNome(patio.getNome());
        dto.setAdministradorResponsavel(patio.getAdministradorResponsavel());
        dto.setCapacidadeMaxima(patio.getCapacidadeMaxima());

        if (patio.getEndereco() != null) {
            EnderecoDto enderecoDto = new EnderecoDto();
            enderecoDto.setLogradouro(patio.getEndereco().getLogradouro());
            enderecoDto.setNumero(patio.getEndereco().getNumero());
            enderecoDto.setCep(patio.getEndereco().getCep());
            enderecoDto.setComplemento(patio.getEndereco().getComplemento());
            enderecoDto.setCidade(patio.getEndereco().getCidade());
            enderecoDto.setPais(patio.getEndereco().getPais());
            dto.setEndereco(enderecoDto);
        }

        return dto;
    }
}