package com.x;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.x.logic.salon.app.util.RandomGenerator;
import com.x.test.UserCapability;
import com.x.test.UserType;

public class UserCapabilityTest {

	public static void main(String[] args) {
	
		Gson gson = new Gson();
		RandomGenerator generator = new RandomGenerator();
		UserCapability capability = new UserCapability();
		List<UserType> list = new ArrayList<>();
		UserType userType = new UserType();
		
		userType.setTypeId(generator.getRandomNumber(5));
		userType.setTypeName(generator.getRandomString(5));
		userType.setDisplayName(generator.getRandomAlphaNumaric(10));
		list.add(userType);
		
		userType = new UserType();
		userType.setTypeId(generator.getRandomNumber(5));
		userType.setTypeName(generator.getRandomString(5));
		userType.setDisplayName(generator.getRandomAlphaNumaric(10));
		list.add(userType);
		
		capability.setUserTypeId(userType.getTypeId());
		capability.setCapabilities(list);
		System.out.println(gson.toJson(capability));
		
		

	}

}
