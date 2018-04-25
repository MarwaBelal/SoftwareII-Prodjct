
package com.SWEProject.controller;

import org.springframework.data.rest.core.annotation.RestResource;


import com.SWEProject.Repositories.StatRepository;
import com.SWEProject.Repositories.StoresProductsRepository;
import com.SWEProject.Repositories.UserRepository;
@RestResource
public abstract class StatisticsController 
{
	public abstract double sum(String s, String store, int id,StatRepository Srep,StoresProductsRepository repo,UserRepository Urepo) ;
	public abstract double avg(String s, String store, int id,StatRepository Srep,StoresProductsRepository repo,UserRepository Urepo) ;
	public abstract double max(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) ;
	public abstract double min(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) ;
		
}
	



