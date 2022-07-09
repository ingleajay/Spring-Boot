package com.sboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sboot.models.Citizen;
import com.sboot.services.CitizenService;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
	
	@Autowired
	private CitizenService citizenService;
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Citizen>> add(@PathVariable Integer id){
		List<Citizen> c = citizenService.findByVcenterid(id);
		return new ResponseEntity<List<Citizen>>(c,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Citizen> add(@RequestBody Citizen citizen){
		Citizen c = citizenService.save(citizen);
		return new ResponseEntity<Citizen>(c,HttpStatus.OK);
	}
	
}
