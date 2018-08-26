package com.x;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.x.logic.salon.app.util.RandomGenerator;
import com.x.test.EmployeeRole;

public class EmployeeRoleTest {

	public static void main(String[] args) {

		Gson gson = new Gson();
		RandomGenerator generator = new RandomGenerator();
		List<EmployeeRole> list = new ArrayList<>();
		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleId(generator.getRandomNumber(5));
		employeeRole.setRoleName(generator.getRandomString(5));
		list.add(employeeRole);
		employeeRole = new EmployeeRole();
		employeeRole.setRoleId(generator.getRandomNumber(5));
		employeeRole.setRoleName(generator.getRandomString(5));
		list.add(employeeRole);
		
		System.out.println(gson.toJson(list));
		System.out.println(gson.toJson(employeeRole));
	}
}
