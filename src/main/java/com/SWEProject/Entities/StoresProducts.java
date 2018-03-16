package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StoresProducts {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String productname;
	private int quantity; 
	private double price;
	private String storename;
	private Integer numofbuys;
	public Integer getNumofbuys() {
		return numofbuys;
	}

	public void setNumofbuys(Integer numofbuys) {
		this.numofbuys = numofbuys;
	}

	public StoresProducts() {
		storename = "";
		productname = "";
		price=0.0;
		quantity=0;
	}
	
	public StoresProducts(String storename, String productname, double price, int quantity) {
		this.storename = storename;
		this.productname = productname;
		this.price = price;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setquantity(Integer quantity) {
		this.quantity = quantity;
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

	public void setPrice(double price, double upper, double lower) {
		if(price>upper) {
			this.price = upper;
		}
		else if(price<lower) {
			this.price = lower;
		}
		else {
			this.price = price;
		}
	}
	
}
