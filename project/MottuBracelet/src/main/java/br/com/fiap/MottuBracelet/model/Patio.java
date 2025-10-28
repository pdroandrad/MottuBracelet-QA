package br.com.fiap.MottuBracelet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Embedded
    private Endereco endereco;

    @NotNull
    private Integer capacidadeMaxima;

    @NotBlank
    private String administradorResponsavel;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Moto> motosAtuais;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dispositivo> dispositivos;

    public Patio() {
    }

    public Patio(Long id, String nome, Endereco endereco, Integer capacidadeMaxima, String administradorResponsavel, List<Moto> motosAtuais, List<Dispositivo> dispositivos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.capacidadeMaxima = capacidadeMaxima;
        this.administradorResponsavel = administradorResponsavel;
        this.motosAtuais = motosAtuais;
    }

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
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

    public List<Moto> getMotosAtuais() {
        return motosAtuais;
    }

    public void setMotosAtuais(List<Moto> motosAtuais) {
        this.motosAtuais = motosAtuais;
    }

}
