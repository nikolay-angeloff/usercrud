package com.usercrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.usercrud.entity.User;
import com.usercrud.service.UserService;

@RestController
@RequestMapping("/api/")
public class ApiController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
    public @ResponseBody Iterable<User> listUsers() {
        return userService.findAllUsers();
    }
	
	@GetMapping("/view/{id}")
    public @ResponseBody User viewUser(@PathVariable("id") Integer id) {
        return userService.findUser(id);
    }
	
	@PostMapping("/save/")
	@ResponseStatus(value = HttpStatus.OK)
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }
	
	@DeleteMapping("/remove/{id}")
	@ResponseStatus(value = HttpStatus.OK)
    public void removeUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }
}
