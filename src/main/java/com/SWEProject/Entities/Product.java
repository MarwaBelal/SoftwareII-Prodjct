package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String category;
	private String type;
	private double priceUpperRange;
	private double priceLowerRange;
	private double price;
	public Product() {
		name="";
		category="";
		type="";
		priceUpperRange=0.0;
		priceLowerRange=0.0;
	}
	public Product(String name, String category, String type, double priceUpperRange,
			double priceLowerRange) {
		this.name = name;
		this.category = category;
		this.type = type;
		this.priceUpperRange = priceUpperRange;
		this.priceLowerRange = priceLowerRange;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPriceUpperRange() {
		return priceUpperRange;
	}
	public void setPriceUpperRange(double priceUpperRange) {
		this.priceUpperRange = priceUpperRange;
	}
	public double getPriceLowerRange() {
		return priceLowerRange;
	}
	public void setPriceLowerRange(double priceLowerRange) {
		this.priceLowerRange = priceLowerRange;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
