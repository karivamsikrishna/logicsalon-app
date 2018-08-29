package com.x.logic.salon.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.x.logic.salon.app.data.modal.CompanyDetails;
import com.x.logic.salon.app.data.modal.Procedure;
import com.x.logic.salon.app.data.modal.Store;
import com.x.logic.salon.app.repos.CompanyRepository;
import com.x.logic.salon.app.repos.ProcedureRepository;
import com.x.logic.salon.app.repos.StoreRepository;

public class StoreController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private CompanyRepository companyRepository;
	private StoreRepository storeRepository;
	private ProcedureRepository procedureRepository;

	public StoreController(CompanyRepository companyRepository, StoreRepository storeRepository,
			ProcedureRepository procedureRepository) {
		this.companyRepository = companyRepository;
		this.storeRepository = storeRepository;
		this.procedureRepository = procedureRepository;
	}

	private boolean isStoreNameExist(Store store) {
		List<Store> storeList = storeRepository.findAll();
		String storeNameToUpperCase = store.getStoreName().toUpperCase();
		for (Store s : storeList) {
			if (storeNameToUpperCase.equals(s.getStoreName().toUpperCase())) {
				return false;
			}
		}
		return true;
	}

	public boolean isStoreValidToAdd(String companyId, Store store) {

		if (companyRepository.exists(companyId)) {
			if (isStoreNameExist(store)) {
				if (isProceduresExist(store.getProcedures())) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isProceduresExist(List<Procedure> procedureList) {
		if (procedureList != null) {
			for (Procedure procedure : procedureList) {
				if (!procedureRepository.exists(procedure.getProcedureId())) {
					return false;
				}
			}
		}

		return true;
	}

	public Store addStore(String companyId, Store store) {
		Store s = storeRepository.save(store);
		CompanyDetails companyDetails = companyRepository.findOne(companyId);
		List<Store> storeList = companyDetails.getStores();
		if (storeList == null) {
			storeList = new ArrayList<>();
		}
		storeList.add(s);
		companyDetails.setStores(storeList);
		companyRepository.save(companyDetails);
		return s;
	}

	public boolean isStoreValidToUpdate(String companyId, Store store) {

		if (companyRepository.exists(companyId)) {
			if (storeRepository.exists(store.getStoreId())) {
				if (isProceduresExist(store.getProcedures())) {
					return true;
				}
			}
		}

		return false;
	}

	public Store updateStore(String companyId, Store store) {
		Store s = storeRepository.save(store);
		CompanyDetails companyDetails = companyRepository.findOne(companyId);
		List<Store> storeList = companyDetails.getStores();
		for (Store st : storeList) {
			if (s.getStoreId().equals(st.getStoreId())) {
				st.setAddress(s.getAddress());
				st.setDisplayName(s.getDisplayName());
				st.setProcedures(s.getProcedures());
				st.setStoreName(s.getStoreName());
				st.setTradingHours(s.getTradingHours());
				st.setOperational(s.isOperational());
				break;
			}
		}

		companyDetails.setStores(storeList);
		companyRepository.save(companyDetails);
		return s;
	}

}
