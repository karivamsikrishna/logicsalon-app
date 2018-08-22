package com.x.logic.salon.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.x.logic.salon.app.data.modal.UserType;
import com.x.logic.salon.app.repos.UserTypeRepository;

public class UserTypeController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public boolean checkUserTypeExist(UserTypeRepository userTypeRepository, UserType userType) {
		boolean isUserTypeExist = false;
		String userTypeNameToUpperCase = userType.getTypeName().toUpperCase();
		List<UserType> userTypeList = userTypeRepository.findAll();
		for (UserType type : userTypeList) {
			if (type.getTypeName().toUpperCase().equals(userTypeNameToUpperCase)) {
				isUserTypeExist = true;
			}
		}

		return isUserTypeExist;
	}

	public UserType getUserTypeByName(UserTypeRepository userTypeRepository, String userTypeName) {
		UserType userType = new UserType();
		String userTypeNameToUpperCase = userTypeName.toUpperCase();
		List<UserType> userTypeList = userTypeRepository.findAll();
		for (UserType type : userTypeList) {
			if (type.getTypeName().toUpperCase().equals(userTypeNameToUpperCase)) {
				userType = type;
				break;
			}
		}

		return userType;
	}

	public boolean checkUserTypeExistForGivenList(UserTypeRepository userTypeRepository, UserType userType) {
		boolean isUserTypeNameExist = false;
		String userTypeNameToUpperCase = userType.getTypeName().toUpperCase();
		List<UserType> userTypeList = userTypeRepository.findAll();
		for (UserType type : userTypeList) {
			if (!userType.getTypeId().equals(type.getTypeId())) {
				if (type.getTypeName().toUpperCase().equals(userTypeNameToUpperCase)) {
					isUserTypeNameExist = true;
					break;
				}
			}

		}

		return isUserTypeNameExist;
	}
}
