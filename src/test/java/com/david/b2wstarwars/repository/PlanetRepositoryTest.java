package com.david.b2wstarwars.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.david.b2wstarwars.domain.Planet;
import com.david.b2wstarwars.repositories.PlanetRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PlanetRepositoryTest {

	@Autowired
	PlanetRepository planetRepository;
	
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
		planetRepository.save(planet2);
		planetRepository.save(planet3);
		planetRepository.save(planet4);
		planetRepository.save(planet5);
	}
	
	@AfterEach
	public void after() {
		planetRepository.deleteById(planet1.getId());
		planetRepository.deleteById(planet2.getId());
		planetRepository.deleteById(planet3.getId());
		planetRepository.deleteById(planet4.getId());
		planetRepository.deleteById(planet5.getId());
	}
	
	@Test
	public void insertingPlanet() {
		assertThat(planet1.getId()).isNull();
		assertThat(planetRepository.save(planet1)).isNotNull();
		assertThat(planet1.getNome()).isEqualTo("Bespin");
		assertThat(planet1.getTerreno()).isNotNull();
		assertThat(planet1.getClima()).isNotNull();
	}
	
	@Test
	public void findPlanetById() {
		assertThat(planetRepository.findById(planet2.getId())).isNotNull();
		assertThat(planetRepository.findById(planet2.getId()).get().getNome()).isEqualTo("Alderaan");
	}
	
	@Test
	public void findPlanetByNome() {
		assertThat(planetRepository.findByNome(planet3.getNome())).isNotNull();
		assertThat(planetRepository.findByNome(planet3.getNome()).getTerreno()).isEqualTo("tundra, ice caves, mountain ranges");
		
	}
	
	@Test
	public void findAllPlanets() {
		assertThat(planetRepository.findAll()).hasOnlyElementsOfType(Planet.class);
		assertThat(planetRepository.findAll().size()).isEqualTo(4);
	}
	
	@Test
	public void deletePlanetById() {
		planetRepository.deleteById(planet4.getId());
		assertThat(planetRepository.findByNome(planet4.getNome())).isNull();
		assertThat(planetRepository.findByNome(planet5.getNome())).isNotNull();
	}
	
	
}
