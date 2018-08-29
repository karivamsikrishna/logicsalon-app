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
		String companyNameUpperCase = companyDetails.getCompanyName().toUpperCase();
		for (CompanyDetails details : companyDetailsList) {
			if (companyNameUpperCase.equals(details.getCompanyName().toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	public CompanyDetails getCompanyDetailsByName(CompanyRepository companyRepository, String companyName) {
		List<CompanyDetails> companyDetailsList = companyRepository.findAll();
		String companyNameUpperCase = companyName.toUpperCase();
		for (CompanyDetails companyDetails : companyDetailsList) {
			if (companyDetails.getCompanyName().toUpperCase().equals(companyNameUpperCase)) {
				return companyDetails;
			}
		}
		return new CompanyDetails();
	}

	public CompanyDetails updateCompanyDetails(CompanyRepository companyRepository, CompanyDetails companyDetails) {
		CompanyDetails company = companyRepository.findOne(companyDetails.getCompanyId());
		company.setAddress(companyDetails.getAddress());
		company.setCompanyName(companyDetails.getCompanyName());
		return companyRepository.save(company);
	}

}
