package com.SWEProject.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class StoresProducts {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	@ManyToOne
	@NotNull
	private Store store;
	@OneToOne
	@NotNull
	private Product product;
	@NotNull
	private double price;
	@NotNull
	private int quantity;
	private Integer numofbuys;
	@OneToOne
	@NotNull
	private Brand brand;
	@ManyToMany(cascade= CascadeType.ALL)
	public Set<User> purchasers;
	public StoresProducts() {
		store = null;
		product =null;
		price=0;
		numofbuys=0;
		quantity=0;
		brand=null;
		purchasers=new HashSet<User>();
	}
	public StoresProducts(Store store, Product product, double price, int quantity, Brand brand) {
		this.store = store;
		this.product = product;
		this.price = price;
		this.quantity=quantity;
		this.brand=brand;
		numofbuys=0;
		purchasers=new HashSet<User>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
