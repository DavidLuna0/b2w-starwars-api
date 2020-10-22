package com.david.b2wstarwars.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.b2wstarwars.domain.Planet;
import com.david.b2wstarwars.repositories.PlanetRepository;
import com.david.b2wstarwars.services.exceptions.ObjectNotFoundException;

@Service
public class PlanetService {

	@Autowired
	PlanetRepository planetRepository;
	
	@Autowired
	SWAPIService swapiService;
	
	private static Logger logger = LoggerFactory.getLogger(PlanetService.class);
	
	public Planet findById(String id) {
		Optional<Planet> obj = planetRepository.findById(id);
		int filmsQtd = 0;
		try {
			filmsQtd = swapiService.getPlanetQuantity(obj.get().getNome());
			obj.get().setQuantidadeFilmes(filmsQtd);
		} catch (Exception e) {
			logger.error(e.toString(), e.getMessage());
		}
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - Id: " + id + " Tipo: " + Planet.class.getName()));
	}
	
	public Planet findByNome(String nome) {
		Planet obj = planetRepository.findByNome(nome);
		int filmsQtd = 0;
		
		if(obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! - Id: " + nome + " Tipo: " + Planet.class.getName());
		}
		try {
			filmsQtd = swapiService.getPlanetQuantity(obj.getNome());
		} catch (Exception e) {
			logger.error(e.toString(), e.getMessage());
		}
		obj.setQuantidadeFilmes(filmsQtd);
		return obj;
	}
	
	public List<Planet> findAll() {
		List<Planet> objs = planetRepository.findAll();
		int filmsQtd = 0;
		for (Planet planet : objs) {
			try {
				filmsQtd = swapiService.getPlanetQuantity(planet.getNome());
			} catch (Exception e) {
				logger.error(e.toString(), e.getMessage());
			}
			planet.setQuantidadeFilmes(filmsQtd);
		}
		return objs;
	}
	
	public Planet save(Planet planet) {
		Planet obj = planetRepository.save(planet);
		return obj;
	}
	
	public void deleteById(String id) {
		try {
			planetRepository.deleteById(id);
		} catch(Exception e) {
			logger.error(e.toString(), e.getMessage());
		}
	}
}
