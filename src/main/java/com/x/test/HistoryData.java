package com.x.test;

import java.util.Date;

public class HistoryData {

	private UserDetails previousData;
	private UserDetails UpdatedData;
	private Date updatedOn;

	public UserDetails getPreviousData() {
		return previousData;
	}

	public void setPreviousData(UserDetails previousData) {
		this.previousData = previousData;
	}

	public UserDetails getUpdatedData() {
		return UpdatedData;
	}

	public void setUpdatedData(UserDetails updatedData) {
		UpdatedData = updatedData;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
