package br.com.fiap.MottuBracelet.model;

import br.com.fiap.MottuBracelet.util.StatusDispositivo;
import jakarta.persistence.*;

@Entity
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusDispositivo status;

    @OneToOne
    @JoinColumn(name = "moto_id")
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "patio_id")
    private Patio patio;

    // ✅ Campo adicionado para compatibilidade com os testes unitários
    private boolean ativo;

    public Dispositivo() {
        this.ativo = false; // por padrão, o dispositivo inicia desativado
    }

    public Dispositivo(Long id, StatusDispositivo status, Moto moto, Patio patio) {
        this.id = id;
        this.status = status;
        this.moto = moto;
        this.patio = patio;
        this.ativo = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusDispositivo getStatus() {
        return status;
    }

    public void setStatus(StatusDispositivo status) {
        this.status = status;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    // ✅ Métodos para ativar/desativar dispositivo (usados nos testes JUnit)
    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
