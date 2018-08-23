package com.x.logic.salon.app.response.user;

import com.x.logic.salon.app.message.Message;

public class UserCreateResponse {

	private String userName;
	private String email;
	private Message message;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
