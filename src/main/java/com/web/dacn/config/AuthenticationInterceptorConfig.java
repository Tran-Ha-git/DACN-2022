package com.web.dacn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.web.dacn.iterceptor.AuthorizationInterceptor;
import com.web.dacn.iterceptor.SiteAuthenticationInterceptor;


@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer{

	
	@Autowired
	private SiteAuthenticationInterceptor siteAuthenticationInterceptor;
	
	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(siteAuthenticationInterceptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns("/auth/login", "/static/**", "/css/**", "/js/**","/assets/**");	
		registry.addInterceptor(authorizationInterceptor);
	}
	
}
