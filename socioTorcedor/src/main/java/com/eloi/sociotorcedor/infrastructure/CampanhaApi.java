package com.eloi.sociotorcedor.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eloi.sociotorcedor.domain.Campanha;


@Component
public class CampanhaApi {

//	@Value("${socio.torcedor.url}")
	private String SOCIO_TORCEDOR_URL = "http://localhost:8080/clubes/";
	
	private final String ENDPOINT = "/campanhas/";
	
	public List<Campanha> getCampanhas(Long id) {
		 RestTemplate restTemplate = new RestTemplate();
//		 restTemplate.getForObject(SOCIO_TORCEDOR_URL + ENDPOINT + id, List<Campanha.class>.getClass());
	
//		 List<Campanha> p = (List<Campanha>) restTemplate.postForObject(SOCIO_TORCEDOR_URL + ENDPOINT + id, List.class);

		 List<Campanha> p = (List<Campanha>) restTemplate.getForObject(SOCIO_TORCEDOR_URL + id + ENDPOINT, ArrayList.class);
				 
		 return p;
	}
	
}
