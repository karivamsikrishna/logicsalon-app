package com.x.logic.salon.app.response.publicholiday;

import com.x.logic.salon.app.data.modal.PublicHoliday;
import com.x.logic.salon.app.message.Message;

public class PublicHolidayResponse {

	private PublicHoliday holiday;
	private Message message;

	public PublicHoliday getHoliday() {
		return holiday;
	}

	public void setHoliday(PublicHoliday holiday) {
		this.holiday = holiday;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
