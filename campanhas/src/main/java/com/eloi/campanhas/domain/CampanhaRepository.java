package com.eloi.campanhas.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
	public List<Campanha> findByDataInicioVigenciaAndDataFimVigencia(LocalDate dataInicio, LocalDate dataFim);
	public List<Campanha> findByIdTimeCoracao(Long id);
}
