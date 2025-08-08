package com.ms.companyms.company;
import java.util.List;

 

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
 

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double rating;	
	 
	
	public Company()
	{
		
	}
 

	

	public Double getRating() {
		return rating;
	}




	public void setRating(Double rating) {
		this.rating = rating;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 
	


}
