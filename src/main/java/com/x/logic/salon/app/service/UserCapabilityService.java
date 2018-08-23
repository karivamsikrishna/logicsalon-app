package com.x.logic.salon.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.data.modal.UserCapability;
import com.x.logic.salon.app.data.modal.UserType;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.UserCapabilityRepository;
import com.x.logic.salon.app.repos.UserTypeRepository;
import com.x.logic.salon.app.response.usertype.UserCapabilityCreateResponse;

@RestController
@RequestMapping(value = "/user/capability")
public class UserCapabilityService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserCapabilityRepository userCapabilityRepository;
	private final UserTypeRepository userTypeRepository;

	public UserCapabilityService(UserCapabilityRepository userCapabilityRepository,
			UserTypeRepository userTypeRepository) {
		this.userCapabilityRepository = userCapabilityRepository;
		this.userTypeRepository = userTypeRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public UserCapabilityCreateResponse addNewUserCapabilities(@RequestBody UserCapability userCapability) {

		UserCapabilityCreateResponse capabilityCreateResponse = new UserCapabilityCreateResponse();
		Message message = new Message();
		capabilityCreateResponse.setUserCapability(userCapability);
		boolean isUserTypeExist = userTypeRepository.exists(userCapability.getUserTypeId());
		boolean isUserTypeCapabilitiesExist = true;
		if (isUserTypeExist) {
			List<UserType> userTypesList = userCapability.getCapabilities();
			for (UserType userType : userTypesList) {
				if (!userTypeRepository.exists(userType.getTypeId())) {
					isUserTypeCapabilitiesExist = false;
				}
			}
		} else {
			message.setErrorMessage("User Type is not exist!");
		}

		if (isUserTypeExist && isUserTypeCapabilitiesExist) {
			userCapabilityRepository.save(userCapability);
			message.setSuccessMessage("User Capabilities are saved.");
		} else if (isUserTypeExist && !isUserTypeCapabilitiesExist) {
			message.setErrorMessage("User capabilities are not exist!");
		}

		capabilityCreateResponse.setMessage(message);

		return capabilityCreateResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public UserCapabilityCreateResponse updateUserCapabilities(@RequestBody UserCapability userCapability) {

		UserCapabilityCreateResponse capabilityCreateResponse = new UserCapabilityCreateResponse();
		Message message = new Message();
		capabilityCreateResponse.setUserCapability(userCapability);
		boolean isUserTypeExist = userTypeRepository.exists(userCapability.getUserTypeId());
		boolean isUserTypeCapabilitiesExist = true;
		if (isUserTypeExist) {
			List<UserType> userTypesList = userCapability.getCapabilities();
			for (UserType userType : userTypesList) {
				if (!userTypeRepository.exists(userType.getTypeId())) {
					isUserTypeCapabilitiesExist = false;
				}
			}
		} else {
			message.setErrorMessage("User Type is not exist!");
		}

		if (isUserTypeExist && isUserTypeCapabilitiesExist) {
			userCapabilityRepository.save(userCapability);
			message.setSuccessMessage("User Capabilities are updated.");
		} else {
			message.setErrorMessage("User capabilities are not exist!");
		}

		capabilityCreateResponse.setMessage(message);

		return capabilityCreateResponse;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<UserCapability> getAllUserTypeCapabilities() {
		return userCapabilityRepository.findAll();
	}

	@RequestMapping(value = "/{userTypeId}", method = RequestMethod.GET)
	public UserCapability getUserTypeCapability(@PathVariable String userTypeId) {
		LOG.info("Getting user type with ID: {}.", userTypeId);
		return userCapabilityRepository.findOne(userTypeId);
	}

}
