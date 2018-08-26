package com.x.test;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Procedure {

	@Id
	private String procedureId;
	private String procedureName;
	private int totalTimeRequired;
	private List<ProcedureStep> procedureSteps;
	private String[] executionOrder;

	public String getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public int getTotalTimeRequired() {
		return totalTimeRequired;
	}

	public void setTotalTimeRequired(int totalTimeRequired) {
		this.totalTimeRequired = totalTimeRequired;
	}

	public List<ProcedureStep> getProcedureSteps() {
		return procedureSteps;
	}

	public void setProcedureSteps(List<ProcedureStep> procedureSteps) {
		this.procedureSteps = procedureSteps;
	}

	public String[] getExecutionOrder() {
		return executionOrder;
	}

	public void setExecutionOrder(String[] executionOrder) {
		this.executionOrder = executionOrder;
	}

}
