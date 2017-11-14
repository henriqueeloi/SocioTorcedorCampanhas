package com.eloi.campanhas.application;

import org.springframework.hateoas.ResourceSupport;

import com.eloi.campanhas.domain.Campanha;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CampanhaResource extends ResourceSupport {

	final private Campanha content;
	
	@JsonCreator
	public CampanhaResource(@JsonProperty("content") Campanha content) {
		this.content = content;		
	}
	
	public Campanha getContent(){
		return content;
	}
	
}
