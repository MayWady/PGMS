package com.pms.bean;

import javax.validation.constraints.NotEmpty;

public class LoginBean {
	@NotEmpty
	private String userId;

	@NotEmpty
	private String password;
	

	public LoginBean(@NotEmpty String userId, @NotEmpty String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public LoginBean() {
		super();
	}

	public void setUserId(String userId) {
		this.userId = userId.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}
}
