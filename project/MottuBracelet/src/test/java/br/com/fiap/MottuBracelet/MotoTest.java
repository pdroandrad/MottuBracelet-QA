package br.com.fiap.MottuBracelet;

import br.com.fiap.MottuBracelet.model.Moto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotoTest {

    private Moto moto;

    @BeforeEach
    void setUp() {
        moto = new Moto();
        moto.setStatus("Ativa");
    }

    @Test
    void deveAtualizarStatusDaMoto() {
        moto.setStatus("Em manutenção");
        assertEquals("Em manutenção", moto.getStatus(), "O status deve ser atualizado corretamente");
    }

    @Test
    void deveMarcarMotoEmManutencao() {
        moto.marcarEmManutencao();
        assertEquals("Em manutenção", moto.getStatus(), "A moto deve ser marcada como 'Em manutenção'");
    }

    @Test
    void deveAtivarMoto() {
        moto.marcarEmManutencao();
        moto.ativarMoto();
        assertEquals("Ativa", moto.getStatus(), "A moto deve ser reativada corretamente");
    }
}
