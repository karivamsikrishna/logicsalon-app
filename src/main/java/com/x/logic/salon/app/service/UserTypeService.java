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
import com.x.logic.salon.app.repos.UserTypeRepository;
import com.x.logic.salon.app.response.usertype.UserTypeCreateResponse;
import com.x.logic.salon.app.util.RandomGenerator;

@RestController
@RequestMapping(value = "/user/type")
public class UserTypeService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserTypeRepository userTypeRepository;

	public UserTypeService(UserTypeRepository userTypeRepository) {
		this.userTypeRepository = userTypeRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public UserTypeCreateResponse addNewUserType(@RequestBody UserType userType) {
		UserTypeController userTypeController = new UserTypeController();
		boolean isUserTypeExist = userTypeController.checkUserTypeExist(userTypeRepository, userType);
		UserTypeCreateResponse userTypeCreateResponse = new UserTypeCreateResponse();
		Message message = new Message();
		userTypeCreateResponse.setUserType(userType.getTypeName());
		if (isUserTypeExist) {
			message.setErrorMessage("User Type already exist.");
		} else {
			//userType.setTypeId(RandomGenerator.getRandomString(3)+"-"+RandomGenerator.getRandomNumber(3));
			userTypeRepository.save(userType);
			message.setSuccessMessage("User Type created.");
		}
		userTypeCreateResponse.setMessage(message);
		return userTypeCreateResponse;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<UserType>> getAllUserTypes(HttpServletResponse response, HttpServletRequest request) {
		/*Cookie[] cookies = request.getCookies();
		for(int i=0 ; i< cookies.length ; i++) {
			LOG.info("cokkie------------->"+cookies[i].getName() + "-" + cookies[i].getValue());
		}
		
		 
		response.addCookie(new Cookie("KVK", "kari"));
		response.addCookie(new Cookie("tata", "jai"));*/
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
	public UserTypeCreateResponse updateUserType(@RequestBody UserType userType) {

		UserTypeController userTypeController = new UserTypeController();
		UserTypeCreateResponse userTypeCreateResponse = new UserTypeCreateResponse();
		Message message = new Message();
		userTypeCreateResponse.setUserType(userType.getTypeName());
		boolean isUserTypeExist = userTypeRepository.exists(userType.getTypeId());// userTypeController.checkUserTypeExist(userTypeRepository,
																					// userType);
		if (isUserTypeExist) {
			boolean isUserTypeNameExistWithDifferentId = userTypeController
					.checkUserTypeExistForGivenList(userTypeRepository, userType);
			if (isUserTypeNameExistWithDifferentId) {
				message.setErrorMessage("User Type Already linked with different Id.");
			} else {
				userTypeRepository.save(userType);
				message.setSuccessMessage("User Type updated successfully.");
			}

		} else {
			message.setErrorMessage("User Type not exist!");
		}
		userTypeCreateResponse.setMessage(message);

		return userTypeCreateResponse;
	}
}
