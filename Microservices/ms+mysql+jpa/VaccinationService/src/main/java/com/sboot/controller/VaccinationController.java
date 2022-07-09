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
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sboot.models.Citizen;
import com.sboot.models.RestAPIResponse;
import com.sboot.models.Vaccination;
import com.sboot.services.VaccinationService;

@RestController
@RequestMapping("/center")
public class VaccinationController {
	
	@Autowired
	private VaccinationService vaccinationService;
	
	@Autowired
	private RestAPIResponse response;
	
	@Autowired
	private RestTemplate restTemplate;


	@PostMapping("/add")
	public ResponseEntity<Vaccination> add(@RequestBody Vaccination vaccination){
		Vaccination c = vaccinationService.save(vaccination);
		return new ResponseEntity<Vaccination>(c,HttpStatus.OK);
	}
	
	// id of vaccination center
	@GetMapping("/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizendowntime")
	public ResponseEntity<RestAPIResponse> getresp(@PathVariable int id){
		Vaccination  center = vaccinationService.findById(id).get(); 
		response.setVaccination(center);
		List<Citizen> citizens= restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/"+id, List.class);
		response.setCitizens(citizens);
		return new ResponseEntity<RestAPIResponse>(response,HttpStatus.OK);
	}
	
	public ResponseEntity<RestAPIResponse> handleCitizendowntime(@PathVariable int id){
		Vaccination  center = vaccinationService.findById(id).get(); 
		response.setVaccination(center);
		return new ResponseEntity<RestAPIResponse>(response,HttpStatus.OK);
	}
}
