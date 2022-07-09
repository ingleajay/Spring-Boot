package com.jwt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JWTUtil;
import com.jwt.pojo.JWTRequest;
import com.jwt.pojo.JWTResponse;
import com.jwt.service.CustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class JWTController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value="/token" , method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad credentials");
		}
		UserDetails  userdetails= this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userdetails);
		System.out.println("JWT" + token);
		return ResponseEntity.ok(new JWTResponse(token));
	}
}
