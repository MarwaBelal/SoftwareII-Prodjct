package com.SWEProject.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.SWEProject.Entities.StoresProducts;

public interface StoresProductsRepository extends CrudRepository<StoresProducts , Integer>{
	@Query("SELECT storename, productname FROM StoresProducts WHERE storename = :storename AND productname = :name")
	public List<Object[]>  find(@Param("storename") String storename, @Param("name") String name);
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE StoresProducts p SET p.quantity = :quantity WHERE p.storename = :name ")
	public void UpdateQuantity(@Param("quantity") int quantity, @Param("name") String name);
	/*
	@Query("SELECT id,storename,quantity,price,productname FROM StoresProducts WHERE id = :id ")
	public List<Object[]> findStoreProduct(@Param("id") int id);*/
	
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE StoresProducts u SET u.numofbuys = :value WHERE u.id = :id ")
	public void UpdateNumofbuys(@Param("value") Integer value, @Param("id") Integer id);
}
