package com.eloi.campanhas.application;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eloi.campanhas.domain.Campanha;
import com.eloi.campanhas.domain.CampanhaRepository;

@RestController
@RequestMapping(path = "/clubes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClubeController {

	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@GetMapping("/{id}/campanhas")
	public ResponseEntity<List<CampanhaResource>> getCampanhasByClube(@PathVariable Long id){
		List<Campanha> list =  campanhaRepository.findByIdTimeCoracao(id);
		List<CampanhaResource> listResources = new ArrayList<CampanhaResource>();
		
		list.forEach(l -> {
			CampanhaResource resource = new CampanhaResource(l);
			resource.add(linkTo(methodOn(CampanhaController.class).getById(l.getId())).withSelfRel());
			listResources.add(resource);
		});
		
		return new ResponseEntity<List<CampanhaResource>>(listResources, HttpStatus.OK);
	}
	
}
