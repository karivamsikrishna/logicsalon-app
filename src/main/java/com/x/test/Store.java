package com.x.test;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Store {

	@Id
	private String storeId;
	private String storeName;
	private String displayName;
	private Address address;
	private List<Procedure> procedures;
	private Map<String, String> tradingHours;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Procedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}

	public Map<String, String> getTradingHours() {
		return tradingHours;
	}

	public void setTradingHours(Map<String, String> tradingHours) {
		this.tradingHours = tradingHours;
	}

}
