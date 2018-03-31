package com.SWEProject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.SWEProject.Entities.Brand;

public interface BrandRepository extends CrudRepository<Brand,String>{
	/*@Query("SELECT columnName ")
	public List<Object[]> findcol(@Param("columnName") String columnName);*/
}
