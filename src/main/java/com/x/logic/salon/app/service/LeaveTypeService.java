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

import com.x.logic.salon.app.controller.LeaveTypeController;
import com.x.logic.salon.app.data.modal.LeaveType;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.LeaveTypeRepository;
import com.x.logic.salon.app.response.leave.type.LeaveTypeResponse;

@RestController
@RequestMapping(value = "/admin/leave/type")
public class LeaveTypeService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final LeaveTypeRepository leaveTypeRepository;

	public LeaveTypeService(LeaveTypeRepository leaveTypeRepository) {
		this.leaveTypeRepository = leaveTypeRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<LeaveTypeResponse> createLeaveType(@RequestBody LeaveType leaveType) {
		LOG.info("------------------->createLeaveType");
		Message message = new Message();
		LeaveTypeResponse leaveTypeResponse = new LeaveTypeResponse();
		if (!StringUtils.isEmpty(leaveType.getLeaveId())) {
			message.setErrorMessage("Operation invalide.");
			leaveTypeResponse.setMessage(message);
			return new ResponseEntity<LeaveTypeResponse>(leaveTypeResponse, HttpStatus.OK);
		}

		LeaveTypeController controller = new LeaveTypeController();
		boolean isValide = controller.validateLeaveTypeForCreating(leaveTypeRepository, leaveType);
		if (isValide) {
			LeaveType result = leaveTypeRepository.save(leaveType);
			message.setSuccessMessage("Leave Type added successfully");
			leaveTypeResponse.setLeaveType(result);

		} else {
			message.setErrorMessage("Leave Type not created.");
		}
		leaveTypeResponse.setMessage(message);

		return new ResponseEntity<LeaveTypeResponse>(leaveTypeResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<LeaveTypeResponse> updateLeaveType(@RequestBody LeaveType leaveType) {
		LOG.info("------------------->updateLeaveType");
		Message message = new Message();
		LeaveTypeResponse leaveTypeResponse = new LeaveTypeResponse();
		if (StringUtils.isEmpty(leaveType.getLeaveId())) {
			message.setErrorMessage("Empty Id.");
			leaveTypeResponse.setMessage(message);
			return new ResponseEntity<LeaveTypeResponse>(leaveTypeResponse, HttpStatus.OK);
		}

		LeaveTypeController controller = new LeaveTypeController();
		boolean isValide = controller.validateLeaveTypeForUpdating(leaveTypeRepository, leaveType);
		if (isValide) {
			LeaveType result = leaveTypeRepository.save(leaveType);
			message.setSuccessMessage("Leave Type updated successfully");
			leaveTypeResponse.setLeaveType(result);

		} else {
			message.setErrorMessage("Leave Type not updated.");
		}
		leaveTypeResponse.setMessage(message);

		return new ResponseEntity<LeaveTypeResponse>(leaveTypeResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<LeaveType>> getLeaveTypes() {
		LOG.info("------------------->get Leave Types");
		return new ResponseEntity<List<LeaveType>>(leaveTypeRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/id/{leaveTypeId}", method = RequestMethod.GET)
	public ResponseEntity<LeaveType> getLeaveTypeById(@PathVariable String leaveTypeId) {
		LOG.info("------------------->get Leave Type by Id");
		return new ResponseEntity<LeaveType>(leaveTypeRepository.findOne(leaveTypeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/name/{leaveTypeName}", method = RequestMethod.GET)
	public ResponseEntity<LeaveType> getLeaveTypeByName(@PathVariable String leaveTypeName) {
		LOG.info("------------------->get Leave Type by leaveTypeName");
		LeaveTypeController controller = new LeaveTypeController();
		LeaveType leaveType = controller.getLeaveTypeByName(leaveTypeRepository, leaveTypeName);
		return new ResponseEntity<LeaveType>(leaveType, HttpStatus.OK);
	}
}
