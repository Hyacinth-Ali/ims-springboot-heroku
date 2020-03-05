package com.ali.hyacinth.ims.shared.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Receipt {
	
	//Receipt Attributes
	  private String date;
	  private double totalAmount;
	  private double amoundPaid;

	  //Receipt Associations
	  private List<ProductTransactionDTO> pTransactions;

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------

	  public Receipt()
	  {
	    date = null;
	    totalAmount = 0;
	    amoundPaid = 0;
	    pTransactions = new ArrayList<ProductTransactionDTO>();
	  }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getAmoundPaid() {
		return amoundPaid;
	}

	public void setAmoundPaid(double amoundPaid) {
		this.amoundPaid = amoundPaid;
	}

	public List<ProductTransactionDTO> getpTransactions() {
		return pTransactions;
	}

//	public void setpTransactions(List<ProductTransactionDTO> pTransactions) {
//		this.pTransactions = pTransactions;
//	}

}
