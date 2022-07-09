package com.codeouter.helper;

import com.codeouter.model.Order;
import com.codeouter.model.Payment;

public class TransactionRequest {

	private Order order;
	private Payment payment;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public TransactionRequest(Order order, Payment payment) {
		super();
		this.order = order;
		this.payment = payment;
	}
	public TransactionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TransactionRequest [order=" + order + ", payment=" + payment + "]";
	}
	
	
}
