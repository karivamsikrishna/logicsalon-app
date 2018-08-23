package com.x.logic.salon.app.sso.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.reqresp.LoginRequest;
import com.x.logic.salon.app.reqresp.LoginResponse;



public class ServiceController {

/*	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}*/

	String serviceURL = "http://localhost:8080/sso/auth";

	public String validateRequest(HttpServletRequest request, HttpServletResponse response, String userName) {
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("auth-token", request.getHeader("auth-token"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> res = restTemplate.exchange(serviceURL + "/validation/" + userName, HttpMethod.GET,
				entity, String.class);

		String result = res.getBody();
		Gson gson = new Gson();
		Message message = gson.fromJson(result, Message.class);
		if (("").equals(message.getErrorMessage())) {
			HttpHeaders responseHeaders = res.getHeaders();
			if (responseHeaders.containsKey("auth-token")) {
				if (responseHeaders.get("auth-token") != null && ("").equals(responseHeaders.get("auth-token"))) {
					return responseHeaders.getFirst("auth-token");
				}
			}
		}
		return "";
	}

	public ResponseEntity<LoginResponse> login(LoginRequest loginRequest, HttpServletResponse response,
			LoginResponse loginResponse) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForEntity(serviceURL + "/login", loginRequest, LoginResponse.class);
	}

	public ResponseEntity<Message> logout(HttpServletRequest request, HttpServletResponse response, String userName) {

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("auth-token", request.getHeader("auth-token"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(serviceURL + "/logout/" + userName, HttpMethod.GET, entity, Message.class);
	}
}
