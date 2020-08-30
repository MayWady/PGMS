package com.pms.persistant.dao;

import java.sql.Date;

public class MembershipRequestDao {
	private String memberId;
	private String memberName;
	private String phone;
	private Date validFrom;
	private Date validTo;
	private Date date;
	private int membershipStatusId;
	private String membershipStatusName;
	private String memberType;
	private double price;
	private double discount;
	private String nrc;
	private String address;

	
	public String getMembershipStatusName() {
		return membershipStatusName;
	}

	public void setMembershipStatusName(String membershipStatusName) {
		this.membershipStatusName = membershipStatusName;
	}

	
	public MembershipRequestDao() {
	}

	public MembershipRequestDao(String memberId, double discount) {
		super();
		this.memberId = memberId;
		this.discount = discount;
	}

	public MembershipRequestDao(String memberId, String memberName, double discount) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.discount = discount;
	}

	public MembershipRequestDao(String memberId, String memberName, String phone, Date validFrom, Date validTo,
			Date date, int membershipStatusId, String memberType, double price, double discount, String nrc,
			String address,String membershipStatusName) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.phone = phone;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.date = date;
		this.membershipStatusId = membershipStatusId;
		this.memberType = memberType;
		this.price = price;
		this.discount = discount;
		this.nrc = nrc;
		this.address = address;
		this.membershipStatusName=membershipStatusName;
	}

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
		this.memberName = memberName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMembershipStatusIdId() {
		return membershipStatusId;
	}

	public void setMembershipStatusId(int membershipStatusId) {
		this.membershipStatusId = membershipStatusId;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
