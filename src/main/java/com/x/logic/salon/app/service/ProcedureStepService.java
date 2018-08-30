package com.x.logic.salon.app.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.controller.ProcedureStepController;
import com.x.logic.salon.app.data.modal.ProcedureStep;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.EmployeeRolesRepository;
import com.x.logic.salon.app.repos.ProcedureStepRepository;
import com.x.logic.salon.app.response.procedure.step.ProcedureStepResponse;

@RestController
@RequestMapping(value = "/admin/procedure/step")
public class ProcedureStepService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final ProcedureStepRepository procedureStepRepository;
	private final EmployeeRolesRepository employeeRolesRepository;

	public ProcedureStepService(ProcedureStepRepository procedureStepRepository,
			EmployeeRolesRepository employeeRolesRepository) {
		this.procedureStepRepository = procedureStepRepository;
		this.employeeRolesRepository = employeeRolesRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<ProcedureStepResponse> createProcedureStep(@RequestBody ProcedureStep procedureStep) {
		LOG.info("-------->createProcedureStep");
		ProcedureStepResponse procedureStepResponse = new ProcedureStepResponse();
		Message message = new Message();

		if (!StringUtils.isEmpty(procedureStep.getStepId())) {
			message.setErrorMessage("Id Presernt");
			procedureStepResponse.setMessage(message);
			return new ResponseEntity<ProcedureStepResponse>(procedureStepResponse, HttpStatus.OK);
		}

		ProcedureStepController controller = new ProcedureStepController(procedureStepRepository,
				employeeRolesRepository);
		boolean isValide = controller.isValideTocreateProcedureStep(procedureStep);
		if (isValide) {
			ProcedureStep step = procedureStepRepository.save(procedureStep);
			procedureStepResponse.setProcedureStep(step);
			message.setSuccessMessage("Procedure Step created successfully.");
		} else {
			message.setErrorMessage("Procedure Step creation failed!");
		}
		procedureStepResponse.setMessage(message);

		return new ResponseEntity<ProcedureStepResponse>(procedureStepResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ProcedureStepResponse> updateProcedureStep(@RequestBody ProcedureStep procedureStep) {
		LOG.info("-------->updateProcedureStep");
		ProcedureStepResponse procedureStepResponse = new ProcedureStepResponse();
		Message message = new Message();

		if (StringUtils.isEmpty(procedureStep.getStepId())) {
			message.setErrorMessage("Id not Presernt");
			procedureStepResponse.setMessage(message);
			return new ResponseEntity<ProcedureStepResponse>(procedureStepResponse, HttpStatus.OK);
		}
		ProcedureStepController controller = new ProcedureStepController(procedureStepRepository,
				employeeRolesRepository);
		boolean isValide = controller.isValideToUpdateProcedureStep(procedureStep);
		if (isValide) {
			ProcedureStep step = procedureStepRepository.save(procedureStep);
			procedureStepResponse.setProcedureStep(step);
			message.setSuccessMessage("Procedure Step updated successfully.");
		} else {
			message.setErrorMessage("Procedure Step update failed!");
		}
		procedureStepResponse.setMessage(message);

		return new ResponseEntity<ProcedureStepResponse>(procedureStepResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<ProcedureStep>> getAllProcedureSteps() {
		LOG.info("-------->getAllProcedureSteps");
		return new ResponseEntity<List<ProcedureStep>>(procedureStepRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/id/{procedureId}", method = RequestMethod.GET)
	public ResponseEntity<ProcedureStep> getProcedureStepById(@PathVariable String procedureId) {
		LOG.info("-------->getProcedureStepById");
		return new ResponseEntity<ProcedureStep>(procedureStepRepository.findOne(procedureId), HttpStatus.OK);
	}

	@RequestMapping(value = "/name/{procedureStepName}", method = RequestMethod.GET)
	public ResponseEntity<ProcedureStep> getProcedureStepByName(@PathVariable String procedureStepName) {
		LOG.info("-------->getProcedureStepByName");
		ProcedureStepController controller = new ProcedureStepController(procedureStepRepository,
				employeeRolesRepository);
		ProcedureStep step = controller.getProcedureStepByName(procedureStepName);
		return new ResponseEntity<ProcedureStep>(step, HttpStatus.OK);
	}
}
