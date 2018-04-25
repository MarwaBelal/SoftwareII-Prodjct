package com.SWEProject.Repositories;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.SWEProject.Entities.Store;
import com.SWEProject.Entities.User;

public interface StoreRepository extends CrudRepository<Store,String>{
	
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Store u SET u.numofviews = :value WHERE u.name = :name ")
	public void UpdateNumofviews(@Param("value") Integer value, @Param("name") String name);
	
	public List <Store> findByOwner(User user);
	public List <Store> findByCollaborators(User user);
	
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Store s SET s.collaborators = :value WHERE s.name = :name ")
	public void UpdateCollborators(@Param("value") Set<User> value, @Param("name") String name);
}
