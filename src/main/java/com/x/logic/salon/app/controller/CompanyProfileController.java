package com.x.logic.salon.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.x.logic.salon.app.data.modal.CompanyDetails;
import com.x.logic.salon.app.data.modal.Store;
import com.x.logic.salon.app.repos.CompanyRepository;

public class CompanyProfileController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public boolean isCompanyProfileExist(CompanyRepository companyRepository, CompanyDetails companyDetails) {

		List<CompanyDetails> companyDetailsList = companyRepository.findAll();
		boolean isCompanyExist = false;
		String companyNameUpperCase = companyDetails.getCompanyName().toUpperCase();
		for (CompanyDetails details : companyDetailsList) {
			if (companyNameUpperCase.equals(details.getCompanyName().toUpperCase())) {
				isCompanyExist = true;
				break;
			}
		}
		return isCompanyExist;
	}

	public boolean isStoreExistFromAvailableList(CompanyRepository companyRepository, CompanyDetails companyDetails) {

		List<CompanyDetails> companyDetailsList = companyRepository.findAll();
		boolean isStoreExist = false;
		for (CompanyDetails details : companyDetailsList) {
			List<Store> storeList = details.getStores();
			for (Store store : companyDetails.getStores()) {
				if (isStoreExist(storeList, store.getStoreName())) {
					isStoreExist = true;
					break;
				}
			}
			if (isStoreExist) {
				break;
			}
		}
		return isStoreExist;
	}

	public boolean isStoreExistFromGivenList(CompanyRepository companyRepository, CompanyDetails companyDetails) {

		List<CompanyDetails> companyDetailsList = companyRepository.findAll();
		boolean isStoreExist = false;
		for (CompanyDetails details : companyDetailsList) {
			if (!details.getCompanyId().equals(companyDetails.getCompanyId())) {
				List<Store> storeList = details.getStores();
				for (Store store : companyDetails.getStores()) {
					if (isStoreExist(storeList, store.getStoreName())) {
						isStoreExist = true;
						break;
					}
				}
				if (isStoreExist) {
					break;
				}
			}
		}
		return isStoreExist;
	}

	public boolean isStoreExist(List<Store> storeList, String storeName) {
		boolean isStoreExist = false;
		String storeNameUpperCase = storeName.toUpperCase();
		for (Store store : storeList) {
			if (storeNameUpperCase.equals(store.getStoreName().toUpperCase())) {
				isStoreExist = true;
				break;
			}
		}
		return isStoreExist;
	}

	public CompanyDetails getCompanyDetailsByName(CompanyRepository companyRepository, String companyName) {
		List<CompanyDetails> companyDetailsList = companyRepository.findAll();
		CompanyDetails details = new CompanyDetails();
		String companyNameUpperCase = companyName.toUpperCase();
		for (CompanyDetails companyDetails : companyDetailsList) {
			if (companyDetails.getCompanyName().toUpperCase().equals(companyNameUpperCase)) {
				details = companyDetails;
			}
		}
		return details;
	}


}
