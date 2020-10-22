package com.david.b2wstarwars.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.b2wstarwars.domain.Planet;
import com.david.b2wstarwars.repositories.PlanetRepository;
import com.david.b2wstarwars.services.exceptions.ObjectNotFoundException;

@Service
public class PlanetService {

	@Autowired
	PlanetRepository planetRepository;
	
	public Planet findById(String id) {
		Optional<Planet> obj = planetRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - Id: " + id + " Tipo: " + Planet.class.getName()));
	}
	
	public Planet findByNome(String nome) {
		Optional<Planet> obj = planetRepository.findById(nome);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - Id: " + nome + " Tipo: " + Planet.class.getName()));
	}
	
	public List<Planet> findAll() {
		List<Planet> objs = planetRepository.findAll();
		return objs;
	}
	
	public Planet save(Planet planet) {
		Planet obj = planetRepository.save(planet);
		return obj;
	}
	
	public void deleteById(String id) {
		planetRepository.deleteById(id);
	}
}
