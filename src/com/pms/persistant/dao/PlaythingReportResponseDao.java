package com.pms.persistant.dao;

import java.sql.Date;

public class PlaythingReportResponseDao {

	private String playthingCategory;
	private String plaything;
	private int runningCount;
	private int playCount;
	private int playthingId;
	private Date date;
	private int serviceCount;
	private String playthingStatus;
	private int totalRunningCount;
	private int maintenanceCount;

	public int getTotalRunningCount() {
		return totalRunningCount;
	}

	public void setTotalRunningCount(int totalRunningCount) {
		this.totalRunningCount = totalRunningCount;
	}

	public int getMaintenanceCount() {
		return maintenanceCount;
	}

	public void setMaintenanceCount(int maintenanceCount) {
		this.maintenanceCount = maintenanceCount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	public String getPlaythingStatus() {
		return playthingStatus;
	}

	public void setPlaythingStatus(String playthingStatus) {
		this.playthingStatus = playthingStatus;
	}

	public int getPlaythingId() {
		return playthingId;
	}

	public void setPlaythingId(int playthingId) {
		this.playthingId = playthingId;
	}

	public String getPlaythingCategory() {
		return playthingCategory;
	}

	public void setPlaythingCategory(String playthingCategory) {
		this.playthingCategory = playthingCategory;
	}

	public String getPlaything() {
		return plaything;
	}

	public void setPlaything(String plaything) {
		this.plaything = plaything;
	}

	public int getRunningCount() {
		return runningCount;
	}

	public void setRunningCount(int runningCount) {
		this.runningCount = runningCount;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

}
