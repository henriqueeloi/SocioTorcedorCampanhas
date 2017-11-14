package com.eloi.sociotorcedor.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class UnicoEmailValidator implements ConstraintValidator<UnicoEmail, String> {
 
	@Autowired
    private ClienteRepository clienteRepository;
 
    public UnicoEmailValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
 
    public UnicoEmailValidator() {}
    
    public void initialize(UnicoEmail constraint) {
    }
 
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !clienteRepository.findByEmail(email).isPresent();
    }
 
}


