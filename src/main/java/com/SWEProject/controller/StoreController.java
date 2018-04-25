package com.SWEProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.SWEProject.Entities.Statistics;
import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.StoreOwner;

import com.SWEProject.Entities.SuggestedStore;
import com.SWEProject.Entities.User;
import com.SWEProject.Repositories.StatRepository;
import com.SWEProject.Repositories.StoreRepository;

import com.SWEProject.Repositories.SuggestedStoreRepository;

@Controller
public class StoreController {
	@Autowired
	private StoreRepository repo;
	@Autowired
	private SuggestedStoreRepository repoSug;
	@Autowired
	private StatRepository Statrepo;
	@GetMapping("/AddStore")
	public String getAddStore(Model model) {
		model.addAttribute("suggestedstore",new SuggestedStore());
		return "AddStore";
	}
	@PostMapping("/AddStore")
	public String addStore(Model model, @ModelAttribute SuggestedStore suggestedstore) {
		if(!repoSug.exists(suggestedstore.getName()) && UserController.currentUser.getType().equals("storeowner")) {
			suggestedstore.setOwner(UserController.currentUser);
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
	public String postSuggested(Model model, @RequestParam ("name") String name) {
		Iterable <SuggestedStore> storeIterable = repoSug.findAll();
		ArrayList <SuggestedStore> storeList  = new ArrayList<SuggestedStore>();
		for (SuggestedStore suggestedstore : storeIterable) {
			storeList.add(suggestedstore);
		}
		model.addAttribute("stores", storeList);
		SuggestedStore tmp=new SuggestedStore();
		if(!repo.exists(name)) {
			tmp=repoSug.findOne(name);
			Store newStore = new Store(tmp.getName(),tmp.getType(),tmp.getAddress(),tmp.getLink(),tmp.getOwner());
			repo.save(newStore);
		}
		repoSug.delete(name);
		return "Admin";
	}
	
	@GetMapping("/Show-Stores")
	public String ShowStores(Model model)
	{
		if(UserController.currentUser.getType().equals("storeowner")) {
			List <Store> stores=repo.findByOwner(UserController.currentUser);
			model.addAttribute("stores", stores);
			return "ShowStatistics";
		}
		else if(UserController.currentUser.isCollabrated()==true)
		{
			List <Store> stores=repo.findByCollaborators(UserController.currentUser);
			model.addAttribute("stores", stores);
			return "ShowStoresCollaborator";
		}
		Iterable <Store> storeIterable=repo.findAll();
		ArrayList <Store> Storelist = new ArrayList<Store>();
		for( Store store : storeIterable )
		{
			Storelist.add(store);
		}
		model.addAttribute("stores", Storelist);
		return "Show-Stores";
	}
	
	@Autowired
	StatRepository statrepo;
	
	@PostMapping("/ShowStatistics")
	public String viewStatistics(Model model/* ,@RequestParam ("name") String name*/)
	{
		/*Store s= repo.findOne(name);
		model.addAttribute("store", s.getNumofviews());
		List<Object> SOProduct=Srepo.soldoutProducts(name);
		ArrayList<Product> SOProductList=new ArrayList<Product>();
		Product tmp=new Product();
		for(int i=0;i<SOProduct.size();i++) {
			tmp.setName((String)SOProduct.get(i));
			SOProductList.add(tmp);
		}
		model.addAttribute("SOProducts", SOProductList);
		return "ViewsSoldout";*/
		Iterable <Statistics> statisticsIterable = statrepo.findAll();
		ArrayList <Statistics> statisticslist1 = new ArrayList<Statistics>();
		for (Statistics stat : statisticsIterable) {
			statisticslist1.add(stat);
		}
		ArrayList <Statistics> statisticslist = new ArrayList<Statistics>();
		for (int i=0 ; i< statisticslist1.size() ; i++)
		{
			if (statisticslist1.get(i).isView())
			{
				statisticslist.add(statisticslist1.get(i));
			}
		}
		model.addAttribute("stat", statisticslist);
		return "showProductStat";
		
	}
	
	@PostMapping("/ShowStatistics2")
	public String viewStatistics2(Model model ,@RequestParam ("id") Integer id)
	{
		double s= Statrepo.findVal(id);
		model.addAttribute("s", s);
		return "ShowNumofBuys";
	}
}
