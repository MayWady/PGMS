package com.pms.persistant.dao;

import javax.validation.constraints.Size;

public class ChangePasswordRequestDao {
	private String userId;
	@Size(min = 6, max = 16)
	private String oldPassword;
	@Size(min = 6, max = 16)
	private String newPassword;
	@Size(min = 6, max = 16)
	private String confirmPassword;

	public ChangePasswordRequestDao() {

	}

	public ChangePasswordRequestDao(@Size(min = 6, max = 16) String oldPassword,
			@Size(min = 6, max = 16) String newPassword, @Size(min = 6, max = 16) String confirmPassword) {

		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public ChangePasswordRequestDao(String userId, @Size(min = 6, max = 16) String oldPassword,
			@Size(min = 6, max = 16) String newPassword, @Size(min = 6, max = 16) String confirmPassword) {

		this.userId = userId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
