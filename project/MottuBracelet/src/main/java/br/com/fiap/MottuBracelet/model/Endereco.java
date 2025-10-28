package br.com.fiap.MottuBracelet.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String cep;

    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String pais;

    public Endereco() {
    }

    public Endereco(String logradouro, String numero, String cep, String complemento, String cidade, String pais) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // ✅ Método corrigido para usar os campos reais e gerar o endereço completo
    public String formatarEndereco() {
        StringBuilder enderecoFormatado = new StringBuilder();
        enderecoFormatado.append(logradouro)
                .append(", ")
                .append(numero)
                .append(" - ")
                .append(cidade)
                .append(" / ")
                .append(pais);

        if (complemento != null && !complemento.isBlank()) {
            enderecoFormatado.append(" (").append(complemento).append(")");
        }

        return enderecoFormatado.toString();
    }
}
