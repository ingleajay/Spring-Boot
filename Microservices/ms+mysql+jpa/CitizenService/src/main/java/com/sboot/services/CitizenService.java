package com.sboot.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sboot.models.Citizen;

public interface CitizenService extends JpaRepository<Citizen, Integer> {

	public List<Citizen> findByVcenterid(Integer id);
}
