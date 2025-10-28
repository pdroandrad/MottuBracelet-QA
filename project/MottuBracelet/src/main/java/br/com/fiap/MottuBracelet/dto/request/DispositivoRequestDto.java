package br.com.fiap.MottuBracelet.dto.request;

import br.com.fiap.MottuBracelet.util.StatusDispositivo;
import jakarta.validation.constraints.NotNull;

public class DispositivoRequestDto {
    @NotNull
    private StatusDispositivo status;

    private Long motoId;  // Apenas o ID para relacionamento
    private Long patioId; // Apenas o ID para relacionamento

    // Construtor vazio
    public DispositivoRequestDto() {}

    // Getters e Setters

    public StatusDispositivo getStatus() {
        return status;
    }

    public void setStatus(StatusDispositivo status) {
        this.status = status;
    }

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
}