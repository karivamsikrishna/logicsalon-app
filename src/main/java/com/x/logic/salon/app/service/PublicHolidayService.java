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

import com.x.logic.salon.app.controller.PublicHolidayController;
import com.x.logic.salon.app.data.modal.PublicHoliday;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.repos.PublicHolidaysRepository;
import com.x.logic.salon.app.response.publicholiday.PublicHolidayResponse;

@RestController
@RequestMapping(value = "/admin/public/holidays")
public class PublicHolidayService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final PublicHolidaysRepository holidaysRepository;

	public PublicHolidayService(PublicHolidaysRepository holidaysRepository) {
		this.holidaysRepository = holidaysRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<PublicHolidayResponse> createPublicHoliday(@RequestBody PublicHoliday publicHoliday) {
		LOG.info("----------------->createPublicHoliday");
		PublicHolidayResponse holidayResponse = new PublicHolidayResponse();
		Message message = new Message();

		if (!StringUtils.isEmpty(publicHoliday.getHolidayId())) {
			message.setErrorMessage("Id Presernt");
			holidayResponse.setMessage(message);
			return new ResponseEntity<PublicHolidayResponse>(holidayResponse, HttpStatus.OK);
		}

		PublicHolidayController publicHolidayController = new PublicHolidayController();
		boolean isValide = publicHolidayController.validateForCreatingPublicHoliday(holidaysRepository, publicHoliday);

		if (isValide) {
			PublicHoliday holiday = holidaysRepository.save(publicHoliday);
			message.setSuccessMessage("Public holiday added successfully.");
			holidayResponse.setHoliday(holiday);
		} else {
			message.setErrorMessage("Fail to add public holiday.");
		}
		holidayResponse.setMessage(message);

		return new ResponseEntity<PublicHolidayResponse>(holidayResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<PublicHolidayResponse> updatePublicHoliday(@RequestBody PublicHoliday publicHoliday) {
		LOG.info("----------------->updatePublicHoliday");
		PublicHolidayResponse holidayResponse = new PublicHolidayResponse();
		Message message = new Message();

		if (StringUtils.isEmpty(publicHoliday.getHolidayId())) {
			message.setErrorMessage("Id not Presernt");
			holidayResponse.setMessage(message);
			return new ResponseEntity<PublicHolidayResponse>(holidayResponse, HttpStatus.OK);
		}

		PublicHolidayController publicHolidayController = new PublicHolidayController();
		boolean isValide = publicHolidayController.validateForUpdatingPublicHoliday(holidaysRepository, publicHoliday);

		if (isValide) {
			PublicHoliday holiday = holidaysRepository.save(publicHoliday);
			message.setSuccessMessage("Public holiday updated successfully.");
			holidayResponse.setHoliday(holiday);
		} else {
			message.setErrorMessage("Fail to update public holiday.");
		}
		holidayResponse.setMessage(message);

		return new ResponseEntity<PublicHolidayResponse>(holidayResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<PublicHoliday>> getPublicHolidays() {
		LOG.info("----------------->getPublicHolidays");

		return new ResponseEntity<List<PublicHoliday>>(holidaysRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/id/{holidayId}", method = RequestMethod.GET)
	public ResponseEntity<PublicHoliday> getPublicHolidayById(@PathVariable String holidayId) {
		LOG.info("----------------->getPublicHolidayById");

		return new ResponseEntity<PublicHoliday>(holidaysRepository.findOne(holidayId), HttpStatus.OK);
	}

	@RequestMapping(value = "/name/{holidayName}", method = RequestMethod.GET)
	public ResponseEntity<PublicHoliday> getPublicHolidayByName(@PathVariable String holidayName) {
		LOG.info("----------------->getPublicHolidayByName");
		PublicHolidayController publicHolidayController = new PublicHolidayController();
		PublicHoliday holiday = publicHolidayController.getHolidayByName(holidaysRepository, holidayName);
		return new ResponseEntity<PublicHoliday>(holiday, HttpStatus.OK);
	}
}
