
package com.SWEProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SWEProject.Entities.StoresProducts;
import com.SWEProject.Repositories.StatRepository;
import com.SWEProject.Repositories.StoresProductsRepository;
import com.SWEProject.Repositories.UserRepository;
@RestResource
public abstract class StatisticsController 

{
	
	
	
	/*@GetMapping("/calcStat")
	public String statGet(Model model,@RequestParam ("storename") String storename) {
		System.out.println("hi");
		model.addAttribute("store", new StoresProducts());
		return storename;
	}*/
	
	
	
	public abstract double sum(String s, String store, int id,StatRepository Srep,StoresProductsRepository repo,UserRepository Urepo) ;
	public abstract double avg(String s, String store, int id,StatRepository Srep,StoresProductsRepository repo,UserRepository Urepo) ;
	public abstract double max(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) ;
	public abstract double min(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) ;
		
	}
	



