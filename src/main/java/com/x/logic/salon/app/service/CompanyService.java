package com.x.logic.salon.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.data.modal.CompanyDetails;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.CompanyRepository;
import com.x.logic.salon.app.response.company.CompanyProfileResponse;

@RestController
@RequestMapping(value = "/company/test")
public class CompanyService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public CompanyProfileResponse addCompanyProfile(@RequestBody CompanyDetails companyDetails) {
		System.out.println("1");
		CompanyProfileResponse companyProfileResponse = new CompanyProfileResponse();
		System.out.println("2");
		companyProfileResponse.setCompanyName(companyDetails.getCompanyName());
		System.out.println("3");
		Message message = new Message();
		System.out.println("4");
		message.setSuccessMessage("SUCCESS");
		System.out.println("5");
		companyProfileResponse.setMessage(message);
		System.out.println("6");
		companyRepository.save(companyDetails);
		System.out.println("7");
		return companyProfileResponse;
	}

}
