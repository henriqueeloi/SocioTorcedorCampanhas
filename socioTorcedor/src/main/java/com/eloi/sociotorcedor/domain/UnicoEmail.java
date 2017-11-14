package com.eloi.sociotorcedor.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UnicoEmailValidator.class)
public @interface UnicoEmail {
	String message() default "Cliente já existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
