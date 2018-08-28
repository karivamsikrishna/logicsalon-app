package com.x.logic.salon.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private boolean isValidStoreToAdd(Store store) {
		return storeRepository.exists(store.getStoreId());
	}

}
