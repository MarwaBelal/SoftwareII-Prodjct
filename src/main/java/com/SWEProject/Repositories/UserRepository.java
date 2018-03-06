package com.SWEProject.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.SWEProject.Entities.User;

public interface UserRepository extends CrudRepository<User,Integer>{

}
