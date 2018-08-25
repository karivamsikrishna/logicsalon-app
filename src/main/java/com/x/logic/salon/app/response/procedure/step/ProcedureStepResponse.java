package com.x.logic.salon.app.response.procedure.step;

import com.x.logic.salon.app.data.modal.ProcedureStep;
import com.x.logic.salon.app.message.Message;

public class ProcedureStepResponse {

	private ProcedureStep procedureStep;
	private Message message;

	public ProcedureStep getProcedureStep() {
		return procedureStep;
	}

	public void setProcedureStep(ProcedureStep procedureStep) {
		this.procedureStep = procedureStep;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
