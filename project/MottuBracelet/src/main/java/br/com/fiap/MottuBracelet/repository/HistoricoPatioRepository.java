package br.com.fiap.MottuBracelet.repository;

import br.com.fiap.MottuBracelet.model.HistoricoPatio;
import br.com.fiap.MottuBracelet.model.Moto;
import br.com.fiap.MottuBracelet.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricoPatioRepository extends JpaRepository<HistoricoPatio, Long> {
    Optional<HistoricoPatio> findByMotoAndPatioAndDataSaidaIsNull(Moto moto, Patio patio);

    List<HistoricoPatio> findByMoto(Moto moto);

    List<HistoricoPatio> findByPatio(Patio patio);

    List<HistoricoPatio> findByDataSaidaIsNull();
}
