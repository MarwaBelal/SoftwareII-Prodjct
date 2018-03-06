package com.SWEProject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SWEProject.Entities.User;
import com.SWEProject.Repositories.UserRepository;

@Controller

public class UserController {
	@Autowired
	private UserRepository repo;

	@GetMapping("/Register")
	public String registerGet(Model model) {
		model.addAttribute("user", new User());
		return "Register";
	}

	@PostMapping("/Register")
	public String registerPost(Model model, @ModelAttribute User user) {
		repo.save(user);
		model.addAttribute("user", new User());
		return "Register";
	}
	
	@GetMapping("/Login")
	public String getLogin(Model model) {
		model.addAttribute("user", new User());
		return "Login";
	}

	@PostMapping("/Login")
	public String show(Model model, @ModelAttribute User user) {
		Iterable<User> usersIterable = repo.findAll();
		ArrayList<User> usersList = new ArrayList<User>();
		for (User tmp : usersIterable) {
			usersList.add(tmp);
		}
		model.addAttribute("user", new User());
		for(int i=0;i<usersList.size();i++) {
			if(user.getEmail().equals(usersList.get(i).getEmail()) && user.getPassword().equals(usersList.get(i).getPassword())) {
				user=usersList.get(i);
				System.out.println(usersList.get(i).getUsername());
				System.out.println(user.getEmail());
				System.out.println(user.getPassword());
			}
		}
		
		return "Login";
	}
}
