package com.eloi.campanhas.domain;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

public class ClubeCliente {
	private Long id;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	private Clube clube;
	
	private Long clienteId;
}
