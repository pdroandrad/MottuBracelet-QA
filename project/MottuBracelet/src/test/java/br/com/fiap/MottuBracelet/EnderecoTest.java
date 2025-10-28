package br.com.fiap.MottuBracelet;

import br.com.fiap.MottuBracelet.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    private Endereco endereco;

    @BeforeEach
    void setUp() {
        endereco = new Endereco();
        endereco.setLogradouro("Rua das Rosas");
        endereco.setNumero("123");
        endereco.setCidade("São Paulo");
        endereco.setPais("Brasil"); // substitui o antigo 'estado'
    }

    @Test
    void deveRetornarEnderecoCompletoFormatado() {
        String esperado = "Rua das Rosas, 123 - São Paulo / Brasil";
        String resultado = endereco.formatarEndereco();
        assertEquals(esperado, resultado, "O endereço deve ser formatado corretamente");
    }
}
