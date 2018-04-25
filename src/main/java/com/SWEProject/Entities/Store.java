package com.SWEProject.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Store {
	@Id
	private String name;
	private String type;
	private String address;
	@NotNull
	private String link;
	@OneToOne
	@NotNull
	private User owner;
	private Integer numofviews;
	@OneToMany(cascade= CascadeType.ALL)
	public Set<StoresProducts> products;
	@ManyToMany(cascade= CascadeType.ALL)
	public Set<User> collaborators;
	public Store() { 
		owner=new User();
		name = "";
		type = "";
		address = "";
		link = "";
		numofviews=0;
		products=new HashSet<StoresProducts>();
		collaborators=new HashSet<User>();
	}
	
	public Store(String name, String type, String link, User owner) {
		this.name = name;
		this.type = type;
		this.link = link;
		this.owner=new User();
		this.owner = owner;
		this.numofviews=0;
		products=new HashSet<StoresProducts>();
		collaborators=new HashSet<User>();
	}

	public Store(String name, String type, String address, String link, User owner) {
		this.name = name;
		this.type = type;
		this.address = address;
		this.link = link;
		this.owner=new User();
		this.owner = owner;
		this.numofviews=0;
		products=new HashSet<StoresProducts>();
		collaborators=new HashSet<User>();
	}

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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public User getOwner() {
		return owner;
	}

	public void setUser(User owner) {
		this.owner = owner;
	}

	public Integer getNumofviews() {
		return numofviews;
	}

	public void setNumofviews(Integer numofviews) {
		this.numofviews = numofviews;
	}
	
}
