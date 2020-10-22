package com.david.b2wstarwars.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.b2wstarwars.domain.Planet;
import com.david.b2wstarwars.services.PlanetService;

@RestController
@RequestMapping(value = "/api/planets")
public class PlanetResource {

	@Autowired
	private PlanetService planetService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Planet> findById(@PathVariable String id) {
		Planet obj = planetService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<Planet> findByNome(@RequestParam String nome) {
		Planet obj = planetService.findByNome(nome);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Planet> insert(@RequestBody Planet obj) {
		obj = planetService.save(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		planetService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Planet>> findAll() {
		List<Planet> list = planetService.findAll();
		return ResponseEntity.ok().body(list);
	}
}
