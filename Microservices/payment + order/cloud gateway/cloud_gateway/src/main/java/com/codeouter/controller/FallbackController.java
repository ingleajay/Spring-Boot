package com.codeouter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping("/orderFallBack")
	public Mono<String> orderServicefallBack(){
		return Mono.just("Order Service is taking too long to reponde or is down , please try again later");
	}
	

	@RequestMapping("/paymentFallBack")
	public Mono<String> paymentServicefallBack(){
		return Mono.just("Payment Service is taking too long to reponde or is down , please try again later");
	}
}
