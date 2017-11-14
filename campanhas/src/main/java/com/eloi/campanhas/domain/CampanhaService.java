package com.eloi.campanhas.domain;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloi.campanhas.infrastructure.ClubeApi;

@Service
public class CampanhaService {

	@Autowired
	private ClubeApi clubApi;
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	AtualizaDataValidadeCampanha atualizaDataValidadeCampanha;
	
	public Campanha save(Campanha campanha) throws EntityNotFoundException{		
		clubeExiste(campanha);		
		atualizaDataValidadeCampanha.atualizarCampanhas(campanha);
		return campanhaRepository.save(campanha);		
	}

	private void clubeExiste(Campanha campanha) throws EntityNotFoundException {		

		Long id = campanha.getIdTimeCoracao();
		
		Clube clube = clubApi.getClube(id);
		if(!clube.existId().isPresent()) {
			throw new EntityNotFoundException("Time do coração não existe");
		}
		
	}

	public Campanha getCampanha(Long id) {
		Campanha campanha = campanhaRepository.getOne(id);
		
		campanha.validaDataVigente();
		
		return campanha;
	}
	
}
