package com.david.b2wstarwars.dto;

import javax.validation.constraints.NotNull;

public class PlanetDTO {

	@NotNull(message = "Planet name must not be null")
	private String name;
	@NotNull(message = "Planet climate must not be null")
	private String climate;
	@NotNull(message = "Planet terrain must not be null")
	private String terrain;
	
	public PlanetDTO() {
		
	}

	public PlanetDTO(String name, String climate, String terrain) {
		super();
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
	
	
	
}
