package com.x.logic.salon.app.respoinse.procedure;

import com.x.logic.salon.app.data.modal.Procedure;
import com.x.logic.salon.app.message.Message;

public class ProcedureResponse {

	private Procedure procedure;
	private Message message;

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
