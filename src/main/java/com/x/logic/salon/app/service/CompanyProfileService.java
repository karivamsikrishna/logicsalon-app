package com.x.logic.salon.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.controller.CompanyProfileController;
import com.x.logic.salon.app.data.modal.CompanyDetails;
import com.x.logic.salon.app.repos.CompanyRepository;
import com.x.logic.salon.app.response.company.CompanyProfileResponse;
import com.x.logic.salon.app.response.message.Message;

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
	public CompanyProfileResponse addCompanyProfile(@RequestBody CompanyDetails companyDetails) {

		CompanyProfileResponse companyProfileResponse = new CompanyProfileResponse();
		companyProfileResponse.setCompanyName(companyDetails.getCompanyName());

		CompanyProfileController companyProfileController = new CompanyProfileController();
		boolean isCompanyExist = companyProfileController.isCompanyProfileExist(companyRepository, companyDetails);
		Message message = new Message();
		if (isCompanyExist) {
			message.setErrorMessage("Company profile alreay exist!.");
			companyProfileResponse.setMessage(message);
		} else {
			boolean isStoreExist = companyProfileController.isStoreExistFromAvailableList(companyRepository,
					companyDetails);
			if (isStoreExist) {
				message.setErrorMessage("Given Store details already linked with different company!");
			} else {
				companyRepository.save(companyDetails);
				message.setSuccessMessage("Company Profile created successfully.");
			}
			companyProfileResponse.setMessage(message);
		}

		return companyProfileResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public CompanyProfileResponse updateCompanyProfile(@RequestBody CompanyDetails companyDetails) {

		CompanyProfileResponse companyProfileResponse = new CompanyProfileResponse();
		companyProfileResponse.setCompanyName(companyDetails.getCompanyName());

		CompanyProfileController companyProfileController = new CompanyProfileController();
		boolean isCompanyExist = companyRepository.exists(companyDetails.getCompanyId());
		Message message = new Message();
		if (isCompanyExist) {
			boolean isStoreExist = companyProfileController.isStoreExistFromGivenList(companyRepository,
					companyDetails);
			if (isStoreExist) {
				message.setErrorMessage("Given Store details already linked with different company!");
			} else {
				companyRepository.save(companyDetails);
				message.setSuccessMessage("Company Profile updated successfully.");
			}
			companyProfileResponse.setMessage(message);
		} else {
			message.setErrorMessage("Given company details not exist!");
			companyProfileResponse.setMessage(message);
		}

		return companyProfileResponse;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<CompanyDetails> getAllCompanyProfiles() {
		return companyRepository.findAll();
	}

	@RequestMapping(value = "/id/{companyId}", method = RequestMethod.GET)
	public CompanyDetails getCompanyProfileById(@PathVariable String companyId) {
		return companyRepository.findOne(companyId);
	}

	@RequestMapping(value = "/name/{companyName}", method = RequestMethod.GET)
	public CompanyDetails getCompanyProfileByName(@PathVariable String companyName) {
		CompanyProfileController companyProfileController = new CompanyProfileController();
		return companyProfileController.getCompanyDetailsByName(companyRepository, companyName);
	}
}
