package com.usercrud.repository;

import org.springframework.data.repository.CrudRepository;

import com.usercrud.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
