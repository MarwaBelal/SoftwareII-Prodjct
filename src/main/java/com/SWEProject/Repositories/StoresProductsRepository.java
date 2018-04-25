package com.SWEProject.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.SWEProject.Entities.Product;
import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.StoresProducts;


@RepositoryRestResource
public interface StoresProductsRepository extends CrudRepository<StoresProducts , Integer>{
	
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE StoresProducts p SET p.quantity = :quantity WHERE p.id = :id ")
	public void UpdateQuantity(@Param("quantity") int quantity, @Param("id") Integer id);
	
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE StoresProducts p SET p.price = :price WHERE p.id = :id ")
	public void UpdatePrice(@Param("price") double price, @Param("id") Integer id);
	
	public List <StoresProducts> findByStore(Store store);
	public StoresProducts findByStoreAndProduct(Store store, Product product);
	
	
	

	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE StoresProducts u SET u.numofbuys = :value WHERE u.id = :id ")
	public void UpdateNumofbuys(@Param("value") Integer value, @Param("id") Integer id);
	/*
	
	
	@Query("SELECT productname FROM StoresProducts WHERE quantity = 0 AND storename= :storename")
	public List<String>  findSoldOut(@Param("storename") String storename);
	
	
	@Query("SELECT numofbuys FROM StoresProducts WHERE storename= :storename")
	public List<Integer>  findnumOfBuys(@Param("storename") String storename);
	
	@Query("SELECT quantity FROM StoresProducts WHERE storename= :storename")
	public List<Integer>  findQuantity(@Param("storename") String storename);
	
	@Query("SELECT productname FROM StoresProducts WHERE storename = :storename AND quantity = 0")
	public List<Object>  soldoutProducts(@Param("storename") String storename);
	
	
	@Query("SELECT price FROM StoresProducts WHERE productname= :productname")
	public List<Double>  findprice(@Param("productname") String productname);
	
	@Query("SELECT price FROM StoresProducts WHERE storename = :storename")
	public List<Double>  findstoreprice(@Param("storename") String storename);
	
	*/
}
