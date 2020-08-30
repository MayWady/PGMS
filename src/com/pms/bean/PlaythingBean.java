package com.pms.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class PlaythingBean {

	@NotEmpty
	private String playthingId;
	@NotEmpty
	private String playthingName;
	@NotEmpty
	@Pattern(regexp = "[^-][0-9]*.[0-9]+|[^-][0-9]*")
	private String price;
	private String description;
	@NotEmpty
	private String createdDate;
	private int playCount;
	private int serviceCount;
	private int runningCount;
	private int maintenanceCount;
	private String playthingStatusId;
	private String playthingStatusName;
	private String playthingCategoriesId;
	private String playthingCategoriesName;
	private String image;
	private byte[] b; 

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPlaythingCategoriesName() {
		return playthingCategoriesName;
	}

	public void setPlaythingCategoriesName(String playthingCategoriesName) {
		this.playthingCategoriesName = playthingCategoriesName.trim();
	}

	public PlaythingBean() {

	}

	public PlaythingBean(String playthingId) {
		super();
		this.playthingId = playthingId;
	}

	public PlaythingBean(String playthingId, @NotEmpty String playthingName) {
		super();
		this.playthingId = playthingId;
		this.playthingName = playthingName;
	}

	public PlaythingBean(String playthingId, @NotEmpty String playthingName, @NotEmpty String price) {
		super();
		this.playthingId = playthingId;
		this.playthingName = playthingName;
		this.price = price;
	}

	public String getPlaythingId() {
		return playthingId;
	}

	public void setPlaythingId(String playthingId) {
		this.playthingId = playthingId;
	}

	public String getPlaythingName() {
		return playthingName;
	}

	public void setPlaythingName(String playthingName) {
		this.playthingName = playthingName.trim();
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate.trim();
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public int getRunningCount() {
		return runningCount;
	}

	public void setRunningCount(int runningCount) {
		this.runningCount = runningCount;
	}

	public int getMaintenanceCount() {
		return maintenanceCount;
	}

	public void setMaintenanceCount(int maintenanceCount) {
		this.maintenanceCount = maintenanceCount;
	}

	public String getPlaythingStatusId() {
		return playthingStatusId;
	}

	public void setPlaythingStatusId(String playthingStatusId) {
		this.playthingStatusId = playthingStatusId.trim();
	}

	public String getPlaythingStatusName() {
		return playthingStatusName;
	}

	public void setPlaythingStatusName(String playthingStatusName) {
		this.playthingStatusName = playthingStatusName.trim();
	}

	public String getPlaythingCategoriesId() {
		return playthingCategoriesId;
	}

	public void setPlaythingCategoriesId(String playthingCategoriesId) {
		this.playthingCategoriesId = playthingCategoriesId.trim();
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	public byte[] getB() {
		return b;
	}

	public void setB(byte[] b) {
		this.b = b;
	}

}
