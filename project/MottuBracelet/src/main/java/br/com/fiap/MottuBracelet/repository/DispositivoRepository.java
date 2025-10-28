package br.com.fiap.MottuBracelet.repository;

import br.com.fiap.MottuBracelet.model.Dispositivo;
import br.com.fiap.MottuBracelet.util.StatusDispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    List<Dispositivo> findByStatus(StatusDispositivo status);
}
