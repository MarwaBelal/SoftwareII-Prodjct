package com.SWEProject.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	@NotNull
	protected String username;
	@NotNull
	protected String email;
	@NotNull
	protected String password;
	@NotNull
	protected String type;
	protected double money;
	protected int numoflogin;
	protected boolean collabrated;
	public User(){
		username="";
		email="";
		password="";
		type="";
		money=0;
		numoflogin=1;
		collabrated=false;
	}
	public User(String username, String email, String password, String type, double money) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
		this.money=money;
		numoflogin=1;
		collabrated=false;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
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
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public boolean isCollabrated() {
		return collabrated;
	}
	public void setCollabrated(boolean collabrated) {
		this.collabrated = collabrated;
	}
	public int getNumoflogin() {
		return numoflogin;
	}
	public void setNumoflogin(int numoflogin) {
		this.numoflogin = numoflogin;
	}
}
