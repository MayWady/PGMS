package com.pms.bean;

import javax.validation.constraints.NotEmpty;

public class PlaythingCategoriesBean {

	private int id;
	@NotEmpty
	private String name;

	public PlaythingCategoriesBean() {

	}

	public PlaythingCategoriesBean(int id) {
		super();
		this.id = id;
	}

	public PlaythingCategoriesBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
