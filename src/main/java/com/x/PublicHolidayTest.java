package com.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.x.logic.salon.app.util.RandomGenerator;
import com.x.test.PublicHoliday;

public class PublicHolidayTest {

	public static void main(String[] args) {
		Gson gson = new Gson();
		RandomGenerator generator = new RandomGenerator();
		List<PublicHoliday> list = new ArrayList<>();
		PublicHoliday holiday = new PublicHoliday();
		holiday.setHolidayId(generator.getRandomNumber(5));
		holiday.setHolidayName(generator.getRandomString(5));
		holiday.setHolidayYear("2018");
		holiday.setNoOfDays(1);
		holiday.setStartDate(new Date());
		holiday.setEndDate(new Date());
		list.add(holiday);
		
		holiday = new PublicHoliday();
		holiday.setHolidayId(generator.getRandomNumber(5));
		holiday.setHolidayName(generator.getRandomString(5));
		holiday.setHolidayYear("2018");
		holiday.setNoOfDays(1);
		holiday.setStartDate(new Date());
		holiday.setEndDate(new Date());
		list.add(holiday);
		
		System.out.println(gson.toJson(list));
		System.out.println(gson.toJson(holiday));

	}

}
