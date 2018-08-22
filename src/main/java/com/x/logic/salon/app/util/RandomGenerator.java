package com.x.logic.salon.app.util;

import org.apache.commons.lang.RandomStringUtils;

public class RandomGenerator {

	public static String getRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length).toUpperCase();
	}

	public static String getRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public static String getRandomAlphaNumaric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
}
