package com.x;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.x.logic.salon.app.util.RandomGenerator;
import com.x.test.EmployeeRole;
import com.x.test.ProcedureStep;

public class ProcedureStepTest {

	public static void main(String[] args) {

		Gson gson = new Gson();
		RandomGenerator generator = new RandomGenerator();
		List<ProcedureStep> list = new ArrayList<>();
		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleId(generator.getRandomNumber(5));
		employeeRole.setRoleName(generator.getRandomString(5));
		ProcedureStep procedureStep = new ProcedureStep();
		procedureStep.setStepId(generator.getRandomNumber(5));
		procedureStep.setStepName(generator.getRandomString(5));
		procedureStep.setTimeRequired(30);
		procedureStep.setRole(employeeRole);
		list.add(procedureStep);

		procedureStep = new ProcedureStep();
		procedureStep.setStepId(generator.getRandomNumber(5));
		procedureStep.setStepName(generator.getRandomString(5));
		procedureStep.setTimeRequired(30);
		procedureStep.setRole(employeeRole);
		list.add(procedureStep);

		System.out.println(gson.toJson(list));
		System.out.println(gson.toJson(procedureStep));
	}

}
