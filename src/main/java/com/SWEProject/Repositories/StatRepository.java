package com.SWEProject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.SWEProject.Entities.Statistics;

public interface StatRepository extends CrudRepository<Statistics,Integer>
{
	
	
	@Query("SELECT tab, col, operation FROM Statistics WHERE id = :id ")
	public List<Object[]>  find(@Param("id") int id);
	
	//storesproducts
	
	
	
	
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Statistics u SET u.value = :value WHERE u.id = :id ")
	public void UpdateValue(@Param("value") double value, @Param("id") int id);
	
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Statistics u SET u.view = :value WHERE u.id = :id ")
	public void UpdateView(@Param("value") boolean value, @Param("id") int id);
	
	@Query("SELECT value FROM Statistics WHERE id = :id ")
	public double  findVal(@Param("id") int id);
	
	
	/*@Query("SELECT tab, col, operation FROM Statistics WHERE view= 1")
	public List<Object[]>  findture();*/
	
	
	
	
	/*
	@Modifying
	@Query("INSERT INTO Brand (name, category) select :value, :value2 from Brand")
	@Transactional
	public int addd(@Param ("value") String value, @Param ("value2") String value2);
	*/
	/*
	
	
	
	
	@Query("SELECT s FROM  Product ")
	public List<Object[]>  findColumnProduct(@Param("s") String s);
	
	@Query("SELECT numofviews FROM  Store ")
	public List<Integer> findColumnStore();

	@Query("SELECT numoflogin FROM  User ")
	public List<Integer> findColumnUser();*/

}
