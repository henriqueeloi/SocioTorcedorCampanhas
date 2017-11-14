package com.eloi.campanhas.application;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eloi.campanhas.domain.Campanha;
import com.eloi.campanhas.domain.CampanhaRepository;
import com.eloi.campanhas.domain.CampanhaService;
import com.eloi.campanhas.infrastructure.exception.NotFoundException;

@RestController
@RequestMapping(path = "/campanhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CampanhaController {
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	private CampanhaService campanhaService;
	
	@Value("${socio.torcedor.url}")
	private String SOCIO_TORCEDOR_URL;
	
	@GetMapping("/{id}")
	public ResponseEntity<CampanhaResource> getById(@PathVariable("id") Long id){

		Campanha findOne = getCampanha(id);
				
		CampanhaResource campanhaResource = new CampanhaResource(findOne);
				
		campanhaResource.add(linkTo(methodOn(CampanhaController.class).getById(findOne.getId())).withSelfRel());
		campanhaResource.add(new Link(SOCIO_TORCEDOR_URL + "/clubes/" + findOne.getIdTimeCoracao(),"timeCoracao"));
		return new ResponseEntity<CampanhaResource>(campanhaResource, HttpStatus.OK);	
	}
		
	@GetMapping	
	public ResponseEntity<List<CampanhaResource>> getAll(){
		List<Campanha> list =  campanhaRepository.findAll();
		List<CampanhaResource> listResources = new ArrayList<CampanhaResource>();
		
		list.forEach(l -> {
			CampanhaResource resource = new CampanhaResource(l);
			resource.add(linkTo(methodOn(CampanhaController.class).getById(l.getId())).withSelfRel());
			listResources.add(resource);
		});
		
		return new ResponseEntity<List<CampanhaResource>>(listResources, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CampanhaResource> create(@RequestBody @Valid Campanha campanha){
		Campanha saved = campanhaService.save(campanha);
		
		CampanhaResource campanhaResource = new CampanhaResource(saved);		
		return new ResponseEntity<CampanhaResource>(campanhaResource, HttpStatus.CREATED);			
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		Campanha one = getCampanha(id);
		campanhaRepository.delete(one.getId());
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody Campanha campanha){
		
		Campanha one = getCampanha(id);
		
		campanha.setId(one.getId());		
		campanhaService.save(campanha);
	}

	private Campanha getCampanha(Long id) {
		Campanha one = campanhaService.getCampanha(id);
		if(one == null) throw new NotFoundException();
		return one;
	}
	
}
