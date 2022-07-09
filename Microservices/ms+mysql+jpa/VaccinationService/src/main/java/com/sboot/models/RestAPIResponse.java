package com.sboot.models;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RestAPIResponse {

	private Vaccination vaccination;
	private List<Citizen> citizens;
	public Vaccination getVaccination() {
		return vaccination;
	}
	public void setVaccination(Vaccination vaccination) {
		this.vaccination = vaccination;
	}
	public List<Citizen> getCitizens() {
		return citizens;
	}
	public void setCitizens(List<Citizen> citizens) {
		this.citizens = citizens;
	}
	public RestAPIResponse(Vaccination vaccination, List<Citizen> citizens) {
		super();
		this.vaccination = vaccination;
		this.citizens = citizens;
	}
	public RestAPIResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
