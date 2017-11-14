package com.eloi.sociotorcedor.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


public class Campanha {
	private Long id;
	
	private String nome;

	private Long idTimeCoracao;
	
	private LocalDate dataInicioVigencia;
	
	private LocalDate dataFimVigencia;

	public Campanha(String nome, Long timeCoracao, LocalDate dataInicioVigencia, LocalDate dataFimVigencia) {
		super();
		this.nome = nome;
		this.idTimeCoracao = timeCoracao;
		this.dataInicioVigencia = dataInicioVigencia;
		this.dataFimVigencia = dataFimVigencia;
	}
	public Campanha() {}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Long getIdTimeCoracao() {
		return idTimeCoracao;
	}

	public LocalDate getDataVigencia() {
		return dataInicioVigencia;
	}
	public void defineClube(Long idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDataFimVigencia() {
		return dataFimVigencia;
	}

	public LocalDate getDataInicioVigencia() {
		return dataInicioVigencia;
	}

}
