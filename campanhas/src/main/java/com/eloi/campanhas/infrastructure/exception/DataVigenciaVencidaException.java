package com.eloi.campanhas.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY, reason="campaign date expired")
public class DataVigenciaVencidaException extends RuntimeException {
	private static final long serialVersionUID = 112314845777889999L;

}
