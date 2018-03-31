package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StoresProducts {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	private String storename;
	private String productname;
	private double price;
	private int quantity;
	private Integer numofbuys;
	private String brandname; 
	public StoresProducts() {
		storename = "";
		productname = "";
		price=0;
		numofbuys=0;
		quantity=0;
	}
	public StoresProducts(String storename, String productname, double price, int quantity) {
		this.storename = storename;
		this.productname = productname;
		this.price = price;
		this.quantity=quantity;
		numofbuys=0;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
