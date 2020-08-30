package com.pms.bean;

import javax.validation.constraints.NotEmpty;

public class PlayStatusBean {

	private int id;
	@NotEmpty
	private String name;

	public PlayStatusBean() {

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

	public PlayStatusBean(int id) {
		super();
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayStatusBean(int id, @NotEmpty String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
