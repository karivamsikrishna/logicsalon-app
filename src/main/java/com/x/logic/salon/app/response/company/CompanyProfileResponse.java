package com.x.logic.salon.app.response.company;

import com.x.logic.salon.app.data.modal.CompanyDetails;
import com.x.logic.salon.app.message.Message;

public class CompanyProfileResponse {

	private CompanyDetails company;
	private Message message;

	public CompanyDetails getCompany() {
		return company;
	}

	public void setCompany(CompanyDetails company) {
		this.company = company;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
