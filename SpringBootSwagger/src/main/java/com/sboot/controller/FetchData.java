package com.sboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;

import com.sboot.models.College;
import com.sboot.models.Student;

@RestController
public class FetchData {

	@Autowired
	College college;
	
	@PostMapping(
			value="/college"
			)
	public ResponseEntity<College> getinfo(@RequestBody Student s){
		college.setCid(111);
		college.setClocation("Mumbai");
		college.setCname("VIT");
		college.setStuid(s.getSid());
		college.setStuname(s.getSname());
		return new ResponseEntity<>(college,HttpStatus.CREATED);
	}
}
