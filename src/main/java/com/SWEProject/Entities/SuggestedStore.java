package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SuggestedStore {
	@Id
	@NotNull
	private String name;
	private String type;
	private String address;
	@NotNull
	private String link;
	@OneToOne
	@NotNull
	private User owner;
	public SuggestedStore() {
		name = "";
		type = "";
		address = "";
		link = "";
		this.owner=new User();
	}
	public SuggestedStore(String name, String type, String link, User owner) {
		this.name = name;
		this.type = type;
		this.link = link;
		this.owner=new User();
		this.owner = owner;
	}
	public SuggestedStore(String name, String type, String address, String link, User owner) {
		this.name = name;
		this.type = type;
		this.address = address;
		this.link = link;
		this.owner=new User();
		this.owner = owner;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
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
	
}
