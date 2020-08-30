package com.pms.persistant.dao;

import java.sql.Date;

public class PlayResponseDao {

	private String ticketId;
	private String time;
	private Date date;
	private String playthingId;
	private String status;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

}
