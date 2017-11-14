package com.eloi.sociotorcedor.domain;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClubeRepository clubeRepository;
	
	
	public Cliente save(Cliente cliente) {
		
		Clube findOne = getClube(cliente);
		
		cliente.defineClube(findOne);
		return clienteRepository.save(cliente);
	}


	private Clube getClube(Cliente cliente) {
		Optional<Long> existId = cliente.getTimeCoracao().existId();
		if(!existId.isPresent()) {
			throw new EntityNotFoundException("Clube não existe");
		}
		
		Clube findOne = clubeRepository.findOne(existId.get());
		return findOne;
	}
}
