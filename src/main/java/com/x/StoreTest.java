package com.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mongodb.selector.PrimaryServerSelector;
import com.x.logic.salon.app.util.RandomGenerator;
import com.x.test.Address;
import com.x.test.EmployeeRole;
import com.x.test.Procedure;
import com.x.test.ProcedureStep;
import com.x.test.Store;

public class StoreTest {

	public static void main(String[] args) {
		StoreTest storeTest = new StoreTest();
		Gson gson = new Gson();
		RandomGenerator generator = new RandomGenerator();
		List<Store> storeList = new ArrayList<>();

		storeList.add(storeTest.getStoreinfo(generator, storeTest));
		storeList.add(storeTest.getStoreinfo(generator, storeTest));
		System.out.println(gson.toJson(storeList));
		System.out.println(gson.toJson(storeTest.getStoreinfo(generator, storeTest)));

	}
	
	public Store getStoreinfo(RandomGenerator generator, StoreTest storeTest) {
		Store store = new Store();
		store.setStoreId(generator.getRandomNumber(5));
		store.setStoreName(generator.getRandomString(5));
		store.setDisplayName(generator.getRandomAlphaNumaric(5));
		store.setProcedures(storeTest.getProcedureList(storeTest, generator));
		store.setAddress(storeTest.getAddress(generator));	
		store.setTradingHours(storeTest.getTradingHours());
		
		return store;
	}
	
	public Map<String, String> getTradingHours(){
		
		Map<String, String> tradingHours = new HashMap<>();
		tradingHours.put("MON", "8-16");
		tradingHours.put("TUE", "8-16");
		tradingHours.put("WED", "8-16");
		tradingHours.put("THR", "8-16");
		tradingHours.put("FRI", "8-16");
		tradingHours.put("SAT", "8-13");
		tradingHours.put("SUN", "8-13");
		
		return tradingHours;
	}
	
	public Address getAddress(RandomGenerator generator) {
		Address address = new Address();
		address.setId(Integer.parseInt(generator.getRandomNumber(5)));
		address.setPinCode(generator.getRandomNumber(4));
		address.setProvence(generator.getRandomString(5));
		address.setStreet(generator.getRandomString(5));
		address.setSubrub(generator.getRandomString(5));
		address.setAddress(generator.getRandomString(10));
		return address;
	}
	
	public List<Procedure> getProcedureList(StoreTest storeTest, RandomGenerator generator){
		List<Procedure> procedureList = new ArrayList<>();
		procedureList.add(storeTest.getProcedure(generator));
		procedureList.add(storeTest.getProcedure(generator));
		return procedureList;
	}
	
	public Procedure getProcedure(RandomGenerator generator) {
		Procedure procedure = new Procedure();
		procedure.setProcedureId(generator.getRandomNumber(5));
		procedure.setProcedureName(generator.getRandomString(5));
		List<ProcedureStep> procedureSteps = getProcedureSteps(generator);
		procedure.setProcedureSteps(procedureSteps);
		procedure.setTotalTimeRequired(60);
		String[] order = { procedureSteps.get(0).getStepId(), "break_30", procedureSteps.get(1).getStepId() };
		procedure.setExecutionOrder(order);
		return procedure;
	}

	public List<ProcedureStep> getProcedureSteps(RandomGenerator generator) {
		List<ProcedureStep> ProcedureStepList = new ArrayList<>();
		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleId(generator.getRandomNumber(5));
		employeeRole.setRoleName(generator.getRandomString(5));
		ProcedureStep procedureStep = new ProcedureStep();
		procedureStep.setStepId(generator.getRandomNumber(5));
		procedureStep.setStepName(generator.getRandomString(5));
		procedureStep.setTimeRequired(30);
		procedureStep.setRole(employeeRole);
		ProcedureStepList.add(procedureStep);

		procedureStep = new ProcedureStep();
		procedureStep.setStepId(generator.getRandomNumber(5));
		procedureStep.setStepName(generator.getRandomString(5));
		procedureStep.setTimeRequired(30);
		procedureStep.setRole(employeeRole);
		ProcedureStepList.add(procedureStep);
		return ProcedureStepList;
	}

}
