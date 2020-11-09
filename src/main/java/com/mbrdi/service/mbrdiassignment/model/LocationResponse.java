package com.mbrdi.service.mbrdiassignment.model;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LocationResponse {
	
	//private Item items;
	private List<Place> place;
	private String city;
	private String country;
	public List<Place> getPlace() {
		return place;
	}
	public void setPlace(List<Place> place) {
		this.place = place;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
