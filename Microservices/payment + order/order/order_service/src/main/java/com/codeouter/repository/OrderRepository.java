package com.codeouter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeouter.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
