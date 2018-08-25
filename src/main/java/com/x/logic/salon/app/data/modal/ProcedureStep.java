package com.x.logic.salon.app.data.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProcedureStep {

	@Id
	private String stepId;
	private String stepName;
	private int timeRequired;
	private EmployeeRole role;

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public int getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(int timeRequired) {
		this.timeRequired = timeRequired;
	}

	public EmployeeRole getRole() {
		return role;
	}

	public void setRole(EmployeeRole role) {
		this.role = role;
	}

}
