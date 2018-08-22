package com.x.logic.salon.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.x.logic.salon.app.data.modal.CompanyDetails;
import com.x.logic.salon.app.data.modal.UserCapability;
import com.x.logic.salon.app.data.modal.UserDetails;
import com.x.logic.salon.app.data.modal.UserType;
import com.x.logic.salon.app.repos.CompanyRepository;
import com.x.logic.salon.app.repos.UserCapabilityRepository;
import com.x.logic.salon.app.repos.UserRepository;
import com.x.logic.salon.app.repos.UserTypeRepository;
import com.x.logic.salon.app.util.EAD;

public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public String validateUserToAdd(UserDetails userDetails, UserRepository userRepository,
			UserTypeRepository userTypeRepository, CompanyRepository companyRepository,
			UserCapabilityRepository capabilityRepository) {

		LOG.info("validate User To Create.");
		if (isUserExist(userDetails.getEmail(), userRepository)) {
			LOG.info("1");
			return "User Already exist!";
		}

		if (!isUserTypeExist(userTypeRepository, userDetails.getUserType())) {
			LOG.info("2");
			return "User Type not exist!";
		}

		if (!userDetails.isRequestFromloggedInUser()) {
			LOG.info("3");
			if (!isUserExist(userDetails.getActionerUserName(), userRepository)) {
				LOG.info("4");
				return "Actioner User not exist!";
			}

			UserDetails actionerDetails = userRepository.findOne(userDetails.getActionerUserName().toUpperCase());

			if (!actionerCompanyIdValidate(actionerDetails.getCompanyList().get(0),
					userDetails.getCompanyList().get(0))) {
				LOG.info("5");
				return "Actioner not belongs to same company!";
			}

			if (!isValideUserCapability(capabilityRepository, actionerDetails.getUserType().getTypeId(),
					userDetails.getUserType())) {
				LOG.info("6");
				return "Actioner don't have rights to add a user!";
			}
		} else {
			if (!validateCompayProfileExist(companyRepository, userDetails.getCompanyList())) {
				return "Company does not exist!";
			}
		}

		return "";
	}

	public String validateUserToUpdate(UserDetails userDetails, UserRepository userRepository,
			UserTypeRepository userTypeRepository, CompanyRepository companyRepository,
			UserCapabilityRepository capabilityRepository) {

		LOG.info("validate User To Update.");
		if (!isUserExist(userDetails.getEmail(), userRepository)) {
			LOG.info("1.");
			return "User not exist!";
		}

		if (!isUserTypeExist(userTypeRepository, userDetails.getUserType())) {
			LOG.info("2.");
			return "User Type not exist!";
		}

		if (!userDetails.isRequestFromloggedInUser()) {
			LOG.info("3.");
			if (!isUserExist(userDetails.getActionerUserName(), userRepository)) {
				LOG.info("4.");
				return "Actioner User not exist!";
			}

			UserDetails actionerDetails = userRepository.findOne(userDetails.getActionerUserName().toUpperCase());

			if (!actionerCompanyIdValidateForUpdate(actionerDetails.getCompanyList().get(0),
					userDetails.getCompanyList())) {
				LOG.info("5.");
				return "Actioner not belongs to same company!";
			}

			if (!isValideUserCapability(capabilityRepository, actionerDetails.getUserType().getTypeId(),
					userDetails.getUserType())) {
				LOG.info("6.");
				return "Actioner don't have rights to add a user!";
			}
		} else {
			if (!validateCompayProfileExist(companyRepository, userDetails.getCompanyList())) {
				return "Company does not exist!";
			}
		}

		return "";
	}

	private boolean validateCompayProfileExist(CompanyRepository companyRepository, List<String> companyList) {
		List<CompanyDetails> companyDetailsList = companyRepository.findAll();
		List<String> companyIdList = new ArrayList<>();
		for (CompanyDetails companyDetails : companyDetailsList) {
			companyIdList.add(companyDetails.getCompanyId());
		}

		for (String companyId : companyList) {
			if (!companyIdList.contains(companyId)) {
				return false;
			}
		}
		return true;
	}

	private boolean actionerCompanyIdValidateForUpdate(String actionerCompanyId, List<String> companyList) {

		return companyList.contains(actionerCompanyId);
	}

	public boolean isUserExist(String userEmail, UserRepository userRepository) {
		LOG.info("is User exist.");
		List<UserDetails> userList = userRepository.findAll();
		String emailToUpperCase = userEmail.toUpperCase();
		for (UserDetails user : userList) {
			if (user.getEmail().toUpperCase().equals(emailToUpperCase)) {
				return true;
			}
		}

		return false;
	}

	public boolean isUserTypeExist(UserTypeRepository userTypeRepository, UserType userType) {
		LOG.info("is User Type exist.");
		UserType type = userTypeRepository.findOne(userType.getTypeId());
		if (type != null && type.getTypeName().toUpperCase().equals(userType.getTypeName().toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValideUserCapability(UserCapabilityRepository capabilityRepository, String capabilityId,
			UserType userType) {
		LOG.info("is User capabillity exist.");
		UserCapability userCapability = capabilityRepository.findOne(capabilityId);
		List<UserType> userTypeList = userCapability.getCapabilities();
		String userTypeIdToUpperCase = userType.getTypeId().toUpperCase();
		String userTypeNameToUpperCase = userType.getTypeName().toUpperCase();
		for (UserType type : userTypeList) {
			if (type.getTypeId().toUpperCase().equals(userTypeIdToUpperCase)
					&& type.getTypeName().toUpperCase().equals(userTypeNameToUpperCase)) {
				return true;
			}
		}
		return false;
	}

	public boolean actionerCompanyIdValidate(String actionerCompanyId, String userCompanyId) {
		return actionerCompanyId.toUpperCase().equals(userCompanyId.toUpperCase());
	}

	public UserDetails getEncryptedPassword(UserDetails userDetails) {
		userDetails.setPassword(EAD.encrypt("hfhfhfhfhfhfhfhf", "RandomInitVector", userDetails.getPassword()));
		return userDetails;
	}

}
