package com.eloi.campanhas.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Resource not found")
public class NotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 1392926835663386885L;

}
