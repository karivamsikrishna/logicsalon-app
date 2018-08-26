package com.x.test;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserCapability {

	@Id
	private String userTypeId;
	private List<UserType> capabilities;

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public List<UserType> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<UserType> capabilities) {
		this.capabilities = capabilities;
	}

}
