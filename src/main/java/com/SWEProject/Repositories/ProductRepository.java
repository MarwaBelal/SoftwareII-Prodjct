package com.SWEProject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.SWEProject.Entities.Product;

public interface ProductRepository extends CrudRepository<Product,String>{
	/*@Query("SELECT columnName ")
	public List<Product> findcol(@Param("columnName") String columnName);
	*/
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Product p SET p.numOfBuy = :value WHERE p.name = :name ")
	public Product updateNumOfBuys(@Param("value") Integer value, @Param("name") String name);
	
	@Query("SELECT numOfBuy FROM Product WHERE name = :name")
	public int TotalNUmOfBuys (@Param("name") String name);
	
	@Query("SELECT productname FROM StoresProducts WHERE storename = :storename")
	public List<String> TotalNUmOfProducts (@Param("storename") String storename);
	
	@Query("SELECT price FROM StoresProducts WHERE storename = :storename")
	public List<Integer> TotalNUmOfPrices (@Param("storename") String storename);
	
	@Query("SELECT numofbuys FROM StoresProducts WHERE storename = :storename")
	public List<Integer> TotalNUmOfbuys (@Param("storename") String storename);
}
