package com.x.logic.salon.app;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean abcFilter() {
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
		filterRegBean.setFilter(new LoginValidationFilter());
		filterRegBean.addUrlPatterns("/kvk/*", "/vamsi/*");
		filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
		return filterRegBean;
	}

}
