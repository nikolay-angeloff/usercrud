package com.usercrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usercrud.entity.User;
import com.usercrud.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public Iterable<User> findAllUsers() {
        return repo.findAll();
    }

	public User findUser(Integer id) {
		return repo.findOne(id);
	}

	public void deleteUser(Integer id) {
		repo.delete(id);
	}

	public void saveUser(User user) {
		repo.save(user);
	}
}
