package com.x.logic.salon.app.controller;

import java.util.List;

import com.x.logic.salon.app.data.modal.PublicHoliday;
import com.x.logic.salon.app.repos.PublicHolidaysRepository;

public class PublicHolidayController {

	public boolean validateForCreatingPublicHoliday(PublicHolidaysRepository holidaysRepository,
			PublicHoliday publicHoliday) {

		List<PublicHoliday> publicHolidayList = holidaysRepository.findAll();
		String publicHolidayNameUpperCase = publicHoliday.getHolidayName().toUpperCase();
		for (PublicHoliday holiday : publicHolidayList) {
			if (publicHolidayNameUpperCase.equals(holiday.getHolidayName().toUpperCase())) {
				return false;
			}
		}

		return true;

	}

	public boolean validateForUpdatingPublicHoliday(PublicHolidaysRepository holidaysRepository,
			PublicHoliday publicHoliday) {

		if (holidaysRepository.exists(publicHoliday.getHolidayId())) {
			return true;
		}

		return false;

	}

	public PublicHoliday getHolidayByName(PublicHolidaysRepository holidaysRepository, String holidayName) {

		List<PublicHoliday> holidaysList = holidaysRepository.findAll();
		String holidayNameToUpperCase = holidayName.toUpperCase();
		for (PublicHoliday holiday : holidaysList) {
			if (holidayNameToUpperCase.equals(holiday.getHolidayName().toUpperCase())) {
				return holiday;
			}
		}
		return new PublicHoliday();

	}
}
