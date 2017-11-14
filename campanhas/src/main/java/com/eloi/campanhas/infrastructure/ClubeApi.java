package com.eloi.campanhas.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eloi.campanhas.domain.Clube;


@Component
public class ClubeApi {

	@Value("${socio.torcedor.url}")
	private String SOCIO_TORCEDOR_URL;
	
	private final String ENDPOINT = "/clubes/";
	
	public Clube getClube(Long id) {
		 RestTemplate restTemplate = new RestTemplate();
	     return restTemplate.getForObject(SOCIO_TORCEDOR_URL + ENDPOINT + id, Clube.class);
	}
	
}
