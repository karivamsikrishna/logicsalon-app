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

import com.x.logic.salon.app.controller.CompanyProfileController;
import com.x.logic.salon.app.data.modal.CompanyDetails;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.CompanyRepository;
import com.x.logic.salon.app.response.company.CompanyProfileResponse;

@RestController
@RequestMapping(value = "/company/profile")
public class CompanyProfileService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final CompanyRepository companyRepository;

	public CompanyProfileService(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<CompanyProfileResponse> addCompanyProfile(@RequestBody CompanyDetails companyDetails) {

		CompanyProfileResponse companyProfileResponse = new CompanyProfileResponse();
		Message message = new Message();
		if (!StringUtils.isEmpty(companyDetails.getCompanyId())) {
			message.setErrorMessage("Operation invalide.");
			companyProfileResponse.setMessage(message);
			return new ResponseEntity<CompanyProfileResponse>(companyProfileResponse, HttpStatus.OK);
		}

		CompanyProfileController companyProfileController = new CompanyProfileController();
		boolean isCompanyExist = companyProfileController.isCompanyProfileExist(companyRepository, companyDetails);

		if (isCompanyExist) {
			message.setErrorMessage("Company profile alreay exist!.");
		} else {
			CompanyDetails company = companyRepository.save(companyDetails);
			companyProfileResponse.setCompany(company);
			message.setSuccessMessage("Company Profile created successfully.");

		}

		companyProfileResponse.setMessage(message);

		return new ResponseEntity<CompanyProfileResponse>(companyProfileResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<CompanyProfileResponse> updateCompanyProfile(@RequestBody CompanyDetails companyDetails) {

		CompanyProfileResponse companyProfileResponse = new CompanyProfileResponse();
		Message message = new Message();
		if (StringUtils.isEmpty(companyDetails.getCompanyId())) {
			message.setErrorMessage("No Company Id present.");
			companyProfileResponse.setMessage(message);
			return new ResponseEntity<CompanyProfileResponse>(companyProfileResponse, HttpStatus.OK);
		}
		CompanyProfileController companyProfileController = new CompanyProfileController();
		boolean isCompanyExist = companyRepository.exists(companyDetails.getCompanyId());

		if (isCompanyExist) {
			CompanyDetails company = companyProfileController.updateCompanyDetails(companyRepository, companyDetails);
			companyProfileResponse.setCompany(company);
			message.setSuccessMessage("Company Details are updated!");
		} else {
			message.setErrorMessage("Given company details not exist!");

		}
		companyProfileResponse.setMessage(message);
		return new ResponseEntity<CompanyProfileResponse>(companyProfileResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyDetails>> getAllCompanyProfiles() {
		return new ResponseEntity<List<CompanyDetails>>(companyRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/id/{companyId}", method = RequestMethod.GET)
	public ResponseEntity<CompanyDetails> getCompanyProfileById(@PathVariable String companyId) {
		return new ResponseEntity<CompanyDetails>(companyRepository.findOne(companyId), HttpStatus.OK);
	}

	@RequestMapping(value = "/name/{companyName}", method = RequestMethod.GET)
	public ResponseEntity<CompanyDetails> getCompanyProfileByName(@PathVariable String companyName) {
		CompanyProfileController companyProfileController = new CompanyProfileController();
		return new ResponseEntity<CompanyDetails>(
				companyProfileController.getCompanyDetailsByName(companyRepository, companyName), HttpStatus.OK);
	}
}
