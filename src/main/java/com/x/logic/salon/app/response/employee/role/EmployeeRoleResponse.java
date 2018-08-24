package com.x.logic.salon.app.response.employee.role;

import com.x.logic.salon.app.data.modal.EmployeeRole;
import com.x.logic.salon.app.message.Message;

public class EmployeeRoleResponse {

	private EmployeeRole employeeRole;
	private Message message;

	public EmployeeRole getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(EmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
