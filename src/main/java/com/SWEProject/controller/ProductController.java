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
import com.SWEProject.Entities.Product;
import com.SWEProject.Entities.ProductTemplate;
import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.StoresProducts;
import com.SWEProject.Repositories.BrandRepository;
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
	@Autowired
	private BrandRepository repobrand;
	
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
		
		Product tmp=new Product();
		tmp=repo.findOne(name);
		StoresProducts sproduct=new StoresProducts();
		sproduct.setProductname(tmp.getName());
		sproduct.setStorename(storename);
		sproduct.setPrice(price, tmp.getPriceUpperRange(), tmp.getPriceLowerRange());
		sproduct.setQuantity(quantity);
		sproduct.setBrandname(brandname);
		boolean store_exists=repoStore.exists(storename);
		boolean brand_exists=repobrand.exists(brandname);
		List<Object[]> x=repoStorep.find(storename,name);
		if(x.size()==0) {
			if(store_exists==true && brand_exists==true) {
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
		Iterable <Product> productIterable=repo.findAll();
		ArrayList <Product> productlist = new ArrayList<Product>();
		for( Product product : productIterable )
		{
			productlist.add(product);
		}
		
		ArrayList <ProductTemplate> productsofStore = new ArrayList<ProductTemplate>();
		for(int i=0 ; i<storeproductlist.size() ; i++)
		{
			for(int j=0 ; j<productlist.size(); j++)
			{
				if(storeproductlist.get(i).getProductname().equals(productlist.get(j).getName()) && storeproductlist.get(i).getStorename().equals(name))
				{
					ProductTemplate tmp=new ProductTemplate();
					tmp.id=storeproductlist.get(i).getId();
					tmp.name=productlist.get(j).getName();
					tmp.category=productlist.get(j).getCategory();
					tmp.type=productlist.get(j).getType();
					tmp.price=storeproductlist.get(i).getPrice();
					if(productsofStore.contains(tmp)==false) {
						productsofStore.add(tmp);
					}
					break;
				}
			}
		}
		for(int i=0;i<productsofStore.size();i++) {
			System.out.println(productsofStore.get(i).name+"   "+productsofStore.get(i).price);
		}
		model.addAttribute("products1", productsofStore);
		
		if(UserController.currentUser.getType().equals("storeowner")) {
			return "ShowProductsOwner";
		}
		return "ShowProducts";
	}
}
