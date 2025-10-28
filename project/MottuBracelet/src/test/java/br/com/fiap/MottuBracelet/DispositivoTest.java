package br.com.fiap.MottuBracelet;

import br.com.fiap.MottuBracelet.model.Dispositivo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispositivoTest {

    private Dispositivo dispositivo;

    @BeforeEach
    void setUp() {
        dispositivo = new Dispositivo();
    }

    @Test
    void deveAtivarDispositivo() {
        // Garante que começa desativado
        dispositivo.desativar();
        // Executa a ação
        dispositivo.ativar();
        // Verifica o resultado
        assertTrue(dispositivo.isAtivo(), "O dispositivo deve estar ativo após ativação");
    }

    @Test
    void deveDesativarDispositivo() {
        // Garante que começa ativado
        dispositivo.ativar();
        // Executa a ação
        dispositivo.desativar();
        // Verifica o resultado
        assertFalse(dispositivo.isAtivo(), "O dispositivo deve estar inativo após desativação");
    }
}
