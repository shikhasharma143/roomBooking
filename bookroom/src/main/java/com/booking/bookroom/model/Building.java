package com.booking.bookroom.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Building {
	List<Floor> floors;
	String name;
	String id;
	String address;
	String plotLocation;

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlotLocation() {
		return plotLocation;
	}

	public void setPlotLocation(String plotLocation) {
		this.plotLocation = plotLocation;
	}

}
