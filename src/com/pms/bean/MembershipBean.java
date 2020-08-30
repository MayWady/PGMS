package com.pms.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class MembershipBean {

	private String memberId;
	@NotEmpty
	private String memberName;
	@NotEmpty
	@Pattern(regexp = "[0-9]*")
	private String phone;
	private String price;
	private String discount;
	@NotEmpty
	private String nrc;
	@NotEmpty
	private String address;
	@NotEmpty
	private String validFrom;
	@NotEmpty
	private String validTo;
	private String date;
	private String membershipStatusId;
	private String membershipStatusName;
	private String memberType;
	private String memberTypeName;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone.trim();
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
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

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom.trim();
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo.trim();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMembershipStatusId() {
		return membershipStatusId;
	}

	public void setMembershipStatusId(String membershipStatusId) {
		this.membershipStatusId = membershipStatusId;
	}

	public String getMembershipStatusName() {
		return membershipStatusName;
	}

	public void setMembershipStatusName(String membershipStatusName) {
		this.membershipStatusName = membershipStatusName;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getMemberTypeName() {
		return memberTypeName;
	}

	public void setMemberTypeName(String memberTypeName) {
		this.memberTypeName = memberTypeName;
	}

}
