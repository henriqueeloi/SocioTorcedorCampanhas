package com.eloi.campanhas;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
		basePackageClasses = {CampanhasApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class CampanhasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampanhasApplication.class, args);
	}
	
	public static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");


}
