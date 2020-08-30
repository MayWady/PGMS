package com.pms.bean;

import java.sql.Date;

public class PlayBean {

	private String ticketId;
	private String time;
	private String date;
	private String playthingId;
	private String playthingName;
	private String price;
	private String description;
	private byte[] b;
	public String getPlaythingName() {
		return playthingName;
	}

	public void setPlaythingName(String playthingName) {
		this.playthingName = playthingName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String status;
	private String balance;
	private String image;
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlaythingId() {
		return playthingId;
	}

	public void setPlaythingId(String playthingId) {
		this.playthingId = playthingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId.trim();
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

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public byte[] getB() {
		return b;
	}

	public void setB(byte[] b) {
		this.b = b;
	}

}
