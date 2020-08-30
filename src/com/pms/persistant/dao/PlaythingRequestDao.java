package com.pms.persistant.dao;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class PlaythingRequestDao {

	@NotEmpty
	private String playthingId;
	@NotEmpty
	private String playthingName;
	@NotEmpty

	private double price;
	private String description;
	private Date createdDate;
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

	public String getPlaythingCategoriesName() {
		return playthingCategoriesName;
	}

	public void setPlaythingCategoriesName(String playthingCategoriesName) {
		this.playthingCategoriesName = playthingCategoriesName;
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
		this.playthingName = playthingName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
		this.playthingStatusId = playthingStatusId;
	}

	public String getPlaythingStatusName() {
		return playthingStatusName;
	}

	public void setPlaythingStatusName(String playthingStatusName) {
		this.playthingStatusName = playthingStatusName;
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	public String getPlaythingCategoriesId() {
		return playthingCategoriesId;
	}

	public void setPlaythingCategoriesId(String playthingCategoriesId) {
		this.playthingCategoriesId = playthingCategoriesId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public byte[] getB() {
		return b;
	}

	public void setB(byte[] b) {
		this.b = b;
	}

}
