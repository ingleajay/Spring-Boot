package com.sboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sboot.models.User;
import com.sboot.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	public UserServices userServices;
	
	// get all users here
	
	@GetMapping("/")
	public List<User> getallusers(){
		return userServices.getallusers();
	}
	
	// return single user
	
	// this url is only for author role
	@PreAuthorize("hasRole('author')")
	@GetMapping("/{name}")
	public User getsingleuser(@PathVariable String name) {
		return userServices.getsingleuser(name);
	}
	
	
	// @request body is used to convert json intopojo format it means it's doing 
	// deserailization of object
	
	
	@PostMapping
	public User adduser(@RequestBody User user) {
		return this.userServices.addUser(user);
	}
	
}
