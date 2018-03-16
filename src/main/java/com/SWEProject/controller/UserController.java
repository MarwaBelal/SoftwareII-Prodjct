package com.SWEProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.SWEProject.Entities.Administrator;
import com.SWEProject.Entities.NormalUser;
import com.SWEProject.Entities.Product;
import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.StoreOwner;
import com.SWEProject.Entities.StoresProducts;
import com.SWEProject.Entities.User;
import com.SWEProject.Repositories.StoreRepository;
import com.SWEProject.Repositories.StoresProductsRepository;
import com.SWEProject.Repositories.UserRepository;

@Controller
@SessionAttributes("email")
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
	private StoreRepository repostore;
	@GetMapping("/Register")
	public String registerGet(Model model) {
		model.addAttribute("user", new User());
		return "Register";
	}

	@PostMapping("/Register")
	public String registerPost(Model model,@ModelAttribute User user) {
		List<Object> x=repo.existUser(user.getEmail());
		if(x.size()==0) {
			System.out.println(user.getType());
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
	public String show(@RequestParam ("email") String email, @RequestParam ("password") String pass ,ModelMap m) {
		List<Object[]> users=repo.findUser(email,pass);
		if(users.size()!=0) {
			for (int i=0 ; i<5 ; i++)
				System.out.println(users.get(0)[i]);
			currentUser=InitializeUser((String)users.get(0)[3]);
			currentUser.setUsername((String)users.get(0)[0]);
			currentUser.setEmail((String)users.get(0)[1]);
			currentUser.setPassword((String)users.get(0)[2]);
			currentUser.setType((String)users.get(0)[3]);
			currentUser.setId((Integer)users.get(0)[4]);
			currentUser.setMoney((Double)users.get(0)[5]);
		}
		if (currentUser.getType().equals("normaluser"))return "NormalUser";
		if (currentUser.getType().equals("administrator")) return "Admin";
		if (currentUser.getType().equals("storeowner")) return "StoreOwner";
			
		return "Login";
	}
	
	
	@GetMapping("/BuyVoucher")
	public String voucher() {
		return "BuyVoucher";
	}

	@RequestMapping("/BuyVoucher")
	public void BuyVoucher(@RequestParam ("money") double money,@RequestParam ("card") String card )
	{
				currentUser.setMoney(currentUser.getMoney()+money);
				System.out.println(currentUser.getMoney());
				repo.UpdateMoney(currentUser.getMoney(), currentUser.getEmail());
				System.out.println("empty ?? "+currentUser.getEmail());
				
			
		}
	@Autowired
	StoresProductsRepository Srepo;
	@GetMapping("/BuyProduct")
	public String product() {
		return "BuyProduct";
	}
	@PostMapping("/getStoreproduct")
	public String takestore(@RequestParam ("id") int id)
	{
		
		currsp=new StoresProducts();
		System.out.println("id    "+id);
		currsp=Srepo.findOne(id);
		System.out.println(currsp.getQuantity());
		System.out.println("ay haga");
			return "BuyProduct";	
	}

	@PostMapping("/BuyProduct")
	public void BuyProduct(@RequestParam ("amount") int amount,@RequestParam ("address") String adress )
	{
		   
			double total=0;
			int newQuantity=0;
			total = amount * currsp.getPrice();
			System.out.println(total);
			System.out.println(amount);
			System.out.println(currsp.getPrice());
			currentUser.setMoney(currentUser.getMoney()-total);
			repo.UpdateMoney(currentUser.getMoney(), currentUser.getEmail());
			newQuantity = currsp.getQuantity() - amount ;
			Srepo.UpdateQuantity(newQuantity, currsp.getStorename());
			currsp.setNumofbuys(currsp.getNumofbuys()+1);
			Srepo.UpdateNumofbuys(currsp.getNumofbuys(), currsp.getId());
	}
	@PostMapping("/ShowStatistics")
	public String viewStatistics(Model model ,@RequestParam ("name") String name)
	{

		Store s= repostore.findOne(name);
		model.addAttribute("store", s.getNumofviews());
		return "ShowNumofViews";
	}
	
	@PostMapping("/ShowStatistics2")
	public String viewStatistics2(Model model ,@RequestParam ("id") Integer id)
	{
		StoresProducts s= Srepo.findOne(id);
		model.addAttribute("s", s.getNumofbuys());
		return "ShowNumofBuys2";
	}
	
}


