package com.jwt.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jwt.dao.UserRepository;
import com.jwt.pojo.User;

@Controller
public class MainController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/signin")
	public String login(Model m ) {
		m.addAttribute("title", "Login");
	
		return "signin";
	}
	
	@RequestMapping("/register")
	public String register(Model m) {
		m.addAttribute("title", "Register");
		 m.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping(value="/doregister", method = RequestMethod.POST)
	public String processofregister(@Valid @ModelAttribute("user") User user, BindingResult result, Model m , 
			HttpSession session ) {
		try {
			if(result.hasErrors()) {
				m.addAttribute("user",user);
				return "signup";
			}
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			this.userRepository.save(user);
			m.addAttribute("user", new User());
			session.setAttribute("message","Successfully register !! ");
		} catch (Exception e) {
			m.addAttribute("user",user);
			session.setAttribute("message",e.getMessage());
		}
		
		return "signup";
	}
	
	@RequestMapping("/welcome")
	public String Welcome(Model m , Principal principal) {
		String username = principal.getName();
		User user = userRepository.getUserByUserName(username);
		m.addAttribute("user", user);
		m.addAttribute("title","Welcome");
		return "welcome";
	}
}
