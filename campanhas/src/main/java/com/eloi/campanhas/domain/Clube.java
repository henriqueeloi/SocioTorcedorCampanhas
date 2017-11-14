package com.eloi.campanhas.domain;

import java.util.Optional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clube {
	
	private Long id;
	
	private String nome;
	
	public Clube(String nome) {
		super();
		this.nome = nome;
	}
	public Clube() {}

	public Optional<Long> existId() {	
		return id != null ? Optional.of(id) : Optional.empty();
	}
	
	public Long getId(){
		return id;
	}

	public String getNome() {
		return nome;
	}
	
}
