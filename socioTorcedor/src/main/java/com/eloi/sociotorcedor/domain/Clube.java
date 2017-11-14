package com.eloi.sociotorcedor.domain;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clube {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
