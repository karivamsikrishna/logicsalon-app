package com.x.logic.salon.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.controller.UserController;
import com.x.logic.salon.app.data.modal.HistoryData;
import com.x.logic.salon.app.data.modal.UserDetails;
import com.x.logic.salon.app.data.modal.UserUpdateHistory;
import com.x.logic.salon.app.repos.CompanyRepository;
import com.x.logic.salon.app.repos.UserCapabilityRepository;
import com.x.logic.salon.app.repos.UserRepository;
import com.x.logic.salon.app.repos.UserTypeRepository;
import com.x.logic.salon.app.repos.UserUpdateRepository;
import com.x.logic.salon.app.response.message.Message;
import com.x.logic.salon.app.response.user.UserCreateResponse;

@RestController
@RequestMapping(value = "/user")
public class UserService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	private final UserUpdateRepository userUpdateRepository;

	private final UserTypeRepository userTypeRepository;

	private final CompanyRepository companyRepository;

	private final UserCapabilityRepository capabilityRepository;

	public UserService(UserRepository userRepository, UserUpdateRepository userUpdateRepository,
			UserTypeRepository userTypeRepository, CompanyRepository companyRepository,
			UserCapabilityRepository capabilityRepository) {
		this.userRepository = userRepository;
		this.userUpdateRepository = userUpdateRepository;
		this.userTypeRepository = userTypeRepository;
		this.companyRepository = companyRepository;
		this.capabilityRepository = capabilityRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public UserCreateResponse addNewUser(@RequestBody UserDetails userDetails) {
		UserController userController = new UserController();
		String dataValidated = userController.validateUserToAdd(userDetails, userRepository, userTypeRepository,
				companyRepository, capabilityRepository);
		UserCreateResponse createResponse = new UserCreateResponse();
		createResponse.setUserName(userDetails.getName());
		createResponse.setEmail(userDetails.getEmail());
		Message message = new Message();
		if (dataValidated.equals("")) {
			userDetails = userController.getEncryptedPassword(userDetails);
			userDetails.setEmail(userDetails.getEmail().toUpperCase());
			userRepository.save(userDetails);
			message.setSuccessMessage("User Created Sucesfully.");
		} else {
			message.setErrorMessage(dataValidated);
		}
		createResponse.setMessage(message);
		return createResponse;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<UserDetails> getAllUsers() {
		List<UserDetails> userDetailsList = userRepository.findAll();
		List<UserDetails> maskedUserDetailsList = new ArrayList<>();
		for (UserDetails userDetails : userDetailsList) {
			userDetails.setPassword("");
			maskedUserDetailsList.add(userDetails);
		}
		return maskedUserDetailsList;
	}

	@RequestMapping(value = "/{userEmail}", method = RequestMethod.GET)
	public UserDetails getUser(@PathVariable String userEmail) {
		LOG.info("Getting user with ID: {}.", userEmail);
		UserDetails userDetails = userRepository.findOne(userEmail.toUpperCase());
		if (userDetails != null) {
			userDetails.setPassword("");
		}
		return userDetails;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public UserCreateResponse updateNewUser(@RequestBody UserDetails userDetails) {
		userDetails.setEmail(userDetails.getEmail().toUpperCase());
		UserDetails oldDetails = userRepository.findOne(userDetails.getEmail());
		UserCreateResponse userCreateResponse = new UserCreateResponse();
		userCreateResponse.setUserName(userDetails.getName());
		Message message = new Message();
		if (oldDetails == null) {
			message.setErrorMessage("User not exist!");
		}
		UserController userController = new UserController();
		String dataValidated = userController.validateUserToUpdate(userDetails, userRepository, userTypeRepository,
				companyRepository, capabilityRepository);
		if (dataValidated.equals("")) {
			UserUpdateHistory userUpdateHistory = userUpdateRepository.findOne(userDetails.getEmail());
			List<HistoryData> historyDataList = new ArrayList<>();
			HistoryData historyData = new HistoryData();
			if (userUpdateHistory != null) {
				historyDataList = userUpdateHistory.getHistoryDatas();
			} else {
				userUpdateHistory = new UserUpdateHistory();
				userUpdateHistory.setUserName(userDetails.getEmail());
			}
			historyData.setPreviousData(oldDetails);
			historyData.setUpdatedData(userDetails);
			historyData.setUpdatedOn(new Date());
			historyDataList.add(historyData);
			userUpdateHistory.setHistoryDatas(historyDataList);
			userUpdateRepository.save(userUpdateHistory);

			userRepository.save(userDetails);
			message.setSuccessMessage("User data successfully updated.");
		} else {
			message.setErrorMessage(dataValidated);
		}

		userCreateResponse.setMessage(message);

		return userCreateResponse;
	}
}
