package com.x.logic.salon.app.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.controller.StoreController;
import com.x.logic.salon.app.data.modal.Store;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.CompanyRepository;
import com.x.logic.salon.app.repos.ProcedureRepository;
import com.x.logic.salon.app.repos.StoreRepository;
import com.x.logic.salon.app.response.store.StoreResponse;

@RestController
@RequestMapping(value = "/company/store")
public class StoreService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final StoreRepository storeRepository;
	private final ProcedureRepository procedureRepository;
	private final CompanyRepository companyRepository;

	public StoreService(StoreRepository storeRepository, ProcedureRepository procedureRepository,
			CompanyRepository companyRepository) {
		this.storeRepository = storeRepository;
		this.procedureRepository = procedureRepository;
		this.companyRepository = companyRepository;
	}

	@RequestMapping(value = "/create/{companyId}", method = RequestMethod.PUT)
	public ResponseEntity<StoreResponse> createStore(@RequestBody Store store, @PathVariable String companyId) {
		LOG.info("-------------->createStore");
		StoreResponse response = new StoreResponse();
		Message message = new Message();

		if (!StringUtils.isEmpty(store.getStoreId())) {
			message.setErrorMessage("Id Presernt");
			response.setMessage(message);
			return new ResponseEntity<StoreResponse>(response, HttpStatus.OK);
		}
		if (StringUtils.isEmpty(companyId)) {
			message.setErrorMessage("Company Id not Presernt");
			response.setMessage(message);
			return new ResponseEntity<StoreResponse>(response, HttpStatus.OK);
		}

		StoreController controller = new StoreController(companyRepository, storeRepository, procedureRepository);
		boolean isStoreValidToAdd = controller.isStoreValidToAdd(companyId, store);

		if (isStoreValidToAdd) {
			Store s = controller.addStore(companyId, store);
			response.setStore(s);
			message.setSuccessMessage("Store added successfully.");
		} else {
			message.setErrorMessage("Store not added");
		}
		response.setMessage(message);

		return new ResponseEntity<StoreResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{companyId}", method = RequestMethod.POST)
	public ResponseEntity<StoreResponse> updateStore(@RequestBody Store store, @PathVariable String companyId) {
		LOG.info("-------------->updateStore");
		StoreResponse response = new StoreResponse();
		Message message = new Message();

		if (StringUtils.isEmpty(store.getStoreId())) {
			message.setErrorMessage("Id not Presernt");
			response.setMessage(message);
			return new ResponseEntity<StoreResponse>(response, HttpStatus.OK);
		}
		if (StringUtils.isEmpty(companyId)) {
			message.setErrorMessage("Company Id not Presernt");
			response.setMessage(message);
			return new ResponseEntity<StoreResponse>(response, HttpStatus.OK);
		}

		StoreController controller = new StoreController(companyRepository, storeRepository, procedureRepository);
		boolean isStoreValidToUpdate = controller.isStoreValidToUpdate(companyId, store);

		if (isStoreValidToUpdate) {
			Store s = controller.updateStore(companyId, store);
			response.setStore(s);
			message.setSuccessMessage("Store updated successfully.");
		} else {
			message.setErrorMessage("Store not updated!");
		}
		response.setMessage(message);

		return new ResponseEntity<StoreResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{companyId}", method = RequestMethod.POST)
	public ResponseEntity<List<Store>> getAllStores(@PathVariable String companyId) {
		LOG.info("-------------->getAllStores");
		return new ResponseEntity<List<Store>>(companyRepository.findOne(companyId).getStores(), HttpStatus.OK);
	}

}
