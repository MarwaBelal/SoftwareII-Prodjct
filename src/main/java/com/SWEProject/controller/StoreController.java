package com.SWEProject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SWEProject.Entities.Store;
import com.SWEProject.Repositories.StoreRepository;
import com.SWEProject.Repositories.SuggestedStoreRepository;

@Controller
public class StoreController {
	@Autowired
	private StoreRepository repo;
	private SuggestedStoreRepository repo1;
	@GetMapping("/AddStore")
	public String registerGet(Model model) {
		model.addAttribute("store",new Store());
		return "AddStore";
	}
	@PostMapping("/AddStore")
	public String addStore(Model model, @ModelAttribute Store store) {
		System.out.println(store.getName());
		repo1.save(store);
		model.addAttribute("store", new Store());
		return "AddStore";
	}
	@GetMapping("/AcceptStore")
	public String showSuggested(Model model) {
		Iterable <Store> storeIterable = repo1.findAll();
		ArrayList <Store> storeList  = new ArrayList<Store>();
		for (Store store : storeIterable) {
			storeList.add(store);
		}
		model.addAttribute("stores", storeList);
		return "AcceptStore";
	}
	@PostMapping("/AcceptStore")
	public String postSuggested(Model model, @RequestParam ("id") Integer id) {
		Iterable <Store> storeIterable = repo1.findAll();
		ArrayList <Store> storeList  = new ArrayList<Store>();
		for (Store store : storeIterable) {
			storeList.add(store);
		}
		model.addAttribute("stores", storeList);
		Store newStore = new Store();
		for (int i=0 ; i<storeList.size() ; i++)
		{
			if (storeList.get(i).getId()==id) {
				newStore=storeList.get(i);
			}
		}
		repo.save(newStore);
		return "AcceptStore";
	}
}
