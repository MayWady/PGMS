package com.pms.persistant.dao;

import java.sql.Date;


public class TicketResponseDao {

	private String ticketId;
	private String ticketType;
	private String customerName;
	private double ticketPrice;
	private int quantity;
	private double topupAmount;
	private String memberId;
	private double discount;
	private Date date;
	private double balance;
	private Date validFrom;
	private Date validTo;
	private String ticketStatus;
	private String time;
	private String totalBalance;
	
	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public TicketResponseDao() {

	}

	public TicketResponseDao(String ticketId, String ticketType, String customerName, int quantity, double topupAmount,
			String memberId, Date validFrom, Date validTo, String ticketStatus) {
		super();
		this.ticketId = ticketId;
		this.ticketType = ticketType;
		this.customerName = customerName;
		this.quantity = quantity;
		this.topupAmount = topupAmount;
		this.memberId = memberId;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.ticketStatus = ticketStatus;
	}

	public TicketResponseDao(String ticketId, String ticketType, String customerName, double ticketPrice, int quantity,
			double topupAmount, String memberId, double discount, Date date, double balance, Date validFrom,
			Date validTo, String ticketStatus) {
		super();
		this.ticketId = ticketId;
		this.ticketType = ticketType;
		this.customerName = customerName;
		this.ticketPrice = ticketPrice;
		this.quantity = quantity;
		this.topupAmount = topupAmount;
		this.memberId = memberId;
		this.discount = discount;
		this.date = date;
		this.balance = balance;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.ticketStatus = ticketStatus;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTopupAmount() {
		return topupAmount;
	}

	public void setTopupAmount(double topupAmount) {
		this.topupAmount = topupAmount;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

}
