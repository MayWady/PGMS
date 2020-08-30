package com.pms.persistant.dao;

import java.sql.Date;
import java.sql.Time;

public class TopupAmountRequestDao {

	private int topupAmountId;
	private Double topupAmount;
	private Date date;
	private String time;
	private String ticketId;
	private Double balance;
	private Double totalBalance;

	public TopupAmountRequestDao() {
	}

	public TopupAmountRequestDao(Double topupAmount, String ticketId) {
		super();
		this.topupAmount = topupAmount;
		this.ticketId = ticketId;
	}

	public TopupAmountRequestDao(int topupAmountId, Double topupAmount, Date date, String time, String ticketId) {
		super();
		this.topupAmountId = topupAmountId;
		this.topupAmount = topupAmount;
		this.date = date;
		this.time = time;
		this.ticketId = ticketId;
	}

	public TopupAmountRequestDao(int topupAmountId, Double topupAmount, Date date, String time, String ticketId,
			Double balance, Double totalBalance) {
		super();
		this.topupAmountId = topupAmountId;
		this.topupAmount = topupAmount;
		this.date = date;
		this.time = time;
		this.ticketId = ticketId;
		this.balance = balance;
		this.totalBalance = totalBalance;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public int getTopupAmountId() {
		return topupAmountId;
	}

	public void setTopupAmountId(int topupAmountId) {
		this.topupAmountId = topupAmountId;
	}

	public Double getTopupAmount() {
		return topupAmount;
	}

	public void setTopupAmount(Double topupAmount) {
		this.topupAmount = topupAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String string) {
		this.time = string;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

}
