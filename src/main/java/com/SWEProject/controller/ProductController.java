package com.SWEProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SWEProject.Entities.Product;
import com.SWEProject.Repositories.ProductRepository;


@Controller
public class ProductController {
	@Autowired
	private ProductRepository repo;
	@GetMapping("/AddProduct")
	public String addproductG(Model model)
	{
		model.addAttribute("product", new Product());
		return "Addproduct";
	} 
	@PostMapping("/AddProduct")
	public String addproductP(Model model ,@ModelAttribute Product product)
	{
		repo.save(product);
		model.addAttribute("product", new Product());
		return "Addproduct";
	}

}
