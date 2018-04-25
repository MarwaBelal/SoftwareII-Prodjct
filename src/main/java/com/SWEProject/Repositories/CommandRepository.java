package com.SWEProject.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SWEProject.Entities.Command;


public interface CommandRepository extends CrudRepository<Command,Integer>{
	public List <Command> findByStorename(String storename);
}
