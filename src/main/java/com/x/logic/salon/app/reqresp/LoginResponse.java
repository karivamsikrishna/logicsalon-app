package com.x.logic.salon.app.reqresp;

import com.x.logic.salon.app.data.modal.UserDetails;
import com.x.logic.salon.app.message.Message;


public class LoginResponse {

	private UserDetails user;
	private Message message;

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
