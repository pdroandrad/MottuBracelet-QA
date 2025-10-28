package br.com.fiap.MottuBracelet.dto.request;

import jakarta.validation.constraints.NotBlank;

public class MotoRequestDto {
    @NotBlank
    private String imei;

    @NotBlank
    private String chassi;

    @NotBlank
    private String placa;

    private Long patioId; // Opcional (pátio atual)

    // Construtor vazio
    public MotoRequestDto() {}

    // Getters e Setters

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getPatioId() {
        return patioId;
    }

    public void setPatioId(Long patioId) {
        this.patioId = patioId;
    }
}