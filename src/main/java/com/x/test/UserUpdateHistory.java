package com.x.test;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserUpdateHistory {

	@Id
	private String userName;
	private List<HistoryData> historyDatas;



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<HistoryData> getHistoryDatas() {
		return historyDatas;
	}

	public void setHistoryDatas(List<HistoryData> historyDatas) {
		this.historyDatas = historyDatas;
	}

}
