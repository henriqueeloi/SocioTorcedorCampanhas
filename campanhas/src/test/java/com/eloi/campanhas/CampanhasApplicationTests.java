package com.eloi.campanhas;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eloi.campanhas.domain.Campanha;
import com.eloi.campanhas.domain.CampanhaRepository;
import com.eloi.campanhas.domain.CampanhaService;
import com.eloi.campanhas.infrastructure.exception.DataVigenciaVencidaException;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CampanhasApplicationTests {

	
	@Autowired
	private CampanhaService service;
		
	@Autowired
	private CampanhaRepository repository;
	
	@Test(expected = DataVigenciaVencidaException.class)
	public void deveValidarVigenciaDaCampanha(){
		
		String nome = "Poderoso Timão";
		Long timeCoracao = 1l;
		LocalDate dataInicioVigencia = LocalDate.of(2017, 11, 1);
		LocalDate dataFimVigencia = LocalDate.of(2017, 11, 3);
				
		Campanha campanha = new Campanha(nome, timeCoracao, dataInicioVigencia, dataFimVigencia);
		Campanha saved = service.save(campanha);
				
		service.getCampanha(saved.getId());		
	}

	@Test
	public void deveRetornarCampanhasComMesmaDataDeVigencia(){
		
		String nome = "Campanha 1";
		Long timeCoracao = 1l;
		LocalDate dataInicioVigencia = LocalDate.of(2017, 11, 1);
		LocalDate dataFimVigencia = LocalDate.of(2017, 11, 20);
		Campanha campanha = new Campanha(nome, timeCoracao, dataInicioVigencia, dataFimVigencia);
		Campanha saved = service.save(campanha);
		
		String nome2 = "Campanha 2";
		Long timeCoracao2 = 1l;
		LocalDate dataInicioVigencia2 = LocalDate.of(2017, 11, 1);
		LocalDate dataFimVigencia2 = LocalDate.of(2017, 11, 19);		
		Campanha campanha2 = new Campanha(nome2, timeCoracao2, dataInicioVigencia2, dataFimVigencia2);		
		Campanha saved2 = service.save(campanha2);
				
		List<Campanha> vigencia = repository.findByDataInicioVigenciaAndDataFimVigencia(dataInicioVigencia2, dataFimVigencia2);
		
		Assert.assertTrue(vigencia.stream().filter(c -> c.getDataInicioVigencia().equals(dataInicioVigencia2) && c.getDataFimVigencia().equals(dataFimVigencia2)).count() == 1);
	}
	
	@Test
	public void deveMudarDataVigenciaIguais(){
		String nome = "Campanha 1";
		Long timeCoracao = 1l;
		LocalDate dataInicioVigencia = LocalDate.of(2017, 10, 1);
		LocalDate dataFimVigencia = LocalDate.of(2017, 10, 03);
		Campanha campanha = new Campanha(nome, timeCoracao, dataInicioVigencia, dataFimVigencia);
		Campanha saved = service.save(campanha);
		
		String nome2 = "Campanha 2";
		Long timeCoracao2 = 1l;
		LocalDate dataInicioVigencia2 = LocalDate.of(2017, 10, 1);
		LocalDate dataFimVigencia2 = LocalDate.of(2017, 10, 02);		
		Campanha campanha2 = new Campanha(nome2, timeCoracao2, dataInicioVigencia2, dataFimVigencia2);		
		Campanha saved2 = service.save(campanha2);
		
		String nome3 = "Campanha 2";
		Long timeCoracao3 = 1l;
		LocalDate dataInicioVigencia3 = LocalDate.of(2017, 10, 1);
		LocalDate dataFimVigencia3 = LocalDate.of(2017, 10, 02);		
		Campanha campanha3 = new Campanha(nome3, timeCoracao3, dataInicioVigencia3, dataFimVigencia3);		
		
		service.save(campanha3);
		
		Campanha campanha1DataAlterada = repository.getOne(saved.getId());		
		Assert.assertTrue(campanha1DataAlterada.getDataFimVigencia().isAfter(dataFimVigencia));

		Campanha campanha2DataAlterada = repository.getOne(saved2.getId());
		Assert.assertTrue(campanha2DataAlterada.getDataFimVigencia().isAfter(dataFimVigencia2));
			
	}

	
}
