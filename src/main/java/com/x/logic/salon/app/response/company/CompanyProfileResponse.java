package com.x.logic.salon.app.response.company;

import com.x.logic.salon.app.message.Message;

public class CompanyProfileResponse {

	private String companyName;
	private Message message;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
