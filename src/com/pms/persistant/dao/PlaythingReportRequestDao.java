package com.pms.persistant.dao;

import java.sql.Date;

public class PlaythingReportRequestDao {

	private String playthingCategory;
	private String plaything;
	private int runningCount;
	private int playCount;

	private int playthingId;

	private Date validFrom;
	private Date validTo;

	private int maintenanceCount;

	public int getMaintenanceCount() {
		return maintenanceCount;
	}

	public void setMaintenanceCount(int maintenanceCount) {
		this.maintenanceCount = maintenanceCount;
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
