package com.pms.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class TicketBean {
	@NotEmpty
	private String ticketId;
	@NotEmpty
	private String ticketType;
	private String customerName;
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String ticketPrice;
	@NotEmpty
	@Pattern(regexp = "[0-9]*")
	private String quantity;
	@NotEmpty
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String topupAmount;
	private String memberId;
	@Min(value = 0)
	@Max(value = 100)
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String discount;
	private String date;
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String balance;
	@NotEmpty
	private String validFrom;
	@NotEmpty
	private String validTo;
	private String ticketStatus;
	private String ticketStatusName;
	private String ticketTypeName;
	private String time;
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String totalBalance;

	public TicketBean() {

	}

	public TicketBean(@NotEmpty String ticketId, @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String balance) {
		super();
		this.ticketId = ticketId;
		this.balance = balance;
	}

	public TicketBean(@NotEmpty String ticketId, @NotEmpty String ticketType, String customerName,
			@NotEmpty @Pattern(regexp = "[0-9]*") String quantity,
			@NotEmpty @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String topupAmount, String memberId,
			@NotEmpty String validFrom, @NotEmpty String validTo, String ticketStatus) {
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

	public TicketBean(@NotEmpty String ticketId, @NotEmpty String ticketType, String customerName,
			@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String ticketPrice,
			@NotEmpty @Pattern(regexp = "[0-9]*") String quantity,
			@NotEmpty @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String topupAmount, String memberId,
			@Min(0) @Max(100) @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String discount, String date,
			@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String balance, @NotEmpty String validFrom,
			@NotEmpty String validTo, String ticketStatus, String ticketStatusName, String ticketTypeName, String time,
			@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String totalBalance) {
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
		this.ticketStatusName = ticketStatusName;
		this.ticketTypeName = ticketTypeName;
		this.time = time;
		this.totalBalance = totalBalance;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId.trim();
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType.trim();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName.trim();
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice.trim();
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity.trim();
	}

	public String getTopupAmount() {
		return topupAmount;
	}

	public void setTopupAmount(String topupAmount) {
		this.topupAmount = topupAmount.trim();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId.trim();
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount.trim();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date.trim();
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance.trim();
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom.trim();
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo.trim();
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus.trim();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time.trim();
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance.trim();
	}

	public String getTicketTypeName() {
		return ticketTypeName;
	}

	public void setTicketTypeName(String ticketTypeName) {
		this.ticketTypeName = ticketTypeName.trim();
	}

	public String getTicketStatusName() {
		return ticketStatusName;
	}

	public void setTicketStatusName(String ticketStatusName) {
		this.ticketStatusName = ticketStatusName.trim();
	}

}
