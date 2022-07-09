package com.sboot.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sboot.dao.StudentDao;
import com.sboot.models.Student;

@Controller
@RequestMapping("/user")
public class StudentController {
	
	@Autowired
	private StudentDao studentDao;
	
	@ModelAttribute
	public void commondata(Model model , Principal principal) {
        String stuname = principal.getName();
        Student student = studentDao.getStudentByStuName(stuname);		
        model.addAttribute("student", student);
        System.out.println(student);
	}

	@RequestMapping("/index")
	public String studentindex(Model model , Principal principal) {
		 String stuname = principal.getName();	
		model.addAttribute("title","Dashboard");
		return "index";
	}
}
