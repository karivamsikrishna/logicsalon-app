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

import com.x.logic.salon.app.controller.ProcedureController;
import com.x.logic.salon.app.data.modal.Procedure;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.EmployeeRolesRepository;
import com.x.logic.salon.app.repos.ProcedureRepository;
import com.x.logic.salon.app.repos.ProcedureStepRepository;
import com.x.logic.salon.app.respoinse.procedure.ProcedureResponse;

@RestController
@RequestMapping(value = "/admin/procedure")
public class ProcedureService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final ProcedureRepository procedureRepository;
	private final ProcedureStepRepository procedureStepRepository;
	private final EmployeeRolesRepository employeeRolesRepository;

	public ProcedureService(ProcedureRepository procedureRepository, ProcedureStepRepository procedureStepRepository,
			EmployeeRolesRepository employeeRolesRepository) {
		this.procedureRepository = procedureRepository;
		this.procedureStepRepository = procedureStepRepository;
		this.employeeRolesRepository = employeeRolesRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<ProcedureResponse> createProcedure(@RequestBody Procedure procedure) {
		LOG.info("----------->createProcedure");
		ProcedureResponse procedureResponse = new ProcedureResponse();
		Message message = new Message();
		ProcedureController controller = new ProcedureController(procedureRepository, procedureStepRepository,
				employeeRolesRepository);

		boolean isValide = controller.isValideToCreateProcedure(procedure);
		if (isValide) {
			Procedure proc = procedureRepository.save(procedure);
			procedureResponse.setProcedure(proc);
			message.setSuccessMessage("Procedure created successfully!");
		} else {
			message.setErrorMessage("Procedure created failed!");
		}
		procedureResponse.setMessage(message);

		return new ResponseEntity<ProcedureResponse>(procedureResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ProcedureResponse> updateProcedure(@RequestBody Procedure procedure) {
		LOG.info("----------->updateProcedure");
		ProcedureResponse procedureResponse = new ProcedureResponse();
		Message message = new Message();
		ProcedureController controller = new ProcedureController(procedureRepository, procedureStepRepository,
				employeeRolesRepository);

		boolean isValide = controller.isValideToUpdateProcedure(procedure);
		if (isValide) {
			Procedure proc = procedureRepository.save(procedure);
			procedureResponse.setProcedure(proc);
			message.setSuccessMessage("Procedure updated successfully!");
		} else {
			message.setErrorMessage("Procedure update failed!");
		}
		procedureResponse.setMessage(message);

		return new ResponseEntity<ProcedureResponse>(procedureResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Procedure>> getAllProcedures() {
		LOG.info("----------->getAllProcedures");
		return new ResponseEntity<List<Procedure>>(procedureRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/id/{procedureId}", method = RequestMethod.GET)
	public ResponseEntity<Procedure> getProcedureById(@PathVariable String procedureId) {
		LOG.info("----------->getProcedureById");
		return new ResponseEntity<Procedure>(procedureRepository.findOne(procedureId), HttpStatus.OK);
	}

	@RequestMapping(value = "/name/{procedureName}", method = RequestMethod.GET)
	public ResponseEntity<Procedure> getProcedureByName(@PathVariable String procedureName) {
		LOG.info("----------->getProcedureByName");
		ProcedureController controller = new ProcedureController(procedureRepository, procedureStepRepository,
				employeeRolesRepository);
		Procedure procedure = controller.getProcedureByName(procedureName);
		return new ResponseEntity<Procedure>(procedure, HttpStatus.OK);
	}

}
