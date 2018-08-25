package com.x.logic.salon.app.controller;

import java.util.List;

import com.x.logic.salon.app.data.modal.Procedure;
import com.x.logic.salon.app.data.modal.ProcedureStep;
import com.x.logic.salon.app.repos.EmployeeRolesRepository;
import com.x.logic.salon.app.repos.ProcedureRepository;
import com.x.logic.salon.app.repos.ProcedureStepRepository;

public class ProcedureController {

	private ProcedureRepository procedureRepository;
	private ProcedureStepRepository procedureStepRepository;
	private EmployeeRolesRepository employeeRolesRepository;

	public ProcedureController(ProcedureRepository procedureRepository, ProcedureStepRepository procedureStepRepository,
			EmployeeRolesRepository employeeRolesRepository) {
		this.procedureRepository = procedureRepository;
		this.procedureStepRepository = procedureStepRepository;
		this.employeeRolesRepository = employeeRolesRepository;
	}

	public boolean isValideToCreateProcedure(Procedure procedure) {

		List<Procedure> procedureList = procedureRepository.findAll();
		String procedureNameToUpperCase = procedure.getProcedureName();
		boolean isProcedureExist = false;
		for (Procedure proc : procedureList) {
			if (procedureNameToUpperCase.equals(proc.getProcedureName().toUpperCase())) {
				isProcedureExist = true;
				break;
			}
		}

		if (!isProcedureExist) {
			for (ProcedureStep step : procedure.getProcedureSteps()) {
				if (procedureStepRepository.exists(step.getStepId())) {
					if (!employeeRolesRepository.exists(step.getRole().getRoleId())) {
						return false;
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}

		return true;
	}

	public boolean isValideToUpdateProcedure(Procedure procedure) {

		if (procedureRepository.exists(procedure.getProcedureId())) {
			for (ProcedureStep step : procedure.getProcedureSteps()) {
				if (procedureStepRepository.exists(step.getStepId())) {
					if (!employeeRolesRepository.exists(step.getRole().getRoleId())) {
						return false;
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}

		return true;
	}

	public Procedure getProcedureByName(String procedureName) {
		List<Procedure> procedureList = procedureRepository.findAll();
		String procedureNameToUpperCase = procedureName.toUpperCase();
		for (Procedure procedure : procedureList) {
			if (procedureNameToUpperCase.equals(procedure.getProcedureName().toUpperCase())) {
				return procedure;
			}
		}

		return new Procedure();
	}
}
