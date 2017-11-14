package com.eloi.sociotorcedor.application;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eloi.sociotorcedor.domain.Campanha;
import com.eloi.sociotorcedor.domain.Cliente;
import com.eloi.sociotorcedor.domain.ClienteRepository;
import com.eloi.sociotorcedor.domain.ClienteService;
import com.eloi.sociotorcedor.infrastructure.CampanhaApi;

@RestController
@RequestMapping(path = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CampanhaApi campanhaApi;
	
	@PostMapping
	public ResponseEntity<ClienteResource> create(@RequestBody @Valid Cliente cliente){
						
		Cliente saved = clienteService.save(cliente);
		
		ClienteResource clienteResource = new ClienteResource(saved);		
		return new ResponseEntity<ClienteResource>(clienteResource, HttpStatus.CREATED);			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResource> getById(@PathVariable("id") Long id){

		Cliente cliente = getCliente(id);
				
		ClienteResource clienteResource = new ClienteResource(cliente);
				
		clienteResource.add(linkTo(methodOn(ClienteController.class).getById(cliente.getId())).withSelfRel());
		return new ResponseEntity<ClienteResource>(clienteResource, HttpStatus.OK);	
	}
	
	
	@GetMapping("/{id}/campanhas")
	public ResponseEntity<ClienteResource> getCampanhas(@PathVariable("id") Long id){

		Cliente cliente = getCliente(id);
				
		List<Campanha> campanhas = getCampanhas(cliente);
		
		ClienteResource clienteResource = new ClienteResource(cliente, campanhas);
				
		clienteResource.add(linkTo(methodOn(ClienteController.class).getById(cliente.getId())).withSelfRel());
		return new ResponseEntity<ClienteResource>(clienteResource, HttpStatus.OK);	
	}

	private List<Campanha> getCampanhas(Cliente cliente) {
		List<Campanha> campanhas = campanhaApi.getCampanhas(cliente.getTimeCoracao().getId());
		if(campanhas.isEmpty()){
			throw new EntityNotFoundException("Nenhuma campanha associada a este cliente"); 
		}
		return campanhas;
	}

	private Cliente getCliente(Long id) {
		Cliente cliente = clienteRepository.findOne(id);
		if(cliente == null){
			throw new EntityNotFoundException("Cliente não existe");
		}
		return cliente;
	}
}
