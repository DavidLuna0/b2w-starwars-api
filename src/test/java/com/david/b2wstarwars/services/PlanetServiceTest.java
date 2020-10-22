package com.david.b2wstarwars.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.david.b2wstarwars.domain.Planet;
import com.david.b2wstarwars.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class PlanetServiceTest {
	
	@Autowired
	PlanetService planetService;
	
	Planet planet1;
	Planet planet2;
	Planet planet3;
	Planet planet4;
	Planet planet5;

	@BeforeEach
	public void setUp() {
		planet1 = new Planet(null, "Bespin", "temperate", "gas giant");
		planet2 = new Planet(null, "Alderaan", "temperate", "grasslands, mountains");
		planet3 = new Planet(null, "Hoth", "frozen", "tundra, ice caves, mountain ranges");
		planet4 = new Planet(null, "Endor", "temperate", "forests, mountains, lakes");
		planet5 = new Planet(null, "Naboo", "temperate", "grassy hills, swamps, forests, mountains");
		planetService.save(planet1);
		planetService.save(planet2);
		planetService.save(planet3);
		planetService.save(planet4);
		planetService.save(planet5);
	}
	
	@AfterEach
	public void after() {
		planetService.deleteById(planet1.getId());
		planetService.deleteById(planet2.getId());
		planetService.deleteById(planet3.getId());
		planetService.deleteById(planet4.getId());
		planetService.deleteById(planet5.getId());
	}
	
	@Test
	public void findPlanetById() {
		assertThat(planetService.findById(planet1.getId())).isNotNull();
		assertThat(planetService.findById(planet1.getId()).getNome()).isEqualTo("Bespin");
		assertThat(planetService.findById(planet1.getId()).getQuantidadeFilmes()).isEqualTo(1);
	}
	
	@Test
	public void failFindPlanetById() {
		assertThrows(ObjectNotFoundException.class, () -> {
			planetService.findById("not valid id");
		});
	}
	
	@Test
	public void findPlanetByNome() {
		assertThat(planetService.findByNome(planet2.getNome())).isNotNull();
		assertThat(planetService.findByNome(planet2.getNome()).getClima()).isEqualTo("temperate");
		assertThat(planetService.findByNome(planet2.getNome()).getQuantidadeFilmes()).isEqualTo(2);
	}
	
	@Test
	public void failFindPlanetByNome() {
		assertThrows(ObjectNotFoundException.class, () -> {
			planetService.findByNome("not valid name");
		});
	}
	
	@Test
	public void findAllPlanets() {
		assertThat(planetService.findAll().size()).isEqualTo(5);
	}
	
	@Test
	public void save() {
		assertThat(planetService.save(planet4)).isNotNull();
	}
	
	@Test
	public void deleteById() {
		planetService.deleteById(planet5.getId());
		assertThrows(ObjectNotFoundException.class, () -> {
		planetService.findByNome(planet5.getNome());
	});
	}
	

}
