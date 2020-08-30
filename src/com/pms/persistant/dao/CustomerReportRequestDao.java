package com.pms.persistant.dao;

import java.sql.Date;

public class CustomerReportRequestDao {

	private String customerName;
	private String ticketId;
	private String date;
	private String playThing;
	private String time;
	private String balance;

	private String validFrom;

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	private String validTo;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlayThing() {
		return playThing;
	}

	public void setPlayThing(String playThing) {
		this.playThing = playThing;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBalance() {
		return balance;
	}

	public void setPrice(String balance) {
		this.balance = balance;
	}

	public void setValidFrom(Date valueOf) {
		// TODO Auto-generated method stub

	}

}
