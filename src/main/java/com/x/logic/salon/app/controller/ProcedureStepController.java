package com.x.logic.salon.app.controller;

import java.util.List;

import com.x.logic.salon.app.data.modal.EmployeeRole;
import com.x.logic.salon.app.data.modal.ProcedureStep;
import com.x.logic.salon.app.repos.EmployeeRolesRepository;
import com.x.logic.salon.app.repos.ProcedureStepRepository;

public class ProcedureStepController {

	private ProcedureStepRepository procedureStepRepository;
	private EmployeeRolesRepository employeeRolesRepository;

	public ProcedureStepController(ProcedureStepRepository procedureStepRepository,
			EmployeeRolesRepository employeeRolesRepository) {
		this.procedureStepRepository = procedureStepRepository;
		this.employeeRolesRepository = employeeRolesRepository;
	}

	public boolean isValideTocreateProcedureStep(ProcedureStep procedureStep) {

		String procedureStepNameToUpperCase = procedureStep.getStepName().toUpperCase();
		List<ProcedureStep> procedureStepList = procedureStepRepository.findAll();
		for (ProcedureStep step : procedureStepList) {
			if (procedureStepNameToUpperCase.equals(step.getStepName().toUpperCase())) {
				return false;
			}
		}
		boolean isRoleExist = employeeRolesRepository.exists(procedureStep.getRole().getRoleId());
		if (!isRoleExist) {
			return false;
		} else {
			EmployeeRole employeeRole = employeeRolesRepository.findOne(procedureStep.getRole().getRoleId());
			if (!employeeRole.getRoleName().toUpperCase().equals(procedureStep.getRole().getRoleName().toUpperCase())) {
				return false;
			}
		}

		return true;
	}

	public boolean isValideToUpdateProcedureStep(ProcedureStep procedureStep) {

		if (procedureStepRepository.exists(procedureStep.getStepId())
				&& employeeRolesRepository.exists(procedureStep.getRole().getRoleId())) {
			EmployeeRole employeeRole = employeeRolesRepository.findOne(procedureStep.getRole().getRoleId());
			if (!employeeRole.getRoleName().toUpperCase().equals(procedureStep.getRole().getRoleName().toUpperCase())) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public ProcedureStep getProcedureStepByName(String procedureStepName) {

		String procedureStepNameToUpperCase = procedureStepName.toUpperCase();
		List<ProcedureStep> procedureStepList = procedureStepRepository.findAll();
		for (ProcedureStep step : procedureStepList) {
			if (procedureStepNameToUpperCase.equals(step.getStepName().toUpperCase())) {
				return step;
			}
		}

		return new ProcedureStep();
	}
}
