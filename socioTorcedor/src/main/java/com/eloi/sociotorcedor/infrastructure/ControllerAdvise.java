package com.eloi.sociotorcedor.infrastructure;

import javax.persistence.EntityNotFoundException;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import javassist.NotFoundException;

@RestControllerAdvice
public class ControllerAdvise {
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public VndErrors handleEntityNotFound(EntityNotFoundException ex) {
		String message = ex.getMessage();
		return getVndError(message);
	}
			
	@ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    VndErrors testNotFoundExceptionHandler(NotFoundException ex) {
		String message = ex.getMessage();
		return getVndError(message);		
    }
	
	@ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    VndErrors testNotFoundExceptionHandler(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldError().getDefaultMessage();			
		return getVndError(message);
	}
	
	
	@ResponseBody
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    VndErrors testHttpClientErrorExceptionHandler(HttpClientErrorException ex) {
		return getVndError("Ocorreu algum problema tente mais tarde");
	}
	
	@ResponseBody
    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    VndErrors testHttpClientErrorExceptionHandler(ResourceAccessException ex) {
		return getVndError("Ocorreu algum problema tente mais tarde");
	}
	

	private VndErrors getVndError(String message) {
		return new VndErrors(
				"Error",
				message,
				new Link("http://...", "about")                
				);
	}
	
	
}
