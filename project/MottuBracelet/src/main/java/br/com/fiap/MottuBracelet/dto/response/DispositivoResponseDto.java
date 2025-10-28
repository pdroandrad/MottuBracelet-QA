package br.com.fiap.MottuBracelet.dto.response;

import br.com.fiap.MottuBracelet.util.StatusDispositivo;

public class DispositivoResponseDto {
    private Long id;
    private StatusDispositivo status;
    private Long motoId;
    private Long patioId;

    // Construtor vazio
    public DispositivoResponseDto() {}

    // Getters e Setters

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