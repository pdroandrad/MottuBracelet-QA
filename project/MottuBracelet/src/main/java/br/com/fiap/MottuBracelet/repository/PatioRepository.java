package br.com.fiap.MottuBracelet.repository;

import br.com.fiap.MottuBracelet.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long> {
}
