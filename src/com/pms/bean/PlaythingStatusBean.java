package com.pms.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PlaythingStatusBean {
	private int id;
	@NotEmpty
	@Size(min=6 , max=10)
	private String name;
	public PlaythingStatusBean() {
		
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

	public void setName(String name) {
		this.name = name.trim();
	}
	public PlaythingStatusBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
