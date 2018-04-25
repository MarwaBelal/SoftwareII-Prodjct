package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Command {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String description;
	private String storename;
	private String productname;
	private double price;
	private int quantity;
	private Integer numofbuys;
	private String brandname;
	public Command() {
		description = "";
		storename = "";
		productname = "";
		price = 0.0;
		quantity = 0;
		numofbuys = 0;
		brandname = "";
		
	}
	public Command(String description, String storename, String productname, double price, int quantity,
			Integer numofbuys, String brandname) {
		this.description = description;
		this.storename = storename;
		this.productname = productname;
		this.price = price;
		this.quantity = quantity;
		this.numofbuys = numofbuys;
		this.brandname = brandname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getNumofbuys() {
		return numofbuys;
	}
	public void setNumofbuys(Integer numofbuys) {
		this.numofbuys = numofbuys;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	
}
