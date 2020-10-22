package com.david.b2wstarwars.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;


@Service
public class SWAPIService {
	
	RestTemplate template = new RestTemplate();
	private static String SWAPI_URL = "https://swapi.dev/api/planets/?search=";

	public int getPlanetQuantity(String planetName) throws Exception {
		String URL = SWAPI_URL.concat(planetName);
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = template.getForEntity(URL, String.class);
		
		JsonNode node = mapper.readTree(response.getBody());
		try {
			node = node.get("results").get(0).get("films");
		} catch(NullPointerException e) {
			return 0;
		}
		
		ObjectReader reader = mapper.readerFor(new TypeReference<List<String>>() {
		});
		List<String> films = reader.readValue(node);

		return films.size();
		
	}
}
