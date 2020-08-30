package com.pms.persistant.dao;

public class MemberTypeRequestDao {
	private String memberTypeid;
	private String memberTypename;
	private String description;
	private double price;
	private double perdiscount;
	
	public String getMemberTypeid() {
		return memberTypeid;
	}
	public void setMemberTypeid(String memberTypeid) {
		this.memberTypeid = memberTypeid;
	}
	public String getMemberTypename() {
		return memberTypename;
	}
	public void setMemberTypename(String memberTypename) {
		this.memberTypename = memberTypename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPerdiscount() {
		return perdiscount;
	}
	public void setPerdiscount(double perdiscount) {
		this.perdiscount = perdiscount;
	}
	
}
