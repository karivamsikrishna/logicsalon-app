package com.x.logic.salon.app.response.usertype;

import com.x.logic.salon.app.data.modal.UserCapability;
import com.x.logic.salon.app.message.Message;

public class UserCapabilityCreateResponse {

	private UserCapability userCapability;
	private Message message;

	public UserCapability getUserCapability() {
		return userCapability;
	}

	public void setUserCapability(UserCapability userCapability) {
		this.userCapability = userCapability;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
