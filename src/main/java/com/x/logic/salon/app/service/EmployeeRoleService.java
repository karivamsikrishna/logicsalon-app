package com.x.logic.salon.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.controller.EmployeeRoleController;
import com.x.logic.salon.app.data.modal.EmployeeRole;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.EmployeeRolesRepository;
import com.x.logic.salon.app.response.employee.role.EmployeeRoleResponse;

@RestController
@RequestMapping(value = "/admin/employee/role")
public class EmployeeRoleService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final EmployeeRolesRepository employeeRolesRepository;

	public EmployeeRoleService(EmployeeRolesRepository employeeRolesRepository) {
		this.employeeRolesRepository = employeeRolesRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<EmployeeRoleResponse> createEmployeeRole(@RequestBody EmployeeRole employeeRole) {

		LOG.info("------------->createEmployeeRole");
		EmployeeRoleResponse employeeRoleResponse = new EmployeeRoleResponse();
		Message message = new Message();
		EmployeeRoleController controller = new EmployeeRoleController();
		boolean isValide = controller.validateForCreatingEmployeeRole(employeeRolesRepository, employeeRole);
		if (isValide) {
			EmployeeRole role = employeeRolesRepository.save(employeeRole);
			employeeRoleResponse.setEmployeeRole(role);
			message.setSuccessMessage("Employee role is created.");
		} else {
			message.setErrorMessage("Employee role creation failed!");
		}
		employeeRoleResponse.setMessage(message);

		return new ResponseEntity<EmployeeRoleResponse>(employeeRoleResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<EmployeeRoleResponse> updateEmployeeRole(@RequestBody EmployeeRole employeeRole) {

		LOG.info("------------->updateEmployeeRole");
		EmployeeRoleResponse employeeRoleResponse = new EmployeeRoleResponse();
		Message message = new Message();
		EmployeeRoleController controller = new EmployeeRoleController();
		boolean isValide = controller.validateForUpdatingEmployeeRole(employeeRolesRepository, employeeRole);
		if (isValide) {
			EmployeeRole role = employeeRolesRepository.save(employeeRole);
			employeeRoleResponse.setEmployeeRole(role);
			message.setSuccessMessage("Employee role is updated.");
		} else {
			message.setErrorMessage("Employee role update failed!");
		}
		employeeRoleResponse.setMessage(message);

		return new ResponseEntity<EmployeeRoleResponse>(employeeRoleResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/id/{employeeRoleId}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeRole> getEmployeeRoleById(@PathVariable String employeeRoleId) {
		LOG.info("------------->getEmployeeRoleById");
		return new ResponseEntity<EmployeeRole>(employeeRolesRepository.findOne(employeeRoleId), HttpStatus.OK);
	}

	@RequestMapping(value = "/name/{employeeRoleName}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeRole> getEmployeeRoleByName(@PathVariable String employeeRoleName) {
		LOG.info("------------->getEmployeeRoleByName");
		EmployeeRoleController employeeRoleController = new EmployeeRoleController();
		EmployeeRole employeeRole = employeeRoleController.getEmployeeRoleByName(employeeRolesRepository,
				employeeRoleName);
		return new ResponseEntity<EmployeeRole>(employeeRole, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeRole>> getEmployeeRoles() {
		LOG.info("------------->getEmployeeRoles");
		return new ResponseEntity<List<EmployeeRole>>(employeeRolesRepository.findAll(), HttpStatus.OK);
	}
}
