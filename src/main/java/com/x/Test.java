package com.x;

import org.apache.commons.lang.RandomStringUtils;

public class Test {

	public static void main(String[] args) {

		String generatedString = RandomStringUtils.randomAlphanumeric(5).toUpperCase();
		
		System.out.println(generatedString);
	}

}
