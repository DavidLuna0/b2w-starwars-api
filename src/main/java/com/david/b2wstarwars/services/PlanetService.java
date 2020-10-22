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
			filmsQtd = swapiService.getPlanetQuantity(obj.get().getName());
			obj.get().setNumberOfFilms(filmsQtd);
		} catch (Exception e) {
			logger.error(e.toString(), e.getMessage());
		}
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - Id: " + id + " Tipo: " + Planet.class.getName()));
	}
	
	public Planet findByName(String name) {
		Planet obj = planetRepository.findByName(name);
		int filmsQtd = 0;
		
		if(obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! - Name: " + name + " Tipo: " + Planet.class.getName());
		}
		try {
			filmsQtd = swapiService.getPlanetQuantity(obj.getName());
		} catch (Exception e) {
			logger.error(e.toString(), e.getMessage());
			return null;
		}
		obj.setNumberOfFilms(filmsQtd);
		return obj;
	}
	
	public List<Planet> findAll() {
		List<Planet> objs = planetRepository.findAll();
		int filmsQtd = 0;
		for (Planet planet : objs) {
			try {
				filmsQtd = swapiService.getPlanetQuantity(planet.getName());
			} catch (Exception e) {
				logger.error(e.toString(), e.getMessage());
			}
			planet.setNumberOfFilms(filmsQtd);
		}
		return objs;
	}
	
	public Planet save(Planet planet) {
		Planet obj = planetRepository.findByName(planet.getName());
		int filmsQtd = 0;
		if(obj == null) {			
			obj = planetRepository.save(planet);
		}
		try {
			filmsQtd = swapiService.getPlanetQuantity(obj.getName());
		} catch (Exception e) {
			logger.error(e.toString(), e.getMessage());
			return null;
		}
		obj.setNumberOfFilms(filmsQtd);
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
