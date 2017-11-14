package com.eloi.campanhas.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtualizaDataValidadeCampanha {
	
	@Autowired
	public CampanhaRepository campanhaRepository;
	
	public void atualizarCampanhas(Campanha campanha) {
		List<Campanha> campanhasPorDataVigencia = getCampanhasPorDataVigencia(campanha);		
		while (!campanhasPorDataVigencia.isEmpty()) {
			for (Campanha atual : campanhasPorDataVigencia) {
				atual.AddUmDiaDataFinalVigencia();
				campanhaRepository.save(atual);
				campanhasPorDataVigencia = excluiAtual(atual);
			}						
		}
	}

	private List<Campanha> excluiAtual(Campanha atual) {
		return getCampanhasPorDataVigencia(atual).stream().filter(c -> c.getId() != atual.getId()).collect(Collectors.toList());
	}
	
	private List<Campanha> getCampanhasPorDataVigencia(Campanha campanha) {
		List<Campanha> vigencias = campanhaRepository.findByDataInicioVigenciaAndDataFimVigencia(campanha.getDataInicioVigencia(), campanha.getDataFimVigencia());
		return vigencias;
	}	
}