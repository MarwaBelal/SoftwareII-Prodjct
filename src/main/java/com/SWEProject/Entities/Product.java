package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
	@Id
	@NotNull
	private String name;
	private String category;
	private String type;
	@NotNull
	private double priceUpperRange;
	@NotNull
	private double priceLowerRange;
	public int getNumOfBuy() {
		return numOfBuy;
	}
	public void setNumOfBuy(int numOfBuy) {
		this.numOfBuy = numOfBuy;
	}
	private int numOfBuy;
	
	public Product() {
		name="";
		category="";
		type="";
		priceUpperRange=0.0;
		priceLowerRange=0.0;
		numOfBuy=0;
	}
	public Product(String name, String category, String type, double priceUpperRange,
			double priceLowerRange) {
		this.name = name;
		this.category = category;
		this.type = type;
		this.priceUpperRange = priceUpperRange;
		this.priceLowerRange = priceLowerRange;
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
}
