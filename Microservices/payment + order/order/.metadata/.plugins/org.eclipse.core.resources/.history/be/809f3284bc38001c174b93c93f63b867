package com.codeouter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="PAYMNET_TB")
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int paymentId;
	
	private String paymentStatus;
	private String transactionId;
	
	private int orderid;
	private int amount;
	
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Payment(int paymentId, String paymentStatus, String transactionId, int orderid, int amount) {
		super();
		this.paymentId = paymentId;
		this.paymentStatus = paymentStatus;
		this.transactionId = transactionId;
		this.orderid = orderid;
		this.amount = amount;
	}
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentStatus=" + paymentStatus + ", transactionId="
				+ transactionId + ", orderid=" + orderid + ", amount=" + amount + "]";
	}
	
}
