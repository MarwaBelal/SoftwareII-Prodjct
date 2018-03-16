package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brand {
	@Id
	private String name;
	private String category;
	public Brand() {
		name = "";
		category = "";
	}
	public Brand(String name, String category) {
		this.name = name;
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
