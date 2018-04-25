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

import com.SWEProject.Entities.Brand;
import com.SWEProject.Entities.Command;
import com.SWEProject.Entities.Product;
import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.StoresProducts;
import com.SWEProject.Entities.User;
import com.SWEProject.Repositories.BrandRepository;
import com.SWEProject.Repositories.CommandRepository;
import com.SWEProject.Repositories.ProductRepository;
import com.SWEProject.Repositories.StoreRepository;
import com.SWEProject.Repositories.StoresProductsRepository;


@Controller
public class ProductController {
	
	@Autowired
	public CommandRepository repocommand;
	@Autowired
	public ProductRepository repo;
	@Autowired
	public StoreRepository repoStore;
	@Autowired
	public StoresProductsRepository repoStorep;
	@Autowired
	public BrandRepository repobrand;
	StoresProducts currentProduct;
	public ProductController(){}
	
	@GetMapping("/test")
	public void test()
	{
		/*Product p=new Product("Bag","Bags","Bags",200.0,150.0);
		Brand b=new Brand("Nike","Ath");
		Store s= new Store("LC","Clothes","MallOfArabia","http:\\www.LC@..",(StoreOwner)UserController.currentUser);
		StoresProducts sp=new StoresProducts(s,p,200.0,100,b);*/
	} 
	
	@PostMapping("/getProductID")
	public String getstoreproductID(Model model,@RequestParam ("id") Integer id)
	{
		if(repoStorep.exists(id)) {
			currentProduct=repoStorep.findOne(id);
			model.addAttribute("storeproduct", currentProduct);
		}
		return "UpdateStoreProduct";
	} 
	
	@GetMapping("/UpdateStoreProduct")
	public String updateproductG(Model model){
		model.addAttribute("storeproduct", currentProduct);
		return "UpdateStoreProduct";
	}
	@PostMapping("/UpdateStoreProduct")
	public String updateproductP(Model model, @RequestParam ("quantity") String quantity, @RequestParam ("price") String price)
	{
		int q; //quantity
		double p; //price
		String description="Update product ("+currentProduct.getProduct().getName()+")";
		if(UserController.currentUser.getType().equals("storeowner")) {
			description+="you.";
		}
		else {
			description+=UserController.currentUser.getUsername()+".";
		}
		model.addAttribute("storeproduct", currentProduct);
		Command command=new Command(description,currentProduct.getStore().getName(),
				currentProduct.getProduct().getName(),currentProduct.getPrice(), 
				currentProduct.getQuantity(), currentProduct.getNumofbuys(),
				currentProduct.getBrand().getName());
		q=Integer.parseInt(quantity);
		currentProduct.setQuantity(q);
		p=Double.parseDouble(price);
		currentProduct.setPrice(p, currentProduct.getProduct().getPriceUpperRange(), 
					currentProduct.getProduct().getPriceLowerRange());
		repoStorep.UpdateQuantity(currentProduct.getQuantity(), currentProduct.getId());
		repoStorep.UpdatePrice(currentProduct.getPrice(),currentProduct.getId());
		repocommand.save(command);
		if(UserController.currentUser.isCollabrated()==true) {
			return "Collabrate";
		}
		
		return "StoreOwner";
	}
	
	@PostMapping("/DeleteProduct")
	public String deleteproductP(Model model,@RequestParam ("id") Integer id)
	{
		StoresProducts sp=repoStorep.findOne(id);
		if(repoStorep.exists(id)) {
			String description="Delete product ("+sp.getProduct().getName()+")";
			if(UserController.currentUser.getType().equals("storeowner")) {
				description+="you.";
			}
			else {
				description+=UserController.currentUser.getUsername()+".";
			}
			Command command=new Command(description,sp.getStore().getName(),sp.getProduct().getName(),
					sp.getPrice(), sp.getQuantity(), sp.getNumofbuys(),sp.getBrand().getName());
			repocommand.save(command);
			repoStorep.delete(sp.getId());
		}
		List <StoresProducts> storeproductList=repoStorep.findByStore(sp.getStore());
		model.addAttribute("products", storeproductList);
		return "ShowProductsOwner";
	}
	@GetMapping("/AddProductToStore")
	public String getAddProductToStore(Model model) {
		Iterable <Product> productIterable = repo.findAll();
		ArrayList <Product> productList  = new ArrayList<Product>();
		for (Product product : productIterable) {
			productList.add(product);
		}
		model.addAttribute("products", productList);
		Iterable <Brand> brandIterable = repobrand.findAll();
		ArrayList <Brand> brandList  = new ArrayList<Brand>();
		for (Brand brand : brandIterable) {
			brandList.add(brand);
		}
		model.addAttribute("brands", brandList);
		return "AddProductToStore";
	}
	@PostMapping("/AddProductToStore")
	public String postAddProductToStore(Model model, @RequestParam ("name") String name, 
			@RequestParam ("quantity") int quantity, @RequestParam ("price") double price , 
			@RequestParam ("storename") String storename, 
			@RequestParam ("brandname") String brandname) {
		
		Iterable <Product> productIterable = repo.findAll();
		ArrayList <Product> productList  = new ArrayList<Product>();
		for (Product product : productIterable) {
			productList.add(product);
		}
		model.addAttribute("products", productList);
		Iterable <Brand> brandIterable = repobrand.findAll();
		ArrayList <Brand> brandList  = new ArrayList<Brand>();
		for (Brand brand : brandIterable) {
			brandList.add(brand);
		}
		model.addAttribute("brands", brandList);
		Product tmp=repo.findOne(name);
		Store tmpStore=repoStore.findOne(storename);
		StoresProducts sproduct=new StoresProducts();
		sproduct.setProduct(tmp);
		sproduct.setStore(tmpStore);
		sproduct.setPrice(price, tmp.getPriceUpperRange(), tmp.getPriceLowerRange());
		sproduct.setQuantity(quantity);
		sproduct.setBrand(repobrand.findOne(brandname));
		boolean store_exists=repoStore.exists(storename);
		boolean brand_exists=repobrand.exists(brandname);
		boolean iscollaborator=false;
		for (User u : tmpStore.collaborators) {
		   if(u.getId()==UserController.currentUser.getId()) {
			   iscollaborator=true;
			   break;
		   }
		}
		StoresProducts x=repoStorep.findByStoreAndProduct(tmpStore, tmp);
		if(x==null) {
			if(store_exists==true && brand_exists==true && 
					(tmpStore.getOwner().getId()==UserController.currentUser.getId() ||
					iscollaborator==true)) {
				String description="Add product ("+name+") by ";
				if(UserController.currentUser.getType().equals("storeowner")) {
					description+="you.";
				}
				else {
					description+=UserController.currentUser.getUsername()+".";
				}
				repoStorep.save(sproduct);
				Command command=new Command(description,tmpStore.getName(),tmp.getName(),price, 
						quantity, 0,brandname);
				repocommand.save(command);
				if(UserController.currentUser.isCollabrated()==true) {
					return "Collabrate";
				}
				return "StoreOwner";
			}
		}
		return "AddProductToStore";
	}
	@PostMapping("/Undo")
	public String undoproductg(Model model, @RequestParam ("id") Integer id)
	{
		Command c=repocommand.findOne(id);
		Store s=repoStore.findOne(c.getStorename());
		Product p=repo.findOne(c.getProductname());
		boolean update=c.getDescription().contains("Update");
		boolean add=c.getDescription().contains("Add");
		boolean delete=c.getDescription().contains("Delete");
		if(update) {
			StoresProducts sp=repoStorep.findByStoreAndProduct(s, p);
			sp.setQuantity(c.getQuantity());
			sp.setPrice(c.getPrice(), c.getPrice()+1, c.getPrice()-1);
			repoStorep.UpdateQuantity(sp.getQuantity(), sp.getId());
			repoStorep.UpdatePrice(sp.getPrice(),sp.getId());
			repocommand.delete(id);
		}
		else if(add) {
			StoresProducts sp=repoStorep.findByStoreAndProduct(s, p);
			repoStorep.delete(sp.getId());
			repocommand.delete(id);
		}
		else if (delete) {
			Brand b=repobrand.findOne(c.getBrandname());
			StoresProducts sp=new StoresProducts(s,p,c.getPrice(),c.getQuantity(),b);
			sp.setNumofbuys(c.getNumofbuys());
			repoStorep.save(sp);
			repocommand.delete(id);
		}
		List<Command> commands=repocommand.findByStorename(c.getStorename());
		model.addAttribute("commands", commands);
		return "History";
	}
	@PostMapping("/DeleteCommand")
	public String deleteCommandp (Model model, @RequestParam ("id") Integer id) {
		Command c=repocommand.findOne(id);
		repocommand.delete(id);
		List<Command> commands=repocommand.findByStorename(c.getStorename());
		model.addAttribute("commands", commands);
		return "History";
	}
	@GetMapping("/AddProduct")
	public String addproductG(Model model)
	{
		model.addAttribute("product", new Product());
		return "AddProduct";
	} 
	@PostMapping("/AddProduct")
	public String addproductP(Model model ,@ModelAttribute Product product)
	{
		if(!repo.exists(product.getName())) {
			repo.save(product);
			model.addAttribute("product", new Product());
			return "Admin";
		}
		model.addAttribute("product", new Product());
		return "AddProduct";
	}
	@PostMapping("/ShowHistory")
	public String showHistoryG(Model model, @RequestParam ("name") String storename){
		List<Command> commands=repocommand.findByStorename(storename);
		model.addAttribute("commands", commands);
		return "History";
	}
	@GetMapping("/ShowProducts")
	public String ShowProducts(Model model , @RequestParam ("name") String name)
	{
		Store store= repoStore.findOne(name);
		if(UserController.currentUser.getType().equals("normaluser") && 
				UserController.currentUser.isCollabrated()==false) 
		{
			store.setNumofviews(store.getNumofviews()+1);
			repoStore.UpdateNumofviews(store.getNumofviews(),name);
		}
		List <StoresProducts> storeproductList=repoStorep.findByStore(store);
		model.addAttribute("products", storeproductList);
		if(UserController.currentUser.getType().equals("storeowner") ||
				UserController.currentUser.isCollabrated()==true) {
			return "ShowProductsOwner";
		}
		return "ShowProducts";
	}
}
