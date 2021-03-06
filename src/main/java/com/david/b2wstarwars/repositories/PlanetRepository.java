package com.david.b2wstarwars.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.david.b2wstarwars.domain.Planet;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

	Planet findByName(String name);
}
