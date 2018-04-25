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

import com.SWEProject.Entities.NormalUser;
import com.SWEProject.Entities.Statistics;
import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.StoreOwner;
import com.SWEProject.Entities.StoresProducts;
import com.SWEProject.Entities.Administrator;
import com.SWEProject.Entities.User;
import com.SWEProject.Repositories.ProductRepository;
import com.SWEProject.Repositories.StatRepository;
import com.SWEProject.Repositories.StoreRepository;
import com.SWEProject.Repositories.StoresProductsRepository;
import com.SWEProject.Repositories.UserRepository;

@Controller

public class UserController {
	public static User currentUser;
	public static StoresProducts currsp;

	User InitializeUser(String t) {
		if (t.equals("normaluser")) {
			return new NormalUser();
		} else if (t.equals("storeowner")) {
			return new StoreOwner();
		} else if (t.equals("administrator")) {
			return new Administrator();
		}
		return null;
	}

	@Autowired
	private UserRepository repo;
	@Autowired
	StoresProductsRepository Srepo;
	@Autowired
	ProductRepository repoP;
	@Autowired
	StatRepository repostat;
	@Autowired
	StoreRepository storerepo;

	@GetMapping("/Register")
	public String registerGet(Model model) {
		model.addAttribute("user", new User());
		return "Register";
	}

	@PostMapping("/Register")
	public String registerPost(Model model, @ModelAttribute User user) {
		List<Object> x = repo.existUser(user.getEmail());
		if (x.size() == 0) {
			currentUser = InitializeUser(user.getType());
			repo.save(user);
			currentUser = repo.findOneByEmailAndPassword(user.getEmail(), user.getPassword());
			if (user.getType().equals("normaluser")) {
				return "NormalUser";
			} else if (user.getType().equals("storeowner")) {
				return "StoreOwner";
			} else if (user.getType().equals("administrator")) {
				return "Admin";
			}
		}
		model.addAttribute("user", new User());
		return "Register";
	}

	@GetMapping("/Login")
	public String getLogin() {
		return "Login";
	}

	@PostMapping("/Login")
	public String show(@RequestParam("email") String email, @RequestParam("password") String pass) {
		User tmp = repo.findOneByEmailAndPassword(email, pass);
		if (tmp != null) {
			currentUser = InitializeUser(tmp.getType());
			currentUser = tmp;
			if (currentUser.getType().equals("normaluser")) {
				if (currentUser.isCollabrated() == true) {
					return "Collabrate";
				}
				return "NormalUser";
			} else if (currentUser.getType().equals("storeowner")) {
				return "StoreOwner";
			} else if (currentUser.getType().equals("administrator")) {
				return "Admin";
			}
			currentUser.setNumoflogin(currentUser.getNumoflogin() + 1);
			repo.Updateloginnum(currentUser.getNumoflogin(), email);
		}
		return "Login";
	}

	@GetMapping("/AddCollaborator")
	public String Addcollabrateg(Model model) {
		Iterable<User> userIterable = repo.findAll();
		ArrayList<User> Userlist = new ArrayList<User>();
		for (User user : userIterable) {
			if (user.getType().equals("normaluser")) {
				Userlist.add(user);
			}
		}
		model.addAttribute("users", Userlist);
		List<Store> Storelist = storerepo.findByOwner(currentUser);
		model.addAttribute("stores", Storelist);
		return "AddCollaborator";
	}

	@PostMapping("/AddCollaborator")
	public String Addcollabratep(Model model, @RequestParam("userid") Integer id,
			@RequestParam("storename") String storename) {
		Store s = storerepo.findOne(storename);
		User u = repo.findOne(id);
		if (u.getType().equals("normaluser")) {
			u.setCollabrated(true);
			s.collaborators.add(u);
			repo.save(u);
			storerepo.save(s);
			return "StoreOwner";
		}
		return "AddCollaborator";
	}

	@GetMapping("/BuyVoucher")
	public String voucher() {
		return "BuyVoucher";
	}

	@PostMapping("/BuyVoucher")
	public String BuyVoucher(@RequestParam("money") double money, @RequestParam("card") String card) {
		currentUser.setMoney(currentUser.getMoney() + money);
		repo.UpdateMoney(currentUser.getMoney(), currentUser.getEmail());
		if (currentUser.getType().equals("normaluser")) {
			if (currentUser.isCollabrated() == true) {
				return "Collabrate";
			}
			return "NormalUser";
		} else if (currentUser.getType().equals("storeowner")) {
			return "StoreOwner";
		}
		return "BuyVouvher";
	}

	@GetMapping("/BuyProduct")
	public String product() {
		return "BuyProduct";
	}

	@PostMapping("/getStoreproduct")
	public String takestore(@RequestParam("id") Integer id) {
		currsp = new StoresProducts();
		currsp = Srepo.findOne(id);
		return "BuyProduct";
	}

	@PostMapping("/BuyProduct")
	public String BuyProduct(@RequestParam("amount") int amount, @RequestParam("address") String adress) {
		double total = 0;
		int newQuantity = 0;
		total = amount * currsp.getPrice();
		String currProductName;

		if (currentUser.getMoney() >= total && amount <= currsp.getQuantity()) {
			double offer = 0, discountValue;
			if (currentUser.getType().equals("storeowner")) {
				offer += 0.15;
			}
			if (amount == 2) {
				offer += 0.10;
			}
			boolean firstTime = true;
			for (User u : currsp.purchasers) {
				if (u.getId() == currentUser.getId()) {
					firstTime = false;
					break;
				}
			}
			if (firstTime == true) {
				offer += 0.05;
			}
			discountValue=total*offer;
			total =total-discountValue;
			currentUser.setMoney(currentUser.getMoney() - total);
			repo.UpdateMoney(currentUser.getMoney(), currentUser.getEmail());
			newQuantity = currsp.getQuantity() - amount;
			Srepo.UpdateQuantity(newQuantity, currsp.getId());
			currsp.setNumofbuys(currsp.getNumofbuys() + 1);
			currProductName = currsp.getProduct().getName();
			int x = repoP.TotalNUmOfBuys(currProductName);
			x += 1;
			repoP.updateNumOfBuys(x, currProductName);
			Srepo.UpdateNumofbuys(currsp.getNumofbuys(), currsp.getId());
			currsp.purchasers.add(currentUser);
			Srepo.save(currsp);
			if (currentUser.getType().equals("normaluser")) {
				if (currentUser.isCollabrated() == true) {
					return "Collabrate";
				}
				return "NormalUser";
			} else if (currentUser.getType().equals("storeowner")) {
				return "StoreOwner";
			}
		}
		return "BuyProduct";
	}

	@GetMapping("/AddStat")
	public String getStat(Model model) {
		model.addAttribute("statistics", new Statistics());
		return "AddStat";
	}

	@PostMapping("/AddStat")
	public String AddStatistics(Model model, @ModelAttribute Statistics s) {
		System.out.println("ayjhaaaaa");
		repostat.save(s);
		// model.addAttribute("statistics",new Statistics());
		// repostat.addd(s.getTable(), s.getColumn());

		System.out.println("ayjhaaaaa");
		return "AddStat";
	}
}
