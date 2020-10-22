package com.david.b2wstarwars.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planets")
public class Planet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@NotNull(message = "Planet name must not be null")
	private String name;
	@NotNull(message = "Planet climate must not be null")
	private String climate;
	@NotNull(message = "Planet terrain must not be null")
	private String terrain;
	
	@Transient
	private int numberOfFilms;
	
	public Planet() {
		
	}

	public Planet(String id, String nome, String clima, String terreno) {
		super();
		this.id = id;
		this.name = nome;
		this.climate = clima;
		this.terrain = terreno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getNumberOfFilms() {
		return numberOfFilms;
	}

	public void setNumberOfFilms(int numberOfFilms) {
		this.numberOfFilms = numberOfFilms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
