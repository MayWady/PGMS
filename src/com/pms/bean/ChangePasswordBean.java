package com.pms.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangePasswordBean {

	private String userId;
	@NotEmpty
	@Size(min = 6, max = 16)
	private String oldPassword;
	@NotEmpty
	@Size(min = 6, max = 16)
	private String newPassword;
	@NotEmpty
	@Size(min = 6, max = 16)
	private String confirmPassword;

	public ChangePasswordBean() {

	}

	public ChangePasswordBean(@NotEmpty @Size(min = 6, max = 16) String oldPassword,
			@NotEmpty @Size(min = 6, max = 16) String newPassword,
			@NotEmpty @Size(min = 6, max = 16) String confirmPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public ChangePasswordBean(String userId, @NotEmpty @Size(min = 6, max = 16) String oldPassword,
			@NotEmpty @Size(min = 6, max = 16) String newPassword,
			@NotEmpty @Size(min = 6, max = 16) String confirmPassword) {
		super();
		this.userId = userId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId.trim();
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword.trim();
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword.trim();
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword.trim();
	}

}
