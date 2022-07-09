package com.sboot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sboot.models.User;

@Service
public class UserServices {

	List<User> u = new ArrayList<>();

	public UserServices() {
		u.add(new User("ajay","ajay@123","ajay@gmail.com"));
		u.add(new User("vijay","vijay@123","vijay@gmail.com"));
	}
	
	// get all users
	public List<User> getallusers() {
		return this.u;
	}
	
	// get single user on the basis of name
	public User getsingleuser(String name) {
		return this.u.stream().filter((i) -> i.getUname().equals(name)).findAny().orElse(null);
	}
	
	
	// add new User
	public User addUser(User user) {
		this.u.add(user);
		return user;
	}
	
}
