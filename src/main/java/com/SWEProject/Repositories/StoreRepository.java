package com.SWEProject.Repositories;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.SWEProject.Entities.Store;

public interface StoreRepository extends CrudRepository<Store,String>{
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Store u SET u.numofviews = :value WHERE u.name = :name ")
	public void UpdateNumofviews(@Param("value") Integer value, @Param("name") String name);
}
