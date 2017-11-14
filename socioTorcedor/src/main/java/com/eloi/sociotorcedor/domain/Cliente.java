package com.eloi.sociotorcedor.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Email;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeCompleto;
	
	@Email
	@UnicoEmail(message="Cliente já existe")
	private String email;
	
	private LocalDate dataNascimento;
	
	@ManyToOne(cascade = { CascadeType.ALL})
	private Clube timeCoracao;
	
	public Cliente(Long id, String nomeCompleto, String email, LocalDate dataNascimento, Clube timeCoracao) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.timeCoracao = timeCoracao;
	}

	public Cliente() {}

	public Long getId() {
		return id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Clube getTimeCoracao() {
		return timeCoracao;
	}
	
	public void defineClube(Clube clube) {
		this.timeCoracao = clube;
	}
	
}
