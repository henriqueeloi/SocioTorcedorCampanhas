package com.eloi.sociotorcedor.application;

import org.springframework.hateoas.ResourceSupport;

import com.eloi.sociotorcedor.domain.Cliente;
import com.eloi.sociotorcedor.domain.Clube;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubeResource extends ResourceSupport {

	final private Clube content;
	
	@JsonCreator
	public ClubeResource(@JsonProperty("content") Clube content) {
		this.content = content;		
	}
	
	public Clube getContent(){
		return content;
	}
	
	
	
}
