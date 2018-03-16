package com.SWEProject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.StoreOwner;
import com.SWEProject.Entities.SuggestedStore;
import com.SWEProject.Repositories.StoreRepository;
import com.SWEProject.Repositories.SuggestedStoreRepository;

@Controller
public class StoreController {
	StoreOwner storeOwner;
	@Autowired
	private StoreRepository repo;
	@Autowired
	private SuggestedStoreRepository repoSug;
	@GetMapping("/AddStore")
	public String getAddStore(Model model) {
		model.addAttribute("suggestedstore",new SuggestedStore());
		return "AddStore";
	}
	@PostMapping("/AddStore")
	public String addStore(Model model, @ModelAttribute SuggestedStore suggestedstore) {
		storeOwner=(StoreOwner)UserController.currentUser;
		System.out.println(storeOwner.getUsername());
		if(!repoSug.exists(suggestedstore.getName()) && storeOwner.getType().equals("storeowner")) {
			suggestedstore.setOwnerid(storeOwner.getId());
			repoSug.save(suggestedstore);
			model.addAttribute("suggestedstore", new SuggestedStore());
			return "StoreOwner";
		}
		model.addAttribute("suggestedstore", new SuggestedStore());
		return "AddStore";
	}
	
	
	@GetMapping("/AcceptStore")
	public String showSuggested(Model model) {
		Iterable <SuggestedStore> storeIterable = repoSug.findAll();
		ArrayList <SuggestedStore> storeList  = new ArrayList<SuggestedStore>();
		for (SuggestedStore suggestedstore : storeIterable) {
			storeList.add(suggestedstore);
		}
		model.addAttribute("stores", storeList);
		return "AcceptStore";
	}
	@PostMapping("/AcceptStore")
	public String postSuggested(Model model, @RequestParam(value="name") String name) {
		Iterable <SuggestedStore> storeIterable = repoSug.findAll();
		ArrayList <SuggestedStore> storeList  = new ArrayList<SuggestedStore>();
		for (SuggestedStore suggestedstore : storeIterable) {
			storeList.add(suggestedstore);
		}
		model.addAttribute("stores", storeList);
		SuggestedStore tmp=new SuggestedStore();
		if(!repo.exists(name)) {
			tmp=repoSug.findOne(name);
			Store newStore = new Store(tmp.getName(),tmp.getType(),tmp.getAddress(),tmp.getLink(),tmp.getOwnerid());
			repo.save(newStore);
		}
		repoSug.delete(name);
		return "Admin";
	}
	
	@GetMapping("/Show-Stores")
	public String ShowStores(Model model)
	{
		Iterable <Store> storeIterable=repo.findAll();
		ArrayList <Store> Storelist = new ArrayList<Store>();
		for( Store store : storeIterable )
		{
			Storelist.add(store);
		}
		model.addAttribute("stores", Storelist);
		return "Show-Stores";
	}
	@GetMapping("/Show-Stores1")
	public String ShowStores1(Model model)
	{
		Iterable <Store> storeIterable=repo.findAll();
		ArrayList <Store> Storelist = new ArrayList<Store>();
		for( Store store : storeIterable )
		{
			Storelist.add(store);
		}
		
		UserController s= new UserController();
		ArrayList<Store>storeowner= new ArrayList<Store>();
		for( int i=0 ; i<Storelist.size() ; i++ )
		{
			if(s.currentUser.getId()==Storelist.get(i).getOwnerid())
			{
				storeowner.add(Storelist.get(i));
			}
		}
		model.addAttribute("stores1", storeowner);
		return "ShowStatistics";
	}
}
