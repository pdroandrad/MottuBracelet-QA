package br.com.fiap.MottuBracelet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistoricoPatio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "moto_id", nullable = false)
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "patio_id", nullable = false)
    private Patio patio;

    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;

    public HistoricoPatio() {
    }

    public HistoricoPatio(Long id, Moto moto, Patio patio, LocalDateTime dataEntrada, LocalDateTime dataSaida) {
        this.id = id;
        this.moto = moto;
        this.patio = patio;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    // ✅ Métodos corrigidos para usar os campos reais da classe
    public void registrarEntrada(LocalDateTime entrada) {
        this.dataEntrada = entrada;
    }

    public void registrarSaida(LocalDateTime saida) {
        this.dataSaida = saida;
    }

    // ✅ Método adicional útil para teste
    public boolean isMotoNoPatio() {
        return dataEntrada != null && dataSaida == null;
    }
}
