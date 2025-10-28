package br.com.fiap.MottuBracelet;

import br.com.fiap.MottuBracelet.model.HistoricoPatio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoPatioTest {

    private HistoricoPatio historico;

    @BeforeEach
    void setUp() {
        historico = new HistoricoPatio();
    }

    @Test
    void deveRegistrarEntrada() {
        LocalDateTime agora = LocalDateTime.now();
        historico.registrarEntrada(agora);
        assertNotNull(historico.getDataEntrada(), "A data de entrada deve ser registrada");
    }

    @Test
    void deveRegistrarSaida() {
        LocalDateTime agora = LocalDateTime.now();
        historico.registrarSaida(agora);
        assertNotNull(historico.getDataSaida(), "A data de saída deve ser registrada");
    }

    @Test
    void deveVerificarMotoNoPatio() {
        historico.registrarEntrada(LocalDateTime.now());
        assertTrue(historico.isMotoNoPatio(), "A moto deve estar no pátio se ainda não houver data de saída");
    }
}
