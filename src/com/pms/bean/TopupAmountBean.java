package com.pms.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class TopupAmountBean {

	private int topupAmountId;
	@NotEmpty
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String topupAmount;
	private String date;
	private String time;
	@NotEmpty
	private String ticketId;
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String balance;
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String totalBalance;
	private String ticketPrice;
	private String discount;
	private String quantity;

	public TopupAmountBean() {

	}

	public TopupAmountBean(@NotEmpty @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String topupAmount,
			@NotEmpty String ticketId) {
		super();
		this.topupAmount = topupAmount;
		this.ticketId = ticketId;
	}

	public TopupAmountBean(@NotEmpty @Pattern(regexp = "[+][0-9]*.[0-9]+|[+][0-9]*") String topupAmount, String date,
			String time, @NotEmpty String ticketId) {
		super();
		this.topupAmount = topupAmount;
		this.date = date;
		this.time = time;
		this.ticketId = ticketId;
	}

	public TopupAmountBean(int topupAmountId,
			@NotEmpty @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String topupAmount, String date, String time,
			@NotEmpty String ticketId) {
		super();
		this.topupAmountId = topupAmountId;
		this.topupAmount = topupAmount;
		this.date = date;
		this.time = time;
		this.ticketId = ticketId;
	}

	public TopupAmountBean(@NotEmpty @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String topupAmount, String date,
			String time, @NotEmpty String ticketId, @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String balance,
			@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String totalBalance) {
		super();
		this.topupAmount = topupAmount;
		this.date = date;
		this.time = time;
		this.ticketId = ticketId;
		this.balance = balance;
		this.totalBalance = totalBalance;
	}

	public TopupAmountBean(int topupAmountId,
			@NotEmpty @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String topupAmount, String date, String time,
			@NotEmpty String ticketId, @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String balance,
			@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String totalBalance) {
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
		this.ticketId = ticketId.trim();
	}

	public int getTopupAmountId() {
		return topupAmountId;
	}

	public void setTopupAmountId(int topupAmountId) {
		this.topupAmountId = topupAmountId;
	}

	public String getTopupAmount() {
		return topupAmount;
	}

	public void setTopupAmount(String topupAmount) {
		this.topupAmount = topupAmount.trim();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date.trim();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time.trim();
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance.trim();
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance.trim();
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
	

}