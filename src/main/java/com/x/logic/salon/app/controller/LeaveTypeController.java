package com.x.logic.salon.app.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.x.logic.salon.app.data.modal.LeaveType;
import com.x.logic.salon.app.repos.LeaveTypeRepository;

public class LeaveTypeController {

	public boolean validateLeaveTypeForCreating(LeaveTypeRepository leaveTypeRepository, LeaveType leaveType) {

		if (!StringUtils.isBlank(leaveType.getLeaveType())) {
			String leaveTypeUpperCase = leaveType.getLeaveType().toUpperCase();
			List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
			for (LeaveType leave : leaveTypeList) {
				if (leaveTypeUpperCase.equals(leave.getLeaveType().toUpperCase())) {
					return false;
				}
			}
		} else {
			return false;
		}

		return true;
	}

	public boolean validateLeaveTypeForUpdating(LeaveTypeRepository leaveTypeRepository, LeaveType leaveType) {

		if (!StringUtils.isBlank(leaveType.getLeaveType()) && !StringUtils.isBlank(leaveType.getLeaveId())) {
			if (leaveTypeRepository.exists(leaveType.getLeaveId())) {
				return true;
			}
		}
		return false;
	}

	public LeaveType getLeaveTypeByName(LeaveTypeRepository leaveTypeRepository, String leaveTypeName) {

		String leaveTypeNameUpperCase = leaveTypeName.toUpperCase();
		List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
		for (LeaveType leave : leaveTypeList) {
			if (leaveTypeNameUpperCase.equals(leave.getLeaveType().toUpperCase())) {
				return leave;
			}
		}

		return new LeaveType();
	}
}
