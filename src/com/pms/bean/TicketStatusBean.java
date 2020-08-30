package com.pms.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TicketStatusBean {
	
	private int id;
	@NotEmpty
	@Size(min=6 , max=10)
	private String name;
	public TicketStatusBean() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public TicketStatusBean(int id) {
		super();
		this.id = id;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	public TicketStatusBean( int id, @NotEmpty String name) {
		super();
		this.id = id;
		this.name = name;
	}
}


