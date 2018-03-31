package com.SWEProject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.SWEProject.Entities.User;

public interface UserRepository extends CrudRepository<User,Integer>
{
	
	@Query("SELECT username,email,password,type,id,money FROM User WHERE email = :name AND password = :pass")
	public List<Object[]> findUser(@Param("name") String name, @Param("pass") String pass);
	@Query("SELECT email FROM User WHERE email = :name")
	public List<Object> existUser(@Param("name") String name);
	
	@Transactional 
	@Modifying(clearAutomatically = true)
	@Query("UPDATE User u SET u.money = :value WHERE u.email = :mail ")
	public void UpdateMoney(@Param("value") double value, @Param("mail") String mail);
	
	/*@Query("SELECT columnName ")
	public List<Object[]> findcol(@Param("columnName") String columnName);*/
	
	@Query("UPDATE User u SET u.numoflogin = :value WHERE u.email = :mail ")
	public void Updateloginnum(@Param("value") double value, @Param("mail") String mail);
	
	@Query("SELECT numoflogin FROM User")
	public List<Integer> AllLogin();
	
	
	
}
