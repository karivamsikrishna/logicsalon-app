package com.x.logic.salon.app.response.usertype;

import com.x.logic.salon.app.message.Message;

public class UserTypeCreateResponse {

	private String userType;
	private Message message;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
