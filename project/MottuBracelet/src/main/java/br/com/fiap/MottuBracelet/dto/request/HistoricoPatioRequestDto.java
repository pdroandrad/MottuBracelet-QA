package br.com.fiap.MottuBracelet.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class HistoricoPatioRequestDto {
    @NotNull
    private Long motoId;

    @NotNull
    private Long patioId;

    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;

    // Construtor vazio
    public HistoricoPatioRequestDto() {}

    // Getters e Setters

    public Long getMotoId() {
        return motoId;
    }

    public void setMotoId(Long motoId) {
        this.motoId = motoId;
    }

    public Long getPatioId() {
        return patioId;
    }

    public void setPatioId(Long patioId) {
        this.patioId = patioId;
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
}