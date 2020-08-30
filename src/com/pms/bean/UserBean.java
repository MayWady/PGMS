package com.pms.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserBean {

	@NotEmpty
	private String userId;
	@NotEmpty

	private String userName;
	@NotEmpty
	@Size(min = 6, max = 16)
	private String password;
	@NotEmpty
	@Size(min = 6, max = 16)
	private String confirmPassword;
	@NotEmpty
	private String nrc;
	@NotEmpty
	private String address;
	@NotEmpty
	@Pattern(regexp = "[0-9]*")
	private String phone;
	@NotEmpty
	@Email
	private String email;
	private int role;
	private int userStatus;
	private String userStatusName;

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public UserBean() {

	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UserBean(@NotEmpty String userId, @NotEmpty String userName,
			@NotEmpty @Size(min = 6, max = 16) String password,
			@NotEmpty @Size(min = 6, max = 16) String confirmPassword, @NotEmpty String nrc, @NotEmpty String address,
			@NotEmpty @Pattern(regexp = "[0-9]*") String phone, @NotEmpty @Email String email, int role,
			int userStatus) {
		super();
		this.userId = userId;
		this.userName = userName.trim();
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.nrc = nrc.trim();
		this.address = address.trim();
		this.phone = phone;
		this.email = email.trim();
		this.role = role;
		this.userStatus = userStatus;
	}

}
