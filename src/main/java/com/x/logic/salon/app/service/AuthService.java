package com.x.logic.salon.app.service;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.logic.salon.app.message.Message;
import com.x.logic.salon.app.reqresp.LoginRequest;
import com.x.logic.salon.app.reqresp.LoginResponse;
import com.x.logic.salon.app.sso.client.ServiceController;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest,
			HttpServletResponse response) {

		ServiceController serviceController = new ServiceController();
		LoginResponse loginResponse = new LoginResponse();
		ResponseEntity<LoginResponse> responseEntity = serviceController.login(loginRequest, response, loginResponse);
		setResponseCookie(responseEntity.getHeaders(), response);
		return responseEntity;

	}

	@RequestMapping(value = "/logout/{userName}", method = RequestMethod.GET)
	public ResponseEntity<Message> logoutUser(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String userName) {
		eraseCookie(request, response);
		ServiceController serviceController = new ServiceController();
		ResponseEntity<Message> responseEntity = serviceController.logout(request, response, userName);
		return responseEntity;
	}

	private void setResponseCookie(HttpHeaders headers, HttpServletResponse response) {
		Cookie myCookie = new Cookie("auth-token", "");
		myCookie.setValue(headers.getFirst("auth-token"));
		myCookie.setPath("/");
		myCookie.setMaxAge(3600);
		response.addCookie(myCookie);

	}
	
	public void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null)
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setValue(null);
				cookies[i].setMaxAge(0);
				resp.addCookie(cookies[i]);
			}
	}

	private void setResponseCookie(ServletResponse response, String token) {
		HttpServletResponse httpResp = (HttpServletResponse) response;
		Cookie myCookie = new Cookie("auth-token", "");
		myCookie.setValue(token);
		myCookie.setPath("/");
		// myCookie.setDomain(".mydomain.com");
		httpResp.addCookie(myCookie);

	}
}
