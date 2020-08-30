package com.pms.persistant.dao;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class TicketTypeRequestDao {

	@NotEmpty
	private String ticketTypeId;
	@NotEmpty
	private String ticketTypeName;
	@NotEmpty
	@Pattern(regexp = "[0-9]*")
	private double price;
	private Date validFrom;
	private Date validTo;
	private String ticketTypeStatusId;
	private String ticketTypeStatusName;
	private String playthingName;

	public String getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(String ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public String getTicketTypeName() {
		return ticketTypeName;
	}

	public void setTicketTypeName(String ticketTypeName) {
		this.ticketTypeName = ticketTypeName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getTicketTypeStatusId() {
		return ticketTypeStatusId;
	}

	public void setTicketTypeStatusId(String ticketTypeStatusId) {
		this.ticketTypeStatusId = ticketTypeStatusId;
	}

	

	public String getPlaythingName() {
		return playthingName;
	}

	public void setPlaythingName(String playthingName) {
		this.playthingName = playthingName;
	}

	public String getTicketTypeStatusName() {
		return ticketTypeStatusName;
	}

	public void setTicketTypeStatusName(String ticketTypeStatusName) {
		this.ticketTypeStatusName = ticketTypeStatusName;
	}

}
