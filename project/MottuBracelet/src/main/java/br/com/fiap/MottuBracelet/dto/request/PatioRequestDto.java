package br.com.fiap.MottuBracelet.dto.request;

import br.com.fiap.MottuBracelet.dto.EnderecoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PatioRequestDto {
    @NotBlank
    private String nome;

    @NotNull
    private EnderecoDto endereco;

    @NotNull
    private Integer capacidadeMaxima;

    @NotBlank
    private String administradorResponsavel;

    // Construtor vazio
    public PatioRequestDto() {}

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }

    public Integer getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Integer capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getAdministradorResponsavel() {
        return administradorResponsavel;
    }

    public void setAdministradorResponsavel(String administradorResponsavel) {
        this.administradorResponsavel = administradorResponsavel;
    }
}