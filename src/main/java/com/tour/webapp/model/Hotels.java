package com.tour.webapp.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotels {

	private int id;
	private String name;
	private String location;

	public Hotels() {

	}
	
	public Hotels(Integer id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
