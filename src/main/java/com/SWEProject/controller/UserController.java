package com.SWEProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SWEProject.Entities.NormalUser;
import com.SWEProject.Entities.Statistics;
import com.SWEProject.Entities.StoreOwner;
import com.SWEProject.Entities.StoresProducts;
import com.SWEProject.Entities.Administrator;
import com.SWEProject.Entities.User;
import com.SWEProject.Repositories.ProductRepository;
import com.SWEProject.Repositories.StatRepository;
import com.SWEProject.Repositories.StoresProductsRepository;
import com.SWEProject.Repositories.UserRepository;

@Controller

public class UserController {
	public static  User currentUser;
	public static StoresProducts currsp;
	User InitializeUser(String t) {
		if(t.equals("normaluser")) {
			return new NormalUser();
		}
		else if(t.equals("storeowner")) {
			return new StoreOwner();
		}
		else if(t.equals("administrator")) {
			return new Administrator();
		}
		return null;
	}
	
	@Autowired
	private UserRepository repo;
	@Autowired
	StoresProductsRepository Srepo;
	
	@GetMapping("/Register")
	public String registerGet(Model model) {
		model.addAttribute("user", new User());
		return "Register";
	}

	@PostMapping("/Register")
	public String registerPost(Model model,@ModelAttribute User user) {
		List<Object> x=repo.existUser(user.getEmail());
		if(x.size()==0) {
			currentUser=InitializeUser(user.getType());
			currentUser.setUsername(user.getUsername());
			currentUser.setEmail(user.getEmail());
			currentUser.setPassword(user.getPassword());
			currentUser.setType(user.getType());
			repo.save(user);
			List<Object[]> users=repo.findUser(user.getEmail(),user.getPassword());
			currentUser.setId((Integer)users.get(0)[4]);
			if(user.getType().equals("normaluser")) {
				return "NormalUser";
			}
			else if(user.getType().equals("storeowner")) {
				return "StoreOwner";
			}
			else if(user.getType().equals("administrator")) {
				return "Admin";
			}
		}
		model.addAttribute("user",new User());
		return "Register";
	}
	
	@GetMapping("/Login")
	public String getLogin() {
		return "Login";
	}

	@PostMapping("/Login")
	public String show(@RequestParam ("email") String email, @RequestParam ("password") String pass) {
		List<Object[]> users=repo.findUser(email,pass);
		if(users.size()!=0) 
		{
			currentUser=InitializeUser((String)users.get(0)[3]);
			currentUser.setUsername((String)users.get(0)[0]);
			currentUser.setEmail((String)users.get(0)[1]);
			currentUser.setPassword((String)users.get(0)[2]);
			currentUser.setType((String)users.get(0)[3]);
			currentUser.setId((Integer)users.get(0)[4]);
			currentUser.setMoney((Double)users.get(0)[5]);
			if(currentUser.getType().equals("normaluser")) {
				return "NormalUser";
			}
			else if(currentUser.getType().equals("storeowner")) {
				return "StoreOwner";
			}
			else if(currentUser.getType().equals("administrator")) {
				return "Admin";
			}
			currentUser.setNumoflogin(currentUser.getNumoflogin()+1);
			repo.Updateloginnum(currentUser.getNumoflogin(), email);
		}
		return "Login";
	}
	
	
	@GetMapping("/BuyVoucher")
	public String voucher() {
		return "BuyVoucher";
	}

	@PostMapping("/BuyVoucher")
	public String BuyVoucher(@RequestParam ("money") double money,@RequestParam ("card") String card )
	{
		currentUser.setMoney(currentUser.getMoney()+money);
		repo.UpdateMoney(currentUser.getMoney(), currentUser.getEmail());
		if(currentUser.getType().equals("normaluser")) {
			return "NormalUser";
		}
		else if(currentUser.getType().equals("storeowner")) {
			return "StoreOwner";
		}
		return "BuyVouvher";
	}
	
	@GetMapping("/BuyProduct")
	public String product() {
		return "BuyProduct";
	}
	@PostMapping("/getStoreproduct")
	public String takestore(@RequestParam ("id") Integer id)
	{
		currsp=new StoresProducts();
		currsp=Srepo.findOne(id);
		return "BuyProduct";	
	}
	
	@Autowired
	ProductRepository repoP;

	@PostMapping("/BuyProduct")
	public String BuyProduct(@RequestParam ("amount") int amount,@RequestParam ("address") String adress )
	{
			double total=0;
			int newQuantity=0;
			total = amount * currsp.getPrice();
			String currProductName;
			
			if(currentUser.getMoney()>=total && amount<=currsp.getQuantity()) {
				currentUser.setMoney(currentUser.getMoney()-total);
				repo.UpdateMoney(currentUser.getMoney(), currentUser.getEmail());
				newQuantity = currsp.getQuantity() - amount ;
				Srepo.UpdateQuantity(newQuantity,currsp.getId());
				currsp.setNumofbuys(currsp.getNumofbuys()+1);
		        currProductName=currsp.getProductname();
		        int x =repoP.TotalNUmOfBuys(currProductName);
		        x+=1;
		        repoP.updateNumOfBuys(x, currProductName);
				Srepo.UpdateNumofbuys(currsp.getNumofbuys(), currsp.getId());
				if(currentUser.getType().equals("normaluser")) {
					return "NormalUser";
				}
				else if(currentUser.getType().equals("storeowner")) {
					return "StoreOwner";
				}
			}
			return "BuyProduct";
	}

	@Autowired
	StatRepository repostat;
	
	@GetMapping("/AddStat")
	public String getStat(Model model) {
		model.addAttribute("statistics",new Statistics());
		return "AddStat";
	}

  @PostMapping("/AddStat")
public String AddStatistics (Model model, @ModelAttribute Statistics s)
{
	System.out.println("ayjhaaaaa");
	    repostat.save(s);
		//model.addAttribute("statistics",new Statistics());
	//repostat.addd(s.getTable(), s.getColumn());

		System.out.println("ayjhaaaaa");
		return "AddStat";
}
}
