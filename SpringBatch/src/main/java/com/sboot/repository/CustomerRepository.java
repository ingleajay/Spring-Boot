package com.sboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sboot.entity.Customer;

public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
}
