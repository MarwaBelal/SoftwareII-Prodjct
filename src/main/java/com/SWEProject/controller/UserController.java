package com.SWEProject.controller;

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
		model.addAttribute("user",new User());
		return "Register";
	}
	@PostMapping("/Register")
	public String registerPost(Model model,@ModelAttribute User user) {
		System.out.println(user.getUsername());
		repo.save(user);
		model.addAttribute("user", new User());
		return "Register";
	}
}
