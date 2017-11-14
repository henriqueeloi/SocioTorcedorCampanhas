package com.eloi.sociotorcedor;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
		basePackageClasses = {SocioTorcedorApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class SocioTorcedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocioTorcedorApplication.class, args);
	}

	public static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");

}
