package com.x;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.x.logic.salon.app.util.RandomGenerator;
import com.x.test.EmployeeRole;
import com.x.test.Procedure;
import com.x.test.ProcedureStep;

public class ProcedureTest {

	public static void main(String[] args) {
		ProcedureTest procedureTest = new ProcedureTest();
		Gson gson = new Gson();
		RandomGenerator generator = new RandomGenerator();
		List<Procedure> procedureList = new ArrayList<>();
		procedureList.add(procedureTest.getProcedure(generator));
		procedureList.add(procedureTest.getProcedure(generator));
		
		System.out.println(gson.toJson(procedureList));
		System.out.println(gson.toJson(procedureTest.getProcedure(generator)));

	}

	public Procedure getProcedure(RandomGenerator generator) {
		Procedure procedure = new Procedure();
		procedure.setProcedureId(generator.getRandomNumber(5));
		procedure.setProcedureName(generator.getRandomString(5));
		List<ProcedureStep> procedureSteps = getProcedureSteps(generator);
		procedure.setProcedureSteps(procedureSteps);
		procedure.setTotalTimeRequired(60);
		String[] order = { procedureSteps.get(0).getStepId(), "break_30", procedureSteps.get(1).getStepId() };
		procedure.setExecutionOrder(order);
		return procedure;
	}

	public List<ProcedureStep> getProcedureSteps(RandomGenerator generator) {
		List<ProcedureStep> ProcedureStepList = new ArrayList<>();
		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleId(generator.getRandomNumber(5));
		employeeRole.setRoleName(generator.getRandomString(5));
		ProcedureStep procedureStep = new ProcedureStep();
		procedureStep.setStepId(generator.getRandomNumber(5));
		procedureStep.setStepName(generator.getRandomString(5));
		procedureStep.setTimeRequired(30);
		procedureStep.setRole(employeeRole);
		ProcedureStepList.add(procedureStep);

		procedureStep = new ProcedureStep();
		procedureStep.setStepId(generator.getRandomNumber(5));
		procedureStep.setStepName(generator.getRandomString(5));
		procedureStep.setTimeRequired(30);
		procedureStep.setRole(employeeRole);
		ProcedureStepList.add(procedureStep);
		return ProcedureStepList;
	}
}
