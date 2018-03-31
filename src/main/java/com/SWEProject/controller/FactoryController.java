package com.SWEProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SWEProject.Entities.Statistics;
import com.SWEProject.Repositories.StatRepository;
import com.SWEProject.Repositories.StoresProductsRepository;
import com.SWEProject.Repositories.UserRepository;
@Controller
public class FactoryController {
	
	
	@Autowired
	public StatRepository Srepo;
	@Autowired
	public StoresProductsRepository repo;
	@Autowired
	UserRepository Urepo;
	
	@PostMapping("/RemoveStat")
	public String Removefrompanel(Model model , @RequestParam ("id") Integer id  )
	{
		Srepo.UpdateView(false, id);
		return "StoreOwner";
	}
	
	@PostMapping("/Add-Stat")
	public String Addtopanel(Model model ,@RequestParam ("id") Integer id )
	{
		Srepo.UpdateView(true, id);
		//Statistics s= Srepo.findOne(id);
		//s.setView(true);
		return "StoreOwner";
	}

@PostMapping("/Show-Stat")
	public String ShowStat(Model model,Model model2)
	{
		
		Iterable <Statistics> statIterable=Srepo.findAll();
		ArrayList <Statistics> Statlist1 = new ArrayList<Statistics>();
		for( Statistics Stat : statIterable )
		{
			Statlist1.add(Stat);
		}
		ArrayList <Statistics> Statlist = new ArrayList<Statistics>();
		ArrayList <Statistics> Statlist2 = new ArrayList<Statistics>();
		for (int i =0 ; i<Statlist1.size() ; i++)
		{
			if (Statlist1.get(i).isView()==false) 
				{
				Statlist.add(Statlist1.get(i));
				}
			else
			{Statlist2.add(Statlist1.get(i));}
		}
		model.addAttribute("Statsadd", Statlist);
		model.addAttribute("Statsremove", Statlist2);
		return "Show-StatOwner";
	}
	
	@PostMapping("/calcStat")
	public String viewStat(Model model,@RequestParam ("id") int id,@RequestParam ("storename") String storename/*,@ModelAttribute StoresProducts store*/) {

		List<Object[]> stat= new ArrayList<Object[]>() ;
		stat = Srepo.find(id);
		ProductStatController p = new ProductStatController();
		UserStatController u = new UserStatController();
		if (stat.get(0)[2].equals("sum"))
		{
			double sum=0;
			if (stat.get(0)[0].equals("User"))
			{
				sum=u.sum((String)stat.get(0)[1], storename,id,Srepo,repo,Urepo);
				model.addAttribute("s", sum);
			}
			else if(stat.get(0)[0].equals("Product"))
			{
				sum=p.sum((String)stat.get(0)[1], storename,id,Srepo,repo,Urepo);
				model.addAttribute("s", sum);
			}
			return "ShowNumofBuys";
		}
		else if (stat.get(0)[2].equals("avg"))
		{
			double avg = 0;
			if (stat.get(0)[0].equals("User"));
			//user.avg(column);
			else if(stat.get(0)[0].equals("Product"))
			{
				avg=p.avg((String)stat.get(0)[1], storename,id,Srepo,repo,Urepo);
				model.addAttribute("s", avg);
			}
			return "ShowNumofBuys";
		}
		if (stat.get(0)[2].equals("min"))
		{
			double minimum=0;
			if (stat.get(0)[0].equals("User"))
			{
				minimum=u.min((String)stat.get(0)[1], storename, id, Srepo, repo, Urepo);
				model.addAttribute("s", minimum);
			}
			else if(stat.get(0)[0].equals("Product"))
			{
				minimum=p.min((String)stat.get(0)[1], storename, id, Srepo, repo, Urepo);
				model.addAttribute("s", minimum);
			}
			return "ShowNumofBuys";
		}
		if (stat.get(0)[2].equals("max"))
		{
			double maximum=0;
			if (stat.get(0)[0].equals("User"))
			{
				maximum=u.max((String)stat.get(0)[1], storename, id, Srepo, repo, Urepo);
				model.addAttribute("s", maximum);
			}
			else if(stat.get(0)[0].equals("Product"))
			{
				maximum=p.max((String)stat.get(0)[1], storename, id, Srepo, repo, Urepo);
				model.addAttribute("s", maximum);
			}
			return "ShowNumofBuys";
		}
		return "ShowNumofBuys";
	}
}
