package com.x.logic.salon.app.response.leave.type;

import com.x.logic.salon.app.data.modal.LeaveType;
import com.x.logic.salon.app.message.Message;

public class LeaveTypeResponse {

	private LeaveType leaveType;
	private Message message;

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
