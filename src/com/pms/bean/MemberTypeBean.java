package com.pms.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberTypeBean {
	private String memberTypeid;
	@NotEmpty
	@Size(min = 4, max = 10)
	private String memberTypename;
	private String description;
	@NotEmpty
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]+")
	private String price;
	@NotEmpty
	@Min(value = 0)
	@Max(value = 100)
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String perdiscount;

	public MemberTypeBean() {

	}

	

	public MemberTypeBean(String memberTypeid,
			@NotEmpty @Min(0) @Max(100) @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String perdiscount) {
		super();
		this.memberTypeid = memberTypeid;
		this.perdiscount = perdiscount;
	}

	


	



	public MemberTypeBean(String memberTypeid, @NotEmpty @Size(min = 4, max = 10) String memberTypename,
			String description, @NotEmpty @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]+") String price,
			@NotEmpty @Min(0) @Max(100) @Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*") String perdiscount) {
		super();
		this.memberTypeid = memberTypeid;
		this.memberTypename = memberTypename;
		this.description = description;
		this.price = price;
		this.perdiscount = perdiscount;
	}



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
		this.memberTypename = memberTypename.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price.trim();
	}

	public String getPerdiscount() {
		return perdiscount;
	}

	public void setPerdiscount(String perdiscount) {
		this.perdiscount = perdiscount.trim();
	}

}
