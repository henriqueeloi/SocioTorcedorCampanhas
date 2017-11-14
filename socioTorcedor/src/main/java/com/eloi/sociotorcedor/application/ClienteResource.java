package com.eloi.sociotorcedor.application;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.eloi.sociotorcedor.domain.Campanha;
import com.eloi.sociotorcedor.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteResource extends ResourceSupport {

	final private Cliente content;
	
	private List<Campanha> campanhas;
	
	@JsonCreator
	public ClienteResource(@JsonProperty("content") Cliente content) {
		this.content = content;		
	}
	
	@JsonCreator
	public ClienteResource(@JsonProperty("content") Cliente content, @JsonProperty("campanhas") List<Campanha> campanhas) {
		this.content = content;
		this.campanhas = campanhas;
	}
	
	public Cliente getContent(){
		return content;
	}
	
	public List<Campanha> getCampanhas(){
		return campanhas;
	}


}
