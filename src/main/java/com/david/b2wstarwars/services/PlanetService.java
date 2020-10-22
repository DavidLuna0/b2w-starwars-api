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
	
	@Autowired
	SWAPIService swapiService;
	
	public Planet findById(String id) {
		Optional<Planet> obj = planetRepository.findById(id);
		int filmsQtd = 0;
		try {
			filmsQtd = swapiService.getPlanetQuantity(obj.get().getNome());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.get().setQuantidadeFilmes(filmsQtd);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - Id: " + id + " Tipo: " + Planet.class.getName()));
	}
	
	public Planet findByNome(String nome) {
		Optional<Planet> obj = planetRepository.findById(nome);
		int filmsQtd = 0;
		try {
			filmsQtd = swapiService.getPlanetQuantity(obj.get().getNome());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.get().setQuantidadeFilmes(filmsQtd);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - Id: " + nome + " Tipo: " + Planet.class.getName()));
	}
	
	public List<Planet> findAll() {
		List<Planet> objs = planetRepository.findAll();
		int filmsQtd = 0;
		for (Planet planet : objs) {
			try {
				filmsQtd = swapiService.getPlanetQuantity(planet.getNome());
			} catch (Exception e) {
				e.printStackTrace();
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
		planetRepository.deleteById(id);
	}
}
