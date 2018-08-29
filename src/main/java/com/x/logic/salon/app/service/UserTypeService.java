package com.x.logic.salon.app.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.controller.UserTypeController;
import com.x.logic.salon.app.data.modal.UserType;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.UserCapabilityRepository;
import com.x.logic.salon.app.repos.UserTypeRepository;
import com.x.logic.salon.app.response.usertype.UserTypeCreateResponse;
import com.x.logic.salon.app.util.RandomGenerator;

@RestController
@RequestMapping(value = "/user/type")
public class UserTypeService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserTypeRepository userTypeRepository;
	private final UserCapabilityRepository userCapabilityRepository;

	public UserTypeService(UserTypeRepository userTypeRepository, UserCapabilityRepository userCapabilityRepository) {
		this.userTypeRepository = userTypeRepository;
		this.userCapabilityRepository = userCapabilityRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<UserTypeCreateResponse> addNewUserType(@RequestBody UserType userType) {
		UserTypeController userTypeController = new UserTypeController();
		boolean isUserTypeExist = userTypeController.checkUserTypeExist(userTypeRepository, userType);
		UserTypeCreateResponse userTypeCreateResponse = new UserTypeCreateResponse();
		Message message = new Message();
		if (isUserTypeExist) {
			message.setErrorMessage("User Type already exist.");
		} else {
			UserType type = userTypeRepository.save(userType);
			userTypeCreateResponse.setUserType(type);
			message.setSuccessMessage("User Type created.");
		}
		userTypeCreateResponse.setMessage(message);
		return new ResponseEntity<UserTypeCreateResponse>(userTypeCreateResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<UserType>> getAllUserTypes(HttpServletResponse response, HttpServletRequest request) {

		List<UserType> userTypeList = userTypeRepository.findAll();

		return new ResponseEntity<List<UserType>>(userTypeList, HttpStatus.OK);
	}

	@RequestMapping(value = "/id/{userTypeId}", method = RequestMethod.GET)
	public UserType getUserTypeById(@PathVariable String userTypeId) {
		LOG.info("Getting user type with ID: {}.", userTypeId);
		return userTypeRepository.findOne(userTypeId);
	}

	@RequestMapping(value = "/name/{userTypeName}", method = RequestMethod.GET)
	public UserType getUserTypeByName(@PathVariable String userTypeName) {
		LOG.info("Getting user type with ID: {}.", userTypeName);
		UserTypeController userTypeController = new UserTypeController();
		return userTypeController.getUserTypeByName(userTypeRepository, userTypeName);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<UserTypeCreateResponse> updateUserType(@RequestBody UserType userType) {

		UserTypeController userTypeController = new UserTypeController();
		UserTypeCreateResponse userTypeCreateResponse = new UserTypeCreateResponse();
		Message message = new Message();
		boolean isUserTypeExist = userTypeRepository.exists(userType.getTypeId());
		if (isUserTypeExist) {
			boolean isUserTypeNameExistWithDifferentId = userTypeController
					.checkUserTypeExistForGivenList(userTypeRepository, userType);
			if (isUserTypeNameExistWithDifferentId) {
				message.setErrorMessage("User Type Already linked with different Id.");
			} else {
				UserType type = userTypeRepository.save(userType);
				userTypeCreateResponse.setUserType(type);
				userTypeController.updateUserCapabilities(userCapabilityRepository, type);
				message.setSuccessMessage("User Type updated successfully.");
			}

		} else {
			message.setErrorMessage("User Type not exist!");
		}
		userTypeCreateResponse.setMessage(message);

		return new ResponseEntity<UserTypeCreateResponse>(userTypeCreateResponse, HttpStatus.OK);
	}
}
