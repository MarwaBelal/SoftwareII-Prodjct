package com.SWEProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SWEProject.Entities.Brand;
import com.SWEProject.Repositories.BrandRepository;

@Controller
public class BrandController {
	@Autowired
	private BrandRepository repo;
	@GetMapping("/AddBrand")
	public String addBrandG(Model model)
	{
		model.addAttribute("brand", new Brand());
		return "AddBrand";
	} 
	@PostMapping("/AddBrand")
	public String addBrandP(Model model ,@ModelAttribute  Brand brand)
	{
		boolean ifExist=repo.exists(brand.getName());
		if(ifExist==false) {
			repo.save(brand);
			return "Admin";
		}
		model.addAttribute("brand", new Brand());
		return "AddBrand";
	}
}
