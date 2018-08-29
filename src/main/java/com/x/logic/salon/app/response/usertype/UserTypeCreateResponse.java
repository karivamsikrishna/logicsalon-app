package com.x.logic.salon.app.response.usertype;

import com.x.logic.salon.app.data.modal.UserType;
import com.x.logic.salon.app.message.Message;

public class UserTypeCreateResponse {

	private UserType userType;
	private Message message;

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
