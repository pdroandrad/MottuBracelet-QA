package br.com.fiap.MottuBracelet.repository;

import br.com.fiap.MottuBracelet.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    Optional<Moto> findByImei(String imei);
    Optional<Moto> findByPlaca(String placa);
    Optional<Moto> findByChassi(String chassi);
}