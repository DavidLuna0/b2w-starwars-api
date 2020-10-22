package com.david.b2wstarwars.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.b2wstarwars.domain.Planet;
import com.david.b2wstarwars.repositories.PlanetRepository;

@Service
public class PlanetService {

	@Autowired
	PlanetRepository planetRepository;
	
	public Optional<Planet> findById(String id) {
		Optional<Planet> obj = planetRepository.findById(id);
		return obj;
	}
	
	public Optional<Planet> findByNome(String nome) {
		Optional<Planet> obj = planetRepository.findById(nome);
		return obj;
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
