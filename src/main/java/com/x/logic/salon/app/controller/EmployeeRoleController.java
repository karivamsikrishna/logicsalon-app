package com.x.logic.salon.app.controller;

import java.util.List;

import com.x.logic.salon.app.data.modal.EmployeeRole;
import com.x.logic.salon.app.repos.EmployeeRolesRepository;

public class EmployeeRoleController {

	public boolean validateForCreatingEmployeeRole(EmployeeRolesRepository employeeRolesRepository,
			EmployeeRole employeeRole) {

		List<EmployeeRole> employeeRolesList = employeeRolesRepository.findAll();
		String employeeRoleNameUpperCase = employeeRole.getRoleName().toUpperCase();
		for (EmployeeRole role : employeeRolesList) {
			if (employeeRoleNameUpperCase.equals(role.getRoleName().toUpperCase())) {
				return false;
			}
		}

		return true;

	}

	public boolean validateForUpdatingEmployeeRole(EmployeeRolesRepository employeeRolesRepository,
			EmployeeRole employeeRole) {

		if (employeeRolesRepository.exists(employeeRole.getRoleId())) {
			return true;
		}

		return false;

	}

	public EmployeeRole getEmployeeRoleByName(EmployeeRolesRepository employeeRolesRepository, String roleName) {

		List<EmployeeRole> roleList = employeeRolesRepository.findAll();
		String roleNameToUpperCase = roleName.toUpperCase();
		for (EmployeeRole role : roleList) {
			if (roleNameToUpperCase.equals(role.getRoleName().toUpperCase())) {
				return role;
			}
		}
		return new EmployeeRole();

	}
}
