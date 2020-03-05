package com.ali.hyacinth.ims.shared.dto;

public class TransactionDTO {
	
	private double totalAmount;
	private double amountPaid;
	private double amoundUnpaid;
	private String date;
	private long id;
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public double getAmoundUnpaid() {
		return amoundUnpaid;
	}
	public void setAmoundUnpaid(double amoundUnpaid) {
		this.amoundUnpaid = amoundUnpaid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
