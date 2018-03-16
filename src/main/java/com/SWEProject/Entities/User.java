package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	protected String username;
	protected String email;
	protected String password;
	protected String type;
	protected double money;
	public User(){
		username="";
		email="";
		password="";
		type="";
		money=0;
	}
	
	public String getType() {
		return type;
	}
	
	public double getMoney() {
		return money;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}

	public User(String username, String email, String password, String type) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
