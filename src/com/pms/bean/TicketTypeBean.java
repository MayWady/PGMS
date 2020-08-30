package com.pms.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class TicketTypeBean {

	@NotEmpty
	private String ticketTypeId;
	@NotEmpty
	private String ticketTypeName;
	@NotEmpty
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]+")
	private String price;
	@NotEmpty
	private String validFrom;
	@NotEmpty
	private String validTo;
	private String ticketTypeStatusId;
	private String ticketTypeStatusName;
	private String[] plaything;
	private String playthingName;

	public String getPlaythingName() {
		return playthingName;
	}

	public void setPlaythingName(String playthingName) {
		this.playthingName = playthingName.trim();
	}

	public TicketTypeBean() {

	}

	public TicketTypeBean(String ticketTypeId) {
		super();
		this.ticketTypeId = ticketTypeId;
	}

	public TicketTypeBean(String ticketTypeId, @NotEmpty String ticketTypeName) {
		super();
		this.ticketTypeId = ticketTypeId;
		this.ticketTypeName = ticketTypeName;
	}

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
		this.ticketTypeName = ticketTypeName.trim();
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price.trim();
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

	public String[] getPlaything() {
		return plaything;
	}

	public void setPlaything(String[] plaything) {
		this.plaything = plaything;
	}

	public String getTicketTypeStatusId() {
		return ticketTypeStatusId;
	}

	public void setTicketTypeStatusId(String ticketTypeStatusId) {
		this.ticketTypeStatusId = ticketTypeStatusId.trim();
	}

	public String getTicketTypeStatusName() {
		return ticketTypeStatusName;
	}

	public void setTicketTypeStatusName(String ticketTypeStatusName) {
		this.ticketTypeStatusName = ticketTypeStatusName.trim();
	}

}
