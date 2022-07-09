package com.sboot;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
	
 @RequestMapping(value="/about",method = RequestMethod.GET)	
 public String about(Model model) {
	System.out.println("Inside about");
	model.addAttribute("name","Ajay M Ingle");
	 List<String> names = List.of("Ajay","Vijay","Sonali");
	 model.addAttribute("i", names); 
	return "about";
 }
 
 @GetMapping("/condition")
 public String condition(Model model) {
	 model.addAttribute("isActive",true);
	 model.addAttribute("gender","M");
	 List<Integer> l = List.of(10,20);
	 model.addAttribute("mlist", l);
	 return "condition";
 }
 
 @GetMapping("/service")
 public String service(Model m) {
	 m.addAttribute("title", "Footer title given..");
	 return "services";
 }
 
 @GetMapping("/content1")
 public String content1() {
	 return "Content1";
 }
 
 @GetMapping("/content2")
 public String content2() {
	 return "Content2";
 }
}
