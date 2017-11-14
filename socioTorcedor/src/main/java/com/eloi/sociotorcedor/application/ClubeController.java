package com.eloi.sociotorcedor.application;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

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

import com.eloi.sociotorcedor.domain.Clube;
import com.eloi.sociotorcedor.domain.ClubeRepository;

@RestController
@RequestMapping(path = "/clubes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClubeController {

	@Autowired
	private ClubeRepository clubeRepository;
		
	@PostMapping
	public ResponseEntity<ClubeResource> create(@RequestBody @Valid Clube clube){
		Clube saved = clubeRepository.save(clube);
		ClubeResource clubeResource = new ClubeResource(saved);
		return new ResponseEntity<ClubeResource>(clubeResource, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Clube> getById(@PathVariable("id") Long id){

		Clube clube = clubeRepository.findOne(id);
				
		ClubeResource clubeResource = new ClubeResource(clube);
				
		//clubeResource.add(linkTo(methodOn(ClubeController.class).getById(clube.getId())).withSelfRel());
		return new ResponseEntity<Clube>(clube, HttpStatus.OK);	
	}
	
	@GetMapping	
	public ResponseEntity<List<ClubeResource>> getAll(){
		List<Clube> list =  clubeRepository.findAll();
		List<ClubeResource> listResources = new ArrayList<ClubeResource>();
		
		list.forEach(l -> {
			ClubeResource resource = new ClubeResource(l);
			resource.add(linkTo(methodOn(ClubeController.class).getById(l.getId())).withSelfRel());
			listResources.add(resource);
		});
		
		return new ResponseEntity<List<ClubeResource>>(listResources, HttpStatus.OK);
	}
	
}
