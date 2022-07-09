package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.dao.UserRepository;
import com.jwt.pojo.User;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.getUserByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Username is not found");
		}
		
		CustomUserDetail customUserDetails = new CustomUserDetail(user);
		
		return customUserDetails;
	}

}
