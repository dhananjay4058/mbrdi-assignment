package com.mbrdi.service.mbrdiassignment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Place {
	
	private String name;
	private String type;
	private Integer distance;
	private Integer averageRating;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Integer getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Integer averageRating) {
		this.averageRating = averageRating;
	}

	
}
