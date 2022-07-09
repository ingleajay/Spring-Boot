package com.codeouter.helper;

import com.codeouter.model.Order;

public class TransactionResponse {

	private Order order;
	private double amount;
	private String transactionid;
	
	private String message;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public TransactionResponse(Order order, double amount, String transactionid, String message) {
		super();
		this.order = order;
		this.amount = amount;
		this.transactionid = transactionid;
		this.message = message;
	}
	public TransactionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TransactionResponse [order=" + order + ", amount=" + amount + ", transactionid=" + transactionid
				+ ", message=" + message + "]";
	}

}
