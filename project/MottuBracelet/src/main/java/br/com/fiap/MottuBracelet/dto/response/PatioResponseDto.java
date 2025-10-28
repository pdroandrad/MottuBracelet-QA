package br.com.fiap.MottuBracelet.dto.response;

import br.com.fiap.MottuBracelet.dto.EnderecoDto;

public class PatioResponseDto {
    private Long id;
    private String nome;
    private EnderecoDto endereco;
    private Integer capacidadeMaxima;
    private String administradorResponsavel;

    // Construtor vazio
    public PatioResponseDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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