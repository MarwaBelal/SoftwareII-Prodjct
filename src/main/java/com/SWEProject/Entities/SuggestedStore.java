package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SuggestedStore {
	@Id
	private String name;
	private String type;
	private String address;
	private String link;
	private Integer ownerid;
	public SuggestedStore() {
		name = "";
		type = "";
		address = "";
		link = "";
	}
	public SuggestedStore(String name, String type, String link, Integer ownerid) {
		super();
		this.name = name;
		this.type = type;
		this.link = link;
		this.ownerid = ownerid;
	}
	public SuggestedStore(String name, String type, String address, String link, Integer ownerid) {
		super();
		this.name = name;
		this.type = type;
		this.address = address;
		this.link = link;
		this.ownerid = ownerid;
	}
	public Integer getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(Integer ownerid) {
		this.ownerid = ownerid;
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
