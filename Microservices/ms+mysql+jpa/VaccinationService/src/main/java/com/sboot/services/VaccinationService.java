package com.sboot.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sboot.models.Vaccination;

public interface VaccinationService extends JpaRepository<Vaccination, Integer> {

}
