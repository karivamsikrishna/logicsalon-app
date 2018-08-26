package com.x;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.x.logic.salon.app.util.RandomGenerator;
import com.x.test.LeaveType;

public class LeaveTypeTest {

	public static void main(String[] args) {
		Gson gson = new Gson();
		RandomGenerator generator = new RandomGenerator();
		List<LeaveType> list = new ArrayList<>();
		LeaveType leaveType = new LeaveType();
		leaveType.setLeaveId(generator.getRandomNumber(5));
		leaveType.setLeaveType(generator.getRandomString(5));
		list.add(leaveType);
		leaveType = new LeaveType();
		leaveType.setLeaveId(generator.getRandomNumber(5));
		leaveType.setLeaveType(generator.getRandomString(5));
		list.add(leaveType);
		
		System.out.println(gson.toJson(list));
		System.out.println(gson.toJson(leaveType));
	}

}
