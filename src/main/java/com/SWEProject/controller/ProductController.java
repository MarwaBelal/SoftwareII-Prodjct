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

import com.SWEProject.Entities.Product;
import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.StoresProducts;
import com.SWEProject.Repositories.ProductRepository;
import com.SWEProject.Repositories.StoreRepository;
import com.SWEProject.Repositories.StoresProductsRepository;


@Controller
public class ProductController {
	@Autowired
	private ProductRepository repo;
	@Autowired
	private StoreRepository repoStore;
	@Autowired
	private StoresProductsRepository repoStorep;
	
	@GetMapping("/AddProduct")
	public String addproductG(Model model)
	{
		model.addAttribute("product", new Product());
		return "Addproduct";
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
		return "Addproduct";
	}
	
	@GetMapping("/AddProductToStore")
	public String showProducts(Model model) {
		Iterable <Product> productIterable = repo.findAll();
		ArrayList <Product> productList  = new ArrayList<Product>();
		for (Product product : productIterable) {
			productList.add(product);
		}
		model.addAttribute("products", productList);
		return "AddProductToStore";
	}
	@PostMapping("/AddProductToStore")
	public String postAddProductToStore(Model model, @RequestParam ("name") String name, @RequestParam ("quantity") int quantity, @RequestParam ("price") double price , @RequestParam ("storename") String storename) {
		Iterable <Product> productIterable = repo.findAll();
		ArrayList <Product> productList  = new ArrayList<Product>();
		for (Product product : productIterable) {
			productList.add(product);
		}
		model.addAttribute("products", productList);
		
		Product tmp=new Product();
		tmp=repo.findOne(name);
		StoresProducts sproduct=new StoresProducts();
		sproduct.setProductname(tmp.getName());
		sproduct.setStorename(storename);
		sproduct.setPrice(price, tmp.getPriceUpperRange(), tmp.getPriceLowerRange());
		sproduct.setquantity(quantity);
		boolean exists=repoStore.exists(storename);
		List<Object[]> x=repoStorep.find(storename,name);
		if(x.size()==0) {
			if(exists==true) {
				repoStorep.save(sproduct);
				return "StoreOwner";
			}
		}
		return "AddProductToStore";
	}
	
	@GetMapping("/ShowProducts")
	public String ShowProducts(Model model , @RequestParam ("name") String name)
	{
		
		Store store= repoStore.findOne(name);
		store.setNumofviews(store.getNumofviews()+1);
		repoStore.UpdateNumofviews(store.getNumofviews(),name);
		Iterable <StoresProducts> storeproductIterable=repoStorep.findAll();
		ArrayList <StoresProducts> storeproductlist = new ArrayList<StoresProducts>();
		for( StoresProducts storeproduct : storeproductIterable )
		{
			storeproductlist.add(storeproduct);
		}

		System.out.println(storeproductlist.size());
		model.addAttribute("storeproducts", storeproductlist);
		Iterable <Product> productIterable=repo.findAll();
		ArrayList <Product> productlist = new ArrayList<Product>();
		for( Product product : productIterable )
		{
			productlist.add(product);
		}

		System.out.println(productlist.size());
		model.addAttribute("products", productlist);
		ArrayList <StoresProducts> storeproductlist1 = new ArrayList<StoresProducts>();
		for(int i=0 ; i<storeproductlist.size() ; i++)
		{
			if(storeproductlist.get(i).getStorename().equals(name))
				storeproductlist1 .add(	storeproductlist.get(i));	
		}
		model.addAttribute("products2", storeproductlist1);
		ArrayList <Product> productlist1 = new ArrayList<Product>();
		for(int i=0 ; i<storeproductlist1.size() ; i++)
		{
			for(int j=0 ; j<productlist.size(); i++)
			{
				if(storeproductlist1.get(i).getProductname().equals(productlist.get(j).getName()))
				{
					productlist1.add(productlist.get(j));
					break;
				}
			}
		}
		System.out.println(productlist1.size());

		System.out.println(storeproductlist1.size());
		model.addAttribute("products1", productlist1);
		return "ShowProducts";
	}
	
	
	@GetMapping("/ShowProducts1")
	public String ShowProducts1(Model model , @RequestParam ("name") String name)
	{
		
		Store store= repoStore.findOne(name);
		store.setNumofviews(store.getNumofviews()+1);
		repoStore.UpdateNumofviews(store.getNumofviews(),name);
		Iterable <StoresProducts> storeproductIterable=repoStorep.findAll();
		ArrayList <StoresProducts> storeproductlist = new ArrayList<StoresProducts>();
		for( StoresProducts storeproduct : storeproductIterable )
		{
			storeproductlist.add(storeproduct);
		}

		System.out.println(storeproductlist.size());
		model.addAttribute("storeproducts", storeproductlist);
		Iterable <Product> productIterable=repo.findAll();
		ArrayList <Product> productlist = new ArrayList<Product>();
		for( Product product : productIterable )
		{
			productlist.add(product);
		}

		System.out.println(productlist.size());
		model.addAttribute("products", productlist);
		ArrayList <StoresProducts> storeproductlist1 = new ArrayList<StoresProducts>();
		for(int i=0 ; i<storeproductlist.size() ; i++)
		{
			if(storeproductlist.get(i).getStorename().equals(name))
				storeproductlist1 .add(	storeproductlist.get(i));	
		}
		model.addAttribute("products2", storeproductlist1);
		ArrayList <Product> productlist1 = new ArrayList<Product>();
		for(int i=0 ; i<storeproductlist1.size() ; i++)
		{
			for(int j=0 ; j<productlist.size(); i++)
			{
				if(storeproductlist1.get(i).getProductname().equals(productlist.get(j).getName()))
				{
					productlist1.add(productlist.get(j));
					break;
				}
			}
		}
		System.out.println(productlist1.size());

		System.out.println(storeproductlist1.size());
		model.addAttribute("products1", productlist1);
		return "ShowProductsOwner";
	}


}
