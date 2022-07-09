package com.sboot.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sboot.dao.StudentDao;
import com.sboot.helper.Message;
import com.sboot.models.Student;

@Controller
public class HomeController {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	 @RequestMapping("/signin")
     public String signin(Model m) {
		 m.addAttribute("title", "Login");
	   	 return "signin";
     }
	 
	 @RequestMapping("/register")
	 public String signup(Model m) {
		 m.addAttribute("title", "Register");
		 m.addAttribute("student", new Student());
	   	 return "register";
	 }
	 
	// processing of register
			@RequestMapping(value="/doregister", method = RequestMethod.POST)
			public String processofregister(@Valid @ModelAttribute("student") Student student,BindingResult result,
					@RequestParam("profileimg") MultipartFile file, Model m , HttpSession session) {
				
				try {
					
					
					if(result.hasErrors()) {
						m.addAttribute("student",student);
						return "register";
					}
					if(file.isEmpty()) {
						System.out.println("file is empty");
						student.setImgurl("profile.png");
					}
					else {
						student.setImgurl(file.getOriginalFilename());
						File savefile = new ClassPathResource("static/img").getFile();
						Path path = Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
						Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
						System.out.println("Image is uploaded ");
					}
					
					student.setRole("ROLE_USER");
					student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
					Student stu = this.studentDao.save(student);
					m.addAttribute("student", new Student());
					session.setAttribute("message", new Message("alert-success","Successfully register !! "));

					
				} catch (Exception e) {
					e.printStackTrace();
					m.addAttribute("student",student);
					session.setAttribute("message", new Message("alert-danger","Something went wrong !! " + e.getMessage()));
				}
				return "register";
				
			}
	 
	 
}
