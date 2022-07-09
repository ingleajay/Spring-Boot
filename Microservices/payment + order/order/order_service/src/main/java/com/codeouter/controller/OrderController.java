package com.codeouter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeouter.helper.TransactionRequest;
import com.codeouter.helper.TransactionResponse;
import com.codeouter.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/bookorder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) {
		
		return orderService.saveorder(transactionRequest);
	}
}
