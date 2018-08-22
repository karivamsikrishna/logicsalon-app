package com.x.logic.salon.app;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.x.logic.salon.app.sso.client.ServiceController;

public class LoginValidationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Cookie[] cookies = req.getCookies();
		String authToken = null;
		String userId = req.getHeader("userName");
		System.out.println("Inside LoginValidationFilter: " + req.getRequestURI());

		if (cookies == null) {
			setErrorResponse(response, "1", "No cookies available");
		} else if (userId == null) {
			setErrorResponse(response, "1", "No user found");
		} else {
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName();
				String value = cookies[i].getValue();
				if ("auth-token".equals(name) && !"".equals(value) && value != null) {
					authToken = value;
					break;
				}
			}

			if (authToken != null && "".equals(authToken) && userId != null && "".equals(userId)) {
				ServiceController controller = new ServiceController();
				String newAuthToken = controller.validateRequest(req, res, userId);
				if (newAuthToken != null && !"".equals(newAuthToken)) {
					setResponseCookie(response, newAuthToken);
					chain.doFilter(request, response);
				} else {
					setErrorResponse(response, "1", "New token is blank.");
				}
			} else {
				setErrorResponse(response, "1", "cookie values are not available");
			}
		}

	}

	private void setResponseCookie(ServletResponse response, String token) {
		HttpServletResponse httpResp = (HttpServletResponse) response;
		Cookie myCookie = new Cookie("auth-token", "");
		myCookie.setValue(token);
		myCookie.setPath("/");
		myCookie.setMaxAge(3600);
		// myCookie.setDomain(".mydomain.com");
		httpResp.addCookie(myCookie);

	}

	@Override
	public void destroy() {
	}

	public void setErrorResponse(ServletResponse response, String code, String message) throws IOException {

		response.getWriter().write(message);
	}

}
